package com.test.mvp.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.test.mvp.client.ClientFactory;
import com.test.mvp.client.place.UserListPlace;
import com.test.mvp.client.ui.UserListView;
import com.test.mvp.client.ui.UserListView.Presenter;

public class UserListActivity extends AbstractActivity implements Presenter {
    // Used to obtain views, eventBus, placeController
    // Alternatively, could be injected via GIN
    private ClientFactory clientFactory;
    
    public UserListActivity(UserListPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }
    
    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        UserListView listView = clientFactory.getUserListView();
        containerWidget.setWidget(listView.asWidget());
    }
    
    /**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
        return "Please hold on. This activity is stopping.";
    }
    
    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
    
}
