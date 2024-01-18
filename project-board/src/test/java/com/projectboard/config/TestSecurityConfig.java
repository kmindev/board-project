package com.projectboard.config;

import com.projectboard.domain.UserAccount;
import com.projectboard.repostiory.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod // 스프링 테스트에서만 가능
    void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "kkmTest",
                "pw",
                "kkm-test@email.com",
                "kkm-test",
                "test memo"
        )));
    }

}
