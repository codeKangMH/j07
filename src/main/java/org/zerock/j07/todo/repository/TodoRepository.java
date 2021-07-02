package org.zerock.j07.todo.repository;
// 21.06.28

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.dynamic.TodoSearch;

//                                                   <entity, pk>
public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

    //7.1
//    @Query("select t from Todo t order by t.tno desc ") // 이 구문이 잘못되면 프로젝트 자체가 실행이 안된다. 테스트 꼭해 중요 **
    @Query("select t from Todo t where t.content like concat('%',:keyword,'%') order by t.tno desc ")
    Page<Todo> getList(String keyword, Pageable pageable);

    @Modifying
    @Query("update Todo set content=:content where tno=:tno") //쿼리는 셀렉트만
    void updateContent(String content, Long tno);
}
