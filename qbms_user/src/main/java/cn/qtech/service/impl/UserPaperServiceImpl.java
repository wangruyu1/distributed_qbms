package cn.qtech.service.impl;

import cn.qtech.domain.UserPaper;
import cn.qtech.mapper.UserPaperMapper;
import cn.qtech.service.UserPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/17-15:55
 */
@Service
public class UserPaperServiceImpl implements UserPaperService {
    @Autowired
    private UserPaperMapper userPaperMapper;

    @Override
    public List<UserPaper> queryAll() {
        return null;
    }

    @Override
    public List<UserPaper> queryByUser(String userId) {
        return null;
    }

    @Override
    public boolean insert(UserPaper userPaper) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(UserPaper userPaper) {
        return false;
    }

    @Override
    public List<UserPaper> queryAllByUserIdAndStatus(String userId, int status) {
        return userPaperMapper.queryAllByUserIdAndStatus(userId, status);
    }

    @Override
    public boolean modifyContent(String paperId, String content) {
        return userPaperMapper.modifyContent(paperId, content) > 0;
    }

    @Override
    public boolean insertUserPapersByBatch(List<UserPaper> userPapers) {
        return userPaperMapper.insertUserPapersByBatch(userPapers) > 0;
    }
}
