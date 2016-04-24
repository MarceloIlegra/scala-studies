package br.com.starwars.challenge

import br.com.starwars.challenge.model.RescueRequest
import br.com.starwars.challenge.services.ApiService
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

  get("/rescue") { request: Request =>
    val idVehicle = request.params.get("idVehicle").get.toInt
    val people = request.params.get("people").get

    val service = new ApiService
    service.exec(idVehicle, people)
  }

}