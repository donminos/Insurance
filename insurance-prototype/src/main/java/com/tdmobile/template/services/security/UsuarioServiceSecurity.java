package com.tdmobile.template.services.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdmobile.template.entity.Users;
import com.tdmobile.template.services.RolesService;
import com.tdmobile.template.services.UsersService;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UsuarioServiceSecurity implements UserDetailsService {

    @Autowired
    UsersService userServices;

    @Autowired
    RolesService rolesServices;
    
    /*@Autowired
    LoginAttemptService loginAttemptService;*/
            
    /*@Autowired
    private HttpServletRequest request;*/
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        /*String ip = Util.getClientIpAddr(request);
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }*/
        Users user = userServices.findUser(username).get(0);
        List<String> rolesSelect = new ArrayList();
        user.getUserRoleList().forEach((ur) -> {
            rolesSelect.add(ur.getIdRoles().getRole());
        });
        //user.setRolesList(roles);
        List<GrantedAuthority> authList = getGrantedAuthorities(rolesSelect);
        UserDetails userdetail = new User(user.getUser(), user.getPassword(), true, true, true, true, authList);
        return userdetail;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        roles.forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

}
