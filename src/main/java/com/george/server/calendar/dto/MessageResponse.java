package com.george.server.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class MessageResponse<T> {
    private String message;
    private T user;
}
