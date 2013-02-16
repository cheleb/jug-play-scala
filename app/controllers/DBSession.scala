package controllers

import scala.slick.session.Database
import play.api.db.DB
import play.api.Play.current
import play.api.mvc.SimpleResult
import play.api.mvc.Request
import scala.slick.driver.BasicDriver.Implicit._
import Database.threadLocalSession
import scala.slick.session.Session
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api._

trait DBSession {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def withSession[A](f: => SimpleResult[A]): SimpleResult[A] = {
    database.withSession {
      f
    }

  }

  def withSession[A](f: => A): A = {
    database.withSession {
      f
    }

  }

}