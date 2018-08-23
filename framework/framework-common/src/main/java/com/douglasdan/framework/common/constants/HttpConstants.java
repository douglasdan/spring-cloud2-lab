package com.douglasdan.framework.common.constants;

/**
 *
 * @author Douglas.Dan
 * @date 2018-08-21
 */
public class HttpConstants extends Constants {

    /**
     * 全局唯一请求ID
     */
    public static final String X_REQUEST_ID = "x-request-id";

    /**
     * 身份验证成功，在gateway中可能会有多重身份、权限认证，只要有一个通过，那么后面不在做身份、权限校验
     * 针对不同API，接口多个版本做验证
     */
    public static final String X_AUTH_PASSED = "x-auth-passed";

    /**
     * gateway通过此属性获取response status
     * 在后台service返回http status不等于200时，不要直接返回，否则在gateway中会认为调用失败
     */
    public static final String X_SERVICE_HTTP_RESP_STATUS = "x-service-resp-http-status";

    /**
     * 金丝雀发布，通过在http加header来表示此次调用是A/B测试，值为true时起用，
     * 在后续调用链路里，加上debug日志
     * 为调用监控添加详细的数据
     */
    public static final String X_DEPLOY_CANARY= "x-deploy-canary";

}