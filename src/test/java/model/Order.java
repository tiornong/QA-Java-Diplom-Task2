package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String[]    ingredients;
    private String      _id;
    private int         number;
    private String      createdAt;
    private String      updatedAt;
}
