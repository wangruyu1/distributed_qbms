package cn.qtech.exception;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author wangruyu
 * @since 2017/3/15-10:17
 */
public class AppException extends Exception {
    private LocaleContext localeContext = LocaleContextHolder.getLocaleContext();

    private int status = 0;
    private int code = 500;
    private String message;

    public AppException() {
        super();
    }

    public AppException(String message) {
        this.message = message;
    }

    public AppException(int code, String message) {
        this.code = code;
        this.message = message;
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
