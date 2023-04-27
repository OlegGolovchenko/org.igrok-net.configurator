javac -source 1.7 -target 1.7 -cp lib\*;jar\*;bin -d bin org\igrok_net\configurator\interfaces\*.java org\igrok_net\configurator\*.java

cd bin
jar -cvfe ..\jar\org.igrok-net.configurator_v004.jar org.igrok_net.configurator.Configurator *
cd ..

xcopy /y /q jar\org.igrok-net.configurator_v004.jar lib-gui\org.igrok-net.configurator_v004.jar

javac -source 1.7 -target 1.7 -cp lib\*;lib-gui\*; -d gui_bin org\igrok_net\configurator_gui\actions\*.java org\igrok_net\configurator_gui\components\*.java org\igrok_net\configurator_gui\window\*.java org\igrok_net\configurator_gui\*.java

cd gui_bin
jar -cvfe ..\jar\org.igrok-net.configurator-gui_v004.jar org.igrok_net.configurator_gui.ConfiguratorGui *
cd ..

javadoc -cp lib\*;jar\*;bin -d doc org\igrok_net\configurator\interfaces\*.java org\igrok_net\configurator\*.java org\igrok_net\configurator_gui\*.java

java -jar lib\junit-platform-console-standalone-1.9.2.jar -cp lib;bin --scan-classpath

xcopy /y /q run.bat jar\run.bat
xcopy /y /q run.sh jar\run.sh