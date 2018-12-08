package hu.unideb.inf.notebookservice.web.security;

import lombok.Data;

@Data
public class JWTConfiguration {

    public final static String SECRET ="superSecretPassword";
    public final static Long EXPIRATION_TIME=600000L;
    public final static String TOKEN_PREFIX="Bearer ";
    public final static String HEADER_STRING="Authorization";

}