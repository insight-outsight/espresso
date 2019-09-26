package org.ootb.espresso.facilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Counter extends ESEntity{

    private Long sId;
    private Integer sType;
    private Integer bizId;
    private Integer bizType;
    private String oId;
    private Integer status;
    private Long mTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The sId
     */
    public Long getSId() {
        return sId;
    }

    /**
     * 
     * @param sId
     *     The s_id
     */
    public void setSId(Long sId) {
        this.sId = sId;
    }

    /**
     * 
     * @return
     *     The sType
     */
    public Integer getSType() {
        return sType;
    }

    /**
     * 
     * @param sType
     *     The s_type
     */
    public void setSType(Integer sType) {
        this.sType = sType;
    }
    
    /**
     * 
     * @return
     *     The bizId
     */
    public Integer getBizId() {
        return bizId;
    }

    /**
     * 
     * @param bizId
     *     The biz_id
     */
    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }

    /**
     * 
     * @return
     *     The bizType
     */
    public Integer getBizType() {
        return bizType;
    }

    /**
     * 
     * @param bizType
     *     The biz_type
     */
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    /**
     * 
     * @return
     *     The oId
     */
    public String getOId() {
        return oId;
    }

    /**
     * 
     * @param oId
     *     The o_id
     */
    public void setOId(String oId) {
        this.oId = oId;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 
     * @return
     *     The mTime
     */
    public Long getMTime() {
        return mTime;
    }

    /**
     * 
     * @param mTime
     *     The m_time
     */
    public void setMTime(Long mTime) {
        this.mTime = mTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(routingKey).append(sId).append(sType).append(bizId).append(bizType).append(oId).append(status).append(mTime).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Counter) == false) {
            return false;
        }
        Counter rhs = ((Counter) other);
        return new EqualsBuilder().append(id, rhs.id).append(routingKey, rhs.routingKey).append(sId, rhs.sId).append(sType, rhs.sType).append(bizId, rhs.bizId).append(bizType, rhs.bizType).append(oId, rhs.oId).append(status, rhs.status).append(mTime, rhs.mTime).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}