package cn.qtech.constant;

/**
 * @author wangruyu
 * @since 2017/4/24-14:51
 */
public enum UserType {
    MANAGER(1), USER(2), ADMIN(3);

    private UserType(int value) {
        this.value = value;
    }

    private int value;

    public int value() {
        return this.value;
    }
}
