#! /bin/bash

cd ../../bin
# jar cmf META-INF/MANIFEST.MF lingtree.jar .
jar --create --file lingtree.jar --main-class org.sil.lingtree.MainApp .
cp lingtree.jar ../installer/macos/input > nul
rm lingtree.jar > nul
cd ../installer/macos
