package cn.qtech.service;

import cn.qtech.domain.Menu;

import java.util.List;

/**
 * @author wangruyu
 * @since 2017/3/16-10:34
 */
public interface MenuService {
    public List<Menu> queryMenusByRoleIds(List<String> roleIds);

    public List<Menu> queryTreeMenusByRoleIds(List<String> roleIds);
}
