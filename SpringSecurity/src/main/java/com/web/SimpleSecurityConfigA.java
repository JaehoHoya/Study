package com.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;
// 메모리기반
@Slf4j
//@Configuration
//@EnableWebSecurity
public class SimpleSecurityConfigA
{
    @Bean  // 스프링이 제공해주는 페스워드를 암호로 바꿔줌   빈으로 등록하면 로그인할때마다 암호화해서 비교
    BCryptPasswordEncoder  passwordEncoder()
    {   /* 매번 다른 인코딩값을 생성하며, 생성된 인코딩 값은 내부에서 결국 hashpw()가 리턴한
      값의 비교에 최종적으로 패스워드 일치 여부가 결정된다 */
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        System.out.println("smith->" + enc.encode("smith"));
        System.out.println("blake->" + enc.encode("blake"));
        System.out.println("jones->" + enc.encode("jones"));
        System.out.println("smith->" + enc.encode("smith"));
        //smith->$2a$10$B1Ac8jgfl40FxvZmvUSy9ertYSxMgSz.Np8tt06buU7YVNccChTXW
        return enc;
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer()
    {
        return (webSecurity) -> webSecurity.ignoring().requestMatchers("/resources/**", "/ignore2");
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        System.out.println("접근제한 설정");
        //HttpSecurity의 설정 메소드들은 HttpSecurity의 참조를 리턴하므로 각 항목 설정시 Chain Action을 사용할 수 있다
        //각 설정 메소드 내에서도 Chain Action을 사용하는 구조로 되어 있다
        // 인가를 어떻게 하겠다라는 설정  밑에
        http.authorizeHttpRequests(authz -> authz  // 인증 오브젝트
                .requestMatchers("/", "/sec/", "/sec/loginForm", "/sec/denied", "/logout").permitAll()
                .requestMatchers("/sec/hello").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/sec/getemps").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/sec/addemp").hasAnyRole("ADMIN")
                .requestMatchers("/sec/menu").hasAnyRole("USER", "GUEST", "ADMIN")
                .requestMatchers("/sec/sample/**").hasAnyRole("GUEST", "ADMIN")    // **은 모든 하위 경로
                //.anyRequest().authenticated()  // 위의 설정 이외의 모든 요청은 인증을 거쳐야 한다
                //anyRequest().denyAll();        // 위의 설정 이외의 모든 요청은 거부한다
                .anyRequest().permitAll()       // 위의 설정 이외의 모든 요청은 인증 요구하지 않음

        ).csrf( csrfConf -> csrfConf.disable()  //가짜 요청을 잡는걸 무효화 시킴 (에러가 많이 뜸)

        ).formLogin(loginConf -> loginConf.loginPage("/sec/loginForm")   // 컨트롤러 메소드와 지정된 위치에 로그인 폼이 준비되어야 함
                .loginProcessingUrl("/doLogin")            // 컨트롤러 메소드 불필요, 폼 action과 일치해야 함
                .failureUrl("/sec/loginForm?error=T")      // 로그인 실패시 이동 경로(컨트롤러 메소드 필요함)
                //.failureForwardUrl("/login?error=Y")  //실패시 다른 곳으로 forward
                .defaultSuccessUrl("/sec/menu", true)
                .usernameParameter("id")  // 로그인 폼에서 이용자 ID 필드 이름, 디폴트는 username
                .passwordParameter("pw")  // 로그인 폼에서 이용자 암호 필트 이름, 디폴트는 password
                .permitAll()

        ).logout(logoutConf -> logoutConf.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 요청시 URL
                .logoutSuccessUrl("/sec/loginForm?logout=T")  // 로그아웃 성공시 다시 로그인폼으로 이동
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()

        ).exceptionHandling(exConf -> exConf.accessDeniedPage("/sec/denied"));  // 권한이 없어 거부할 때 호출됨

        return http.build();
    }


    //@Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception
    {
        authenticationMgr.inMemoryAuthentication() /* 메모리 기반 인증(Authentication) */
                .withUser("employee").password("$2a$10$MZ2ANCUXIj5mrAVbytojruvzrPv9B3v9CXh8qI9qP13kU8E.mq7yO")
                .authorities("ROLE_USER")
                .and()
                .withUser("imadmin").password("$2a$10$FA8kEOhdRwE7OOxnsJXx0uYQGKaS8nsHzOXuqYCFggtwOSGRCwbcK")
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .and()
                .withUser("guest").password("$2a$10$ABxeHaOiDbdnLaWLPZuAVuPzU3rpZ30fl3IKfNXybkOG2uZM4fCPq")
                .authorities("ROLE_GUEST")
                .and()
                .withUser("smith").password("$2a$10$unT/XSffxLnB98TWE60nP.lF95iOUoJkLn.VF57U27aTu9eDu/Xjq")
                .authorities("ROLE_USER", "ROLE_ADMIN");

    }
}