javac -source 1.7 -target 1.7 -cp lib\*;jar\*;bin -d bin org\igrok_net\configurator\interfaces\*.java org\igrok_net\configurator\*.java
javadoc -cp lib\*;jar\*;bin -d doc org\igrok_net\configurator\interfaces\*.java org\igrok_net\configurator\*.java

java -jar lib\junit-platform-console-standalone-1.9.2.jar -cp lib;bin --scan-classpath