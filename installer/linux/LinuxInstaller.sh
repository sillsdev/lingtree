#! /usr/bin/bash
if [ -d installtemp ]; then
 echo "	removing installtemp"
 rm -rf installtemp
fi
echo "	invoking jpackage, part 2"
VERSION=$1
# 	--verbose \
#	--linux-shortcut \
ls -l -R
jpackage --type deb \
	--copyright "2021-2024 SIL International" \
	--description "Linguistic Tree Editor" \
	--name LingTree \
	--install-dir /opt/sil \
	--resource-dir jpackageResources \
	--app-image output/LingTree \
	--linux-menu-group "Education" \
	--license-file License.txt \
	--icon input/LingTree.png \
	--temp installtemp \
	--file-associations lingtree.properties \
	--vendor "SIL International"
# ./FixDesktopShortcutInDebFile.sh $VERSION
