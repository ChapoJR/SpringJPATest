package com.nobelsoft.service;

import com.nobelsoft.dto.ArticleDTO;
import com.nobelsoft.entity.Article;
import com.nobelsoft.entity.Tag;
import com.nobelsoft.repository.ArticleRepository;
import com.nobelsoft.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    TagRepository tagRepository;

    public Optional<ArticleDTO> findById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            Optional<ArticleDTO> returnDTO = Optional.ofNullable(convertToArticleDTO(article.get()));
            return returnDTO;
        } else {
            return Optional.empty();
        }
    }

    public Long saveArticle(ArticleDTO input) {
        Article savedArticle = articleRepository.save(convertToArticle(input));
        this.checkBlackListWords(input.getContent());
        if (input.getTags() != null) {
            tagRepository.saveAll(savedArticle.getTags().stream().map(tag -> {
                tag.setArticle(savedArticle);
                return tag;
            }).collect(Collectors.toList()));
        }
        log.info(articleRepository.findById(savedArticle.getId()).toString());
        return savedArticle.getId();
    }

    public List<ArticleDTO> findByTitle(String title) {
        List<ArticleDTO> returnList = new ArrayList<>();
        List<Article> listArticle = articleRepository.findByTitle(title);
        return returnList;
    }

    public Long create(ArticleDTO articleDTO) {
        Article article = articleRepository.save(convertToArticle(articleDTO));
        return article.getId();
    }

    public void update(Long id, ArticleDTO articleDTO) {
        this.checkBlackListWords(articleDTO.getContent());
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            article.get().setTitle(articleDTO.getTitle());
            article.get().setContent(articleDTO.getContent());
            articleRepository.save(article.get());
        }
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    private void checkBlackListWords(String checkString) {
        if (checkString.contains("_")) {
            throw new RuntimeException("Article content contains forbidden words.");
        }
    }

    private ArticleDTO convertToArticleDTO(Article input) {
        ArticleDTO articleDTO = ArticleDTO.builder()
                .title(input.getTitle())
                .content(input.getContent())
                .tags(input.getTags().stream().map(Tag::getTag).collect(Collectors.toList()))
                .build();
        return articleDTO;
    }

    private Article convertToArticle(ArticleDTO input) {
        Article article = new Article();
        article.setTitle(input.getTitle());
        article.setContent(input.getContent());
        if (input.getTags() != null) {
            article.setTags(input.getTags().stream().map(s -> {
                Tag tag = new Tag();
                tag.setTag(s);
                return tag;
            }).collect(Collectors.toList()));
        }
        return article;
    }
}
