package com.test.mvp.client.ui;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.test.mvp.client.mvp.UserDataProvider;
import com.test.mvp.shared.PersonProxy;

public class UserListViewImpl extends Composite implements UserListView {
    
    public UserListViewImpl() {
        CellTable<PersonProxy> personList = new CellTable<PersonProxy>();
        personList.setSize("700px", "300px");
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
        
        UserDataProvider dataProvider = new UserDataProvider();
        dataProvider.addDataDisplay(personList);
        
        SimplePager pager = new SimplePager();
        pager.setDisplay(personList);
        VerticalPanel panel = new VerticalPanel();
        
        panel.add(personList);
        panel.add(pager);
        initWidget(panel);
    }
}
