package fullbuild.de.surveysitereloaded.security.model;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenRepositoryCleaner {

    public static final int TOKEN_REPOSITORY_CLEAR_RATE_DAYS = 7;

    private final TokenRepository tokenRepository;

    @Scheduled(fixedRate = TOKEN_REPOSITORY_CLEAR_RATE_DAYS * 24 * 60 * 60 * 1000)
    public void clean() {
        tokenRepository.deleteAllByExpirationDateBefore(new Date());
    }
}
