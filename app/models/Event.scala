package models


case class Speaker(id: Int, email: String, fullname: String,url: String, activity: String, company: String)

case class Event(id: Int, label: String, description: String, speakers: List[Speaker]) {

}