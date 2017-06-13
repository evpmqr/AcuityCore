package com.acuity.http.service.util;

import java.util.HashMap;

/**
 * Created by Zachary Herridge on 6/13/2017.
 */
public class PostUtil extends HashMap<String, Object> {

    public String getString(String key){
        Object object = get(key);
        if (object instanceof String){
            if (((String) object).trim().isEmpty()){
                return null;
            }
            return (String) object;
        }
        return null;
    }

}
