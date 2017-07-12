package helloauth
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils
import helloauth.auth.*

class BootStrap {

    def init = {
		// set the OIDC filter up before the pre auth filter
//		SpringSecurityUtils.registerFilter('openIdConnectAuthenticationFilter', SecurityFilterPosition.PRE_AUTH_FILTER - 1)
    }
    def destroy = {
    }
}
