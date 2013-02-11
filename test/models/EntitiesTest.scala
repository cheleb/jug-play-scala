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
      s <- Speakers if s.id === t.speaker_id
    } yield(e,t,s)
    
    
    
    println(q.invoker.selectStatement)
    println(q.list)
    
    val list = q.list
    val map = list.groupBy( t => t._1 )
    
    val r = map map { case (k,v) => EventViewObject(k, v map (_._2), v map ( _._3 ) ) }
    
    println(r)
    
  }

 
}