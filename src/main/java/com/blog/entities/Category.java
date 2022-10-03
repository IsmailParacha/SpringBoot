package com.blog.entities;

import java.util.*;

import javax.persistence.*;

import lombok.*;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int catid;
    @Column(length = 100, nullable = false)
    private String title;
    private String description;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();
}
