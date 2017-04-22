package cn.qtech.service;

import cn.qtech.domain.ManagerUserMap;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/4/21-15:36
 */
public interface ManagerUserService extends BaseService<ManagerUserMap> {
    public List<String> queryUserIdsByManagerId(String managerId);
}
