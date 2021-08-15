package com.learnview.exception;

public class InvalidAnswerException extends Exception {

    private static final long serialVersionUID = 2775777999584073180L;

    public InvalidAnswerException(String message) {
        super(message);
    }

}
