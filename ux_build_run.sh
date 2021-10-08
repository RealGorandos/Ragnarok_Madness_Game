#! /bin/bash


find . -name \*.class -type f -delete # cleaning the directory from older builds


rm -r ./bin
mkdir bin
cp -r src_rm_java/ragmad/ ./bin/ragmad/
cd ./bin
if [ $? -ne 0 ]; then
    exit 1
fi

javac ragmad/*.java
if [ $? -ne 0 ]; then
    exit 1
fi

find . -name \*.java -type f -delete 
java ragmad/Main
cd ..

exit 0
