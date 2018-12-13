package activitiadmissiondatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

import activitiadmissiondatabase.process._

class AdmisstionLoadWithRTLSimulation extends Simulation {
    var httpConf = http.baseUrl("http://localhost:8771/")

// 这种情况下start占了complete的1/3
    def admissionLayerWithThreeModel() {
        var onlineShopping = scenario("online shopping").exec(Process_OnlineShoppingModel.workflow)
        var autoOnlineShopping = scenario("auto online shopping").exec(Process_Auto_OnlineShoppingModel.workflow)
        var leavePass = scenario("leave pass").exec(Process_LeaveModel_PASS.workflow)

//adimissionlayersimulation-20181205031058757/index.html
// auto_onlineShopping作为自动流程，表示一直会进行的流程；占少数
// leavePass作为人工流程，表示一直会进行的流程
//上面两个流程提供一个基本的波形
// 由onlineShopping来提供一个波峰
        setUp(
            autoOnlineShopping.inject(
                constantUsersPerSec(2) during(60 seconds)
            )
            ,
            leavePass.inject(
                constantUsersPerSec(3) during (60 seconds)
            ),
            onlineShopping.inject(
                nothingFor(5 seconds),
                rampUsers(10) during (5 seconds),
                nothingFor(2 seconds),
                atOnceUsers(20),
                rampUsers(5) during (5 seconds),
                nothingFor(2 seconds),
                atOnceUsers(30),
                rampUsers(20) during (5 seconds),
                nothingFor(5 seconds),
                rampUsers(15) during (5 seconds),
                nothingFor(5 seconds),
                atOnceUsers(15)
            )
        ).protocols(httpConf)
    }

    def admissionLayerWithThreeModel1() {
        var autoOnlineShopping = scenario("auto online shopping").exec(Process_Auto_OnlineShoppingModel.workflow)
        var leavePass = scenario("leave pass").exec(Process_LeaveModel_PASS.workflow)
		var leaveNotPass = scenario("leave no pass").exec(Process_LeaveModel_NOTPASS_N.workflow)

//adimissionlayersimulation-20181205031058757/index.html
// auto_onlineShopping作为自动流程，表示一直会进行的流程；占少数
// leavePass作为人工流程，表示一直会进行的流程
//上面两个流程提供一个基本的波形
// 由onlineShopping来提供一个波峰
        setUp(
            autoOnlineShopping.inject(
                constantUsersPerSec(2) during(60 seconds)
            )
            ,
            leavePass.inject(
                constantUsersPerSec(2) during (60 seconds)
            ),
            leaveNotPass.inject(
                nothingFor(5 seconds),
                atOnceUsers(20),
                rampUsers(10) during (5 seconds),
                nothingFor(2 seconds),
                atOnceUsers(20),
                rampUsers(5) during (5 seconds),
                nothingFor(2 seconds),
                atOnceUsers(30),
                rampUsers(20) during (5 seconds),
                nothingFor(5 seconds),
                rampUsers(15) during (5 seconds),
                nothingFor(5 seconds),
                atOnceUsers(15)
            )
        ).protocols(httpConf)
    }

// 这中情况下start的数量是complete的1/3左右，不大现实
    // admissionLayerWithThreeModel()
    //这种比较好
    admissionLayerWithThreeModel1()





}