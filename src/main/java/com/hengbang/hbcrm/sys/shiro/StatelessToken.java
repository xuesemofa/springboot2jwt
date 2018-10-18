package com.hengbang.hbcrm.sys.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 */
public class StatelessToken implements AuthenticationToken {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StatelessToken() {
        super();
    }

    public StatelessToken(String token) {
        this.token = token;
    }

    //    Account
    @Override
    public Object getPrincipal() {
        return token;
    }

    //Digest
    @Override
    public Object getCredentials() {
        return token;
    }
}
