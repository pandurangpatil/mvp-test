package com.test.mvp.client.mvp;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.test.mvp.shared.MVPRequestFactory;
import com.test.mvp.shared.PersonProxy;
import com.test.mvp.shared.PersonRequest;

public class UserDataProvider extends AsyncDataProvider<PersonProxy> {
    
    @Override
    protected void onRangeChanged(HasData<PersonProxy> display) {
        final EventBus eventBus = new SimpleEventBus();
        final MVPRequestFactory requestFactory = GWT.create(MVPRequestFactory.class);
        requestFactory.initialize(eventBus);
        PersonRequest personReq = requestFactory.personRequest();
        personReq.listAll().fire(new Receiver<List<PersonProxy>>() {
            
            @Override
            public void onSuccess(List<PersonProxy> response) {
                updateRowData(0, response);
            }
            
        });
        
    }
    
}