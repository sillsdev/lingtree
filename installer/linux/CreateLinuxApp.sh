#! /usr/bin/bash
if [ -d output ]; then
 echo "	Cleaning output"
 rm -rf output > /dev/null
fi
echo "	invoking jpackage, part 1"
# to see more details, add the --verbose option
#	--verbose \
jpackage --type app-image \
	--input input \
	--dest output \
	--name LingTree \
	--main-jar lingtree.jar \
	--main-class org.sil.lingtree.MainApp \
	--icon input/LingTree.png \
	--module-path jmods \
	--vendor "SIL International"
echo "	MoveResources"
./MoveResources.sh

