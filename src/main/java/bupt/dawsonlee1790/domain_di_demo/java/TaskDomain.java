package bupt.dawsonlee1790.domain_di_demo.java;

import bupt.dawsonlee1790.domain_di_demo.SpringContext;
import bupt.dawsonlee1790.domain_di_demo.UserService;

public class TaskDomain {

    private UserService userService = SpringContext.Companion.getBean(UserService.class);

    public void dosomething(){
        userService.save("TaskDomain");
    }
}
