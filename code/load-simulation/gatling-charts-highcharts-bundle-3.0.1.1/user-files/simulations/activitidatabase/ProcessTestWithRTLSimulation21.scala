package activitiadmissiondatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import activitiadmissiondatabase.process._

class ProcessTestWithRTLSimulation21 extends 
Simulation {
	val httpConf = http
		.baseUrl("http://222.200.180.59:8763/")

	def process_auto_onlineShoppingModel2() {
		var auto_onlineShopping = scenario("auto online shopping2").exec(Process_Auto_OnlineShoppingModel21.workflow)

		// setUp(
		// 	auto_onlineShopping.inject(
		// 		rampUsers(1200) during (3 minutes))
		// ).throttle(
		// 	reachRps(60) in (30 seconds),
		// 	holdFor(2 minute)
		// ).protocols(httpConf)



		setUp(
			auto_onlineShopping.inject(
				constantUsersPerSec(5) during (2 minutes))
		).protocols(httpConf)


		//setUp(auto_onlineShopping.inject(atOnceUsers(1)).protocols(httpConf))
	}


	// basictest()
	// process_leavemodel_pass()
	// process_leavemodel_notpass()
	// process_onlineshoppingmodel()
	process_auto_onlineShoppingModel2()

}