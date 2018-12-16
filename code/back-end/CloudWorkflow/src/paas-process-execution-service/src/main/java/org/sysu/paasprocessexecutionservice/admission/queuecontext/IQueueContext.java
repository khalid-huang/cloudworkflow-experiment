package org.sysu.paasprocessexecutionservice.admission.queuecontext;

import org.sysu.paasprocessexecutionservice.admission.IAdmissionor;
import org.sysu.paasprocessexecutionservice.admission.requestcontext.IRequestContext;

//一个队列的上下文，包括了时间，执行环境等
public interface IQueueContext {

    public IAdmissionor getAdmission();

    public void setAdmission(IAdmissionor admission);
//    弹出一个请求
    public IRequestContext poll();
//   增加一个请求
    public void offer(IRequestContext requestContext);

//    查看队头元素
    public IRequestContext peek();
}
