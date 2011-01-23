package com.test.mvp.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface PersonView extends IsWidget {
    
    interface PersonActionHandler {
        void goTo(Place place);
        
        void save();
    }
    
}
