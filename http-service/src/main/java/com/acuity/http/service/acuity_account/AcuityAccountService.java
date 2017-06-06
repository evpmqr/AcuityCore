package com.acuity.http.service.acuity_account;

import com.acuity.http.service.util.BCrypt;
import com.acuity.http.api.acuity_account.AcuityAccount;
import com.acuity.http.service.util.JwtUtil;
import spark.Request;
import spark.Response;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccountService {

    public static  final AcuityAccount TEMP_ACC = new AcuityAccount("Zach", "zgherridge@gmail.com", "TEMPPASSWORDHASH");

    public String login(Request request, Response response){
        String username = request.queryMap("username").value();
        String password = request.queryMap("password").value();

        String passwordHashFromDB = BCrypt.hashpw("testpassword", BCrypt.gensalt());

        if (BCrypt.checkpw(password, passwordHashFromDB)){
            try {
                return "OK|" + JwtUtil.build(TEMP_ACC);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return "LOGIN_FAILED";
    }

    public AcuityAccount findCurrentAccount(Request request, Response response){
        if (new Random().nextBoolean()){
            return TEMP_ACC;
        }

        return null;
    }

}
