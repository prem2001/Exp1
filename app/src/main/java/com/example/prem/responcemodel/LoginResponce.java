package com.example.prem.responcemodel;

/**
 * Created by prem on 9/3/18.
 */

public class LoginResponce {
    private Boolean success;
    private LoginResData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LoginResData getData() {
        return data;
    }

    public void setData(LoginResData data) {
        this.data = data;
    }
}
