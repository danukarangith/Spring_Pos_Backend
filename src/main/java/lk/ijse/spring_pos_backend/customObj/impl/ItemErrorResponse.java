package lk.ijse.spring_pos_backend.customObj.impl;

import lk.ijse.spring_pos_backend.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemErrorResponse implements ItemResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}