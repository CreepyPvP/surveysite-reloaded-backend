package fullbuild.de.surveysitereloaded.security.model;


import fullbuild.de.surveysitereloaded.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenModel, String> {

    int countByUser(User user);
    Optional<TokenModel> findByToken(String token);

    boolean existsByToken(String token);

    @Transactional
    void deleteAllByUser(User user);

    @Transactional
    void deleteByToken(String token);

    @Transactional
    void deleteAllByExpirationDateBefore(Date now);

    @Transactional
    void deleteByTokenAndExpirationDateBefore(String token, Date now);

    @Transactional
    void deleteByUserAndExpirationDateBefore(User user, Date now);

}
