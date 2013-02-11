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

  def mainAction(c: Html): Action[AnyContent] = Action {
    Ok(views.html.main("JUG de Montpellier - play 2.1")(c))
  }

  implicit def event: Option[(EventViewObject)] = {

    database.withSession {

      val q = for {
        e <- Events if e.open
        t <- Talks if t.event_id === e.id
        s <- Speakers if s.id === t.speaker_id

      } yield (e, t, s)

      val list = q.list
      val map = list.groupBy(t => t._1)

      val r = map map { case (k, v) => EventViewObject(k, v map (_._2), v map (_._3)) }

      r match {
        case h :: _ => Some(h)
        case _ => None
      }
    }

  }
  
  implicit def yearPartners : List[Yearpartner] = {
    database.withSession {
      Query(Yearpartners).list
    }
  }

}