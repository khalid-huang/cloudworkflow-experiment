package activitiadmissiondatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import activitidatabase.process._

class SchedulerDataCollectionSimulation extends 
Simulation {
	val httpConf = http
		.baseUrl("http://192.168.0.39:8763/")

	def process_auto_onlineShoppingModel() {
		var auto_onlineShopping = scenario("auto online shopping").exec(Process_AddPD_OnlineShoppingModel.workflow)

		setUp(
			auto_onlineShopping.inject(
				constantUsersPerSec(12) during (5 minutes))
		).throttle(
			reachRps(20) in (10 seconds),
			holdFor(1.5 minutes),
			reachRps(60) in (10 seconds),
			holdFor(1.5 minutes),
			reachRps(80) in (10 seconds),
			holdFor(1.5 minutes),
			reachRps(100) in (10 seconds),
			holdFor(1.5 minutes),
			reachRps(80) in (10 seconds),   //这组负载是为了保证运行完后续的所有请求而设置的
			holdFor(5 minutes)
		).protocols(httpConf)



/**		setUp(
			auto_onlineShopping.inject(
				constantUsersPerSec(12) during (2 minutes))
		).protocols(httpConf)
*/

		// setUp(auto_onlineShopping.inject(atOnceUsers(1)).protocols(httpConf))
	}


	process_auto_onlineShoppingModel()

}
