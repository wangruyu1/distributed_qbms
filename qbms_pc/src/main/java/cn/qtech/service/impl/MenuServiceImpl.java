package cn.qtech.service.impl;

import cn.qtech.domain.Menu;
import cn.qtech.mapper.MenuMapper;
import cn.qtech.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangruyu
 * @since 2017/3/16-10:55
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryMenusByRoleIds(List<String> roleIds) {
        return menuMapper.queryMenusByRoleIds(roleIds);
    }

    @Override
    public List<Menu> queryTreeMenusByRoleIds(List<String> roleIds) {
        List<Menu> allMenus = this.queryMenusByRoleIds(roleIds);
        Map<String, Menu> menuMap = allMenus.stream().collect(Collectors.toMap(Menu::getId, (p) -> p));
        List<Menu> rootMenu = new ArrayList<>();
        menuMap.forEach((menuId, menu) -> {
            if (menu.getParentid() != null && !"".equals(menu.getParentid())) {
                menuMap.get(menu.getParentid()).getSubMenus().add(menu);
            } else {
                rootMenu.add(menu);
            }
        });
        //获取所有父级菜单
//        if (allMenus != null && allMenus.size() > 0) {
//            allMenus.forEach(menu -> {
//                if (menu.getParentid() == null || menu.getParentid().equals("")) {
//                    menuMap.put(menu.getId(), menu);
//                }
//            });
//            allMenus.forEach(menu -> {
//                if (menu.getParentid() != null && !menu.getParentid().equals("")) {
//                    String pid = menu.getParentid();
//                    menuMap.get(pid).getSubMenus().add(menu);
//                }
//            });
//        }
//        return Arrays.asList(menuMap.values().toArray(new Menu[menuMap.size()]));
        return rootMenu;
    }
}
