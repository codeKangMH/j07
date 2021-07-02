package org.zerock.j07.board.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;



}
