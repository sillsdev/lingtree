#! /usr/bin/bash
echo "	Libraries"
if [ ! -d input/libs ]; then
 mkdir -p input/libs
else
 rm -r input/libs/* > /dev/null
fi
cp ../../libs/ANTLR/* input/libs > /dev/null
cp ../../libs/ControlsFX/* input/libs > /dev/null
cp ../../libs/JAXB/* input/libs > /dev/null
cp ../../libs/jna input/libs > /dev/null
cp ../../libs/json input/libs > /dev/null
cp ../../libs/Richtextfx/* input/libs > /dev/null

echo "	Documentation"
if [ ! -d input/doc ]; then
 mkdir -p input/doc
else
 rm -r input/doc/* > /dev/null
fi
cp ../../doc/*.pdf input/doc > /dev/null

echo "	Resources"
if [ ! -d input/resources ]; then
 mkdir -p input/resources
else
 rm -r input/resources/* > /dev/null
fi
cp -r ../../src/org/sil/lingtree/resources input/resources
cp LongTree.png input/LingTree.png > /dev/null

echo "	Jar file"
./CreateJar.sh

