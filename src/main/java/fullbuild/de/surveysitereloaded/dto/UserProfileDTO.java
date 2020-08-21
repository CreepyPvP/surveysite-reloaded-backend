package fullbuild.de.surveysitereloaded.dto;

import fullbuild.de.surveysitereloaded.models.PermissionRank;
import fullbuild.de.surveysitereloaded.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDTO {

    private String username;
    private int id;
    private String profilePictureLink;
    private PermissionRank permissionRank;

    public UserProfileDTO(User user) {
        username = user.getUsername();
        id = user.getId();
        profilePictureLink = "meme.jpg";
        permissionRank = user.getPermissionRank();
    }

}
