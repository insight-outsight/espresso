package org.ootb.espresso.facilities.beanutils;

public class UserDO {
    
    private Long userId;
    private String userName;
    private Integer age;
    private Integer sex;
    
    public UserDO() {
        super();
    }

    public UserDO(Long userId, String userName, Integer age, Integer sex) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    

}