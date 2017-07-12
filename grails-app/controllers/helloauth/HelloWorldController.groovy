package helloauth

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class HelloWorldController {
    def index() {
        render "<h1>Hello world!</h1>"
        render "<p>If you can read this, then you're authenticated</p>"
        return
    }
}
