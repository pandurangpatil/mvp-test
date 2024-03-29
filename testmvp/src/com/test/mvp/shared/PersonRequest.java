package com.test.mvp.shared;

import java.util.List;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Service;

@Service(com.test.mvp.server.domain.Person.class)
public interface PersonRequest extends RequestContext {
    InstanceRequest<PersonProxy, Void> persist();
    
    Request<List<PersonProxy>> listAll();
    
    Request<Integer> listAllCount();
    
    Request<List<PersonProxy>> findUserEntries(int firstResult, int maxResults);
}
