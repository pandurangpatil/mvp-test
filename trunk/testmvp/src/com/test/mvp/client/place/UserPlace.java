package com.test.mvp.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

// public class HelloPlace extends ActivityPlace<HelloActivity>
public class UserPlace extends Place {
    private String token;
    
    public UserPlace(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return token;
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
