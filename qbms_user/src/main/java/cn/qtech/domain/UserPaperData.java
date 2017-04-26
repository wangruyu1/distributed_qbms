package cn.qtech.domain;

/**
 * @author wangruyu
 * @since 2017/4/26-14:58
 */
public class UserPaperData {
    private String userPaperId;
    private String content;
    private String userAnswer;
    private String rightAnswer;

    public String getUserPaperId() {
        return userPaperId;
    }

    public void setUserPaperId(String userPaperId) {
        this.userPaperId = userPaperId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
