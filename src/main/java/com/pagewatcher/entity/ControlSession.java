package com.pagewatcher.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ControlSession {
    START("START"),
    STOP("STOP");

    @Getter
    private String value;
}
