package org.ootb.espresso.springcloud.infrastructure;

import java.util.Date;


public class CustomJackson2TestVO {
    
    private String name;

    private Date date;
    
    private GenderEnum gender;

    public CustomJackson2TestVO() {
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

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
    
}