package br.com.amaxzon.config;

import br.com.amaxzon.security.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()

                .antMatchers("/inicio").permitAll() // Permito todo mundo acessar /inicio
                .antMatchers("/pessoa/formulario").hasRole("USER") //Somente pessoa com papel "USER" acessa /pessoa/formulario
                .antMatchers("/pessoa/salvar").hasAnyRole("USER","ADMIN") // Pessoa com papel "USER" ou "ADMIN" acessa /pessoa/salvar
                .antMatchers("/pessoa/listar").permitAll() // /pessoa/listar todo mundo pode acessar

                .anyRequest().authenticated() // o resto precisa está autenticado

                .and()
                .formLogin()
                .loginPage("/pessoa/logar") // Esse é o controller que chama nosso formulario
                .permitAll() //permitir acesso para essa url "entrar"

                //.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                .and()
                .logout()
                .logoutSuccessUrl("/pessoa/logar?logout") // logout sucesso
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsServiceImplementation).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/js/**","/images/**"); // ignora e permite uri's com esses arquivos
    }


}
