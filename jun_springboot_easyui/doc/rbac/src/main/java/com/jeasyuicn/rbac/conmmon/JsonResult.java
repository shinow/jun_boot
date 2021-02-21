package com.jeasyuicn.rbac.conmmon;

import lombok.Data;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.jeasyuicn.rbac.conmmon</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年11月14日</li>
 * <li>@author      : ____′↘夏悸 <wmails@126.com></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public final class JsonResult {
    private boolean success = true;
    private String msg;

    public JsonResult() {

    }

    public JsonResult(String msg) {
        this.msg = msg;
    }

    public static JsonResult success() {
        return new JsonResult();
    }

    public static JsonResult success(String msg) {
        return new JsonResult(msg);
    }

    public static JsonResult error() {
        JsonResult jr = new JsonResult();
        jr.setSuccess(false);
        return jr;
    }

    public static JsonResult error(String msg) {
        JsonResult jr = new JsonResult(msg);
        jr.setSuccess(false);
        return jr;
    }
}
