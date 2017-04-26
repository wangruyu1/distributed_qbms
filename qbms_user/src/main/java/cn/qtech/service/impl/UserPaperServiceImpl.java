package cn.qtech.service.impl;

import cn.qtech.domain.UserPaper;
import cn.qtech.domain.UserPaperWithBLOBs;
import cn.qtech.mapper.UserPaperMapper;
import cn.qtech.service.UserPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<UserPaperWithBLOBs> queryAllByUserIdAndStatus(String userId, int status) {
        return userPaperMapper.queryAllByUserIdAndStatus(userId, status);
    }

    @Override
    public boolean modifyUserPaperForCommit(UserPaperWithBLOBs userPaper) {
        return userPaperMapper.modifyUserPaperForCommit(userPaper) > 0;
    }

    @Override
    public boolean insertUserPapersByBatch(List<UserPaperWithBLOBs> userPapers) {
        return userPaperMapper.insertUserPapersByBatch(userPapers) > 0;
    }

    @Override
    public boolean changeUserPaperStatus(Date date, int value) {
        return userPaperMapper.changeUserPaperStatus(date, value);
    }

    @Override
    public boolean changeUserPaperStatusToCommited(Date date, int value) {
        return userPaperMapper.changeUserPaperStatusToCommited(date, value);
    }

    @Override
    public List<UserPaperWithBLOBs> queryUserPaperByManagerAndStatus(String managerId, int status) {
        return userPaperMapper.queryUserPaperByManagerAndStatus(managerId, status);
    }

    @Override
    public boolean grade(String userPaperId, int score, int status) {
        return userPaperMapper.grade(userPaperId, score, status) > 0;
    }

    @Override
    public boolean modifyUserPaperStatusById(String userPaperId, int value) {
        return userPaperMapper.modifyUserPaperStatusById(userPaperId, value);
    }

    @Override
    public UserPaperWithBLOBs queryUserPaperById(String userPaperId) {
        return userPaperMapper.selectByPrimaryKey(userPaperId);
    }
}
