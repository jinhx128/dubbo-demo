ps -fu root|grep /usr/local/service/thirdparty/6041/dubbo-demo-thirdparty-1.0.0.jar|grep -v grep|awk '{print $2}'|xargs kill -9
sleep 2 
java -jar /usr/local/service/thirdparty/6041/dubbo-demo-thirdparty-1.0.0.jar --spring.profiles.active=prod-6041 >> /usr/local/service/tomcat/dubbo-demo-thirdparty/6041/tomcat.log 2>&1   &
ps -fu root|grep hexo|grep -v grep|awk '{print $2}'|xargs kill -9