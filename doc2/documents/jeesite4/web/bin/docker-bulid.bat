@echo off
rem /**
rem  * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [��Ϣ] ���Web���̣����뵽Docker����
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package docker:stop docker:remove docker:build docker:run -Dmaven.test.skip=true -U

cd bin
pause