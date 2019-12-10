ps -fu root|grep /usr/local/service/thirdparty/6042/dubbo-demo-thirdparty-1.0.0.jar|grep -v grep|awk '{print $2}'|xargs kill -9
sleep 2 
java -jar /usr/local/service/thirdparty/6042/dubbo-demo-thirdparty-1.0.0.jar --spring.profiles.active=prod-6042 >> /usr/local/service/tomcat/dubbo-demo-thirdparty/6042/tomcat.log 2>&1   &
