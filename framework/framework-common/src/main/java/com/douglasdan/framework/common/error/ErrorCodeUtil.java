package com.douglasdan.framework.common.error;

/**
 * Created by Dougals.Dan on 2018-08-22.
 */
import com.douglasdan.framework.common.constants.Constants;
import com.douglasdan.framework.common.util.HttpHelper;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Douglas.Dan
 */
@Component
public class ErrorCodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(ErrorCodeUtil.class);

    private static Properties prop = new Properties();

    static {
        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(
                ClasspathHelper.forPackage(Constants.FRAMEWORK_BASE_PACKAGE)).setScanners(new ResourcesScanner()));
        Set<String> filesName = reflections.getResources(Pattern.compile(".*\\.error.properties"));
        loadResources(filesName);
    }

    @PostConstruct
    public void init() {

    }

    private static void loadResources(Set<String> filesName) {
        if (CollectionUtils.isEmpty(filesName)) {
            return;
        }
        for (String fileName : filesName) {
            PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource resource = pathMatchingResourcePatternResolver.getResource(
                    "classpath:" + fileName);
            if (!resource.exists()) {
                logger.error("classpath:{}不存在", fileName);
            }
            try {
                prop.load(resource.getInputStream());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public static String generateErrorMessage(String errorCode, Object... args) {
        String message = prop.getProperty(errorCode, errorCode);
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }

    public static Throwable getRootCause(Throwable e) {
        Throwable cause = e;
        while(null != cause.getCause()) {
            cause = cause.getCause();
        }
        return cause;
    }

    public static String getErrorCauseStackCode(Exception e) {

        String simpleErrorMsg = null;
        Throwable rootCause = getRootCause(e);

        for (StackTraceElement ste :rootCause.getStackTrace()) {
            String s = ste.toString();
            if (s.startsWith(Constants.FRAMEWORK_BASE_PACKAGE) && StringUtils.isNotBlank(s)) {
                if (simpleErrorMsg == null) {
                    simpleErrorMsg = s;
                } else {
                    simpleErrorMsg += "\n"+s;
                }
                return simpleErrorMsg;
            }
        }
        return null;
    }

    public static void printException(Logger logger, Exception e) {

        logger.error("handle exception: " + e.getMessage());
        if (shouldPrintDebugLog(logger)) {
            for(StackTraceElement ste : e.getStackTrace())
            {
                logger.error("\tat " + ste);
            }
        }
    }

    public static boolean shouldPrintDebugLog(Logger logger) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (null != request && HttpHelper.isCanaryEnabled(request)) {
            return true;
        }
        if (logger.isDebugEnabled()) {
            return true;
        }
        return false;
    }

}
