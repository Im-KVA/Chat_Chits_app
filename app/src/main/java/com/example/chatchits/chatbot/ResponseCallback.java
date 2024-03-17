package com.example.chatchits.chatbot;

public interface ResponseCallback {

    void onResponse(String response);

    void onError(Throwable throwable);

}
