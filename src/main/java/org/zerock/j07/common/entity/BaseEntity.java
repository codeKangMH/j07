package org.zerock.j07.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 createDate, modifiedDate를 컬럼으로 인식
@MappedSuperclass

// 해당 클래스에 Auditing 기능을 포함
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
public abstract class BaseEntity {

    // Entity가 생성되어 저장될 때 시간이 자동저장
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    // 조회한 Entity의 값을 변경할 때 시간이 자동저장
    @LastModifiedDate
    @Column(name ="moddate" )
    private LocalDateTime modDate;

}
