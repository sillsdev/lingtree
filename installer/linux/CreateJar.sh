#! /usr/bin/bash

cd ../../bin
# jar cmf META-INF/MANIFEST.MF lingtree.jar .
jar cf lingtree.jar --main-class org.sil.lingtree.MainApp .
cp lingtree.jar ../installer/linux/input > nul
rm lingtree.jar > nul
cd ../installer/linux
