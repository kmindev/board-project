package com.projectboard.service;

import com.projectboard.domain.Article;
import com.projectboard.domain.ArticleComment;
import com.projectboard.domain.UserAccount;
import com.projectboard.domain.type.SearchType;
import com.projectboard.dto.ArticleCommentDto;
import com.projectboard.dto.ArticleDto;
import com.projectboard.repostiory.ArticleCommentRepository;
import com.projectboard.repostiory.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
public class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsArticleComments() {
        // Given
        long articleId = 1L;
        UserAccount userAccount = UserAccount.of("kkm", "pw", null, null, null);

        Article.of(userAccount, "title", "content", "#java").getArticleComments();
        given(articleRepository.findById(articleId))
                .willReturn(Optional.of(Article.of(userAccount, "title", "content", "#java"))
                );

        // When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        // Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "kkm", LocalDateTime.now(), "kkm", "comment"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}
