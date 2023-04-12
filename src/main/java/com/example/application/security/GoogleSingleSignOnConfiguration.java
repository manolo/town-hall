package com.example.application.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import com.vaadin.sso.core.BackChannelLogoutFilter;

import dev.hilla.sso.starter.BackChannelLogoutSubscription;
import dev.hilla.sso.starter.BootstrapDataServiceListener;
import dev.hilla.sso.starter.SingleSignOnContext;
import dev.hilla.sso.starter.SingleSignOnProperties;

@EnableWebSecurity
@Configuration
@EnableConfigurationProperties(SingleSignOnProperties.class)
public class GoogleSingleSignOnConfiguration extends VaadinWebSecurity {
    private final GoogleSingleSingOnContext googleSingleSingOnContext;

    private final SingleSignOnProperties properties;

    private final BackChannelLogoutFilter backChannelLogoutFilter;

    private final SessionRegistry sessionRegistry;

    private final BackChannelLogoutSubscription backChannelLogoutSubscription;

    private final BootstrapDataServiceListener bootstrapDataServiceListener;

    /**
     * Creates an instance of this configuration bean.
     *
     * @param properties
     *            the configuration properties
     * @param sessionRegistry
     *            the session registry
     * @param clientRegistrationRepository
     *            the client-registration repository
     * @param eventPublisher
     *            the event publisher for logout events
     */
    public GoogleSingleSignOnConfiguration(SingleSignOnProperties properties,
            SessionRegistry sessionRegistry,
            ClientRegistrationRepository clientRegistrationRepository,
            ApplicationEventPublisher eventPublisher) {
        this.properties = properties;
        this.sessionRegistry = sessionRegistry;
        this.backChannelLogoutFilter = new BackChannelLogoutFilter(
                sessionRegistry, clientRegistrationRepository, eventPublisher);
        this.backChannelLogoutSubscription = new BackChannelLogoutSubscription();
        this.googleSingleSingOnContext = new GoogleSingleSingOnContext(clientRegistrationRepository, properties,
            backChannelLogoutSubscription);
        this.bootstrapDataServiceListener = new BootstrapDataServiceListener(
                googleSingleSingOnContext);
    }

    @Bean
    public BackChannelLogoutSubscription backChannelLogoutSubscription() {
        return backChannelLogoutSubscription;
    }

    @Bean
    public SingleSignOnContext singleSignOnContext() {
        return googleSingleSingOnContext;
    }

    @Bean
    public BootstrapDataServiceListener bootstrapDataServiceListener() {
        return bootstrapDataServiceListener;
    }

    @Bean(name = "VaadinSecurityFilterChainBean")
    @Override
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2Login()
                // Set the default login route
                .loginPage(properties.getLoginRoute())
                // Set a default logout success route, as it is required,
                // although it is not used
                .and().logout().logoutSuccessUrl("/")
                // Setup session management
                .and().sessionManagement().sessionConcurrency(concurrency -> {
                    // Sets the maximum number of concurrent sessions per user.
                    // The default is -1 which means no limit on the number of
                    // concurrent sessions per user.
                    concurrency.maximumSessions(
                            properties.getMaximumConcurrentSessions());
                    // Sets the session-registry which is used for Back-Channel
                    concurrency.sessionRegistry(sessionRegistry);
                });

        if (properties.isBackChannelLogout()) {
            backChannelLogoutFilter.setBackChannelLogoutRoute(
                    properties.getBackChannelLogoutRoute());

            // Adds the Back-Channel logout filter to the filter chain
            http.addFilterAfter(backChannelLogoutFilter, LogoutFilter.class);

            // Disable CSRF for Back-Channel logout requests
            final var matcher = backChannelLogoutFilter.getRequestMatcher();
            http.csrf().ignoringRequestMatchers(matcher);
        }

        return http.build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.logout(logout -> {
            logout.logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler());
        });
    }
}
