package com.test.mvp.shared;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(com.test.mvp.server.domain.Person.class)
public interface PersonProxy extends EntityProxy {
    public Long getId();
    
    public void setId(Long id);
    
    public String getFirstName();
    
    public void setFirstName(String firstName);
    
    public String getLastName();
    
    public void setLastName(String lastName);
    
    public String getEmailId();
    
    public void setEmailId(String emailId);
    
    EntityProxyId<PersonProxy> stableId();
}
