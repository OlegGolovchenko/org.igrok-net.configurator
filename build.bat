javac -source 1.7 -target 1.7 -cp lib\*;jar\*;bin -d bin org\igrok_net\configurator\interfaces\*.java org\igrok_net\configurator\*.java org\igrok_net\*.java
javadoc -cp lib\*;jar\*;bin -d doc org\igrok_net\configurator\interfaces\*.java org\igrok_net\configurator\*.java org\igrok_net\*.java

java -jar lib\junit-platform-console-standalone-1.9.2.jar -cp lib;bin --scan-classpath
cd bin
jar -cvfe ..\jar\org.igrok-net.configurator_v002.jar org.igrok_net.Configurator *
cd ..