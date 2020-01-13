package enterprise.framework.service.auth.button;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.button.SysAuthButtonVO;
import org.springframework.stereotype.Service;


public interface SysAuthButtonService {

    /**
     * 保存按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    HttpResponse saveButton(SysAuthButtonVO sysAuthButtonVO);

    /**
     * 更新按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    HttpResponse updateButton(SysAuthButtonVO sysAuthButtonVO);

    /**
     * 删除按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    HttpResponse deleteButton(SysAuthButtonVO sysAuthButtonVO);

    /**
     * 根据id获取按钮
     *
     * @param sysAuthButtonVO
     * @return
     */
    HttpResponse getButtonById(SysAuthButtonVO sysAuthButtonVO);

    /**
     * 获取所有按钮
     *
     * @return
     */
    HttpResponse listAllButton();

    /**
     * 获取所有未删除的按钮用于为菜单分配按钮
     *
     * @return
     */
    HttpResponse listAllButtonInfo();
}
