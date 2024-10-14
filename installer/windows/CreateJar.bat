@echo off
cd ..\..\binc
REM jar cmf META-INF\MANIFEST.MF pcpatreditor.jar .
REM echo java_path=%1
%1\bin\jar cf lingtree.jar .
copy lingtree.jar ..\installer\windows\input > nul
del lingtree.jar > nul
cd ..\installer\windows
