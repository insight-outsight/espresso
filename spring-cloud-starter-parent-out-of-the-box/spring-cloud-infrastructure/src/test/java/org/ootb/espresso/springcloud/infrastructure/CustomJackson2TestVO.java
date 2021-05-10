package org.ootb.espresso.springcloud.infrastructure;

import java.util.Date;

import org.ootb.espresso.springcloud.infrastructure.enums.AppIdEnum;
import org.ootb.espresso.springcloud.infrastructure.enums.GenderEnum;


public class CustomJackson2TestVO {
    
    private String name;

    private Date date;
    
    private AppIdEnum appId;
    
    private GenderEnum gender;

    public CustomJackson2TestVO() {
    }
    
    public CustomJackson2TestVO(AppIdEnum appId, GenderEnum gender) {
        this.appId = appId;
        this.gender = gender;
    }

    public CustomJackson2TestVO(String name, Date date, GenderEnum gender) {
        this.name = name;
        this.date = date;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppIdEnum getAppId() {
        return appId;
    }

    public void setAppId(AppIdEnum appId) {
        this.appId = appId;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    
}