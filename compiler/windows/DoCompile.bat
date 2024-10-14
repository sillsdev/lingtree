@echo off
echo    Compiling on Windows
set java_path="C:\Users\Andy Black\Downloads\Java\AzulZulu\zulu17.30.15-ca-fx-jdk17.0.1-win_x64"
%java_path%\bin\javac -encoding "UTF-8" ^
-g ^
-p ..\..\libs\ANTLR\antlr-4.7-complete.jar;^
..\..\libs\ControlsFX\controlsfx-11.0.2.jar;^
..\..\libs\JAXB\jakarta.activation.jar;^
..\..\libs\JAXB\jakarta.xml.bind-api.jar;^
..\..\libs\JAXB\jaxb-core.jar;^
..\..\libs\JAXB\jaxb-impl.jar;^
..\..\libs\JAXB\jaxb-jxc.jar;^
..\..\libs\JAXB\jaxb-xjc.jar;^
..\..\libs\jna\jna-5.11.0.jar;^
..\..\libs\jna\jna-platform-5.11.0.jar;^
..\..\libs\json\json-simple-1.1.1.jar;^
..\..\libs\Richtextfx\richtextfx-fat-0.10.7.jar;^
org.junit_4.13.2.v20230809-1000.jar ^
-d ..\..\binc ^
..\..\src\module-info.java ^
..\..\src\org\sil\lingtree\*.java ^
..\..\src\org\sil\lingtree\backendprovider\*.java ^
..\..\src\org\sil\lingtree\descriptionparser\*.java ^
..\..\src\org\sil\lingtree\descriptionparser\antlr4generated\*.java ^
..\..\src\org\sil\lingtree\model\*.java ^
..\..\src\org\sil\lingtree\service\*.java ^
..\..\src\org\sil\lingtree\view\*.java ^
..\..\src\org\sil\utility\*.java ^
..\..\src\org\sil\utility\service\*.java ^
..\..\src\org\sil\utility\service\keyboards\*.java ^
..\..\src\org\sil\utility\view\*.java ^
..\..\src\org\sil\utility\xml\*.java ^
 > hab.log 2>&1

echo    Copying resource files
xcopy ..\..\src\org\sil\lingtree\resources ..\..\binc\org\sil\lingtree\resources /S /I /Y >nul
echo    Copying fxml files
xcopy ..\..\src\org\sil\lingtree\view\fxml\*.fxml ..\..\binc\org\sil\lingtree\view\fxml\ /Y >nul
echo Done


