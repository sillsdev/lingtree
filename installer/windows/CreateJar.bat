@echo off
cd ..\..\bin
REM echo before go up two levels; is bin there?
REM cd ..\..
REM dir
jar cmf META-INF\MANIFEST.MF lingtree.jar .
REM echo java_path=%1
REM jar cf lingtree.jar .
copy lingtree.jar ..\installer\windows\input > nul
del lingtree.jar > nul
cd ..\installer\windows

