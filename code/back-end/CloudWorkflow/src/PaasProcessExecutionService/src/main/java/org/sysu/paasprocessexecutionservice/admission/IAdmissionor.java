package org.sysu.paasprocessexecutionservice.admission;

import org.sysu.paasprocessexecutionservice.admission.requestcontext.IRequestContext;

public interface IAdmissionor {
    public void admit(IRequestContext requestContext);

    public void dispatch(IRequestContext requestContext);
}
