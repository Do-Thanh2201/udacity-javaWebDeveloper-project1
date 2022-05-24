package com.udacity.jwdnd.course1.cloudstorage.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private int userId;
    private String username;

    public UserDto(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
