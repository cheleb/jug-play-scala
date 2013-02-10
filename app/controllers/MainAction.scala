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
import java.sql.Timestamp
import models.Events
import scala.slick.lifted.Query
import models.Speakers
import scala.slick.driver.BasicDriver.Implicit._
import Database.threadLocalSession
import scala.slick.session.Session
import models.Talks
import models.Talk

trait MainAction extends Controller {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def mainAction(c: Html): Action[AnyContent] = Action {
    Ok(views.html.main("JUG de Montpellier - play 2.1")(c))
  }

  implicit def event: Option[(Event,Talk, Speaker)] = {

    database.withSession {

      val q = for {
        e <- Events if e.open
        t <- Talks
        s <- Speakers

      } yield (e, t, s)

      
      
      val list = q.list
      val ret = list match {
        case h :: _ => Some(h)
        case _ => None
      }
      
      
      
      
      ret
    }

  }

}