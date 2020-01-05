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
import enterprise.framework.mapper.auth.role.SysAuthRoleMapper;
import enterprise.framework.pojo.auth.role.SysAuthRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysAuthRoleServiceImpl implements SysAuthRoleService {

    @Autowired(required = false)
    private SysAuthRoleMapper sysAuthRoleMapper;

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
}
