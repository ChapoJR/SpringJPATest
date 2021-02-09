package com.nobelsoft;

import com.nobelsoft.dto.ArticleDTO;
import com.nobelsoft.entity.Article;
import com.nobelsoft.service.ArticleService;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@CucumberContextConfiguration
@Slf4j
class SpringJpaTestApplicationTests {

    @Autowired
    ArticleService service;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Save Article")
    void testSaveArticle() {
        ArticleDTO dto = ArticleDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .tags(Arrays.asList("Tag1", "Tag2", "Tag3")).build();
        Long id = service.saveArticle(dto);
        log.info(id.toString());
    }

    @Test
    @DisplayName("Save Article without Tags")
    void testSaveWithoutTags() {
        ArticleDTO dto = ArticleDTO.builder()
                .title("Test Title 2")
                .content("Test Content 2")
                .build();
        Long id = service.saveArticle(dto);
        log.info(id.toString());
    }

    @Test
    @DisplayName("Not find Article by Id")
    void testNotFindArticleById() {
        ArticleDTO dto = ArticleDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .tags(Arrays.asList("Tag1", "Tag2", "Tag3")).build();
        service.saveArticle(dto);
        assertEquals(Optional.empty(), service.findById(new Long(100)));
    }

    @Test
    @DisplayName("Find Article without Tags")
    void testFindWithoutTags() {
        ArticleDTO dto = ArticleDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .tags(Arrays.asList("Tag1", "Tag2", "Tag3")).build();
        service.saveArticle(dto);
        assertNotEquals(Optional.empty(), service.findById(new Long(1)));
    }

    @Test
    @DisplayName("Check Blacklist Words Save")
    void testBlacklistWordsSave() {
        assertThrows(RuntimeException.class, () -> {
            ArticleDTO dto = ArticleDTO.builder()
                    .title("Test_Title")
                    .content("Test_Content")
                    .build();
            service.saveArticle(dto);
        });
    }

    @Test
    @DisplayName("Check Blacklist Words Update")
    void testBlacklistWordsUpdate() {
        assertThrows(RuntimeException.class, () -> {
            ArticleDTO dto = ArticleDTO.builder()
                    .title("Test Title")
                    .content("Test Content")
                    .build();
            Long id = service.saveArticle(dto);
            ArticleDTO article = service.findById(id).get();
            article.setContent("____");
            service.update(id, article);
        });
    }

}
