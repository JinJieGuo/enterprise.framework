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
 *		gl				 2019-12-21		        1.00					新建
 *******************************************************************************/

package enterprise.framework.service.auth.user;

import enterprise.framework.domain.auth.SysAuthUser;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.domain.auth.SysAuthUserRole;
import enterprise.framework.mapper.auth.user.SysAuthUserMapper;
import enterprise.framework.mapper.auth.user.SysAuthUserRoleMapper;
import enterprise.framework.pojo.auth.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Component
public class SysAuthUserServiceImpl implements SysAuthUserService {

    @Autowired(required = false)
    private SysAuthUserMapper sysAuthUserMapper;

    @Autowired(required = false)
    private SysAuthUserRoleMapper sysAuthUserRoleMapper;


    // region ""
    // endregion

    /**
     * 保存用户信息
     *
     * @param sysAuthUserVO 用户实体
     * @return
     */
    public HttpResponse saveUser(SysAuthUserVO sysAuthUserVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthUser sysAuthUser = new SysAuthUser(sysAuthUserVO);
            int response = sysAuthUserMapper.saveUser(sysAuthUser);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "保存成功";
                sysAuthUserVO.setUserId(sysAuthUser.getUserId());
                httpResponse.content = sysAuthUserVO;
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
     * 更新用户
     *
     * @param sysAuthUser
     * @return
     */
    public HttpResponse updateUser(SysAuthUser sysAuthUser) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            int response = sysAuthUserMapper.updateUser(sysAuthUser);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "更新成功";
                httpResponse.content = sysAuthUser;
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
     * 更新用户
     *
     * @param sysAuthUserVO
     * @return
     */
    public HttpResponse updateUser(SysAuthUserVO sysAuthUserVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthUser sysAuthUser = new SysAuthUser(sysAuthUserVO);
            int response = sysAuthUserMapper.updateUser(sysAuthUser);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "更新成功";
                httpResponse.content = sysAuthUser;
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
     * 删除用户
     *
     * @param sysAuthUserVO
     * @return
     */
    public HttpResponse deleteUser(SysAuthUserVO sysAuthUserVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            sysAuthUserVO.setIsDeleted(1);
            SysAuthUser sysAuthUser = new SysAuthUser(sysAuthUserVO);
            int response = sysAuthUserMapper.updateUser(sysAuthUser);
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
     * 保存用户角色
     *
     * @param choosedUserRoleDTO
     * @return
     */
    public HttpResponse saveUserRoleList(ChoosedUserRoleDTO choosedUserRoleDTO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthUserRole sysAuthUserRole = new SysAuthUserRole();
            sysAuthUserRole.setRoleId(choosedUserRoleDTO.getRoleId());
            int count = sysAuthUserRoleMapper.selectCount(sysAuthUserRole);
            int response = 0;
            if (count > 0) {
                int deleteResponse = sysAuthUserRoleMapper.deleteUserRole(choosedUserRoleDTO.getRoleId());
                if (deleteResponse > 0) {
                    if (choosedUserRoleDTO.getSysAuthUserRoleVOList().size() > 0) {
                        response = sysAuthUserRoleMapper.saveUserRoleList(choosedUserRoleDTO.getSysAuthUserRoleVOList());
                    } else {
                        response = 1;
                    }
                }
            } else {
                response = sysAuthUserRoleMapper.saveUserRoleList(choosedUserRoleDTO.getSysAuthUserRoleVOList());
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
     * 根据用户主键获取用户信息
     *
     * @param sysAuthUserVO
     * @return
     */
    public HttpResponse getUserById(SysAuthUserVO sysAuthUserVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthUser param = new SysAuthUser(sysAuthUserVO);
            SysAuthUser dataSource = sysAuthUserMapper.selectOne(param);
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
     * 获取所有用户
     *
     * @return
     */
    public HttpResponse listAllUser() {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<SysAuthUserVO> response = sysAuthUserMapper.listAllUser();
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
     * 根据条件获取用户集合
     *
     * @param loginName
     * @return
     */
    public HttpResponse listUserByParameters(String loginName) {
        HttpResponse httpResponse = new HttpResponse();
        try {
//            List<SysAuthUser> response = sysAuthUserMapper.select(sysAuthUser);
            List<SysAuthUserVO> response = sysAuthUserMapper.getUserByLoginName(loginName);

            if (response.size() > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "查询成功";
                httpResponse.content = response;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "查询失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    public HttpResponse listUserAuth(int userId) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            Map<String, Object> responseMap = new HashMap<>();
            List<UserAuthDTO> response = sysAuthUserMapper.listUserAuth(userId);
            List<UserAuthMenuDTO> userAuthMenuDTOList = new ArrayList<>();
            List<UserAuthMenuDTO> rootUserAuthMenuDTOList = new ArrayList<>();
            Map<Long, List<UserAuthMenuButtonDTO>> userAuthMenuButtonMap = new HashMap<>();
            if (response.size() > 0) {
                for (UserAuthDTO authDTO : response) {
                    UserAuthMenuDTO userAuthMenuDTO = new UserAuthMenuDTO();
                    userAuthMenuDTO.setMenuId(authDTO.getMenuId());
                    userAuthMenuDTO.setParentId(authDTO.getParentId());
                    userAuthMenuDTO.setI18n(authDTO.getI18n());
                    userAuthMenuDTO.setMenuSort(authDTO.getMenuSort());
                    userAuthMenuDTO.setText(authDTO.getText());
                    userAuthMenuDTO.setLink(authDTO.getLink());
                    userAuthMenuDTO.setIcon(authDTO.getIcon());
                    userAuthMenuDTO.setIsMenu(authDTO.getIsMenu());
                    userAuthMenuDTO.setGroup(authDTO.getGroup());
                    userAuthMenuDTO.setHideInBreadcrumb(authDTO.getHideInBreadcrumb());
                    userAuthMenuDTO.setHide(authDTO.getHide());
                    if (userAuthMenuDTOList.contains(userAuthMenuDTO)) {
                        continue;
                    }
                    userAuthMenuDTOList.add(userAuthMenuDTO);

                }

                for (UserAuthMenuDTO authMenuDto : userAuthMenuDTOList) {
                    List<UserAuthDTO> temp = response.stream().filter(t -> t.getMenuId() == authMenuDto.getMenuId() && t.getButtonId() != 0).collect(Collectors.toList());
                    List<UserAuthMenuButtonDTO> userAuthMenuButtonDTOList = new ArrayList<>();
                    for (UserAuthDTO item : temp) {
                        UserAuthMenuButtonDTO userAuthMenuButtonDTO = new UserAuthMenuButtonDTO();
                        userAuthMenuButtonDTO.setMenuId(item.getMenuId());
                        userAuthMenuButtonDTO.setButtonId(item.getButtonId());
                        userAuthMenuButtonDTO.setButtonName(item.getButtonName());
                        userAuthMenuButtonDTO.setButtonIcon(item.getButtonIcon());
                        userAuthMenuButtonDTO.setButtonSort(item.getButtonSort());
                        userAuthMenuButtonDTO.setButtonClass(item.getButtonClass());
                        userAuthMenuButtonDTO.setMethod(item.getMethod());
                        if (userAuthMenuButtonDTOList.contains(userAuthMenuButtonDTO)) {
                            continue;
                        }
                        userAuthMenuButtonDTOList.add(userAuthMenuButtonDTO);
                    }
                    userAuthMenuButtonMap.put(authMenuDto.getMenuId(), userAuthMenuButtonDTOList);
                    authMenuDto.setButtonDTOList(userAuthMenuButtonDTOList);
                }


                for (UserAuthMenuDTO authDTO : userAuthMenuDTOList) {
                    if (authDTO.getParentId() == 0) {
                        UserAuthMenuDTO userAuthDTO = new UserAuthMenuDTO();
                        userAuthDTO.setMenuId(authDTO.getMenuId());
                        userAuthDTO.setParentId(authDTO.getParentId());
                        userAuthDTO.setI18n(authDTO.getI18n());
                        userAuthDTO.setMenuSort(authDTO.getMenuSort());
                        userAuthDTO.setText(authDTO.getText());
                        userAuthDTO.setLink(authDTO.getLink());
                        userAuthDTO.setIcon(authDTO.getIcon());
                        userAuthDTO.setIsMenu(authDTO.getIsMenu());
                        userAuthDTO.setGroup(authDTO.getGroup());
                        userAuthDTO.setHideInBreadcrumb(authDTO.getHideInBreadcrumb());
                        userAuthDTO.setHide(authDTO.getHide());
                        userAuthDTO.setButtonDTOList(authDTO.getButtonDTOList());
                        rootUserAuthMenuDTOList.add(userAuthDTO);
                    }
                }

                for (UserAuthMenuDTO authDTO : rootUserAuthMenuDTOList) {
                    recursiveTreeAuth(authDTO, userAuthMenuDTOList);
                }
            }
            responseMap.put("userAuthMenuArray", rootUserAuthMenuDTOList);
            responseMap.put("userAuthMenuButtonMap", userAuthMenuButtonMap);
            httpResponse.status = HttpStatus.SUCCESS.value();
            httpResponse.msg = "查询成功";
            httpResponse.content = responseMap;
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "查询异常:" + error.getMessage();
            return httpResponse;
        }
    }

    private void recursiveTreeAuth(UserAuthMenuDTO rootNode, List<UserAuthMenuDTO> dataSource) {
        List<UserAuthMenuDTO> childrenList = new ArrayList<>();
        for (UserAuthMenuDTO authDTO : dataSource) {
            if (authDTO.getParentId() != 0 && authDTO.getParentId() == rootNode.getMenuId()) {
                UserAuthMenuDTO userAuthDTO = new UserAuthMenuDTO();
                userAuthDTO.setMenuId(authDTO.getMenuId());
                userAuthDTO.setParentId(authDTO.getParentId());
                userAuthDTO.setI18n(authDTO.getI18n());
                userAuthDTO.setMenuSort(authDTO.getMenuSort());
                userAuthDTO.setText(authDTO.getText());
                userAuthDTO.setLink(authDTO.getLink());
                userAuthDTO.setIcon(authDTO.getIcon());
                userAuthDTO.setIsMenu(authDTO.getIsMenu());
                userAuthDTO.setGroup(authDTO.getGroup());
                userAuthDTO.setHideInBreadcrumb(authDTO.getHideInBreadcrumb());
                userAuthDTO.setHide(authDTO.getHide());
                userAuthDTO.setButtonDTOList(authDTO.getButtonDTOList());
                childrenList.add(userAuthDTO);
            }
        }
        rootNode.setChildren(childrenList);
        if (rootNode.getChildren().size() > 0) {
            for (UserAuthMenuDTO childrenUserAuth : rootNode.getChildren()) {
                recursiveTreeAuth(childrenUserAuth, dataSource);
            }
        }
    }
}
