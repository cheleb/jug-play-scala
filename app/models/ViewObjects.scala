package models

case class EventViewObject(event: Event, talks: List[Talk], speakers: List[Speaker])