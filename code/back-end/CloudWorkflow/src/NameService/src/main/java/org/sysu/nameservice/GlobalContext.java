package org.sysu.nameservice;

import java.util.Date;

/**
 * Global data access
 */
public class GlobalContext {
    
    public static final String SERVICEID_ACTIVITISERVICE = "activiti-service";

    public static final String ACTION_ACTIVITISERVICE_STARTPROCESS = "startProcess";

    public static final String ACTION_ACTIVITISERVICE_TEST = "test";

    public static final String ACTION_ACTIVITISERVICE_CLAIMTASK = "claimTask";

    public static final String ACTION_ACTIVITISERVICE_COMPLETETASK = "completeTask";

    public static final String ACTION_ACTIVITISERVICE_GETCURRENTSINGLETASK = "getCurrentSingleTask";

    public static final String ACTION_ACTIVITISERVICE_GETCURRENTTASKS = "getCurrentTasks";

    public static final String ACTION_ACTIVITISERVICE_GETCURRENTTASKSOFASSIGNEE = "getCurrentTasksOfAssignee";

    /**用于保持busynessIndicator算法的busyness的目录地址；在MultiplePastTimeSlot中写入信息 */
    public static final String ACTIVITISERVICE_BUSYNESS_DIRECTORY = "D:\\workspace\\cloud_workflow\\busynessDirectory";


    /**
     * Service URL for activiti-service start process
     */
    public static final String URL_ACTIVITISERVICE_STARTPROCESS="/startProcess";


    /**
     * Service URL for activiti-service get current tasks
     */
    public static final String URL_ACTIVITISERVICE_GETCURRENTTASKS="/getCurrentTasks";

    public static final String URL_ACTIVITISERVICE_GETCURRENTSINGLETASK="/getCurrentSingleTask";

    /**
     * Service URL for activiti-service get one assignee's current tasks of one processinstance
     */
    public static final String URL_ACTIVITISERVICE_GETCURRENTTASKSOFASSIGNEE="/getCurrentTasksOfAssignee";

    /**
     * Service URL for activiti-service claim task
     */
    public static final String URL_ACTIVITISERVICE_CLAIMTASK="/claimTask";

    /**
     * Service URL for activiti-service complete task
     */
    public static final String URL_ACTIVITISERVICE_COMPLETETASK="/completeTask";

    // 可配置服务器组最多维护的服务器数
    public static final int MAX_SERVER_IN_GROUP = 3;

    // 指定负载
    public static final int LOADFACTOR = 1000;

    // 服务器组修改时间段 设置1小时
    public static final long MOD_TIMESTAMP = 60*60*1000;

    // 服务器组垃圾回收时间 设置6小时
    public static final long GARBAGE_COLLECTION_TIMESTAMP = 40*1000;

}
