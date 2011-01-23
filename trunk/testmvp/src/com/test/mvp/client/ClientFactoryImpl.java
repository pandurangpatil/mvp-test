package com.test.mvp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.test.mvp.client.ui.PersonListView;
import com.test.mvp.client.ui.PersonListViewImpl;
import com.test.mvp.shared.MVPRequestFactory;

public class ClientFactoryImpl implements ClientFactory {
    private static final EventBus          eventBus        = new SimpleEventBus();
    private static final PlaceController   placeController = new PlaceController(eventBus);
    private static final PersonListView    userListView    = new PersonListViewImpl();
    private static final MVPRequestFactory requestFactory  = GWT.create(MVPRequestFactory.class);
    
    @Override
    public EventBus getEventBus() {
        return eventBus;
    }
    
    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }
    
    @Override
    public PersonListView getUserListView() {
        return userListView;
    }
    
    @Override
    public MVPRequestFactory getRequestFactory() {
        requestFactory.initialize(eventBus);
        return requestFactory;
    }
    
}
