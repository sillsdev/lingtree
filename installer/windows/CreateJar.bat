@echo off
cd ..\..\bin
REM jar cmf META-INF\MANIFEST.MF pcpatreditor.jar .
jar --create --file lingtree.jar --main-class org.sil.lingtree.MainApp .
copy lingtree.jar ..\installer\windows\input > nul
del lingtree.jar > nul
cd ..\installer\windows

