package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoToGet {
    private String      name;
    private OrderNumber orderNumber;
    private String      success;
}
