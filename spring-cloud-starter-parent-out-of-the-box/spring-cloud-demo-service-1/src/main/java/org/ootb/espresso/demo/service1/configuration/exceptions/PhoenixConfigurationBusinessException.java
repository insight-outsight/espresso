package org.ootb.espresso.demo.service1.configuration.exceptions;

import org.ootb.espresso.demo.service1.configuration.enums.ServerErrorEnum;
import org.ootb.espresso.springcloud.infrastructure.OotbBusinessException;

public class PhoenixConfigurationBusinessException extends OotbBusinessException {

    /**
     * 
     */
    private static final long serialVersionUID = -4271148911600008882L;
    
    private final ServerErrorEnum serverErrorEnum;
    
    public PhoenixConfigurationBusinessException(ServerErrorEnum serverErrorEnum) {
        super(serverErrorEnum.getDesciption());
        this.serverErrorEnum = serverErrorEnum;
    }
    
    public PhoenixConfigurationBusinessException(String message, ServerErrorEnum serverErrorEnum) {
        super(message);
        this.serverErrorEnum = serverErrorEnum;
    }

    public PhoenixConfigurationBusinessException(Throwable cause, ServerErrorEnum serverErrorEnum) {
        super(cause);
        this.serverErrorEnum = serverErrorEnum;
    }
    
    public PhoenixConfigurationBusinessException(String message, Throwable cause, ServerErrorEnum serverErrorEnum) {
        super(message, cause);
        this.serverErrorEnum = serverErrorEnum;
    }

    public ServerErrorEnum getServerErrorEnum() {
        return serverErrorEnum;
    }

    @Override
    public String toString() {
        return "PhoenixConfigurationBusinessException,message: " + super.getMessage() + ", [serverErrorEnum=" + serverErrorEnum + "]";
    }

}
