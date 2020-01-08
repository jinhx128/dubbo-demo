ps -fu root|grep /usr/local/service/web/6062+8442/dubbo-demo-web-1.0.0.jar|grep -v grep|awk '{print $2}'|xargs kill -9
sleep 2 
java -jar /usr/local/service/web/6062+8442/dubbo-demo-web-1.0.0.jar --spring.profiles.active=prod-6062 >> /usr/local/service/tomcat/dubbo-demo-web/6062+8442/tomcat.log 2>&1   &
