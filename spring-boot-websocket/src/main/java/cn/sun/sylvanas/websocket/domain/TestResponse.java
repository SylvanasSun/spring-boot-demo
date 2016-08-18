package cn.sun.sylvanas.websocket.domain;

/**
 * Created by sylvanasp on 2016/7/27.
 */
public class TestResponse {

    private String responseMessage;

    public TestResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
