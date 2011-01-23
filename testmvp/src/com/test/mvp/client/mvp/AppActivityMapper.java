package com.test.mvp.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.test.mvp.client.ClientFactory;
import com.test.mvp.client.place.UserListPlace;
import com.test.mvp.client.place.UserPlace;

public class AppActivityMapper implements ActivityMapper {
    
    private ClientFactory clientFactory;
    
    /**
     * AppActivityMapper associates each Place with its corresponding {@link Activity}
     * 
     * @param clientFactory
     *            Factory to be passed to activities
     */
    public AppActivityMapper(ClientFactory clientFactory) {
        super();
        this.clientFactory = clientFactory;
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
            return new PersonActivity((UserPlace) place, clientFactory);
        return null;
    }
}
