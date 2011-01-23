package com.test.mvp.client.mvp;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.test.mvp.shared.MVPRequestFactory;
import com.test.mvp.shared.PersonProxy;
import com.test.mvp.shared.PersonRequest;

public class UserDataProvider extends AsyncDataProvider<PersonProxy> {
    
    private SimplePager pager;
    
    public UserDataProvider(SimplePager pager) {
        this.pager = pager;
    }
    
    @Override
    protected void onRangeChanged(HasData<PersonProxy> display) {
        final CellTable<PersonProxy> table = (CellTable<PersonProxy>) display;
        final Range range = display.getVisibleRange();
        final EventBus eventBus = new SimpleEventBus();
        final MVPRequestFactory requestFactory = GWT.create(MVPRequestFactory.class);
        requestFactory.initialize(eventBus);
        PersonRequest personReq = requestFactory.personRequest();
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
