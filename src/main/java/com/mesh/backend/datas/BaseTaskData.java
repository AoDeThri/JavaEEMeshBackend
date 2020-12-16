package com.mesh.backend.datas;

import com.mesh.backend.entity.Tasks;

import java.util.List;

/**
 * 任务返回值
 *
 * @author xuedixuedi
 */
public class BaseTaskData extends BaseData {
    public TaskData tasks;


    /**
     * 任务:update/create/query
     *
     * @param tasks
     * @param founder
     * @param principal
     * @param subTasks
     */
    BaseTaskData(Tasks tasks, String founder, String principal, List<SubTaskData> subTasks) {
        super(true, "");
        this.tasks = new TaskData(tasks, founder, principal, subTasks);
    }


}
