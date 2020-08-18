package fullbuild.de.surveysitereloaded.security.model;


import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.security.model.TokenModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<TokenModel, String> {

    Optional<TokenModel> findByUser(User user);
    Optional<TokenModel> findByToken(String token);

    boolean existsByToken(String token);

}
