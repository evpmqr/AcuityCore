package com.acuity.http.service.auth_filters;

import com.acuity.http.api.acuity_account.AcuityAccount;
import com.acuity.http.service.SparkApp;
import com.acuity.http.service.acuity_account.AcuityAccountService;
import spark.Filter;
import spark.Request;
import spark.Response;

import java.util.Optional;

import static spark.Spark.halt;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public abstract class AuthFilter implements Filter{

    public static final IllegalAccessError ILLEGAL_ACCESS_ERROR = new IllegalAccessError("Not authed.");

    @Override
    public void handle(Request request, Response response) throws Exception {
        Optional<AcuityAccount> currentAccount = AcuityAccountService.findAccountByHeader(request);
        if (!currentAccount.isPresent() || !isAuthed(currentAccount.get())) halt(401, "You are not authenticated.");
    }

    abstract boolean isAuthed(AcuityAccount acuityAccount);
}
