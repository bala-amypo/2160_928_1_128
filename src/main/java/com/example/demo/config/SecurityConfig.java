@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilterConfig jwtFilterConfig;

    public SecurityConfig(JwtFilterConfig jwtFilterConfig) {
        this.jwtFilterConfig = jwtFilterConfig;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/auth/**",
                        "/status",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(
                    jwtFilterConfig,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
