package com.jeasyuicn.rbac.conmmon;

import lombok.Data;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.jeasyuicn.rbac.conmmon</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年12月25日</li>
 * <li>@author     : ____′↘夏悸</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Data
public class Menu {

    private Long id;

    private String text;

    private String state = "open";

    private Long parentId;

    private String href;

}
