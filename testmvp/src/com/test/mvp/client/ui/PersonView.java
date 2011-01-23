package com.test.mvp.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.test.mvp.shared.PersonProxy;

public interface PersonView extends IsWidget {
    
    interface PersonActionHandler {
        void goTo(Place place);
        
        void edit(PersonProxy person, PersonEditor editor);
        
        void create(PersonEditor editor);
        
        void save();
    }
    
}
