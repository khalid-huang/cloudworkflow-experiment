# cloudworkflow-experiment
云工作流实验代码

### 分支作用
#### 这个分支主要用于
+ （test目录）测试了流程模型解析之后占用的内存大小
+ （test目录）测试不同的流程任务消耗的时长
+ （main目录）测试服务吞吐量,负载代码使用gatling生成，代码在load-simulation中

#### 代码
+ 为了方便测试，把activiti的版本改成了5.21.0，这样可以直接从官网上下载，后面需要解析使用本地的jar包解决依赖
+ activiti-serivce下的docker放了Dockerfile

#### 测试负载的方式
+ 编辑好Dockerfile，生成jar包
+ 将jar和Dockerfile发送到kevin-pc，在kevin-pc上打包并发布；
+ 在workflow-1上安装cadvisor，和运行activiti-service
+ 在kevin-pc上运动gatling

