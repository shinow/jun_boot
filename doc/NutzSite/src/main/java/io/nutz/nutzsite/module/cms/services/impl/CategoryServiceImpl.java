package io.nutz.nutzsite.module.cms.services.impl;

import io.nutz.nutzsite.common.service.BaseServiceImpl;
import io.nutz.nutzsite.module.cms.models.Category;
import io.nutz.nutzsite.module.cms.services.CategoryService;
import org.nutz.boot.starter.caffeine.Cache;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栏目 服务层实现
 * 
 * @author haiming
 * @date 2019-05-06
 */
@IocBean(args = {"refer:dao"})
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	public CategoryServiceImpl(Dao dao) {
		super(dao);
	}

	@Override
    @Cache
	public List<Category> getCateById(String id){
		return this.query(Cnd.where("parent_id", "=", id).asc("sort"));
	}

	/**
     * 对象转 栏目 树
     *
     * @param list 栏目列表
     * @return
     */
	@Override
    public List<Map<String, Object>> getTrees(List<Category> list) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (Category data : list) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("id", data.getId());
			dataMap.put("pId", data.getParentId());
			dataMap.put("name", data.getName());
			dataMap.put("title", data.getName());
			dataMap.put("checked", false);
			trees.add(dataMap);
		}
		return trees;
	}

	/**
	 * 查询数据树
	 * @param parentId
	 * @param name
	 * @return
	 */
	@Override
    public List<Map<String, Object>> selectTree(String parentId, String name) {
		Cnd cnd = Cnd.NEW();
		if (Strings.isNotBlank(name)) {
			//cnd.and("name", "like", "%" + name + "%");
		}
		if (Strings.isNotBlank(parentId)) {
			cnd.and("parent_id", "=", parentId);
		}
		//cnd.and("status", "=", false).and("del_flag", "=", false);
		cnd.asc("sort");
		List<Category> list = this.query(cnd);
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		trees = getTrees(list);
		return trees;
	}

	@Override
	public Category insert(Category category) {
		if(Lang.isNotEmpty(category) && Strings.isNotBlank(category.getParentId())){
			Category parent = this.fetch(category.getParentId());
			if(Lang.isNotEmpty(parent)){
				category.setParentIds(parent.getParentIds() + "," + category.getParentId());
			}
		}
		return super.insert(category);
	}

	@Override
    public int update(Category category) {
		if(Lang.isNotEmpty(category) && Strings.isNotBlank(category.getParentId())){
			Category parent = this.fetch(category.getParentId());
			if(Lang.isNotEmpty(parent)){
				category.setParentIds(parent.getParentIds() + "," + category.getParentId());
			}
		}
		return super.update(category);
	}
}
