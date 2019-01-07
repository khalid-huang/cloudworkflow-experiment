package activitidatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

import activitidatabase.process._

class RamTestSimulation extends Simulation {
    // val httpConf = http.baseUrl("http://222.200.180.59:8760/")
    val httpConf = http.baseUrl("http://localhost:8763/")

    //调用Process_AddPD_OnlineShoppingModel.scala，每次增加10个不同的流程定义文件到引擎上
    def addProcessDefintionToEngine() {
        //需要先修改下Process_AddPD_onlineshoppingModel里面的slice的参数
        var addPD_onlineshopping = scenario("add PD online shopping").exec(Process_AddPD_OnlineShoppingModel.workflow)
        setUp(addPD_onlineshopping.inject(rampUsers(1000) during (100 seconds)).protocols(httpConf))
    }

        //调用Process_AddPD_OnlineShoppingModel.scala，每次增加10个不同的流程定义文件到引擎上
    def addProcessDefintionToEngine1() {
        //需要先修改下Process_AddPD_onlineshoppingModel里面的slice的参数
        var addPD_onlineshopping = scenario("add PD leave").exec(Process_AddPD_LeaveModel_PASS.workflow)
        setUp(addPD_onlineshopping.inject(rampUsers(1000) during (100 seconds)).protocols(httpConf))
    }

    //用一个并发实例，测试在当前RAM状态下的引擎的处理性能
    //这个测试用例不要引入多余的流程进来，也就是单个部署器部署的
    def testResponseTime() {
        var onlineshopping = scenario("online process").exec(Process_OnlineShoppingModel.workflow)
		// setUp(onlineshopping.inject(atOnceUsers(20)).protocols(httpConf))
		setUp(onlineshopping.inject(rampUsers(10) during (2 seconds)).protocols(httpConf))
    }

    def testLeave() {
        var onlineshopping = scenario("leave process").exec(Process_LeaveModel_PASS.workflow)
		// setUp(onlineshopping.inject(atOnceUsers(20)).protocols(httpConf))
		setUp(onlineshopping.inject(atOnceUsers(1)).protocols(httpConf))        
    }

    addProcessDefintionToEngine();
    // addProcessDefintionToEngine1();
    // testLeave()
    // testResponseTime()
}
