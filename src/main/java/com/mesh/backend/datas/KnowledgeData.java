package com.mesh.backend.datas;

import java.time.LocalDateTime;

/**
 * @author xuedixuedi
 * 知识库基本类
 */
public class KnowledgeData {
    public int knowledgeId;
    public String knowledgeName;
    //链接地址
    public String hyperlink;
    public String uploaderName;
    public LocalDateTime createTime;

    public KnowledgeData(int knowledgeId, String knowledgeName, String hyperlink, String uploaderName, LocalDateTime createTime) {
        this.knowledgeId = knowledgeId;
        this.knowledgeName = knowledgeName;
        this.hyperlink = hyperlink;
        this.uploaderName = uploaderName;
        this.createTime = createTime;
    }
}
