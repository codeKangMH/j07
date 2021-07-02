package org.zerock.j07.todo.controller;
// 21.07.01

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.service.TodoService;
import org.zerock.j07.todo.dto.TodoDTO;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;




    // 7.2
    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<TodoDTO>> list(ListRequestDTO listRequestDTO) {

        log.info(listRequestDTO);

        todoService.list(listRequestDTO);

        return ResponseEntity.ok(todoService.list(listRequestDTO));

    }
    // -> 서비스에 전달




    @PostMapping("/")
    //                  몇번 todo가 새로 떴는지 알려주기 위해
    public ResponseEntity<Long> register(@RequestBody TodoDTO dto) {

        log.info("........."+ dto);

        Long tno = todoService.register(dto);

        return ResponseEntity.ok().body(tno);

    }

    @GetMapping("/{tno}")
    public ResponseEntity<TodoDTO> read(@PathVariable Long tno) {

        TodoDTO dto = todoService.read(tno);

        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping("/{tno}")
    public ResponseEntity<Long> remove(@PathVariable("tno") Long tno)  {

        Long deletedTno = todoService.remove(tno);

        return ResponseEntity.ok().body(deletedTno);

    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long tno, @RequestBody TodoDTO todoDTO) {

        todoDTO.setTno(tno);

        log.info(todoDTO);

        TodoDTO resultDTO = todoService.modify(todoDTO);

        return ResponseEntity.ok(resultDTO);

    }

}

// 의존성 주입에 문제가 없는지 먼저 확인


