#! /usr/bin/bash
echo "	Libraries"
if [ ! -d input/libs ]; then
 mkdir -p input/libs
else
 rm -r input/libs/* > /dev/null
fi
cp -r ../../libs/ANTLR/* input/libs > /dev/null
cp -r ../../libs/ControlsFX/* input/libs > /dev/null
cp -r ../../libs/JAXB/* input/libs > /dev/null
cp -r ../../libs/jna input/libs > /dev/null
cp -r ../../libs/json input/libs > /dev/null
cp -r ../../libs/LibJavaDev input/libs > /dev/null
cp -r ../../libs/Richtextfx/* input/libs > /dev/null

echo "	Documentation"
if [ ! -d input/doc ]; then
 mkdir -p input/doc
else
 rm -r input/doc/* > /dev/null
fi
cp -R ../../doc/*.pdf input/doc > /dev/null

echo "	Resources"
if [ ! -d input/resources ]; then
 mkdir -p input/resources
else
 rm -r input/resources/* > /dev/null
fi
cp -R ../../src/org/sil/lingtree/resources input/resources
cp LingTree.png input/LingTree.png > /dev/null

echo "	Jar file"
./CreateJar.sh

