package com.pearadmin.pro.common.xss;

import com.pearadmin.pro.common.constant.SystemConstant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class XssContent {

    private static final Whitelist whitelist = Whitelist.basicWithImages();

    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    static {
        whitelist.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, SystemConstant.EMPTY, whitelist, outputSettings);
    }
}
