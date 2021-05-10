package org.ootb.espresso.springcloud.infrastructure;

public class OotbExceptionCarrier<T> {

    private String exceptionType;
    private T cause;

    OotbExceptionCarrier() {
    }

    OotbExceptionCarrier(T cause) {
        this.exceptionType = cause.getClass().getCanonicalName();
        this.cause = cause;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public T getCause() {
        return cause;
    }
}
