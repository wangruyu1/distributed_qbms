package cn.qtech.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangruyu
 * @since 2017/4/21-15:53
 */
public enum UserPaperStatus {
    UNKNOW(-1), NOTBEGIN(1), BEGINING(2), COMMITED(3), FINISHED(4);
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPaperStatus.class);
    private int value;

    private UserPaperStatus(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static int valueOf(UserPaperStatus userPaperStatus) {
        return userPaperStatus.value;
    }

    public static UserPaperStatus valueOf(int value) {
        switch (value) {
            case -1:
                return UNKNOW;
            case 1:
                return NOTBEGIN;
            case 2:
                return BEGINING;
            case 3:
                return COMMITED;
            case 4:
                return FINISHED;
            default:
                LOGGER.error("未知的userpaper状态:" + value);
                return UNKNOW;
        }
    }
}
