package com.test.mvp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.test.mvp.client.place.UserListPlace;
import com.test.mvp.client.ui.UserListView.Presenter;
import com.test.mvp.shared.MVPRequestFactory;
import com.test.mvp.shared.PersonProxy;
import com.test.mvp.shared.PersonRequest;

public class UserViewImpl extends DialogBox implements UserView {
    interface Driver extends RequestFactoryEditorDriver<PersonProxy, PersonEditor> {
    };
    
    private MVPRequestFactory requestFactory;
    private PersonEditor      editor;
    private Presenter         listner;
    
    public UserViewImpl(MVPRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        setSize("300px", "600px");
        setText("Person Editor");
        editor = new PersonEditor();
        // Enable animation.
        setAnimationEnabled(true);
        
        // Enable glass background.
        setGlassEnabled(true);
        center();
        VerticalPanel panel = new VerticalPanel();
        panel.add(editor);
        Button save = new Button("Save");
        save.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                save();
                
            }
        });
        panel.add(save);
        setWidget(panel);
    }
    
    public void setPresenter(Presenter listner) {
        this.listner = listner;
    }
    
    Driver driver = GWT.create(Driver.class);
    
    public void init() {
        driver.initialize(requestFactory, editor);
    }
    
    public void edit(PersonProxy p) {
        // Initialize the driver with the top-level editor
        driver.initialize(requestFactory, editor);
        // Copy the data in the object into the UI
        PersonRequest personReq = requestFactory.personRequest();
        PersonProxy person = personReq.create(PersonProxy.class);
        driver.edit(person, personReq);
        personReq.persist().using(person);
        // Put the UI on the screen.
    }
    
    // Called by some UI action
    void save() {
        RequestContext ctx = driver.flush();
        ctx.fire(new Receiver<Void>() {
            
            @Override
            public void onSuccess(Void response) {
                listner.goTo(new UserListPlace("list"));
            }
        });
        if (driver.hasErrors()) {
            // A sub-editor reported errors
        }
        
    }
}
