package controllers

import play.api.templates.Html
import views.html._
import models.Speakers
import models.Events
import models.Eventpartner
import models.Eventpartners
import play.api.mvc.Action

object Application extends MainAction {

  def index = mainDBAction {
    views.html.home(Events.last(3))
  }

  def news() = mainAction {
    Html("News")
  }

  def event(id: Long) = mainDBActionWithJS(List("EventBrite.jquery.js")) {
    val event = Events.getById(id).get
    views.html.event(event, Events.pastAndUpComing)
  }

  def about = mainAction {
    views.html.about()
  }

  def members = mainDBAction {
    views.html.members(Speakers.all)
  }

  def member(id: Long) = mainAction {
    Html("Member: " + id)
  }

  def polls = mainAction {
    Html("Polls")
  }

  def partners = mainAction {
    Html("Partners")
  }

  def partner(id: Long) = mainAction {
    Html("Partner id: " + id)
  }

  def speakers = mainDBAction {
    views.html.speakers(Speakers.all)
  }

}
