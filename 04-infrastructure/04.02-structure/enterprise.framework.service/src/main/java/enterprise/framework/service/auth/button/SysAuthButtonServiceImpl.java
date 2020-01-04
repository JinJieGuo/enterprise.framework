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
 *		gl				 2020-01-03		      1.00					新建
 *******************************************************************************/

package enterprise.framework.service.auth.button;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.domain.auth.SysAuthButton;
import enterprise.framework.mapper.auth.button.SysAuthButtonMapper;
import enterprise.framework.pojo.auth.button.SysAuthButtonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAuthButtonServiceImpl implements SysAuthButtonService {

    @Autowired(required = false)
    private SysAuthButtonMapper sysAuthButtonMapper;

    /**
     * 保存按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    public HttpResponse saveButton(SysAuthButtonVO sysAuthButtonVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthButton sysAuthButton = new SysAuthButton(sysAuthButtonVO);
            int response = sysAuthButtonMapper.saveButton(sysAuthButton);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "保存成功";
                sysAuthButtonVO.setButtonId(sysAuthButton.getButtonId());
                httpResponse.content = sysAuthButtonVO;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "保存失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "保存按钮异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 更新按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    public HttpResponse updateButton(SysAuthButtonVO sysAuthButtonVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthButton sysAuthButton = new SysAuthButton(sysAuthButtonVO);
            int response = sysAuthButtonMapper.updateButton(sysAuthButton);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "更新成功";
                httpResponse.content = sysAuthButton;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "更新失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "更新按钮异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 删除按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    public HttpResponse deleteButton(SysAuthButtonVO sysAuthButtonVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            sysAuthButtonVO.setIsDelete(1);
            SysAuthButton sysAuthButton = new SysAuthButton(sysAuthButtonVO);
            int response = sysAuthButtonMapper.updateButton(sysAuthButton);
            if (response > 0) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "删除成功";
                httpResponse.content = sysAuthButton;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "删除失败";
            }
            return httpResponse;
        } catch (Exception error) {
            httpResponse.status = HttpStatus.ERROR.value();
            httpResponse.msg = "[类名:(" + this.getClass() + ")]" + "更新按钮异常:" + error.getMessage();
            return httpResponse;
        }
    }

    /**
     * 根据id获取按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    public HttpResponse getButtonById(SysAuthButtonVO sysAuthButtonVO) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            SysAuthButton sysAuthButton = new SysAuthButton(sysAuthButtonVO);
            SysAuthButton response = sysAuthButtonMapper.selectOne(sysAuthButton);
            if (response != null) {
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
     * 获取所有按钮
     *
     * @return
     */
    public HttpResponse listAllButton() {
        HttpResponse httpResponse = new HttpResponse();
        try {
            List<SysAuthButtonVO> dataSource = sysAuthButtonMapper.listAllButton();
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
}
