package models

import java.io._
import java.security._
import controllers.routes

case class TalkViewObject(talk: Talk, speakers: List[Speaker], tags: List[Tag])
case class EventViewObject(event: Event, talks: List[TalkViewObject], partner: Option[Eventpartner])

object gravatar {
  private def hex(array: Array[Byte]) = {
    val sb = new StringBuffer()
    for (i <- 0 to array.length - 1)
      sb.append(Integer.toHexString((array(i)
        & 0xFF) | 0x100).substring(1, 3));

    sb.toString();
  }

  private def md5Hex(message: String) = {
    try {
      val md =
        MessageDigest.getInstance("MD5");
      hex(md.digest(message.getBytes("UTF-8")));
    } catch {
      case e: NoSuchAlgorithmException => "nop"
      case e: UnsupportedEncodingException => "nop"
    }
  }

  def url(speaker: Speaker) = {
    speaker.email match {
      case Some(e) => "http://www.gravatar.com/avatar/" + md5Hex(e)
      case _ => routes.Assets.at("images/none.jpg")
    }
  }
}

object eventBrite {

  val PREFIX = "http://www.eventbrite.fr/event/"

  /**
   *
   * @param url
   * @return
   */
  def getId(event: Event) = {

    event.registrationurl.map(url => if (url.startsWith(PREFIX))
      url.substring(
      PREFIX.length(),
      url.length())
    else
      url)

  }

  /**
   *
   * @param event
   * @return
   */
  def isEventBrite(event: Event) = {
    event.registrationurl.map(url => url.startsWith(PREFIX)).getOrElse(false)
  }
}
