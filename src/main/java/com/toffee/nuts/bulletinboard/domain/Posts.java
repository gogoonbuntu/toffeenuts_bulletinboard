package com.toffee.nuts.bulletinboard.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="TB_POSTS")
@Data
public class Posts {
    @Id @GeneratedValue
    @Column(name="post_id")
    private String id;
    private String title;
    private String contents;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Members member;
}
