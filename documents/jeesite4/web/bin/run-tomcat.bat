@echo off
rem /**
rem  * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [��Ϣ] ʹ�� Spring Boot Tomcat ���� Web ���̡�
echo.

%~d0
cd %~dp0

cd ..
title %cd%
set "MAVEN_OPTS=%MAVEN_OPTS% -Xms512m -Xmx1024m"
call mvn clean spring-boot:run -Dmaven.test.skip=true

pause