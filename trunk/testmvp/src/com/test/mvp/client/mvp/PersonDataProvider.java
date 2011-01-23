package com.test.mvp.client.mvp;

import java.util.List;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.test.mvp.client.ClientFactory;
import com.test.mvp.shared.PersonProxy;
import com.test.mvp.shared.PersonRequest;

public class PersonDataProvider extends AsyncDataProvider<PersonProxy> {
    
    private ClientFactory clientFactory;
    
    public PersonDataProvider(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }
    
    @Override
    protected void onRangeChanged(HasData<PersonProxy> display) {
        final Range range = display.getVisibleRange();
        PersonRequest personReq = clientFactory.getRequestFactory().personRequest();
        personReq.findUserEntries(range.getStart(), range.getLength()).fire(new Receiver<List<PersonProxy>>() {
            
            @Override
            public void onSuccess(List<PersonProxy> response) {
                if (response.size() > 0) {
                    updateRowData(range.getStart(), response);
                }
                
            }
            
        });
        
    }
}
