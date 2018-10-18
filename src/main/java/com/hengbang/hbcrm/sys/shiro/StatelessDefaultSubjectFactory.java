package com.hengbang.hbcrm.sys.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * subject factory
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
//        Do not create session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
