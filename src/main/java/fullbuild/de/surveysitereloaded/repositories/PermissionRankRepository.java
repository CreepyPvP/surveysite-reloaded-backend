package fullbuild.de.surveysitereloaded.repositories;

import fullbuild.de.surveysitereloaded.models.PermissionRank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRankRepository extends CrudRepository<PermissionRank, String> {

    Optional<PermissionRank> findByName(String name);

}
