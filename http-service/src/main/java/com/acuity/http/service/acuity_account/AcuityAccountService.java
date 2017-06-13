package com.acuity.http.service.acuity_account;

import com.acuity.db.AcuityDB;
import com.acuity.http.service.util.BCrypt;
import com.acuity.http.api.acuity_account.AcuityAccount;
import com.acuity.http.service.util.JwtUtil;
import spark.Request;
import spark.Response;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccountService {

    public static  final AcuityAccount TEMP_ACC = new AcuityAccount("Zach", "zgherridge@gmail.com", BCrypt.hashpw("password123",BCrypt.gensalt()));

    public String login(Request request, Response response){
        AcuityDB.getAccountCollection().drop();
        AcuityDB.getAccountCollection().save(TEMP_ACC);

        String testUsername = request.queryMap("username").value();
        String testPassword = request.queryMap("password").value();

        AcuityAccount acuityAccount = AcuityDB.getAccountCollection().findOne("{email: #}", testUsername).as(AcuityAccount.class);

        return Optional.ofNullable(acuityAccount)
                .map(AcuityAccount::getPasswordHash)
                .map(passwordHashFromDB -> BCrypt.checkpw(testPassword, passwordHashFromDB))
                .filter(goodLogin -> goodLogin)
                .map(goodLogin -> {
                    try {
                        return "LOGIN_SUCCESS:" + JwtUtil.build(acuityAccount);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return "LOGIN_FAILED:" + e.getMessage();
                    }
                })
                .orElse("LOGIN_FAILED:Bad Login");
    }

    public AcuityAccount findCurrentAccount(Request request, Response response){
        if (new Random().nextBoolean()){
            return TEMP_ACC;
        }

        return null;
    }

}
