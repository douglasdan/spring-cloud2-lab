package com.douglasdan.framework.common.constants;

/**
 *
 * @author Douglas.Dan
 * @date 2018-08-21
 */
public class Constants {

    public static final String FRAMEWORK_BASE_PACKAGE= "com.douglasdan";

    public static final String HTTP_CHARSET = "UTF-8";

    public static final String ENABLE = "1";

    public static final String DISABLE = "0";

    public static final String YES = "1";

    public static final String NO = "0";

    public static long yearMillis = 365L*24L*3600L*1000L;

    public static long dayMillis = 24L*3600L*1000L;

    /**
     * 未知错误
     */
    public static final String UNKNOWN_ERROR= "-1";

    /**
     * 成功
     */
    public static final String SUCCESS= "0";

    /**
     * 签名结果错误
     */
    public static final String REQUEST_ILLEGAL= "001";

    /**
     * 版本号不正确
     */
    public static final String REQUEST_VERSION_INVALID= "002";

    /**
     * 时间差异超出范围
     */
    public static final String REQUEST_TIMESTAMP_INVALID= "003";

    /**
     * SignatureNonce值已被使用过
     */
    public static final String REQUEST_SIGNATURENONCE_INVALID= "004";

    /**
     * 请求参数不正确
     */
    public static final String REQUEST_PARAMETERS_INVALID = "005";

    /**
     * IP不在授权范围内
     */
    public static final String REQUEST_IP_NOT_ALLOWED = "006";

    /**
     * 接口没有权限
     */
    public static final String PRIVILEGE_FAILED = "007";

    /**
     * 数据没有权限
     */
    public static final String DATA_PRIVILEGE_FAILED = "008";

    /**
     * 资源不存在
     */
    public static final String OBJECT_NOT_EXISTS = "009";

    /**
     * 请求内容不规范
     */
    public static final String REQUEST_BODY_INVALID = "011";

    /**
     * 费用不足
     */
    public static final String RUN_OUT_OF_FEE = "012";

}
