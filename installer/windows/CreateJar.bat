@echo off
pwd
echo before cd to bin
cd ..\..\bin
dir
REM echo before go up two levels; is bin there?
REM cd ..\..
REM dir
REM jar cmf META-INF\MANIFEST.MF pcpatreditor.jar .
REM echo java_path=%1
echo before jar
jar cf lingtree.jar .
echo copy
copy lingtree.jar ../installer/windows/input > nul
echo del
del lingtree.jar > nul
echo cd to installer windows
cd ..\installer\windows
dir

