package com.pvh.group3_spring_mini_project001.constance;

import com.pvh.group3_spring_mini_project001.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Auth {
    private static Integer currentId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
    public static final Integer USER_ID=currentId();


}
