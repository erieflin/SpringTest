package com.elliot.restTemplates.restResponse;

public class BaseResponse {

    private final long id;
    private final String jwt;

    public  BaseResponse(long id, String jwt) {
        this.id = id;
        this.jwt = jwt;
    }

    public long getId() {
        return id;
    }

    public String getJwt() {
        return jwt;
    }
}