#! /usr/bin/bash
# Work-around to get exe to find resource and doc files
#cp -r input/resources output/PcPatrEditor/lib/app > /dev/null
# we run from bin and the executable looks here for the resources
cp -r input/resources output/LingTree/bin > /dev/null
#rm -r input/LingTree/lib/app/resources > /dev/null
cp -r input/doc output/LingTree/doc > /dev/null
#rm -r input/LingTree/lib/app/doc > /dev/null
