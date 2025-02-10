package br.com.ifpe.oxefood.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ifpe.oxefood.modelo.acesso.Perfil;
import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.seguranca.JwtAuthenticationFilter;

@Configuration // @Configuration é uma anotação que indica que a classe é uma classe de configuração
@EnableWebSecurity // @EnableWebSecurity é uma anotação que habilita a segurança web no projeto
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // a classe SecurityFilterChain controla a permissão de acesso aos endpoints/rotas, liberando as rotas publicas e protegendo as privadas
    
    @Bean // @Bean é uma anotação que indica que o método anotado irá retornar um objeto que deve ser gerenciado pelo Spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(c -> c.disable())
            .authorizeHttpRequests(authorize -> authorize
            // .permitAll() permite acesso a todos os endpoints, sem necessidade de autenticação
            // .hasAnyAuthority() permite acesso a endpoints que possuem a autorização especificada
            

                .requestMatchers(HttpMethod.POST, "/api/cliente").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/funcionario").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()
                // caso uma rota receba parametros como /api/cliente/{id}, o uso de * é necessário, ficando: /api/cliente/*
                // pode permitir rotas do mesmo tipo de requisição em uma mesma linha de codigo, como "/api/cliente", "/api/produto/*"

                .requestMatchers(HttpMethod.GET, "/api/produto/").hasAnyAuthority(
                   Perfil.ROLE_CLIENTE,
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Consulta de produto

               .requestMatchers(HttpMethod.POST, "/api/produto").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Cadastro de produto

               .requestMatchers(HttpMethod.PUT, "/api/produto/*").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN,
                   Perfil.ROLE_FUNCIONARIO_USER) //Alteração de produto
                  
               .requestMatchers(HttpMethod.DELETE, "/api/produto/*").hasAnyAuthority(
                   Perfil.ROLE_FUNCIONARIO_ADMIN) //Exclusão de produto


                .requestMatchers(HttpMethod.GET, "/api-docs/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()

                .anyRequest().authenticated()

            )
            // .sessionManagement() define a politica de gerenciamento de sessão, no caso, STATELESS não mantem estado de sessão
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )         
            // .addFilterBefore() adiciona um filtro antes de outro filtro, no caso, antes do filtro de autenticação   
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
    
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);    
        return source;
    }
}

