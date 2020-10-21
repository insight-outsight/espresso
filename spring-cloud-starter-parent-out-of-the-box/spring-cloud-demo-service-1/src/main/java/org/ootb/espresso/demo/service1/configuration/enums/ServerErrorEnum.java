package org.ootb.espresso.demo.service1.configuration.enums;


public enum ServerErrorEnum {
    SYSTEM_ERROR(0, "系统错误");

    private final int code;
    private final String desc;
    
    private ServerErrorEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public int getCode() {
        return code;
    }

    public String getDesciption() {
        return desc;
    }
    
    @Override
    public String toString() {
        return this.name() + "[code:" + this.code + ",description:" + desc + "]";
    }
}
