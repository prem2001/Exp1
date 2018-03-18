package com.example.prem.model;

import butterknife.BindView;

/**
 * Created by prem on 13/3/18.
 */

public class SenderMsgResponse {

    private Boolean success;
    private MsgResponceModel data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public MsgResponceModel getData() {
        return data;
    }

    public void setData(MsgResponceModel data) {
        this.data = data;
    }
}
