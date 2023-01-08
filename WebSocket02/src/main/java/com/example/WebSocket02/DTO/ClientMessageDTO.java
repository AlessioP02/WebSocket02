package com.example.WebSocket02.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClientMessageDTO {

    private String clientName;
    private String clientAlert;
    private String clientMsg;

}
