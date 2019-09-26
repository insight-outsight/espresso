package org.ootb.espresso.facilities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class ESEntity {

    @JsonIgnore
    protected String id;
    @JsonIgnore
    protected String routingKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

}
