package com.example.security.config;

import com.example.security.securities.JwtAuthenticationEntryPoint;
import com.example.security.securities.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Primary
    public UserDetailsManager userDetailsManager(DataSource dataSource){
      JdbcUserDetailsManager theUserDetailsManager=new JdbcUserDetailsManager(dataSource);

      theUserDetailsManager.setUsersByUsernameQuery("select * from customer where email=?");

      theUserDetailsManager.setAuthoritiesByUsernameQuery("select customer_id,role_id from roles where customer_id=?");


      return theUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//      http.authorizeHttpRequests(configurer ->
//        configurer
//          .requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create-user").permitAll()
//
////          .requestMatchers(HttpMethod.GET, "/order/all").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.POST, "/order/add").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.GET, "/order/orderedProduct").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.POST, "/order/orderedProduct").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.GET, "/product/all").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.POST, "/product/add").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.POST, "/createNewRole").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.POST, "/customer/add").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.GET, "/customer/forAdmin").hasRole("ADMIN")
////          .requestMatchers(HttpMethod.GET, "/customer/forUser").hasRole("CUSTOMER")
////          .requestMatchers(HttpMethod.POST, "/customer/delete/{customer_id}").hasRole("ADMIN")
//        );

        http.csrf(csrf -> csrf.disable())
                .cors().and()
                .authorizeRequests().
                requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create-user").permitAll()
//
//          .requestMatchers(HttpMethod.GET, "/order/all").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.POST, "/order/add").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.GET, "/order/orderedProduct").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.POST, "/order/orderedProduct").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.GET, "/product/all").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.POST, "/product/add").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.POST, "/createNewRole").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.POST, "/customer/add").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.GET, "/customer/forAdmin").hasRole("ADMIN")
//          .requestMatchers(HttpMethod.GET, "/customer/forUser").hasRole("CUSTOMER")
//          .requestMatchers(HttpMethod.POST, "/customer/delete/{customer_id}").hasRole("ADMIN")

          .anyRequest()
          .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.httpBasic(Customizer.withDefaults());
        return http.build();



    }

//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//      .authorizeRequests(authorizeRequests ->
//        authorizeRequests
//          .antMatchers(HttpMethod.GET, "/order/all").hasRole("ADMIN")
//          .antMatchers(HttpMethod.POST, "/order/add").hasRole("ADMIN")
//          .antMatchers(HttpMethod.GET, "/order/orderedProduct").hasRole("ADMIN")
//          .antMatchers(HttpMethod.POST, "/order/orderedProduct").hasRole("ADMIN")
//          .antMatchers(HttpMethod.GET, "/product/all").hasRole("ADMIN")
//          .antMatchers(HttpMethod.POST, "/product/add").hasRole("ADMIN")
//          .antMatchers(HttpMethod.POST, "/createNewRole").hasRole("ADMIN")
//          .antMatchers(HttpMethod.POST, "/customer/add").hasRole("ADMIN")
//          .antMatchers(HttpMethod.GET, "/customer/forAdmin").hasRole("ADMIN")
//          .antMatchers(HttpMethod.GET, "/customer/forUser").hasRole("CUSTOMER")
//          .antMatchers(HttpMethod.POST, "/customer/delete/{customer_id}").hasRole("ADMIN")
//      )
//      .csrf().disable()
//      .cors()
//      .and()
//      .authorizeRequests(authorizeRequests ->
//        authorizeRequests
//          .antMatchers("/auth/login").permitAll()
//          .antMatchers("/auth/create-user").permitAll()
//      )
//      .anyRequest().authenticated()
//      .and()
//      .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//
//    return http.build();
//  }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
