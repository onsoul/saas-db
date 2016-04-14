@title HITLER-HA-DB RUNING %1

@echo %~dp0
echo off
rem Guess MNIS_HOME if not defined
set "CURRENT_DIR=%cd%"
if not "%MNIS_HOME%" == "" goto gotHome
set "MNIS_HOME=%CURRENT_DIR%"
if exist "%MNIS_HOME%\bin\hitler-provider-jar.bat" goto okHome
set "MNIS_HOME=%~dp0.."
cd "%CURRENT_DIR%"

:gotHome
if exist "%MNIS_HOME%\bin\hitler-provider-jar.bat" goto okHome
echo The MNIS_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end

:okHome
if "%1" == "" goto startup

:startup
@set classpath=%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;%MNIS_HOME%\lib;
java -Xms32m -Xmx128m -XX:MaxNewSize=32m -XX:MaxPermSize=128m  -jar %MNIS_HOME%\db-proxy-dao-0.0.1.jar %1 &

echo on
:end
pause