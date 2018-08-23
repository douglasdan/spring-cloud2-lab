package com.douglasdan.framework.common.util;

import com.douglasdan.framework.common.constants.Constants;
import com.douglasdan.framework.common.constants.HttpConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 *
 * @author Dougals.Dan
 * @date 2018-08-23
 */
public class HttpHelper {

    public static boolean isCanaryEnabled(HttpServletRequest request) {
        return "true".equalsIgnoreCase(request.getHeader(HttpConstants.X_DEPLOY_CANARY));
    }

    /**
     * try use global request id
     * @return
     */
    public static String generateRequestId() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String requestId = null;

        if (null != request) {
            requestId = request.getHeader(HttpConstants.X_REQUEST_ID);
        }

        if (StringUtils.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        return requestId;
    }
}
