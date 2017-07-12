import org.mitre.oauth2.model.RegisteredClient
import org.mitre.oauth2.model.ClientDetailsEntity.AuthMethod
import org.mitre.openid.connect.client.OIDCAuthenticationFilter
import org.mitre.openid.connect.client.service.ClientConfigurationService
import org.mitre.openid.connect.client.service.impl.DynamicRegistrationClientConfigurationService
import org.mitre.openid.connect.client.service.impl.DynamicServerConfigurationService
import org.mitre.openid.connect.client.service.impl.JsonFileRegisteredClientService
import org.mitre.openid.connect.client.service.impl.PlainAuthRequestUrlBuilder
import org.mitre.openid.connect.client.service.impl.StaticAuthRequestOptionsService
import org.mitre.openid.connect.client.service.impl.StaticSingleIssuerService

import helloauth.auth.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
	/*
	openIdConnectAuthenticationFilter(OIDCAuthenticationFilter) {
		issuerService = staticIssuerService(StaticSingleIssuerService) {
			issuer = 'https://mitreid.org/'
		}
		serverConfigurationService = dynamicServerConfigurationService(DynamicServerConfigurationService)
		clientConfigurationService = dynamicClientConfigurationService(DynamicRegistrationClientConfigurationService) {
			template = clientRegistrationTemplate(RegisteredClient) {
				clientName = 'Grails Integration Test'
				scope = ['openid', 'email', 'address', 'profile', 'phone']
				tokenEndpointAuthMethod = AuthMethod.SECRET_BASIC
				redirectUris = ['http://localhost:8080/openid_connect_login']
			}
			registeredClientService = registeredClientService(JsonFileRegisteredClientService) {
				filename = '/tmp/grails-test-clients.json'
			}
		}
		authRequestOptionsService = staticAuthRequestOptionsService(StaticAuthRequestOptionsService)
		authRequestUrlBuilder = plainAuthRequestUrlBuilder(PlainAuthRequestUrlBuilder)
	}
	*/
}
