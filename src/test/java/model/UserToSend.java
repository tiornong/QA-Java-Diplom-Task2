package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToSend {
    private String email;
    private String password;
    private String name;

    public UserToSend copy() {
        return new UserToSend(this.email, this.password, this.name);
    }
}
