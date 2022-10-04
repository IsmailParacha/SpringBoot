package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.entities.*;

public interface PostRepo extends JpaRepository<Post,Integer>{
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
