package model.bodytoget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationInfo {
    private boolean     success;
    private String      accessToken;
    private String      refreshToken;
    private UserToGet   user;
}
