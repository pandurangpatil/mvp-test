package com.test.mvp.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.test.mvp.client.place.UserListPlace;

public class PersonViewImpl extends DialogBox implements PersonView {
    
    private PersonEditor        editor;
    private PersonActionHandler listner;
    
    public PersonViewImpl() {
        setSize("300px", "200px");
        setText("Person Editor");
        editor = new PersonEditor();
        // Enable animation.
        setAnimationEnabled(true);
        
        // Enable glass background.
        setGlassEnabled(true);
        center();
        VerticalPanel panel = new VerticalPanel();
        HorizontalPanel hpanel = new HorizontalPanel();
        
        panel.add(editor);
        Button cancel = new Button("Cancel");
        cancel.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                listner.goTo(new UserListPlace("list"));
            }
        });
        Button save = new Button("Save");
        save.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                save();
                
            }
        });
        hpanel.add(save);
        hpanel.add(cancel);
        panel.add(hpanel);
        
        setWidget(panel);
    }
    
    public PersonEditor getEditor() {
        return editor;
    }
    
    public void setListner(PersonActionHandler listner) {
        this.listner = listner;
    }
    
    void save() {
        listner.save();
    }
}
