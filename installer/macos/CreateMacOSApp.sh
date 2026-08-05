#! /bin/bash
if [ -d output ]; then
 echo "	Cleaning output"
 rm -rf output > /dev/null
fi
echo "	invoking jpackage, part 1"
# to see more details, add the --verbose option
#	--verbose \
jpackage --type app-image \
	--input input \
	--dest output \
	--name LingTree \
	--main-jar lingtree.jar \
	--main-class org.sil.lingtree.MainApp \
	--java-options --enable-native-access=javafx.graphics \
	--java-options --enable-native-access=javafx.web \
	--java-options --enable-native-access=com.sun.jna \
	--add-modules jdk.management.jfr,java.rmi,jfx.incubator.input,java.xml,jdk.xml.dom,java.datatransfer,jdk.httpserver,javafx.base,java.desktop,java.security.sasl,jdk.zipfs,java.base,jfx.incubator.richtext,jdk.management.agent,jdk.sctp,java.sql.rowset,jdk.jsobject,javafx.swing,jdk.unsupported,java.smartcardio,java.security.jgss,java.compiler,javafx.graphics,jdk.nio.mapmode,jdk.dynalink,javafx.fxml,jdk.accessibility,javafx.media,jdk.security.jgss,vector,java.sql,javafx.web,java.logging,java.transaction.xa,java.xml.crypto,jdk.jfr,jdk.internal.md,jdk.net,java.naming,javafx.controls,java.prefs,java.net.http,java.management,jdk.jdwp.agent,java.instrument,jdk.management,jdk.security.auth,java.scripting,java.management.rmi,jdk.localedata \
	--jlink-options "--include-locales=en,fr,es" \
	--icon input/LingTree.icns \
	--module-path jmods \
	--vendor "SIL International"
#	--vendor "SIL International" \
#	--mac-sign \
#	--mac-signing-key-user-name "Developer ID Application: Summer Institute of Linguistics, Inc (SIL) (3YE4W86L3G)"
echo "	MoveResources"
./MoveResources.sh

