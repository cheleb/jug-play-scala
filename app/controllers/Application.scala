package controllers

import play.api._
import play.api.mvc._
import models.Event
import models.Speaker

object Application extends Controller {
  
  
  def main = views.html.main("JUG de Montpellier") _
  
  
  def index = Action {
    Ok(views.html.index("JUG de Montpellier."))
  }
 
  def news() = Action {
    Ok("News")
  }
  
  def event(nextEventId: Int) = Action {
    Ok("Event: " + nextEventId)
  }
  
  def about = Action {
    Ok(views.html.main("ss")(views.html.about()))
  }
  def members = Action {
    Ok("Members")
  }
  
  def member(id: Int) = Action {
    Ok("Member: " + id)
  }
  
  def polls = Action {
    Ok("Polls")
  }
  
  def speakers = Action {
    Ok("Speakers")
  }
  
  implicit def event :  Option[Event] = {
   // None
    Some(Event(2, "bidon", "test test", List(Speaker(1, "olivier.nouguier@gmail.com", "Olivier NOUGUIER","http://www.agilent.com","Architecte","Agilent Technolgies, Inc"))))
  } 
  
}