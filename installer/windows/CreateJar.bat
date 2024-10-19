@echo off
cd ..\..\bin
REM echo before go up two levels; is bin there?
REM cd ..\..
REM dir
REM jar cmf META-INF\MANIFEST.MF pcpatreditor.jar .
REM echo java_path=%1
jar cf lingtree.jar .
copy lingtree.jar ..\installer\windows\input > nul
del lingtree.jar > nul
cd ..\installer\windows

