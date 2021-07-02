package org.zerock.j07.board.entity;

// 21.06.30
import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;
import javax.persistence.*;

@Entity
@Table(name = "tbl_reply", indexes = @Index(name = "idx_board", columnList = "board_bno"))
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"board"}) // ** 테스트에서 @Transaction 노노
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    @ManyToOne(fetch = FetchType.LAZY)  // 무조건 lazy 걸어줘 디폴트
    private Board board; // Board 에러 왜? associate 해줘야해

}
