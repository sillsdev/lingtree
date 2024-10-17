@echo off
pwd
echo before cd to bin
cd ..\..\bin
echo before go up two levels; is bin there?
cd ..\..
dir
REM jar cmf META-INF\MANIFEST.MF pcpatreditor.jar .
REM echo java_path=%1
echo before jar
jar cf lingtree.jar .
echo copy
copy lingtree.jar ../installer/windows/input > nul
echo del
del lingtree.jar > nul
echo cd
cd ..\installer\windows
