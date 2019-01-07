package activitidatabase.process

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object Process_AddPD_OnlineShoppingModel {


    var contentType = Map("Content-Type" -> "application/x-www-form-urlencoded")

    //由外部赋值，里面包含了processDefinitionId，每次取10个；需要修改下
    var feeder = csv("ACT_RE_PROCDEF.csv").circular 
    // var feeder0 = csv("ACT_RE_PROCDEF_0.csv").circular 
    // var feeder1 = csv("ACT_RE_PROCDEF_1.csv").circular 
    // var feeder2 = csv("ACT_RE_PROCDEF_2.csv").circular 
    // var feeder3 = csv("ACT_RE_PROCDEF_3.csv").circular 
    // var feeder4 = csv("ACT_RE_PROCDEF_4.csv").circular 
    // var feeder5 = csv("ACT_RE_PROCDEF_5.csv").circular 
    // var feeder6 = csv("ACT_RE_PROCDEF_6.csv").circular 
    // var feeder7 = csv("ACT_RE_PROCDEF_7.csv").circular 
    // var feeder8 = csv("ACT_RE_PROCDEF_8.csv").circular 
    // var feeder9 = csv("ACT_RE_PROCDEF_9.csv").circular 

    var workflow = feed(feeder)
        .exec(http("startProcessInstanceById")
            .post("/startProcessInstanceById/${processDefinitionId}")
            .headers(contentType)
            .check(jsonPath("$..processInstanceId").saveAs("processInstanceId")))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .exec(http("completeTask")   //choose-goods task
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .exec(http("completeTask")   //pay task
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .exec(http("completeTask")    //send goods
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .exec(http("completeTask")  //receive goods 
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
}