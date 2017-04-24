package cn.qtech.commopent;

import cn.qtech.domain.dto.BaseMessage;
import cn.qtech.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wangruyu
 * @since 2017/3/21-14:39
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private LocalMessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handler(Exception e) {
        LOGGER.error("控制器异常:");
        e.printStackTrace();
        if (e instanceof AppException) {
            return new BaseMessage(500, false, e.getMessage());
        }
        return new BaseMessage(500, false, messageSource.getMessage("controller.exception.occur"));
    }

}
