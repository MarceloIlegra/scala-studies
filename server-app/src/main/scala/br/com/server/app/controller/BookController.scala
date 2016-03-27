package br.com.server.app.controller

import br.com.server.app.model.BookRequest
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class BookController extends Controller{

  post("/books") { request: BookRequest =>
    "Book: " + request.id + " - " + request.title
  }

  get("/books") { request: Request =>
    List(new BookRequest(1, "The Shinning"), new BookRequest(2, "It"), new BookRequest(1, "Under the Domme"))
  }

}