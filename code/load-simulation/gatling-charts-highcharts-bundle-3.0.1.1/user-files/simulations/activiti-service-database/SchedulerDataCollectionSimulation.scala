package activitiadmissiondatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import activitidatabase.process._

class SchedulerDataCollectionSimulation extends 
Simulation {
	val httpConf = http
		.baseUrl("http://222.200.180.59:8763/")

	def process_auto_onlineShoppingModel() {
		var auto_onlineShopping = scenario("auto online shopping").exec(Process_AddPD_OnlineShoppingModel.workflow)

		// setUp(
		// 	auto_onlineShopping.inject(
		// 		rampUsers(1200) during (3 minutes))
		// ).throttle(
		// 	reachRps(60) in (30 seconds),
		// 	holdFor(2 minute)
		// ).protocols(httpConf)



/**		setUp(
			auto_onlineShopping.inject(
				constantUsersPerSec(12) during (2 minutes))
		).protocols(httpConf)
*/

		setUp(auto_onlineShopping.inject(atOnceUsers(1)).protocols(httpConf))
	}


	process_auto_onlineShoppingModel()

}
