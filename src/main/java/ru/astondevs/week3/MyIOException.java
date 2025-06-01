package ru.astondevs.week3;

import java.io.IOException;

public class MyIOException extends IOException {
    public MyIOException() {
        super();
    }

    public MyIOException(String message) {
        super(message);
    }

    public MyIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyIOException(Throwable cause) {
        super(cause);
    }
}
