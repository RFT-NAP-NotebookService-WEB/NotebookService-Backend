package hu.unideb.inf.notebookservice.web.security;

import hu.unideb.inf.notebookservice.service.domain.User;

public class SecurityContextHolder {

    public static User getUser() {
        return ((NotebookServiceUserDetails) org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal()).getUser();
    }
}
