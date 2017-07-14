import org.mitre.jose.keystore.JWKSetKeyStore
import org.mitre.jwt.signer.service.impl.DefaultJWTSigningAndValidationService
import org.mitre.jwt.signer.service.impl.JWKSetCacheService
import org.mitre.oauth2.model.RegisteredClient
import org.mitre.oauth2.model.ClientDetailsEntity.AuthMethod
import org.mitre.openid.connect.client.NamedAdminAuthoritiesMapper
import org.mitre.openid.connect.client.OIDCAuthenticationFilter
import org.mitre.openid.connect.client.OIDCAuthenticationProvider
import org.mitre.openid.connect.client.SubjectIssuerGrantedAuthority
import org.mitre.openid.connect.client.service.ClientConfigurationService
import org.mitre.openid.connect.client.service.impl.DynamicRegistrationClientConfigurationService
import org.mitre.openid.connect.client.service.impl.DynamicServerConfigurationService
import org.mitre.openid.connect.client.service.impl.JsonFileRegisteredClientService
import org.mitre.openid.connect.client.service.impl.PlainAuthRequestUrlBuilder
import org.mitre.openid.connect.client.service.impl.StaticAuthRequestOptionsService
import org.mitre.openid.connect.client.service.impl.StaticClientConfigurationService
import org.mitre.openid.connect.client.service.impl.StaticSingleIssuerService

import helloauth.auth.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
	
	openIdConnectAuthenticationFilter(OIDCAuthenticationFilter) {
		authenticationManager = ref('authenticationManager')
		issuerService = ref('staticIssuerService')
		serverConfigurationService = ref('dynamicServerConfigurationService')
		clientConfigurationService = ref('staticClientConfigurationService')
		authRequestOptionsService = ref('staticAuthRequestOptionsService')
		authRequestUrlBuilder = ref('plainAuthRequestUrlBuilder')
	}
	
	staticIssuerService(StaticSingleIssuerService) {
		issuer = 'https://idp.int.login.gov/'
	}
	dynamicServerConfigurationService(DynamicServerConfigurationService)
	staticClientConfigurationService(StaticClientConfigurationService) {
		clients = [
			'https://idp.int.login.gov/': ref('clientRegistrationTemplate')
		]
	}
	clientRegistrationTemplate(RegisteredClient) {
		clientId = "you-shouldn't-let-people-pick-their-client-id-you-know"
		clientName = 'Grails Integration Test'
		scope = ['openid', 'email', 'address', 'profile', 'phone']
		tokenEndpointAuthMethod = AuthMethod.PRIVATE_KEY
		redirectUris = ['http://localhost:8080/openid_connect_login']
	}
	staticAuthRequestOptionsService(StaticAuthRequestOptionsService)
	plainAuthRequestUrlBuilder(PlainAuthRequestUrlBuilder)
	
	openIdConnectAuthenticationProvider(OIDCAuthenticationProvider) {
		authoritiesMapper = ref('namedAuthoritiesMapper') 
	}
	
	namedAuthoritiesMapper(NamedAdminAuthoritiesMapper) {
		admins = [
			ref('admin')
		]
	}
	
	admin(SubjectIssuerGrantedAuthority, '90342.ASDFJWFA', 'https://mitreid.org/')
	
	validatorCache(JWKSetCacheService)
	
	defaultSignerService(DefaultJWTSigningAndValidationService, ref('keyStore')) {
		defaultSignerKeyId = 'rsa1'
		defaultSigningAlgorithmName = 'RS256'
	}
	
	keyStore(JWKSetKeyStore) {
		location = 'classpath:keystore.jwks'
	}
}
