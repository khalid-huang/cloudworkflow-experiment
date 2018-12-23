package activitidatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

import activitidatabase.process._

class ThroughtSimulation extends Simulation {
    val httpConf = http
        .baseUrl("http://localhost:8763/")

    //从一段时间里面的启动的流程实例数进行控制；
    def throughtoutWithOneModel() {
		var onlineshopping = scenario("online shopping").exec(Process_OnlineShoppingModel.workflow)
		setUp(onlineshopping.inject(rampUsers(20) during(10 seconds)).protocols(httpConf))
		// setUp(onlineshopping.inject(rampUsers(30) during(30 seconds)).protocols(httpConf))
		// setUp(onlineshopping.inject(rampUsers(30) during(30 seconds)).protocols(httpConf))
    }
    //使用constantUsersPerSec 设置每秒钟到达的流程实例数

    //从请求数上进行控制
    throughtoutWithOneModel()
}
