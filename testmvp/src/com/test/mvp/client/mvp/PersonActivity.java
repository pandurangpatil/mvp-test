package com.test.mvp.client.mvp;

import java.util.Set;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.test.mvp.client.ClientFactory;
import com.test.mvp.client.place.UserListPlace;
import com.test.mvp.client.place.UserPlace;
import com.test.mvp.client.ui.PersonEditor;
import com.test.mvp.client.ui.PersonView;
import com.test.mvp.client.ui.PersonViewImpl;
import com.test.mvp.shared.PersonProxy;
import com.test.mvp.shared.PersonRequest;

public class PersonActivity extends AbstractActivity implements PersonView.PersonActionHandler {
    interface Driver extends RequestFactoryEditorDriver<PersonProxy, PersonEditor> {
    };
    
    private ClientFactory  clientFactory;
    
    private UserPlace      place;
    
    private PersonViewImpl impl;
    
    private Driver         driver = GWT.create(Driver.class);
    
    public PersonActivity(UserPlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.place = place;
    }
    
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        impl = new PersonViewImpl();
        impl.setListner(this);
        if (UserPlace.NEW.equals(place.getToken())) {
            create(impl.getEditor());
        } else if (UserPlace.EDIT.equals(place.getToken())) {
            edit(place.getPerson(), impl.getEditor());
        }
        panel.setWidget(impl);
    }
    
    public void onStop() {
        impl.hide();
    }
    
    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
        
    }
    
    public void edit(PersonProxy person, PersonEditor editor) {
        // Initialize the driver with the top-level editor
        driver.initialize(clientFactory.getRequestFactory(), editor);
        // Copy the data in the object into the UI
        PersonRequest personReq = clientFactory.getRequestFactory().personRequest();
        driver.edit(person, personReq);
        personReq.persist().using(person);
    }
    
    public void create(PersonEditor editor) {
        // Initialize the driver with the top-level editor
        driver.initialize(clientFactory.getRequestFactory(), editor);
        PersonRequest personReq = clientFactory.getRequestFactory().personRequest();
        PersonProxy person = personReq.create(PersonProxy.class);
        driver.edit(person, personReq);
        personReq.persist().using(person);
    }
    
    @Override
    public void save() {
        RequestContext ctx = driver.flush();
        if (driver.hasErrors()) {
            Window.alert("There is some error");
        }
        ctx.fire(new Receiver<Void>() {
            
            @Override
            public void onSuccess(Void response) {
                PersonActivity.this.goTo(new UserListPlace("list"));
            }
            
            @Override
            public void onViolation(Set<Violation> errors) {
                Window.alert("There is some error on server");
            }
        });
    }
    
}
