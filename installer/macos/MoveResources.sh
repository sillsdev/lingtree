#! /bin/bash
# Work-around to get executable to find resource and doc files
# mkdir -p output//PcPatrEditor/lib/app
# cp -r input/resources output/ > /dev/null
# we run from bin and the executable looks here for the resources
echo 'input/resources has'
ls -l -R input/resources
echo 'before input chmod'
chmod +x input/resources/Keyboards/macOS/xkbswitch
echo 'after input chmod'
ls -l -R input/resources
mkdir -p output/LingTree/resources
echo 'after making dirs'
ls -l -R output/LingTree
echo 'before copy'
ls -l -R output/LingTree/
cp -r input/resources output/LingTree/ > /dev/null
echo 'after copy'
ls -l -R output/LingTree/
chmod +x output/LingTree/resources/Keyboards/macOS/xkbswitch
echo 'after chmod'
ls -l -R output/LingTree/
#rm -r input/PcPatrEditor/lib/app/resources > /dev/null
cp -r input/doc output/LingTree > /dev/null
#rm -r input/PcPatrEditor/lib/app/doc > /dev/null
