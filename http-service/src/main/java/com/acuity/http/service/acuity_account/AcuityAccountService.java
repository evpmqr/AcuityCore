package com.acuity.http.service.acuity_account;

import com.acuity.db.AcuityDB;
import com.acuity.http.api.util.JsonUtil;
import com.acuity.http.service.util.BCrypt;
import com.acuity.http.api.acuity_account.AcuityAccount;
import com.acuity.http.service.util.JwtUtil;
import com.acuity.http.service.util.PostUtil;
import com.auth0.jwt.interfaces.Claim;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import static spark.Spark.halt;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccountService {

    public static synchronized String postRegister(Request request, Response response){
        PostUtil account = JsonUtil.getGSON().fromJson(request.body(), PostUtil.class);

        String email = account.getString("username");
        String displayName = account.getString("displayName");
        String password = account.getString("password");

        if (email == null || displayName == null || password == null){
            halt();
        }

        Optional<AcuityAccount> accountByEmail = findAccountByEmail(email);
        if (accountByEmail.isPresent()) return JsonUtil.toJSON("result", "Email already in use");

        Optional<AcuityAccount> accountByDisplayName = findAccountByDisplayName(displayName);
        if (accountByDisplayName.isPresent()) return JsonUtil.toJSON("result", "Display name already in use");

        AcuityDB.getAccountCollection().save(new AcuityAccount(displayName, email, BCrypt.hashpw(password, BCrypt.gensalt())));

        return JsonUtil.toJSON("result", "Account registered");
    }

    public static String login(Request request, Response response){
        String testUsername = request.queryMap("username").value();
        String testPassword = request.queryMap("password").value();

        Optional<AcuityAccount> accountByEmail = findAccountByEmail(testUsername);

        if (!accountByEmail.isPresent()) return "LOGIN_FAILED:Bad Login";

        return accountByEmail
                .map(AcuityAccount::getPasswordHash)
                .map(passwordHashFromDB -> BCrypt.checkpw(testPassword, passwordHashFromDB))
                .filter(goodLogin -> goodLogin)
                .map(goodLogin -> "LOGIN_SUCCESS:" + JwtUtil.build(accountByEmail.get()))
                .orElse("LOGIN_FAILED:Bad Login");
    }

    public static AcuityAccount getCurrentAccount(Request request, Response response){
        return findAccountByHeader(request).orElse(null);
    }

    public static Optional<AcuityAccount> findAccountByHeader(Request request){
        String tooken = request.headers("ACUITY_AUTH");
        return JwtUtil.decode(tooken).map(decodedJWT -> decodedJWT.getHeaderClaim("email"))
                .map(Claim::asString)
                .map(AcuityAccountService::findAccountByEmail)
                .flatMap(Function.identity());
    }

    public static Optional<AcuityAccount> findAccountByEmail(String email){
        return Optional.ofNullable(AcuityDB.getAccountCollection().findOne("{email: #}", email).as(AcuityAccount.class));
    }

    public static Optional<AcuityAccount> findAccountByDisplayName(String displayName){
        return Optional.ofNullable(AcuityDB.getAccountCollection().findOne("{displayName: #}", displayName).as(AcuityAccount.class));
    }

}
