package controllers

import play.api._
import play.api.mvc._
import models.Event
import models.Speaker
import play.api.templates.Html


object Application extends Controller {
  
  
  def MainOk(c: Html) : SimpleResult[Html] = Ok(views.html.main("JUG de Montpellier")(c))
  
  
  
  def index = Action {
    MainOk(Html("JUG de Montpellier."))
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
    MainOk(Html("Speakers"))
  }
  
  implicit def event :  Option[Event] = {
    //None
    Some(Event(2, "bidon", "test test", List(Speaker(1, "olivier.nouguier@gmail.com", "Olivier NOUGUIER","http://www.agilent.com","Architecte","Agilent Technolgies, Inc"))))
  } 
  
}