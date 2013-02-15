package controllers

import play.api._
import play.api.mvc._
import play.api.templates.Html
import views.html._
import models._

import scala.slick.session.Database
import play.api.db.DB
import play.api.Play.current
import java.sql.Timestamp

import scala.slick.lifted.Query
import models.Speakers
import scala.slick.driver.BasicDriver.Implicit._
import Database.threadLocalSession
import scala.slick.session.Session
import models.Talks
import models.Talk

trait MainAction extends Controller with DBSession {

  val title = "JUG de Montpellier - play 2.1"

  def mainPage(c: Html): Html =
    views.html.main(title)(List())(c)

  def mainDBActionWithJS(js: List[String])(c: => Html): Action[AnyContent] = Action {
    withSession {
      Ok(views.html.main(title)(js)(c))
    }
  }

  def mainDBAction(c: => Html): Action[AnyContent] =
    mainDBActionWithJS(List())(c)

  def mainAction(c: Html): Action[AnyContent] =
    mainActionWithJS(List())(c)

  def mainActionWithJS(script: List[String])(c: Html): Action[AnyContent] = Action {
    Ok(views.html.main(title)(script)(c))
  }

  implicit def event: Option[(EventViewObject)] = withSession {
    Events.getOpened
  }

  implicit def yearPartners: List[Yearpartner] = withSession {
    Yearpartners.runnings
  }

}
