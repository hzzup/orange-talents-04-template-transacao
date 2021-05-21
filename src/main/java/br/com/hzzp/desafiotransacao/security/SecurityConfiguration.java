package br.com.hzzp.desafiotransacao.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers(HttpMethod.GET, "/transacao/**").hasAuthority("SCOPE_transacao:read")
                                .antMatchers(HttpMethod.POST, "/transacao/**").hasAuthority("SCOPE_transacao:write")
                                .antMatchers(HttpMethod.DELETE, "/transacao/**").hasAuthority("SCOPE_transacao:delete")
                                .anyRequest().authenticated()                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
