#! /bin/bash
if [ -d installtemp ]; then
 echo "	removing installtemp"
 rm -rf installtemp
fi
echo "	invoking jpackage, part 2"
# 	--verbose \
jpackage --type dmg \
	--copyright "2021-2024 SIL International" \
	--description "Linguistic Tree Editor" \
	--name LingTree \
	--resource-dir input/resources \
	--app-image output/LingTree.app \
    --mac-package-name "Linguistic Tree Editor" \
	--license-file License.txt \
	--temp installtemp \
	--icon input/LingTree.icns \
	--app-version $1 \
	--file-associations lingtree.properties \
	--vendor "SIL International"

