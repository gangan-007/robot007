# 基础镜像
FROM openjdk:8-jre-slim
# 作者
MAINTAINER gangan
# 将文件挂载到当前容器
VOLUME /tmp/chatgpt
# 配置
ENV PARAMS=""
# 添加应用
ADD /interfaces/target/robot-api.jar /rebot007.jar
# 执行镜像；docker run
#-e PARAMS=" --chatbot-api.groupId=你的星球ID --chatbot-api.openAiKey=自行申请 --chatbot-api.cookie=登录cookie信息"
# -p 8090:8090 --name chatbot-api -d chatbot-api:1.0
# java [-options] class [args...] 其中，-options即为Java_opts，后面可跟多个参数，用于配置JVM的各种启动选项  @see https://www.python100.com/html/95700.html
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /rebot007.jar $PARAMS"]