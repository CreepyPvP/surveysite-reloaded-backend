package fullbuild.de.surveysitereloaded.models;

import fullbuild.de.surveysitereloaded.Permission;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PermissionRank {

    @Id
    private String name;

    /**
     * for example: #00ff00 for green
     */
    private String color = "#000000";

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Permission", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Permission> authorities = new ArrayList<>();


}
