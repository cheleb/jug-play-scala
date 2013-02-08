package controllers

import play.api._
import play.api.mvc._
import play.api.templates.Html
import views.html._
import models.Event
import models.Speaker
import scala.slick.session.Database
import play.api.db.DB

import play.api.Play.current

trait MainAction extends Controller {
 
  lazy val database = Database.forDataSource(DB.getDataSource())
  
  def mainAction(c: Html): Action[AnyContent] = Action {
    Ok(views.html.main("JUG de Montpellier - play 2.1")(c))
  }
 
  implicit def event: Option[Event] = {
    None
    Some(Event(2, "bidon", "test test", List(Speaker(Some(1), "Olivier NOUGUIER", "olivier.nouguier@gmail.com", "zozo", "Architect", "Agilent", true, "http://www.agilent.com", "Architecte", "Agilent Technolgies, Inc"))))
  }

}