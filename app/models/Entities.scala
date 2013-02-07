package models

import scala.slick.driver.BasicDriver.simple._

import Database.threadLocalSession

case class User(id: Option[Int], name: String)

trait UserComponent {

  object Users extends Table[User]("users") {
    def id = column[Int]("id", O.PrimaryKey)
    def name = column[String]("name", O.NotNull)
    def * = id.? ~ name <> (User, User.unapply _)

  }
}

case class Speaker(id: Option[Int], fullname: String, email: String, description: String, activity: String, company: String, jugmember: Boolean, memberFct: String, photoUrl: String, url: String)

case class Event(id: Int, label: String, description: String, speakers: List[Speaker]) {

}

object Speakers extends Table[Speaker]("speaker") {
  def id = column[Int]("id", O.PrimaryKey) // This is the primary key column
  def fullname = column[String]("fullname")
  def email = column[String]("email")
  def description = column[String]("description")
  def activity = column[String]("activity")
  def company = column[String]("compan")
  def jugmember = column[Boolean]("jugmember")
  def memberFct = column[String]("memberfct")
  def photoUrl = column[String]("photourl")
  def url = column[String]("url")

  def * = id.? ~ fullname ~ email ~ description ~ activity ~ company ~ jugmember ~ memberFct ~ photoUrl ~ url <> (Speaker, Speaker.unapply _)

  def all = {
    val q = for {
      c <- Speakers
    } yield (c)

    q.list
  }
}

