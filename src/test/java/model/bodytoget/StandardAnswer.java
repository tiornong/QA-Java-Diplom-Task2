package model.bodytoget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardAnswer {
    private String success;
    private String message;
}
