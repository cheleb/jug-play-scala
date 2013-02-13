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

trait MainAction extends Controller {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def mainActionW(script: List[String])(c: Html): Action[AnyContent] = Action {
    Ok(views.html.main("JUG de Montpellier - play 2.1")(script)(c))
  }

  def mainAction(c: Html): Action[AnyContent] = Action {
    Ok(views.html.main("JUG de Montpellier - play 2.1")(List())(c))
  }


  implicit def event: Option[(EventViewObject)] = {

    database.withSession {

      Events.getOpened
     
    }

  }
  
  implicit def yearPartners : List[Yearpartner] = {
    database.withSession {
      Yearpartners.runnings
    }
  }

}
