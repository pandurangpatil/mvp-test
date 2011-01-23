package com.test.mvp.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.test.mvp.client.ClientFactory;
import com.test.mvp.client.place.UserPlace;
import com.test.mvp.client.ui.PersonViewImpl;
import com.test.mvp.client.ui.PersonListView.Presenter;
import com.test.mvp.shared.MVPRequestFactory;

public class PersonActivity extends AbstractActivity implements Presenter {
    
    private ClientFactory clientFactory;
    
    private UserPlace     place;
    
    private PersonViewImpl  impl;
    
    public PersonActivity(UserPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.place = place;
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        impl = new PersonViewImpl(place.getRequestFactory());
        impl.setPresenter(this);
        impl.edit(place.getPerson());
        panel.setWidget(impl);
    }
    
    public void onStop() {
        impl.hide();
    }
    
    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
        
    }
    
}
