package models

import scala.slick.session.Database
import scala.slick.driver.BasicDriver.Implicit._
import Database.threadLocalSession
import play.api.db.DB
import scala.slick.session.Session


object EntitiesTest extends App {
  Database.forURL("jdbc:postgresql:jug", "test", "test", driver = "org.postgresql.Driver") withSession {
    println("Speakers:")
    val v = Talks.all
    println(v)
    v.foreach(t=>t.speaker_id)

    
    val q = for {
      e <- Events if e.open
      t <- Talks if e.id === t.event_id
    } yield(t)
    
    
    
    println(q.invoker.selectStatement)
    println(q.list)

  }

 
}