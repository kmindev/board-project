package com.projectboard.dto.request;

import com.projectboard.dto.ArticleCommentDto;
import com.projectboard.dto.UserAccountDto;

/**
 * DTO for {@link com.projectboard.domain.ArticleComment}
 */
public record ArticleCommentRequest(Long articleId, String content) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto) {
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }

}