package com.projectScope.projectScope.config;


import com.projectScope.projectScope.security.JwtAuthenticationEntryPoint;
import com.projectScope.projectScope.security.JwtAuthenticationTokenFilter;
import com.projectScope.projectScope.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user","/user/activate", "/users", "/image").permitAll()
                .antMatchers(HttpMethod.POST, "/user/auth", "/user").permitAll()

                .antMatchers(HttpMethod.GET, "/logs").hasAnyAuthority("TEAM_MEMBER")
                .antMatchers(HttpMethod.POST, "/userMember/projects", "/log").hasAnyAuthority("TEAM_MEMBER")
                .antMatchers(HttpMethod.DELETE, "/log/{listId}").hasAnyAuthority("TEAM_MEMBER")

                .antMatchers(HttpMethod.POST, "/userLeader/projects","/projects", "/projects/user/add").hasAnyAuthority("TEAM_LEADER")
                .antMatchers(HttpMethod.DELETE, "/projects").hasAnyAuthority("TEAM_LEADER")
                .anyRequest().authenticated();

        // Custom JWT based security filter
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

}
