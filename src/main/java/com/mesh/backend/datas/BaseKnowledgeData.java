package com.mesh.backend.datas;

import com.mesh.backend.entity.Projectmemos;
import com.mesh.backend.entity.Teammemos;

/**
 * 项目知识库和团队知识库直接写一起了，没什么区别
 *
 * @author xuedixuedi
 */
public class BaseKnowledgeData extends BaseData {
    public KnowledgeData knowledge;

    /**
     * 项目知识库
     *
     * @param projectmemos
     * @param uploaderName
     */
    public BaseKnowledgeData(Projectmemos projectmemos, String uploaderName) {
        super(true, "");
        knowledge = new KnowledgeData(projectmemos, uploaderName);
    }

    /**
     * 团队知识库
     *
     * @param teammemos
     * @param uploaderName
     */
    public BaseKnowledgeData(Teammemos teammemos, String uploaderName) {
        super(true, "");
        knowledge = new KnowledgeData(teammemos, uploaderName);
    }
}
