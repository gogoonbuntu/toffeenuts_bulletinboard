package com.toffee.nuts.bulletinboard.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TB_POSTS")
@Getter @Setter
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
