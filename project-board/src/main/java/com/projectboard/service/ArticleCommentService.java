package com.projectboard.service;

import com.projectboard.dto.ArticleCommentDto;
import com.projectboard.repostiory.ArticleCommentRepository;
import com.projectboard.repostiory.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public List<ArticleCommentDto> searchArticleComment(long articleId) {

        return List.of();
    }

    public void saveArticleComment(ArticleCommentDto of) {
    }
}
