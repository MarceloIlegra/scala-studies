
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter


class MyController extends Controller {

  get("/hi") { request: Request =>
    info("hi")
    "Hello " + request.params.getOrElse("name", "unnamed")
  }

}

object AppServerMain extends AppServer

class AppServer extends HttpServer {

  override val defaultFinatraHttpPort: String = ":8080"

  override def configureHttp(router: HttpRouter) {
    router.add[MyController]
  }
}