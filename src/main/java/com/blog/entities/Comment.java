package com.blog.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Comments")
@Getter
@Setter

public class Comment {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;
    private String content;
    @ManyToOne
    private Post post;
    

}
