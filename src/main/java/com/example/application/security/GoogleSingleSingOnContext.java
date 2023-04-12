package com.example.application.security;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import dev.hilla.sso.starter.BackChannelLogoutSubscription;
import dev.hilla.sso.starter.SingleSignOnContext;
import dev.hilla.sso.starter.SingleSignOnData;
import dev.hilla.sso.starter.SingleSignOnProperties;

public class GoogleSingleSingOnContext extends SingleSignOnContext {
  private final SingleSignOnProperties properties;

  public GoogleSingleSingOnContext(ClientRegistrationRepository clientRegistrationRepository,
      SingleSignOnProperties properties, BackChannelLogoutSubscription backChannelLogoutSubscription) {
    super(clientRegistrationRepository, properties, backChannelLogoutSubscription);
    this.properties = properties;
  }

  /**
   * Returns the data for the single sign-on, to be used to get everything
   * with a single request.
   *
   * @return the data for the single sign-on.
   */
  @Override
  public SingleSignOnData getSingleSignOnData() {
    SingleSignOnData data = new SingleSignOnData();
    data.setLoginLink(properties.getLoginRoute());

    getOidcUser().ifPresent(user -> {
      data.setAuthenticated(true);
      data.setRoles(userRoles(user));
      // Logout link is not supported by Google OIDC
      data.setBackChannelLogoutEnabled(isBackChannelLogoutEnabled());
    });

    return data;
  }

}
