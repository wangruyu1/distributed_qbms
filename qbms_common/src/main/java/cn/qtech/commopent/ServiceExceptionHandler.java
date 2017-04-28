package cn.qtech.commopent;

import cn.qtech.exception.AppException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangruyu
 * @since 2017/4/28-14:14
 */
@Component
@Aspect
public class ServiceExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);
    private static final String POINTCUT = "execution(* cn.qtech.service.impl.*.*(..))";
    @Autowired
    private LocalMessageSource localMessageSource;

    @Pointcut(value = POINTCUT)
    public void pointcut() {
    }

    @AfterThrowing(value = POINTCUT, throwing = "e")
    public void afterThrowing(Throwable e) throws AppException {
        LOGGER.error("service发生异常:\n");
        e.printStackTrace();
        throw new AppException(501, localMessageSource.getMessage("service.exception.occur"));
    }
}
