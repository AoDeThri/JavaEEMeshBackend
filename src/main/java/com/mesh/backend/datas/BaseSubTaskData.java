package com.mesh.backend.datas;

import com.mesh.backend.entity.Subtasks;

/**
 * 子任务
 *
 * @author xuedixuedi
 */
public class BaseSubTaskData extends BaseData {
    public SubTaskData tasks;

    BaseSubTaskData(Subtasks subtasks, String founder, String principal) {
        super(true, "");
        this.tasks = new SubTaskData(subtasks, founder, principal);
    }
}
