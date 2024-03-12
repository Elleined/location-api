package com.elleined.philippinelocationapi.utility;

public interface StringValidator {
    static boolean isNotValidBody(String body) {
        return body == null || body.isEmpty() || body.isBlank();
    }


}
