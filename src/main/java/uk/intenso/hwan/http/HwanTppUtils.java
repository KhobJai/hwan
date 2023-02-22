package uk.intenso.hwan.http;

import java.util.Set;

public abstract class HwanTppUtils {

    public static boolean isOk(int statusCode) {
        validateCode(statusCode);
        return doesIntStartWith(statusCode, "2");
    }

    public static boolean isFailure(int statusCode) {
        return isClientError(statusCode) || isServerError(statusCode);
    }

    public static boolean isRedirect(int statusCode) {
        validateCode(statusCode);
        return doesIntStartWith(statusCode, "3");
    }

    public static boolean isClientError(int statusCode) {
        validateCode(statusCode);
        return doesIntStartWith(statusCode, "4");
    }

    public static boolean isServerError(int statusCode) {
        validateCode(statusCode);
        return doesIntStartWith(statusCode, "5");
    }

    private static boolean doesIntStartWith(int statusCode, String prefix) {
        return String.valueOf(statusCode).startsWith(prefix);
    }


    static void validateCode(int statusCode) {
        var codeAsString = String.valueOf(statusCode);
        if (codeAsString.length() != 3) {
            throw new InvalidStatusCodeException(statusCode, "Invalid Length");
        } else if (!Set.of('1', '2', '3', '4', '5').contains(codeAsString.charAt(0))) {
            throw new InvalidStatusCodeException(statusCode, "Unknown Code");
        }
    }

    static class InvalidStatusCodeException extends RuntimeException {

        public InvalidStatusCodeException(int statusCode) {
            super("Invalid Status Code: " + statusCode);
        }

        public InvalidStatusCodeException(int statusCode, String errorMessage) {
            super("Invalid Status Code: " + statusCode + " " + errorMessage);
        }
    }
}
