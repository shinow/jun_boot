package org.springrain.system.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;
import org.springrain.rpc.sessionuser.SessionUser;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserOrg;
import org.springrain.system.service.IOrgService;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service("orgService")
public class OrgServiceImpl extends BaseSpringrainServiceImpl implements IOrgService {


    @Override
    public Org findOrgById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findOrgById_" + id;
        Org org = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, Org.class);
        if (org != null) {
            return org;
        }
        org = super.findById(id, Org.class);
        if (org == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, org);
        return org;
    }


    @Override
    public String saveOrg(Org entity) throws Exception {

        //String id= SecUtils.getUUID();
        String id = null;
        if (StringUtils.isNotBlank(entity.getId())) {
            id = entity.getId();
        } else {
            id = SecUtils.getTimeNO();
            //id = tableindexService.updateNewId(Org.class);
        }
        entity.setId(id);

        String comcode = findOrgNewComcode(id, entity.getPid());

        entity.setComcode(comcode);

        entity.setActive(1);

        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setUpdateUserId(SessionUser.getUserId());
        entity.setCreateUserId(SessionUser.getUserId());
        entity.setOrgType(100);
        if (entity.getPid() == null) {
            entity.setPid("");
        }

        if (entity.getSortno() == null) {
            entity.setSortno(100);
        }


        super.save(entity);

        // updateOrgManager(id,entity.getManagerRoleId());

        return id;
    }

    @Override
    public Integer updateOrg(Org entity) throws Exception {
        String pid = entity.getPid();
        entity.setComcode(null);

        String id = entity.getId();
        if (StringUtils.isEmpty(id)) {
            return null;
        }


        Org old_org = findOrgById(id);
        if (old_org == null) {
            return null;
        }

        String old_c = old_org.getComcode();

        String new_c = findOrgNewComcode(id, pid);

        if (new_c.equalsIgnoreCase(old_c)) {// 编码没有改变
            return super.update(entity, true);
        }
        // 编码改变
        entity.setComcode(new_c);
        Integer update = super.update(entity, true);
        // 级联更新
        Finder f_s_list = Finder.getSelectFinder(Org.class, "id,comcode")
                .append(" WHERE comcode like :comcode and id<>:id ").setParam("comcode", old_c + "%")
                .setParam("id", id);
        List<Org> list = super.queryForList(f_s_list, Org.class);
        if (CollectionUtils.isEmpty(list)) {
            return update;
        }

        for (Org org : list) {
            String _id = org.getId();
            String _c = findOrgNewComcode(_id, id);
            org.setComcode(_c);
            org.setPid(id);
            //清理缓存
            super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findOrgById_" + _id);
        }

        super.update(list, true);

        return update;
    }

    /**
     * 更新部门的主管Id
     *
     * @param orgId
     * @param managerId
     * @return
     * @throws Exception
     */

    @SuppressWarnings("unused")
    private void updateOrgManager(String orgId, String managerId) throws Exception {
        /*
         * 此方法不在用。 部门没有主管，只要主管角色相关联
         */
        if (StringUtils.isBlank(orgId) || StringUtils.isBlank(managerId)) {
            return;
        }

        Finder finder = Finder.getDeleteFinder(UserOrg.class).append(" WHERE orgId=:orgId and manager=1 ");
        finder.setParam("orgId", orgId);
        super.update(finder);
        UserOrg userOrg = new UserOrg();
        userOrg.setOrgId(orgId);
        userOrg.setUserId(managerId);

        super.save(userOrg);
    }

    @Override
    public Org findOrgById(Object id) throws Exception {

        Org org = super.findById(id, Org.class);
        if (org == null) {
            return null;
        }

        Finder f = new Finder("SELECT u.id id,u.name name FROM  ").append(Finder.getTableName(User.class)).append(" u,")
                .append(Finder.getTableName(UserOrg.class)).append(" re ");
        f.append(" WHERE re.userId=u.id and re.managerType=11 and re.orgId=:orgId order by u.id asc ");
        f.setParam("orgId", org.getId());

        Page page = new Page(1);
        page.setPageSize(1);
        page.setSelectpagecount(false);

        List<User> list = super.queryForList(f, User.class, page);

        if (CollectionUtils.isEmpty(list)) {
            return org;
        }

        // User u=list.get(0);

        return org;
    }


    @Override
    public List<Org> findTreeOrg() throws Exception {

        return findTreeByPid("");
    }


    @Override
    public String deleteOrgById(String orgId) throws Exception {
        if (StringUtils.isEmpty(orgId)) {
            return null;
        }
        Org org = findOrgById(orgId);
        if (org == null) {
            return null;
        }

        Finder f_update = Finder.getUpdateFinder(Org.class, " active=:active ").append(" WHERE comcode like :comcode ")
                .setParam("active", 0).setParam("comcode", org.getComcode() + "%");
        super.update(f_update);
        return null;
    }

    @Override
    public List<Org> findTreeByPid(String pid) throws Exception {
        Finder finder = Finder.getSelectFinder(Org.class).append(" WHERE active=:active ").setParam("active", 1);

        if (StringUtils.isNotBlank(pid)) {//不是根目录
            Org porg = findOrgById(pid);
            if (porg == null) {
                return null;
            }
            finder.append(" and comcode like :comcode  ").setParam("comcode", porg.getComcode() + "%");
            //  .setParam("pid", pid);
        }

        finder.append(" order by sortno asc ");

        List<Org> list = super.queryForList(finder, Org.class);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return listOrg2Tree(list);
    }


    /**
     * 把 部门列表 转换成树形结构
     *
     * @param orgList
     * @return
     */
    private List<Org> listOrg2Tree(List<Org> orgList) {
        if (CollectionUtils.isEmpty(orgList)) {
            return null;
        }
        // 先把数据放到map里,方便取值,对象和orgList里的是同一个
        Map<String, Org> map = new HashMap<>();
        for (Org org : orgList) {
            map.put(org.getId(), org);
        }

        // 循环遍历orgList
        List<Org> list = new ArrayList<>();
        for (Org org : orgList) {
            String pid = org.getPid();
            Org parent = map.get(pid);
            // 没有父节点
            if (parent == null) {
                list.add(org);
                continue;
            }

            //如果有父节点
            List<Org> children = parent.getChildren();
            if (children == null) {
                children = new ArrayList<>();
                parent.setChildren(children);
            }
            children.add(org);
        }

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list;

    }

    @Override
    public String findOrgNewComcode(String id, String pid) throws Exception {

        if (StringUtils.isEmpty(id)) {
            return null;
        }

        String comcode = null;
        Finder f_p_c = Finder.getSelectFinder(Org.class, "comcode").append(" WHERE id=:pid ").setParam("pid", pid);
        String p_c = super.queryForObject(f_p_c, String.class);
        // 如果没有上级部门
        if (StringUtils.isEmpty(p_c)) {
            p_c = ",";
        }

        comcode = p_c + id + ",";

        return comcode;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> findOrgTreeVoList() throws Exception {
        Finder finder = Finder.getSelectFinder(Org.class).append(" WHERE active=:active");
        finder.setParam("active", 1);
        List<Org> orgList = this.queryForList(finder, Org.class);
        if (CollectionUtils.isNotEmpty(orgList)) {
            Map<String, Object> idOrgMap = new HashMap<String, Object>();
            Map<String, Object> orgMap = new HashMap<String, Object>();
            // 按层级排序  让循环从父到子循环
            orgList = orgList.stream().sorted(Comparator.comparingInt(t -> {
                return StringUtils.split(t.getComcode(), ";").length;
            })).collect(Collectors.toList());
            for (Org org : orgList) {
                String pid = org.getPid();

                Map<String, Object> orgInfo = new HashMap<String, Object>();
                orgInfo.put("id", org.getId());
                orgInfo.put("label", org.getName());
                idOrgMap.put(org.getId(), orgInfo);

                if (StringUtils.isBlank(org.getPid())) {
                    // 顶级部门
                    orgMap.put(org.getId(), orgInfo);
                } else {
                    // 子级部门
                    Map<String, Object> parentOrg = (Map<String, Object>) idOrgMap.get(pid);
                    List<Map<String, Object>> children = null;
                    if (parentOrg.containsKey("children")) {
                        children = (List<Map<String, Object>>) parentOrg.get("children");
                    } else {
                        children = new ArrayList<Map<String, Object>>();
                    }
                    children.add(orgInfo);
                    parentOrg.put("children", children);
                }
            }
            List<Map<String, Object>> orgInfoList = new ArrayList<Map<String, Object>>();
            for (String orgIdKey : orgMap.keySet()) {
                orgInfoList.add((Map<String, Object>) orgMap.get(orgIdKey));
            }
            return orgInfoList;
        }
        return null;
    }
}
