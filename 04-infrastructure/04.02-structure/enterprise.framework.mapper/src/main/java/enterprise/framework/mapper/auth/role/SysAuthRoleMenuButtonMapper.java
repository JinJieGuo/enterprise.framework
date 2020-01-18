package enterprise.framework.mapper.auth.role;

import enterprise.framework.domain.auth.SysAuthRoleMenuButton;
import enterprise.framework.pojo.auth.role.SysAuthRoleMenuButtonVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAuthRoleMenuButtonMapper extends Mapper<SysAuthRoleMenuButton> {


    @Insert("<script>" +
            "INSERT INTO sys_auth_role_menu_button (role_id, menu_id, button_id) VALUES" +
            "<foreach collection='sysAuthRoleMenuButtonVOList' item='item' index='index' separator=','>" +
            "(#{item.roleId}, #{item.menuId}, #{item.buttonId})" +
            "</foreach>" +
            "</script>")
    int saveRoleMenuButtonList(@Param(value = "sysAuthRoleMenuButtonVOList") List<SysAuthRoleMenuButtonVO> sysAuthRoleMenuButtonVOList);

    /**
     * 逻辑删除角色下的菜单与按钮
     *
     * @param roleId
     * @return
     */
    @Update("\tUPDATE sys_auth_role_menu_button SET is_deleted = 1 WHERE role_id = #{roleId}")
    int deleteRoleMenuButton(long roleId);
}
