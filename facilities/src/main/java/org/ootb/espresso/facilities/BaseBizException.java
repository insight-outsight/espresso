package org.ootb.espresso.facilities;

public class BaseBizException extends Exception {


    /**
     * 
     */
    private static final long serialVersionUID = -3091180668714502577L;

    public BaseBizException() {
        super();
    }

    public BaseBizException(String message) {
        super(message);
    }

    public BaseBizException(String message, Throwable cause) {
        super(message, cause);
    }

}
