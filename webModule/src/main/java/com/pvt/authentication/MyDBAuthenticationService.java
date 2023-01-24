package com.pvt.authentication;

import com.pvt.fasad.UserFasad;
import com.pvt.forms.UserForm;
import com.pvt.jar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyDBAuthenticationService implements UserDetailsService {

    @Autowired
    UserFasad userFasad;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserForm account = userFasad.getByUsername(username);

        if(account==null){
            throw new UsernameNotFoundException("User "+ username + " was not found");
        }



        List<GrantedAuthority> grantList = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+account.getRole());

        grantList.add(authority);


        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        UserDetails userDetails = (UserDetails) new User(account.getUsername(), account.getPassword(), enabled,
                accountNonExpired,credentialsNonExpired,accountNonLocked,grantList);
        return userDetails;
    }
}
