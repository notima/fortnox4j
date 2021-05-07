package org.notima.api.fortnox.entities3;

public class WareHouseTenant {
    private Long tenantId;
    private boolean activated;
    //TODO: Find out what datatype they are supposed to be
    private Object created;
    private Object mode;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Object getCreated() {
        return created;
    }
    
    public void setCreated(Object created) {
        this.created = created;
    }

    public Object getMode() {
        return mode;
    }
    
    public void setMode(Object mode) {
        this.mode = mode;
    }
}
