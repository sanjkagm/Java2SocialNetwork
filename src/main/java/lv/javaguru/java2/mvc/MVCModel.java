package lv.javaguru.java2.mvc;

public class MVCModel {

    private String jspName;
    private Object messages;
    private Object data;



    public MVCModel(String jspName, Object data, Object messages) {
        this.jspName = jspName;
        this.data = data;
        this.messages = messages;
    }

    public String getJspName() {
        return jspName;
    }

    public Object getData() {
        return data;
    }

    public Object getMessages() {
        return messages;
    }

}