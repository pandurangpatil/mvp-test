package com.test.mvp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.test.mvp.shared.PersonProxy;

public class PersonEditor extends Composite implements Editor<PersonProxy> {
    interface Binder extends UiBinder<Widget, PersonEditor> {
    }
    
    @UiField
    ValueBoxEditorDecorator<String> firstName;
    
    @UiField
    ValueBoxEditorDecorator<String> lastName;
    
    @UiField
    ValueBoxEditorDecorator<String> emailId;
    
    public PersonEditor() {
        initWidget(GWT.<Binder> create(Binder.class).createAndBindUi(this));
    }
}
