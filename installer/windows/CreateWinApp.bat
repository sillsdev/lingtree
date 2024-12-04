@echo off
if exist output rmdir output /S /q
REM if exist apptemp rmdir apptemp /S /q
echo 	invoking jpackage, pass 1
REM use --verbose to see more
jpackage --type app-image ^
	--verbose ^
	--input input ^
	--dest output ^
	--name LingTree ^
	--main-jar lingtree.jar ^
	--main-class org.sil.lingtree.MainApp ^
	--icon input/LingTree.ico ^
	--module-path jmods ^
	--vendor "SIL International"
echo 	MoveResources
call MoveResources.bat
