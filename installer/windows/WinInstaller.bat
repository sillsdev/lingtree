@echo off
if exist installtemp rmdir installtemp /S /q
echo 	invoking jpackage, pass 2
jpackage --type exe ^
	--copyright "2017-2025 SIL International" ^
	--description "Linguistic Tree Editor" ^
	--name LingTree ^
	--install-dir "SIL\LingTree" ^
	--resource-dir input/resources ^
	--app-image output/LingTree ^
	--win-menu ^
	--win-shortcut ^
	--license-file License.txt ^
	--icon input/LingTree.ico ^
	--win-upgrade-uuid 651246EB-EFC6-4BE2-97EA-28BDE1D98055 ^
	--temp installtemp ^
	--app-version %1 ^
	--file-associations lingtree.properties ^
	--vendor "SIL International" 
