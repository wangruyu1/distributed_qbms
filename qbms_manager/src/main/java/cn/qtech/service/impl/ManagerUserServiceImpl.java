package cn.qtech.service.impl;

import cn.qtech.domain.ManagerUserMap;
import cn.qtech.mapper.ManagerUserMapMapper;
import cn.qtech.service.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/21-15:37
 */
@Service
public class ManagerUserServiceImpl implements ManagerUserService {
    @Autowired
    private ManagerUserMapMapper managerUserMapMapper;

    @Override
    public List<ManagerUserMap> queryAll() {
        return null;
    }

    @Override
    public List<ManagerUserMap> queryByUser(String userId) {
        return null;
    }

    @Override
    public boolean insert(ManagerUserMap managerUserMap) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(ManagerUserMap managerUserMap) {
        return false;
    }

    @Override
    public List<String> queryUserIdsByManagerId(String managerId) {
        return managerUserMapMapper.queryUserIdsByManagerId(managerId);
    }
}
