package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Checks whether the entered username and password are correct.
 */
@Service
public class AuthenticationService implements AuthenticationProvider {

    private UserService userService;
    private HashService hashService;

    /**
     * Constructor with injected UserService and HashService.
     *
     * @param userService for getting the taba from the user table.
     * @param hashService for enrypting the password.
     */
    public AuthenticationService(UserService userService, HashService hashService) {
        this.userService = userService;
        this.hashService = hashService;
    }

    /**
     * Checks whether the username and password are valid.
     *
     * @param authentication the current Authentication.
     * @return the Authentication if the inputs are correct.
     * @throws AuthenticationException Exception
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = this.userService.getUser(username);

        if (user != null) {
            String encodedSalt = user.getSalt();
            String hashedPassword = this.hashService.getHashedValue(password, encodedSalt);
            if (hashedPassword.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
