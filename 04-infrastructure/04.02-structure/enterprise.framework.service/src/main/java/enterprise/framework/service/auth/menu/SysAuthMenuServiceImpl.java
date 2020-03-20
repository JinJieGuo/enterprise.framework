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
 *		gl				 2020-01-05		        1.00					新建
 *******************************************************************************/

package enterprise.framework.service.auth.menu;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.domain.auth.SysAuthMenu;
import enterprise.framework.domain.auth.SysAuthMenuButton;
import enterprise.framework.mapper.auth.button.SysAuthButtonMapper;
import enterprise.framework.mapper.auth.menu.SysAuthMenuButtonMapper;
import enterprise.framework.mapper.auth.menu.SysAuthMenuMapper;
import enterprise.framework.pojo.auth.menu.ChoosedButtonDTO;
import enterprise.framework.pojo.auth.menu.ChoosedButtonVO;
import enterprise.framework.pojo.auth.menu.SysAuthMenuButtonVO;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.pojo.config.tree.TreeSelectVO;
import enterprise.framework.pojo.config.tree.TreeTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysAuthMenuServiceImpl implements SysAuthMenuService {

    @Autowired(required = false)
    private SysAuthMenuMapper sysAuthMenuMapper;

    @Autowired(required = false)
    private SysAuthButtonMapper sysAuthButtonMapper;

    @Autowired(required = false)
    private SysAuthMenuButtonMapper sysAuthMenuButtonMapper;

    /**
     * 保存菜单
     *
     * @param sysAuthMenuVO
     * @return
     */
    public HttpResponse saveMenu(SysAuthMenuVO sysAuthMenuVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthMenu sysAuthMenu = new SysAuthMenu(sysAuthMenuVO);
            int response = sysAuthMenuMapper.saveMenu(sysAuthMenu);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "保存成功";
                sysAuthMenuVO.setMenuId(sysAuthMenu.getMenuId());
                httpResponse.content = sysAuthMenuVO;
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
     * 保存菜单下的按钮
     *
     * @param choosedButtonDTO
     * @return
     */
    public HttpResponse saveMenuButton(ChoosedButtonDTO choosedButtonDTO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthMenuButton sysAuthMenuButton = new SysAuthMenuButton();
            sysAuthMenuButton.setMenuId(choosedButtonDTO.getMenuId());
            if (choosedButtonDTO.getSysAuthMenuButtonVOList().size() == 0) {
                int deleteMenuButton = sysAuthMenuButtonMapper.deleteMenuButton(sysAuthMenuButton.getMenuId());
            }

            // 菜单下的按钮个数
            int count = sysAuthMenuButtonMapper.selectCount(sysAuthMenuButton);
            int response = 0;
            if (count > 0) { // 菜单下存在按钮时,先移除所有再新增,如果移除失败,则不添加
                int deleteMenuButton = sysAuthMenuButtonMapper.deleteMenuButton(sysAuthMenuButton.getMenuId());
                if (deleteMenuButton > 0) {
                    response = sysAuthMenuButtonMapper.saveMenuButtonList(choosedButtonDTO.getSysAuthMenuButtonVOList());
                }
            } else {
                response = sysAuthMenuButtonMapper.saveMenuButtonList(choosedButtonDTO.getSysAuthMenuButtonVOList());
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
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 更新菜单
     *
     * @param sysAuthMenuVO
     * @return
     */
    public HttpResponse updateMenu(SysAuthMenuVO sysAuthMenuVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthMenu sysAuthMenu = new SysAuthMenu(sysAuthMenuVO);
            int response = sysAuthMenuMapper.updateMenu(sysAuthMenu);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "更新成功";
                httpResponse.content = sysAuthMenu;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "更新失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 删除角色
     *
     * @param sysAuthMenuVO
     * @return
     */
    public HttpResponse deleteMenu(SysAuthMenuVO sysAuthMenuVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            sysAuthMenuVO.setIsDeleted(1);
            SysAuthMenu sysAuthMenu = new SysAuthMenu(sysAuthMenuVO);
            int response = sysAuthMenuMapper.updateMenu(sysAuthMenu);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "删除成功";
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "删除失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 根据角色主键获取角色信息
     *
     * @param sysAuthMenuVO
     * @return
     */
    public HttpResponse getMenuById(SysAuthMenuVO sysAuthMenuVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthMenu param = new SysAuthMenu(sysAuthMenuVO);
            SysAuthMenu dataSource = sysAuthMenuMapper.selectOne(param);
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
    public HttpResponse listAllMenu() {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<SysAuthMenuVO> response = sysAuthMenuMapper.listAllMenu();
            List<TreeTableVO> treeTableVOList = new ArrayList<>();
            List<TreeSelectVO> treeSelectVOList = new ArrayList<>();
            if (response.size() > 0) {
                for (SysAuthMenuVO sysAuthMenuVO : response) {
                    if (sysAuthMenuVO.getParentId() == null || sysAuthMenuVO.getParentId() == 0) {
                        TreeTableVO treeTableVO = new TreeTableVO();
                        treeTableVO.setId(sysAuthMenuVO.getMenuId());
                        treeTableVO.setTreeName(sysAuthMenuVO.getMenuName());
                        treeTableVO.setTreeCode(sysAuthMenuVO.getMenuCode());
                        treeTableVO.setIsMenu(sysAuthMenuVO.getIsMenu());
//                        treeTableVO.setIs_parent(sysAuthMenuVO.getIs_parent());
                        treeTableVO.setLevel(1);
                        treeTableVO.setParentId(sysAuthMenuVO.getParentId() == null ? 0 : sysAuthMenuVO.getParentId());
                        treeTableVO.setParentName(sysAuthMenuVO.getParentName());
                        treeTableVO.setIsMenu(sysAuthMenuVO.getIsMenu());
                        treeTableVO.setIcon(sysAuthMenuVO.getIcon());
                        treeTableVO.setExpand(true);
                        treeTableVOList.add(treeTableVO);

                        TreeSelectVO treeSelectVO = new TreeSelectVO();
                        treeSelectVO.setTitle(sysAuthMenuVO.getMenuName());
                        treeSelectVO.setKey(String.valueOf(sysAuthMenuVO.getMenuId()));
                        treeSelectVO.setValue(sysAuthMenuVO.getMenuId());
                        treeSelectVO.setIcon(sysAuthMenuVO.getIcon());
                        treeSelectVO.setLeaf(true);
                        treeSelectVOList.add(treeSelectVO);
                    }
                }

                for (TreeTableVO treeTableVO : treeTableVOList) {
                    recursiveTreeTable(treeTableVO, response);
                }

                for (TreeSelectVO treeSelectVO : treeSelectVOList) {
                    recursiveTreeSelect(treeSelectVO, response);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("treeTableVOList", treeTableVOList);
                map.put("treeSelectVOList", treeSelectVOList);
                map.put("buttonList", sysAuthButtonMapper.listAllButtonInfo());
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = map;
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
     * 获取菜单树
     *
     * @return
     */
    public HttpResponse listMenuTree() {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<SysAuthMenuVO> response = sysAuthMenuMapper.listAllMenu();
            List<TreeTableVO> treeTableVOList = new ArrayList<>();
            List<TreeSelectVO> treeSelectVOList = new ArrayList<>();
            if (response.size() > 0) {
                for (SysAuthMenuVO sysAuthMenuVO : response) {
                    if (sysAuthMenuVO.getParentId() == null || sysAuthMenuVO.getParentId() == 0) {
                        TreeSelectVO treeSelectVO = new TreeSelectVO();
                        treeSelectVO.setTitle(sysAuthMenuVO.getMenuName());
                        treeSelectVO.setKey(String.valueOf(sysAuthMenuVO.getMenuId()));
                        treeSelectVO.setValue(sysAuthMenuVO.getMenuId());
                        treeSelectVO.setIcon(sysAuthMenuVO.getIcon());
                        treeSelectVO.setLeaf(true);
                        treeSelectVOList.add(treeSelectVO);
                    }
                }

                for (TreeSelectVO treeSelectVO : treeSelectVOList) {
                    recursiveTreeSelect(treeSelectVO, response);
                }

                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = treeSelectVOList;
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
     * 根据菜单id获取按钮及所有按钮
     *
     * @param menuId
     * @return
     */
    public HttpResponse listMenuButton(int menuId) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<ChoosedButtonVO> dataSource = sysAuthMenuButtonMapper.listMenuButton(menuId);
            if (dataSource.size() > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = dataSource;
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
     * 递归建立菜单父子关系 - 用于table
     *
     * @param rootNode
     * @param dataSource
     */
    private void recursiveTreeTable(TreeTableVO rootNode, List<SysAuthMenuVO> dataSource) {
        List<TreeTableVO> childrenList = new ArrayList<>();
        for (SysAuthMenuVO sysAuthMenuVO : dataSource) {
            if (sysAuthMenuVO.getParentId() != null && sysAuthMenuVO.getParentId() != 0 && sysAuthMenuVO.getParentId() == rootNode.getId()) {
                TreeTableVO treeTableVO = new TreeTableVO();
                treeTableVO.setId(sysAuthMenuVO.getMenuId());
                treeTableVO.setTreeName(sysAuthMenuVO.getMenuName());
                treeTableVO.setTreeCode(sysAuthMenuVO.getMenuCode());
                treeTableVO.setLevel(rootNode.getLevel() + 1);
                treeTableVO.setIsMenu(sysAuthMenuVO.getIsMenu());
//                treeTableVO.setIs_parent(sysAuthMenuVO.getIs_parent());
                treeTableVO.setParentId(sysAuthMenuVO.getParentId());
                treeTableVO.setParentName(sysAuthMenuVO.getParentName());
                treeTableVO.setIcon(sysAuthMenuVO.getIcon());
                treeTableVO.setIsMenu(sysAuthMenuVO.getIsMenu());
                treeTableVO.setExpand(true);
                childrenList.add(treeTableVO);
            }
        }
        rootNode.setChildren(childrenList);
        if (rootNode.getChildren().size() > 0) {
            for (TreeTableVO childrenCategory : rootNode.getChildren()) {
                recursiveTreeTable(childrenCategory, dataSource);
            }
        }
    }

    /**
     * 递归建立菜单父子关系 - 用于下拉树
     *
     * @param rootNode
     * @param dataSource
     */
    private void recursiveTreeSelect(TreeSelectVO rootNode, List<SysAuthMenuVO> dataSource) {
        List<TreeSelectVO> treeSelectVOList = new ArrayList<>();
        for (SysAuthMenuVO sysAuthMenuVO : dataSource) {
            String parentId = String.valueOf(sysAuthMenuVO.getParentId());
            if (sysAuthMenuVO.getParentId() != null && sysAuthMenuVO.getParentId() != 0 && String.valueOf(sysAuthMenuVO.getParentId()).equals(rootNode.getKey())) {
                TreeSelectVO treeSelectVO = new TreeSelectVO();
                treeSelectVO.setTitle(sysAuthMenuVO.getMenuName());
                treeSelectVO.setKey(String.valueOf(sysAuthMenuVO.getMenuId()));
                treeSelectVO.setValue(sysAuthMenuVO.getMenuId());
                treeSelectVO.setIcon(sysAuthMenuVO.getIcon());
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
