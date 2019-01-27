package activitidatabase.process

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object Process_AddPD_OnlineShoppingModel {


    var contentType = Map("Content-Type" -> "application/x-www-form-urlencoded")

    var feeder1 = csv("act_re_procdef_1.csv").circular  //包含1个话中实例定义
    var feeder20 = csv("act_re_procdef_20.csv").circular //包含20个流程实例定义 
    var feeder30 = csv("act_re_procdef_30.csv").circular //包含20个流程实例定义 
    var feeder = csv("act_re_procdef.csv").circular

    var workflow = feed(feeder)
		.exec(http("startProcessInstanceById")
            .post("/startProcessInstanceById/${processDefinitionId}")
            .headers(contentType)
			.check(jsonPath("$..processInstanceId").saveAs("processInstanceId")))
 		.exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(2) //模拟外部服务的任务处理时间
        .exec(http("completeTask")   //choose-goods task
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1) //模拟外部服务的任务处理时间
        .exec(http("completeTask")   //pay task
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(2) //模拟外部服务的任务处理时间
        .exec(http("completeTask")    //send goods
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
       .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1) //模拟外部服务的任务处理时间
        .exec(http("completeTask")  //receive goods 
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType))
}