package org.ootb.espresso.springcloud.infrastructure;

public class OotbResourceNotFoundException extends OotbFatalBusinessException {
    /**
     * 
     */
    private static final long serialVersionUID = -2612275231492876685L;

    public OotbResourceNotFoundException(String message) {
        super(message);
    }

    public OotbResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OotbResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
