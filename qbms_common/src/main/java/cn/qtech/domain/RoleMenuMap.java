package cn.qtech.domain;

public class RoleMenuMap {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu_map.id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu_map.role_id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    private String roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu_map.menu_id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    private String menuId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu_map.id
     *
     * @return the value of role_menu_map.id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu_map.id
     *
     * @param id the value for role_menu_map.id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu_map.role_id
     *
     * @return the value of role_menu_map.role_id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu_map.role_id
     *
     * @param roleId the value for role_menu_map.role_id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu_map.menu_id
     *
     * @return the value of role_menu_map.menu_id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu_map.menu_id
     *
     * @param menuId the value for role_menu_map.menu_id
     *
     * @mbggenerated Tue Mar 14 18:05:51 CST 2017
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}