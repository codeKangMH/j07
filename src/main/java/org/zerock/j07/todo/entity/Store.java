package org.zerock.j07.todo.entity;

import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_store")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sNo;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private  String address;

    @Column(nullable = false)
    private String menu;

    public void chageTitle(String name) {

    }
}
