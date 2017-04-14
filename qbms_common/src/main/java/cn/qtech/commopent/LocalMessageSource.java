package cn.qtech.commopent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author wangruyu
 * @since 2017/3/21-16:53
 */
@Component
public class LocalMessageSource {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        return this.getMessage(code, null, "操作成功", Locale.CHINA);
    }

    public String getMessage(String code, Object[] params) {
        return this.getMessage(code, params, "操作成功", Locale.CHINA);
    }

    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, new Object[]{}, Locale.CHINA);
    }

    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, locale);
    }

}
