package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.bodytoget.UserToGet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoToGet {
    private String success;
    private UserToGet user;
}
