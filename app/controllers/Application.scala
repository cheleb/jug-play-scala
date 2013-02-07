package controllers

import play.api._
import play.api.mvc._
import models.Event
import models.Speaker
import play.api.templates.Html
import views.html._
import scala.slick.session.Database
import views.html.speakers.list
import models.Speakers
import play.api.db.DB

import play.api.Play.current

object Application extends Controller {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def MainOk(c: Html): SimpleResult[Html] = Ok(views.html.main("JUG de Montpellier - play 2.1")(c))

  def index = Action {
    MainOk(welcome())
  }

  def news() = Action {
    MainOk(Html("News"))
  }

  def event(nextEventId: Int) = Action {
    MainOk(Html("Event: " + nextEventId))
  }

  def about = Action {
    MainOk(views.html.about())
  }
  def members = Action {
    MainOk(Html("Members"))
  }

  def member(id: Int) = Action {
    MainOk(Html("Member: " + id))
  }

  def polls = Action {
    MainOk(Html("Polls"))
  }

  def speakers = Action {
    database.withSession {
      MainOk(views.html.speakers.list(Speakers.all))
    }
  }

  implicit def event: Option[Event] = {
    None
    Some(Event(2, "bidon", "test test", List(Speaker(Some(1), "Olivier NOUGUIER", "olivier.nouguier@gmail.com", "zozo", "Architect", "Agilent", true, "http://www.agilent.com", "Architecte", "Agilent Technolgies, Inc"))))
  }

}