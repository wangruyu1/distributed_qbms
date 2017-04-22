package cn.qtech.domain.dto;

import cn.qtech.domain.MakePaper;
import cn.qtech.domain.Paper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/21-15:23
 */
public class MakePaperDTO {
    private Paper paper;
    private String managerId;
    private MakePaper makePaper;
    private List<String> userIds = new ArrayList<>();

    public MakePaper getMakePaper() {
        return makePaper;
    }

    public void setMakePaper(MakePaper makePaper) {
        this.makePaper = makePaper;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
