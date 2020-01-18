/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:Enterprise.Framework.Core
 *		2.ClassName:HttpManager.cs
 *		3.FunctionDescription:核心组件 — 模拟请求处理器
 *		4.Call:
 *		5.CalledBy:
 *		6.TableAccessed:
 *		7.TableUpdated:
 *		8.Input:
 *		9.Output:
 *	    10.Return:
 *       11.Others:
 * EditResume:
 *	   Author				Date			  version			   ChangeContent 
 *		gl				 2020-01-04		        1.00					新建
 *******************************************************************************/

package enterprise.framework.service.auth.role;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.domain.auth.SysAuthRole;
import enterprise.framework.domain.auth.SysAuthRoleMenuButton;
import enterprise.framework.mapper.auth.role.SysAuthRoleMapper;
import enterprise.framework.mapper.auth.role.SysAuthRoleMenuButtonMapper;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.pojo.auth.role.*;
import enterprise.framework.pojo.config.tree.TreeSelectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysAuthRoleServiceImpl implements SysAuthRoleService {

    @Autowired(required = false)
    private SysAuthRoleMapper sysAuthRoleMapper;

    @Autowired(required = false)
    private SysAuthRoleMenuButtonMapper sysAuthRoleMenuButtonMapper;

    /**
     * 保存角色
     *
     * @param sysAuthRoleVO
     * @return
     */
    public HttpResponse saveRole(SysAuthRoleVO sysAuthRoleVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthRole sysAuthRole = new SysAuthRole(sysAuthRoleVO);
            int response = sysAuthRoleMapper.saveRole(sysAuthRole);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "保存成功";
                sysAuthRoleVO.setRoleId(sysAuthRole.getRoleId());
                httpResponse.content = sysAuthRoleVO;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "保存失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "保存异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 保存角色下勾选的菜单与按钮权限
     *
     * @param choosedRoleMenuButton
     * @return
     */
    public HttpResponse saveRoleMenuButton(ChoosedRoleMenuButton choosedRoleMenuButton) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthRoleMenuButton sysAuthRoleMenuButton = new SysAuthRoleMenuButton();
            sysAuthRoleMenuButton.setRoleId(choosedRoleMenuButton.getRoleId());
            sysAuthRoleMenuButton.setIsDeleted(0);
            int count = sysAuthRoleMenuButtonMapper.selectCount(sysAuthRoleMenuButton);
            int response = 0;
            if (count > 0) {
                int deleteResponse = sysAuthRoleMenuButtonMapper.deleteRoleMenuButton(choosedRoleMenuButton.getRoleId());
                if (deleteResponse > 0) {
                    if (choosedRoleMenuButton.getSysAuthRoleMenuButtonVOList().size() > 0) {
                        response = sysAuthRoleMenuButtonMapper.saveRoleMenuButtonList(choosedRoleMenuButton.getSysAuthRoleMenuButtonVOList());
                    } else {
                        response = 1;
                    }
                }
            } else {
                response = sysAuthRoleMenuButtonMapper.saveRoleMenuButtonList(choosedRoleMenuButton.getSysAuthRoleMenuButtonVOList());
            }
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "保存成功";
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "保存失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "保存异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 更新角色
     *
     * @param sysAuthRoleVO
     * @return
     */
    public HttpResponse updateRole(SysAuthRoleVO sysAuthRoleVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthRole sysAuthRole = new SysAuthRole(sysAuthRoleVO);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sysAuthRole.setModifyTime(formatter.format(new Date()));
            sysAuthRole.setModifyTime(new Date());
            int response = sysAuthRoleMapper.updateRole(sysAuthRole);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "更新成功";
                httpResponse.content = sysAuthRole;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "更新失败";
            }
            return httpResponse;
        } catch (Exception error) {
            return httpResponse;
        }
    }

    /**
     * 删除角色
     *
     * @param sysAuthRoleVO
     * @return
     */
    public HttpResponse deleteRole(SysAuthRoleVO sysAuthRoleVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            sysAuthRoleVO.setIsDeleted(1);
            SysAuthRole sysAuthRole = new SysAuthRole(sysAuthRoleVO);
            int response = sysAuthRoleMapper.updateRole(sysAuthRole);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "删除成功";
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "删除失败";
            }
            return httpResponse;
        } catch (Exception error) {
            return httpResponse;
        }
    }

    /**
     * 根据角色主键获取角色信息
     *
     * @param sysAuthRoleVO
     * @return
     */
    public HttpResponse getRoleById(SysAuthRoleVO sysAuthRoleVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthRole param = new SysAuthRole(sysAuthRoleVO);
            SysAuthRole dataSource = sysAuthRoleMapper.selectOne(param);
            if (dataSource != null) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = dataSource;
            } else {
                httpResponse.msg = "查询成功无数据";
                httpResponse.status = HttpStatus.SUCCESS.value();
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }


    /**
     * 获取所有角色
     *
     * @return
     */
    public HttpResponse listAllRole() {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<SysAuthRoleVO> response = sysAuthRoleMapper.listAllRole();
            if (response.size() > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = response;
            } else {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功,但无返回值";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 为角色分配用户 — 获取所有用户及该角色下已包含的用户
     *
     * @param roleId 角色主键
     * @return
     */
    public HttpResponse listRoleUser(long roleId) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<RoleUserDTO> response = sysAuthRoleMapper.listRoleUser(roleId);
            if (response.size() > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = response;
            } else {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功,但无返回值";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 根据角色获取所有菜单权限(包括已选和所有权限)
     *
     * @param roleId 角色主键
     * @return
     */
    public HttpResponse listRoleMenuAuth(long roleId, long menuId) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<RoleMenuDTO> roleMenuDTOList = sysAuthRoleMapper.listRoleMenu(roleId);
            List<RoleMenuButtonDTO> roleMenuButtonDTOList = sysAuthRoleMapper.listRoleMenuAuth(roleId, menuId);
            List<TreeSelectVO> choosedMenuList = new ArrayList<>();
            List<String> choosedMenuArray = new ArrayList<>();

            // list 转 map
            Map<Long, List<RoleMenuButtonDTO>> roleMenuButtonMap = roleMenuButtonDTOList.stream().collect(Collectors.groupingBy(RoleMenuButtonDTO::getMenuId));
            List<TreeSelectVO> treeSelectVOList = new ArrayList<>();
            int tempSort = 0;
            for (RoleMenuDTO roleMenu : roleMenuDTOList) {
                TreeSelectVO treeSelectVO = new TreeSelectVO();
                treeSelectVO.setTitle(roleMenu.getMenuName());
                treeSelectVO.setKey(String.valueOf(roleMenu.getMenuId()));
                treeSelectVO.setValue(roleMenu.getMenuId());
                treeSelectVO.setIcon(roleMenu.getIcon());
                treeSelectVO.setLeaf(true);
                if (roleMenu.getParentId() == 0) {
                    treeSelectVOList.add(treeSelectVO);
                }

                if (roleMenu.getIsMenu() && roleMenu.getMenuIsChecked()) {// 判断集合中是否含有已勾选的菜单,如果有用于树菜单默认值
                    if (!(choosedMenuArray.indexOf(String.valueOf(roleMenu.getMenuId())) > -1)) {
                        choosedMenuArray.add(String.valueOf(roleMenu.getMenuId()));
                    }
                }
            }

            for (TreeSelectVO treeSelectVO : treeSelectVOList) {
                recursiveTreeSelect(treeSelectVO, roleMenuDTOList);
            }
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("menuList", treeSelectVOList);
            responseMap.put("choosedMenuList", choosedMenuArray.toArray());
            responseMap.put("moleMenuButtonMap", roleMenuButtonMap);
            httpResponse.status = HttpStatus.SUCCESS.value();
            httpResponse.content = responseMap;
            httpResponse.msg = "查询成功";
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 递归建立菜单父子关系 - 用于下拉树
     *
     * @param rootNode
     * @param dataSource
     */
    private void recursiveTreeSelect(TreeSelectVO rootNode, List<RoleMenuDTO> dataSource) {
        List<TreeSelectVO> treeSelectVOList = new ArrayList<>();
        for (RoleMenuDTO roleMenuDTO : dataSource) {
            String parentId = String.valueOf(roleMenuDTO.getParentId());
            if (roleMenuDTO.getParentId() != 0 && String.valueOf(roleMenuDTO.getParentId()).equals(rootNode.getKey())) {
                TreeSelectVO treeSelectVO = new TreeSelectVO();
                treeSelectVO.setTitle(roleMenuDTO.getMenuName());
                treeSelectVO.setKey(String.valueOf(roleMenuDTO.getMenuId()));
                treeSelectVO.setValue(roleMenuDTO.getMenuId());
                treeSelectVO.setIcon(roleMenuDTO.getIcon());
                treeSelectVO.setLeaf(true);
                treeSelectVOList.add(treeSelectVO);
            }

        }
        rootNode.setChildren(treeSelectVOList);
        if (rootNode.getChildren().size() > 0) {
            for (TreeSelectVO childrenCategory : rootNode.getChildren()) {
                recursiveTreeSelect(childrenCategory, dataSource);
            }
        }
    }
}
