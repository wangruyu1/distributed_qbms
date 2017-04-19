package cn.qtech.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author wangruyu
 * @since 2017/3/15-10:17
 */
public class AppException extends Exception {
    @Autowired
    private MessageSource messageSource;
    private LocaleContext localeContext = LocaleContextHolder.getLocaleContext();

    private int status = 500;
    private String code;
    private String message;

    public AppException() {
        super();
    }

    public AppException(String code) {
        this.message = messageSource.getMessage(code, new Object[]{}, localeContext.getLocale());
    }

    public AppException(int status, String code) {
        this.status = status;
        this.message = messageSource.getMessage(code, new Object[]{}, localeContext.getLocale());
    }


    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    protected AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
