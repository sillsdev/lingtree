@echo off
if exist installtemp rmdir installtemp /S /q
echo 	invoking jpackage, pass 2
jpackage --type exe ^
	--copyright "2017-2024 SIL International" ^
	--description "Linguistic Tree Editor" ^
	--name LingTree ^
	--install-dir "SIL\LingTree" ^
	--resource-dir input/resources ^
	--app-image output/LingTree ^
	--win-menu ^
	--win-shortcut ^
	--license-file License.txt ^
	--icon input/LingTree.ico ^
	--temp installtemp ^
	--file-associations lingtree.properties ^
	--vendor "SIL International" 
