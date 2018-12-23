package activitidatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

import activitidatabase.process._

class RamTestSimulation extends Simulation {
    val httpConf = http.baseUrl("http://222.200.180.59:8760/")

    调用Process_AddPD_OnlineShoppingModel.scala，每次增加10个不同的流程定义文件到引擎上
    def addProcessDefintionToEngine() {
        //需要先修改下Process_AddPD_onlineshoppingModel里面的slice的参数
        var addPD_onlineshopping = scenario("add PD online shopping").exec(Process_AddPD_OnlineShoppingModel.workflow)
        setUp(addPD_onlineshopping.inject(rampUsers(10) during (10 seconds)).protocols(httpConf))
    }

    //用一个并发实例，测试在当前RAM状态下的引擎的处理性能
    def testResponseTime() {
        var onlineshopping = scenario("online process").exec(Process_OnlineShoppingModel.workflow)
		setUp(onlineshopping.inject(atOnceUsers(20)).protocols(httpConf))
    }

    // addProcessDefintionToEngine();

    testResponseTime()
}
