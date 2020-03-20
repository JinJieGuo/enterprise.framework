package enterprise.framework.service.config.tree;

import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.pojo.config.tree.SysConfigTreeVO;

public interface SysConfigTreeService {


    /**
     * 保存字典树
     *
     * @param sysConfigTreeVO
     * @return
     */
    HttpResponse saveTree(SysConfigTreeVO sysConfigTreeVO);

    /**
     * 更新字典树
     *
     * @param sysConfigTreeVO
     * @return
     */
    HttpResponse updateTree(SysConfigTreeVO sysConfigTreeVO);

    /**
     * 删除字典树
     *
     * @param sysConfigTreeVO
     * @return
     */
    HttpResponse deleteTree(SysConfigTreeVO sysConfigTreeVO);

    /**
     * 查询字典树
     *
     * @return
     */
    HttpResponse listAllTree();

}
