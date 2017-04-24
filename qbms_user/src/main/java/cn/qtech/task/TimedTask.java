package cn.qtech.task;

import cn.qtech.constant.UserPaperStatus;
import cn.qtech.service.UserPaperService;
import cn.qtech.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wangruyu
 * @since 2017/4/19-18:25
 */
@Component
public class TimedTask {
    @Autowired
    private UserPaperService userPaperService;
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(TimedTask.class);

    @Scheduled(cron = "0 */1 * * * ?")
    public void changeUserPaperStatus() {
        LOGGER.info("修改userpaper状态正在执行...");
        userPaperService.changeUserPaperStatus(DateUtil.getCommonDate(), UserPaperStatus.BEGINING.value());
    }

}
