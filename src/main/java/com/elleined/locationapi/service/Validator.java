package com.elleined.locationapi.service;

public interface Validator {
    static boolean isNotValidBody(String body) {
        return body == null || body.isEmpty() || body.isBlank();
    }


}
