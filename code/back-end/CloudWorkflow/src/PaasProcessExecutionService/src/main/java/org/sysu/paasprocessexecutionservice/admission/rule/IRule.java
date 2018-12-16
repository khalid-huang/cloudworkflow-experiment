package org.sysu.paasprocessexecutionservice.admission.rule;

import org.sysu.paasprocessexecutionservice.admission.IAdmissionor;
import org.sysu.paasprocessexecutionservice.admission.requestcontext.IRequestContext;

//准入规则
public interface IRule {

    public void setAdmissionor(IAdmissionor admissionor);

    public IAdmissionor getAdmissionor();

    public void admit(IRequestContext requestContext);
}
