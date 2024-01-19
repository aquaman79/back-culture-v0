package com.ntiersproject.cultureapi.bean;

import lombok.Builder;
import lombok.Data;

public class JetonJwt {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
