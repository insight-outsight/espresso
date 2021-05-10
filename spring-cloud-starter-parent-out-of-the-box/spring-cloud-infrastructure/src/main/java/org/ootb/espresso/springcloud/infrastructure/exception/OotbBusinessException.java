package org.ootb.espresso.springcloud.infrastructure.exception;

public class OotbBusinessException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 7590236113729756874L;

    public OotbBusinessException(String message) {
        super(message);
    }

    public OotbBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public OotbBusinessException(Throwable cause) {
        super(cause);
    }

}
