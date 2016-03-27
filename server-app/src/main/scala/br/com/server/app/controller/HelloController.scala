package br.com.server.app.controller

import br.com.server.app.model.BookRequest
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class HelloController extends Controller{

  get("/hi") { request: Request =>
    info("hi")
    "Hello " + request.params.getOrElse("name", "unnamed")
  }

  post("/book") { request: BookRequest =>
    "Book: " + request.id + " - " + request.title
  }

}