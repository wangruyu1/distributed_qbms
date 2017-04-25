package cn.qtech.service;

import cn.qtech.domain.UserPaper;
import cn.qtech.domain.UserPaperWithBLOBs;

import java.util.Date;
import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/17-15:54
 */
public interface UserPaperService extends BaseService<UserPaper> {
    List<UserPaperWithBLOBs> queryAllByUserIdAndStatus(String userId, int status);

    boolean modifyUserPaperForCommit(UserPaperWithBLOBs userPaper);

    boolean insertUserPapersByBatch(List<UserPaperWithBLOBs> userPapers);

    boolean changeUserPaperStatus(Date date, int value);

    boolean changeUserPaperStatusToCommited(Date date, int value);

    List<UserPaperWithBLOBs> queryUserPaperByManagerAndStatus(String managerId, int status);
}
