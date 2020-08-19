package fullbuild.de.surveysitereloaded.configs;

import fullbuild.de.surveysitereloaded.models.PermissionRank;
import fullbuild.de.surveysitereloaded.models.User;
import fullbuild.de.surveysitereloaded.repositories.PermissionRankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackageClasses = User.class)
public class RankConfig {

    private static final String DEFAULT_PERMISSION_RANK_NAME = "Plebejer";

    private final PermissionRankRepository permissionRankRepository;

    @Bean
    public PermissionRank defaultPermissionRank() {
        PermissionRank defaultRank;
        Optional<PermissionRank> defaultRankOp = permissionRankRepository.findByName("default");
        if(defaultRankOp.isPresent()) {
            defaultRank = defaultRankOp.get();
        } else {
            defaultRank = new PermissionRank();
            defaultRank.setName(DEFAULT_PERMISSION_RANK_NAME);
            defaultRank.setColor("#918d8d");
            permissionRankRepository.save(defaultRank);
        }
        return defaultRank;
    }

}
