package enterprise.framework.service.auth.menu;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.auth.menu.SysAuthMenuButtonVO;
import enterprise.framework.pojo.auth.menu.SysAuthMenuVO;
import enterprise.framework.pojo.auth.role.SysAuthRoleVO;

import java.util.List;

public interface SysAuthMenuService {
    /**
     * 保存菜单
     *
     * @param sysAuthMenuVO
     * @return
     */
    HttpResponse saveMenu(SysAuthMenuVO sysAuthMenuVO);

    /**
     * 保存菜单下的按钮
     *
     * @param sysAuthMenuButtonVOList
     * @return
     */
    HttpResponse saveMenuButton(List<SysAuthMenuButtonVO> sysAuthMenuButtonVOList);

    /**
     * 更新菜单
     *
     * @param sysAuthMenuVO
     * @return
     */
    HttpResponse updateMenu(SysAuthMenuVO sysAuthMenuVO);

    /**
     * 删除菜单
     *
     * @param sysAuthMenuVO
     * @return
     */
    HttpResponse deleteMenu(SysAuthMenuVO sysAuthMenuVO);

    /**
     * 根据菜单主键获取菜单信息
     *
     * @param sysAuthMenuVO
     * @return
     */
    HttpResponse getMenuById(SysAuthMenuVO sysAuthMenuVO);

    /**
     * 获取所有用户
     *
     * @return
     */
    HttpResponse listAllMenu();
}
