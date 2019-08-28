package org.ootb.espresso.facilities.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BaseShortToStringBuilderDTO {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, CustomToStringStyle.NO_NULL_NO_CLASS_NAME_NO_HASH_CODE_MULTI_LINE_STYLE);
    }
    
}
