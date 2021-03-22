package com.mos.eboot.admin.platform.controller;

import com.mos.eboot.admin.platform.api.IMenuService;
import com.mos.eboot.platform.entity.SysMenu;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.shiro.utils.PrincipalUtils;
import com.mos.eboot.vo.MenuNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小尘哥
 */
@Controller
public class IndexController {

    @Resource
    private IMenuService menuService;

    @GetMapping("index")
    public String index(Model model) {
		SysUser user = (SysUser) PrincipalUtils.getCurrentUser();
        List<MenuNode> menus;
        if (SysUser.SUPER_ID.equals(user.getId())){
			menus = menuService.queryMenus().getData();
		}else{
			menus = menuService.queryByUser(user.getId()).getData();
		}
        List<MenuNode> firstNode = menus.stream().filter(menu -> SysMenu.ROOT_ID.equals(menu.getpId())).collect(Collectors.toList());
        List<MenuNode> otherNode = menus.stream().filter(menu -> !SysMenu.ROOT_ID.equals(menu.getpId())).collect(Collectors.toList());
        firstNode.forEach(item -> {
            List<MenuNode> nodes = item.getChildren();
            for (MenuNode node : otherNode) {
                if (node.getpId().equals(item.getId())) {
                    nodes.add(node);
                }
            }
        });
        model.addAttribute("menus", firstNode);
        return "index";
    }


    @GetMapping("welcome")
    public String welcome() {
        return "welcome";
    }

}
