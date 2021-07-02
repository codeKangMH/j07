package org.zerock.j07.store;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.j07.todo.entity.Store;
import org.zerock.j07.todo.repository.StoreRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Store store = Store.builder()
                    .name("상점" + i)
                    .address("종로" + i +"가")
                    .menu("짜장면" + i)
//                    .latitude()
//                    .longitude()
                    .build();
            storeRepository.save(store);
        });

//        IntStream.rangeClosed(101,200).forEach(i -> {
//            Store store = Store.builder()
//                    .name("상점" + i)
//                    .address("종로" + i +"가")
//                    .menu("짜장면, 짬뽕" + i)
////                    .latitude()
////                    .longitude()
//                    .build();
//        });
//
//        IntStream.rangeClosed(201,300).forEach(i -> {
//            Store store = Store.builder()
//                    .name("상점" + i)
//                    .address("종로" + i +"가")
//                    .menu("짜장면, 짬뽕, 탕수육" + i)
////                    .latitude()
////                    .longitude()
//                    .build();
//        });


    }

    @Test
    public void testSelect() {

        Optional<Store> result = storeRepository.findById(1L);

        result.ifPresent(store -> log.info(store));
    }

    @Transactional
    @Commit
    @Test
    public void testUpdate() {

        Optional<Store> result = storeRepository.findById(100L);

        result.ifPresent(store -> {
            store.chageTitle("...");
            storeRepository.save(store);
        });
    }

    @Test
    public void testDelete() {

        storeRepository.delete(Store.builder().sNo(100L).build());
    }

    @Test
    public void testPaging() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("sNo").descending());
        Page<Store> result = storeRepository.findAll(pageable);

        log.info(result);
        result.getContent().forEach(store -> log.info(store));
    }

    @Transactional
    @Commit
    @Test
    public void testUpdate2() {

        storeRepository.updateName("메밀소바", 99L);
    }
}
