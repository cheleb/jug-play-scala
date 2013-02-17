package controllers

import play.api.libs.json._
import models._
import java.sql.Timestamp

trait JSonFormats {

  implicit val speakerReads = Json.format[Speaker]

  implicit val formatTimestamp = new Format[Timestamp] {
    def writes(ts: Timestamp): JsValue = JsString(ts.toString())
    def reads(ts: JsValue): JsResult[Timestamp] = { 
      try {
        JsSuccess(Timestamp.valueOf(ts.as[String]))
      } catch {
        case e: IllegalArgumentException => JsError("Unable to parse timestamp")
      }
    }
  }

  implicit val eventReads = Json.format[Event]
  
  implicit val yearpartnerReads = Json.format[Yearpartner]
  
  

}