package com.khangnlg.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCommentCus {

    private long id;

    private String username;

    private String comment;

}
