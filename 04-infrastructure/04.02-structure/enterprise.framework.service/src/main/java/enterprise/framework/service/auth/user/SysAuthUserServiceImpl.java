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
import enterprise.framework.mapper.auth.user.SysAuthUserMapper;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class SysAuthUserServiceImpl implements SysAuthUserService {

    @Autowired(required = false)
    private SysAuthUserMapper sysAuthUserMapper;


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
                httpResponse.content = dataSource;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
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
     * 根据条件获取用户集合
     *
     * @param sysAuthUser
     * @return
     */
    public HttpResponse listUserByParameters(SysAuthUser sysAuthUser) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<SysAuthUser> response = sysAuthUserMapper.select(sysAuthUser);
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
}
