#! /usr/bin/bash

cd ../../mods
# jar cmf META-INF/MANIFEST.MF pcpatreditor.jar .
jar cf lingtree.jar .
cp lingtree.jar ../installer/linux/input > nul
rm lingtree.jar > nul
cd ../installer/linux