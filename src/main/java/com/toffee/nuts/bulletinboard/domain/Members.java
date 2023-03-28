package com.toffee.nuts.bulletinboard.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_MEMBER")
@Data
public class Members {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

//    @Embedded
//    private Address address;
    private String pw;

    @OneToMany(mappedBy = "member")
    private List<Posts> posts = new ArrayList<>();
}
