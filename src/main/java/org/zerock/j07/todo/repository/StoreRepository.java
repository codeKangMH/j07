package org.zerock.j07.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j07.todo.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Modifying
    @Query("update Store set menu=:menu where sNo=:sNo")
    void updateName(String menu, Long sNo);
}
