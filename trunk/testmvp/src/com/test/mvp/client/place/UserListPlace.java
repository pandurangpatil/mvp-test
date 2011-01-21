package com.test.mvp.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

// public class HelloPlace extends ActivityPlace<HelloActivity>
public class UserListPlace extends Place {
    private String token;
    
    public UserListPlace(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
    
    public static class Tokenizer implements PlaceTokenizer<UserListPlace> {
        
        @Override
        public String getToken(UserListPlace place) {
            return place.getToken();
        }
        
        @Override
        public UserListPlace getPlace(String token) {
            return new UserListPlace(token);
        }
        
    }
    
    // @Override
    // protected Place getPlace(String token)
    // {
    // return new HelloPlace(token);
    // }
    //
    // @Override
    // protected Activity getActivity()
    // {
    // return new HelloActivity("David");
    // }
}
