package br.com.starwars.challenge

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

object AppServerMain extends AppServer

class AppServer extends HttpServer {

  override val defaultFinatraHttpPort: String = ":8080"

  override def configureHttp(router: HttpRouter) {
    router.add[ApiController]
  }
}

class ApiController extends Controller {
  get("/") { request: Request =>
    "hello world"
  }
}