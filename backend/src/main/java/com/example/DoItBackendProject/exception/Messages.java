package com.example.DoItBackendProject.exception;

import org.springframework.util.StringUtils;

public enum Messages {
    NOT_FOUND,
    VALIDATION_EXCEPTION,
    RESOURCE_NOT_FOUND,
    BAD_REQUEST,
    DB_CONFLICT,
    DONT_HAVE_ENOUGH_RIGHTS;

    public String getMessage() {
        return StringUtils.capitalize(this.name()
                .toLowerCase().replaceAll("_", " "));
    }
}
