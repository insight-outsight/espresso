package org.ootb.espresso.springcloud.infrastructure;

import org.ootb.espresso.facilities.enums.GeneralEnumInterface;

import com.google.common.collect.Sets;

public enum GenderEnum implements GeneralEnumInterface {

    MALE(23, "男"),
    FEMALE(45, "女");

    private final int code;
    private final String desc;
    
    private GenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return desc;
    }
    
    @Override
    public String toString() {
        return this.name() + "[code:" + this.code + ",description:" + desc + "]";
    }
    
    public static void main(String[] args) {
        System.out.println(GeneralEnumInterface.fromCode(GenderEnum.class, 23));
        System.out.println(GeneralEnumInterface.toCodeSet(Sets.newHashSet(GenderEnum.values())));
    }

}