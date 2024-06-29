package com.math.mathcha.Util;


import com.math.mathcha.entity.Student;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AccountUtil {
    public Student getCurrentStudent() {
        return (Student) SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
    }
}
