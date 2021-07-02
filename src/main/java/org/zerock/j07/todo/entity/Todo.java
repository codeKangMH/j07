package org.zerock.j07.todo.entity;
// 21.06.28

import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;
import javax.persistence.*;

// 테이블 만들것 @entity 추가하면 todo에 에러남 @id 생성: 객체 자료형만 기본 자료형은 안돼
@Entity // gettet만 생성
@Table(name = "tbl_todo") // db에 table 자동 생성
//단순히 필드를 리턴하는 것. 필드 이름이 foo라면 메소드 이름은 getFoo, 필드 타입이 boolean이라면 isFoo
@Getter
//
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString // 로그값
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long tno;

    @Column(nullable = false, length = 300)
    private String content;
    //~ 총 4개의 컬럼 생성

    private boolean del; // 데이터에서는 0 아니면 1

    public void changeTitle(String content) {
        this.content = content;
    }

    // 21.07.01
    public void changeDel(boolean del) {
        this.del = del;

    }

}
