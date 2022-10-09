package com.blog.payload;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int totalPages;
    private int pageSize;
    private long totalElements;
    private boolean lastPage;

}
