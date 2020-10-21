package org.ootb.espresso.facilities.appauth;

import java.io.Serializable;

public class AppAuthInfo implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -9012215878697737238L;

    private String appId;

    private String appKey;

    private String appSecret;
    
    private String appDesc;


    public AppAuthInfo() {
        super();
    }

    public AppAuthInfo(String appId, String appKey, String appSecret) {
        super();
        this.appId = appId;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
    
    public AppAuthInfo(String appId, String appKey, String appSecret, String appDesc) {
        super();
        this.appId = appId;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.appDesc = appDesc;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    @Override
    public String toString() {
        return "AppAuthInfo [appId=" + appId + ", appKey=" + appKey + ", appSecret=" + appSecret + ", appDesc="
                + appDesc + "]";
    }

}