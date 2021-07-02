package org.zerock.j07.board.entity;
// 7.2


import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "favorite", indexes = @Index(name = "idx_board", columnList = "board_bno"))
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "board")
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private boolean mark;

    private String actor;

    @ManyToOne
    private Board board;


}
