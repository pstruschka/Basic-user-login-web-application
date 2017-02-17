package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.User;
import io.muic.ooc.webapp.Webapp;
import io.muic.ooc.webapp.util.Encryption;


import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SecurityService {

    private SecurityService securityService;

    public boolean isAuthorized(HttpServletRequest request) {
        Webapp.mySQLConnection.getUsers();
        Integer id = (Integer) request.getSession()
                .getAttribute("session");
        // do checking
        return id != null && User.getUserMap().containsKey(id);
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        Webapp.mySQLConnection.getUsers();
        Integer id = Webapp.mySQLConnection.userExists(username);
        String passwordInDB = Webapp.mySQLConnection.getUserPassword(username);
        System.out.printf("password: " + passwordInDB);
        boolean isMatched = false;
        try {
            if (id !=null && passwordInDB != null) {
                isMatched = Encryption.validatePassword(password, passwordInDB);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        if (isMatched) {
            request.getSession().setAttribute("session", id);
            return true;
        } else {
            return false;
        }
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
