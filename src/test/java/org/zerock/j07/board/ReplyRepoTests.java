package org.zerock.j07.board;

// 21.06.30
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.entity.Reply;
import org.zerock.j07.board.repository.ReplyRepository;
import java.util.Optional;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class ReplyRepoTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void test1() {
        log.info(replyRepository.getClass().getName());

    }

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1,1000).forEach(i -> {

            long bno = (int)(Math.random()*200) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .replyText("hello...")
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });

    }

    @Test
    public void testRead() {

        Optional<Reply> result = replyRepository.findById(1L);

        log.info(result);

        result.ifPresent(reply -> log.info(reply));
        //~ eager loading <-> lazy loading
        // 연관 시켜주면 @ManyToOne -> eager loading: 실무에서는 lazy loading을 선호 디폴트로 만들어.

    }

    @Test
    public void testPaging() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending()); // Sort

        Page<Reply> result = replyRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(reply -> {
            log.info(reply);
            log.info(".......................");
        });

    }

    @Test
    public void testByBoard() {
        Board board = Board.builder().bno(1L).build();
        Pageable pageable = PageRequest.of(0,10);

        replyRepository.getByBoard(board, pageable);

    }

}
