#!/bin/bash
#当前环境
environment=test

str1=test
str2=prod
str3=preprod

#获取jenkins管理目录根路径
basedir=/mnt/data01/web/jenkins_manger
#设置环境目录 
if [[ $environment=$str1 ]];then
  backup=$basedir/backup/test
  command=$basedir/command/test
  startLog=$basedir/startLog/test
  execute=$basedir/execute/test
elif [[ $environment=$str2 ]];then
  backup=$basedir/backup/prod
  command=$basedir/command/prod
  startLog=$basedir/startLog/prod
  execute=$basedir/execute/prod
elif [[ $environment=$str3 ]];then
  backup=$basedir/backup/preprod
  command=$basedir/command/preprod
  startLog=$basedir/startLog/preprod
  execute=$basedir/execute/preprod
fi

#获取当前日期
date=$(date "+%Y%m%d")  
dateTime=$(date "+%Y%m%d%H%M%S")  
# 判断备份目录是不是已经存在,如果不是，则创建
backupdir=$backup"/"$date
echo $backupdir
if [ ! -d $backupdir  ];then
  mkdir $backupdir
fi

#删除两天前的备份文件
find $backupdir/*  -mtime +2 -exec rm -rf {} \

#移动原始jar包到备份目录
#mv $execute/smart-terminal-service-order-$environment-"2.0.0.jar" $backupdir/smart-terminal-service-order-2.0.0.jar.$dateTime
#将新jar包复制到指定环境目录
#mv $basedir/smart-terminal-service-order-2.0.0.jar $execute/smart-terminal-service-order-$environment-"2.0.0.jar"
mv $execute/smart-terminal-service-order-2.0.0.jar $backupdir/smart-terminal-service-order-2.0.0.jar.$dateTime
mv $basedir/smart-terminal-service-order-2.0.0.jar $execute/smart-terminal-service-order-2.0.0.jar


#kill进程，启动项目
oldPids=`ps -fu web|grep smart-terminal-service-order|grep -v grep|awk '{print $2}'`
echo "项目PID" $oldPids
for oldPid in $oldPids; do
        if [ $oldPid ]; then
          kill -15 $oldPid
          sleep 5
          echo "停机成功"
        fi
done

java -jar $execute/smart-terminal-service-order-2.0.0.jar --spring.profiles.active=test  >> $startLog/service-order-tomcat.log 2>&1 &

newPids=`ps -fu  web|grep smart-terminal-service-order|grep -v grep|awk '{print $2}'`
echo "项目PID" $newPids
for newPid in $newPids 
do
	if [ $newPid ]; then
		echo "项目部署完成"
		echo "部署后PID为" $newPid
	else
		echo "项目启动失败"
	fi
done

echo "====================测试环境smart-terminal-service-order部署完成===================="

sleep 5

#echo "执行成功"
#echo $startLog/service-order-tomcat.log
#tail -fn 100 $startLog/service-order-tomcat.log
