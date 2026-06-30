#! /bin/bash
if [ -d installtemp ]; then
 echo "	removing installtemp"
 rm -rf installtemp
fi
echo "	invoking jpackage, part 2"
# 	--verbose \
jpackage --type dmg \
	--copyright "2021-2026 SIL Global" \
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
	--vendor "SIL Global"

#	--mac-sign \
#	--mac-signing-key-user-name "Developer ID Application: Summer Institute of Linguistics, Inc (SIL) (3YE4W86L3G)" \
