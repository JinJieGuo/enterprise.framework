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
 *		gl				 2020-03-20		        1.00					新建
 *******************************************************************************/

package enterprise.framework.service.config.tree;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.domain.config.SysConfigTree;
import enterprise.framework.mapper.config.tree.SysConfigTreeMapper;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.pojo.config.tree.SysConfigTreeVO;
import enterprise.framework.pojo.config.tree.TreeSelectVO;
import enterprise.framework.pojo.config.tree.TreeTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysConfigTreeServiceImpl implements SysConfigTreeService {


    @Autowired(required = false)
    private SysConfigTreeMapper sysConfigTreeMapper;

    /**
     * 保存字典树
     *
     * @param sysConfigTreeVO
     * @return
     */
    public HttpResponse saveTree(SysConfigTreeVO sysConfigTreeVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysConfigTree sysConfigTree = new SysConfigTree(sysConfigTreeVO);
            int response = sysConfigTreeMapper.saveTree(sysConfigTree);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "保存成功";
                sysConfigTreeVO.setTreeId(sysConfigTree.getTreeId());
                httpResponse.content = sysConfigTreeVO;
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
     * 更新字典树
     *
     * @param sysConfigTreeVO
     * @return
     */
    public HttpResponse updateTree(SysConfigTreeVO sysConfigTreeVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysConfigTree sysConfigTree = new SysConfigTree(sysConfigTreeVO);
            int response = sysConfigTreeMapper.updateTree(sysConfigTree);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "更新成功";
                sysConfigTreeVO.setTreeId(sysConfigTree.getTreeId());
                httpResponse.content = sysConfigTreeVO;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "更新失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "更新异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 删除字典树
     *
     * @param sysConfigTreeVO
     * @return
     */
    public HttpResponse deleteTree(SysConfigTreeVO sysConfigTreeVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            sysConfigTreeVO.setIsDeleted(1);
            SysConfigTree sysConfigTree = new SysConfigTree(sysConfigTreeVO);
            int response = sysConfigTreeMapper.updateTree(sysConfigTree);
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
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "删除异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 查询字典树
     *
     * @return
     */
    public HttpResponse listAllTree() {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<SysConfigTreeVO> response = sysConfigTreeMapper.listAllTree();
            List<TreeTableVO> treeTableVOList = new ArrayList<>();
            List<TreeSelectVO> treeSelectVOList = new ArrayList<>();

            if (response.size() > 0) {
                for (SysConfigTreeVO sysConfigTreeVO : response) {
                    if (sysConfigTreeVO.getParentId() == null || sysConfigTreeVO.getParentId() == 0) {
                        TreeTableVO treeTableVO = new TreeTableVO();
                        treeTableVO.setId(sysConfigTreeVO.getTreeId());
                        treeTableVO.setTreeName(sysConfigTreeVO.getTreeName());
                        treeTableVO.setTreeCode(sysConfigTreeVO.getTreeCode());
//                        treeTableVO.setIs_parent(sysAuthMenuVO.getIs_parent());
                        treeTableVO.setLevel(1);
                        treeTableVO.setParentId(sysConfigTreeVO.getParentId() == null ? 0 : sysConfigTreeVO.getParentId());
                        treeTableVO.setParentName(sysConfigTreeVO.getParentName());
                        treeTableVO.setIcon(sysConfigTreeVO.getIcon());
                        treeTableVO.setExpand(true);
                        treeTableVOList.add(treeTableVO);

                        TreeSelectVO treeSelectVO = new TreeSelectVO();
                        treeSelectVO.setTitle(sysConfigTreeVO.getTreeName());
                        treeSelectVO.setKey(String.valueOf(sysConfigTreeVO.getTreeId()));
                        treeSelectVO.setValue(sysConfigTreeVO.getTreeId());
                        treeSelectVO.setIcon(sysConfigTreeVO.getIcon());
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
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = map;
            }else {
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
    private void recursiveTreeTable(TreeTableVO rootNode, List<SysConfigTreeVO> dataSource) {
        List<TreeTableVO> childrenList = new ArrayList<>();
        for (SysConfigTreeVO sysConfigTreeVO : dataSource) {
            if (sysConfigTreeVO.getParentId() != null && sysConfigTreeVO.getParentId() != 0 && sysConfigTreeVO.getParentId() == rootNode.getId()) {
                TreeTableVO treeTableVO = new TreeTableVO();
                treeTableVO.setId(sysConfigTreeVO.getTreeId());
                treeTableVO.setTreeName(sysConfigTreeVO.getTreeName());
                treeTableVO.setTreeCode(sysConfigTreeVO.getTreeCode());
                treeTableVO.setLevel(rootNode.getLevel() + 1);
//                treeTableVO.setIs_parent(sysAuthMenuVO.getIs_parent());
                treeTableVO.setParentId(sysConfigTreeVO.getParentId());
                treeTableVO.setParentName(sysConfigTreeVO.getParentName());
                treeTableVO.setIcon(sysConfigTreeVO.getIcon());
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
    private void recursiveTreeSelect(TreeSelectVO rootNode, List<SysConfigTreeVO> dataSource) {
        List<TreeSelectVO> treeSelectVOList = new ArrayList<>();
        for (SysConfigTreeVO sysConfigTreeVO : dataSource) {
            String parentId = String.valueOf(sysConfigTreeVO.getParentId());
            if (sysConfigTreeVO.getParentId() != null && sysConfigTreeVO.getParentId() != 0 && String.valueOf(sysConfigTreeVO.getParentId()).equals(rootNode.getKey())) {
                TreeSelectVO treeSelectVO = new TreeSelectVO();
                treeSelectVO.setTitle(sysConfigTreeVO.getTreeName());
                treeSelectVO.setKey(String.valueOf(sysConfigTreeVO.getTreeId()));
                treeSelectVO.setValue(sysConfigTreeVO.getTreeId());
                treeSelectVO.setIcon(sysConfigTreeVO.getIcon());
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
