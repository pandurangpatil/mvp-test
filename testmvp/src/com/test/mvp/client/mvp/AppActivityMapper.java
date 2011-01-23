package com.test.mvp.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.test.mvp.client.ClientFactory;
import com.test.mvp.client.place.UserListPlace;
import com.test.mvp.client.place.UserPlace;
import com.test.mvp.shared.MVPRequestFactory;

public class AppActivityMapper implements ActivityMapper {
    
    private ClientFactory     clientFactory;
    private EventBus          eventBus       = new SimpleEventBus();
    private MVPRequestFactory requestFactory = GWT.create(MVPRequestFactory.class);
    
    /**
     * AppActivityMapper associates each Place with its corresponding {@link Activity}
     * 
     * @param clientFactory
     *            Factory to be passed to activities
     */
    public AppActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
        requestFactory.initialize(eventBus);
    }
    
    /**
     * Map each Place to its corresponding Activity. This would be a great use for GIN.
     */
    @Override
    public Activity getActivity(Place place) {
        // This is begging for GIN
        if (place instanceof UserListPlace)
            return new PersonListActivity((UserListPlace) place, clientFactory);
        else if (place instanceof UserPlace)
            return new PersonActivity((UserPlace) place, clientFactory, requestFactory);
        return null;
    }
}
