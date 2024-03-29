package com.test.mvp.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.test.mvp.client.ClientFactory;
import com.test.mvp.client.place.UserListPlace;
import com.test.mvp.client.ui.PersonListView;
import com.test.mvp.client.ui.PersonListView.Presenter;
import com.test.mvp.shared.PersonRequest;

public class PersonListActivity extends AbstractActivity implements Presenter {
    // Used to obtain views, eventBus, placeController
    // Alternatively, could be injected via GIN
    private ClientFactory clientFactory;
    
    public PersonListActivity(UserListPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }
    
    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        final PersonListView listView = clientFactory.getUserListView();
        PersonDataProvider dataProvider = new PersonDataProvider(clientFactory);
        listView.setDataProvider(dataProvider);
        listView.setPresenter(this);
        PersonRequest personReq = clientFactory.getRequestFactory().personRequest();
        personReq.listAllCount().fire(new Receiver<Integer>() {
            
            @Override
            public void onSuccess(Integer response) {
                listView.setRowCount(response);
            }
        });
        containerWidget.setWidget(listView.asWidget());
    }
    
    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
    
}
