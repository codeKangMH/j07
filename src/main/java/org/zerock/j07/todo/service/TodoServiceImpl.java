package org.zerock.j07.todo.service;
// 21.07.01

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.PageMaker;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;
import org.zerock.j07.todo.dto.TodoDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    // 7.2
//    @Override
//    public ListResponseDTO<TodoDTO> list(ListRequestDTO dto) {
//
//        // 페이저블의 소트정보는 어떻게 처리할 것인가? 1.직접 만들기 2.반환코드 만들기
////        PageRequest.of(dto.getPage() -1)
//        Pageable pageable = dto.getPageable();
//
//        // 호출
//        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(), pageable);
//
////        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(),dto.getPaeable());
//
//        // 람다식
//        Function<Todo, TodoDTO> fn = (todo) -> entityToDTO(todo);
//
//        // 람다식과 같아
////        result.getContent().stream()
////                .map(todo -> entityToDTO(todo))
////                .collect(Collectors.toList());
//
//        List<TodoDTO> dtoList = result.getContent().stream()
//                .map(fn)
//                .collect(Collectors.toList());
//        Pageable pageResult = result.getPageable();
//        PageMaker pageMaker = new PageMaker((dto.getPage()), dto.getSize(), (int)result.getTotalElements());
//
//        log.info(pageMaker);
//
//        ListResponseDTO<TodoDTO> listResult = ListResponseDTO.<TodoDTO>builder()
//                .dtoList(dtoList)
//                .pageMaker(pageMaker)
//                .listRequestDTO(dto)
//                .build();
//
//        return listResult;
//
//        log.info(listResult);
//
//    }

    @Override
    public ListResponseDTO<TodoDTO> list(ListRequestDTO dto) {

        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(),dto.getPageable());

        List<TodoDTO> dtoList = result.getContent().stream()
                .map( (todo) -> entityToDTO(todo))
                .collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(dto.getPage(),dto.getSize(),(int)result.getTotalElements());

        log.info(pageMaker);

        return ListResponseDTO.<TodoDTO>builder()
                .dtoList(dtoList)
                .pageMaker(pageMaker)
                .listRequestDTO(dto)
                .build();

    }

    // 7.1
    @Override
    public Long register(TodoDTO dto) {

        log.info(dto);

        Todo entity = dtoToEntity(dto);

        todoRepository.save(entity);

        return entity.getTno();

    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        log.info(result);

        if (result.isPresent()) {
            Todo todo = result.get();
        }
        return null;

    }

    @Override
    public Long remove(Long tno) {

        todoRepository.deleteById(tno);

        return tno;

    }

    @Override
    public TodoDTO modify(TodoDTO todoDTO) {

        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

        if(result.isPresent()){

            Todo entity = result.get();
            entity.changeTitle(todoDTO.getContent());
            entity.changeDel(todoDTO.isDel());

            Todo saveResult = todoRepository.save(entity);

            return entityToDTO(saveResult);

        }
        return null;

    }

}
