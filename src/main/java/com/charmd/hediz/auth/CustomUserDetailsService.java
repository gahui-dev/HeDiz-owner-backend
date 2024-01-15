package com.charmd.hediz.auth;

import java.util.*;
import com.charmd.hediz.dto.StaffDTO;
import com.charmd.hediz.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffService staffService;

    @Override
    public UserDetails loadUserByUsername(String staffId) throws UsernameNotFoundException {
        StaffDTO staffDto = staffService.getUserById(staffId);
        if (staffDto != null) {
            List<SimpleGrantedAuthority> list = new ArrayList<>();
            String staffRole = staffDto.getStaff_role();
            list.add(new SimpleGrantedAuthority(staffRole));
            UserDetails userDetails = new org.springframework.security.core.userdetails
                    .User(staffDto.getStaff_id(), staffDto.getStaff_pw(), list);
            return userDetails;
        } else {
            throw new UsernameNotFoundException(staffId + " > 해당 ID는 데이터베이스에서 찾을 수 없습니다.");
        }
    }
}
