package com.ruoyi.cms.util;

/**
 * cms模块常量
 */
public class CmsConstants {

    /**
     * 使用状态：0-未使用
     */
    public static final String USE_STATE_NO = "0";
    /**
     * 使用状态：1-使用中
     */
    public static final String USE_STATE_YES = "1";
    /*专辑素材使用表的表名*/
    public static final String MATERIAL_USE_ALBUM_TABLE_NAME="cms_album_material";
    /*专辑素材使用表的列名*/
    public static final String MATERIAL_USE_ALBUM_COLUMN_NAME="album_id";


    public static final String MATERIAL_USE_AD_TABLE_NAME="cms_ad_material";

    public static final String MATERIAL_USE_AD_COLUMN_NAME="ad_id";

    /**
     * 审批状态：0-待审批
     */
    public static final Integer AUDIT_STATE_TODO = 0;
    /**
     * 审批状态：2-审批未通过
     */
    public static final Integer AUDIT_STATE_REJECT = 2;

    /**
     * 审批状态：1-审批通过
     */
    public static final Integer AUDIT_STATE_AGREE = 1;

    /**
     * 状态-正常
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 状态-停用
     */
    public static final Integer STATUS_UNNORMAL = 1;
    /**
     * 评论类型-留言
     */
    public static final String COMMENT_TYPE_LIUYAN="liuyan";
    /**
     * 博客主题
     */
    public static final String KEY_BLOG_THEME="blog.theme";
    /**
     * 登录主题（后台)
     */
    public static final String KEY_LOGIN_THEME_ADMIN="login.theme.admin";
    /**
     * 登录主题（前台)
     */
    public static final String KEY_LOGIN_THEME_FRONT="login.theme.front";

    /**
     * 前台页面(主题)
     */
    public static final String KEY_FRONT_PLATFORM="front.platform";
    /**
     * 编辑器类型
     */
    public static final String KEY_EDITOR_TYPE="editor.type";

    /**
     * 编辑器类型-editormd
     */
    public static final String EDITOR_TYPE_EDITORMD="editormd";

    /**
     * 编辑器类型-ueditor
     */
    public static final String EDITOR_TYPE_UEDITOR="ueditor";

    /**
     * 后台首页页面
     */
    public static final String KEY_ADMIN_INDEX="admin.index.type";

    /**
     * 后台首页页面-index
     */
    public static final String ADMIN_INDEX_INDEX="index";
    /**
     * 后台首页页面-index_topMenu
     */
    public static final String ADMIN_INDEX_TOP_MENU="index_topMenu";

    /**
     * 博客标签
     */
    public static final String KEY_TAGS_BLOG="blogTab";

    /**
     * 系统标签
     */
    public static final String KEY_TAGS_SYSTEM="s";

    /**
     * 个人标签
     */
    public static final String KEY_TAGS_PERSON="p";

    /**
     * 文章模板标签
     */
    public static final String KEY_TAGS_ARTICLE_TEMPLATE="articleTemplate";

    public static final String KEY_PLATEFORM_REGIST="plateform-regist";

    public static final String KEY_PLATEFORM_RESET="plateform-reset";
    public static final String KEY_USER_RESET_PWD="user-reset-pwd";//用户密码重置。//cms_tempate表中的template_code字段
    public static final String KEY_USER_RESET_PWD_SMS="user-reset-pwd-sms";//用户密码重置(手机短信)

    public static final String KEY_USER_BIND_PHONE="user-bind-phone";//用户绑定手机发送验证码短信模板

    public static final String KEY_USER_MODIFY_PHONE="user-modify-phone";//用户修改手机发送验证码短信模板

    public static final String KEY_USER_BIND_EMAIL="user-bind-email";//用户绑定邮箱发送验证码短信模板

    public static final String KEY_USER_MODIFY_EMAIL="user-modify-email";//用户修改邮箱发送验证码短信模板

    public static final String KEY_USER_UNBIND_PHONE="user-unbind-phone";//用户解绑手机号发送验证码短信模板

    public static final String CODE_SESSION_KEY="CODE_SESSION_KEY";

    public static final String RESET_CODE_SESSION_KEY="RESET_CODE_SESSION_KEY";

    public static final String PHONE_CODE_SESSION_KEY="PHONE_CODE_SESSION_KEY";

    public static final String KEY_PLATFORM_PHONE_CODE="platform-phone-code";//cms_tempate表中的template_code字段

    public static final String KEY_USER_SMS_LOGIN="user-sms-login";//cms_tempate表中的template_code字段

    public static final String KEY_USER_REGISTER="user-regist";//前台用户注册短息验证码模板
}
