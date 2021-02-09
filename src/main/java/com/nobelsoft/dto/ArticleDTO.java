package com.nobelsoft.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleDTO {
    private String title;
    private String content;
    private List<String> tags;
}
