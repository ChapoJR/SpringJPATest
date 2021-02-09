package com.nobelsoft.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Data
public class Tag {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String tag;

    @Override
    public String toString() {
        if (this.article == null) {
            return "";
        } else {
            return "Tag(id=" + this.id + ",articleId=" + article.getId() + ",tag=" + this.tag + ")";
        }
    }
}
