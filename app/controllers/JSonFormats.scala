package controllers

import play.api.libs.json._

import models._

trait JSonFormats {

  implicit val creatureReads = Json.format[Speaker]
  
}