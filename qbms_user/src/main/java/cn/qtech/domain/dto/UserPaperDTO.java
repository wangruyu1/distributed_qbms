package cn.qtech.domain.dto;

import java.util.Date;

/**
 * @author wangruyu
 * @since 2017/4/24-16:08
 */
public class UserPaperDTO {
    private String userPaperId;
    private Date createTime;
    private Date startTime;
    private int totalTime;
    private String userId;
    private String userName;
    private int score;

    public String getUserPaperId() {
        return userPaperId;
    }

    public void setUserPaperId(String userPaperId) {
        this.userPaperId = userPaperId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
