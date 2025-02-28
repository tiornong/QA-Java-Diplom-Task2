package model.bodytoget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private String[]    ingredients;
    private String      _id;
    private int         number;
    private String      createdAt;
    private String      updatedAt;
}
