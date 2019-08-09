package bupt.dawsonlee1790.domain_di_demo

import bupt.dawsonlee1790.domain_di_demo.java.TaskDomain
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@SpringBootApplication
class DomainDiDemoApplication

fun main(args: Array<String>) {
    runApplication<DomainDiDemoApplication>(*args)
    val userDomain = UserDomain("dawson", 23)
    userDomain.dosomething()
    userDomain.dosomething()
    userDomain.dosomething()
    val taskDomain = TaskDomain()
    taskDomain.dosomething()
    taskDomain.dosomething()
    taskDomain.dosomething()
    UserDomain("dawson1", 23).dosomething()
    UserDomain("dawson2", 23).dosomething()

    DataUser("dawson3",23).dosomething()
//    DataUser("dawson4",23).dosomething()
//    DataUser("dawson5",23).dosomething()

}

class DataUser(var name: String?, var age: Int?) {

    private lateinit var userService: UserService

    @Autowired
    constructor(userService: UserService) : this(name = null, age = null) {
        this.userService = userService
    }

    fun dosomething(){
        userService.save("dataUser $name")
    }

}

class UserDomain(
        val name: String,
        val age: Int
) {

    private val userService: UserService = SpringContext.getBean(UserService::class.java)

    fun dosomething() {
        userService.save(name)
    }

}

interface UserService {
    fun save(word: String)
}

@Service
class UserServiceImpl : UserService {

    override fun save(word: String) {
        println("hello world! $word")
    }
}

@Component
class SpringContext : ApplicationContextAware {

    @Throws(BeansException::class)
    override fun setApplicationContext(context: ApplicationContext) {

        // store ApplicationContext reference to access required beans later on
        SpringContext.context = context
    }

    companion object {

        private var context: ApplicationContext? = null

        /**
         * Returns the Spring managed bean instance of the given class type if it exists.
         * Returns null otherwise.
         * @param beanClass
         * @return
         */
        fun <T : Any> getBean(beanClass: Class<T>): T {
            println("getBean")
            return context!!.getBean(beanClass)
        }
    }
}