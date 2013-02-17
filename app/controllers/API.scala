package controllers

import play.api.mvc._
import play.api.libs.json._
import models._

object API extends Controller with DBSession with JSonFormats {

  def speakers() = Action {
    withSession {
      Ok(Json.toJson(Speakers.all()))
    }
  }

  def events() = Action {
    withSession {
      Ok(Json.toJson(Events.all))
    }
  }

  def yearPartners = Action {
    withSession {
      Ok(Json.toJson(Yearpartners.all))
    }
  }
}