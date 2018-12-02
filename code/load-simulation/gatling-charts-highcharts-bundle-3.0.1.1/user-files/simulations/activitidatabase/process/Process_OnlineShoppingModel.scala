package activitidatabase.process

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object Process_OnlineShoppingModel {
    var contentType = Map("Content-Type" -> "application/x-www-form-urlencoded")
    var workflow = exec(http("startProcess")
            .post("/startProcess/online-shopping")
            .headers(contentType)
            .check(jsonPath("$..processInstanceId").saveAs("processInstanceId")))
        .pause(1)
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1)
        .exec(http("completeTask")
            .post("completeTask/${processInstanceId}/${taskId}")
            .headers(contentType))
        .pause(1)
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1)
        .exec(http("completeTask")
            .post("completeTask/${processInstanceId}/${taskId}")
            .headers(contentType))
        .pause(1)        
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1)
        .exec(http("completeTask")
            .post("completeTask/${processInstanceId}/${taskId}")
            .headers(contentType))
        .pause(1)
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1)
        .exec(http("completeTask")
            .post("completeTask/${processInstanceId}/${taskId}")
            .headers(contentType))
        .pause(1)   //流程结束
}