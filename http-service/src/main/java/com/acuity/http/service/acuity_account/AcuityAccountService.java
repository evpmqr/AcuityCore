package com.acuity.http.service.acuity_account;

import com.acuity.http.api.JSONUtil;
import com.acuity.http.api.acuity_account.AcuityAccount;
import javafx.util.Pair;
import spark.Request;
import spark.Response;

import java.util.Random;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccountService {

    public AcuityAccount findCurrentAccount(Request request, Response response){

        if (new Random().nextBoolean()){
            return new AcuityAccount("Zach", "zgherridge@gmail.com", "SADAS324123SDA$$#$ASDSADSAD");
        }

        return null;
    }

}
