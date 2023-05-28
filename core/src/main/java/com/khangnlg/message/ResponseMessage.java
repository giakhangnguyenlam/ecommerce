package com.khangnlg.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessage {

    private int status;

    private Object data;

    private String message;

    private String error;

}
