package codacy.pylint

import play.api.libs.json.Json

private[pylint] case class PylintResult(message:String,obj:String,column:Int,path:String,line:Int,`type`:String,symbol: String, module:String)
private[pylint] object PylintResult{ implicit lazy val reads = Json.reads[PylintResult]}
