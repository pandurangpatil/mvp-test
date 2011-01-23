package com.test.mvp.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.test.mvp.client.ui.PersonListView;

public interface ClientFactory {
    EventBus getEventBus();
    
    PlaceController getPlaceController();
    
    PersonListView getUserListView();
    
}
