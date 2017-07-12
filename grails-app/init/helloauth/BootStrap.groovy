package helloauth
import helloauth.auth.*

class BootStrap {

    def init = { servletContext ->
        User user = new User(
                username:'admin',
                password:'password'
        )
        user.save()

        Role adminRole = new Role(authority:'ROLE_ADMIN').save()
        UserRole.create(user, adminRole, true)
    }
    def destroy = {
    }
}
