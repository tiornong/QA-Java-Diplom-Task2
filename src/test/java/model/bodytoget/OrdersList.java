package model.bodytoget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersList {
    private String  success;
    private OrderInfo[] orders;
    private int     total;
    private int     totalToday;
}
