REM echo %cd%
set PWD=%~dp0
REM echo %PWD%
REM pause
rem от версии к версии русский язык и пробел могут не верно работать

%PWD%jre1.8.0_211\bin\java.exe  -Xms1048m -Xmx1048m -Djavafx.embed.singleThread=true -Djavafx.autoproxy.disable=true -Dfile.encoding=UTF-8 -jar %PWD%ImgGraber.jar %1 %2 %3 %4 %5 %6
