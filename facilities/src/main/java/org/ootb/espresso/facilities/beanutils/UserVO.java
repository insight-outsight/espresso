package org.ootb.espresso.facilities.beanutils;


public class UserVO {
    
    private Long userId;
    private String userName;
    private Integer age;
    private String sex;
    
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "UserVO [userId=" + userId + ", userName=" + userName + ", age=" + age + ", sex=" + sex + "]";
    }
    
    
    
}