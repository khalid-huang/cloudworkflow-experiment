package activitiadmissiondatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import activitiadmissiondatabase.process._

class ProcessTestWithRTLSimulation extends 
Simulation {
	val httpConf = http
		.baseUrl("http://222.200.180.59:8771/")
	
	def basictest() {
		var contentType = Map("Content-Type" -> "application/x-www-form-urlencoded")
		var scn = scenario("basic activiti scenario")
			.exec { session =>
				session.set("processDefinitionId", "leave:9:52536")
			}
			.exec(http("startProcessInstanceById")
				.post("/startProcessInstanceById/${processDefinitionId}")
				.headers(contentType)
				.formParam("apply", "zhangsan")
				.formParam("approve", "lisi")
				.check(jsonPath("$..processInstanceId").saveAs("processInstanceId")))
			.pause(3)
			.exec(http("getCurrentSingleTask")
				.get("getCurrentSingleTask/${processInstanceId}")
				.check(jsonPath("$..taskId").saveAs("taskId")))
			.pause(1)
			.exec {session => 
				println(session)
				session
			}
		setUp(scn.inject(atOnceUsers(1)).protocols(httpConf))
	}

	def process_leavemodel_pass() {
		var leavePass = scenario("leave pass").exec(Process_LeaveModel_PASS.workflow)
		setUp(leavePass.inject(atOnceUsers(1)).protocols(httpConf))
	}

	def process_leavemodel_notpass() {
		var leaveNotPass = scenario("leave no pass").exec(Process_LeaveModel_NOTPASS_N.workflow)
		setUp(leaveNotPass.inject(atOnceUsers(1)).protocols(httpConf))
	}

	def process_onlineshoppingmodel() {
		var onlineshopping = scenario("online shopping").exec(Process_OnlineShoppingModel.workflow)
		setUp(onlineshopping.inject(atOnceUsers(1)).protocols(httpConf))
	}

	def process_auto_onlineShoppingModel() {
		var auto_onlineShopping = scenario("auto online shopping").exec(Process_Auto_OnlineShoppingModel.workflow)
		setUp(
			auto_onlineShopping.inject(
				rampUsers(10) during (5 seconds),
				nothingFor(5 seconds),
                atOnceUsers(11),
                rampUsers(8) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(8),
                rampUsers(8) during (5 seconds),
                nothingFor(15 seconds),
                atOnceUsers(10),
                rampUsers(10) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(9),
                nothingFor(5 seconds), //60s
                // nothingFor(5 seconds), 
                atOnceUsers(11),
                rampUsers(8) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(8),
                rampUsers(8) during (5 seconds),
                nothingFor(15 seconds),
                atOnceUsers(10),
                rampUsers(10) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(9),
                nothingFor(5 seconds),
                // nothingFor(5 seconds), 
                atOnceUsers(11),
                rampUsers(8) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(8),
                rampUsers(8) during (5 seconds),
                nothingFor(15 seconds),
                atOnceUsers(10),
                rampUsers(10) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(9),
                nothingFor(5 seconds),
                // nothingFor(5 seconds), 
                atOnceUsers(11),
                rampUsers(8) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(8),
                rampUsers(8) during (5 seconds),
                nothingFor(15 seconds),
                atOnceUsers(10),
                rampUsers(10) during (5 seconds),
                nothingFor(10 seconds),
                atOnceUsers(9),
                nothingFor(5 seconds)

		).protocols(httpConf))
	}


	// basictest()
	// process_leavemodel_pass()
	// process_leavemodel_notpass()
	// process_onlineshoppingmodel()
	process_auto_onlineShoppingModel()

}