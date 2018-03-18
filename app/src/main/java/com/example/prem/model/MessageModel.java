package com.example.prem.model;

/**
 * Created by prem on 9/3/18.
 */

public class MessageModel {
    private String senderMessage;
    private String receiverMessage;
    private int type;

    public MessageModel(String s, String s1,int i) {
        this.senderMessage=s;
        this.receiverMessage=s1;
        this.type=i;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void setReceiverMessage(String receiverMessage) {
        this.receiverMessage = receiverMessage;
    }
}
