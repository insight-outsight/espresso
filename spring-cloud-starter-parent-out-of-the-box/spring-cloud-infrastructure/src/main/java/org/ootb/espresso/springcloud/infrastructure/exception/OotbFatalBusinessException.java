package org.ootb.espresso.springcloud.infrastructure.exception;

public class OotbFatalBusinessException extends OotbBusinessException {

    /**
     * 
     */
    private static final long serialVersionUID = -4926605013919593824L;

    public OotbFatalBusinessException(String message) {
        super(message);
    }

    public OotbFatalBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public OotbFatalBusinessException(Throwable cause) {
        super(cause);
    }
    
}
