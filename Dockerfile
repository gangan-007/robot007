# 基础镜像
FROM openjdk:8-jre-slim
# 作者
MAINTAINER gangan
# 将文件挂载到当前容器
VOLUME /tmp/chatgpt
# 添加应用
ADD /interfaces/target/robot-api.jar /rebot007.jar
# 执行镜像
ENTRYPOINT ["sh","-c","java -jar /rebot007.jar"]