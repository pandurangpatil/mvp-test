package com.test.mvp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.test.mvp.client.ui.UserListView;
import com.test.mvp.client.ui.UserListViewImpl;
import com.test.mvp.client.ui.UserView;
import com.test.mvp.client.ui.UserViewImpl;
import com.test.mvp.shared.MVPRequestFactory;

public class ClientFactoryImpl implements ClientFactory {
    private static final EventBus        eventBus        = new SimpleEventBus();
    private static final PlaceController placeController = new PlaceController(eventBus);
    private static final UserListView    userListView    = new UserListViewImpl();
    
    @Override
    public EventBus getEventBus() {
        return eventBus;
    }
    
    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }
    
    @Override
    public UserListView getUserListView() {
        return userListView;
    }
    
}
