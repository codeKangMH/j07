package org.zerock.j07.todo.dto;
/* 21.07.01 3시
* dto 역할
*
*
* */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    // 등록할 때
    // 컨트롤러 입장에서는 dto만 가지고 실행

    private Long tno;
    private String content;
    private boolean del;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // java8버전에서는 인터페이스 defalt



}
