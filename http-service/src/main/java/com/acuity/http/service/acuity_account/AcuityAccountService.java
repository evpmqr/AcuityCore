package com.acuity.http.service.acuity_account;

import com.acuity.db.AcuityDB;
import com.acuity.http.service.util.BCrypt;
import com.acuity.http.api.acuity_account.AcuityAccount;
import com.acuity.http.service.util.JwtUtil;
import com.auth0.jwt.interfaces.Claim;
import spark.Request;
import spark.Response;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Zachary Herridge on 6/1/2017.
 */
public class AcuityAccountService {

    public static  final AcuityAccount TEMP_ACC = new AcuityAccount("Zach", "zgherridge@gmail.com", BCrypt.hashpw("password123", BCrypt.gensalt()));

    public String login(Request request, Response response){
        AcuityDB.getAccountCollection().drop();
        AcuityDB.getAccountCollection().save(TEMP_ACC);

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

    public AcuityAccount findCurrentAccount(Request request, Response response){
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

}
