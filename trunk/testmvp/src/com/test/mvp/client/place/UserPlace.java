package com.test.mvp.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.test.mvp.shared.MVPRequestFactory;
import com.test.mvp.shared.PersonProxy;

// public class HelloPlace extends ActivityPlace<HelloActivity>
public class UserPlace extends Place {
    private String            token;
    private PersonProxy       person;
    
    private MVPRequestFactory requestFactory;
    
    public UserPlace(String token) {
        this.token = token;
    }
    
    public PersonProxy getPerson() {
        return person;
    }
    
    public void setPerson(PersonProxy person) {
        this.person = person;
    }
    
    public String getToken() {
        return token;
    }
    
    public MVPRequestFactory getRequestFactory() {
        return requestFactory;
    }
    
    public void setRequestFactory(MVPRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }
    
    public static class Tokenizer implements PlaceTokenizer<UserPlace> {
        
        @Override
        public String getToken(UserPlace place) {
            return place.getToken();
        }
        
        @Override
        public UserPlace getPlace(String token) {
            return new UserPlace(token);
        }
        
    }
    
}
