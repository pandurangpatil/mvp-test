package com.test.mvp.client.ui;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.test.mvp.client.mvp.PersonDataProvider;

public interface PersonListView extends IsWidget {
    void setPresenter(Presenter listener);
    
    void setRowCount(int count);
    
    void setDataProvider(PersonDataProvider dataProvider);
    
    public interface Presenter {
        void goTo(Place place);
    }
}
