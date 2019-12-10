ps -fu root|grep /usr/local/service/service/6050/dubbo-demo-service-1.0.0.jar|grep -v grep|awk '{print $2}'|xargs kill -9
sleep 2 
java -jar /usr/local/service/service/6050/dubbo-demo-service-1.0.0.jar --spring.profiles.active=prod-6050 >> /usr/local/service/tomcat/dubbo-demo-service/6050/tomcat.log 2>&1   &
