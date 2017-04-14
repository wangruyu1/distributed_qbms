package cn.qtech.domain.dto;

/**
 * @author wangruyu
 * @since 2017/3/15-10:35
 */
public class BaseMessage {
    private int code;
    private boolean result;
    private String messageTip;
    private String message;

    public BaseMessage(int code, boolean result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public BaseMessage(int code, boolean result, String messageTip, String message) {
        this.code = code;
        this.result = result;
        this.messageTip = messageTip;
        this.message = message;
    }

    public String getMessageTip() {
        return messageTip;
    }

    public void setMessageTip(String messageTip) {
        this.messageTip = messageTip;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
