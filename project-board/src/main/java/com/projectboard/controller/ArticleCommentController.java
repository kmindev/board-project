package com.projectboard.controller;

import com.projectboard.dto.UserAccountDto;
import com.projectboard.dto.request.ArticleCommentRequest;
import com.projectboard.dto.security.BoardPrincipal;
import com.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticle(ArticleCommentRequest articleCommentRequest,
                                 @AuthenticationPrincipal BoardPrincipal boardPrincipal) {

        articleCommentService.saveArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticle(@PathVariable Long commentId,
                                @AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                Long articleId) {
        articleCommentService.deleteArticleComment(commentId, boardPrincipal.username());

        return "redirect:/articles/" + articleId;
    }
}
