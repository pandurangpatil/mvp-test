package com.test.mvp.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.test.mvp.client.mvp.AppActivityMapper;
import com.test.mvp.client.mvp.AppPlaceHistoryMapper;
import com.test.mvp.client.place.UserListPlace;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Testmvp implements EntryPoint {
    private UserListPlace defaultPlace = new UserListPlace("list");
    private SimplePanel   appWidget    = new SimplePanel();
    
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();
        
        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);
        
        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);
        
        RootPanel.get().add(appWidget);
        // Goes to place represented on URL or default place
        historyHandler.handleCurrentHistory();
    }
    
    // public void onModuleLoad() {
    //
    // final EventBus eventBus = new SimpleEventBus();
    // final MVPRequestFactory requestFactory = GWT.create(MVPRequestFactory.class);
    // requestFactory.initialize(eventBus);
    // PersonRequest personReq = requestFactory.personRequest();
    // for (int i = 0; i < 20; i++) {
    // PersonRequest personReq1 = requestFactory.personRequest();
    // PersonProxy newPerson = personReq1.create(PersonProxy.class);
    // newPerson.setFirstName("first" + i);
    // newPerson.setLastName("last" + i);
    // newPerson.setEmailId("first.last@gmail.com" + i);
    // personReq1.save().using(newPerson).fire();
    //
    // }
    //
    // personReq.listAll().fire(new Receiver<List<PersonProxy>>() {
    //
    // @Override
    // public void onSuccess(List<PersonProxy> response) {
    // Iterator<PersonProxy> itr = response.iterator();
    // FlexTable table = new FlexTable();
    // int row = 0;
    // while (itr.hasNext()) {
    // PersonProxy personProxy = (PersonProxy) itr.next();
    // table.setWidget(row, 0, new HTML("" + personProxy.getId()));
    // table.setWidget(row, 1, new HTML("" + personProxy.getFirstName()));
    // table.setWidget(row, 2, new HTML("" + personProxy.getLastName()));
    // row++;
    // }
    // RootPanel.get().add(table);
    // }
    //
    // });
    //
    // FlexTable container = new FlexTable();
    // final TextBox first = new TextBox();
    // container.setWidget(0, 0, new HTML("First Name: "));
    // container.setWidget(0, 1, first);
    // final TextBox last = new TextBox();
    // container.setWidget(1, 0, new HTML("Last Name: "));
    // container.setWidget(1, 1, last);
    // final TextBox email = new TextBox();
    // container.setWidget(2, 0, new HTML("Email: "));
    // container.setWidget(2, 1, email);
    // Button save = new Button("Save");
    // container.setWidget(3, 1, save);
    // save.addClickHandler(new ClickHandler() {
    //
    // @Override
    // public void onClick(ClickEvent event) {
    // PersonRequest personReq = requestFactory.personRequest();
    // PersonProxy newPerson = personReq.create(PersonProxy.class);
    // newPerson.setFirstName(first.getText());
    // newPerson.setLastName(last.getText());
    // newPerson.setEmailId(email.getText());
    // personReq.save().using(newPerson).fire();
    // }
    // });
    // RootPanel.get().add(container);
    // }
}
