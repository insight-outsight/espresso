package org.ootb.espresso.springcloud.infrastructure;

import org.ootb.espresso.facilities.enums.GeneralEnumInterface;

public enum AppIdEnum implements GeneralEnumInterface {
    
    APP15(15, "APP_15"),
    APP17(17, "APP_17");

    private final int code;
    private final String desc;

    AppIdEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return desc;
    }
    
    @Override
    public String toString() {
        return this.name() + "[code:" + this.code + ",description:" + desc + "]";
    }
    
}
