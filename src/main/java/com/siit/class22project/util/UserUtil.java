package com.siit.class22project.util;

import com.siit.class22project.exception.BusinessException;
import com.siit.class22project.service.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            return userDetails.getUser().getId();
        }

        throw new BusinessException("User not logged in");
    }
}
