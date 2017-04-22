package cn.qtech.service;

import cn.qtech.domain.UserPaper;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/17-15:54
 */
public interface UserPaperService extends BaseService<UserPaper>{
    List<UserPaper> queryAllByUserIdAndStatus(String userId, int status);

    boolean modifyContent(String paperId, String content);

    boolean insertUserPapersByBatch(List<UserPaper> userPapers);
}
