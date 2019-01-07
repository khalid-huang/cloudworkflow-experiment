package activitiadmissiondatabase.process

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import io.gatling.core.feeder._

object Process_Auto_OnlineShoppingModel2 {
    var contentType = Map("Content-Type" -> "application/x-www-form-urlencoded")

    val mapFeeder = Array(
    	Map("processDefinitionId" -> "online-shopping:100:400"),
		Map("processDefinitionId" -> "online-shopping:10:40"),
		Map("processDefinitionId" -> "online-shopping:11:44"),
		Map("processDefinitionId" -> "online-shopping:12:48"),
		Map("processDefinitionId" -> "online-shopping:13:52"),
		Map("processDefinitionId" -> "online-shopping:14:56"),
		Map("processDefinitionId" -> "online-shopping:15:60"),
		Map("processDefinitionId" -> "online-shopping:16:64"),
		Map("processDefinitionId" -> "online-shopping:17:68"),
		Map("processDefinitionId" -> "online-shopping:18:72"),
		Map("processDefinitionId" -> "online-shopping:19:76"),
		Map("processDefinitionId" -> "online-shopping:1:4"),
		Map("processDefinitionId" -> "online-shopping:20:80"),
		Map("processDefinitionId" -> "online-shopping:21:84"),
		Map("processDefinitionId" -> "online-shopping:22:88"),
		Map("processDefinitionId" -> "online-shopping:23:92"),
		Map("processDefinitionId" -> "online-shopping:24:96"),
		Map("processDefinitionId" -> "online-shopping:25:100"),
		Map("processDefinitionId" -> "online-shopping:26:104"),
		Map("processDefinitionId" -> "online-shopping:27:108"),
		Map("processDefinitionId" -> "online-shopping:28:112"),
		Map("processDefinitionId" -> "online-shopping:29:116"),
		Map("processDefinitionId" -> "online-shopping:2:8"),
		Map("processDefinitionId" -> "online-shopping:30:120"),
		Map("processDefinitionId" -> "online-shopping:31:124"),
		Map("processDefinitionId" -> "online-shopping:32:128"),
		Map("processDefinitionId" -> "online-shopping:33:132"),
		Map("processDefinitionId" -> "online-shopping:34:136"),
		Map("processDefinitionId" -> "online-shopping:35:140"),
		Map("processDefinitionId" -> "online-shopping:36:144"),
		Map("processDefinitionId" -> "online-shopping:37:148"),
		Map("processDefinitionId" -> "online-shopping:38:152"),
		Map("processDefinitionId" -> "online-shopping:39:156"),
		Map("processDefinitionId" -> "online-shopping:3:12"),
		Map("processDefinitionId" -> "online-shopping:40:160"),
		Map("processDefinitionId" -> "online-shopping:41:164"),
		Map("processDefinitionId" -> "online-shopping:42:168"),
		Map("processDefinitionId" -> "online-shopping:43:172"),
		Map("processDefinitionId" -> "online-shopping:44:176"),
		Map("processDefinitionId" -> "online-shopping:45:180"),
		Map("processDefinitionId" -> "online-shopping:46:184"),
		Map("processDefinitionId" -> "online-shopping:47:188"),
		Map("processDefinitionId" -> "online-shopping:48:192"),
		Map("processDefinitionId" -> "online-shopping:49:196"),
		Map("processDefinitionId" -> "online-shopping:4:16"),
		Map("processDefinitionId" -> "online-shopping:50:200"),
		Map("processDefinitionId" -> "online-shopping:51:204"),
		Map("processDefinitionId" -> "online-shopping:52:208"),
		Map("processDefinitionId" -> "online-shopping:53:212"),
		Map("processDefinitionId" -> "online-shopping:54:216"),
		Map("processDefinitionId" -> "online-shopping:55:220"),
		Map("processDefinitionId" -> "online-shopping:56:224"),
		Map("processDefinitionId" -> "online-shopping:57:228"),
		Map("processDefinitionId" -> "online-shopping:58:232"),
		Map("processDefinitionId" -> "online-shopping:59:236"),
		Map("processDefinitionId" -> "online-shopping:5:20"),
		Map("processDefinitionId" -> "online-shopping:60:240"),
		Map("processDefinitionId" -> "online-shopping:61:244"),
		Map("processDefinitionId" -> "online-shopping:62:248"),
		Map("processDefinitionId" -> "online-shopping:63:252"),
		Map("processDefinitionId" -> "online-shopping:64:256"),
		Map("processDefinitionId" -> "online-shopping:65:260"),
		Map("processDefinitionId" -> "online-shopping:66:264"),
		Map("processDefinitionId" -> "online-shopping:67:268"),
		Map("processDefinitionId" -> "online-shopping:68:272"),
		Map("processDefinitionId" -> "online-shopping:69:276"),
		Map("processDefinitionId" -> "online-shopping:6:24"),
		Map("processDefinitionId" -> "online-shopping:70:280"),
		Map("processDefinitionId" -> "online-shopping:71:284"),
		Map("processDefinitionId" -> "online-shopping:72:288"),
		Map("processDefinitionId" -> "online-shopping:73:292"),
		Map("processDefinitionId" -> "online-shopping:74:296"),
		Map("processDefinitionId" -> "online-shopping:75:300"),
		Map("processDefinitionId" -> "online-shopping:76:304"),
		Map("processDefinitionId" -> "online-shopping:77:308"),
		Map("processDefinitionId" -> "online-shopping:78:312"),
		Map("processDefinitionId" -> "online-shopping:79:316"),
		Map("processDefinitionId" -> "online-shopping:7:28"),
		Map("processDefinitionId" -> "online-shopping:80:320"),
		Map("processDefinitionId" -> "online-shopping:81:324"),
		Map("processDefinitionId" -> "online-shopping:82:328"),
		Map("processDefinitionId" -> "online-shopping:83:332"),
		Map("processDefinitionId" -> "online-shopping:84:336"),
		Map("processDefinitionId" -> "online-shopping:85:340"),
		Map("processDefinitionId" -> "online-shopping:86:344"),
		Map("processDefinitionId" -> "online-shopping:87:348"),
		Map("processDefinitionId" -> "online-shopping:88:352"),
		Map("processDefinitionId" -> "online-shopping:89:356"),
		Map("processDefinitionId" -> "online-shopping:8:32"),
		Map("processDefinitionId" -> "online-shopping:90:360"),
		Map("processDefinitionId" -> "online-shopping:91:364"),
		Map("processDefinitionId" -> "online-shopping:92:368"),
		Map("processDefinitionId" -> "online-shopping:93:372"),
		Map("processDefinitionId" -> "online-shopping:94:376"),
		Map("processDefinitionId" -> "online-shopping:95:380"),
		Map("processDefinitionId" -> "online-shopping:96:384"),
		Map("processDefinitionId" -> "online-shopping:97:388"),
		Map("processDefinitionId" -> "online-shopping:98:392"),
		Map("processDefinitionId" -> "online-shopping:99:396"),
		Map("processDefinitionId" -> "online-shopping:9:36")
    ).circular

    val workflow = feed(mapFeeder)
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
            .headers(contentType)
            .formParam("rtl", "0"))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1) //模拟外部服务的任务处理时间
        .exec(http("completeTask")   //pay task
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType)
            .formParam("rtl", "0"))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(3) //模拟外部服务的任务处理时间
        .exec(http("completeTask")    //send goods
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType)
            .formParam("rtl", "0"))
        .exec(http("getCurrentSingleTask")
			.get("getCurrentSingleTask/${processInstanceId}")
			.check(jsonPath("$..taskId").saveAs("taskId")))
        .pause(1) //模拟外部服务的任务处理时间
        .exec(http("completeTask")  //receive goods 
            .post("completeTask/${processDefinitionId}/${processInstanceId}/${taskId}")
            .headers(contentType)
            .formParam("rtl", "0"))
}