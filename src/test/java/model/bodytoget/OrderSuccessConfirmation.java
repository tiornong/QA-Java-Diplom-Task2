package model.bodytoget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSuccessConfirmation {
    private String      name;
    private OrderNumber orderNumber;
    private String      success;
}
