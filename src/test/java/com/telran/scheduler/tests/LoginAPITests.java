package com.telran.scheduler.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAPITests {

    String baseURL = "https://super-scheduler-app.herokuapp.com";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFhQGdtYWlsLmNvbSJ9.6gPFk745YktR7XchAbLeSFCzZyOx1DzTBxjlvkJwBw0";

    @Test
    public void loginRegisteredUserTest() throws IOException {
        String response = sendRequestToLogin("/api/login"
                , "aa@gmail.com", "Aa1234567");

        //System.out.println(response);
        JsonElement parsed = new JsonParser().parse(response);
        JsonElement token = parsed.getAsJsonObject().get("token");
        System.out.println(token);
        JsonElement status = parsed.getAsJsonObject().get("status");
        Assert.assertEquals(status.toString(),"\"Login success\"");
        JsonElement registration = parsed.getAsJsonObject().get("registration");
        //Assert.assertEquals(registration.toString(),"false");
        Assert.assertFalse(registration.getAsBoolean());
    }

    @Test
    public void getCodeResposeLoginTest() throws IOException {
        String email = "aa@gmail.com";
        String pwd = "Aa1234567";

        int statusCode = Request.Post(baseURL + "/api/login").bodyString("{ \"email\": \""
                        + email + "\", \"password\": \"" + pwd + "\"}"
                , ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,200);

    }

    @Test
    public void getRecordsPeriodTest() throws IOException {
        String res = Request.Get(baseURL + "/api/recordsPeriod")
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .execute().returnContent().asString();

        System.out.println(res);

        String events = Request.Post(baseURL + "/api/records")
                .addHeader("Authorization", token)
                .addHeader("Content-Type", "application/json")
                .bodyString(res,ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();

        System.out.println(events);
    }



    private String sendRequestToLogin(String controller, String email, String password) throws IOException {
        String response = Request.Post(baseURL + controller)
                .bodyString("{ \"email\": \"" + email + "\", \"password\": \""
                                + password + "\"}"
                        , ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return response;
    }
}
