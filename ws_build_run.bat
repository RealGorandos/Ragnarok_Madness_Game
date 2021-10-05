Xcopy /E /I src_rm_java\ragmad\ bin\ragmad\ 

cd bin

javac ragmad/*.java

del /s /q *.java

java ragmad/Main