#! /bin/bash
# Work-around to get executable to find resource and doc files
# mkdir -p output//PcPatrEditor/lib/app
# cp -r input/resources output/ > /dev/null
# we run from bin and the executable looks here for the resources
chmod +x input/resources/Keyboards/macOS/xkbswitch
mkdir -p output/LingTree/resources
cp -r input/resources output/LingTree/ > /dev/null
chmod +x output/LingTree/resources/Keyboards/macOS/xkbswitch
#rm -r input/PcPatrEditor/lib/app/resources > /dev/null
cp -r input/doc output/LingTree > /dev/null
#rm -r input/PcPatrEditor/lib/app/doc > /dev/null
