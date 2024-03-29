package com.test.mvp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.Resources;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.test.mvp.client.mvp.PersonDataProvider;
import com.test.mvp.client.place.UserPlace;
import com.test.mvp.shared.PersonProxy;

public class PersonListViewImpl extends Composite implements PersonListView {
    private Presenter              presenter = null;
    private CellTable<PersonProxy> personList;
    private SimplePager            pager;
    private PersonDataProvider     dataProvider;
    
    public PersonListViewImpl() {
        personList = new CellTable<PersonProxy>();
        personList.setPageSize(10);
        TextColumn<PersonProxy> idCol = new TextColumn<PersonProxy>() {
            @Override
            public String getValue(PersonProxy object) {
                return object.getId() + "";
            }
        };
        personList.addColumn(idCol, "ID");
        TextColumn<PersonProxy> firstNameCol = new TextColumn<PersonProxy>() {
            @Override
            public String getValue(PersonProxy object) {
                return object.getFirstName();
            }
        };
        personList.addColumn(firstNameCol, "First Name");
        TextColumn<PersonProxy> lastNameCol = new TextColumn<PersonProxy>() {
            @Override
            public String getValue(PersonProxy object) {
                return object.getLastName();
            }
        };
        personList.addColumn(lastNameCol, "Last Name");
        TextColumn<PersonProxy> emailCol = new TextColumn<PersonProxy>() {
            @Override
            public String getValue(PersonProxy object) {
                return object.getEmailId();
            }
        };
        personList.addColumn(emailCol, "Email");
        pager = new SimplePager(TextLocation.CENTER, (Resources) GWT.create(Resources.class), false, 1000, true);
        pager.setRangeLimited(true);
        pager.setDisplay(personList);
        VerticalPanel panel = new VerticalPanel();
        
        Button newPerson = new Button("New Person");
        
        newPerson.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                UserPlace place = new UserPlace(UserPlace.NEW);
                presenter.goTo(place);
            }
        });
        
        panel.add(personList);
        panel.add(pager);
        panel.add(newPerson);
        
        initWidget(panel);
        
        final SingleSelectionModel<PersonProxy> selectionModel = new SingleSelectionModel<PersonProxy>();
        personList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            private boolean unselectEvent = false;
            
            public void onSelectionChange(SelectionChangeEvent event) {
                if (!unselectEvent) {
                    PersonProxy selected = selectionModel.getSelectedObject();
                    selectionModel.setSelected(selected, false);
                    unselectEvent = true;
                    UserPlace place = new UserPlace(UserPlace.EDIT);
                    place.setPerson(selected);
                    presenter.goTo(place);
                } else {
                    unselectEvent = false;
                }
            }
        });
    }
    
    public void setDataProvider(PersonDataProvider dataProvider) {
        this.dataProvider = dataProvider;
        dataProvider.addDataDisplay(personList);
    }
    
    public void setRowCount(int count) {
        personList.setRowCount(count);
    }
    
    @Override
    public void setPresenter(Presenter listener) {
        presenter = listener;
    }
}
