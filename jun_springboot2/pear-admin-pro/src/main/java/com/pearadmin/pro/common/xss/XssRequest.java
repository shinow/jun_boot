package com.pearadmin.pro.common.xss;

import com.pearadmin.pro.common.tools.core.lang.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssRequest extends HttpServletRequestWrapper {

    HttpServletRequest orgRequest = null;
    private boolean isIncludeRichText = false;

    public XssRequest(HttpServletRequest request, boolean isIncludeRichText) {
        super(request);
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    @Override
    public String getParameter(String name) {
        if(("content".equals(name) || name.endsWith("WithHtml")) && !isIncludeRichText){
            return super.getParameter(name);
        }
        name = XssContent.clean(name);
        String value = super.getParameter(name);
        if (StringUtil.isNotBlank(value)) {
            value = XssContent.clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if(arr != null){
            for (int i=0;i<arr.length;i++) {
                arr[i] = XssContent.clean(arr[i]);
            }
        }
        return arr;
    }

    @Override
    public String getHeader(String name) {
        name = XssContent.clean(name);
        String value = super.getHeader(name);
        if (StringUtil.isNotBlank(value)) {
            value = XssContent.clean(value);
        }
        return value;
    }

    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssRequest) {
            return ((XssRequest) req).getOrgRequest();
        }
        return req;
    }
}
