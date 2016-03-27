package br.com.server.app

import br.com.server.app.controller.HelloController
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer

object AppServerMain extends AppServer

class AppServer extends HttpServer {

  override val defaultFinatraHttpPort: String = ":8080"

  override def configureHttp(router: HttpRouter) {
    router.add[HelloController]
  }
}