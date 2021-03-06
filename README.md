
# Using make to build a simple android app

## Description

Traditionally, android apps are built with ant, gradle or an IDE. There's
a lot of overhead with these methods and some systems (e.g. my Raspberry Pi)
don't have such tools easily available.

If you'd like to build android apps from the command line, this simple program
will show you how to do it. As a bonus, you can have all the files to build an
app in the same directory.

This method works on my Raspberry Pi.

## Usage

Look at the *Makefile* and review the variables at the top. You'll need
to update some of these for your system and versions.

Try "make unsigned" to start. If that builds fine, then try "make debug"
and "make release" to make a debug apk and a release apk, similar to
"ant debug" and "ant release".

## Bash output
```bash
guilty@ftl:~/src/android/skel$ make unsigned
mkdir -p /tmp/androidbuild
cp AndroidManifest.xml /tmp/androidbuild/AndroidManifest.xml
mkdir -p /tmp/androidbuild/res/drawable
mkdir -p /tmp/androidbuild/res/layout
mkdir -p /tmp/androidbuild/res/values
mkdir -p /tmp/androidbuild/src/com/example/skeleton
cp icon.png /tmp/androidbuild/res/drawable/
cp main.xml /tmp/androidbuild/res/layout/
cp strings.xml /tmp/androidbuild/res/values/
cp *.java /tmp/androidbuild/src/com/example/skeleton/
/usr/bin/aapt package -f -m -J /tmp/androidbuild/src -M /tmp/androidbuild/AndroidManifest.xml -S /tmp/androidbuild/res -I /usr/lib/android-sdk/platforms/android-19/android.jar
/usr/bin/javac -source 8 -target 8 -d /tmp/androidbuild/obj -classpath /tmp/androidbuild/src -bootclasspath /usr/lib/android-sdk/platforms/android-19/android.jar /tmp/androidbuild/src/com/example/skeleton/*.java
/usr/lib/android-sdk/build-tools/27.0.1/dx --dex --output=/tmp/androidbuild/classes.dex /tmp/androidbuild/obj
mkdir -p /tmp/androidbuild/bin
/usr/bin/aapt package -f -m -F /tmp/androidbuild/bin/skeleton.unaligned.apk -M /tmp/androidbuild/AndroidManifest.xml -S /tmp/androidbuild/res -I /usr/lib/android-sdk/platforms/android-19/android.jar
cd /tmp/androidbuild ; /usr/bin/aapt add /tmp/androidbuild/bin/skeleton.unaligned.apk classes.dex
 'classes.dex'...
/usr/bin/zipalign -f 4 /tmp/androidbuild/bin/skeleton.unaligned.apk /tmp/androidbuild/bin/skeleton.unsigned.apk
ls -l /tmp/androidbuild/bin/skeleton.unsigned.apk
-rw-r--r-- 1 guilty guilty 6520 Jan 30 21:01 /tmp/androidbuild/bin/skeleton.unsigned.apk
guilty@ftl:~/src/android/skel$ make debug
cp /tmp/androidbuild/bin/skeleton.unsigned.apk /tmp/androidbuild/bin/skeleton.tosign.apk
/usr/bin/apksigner sign --ks ~/.android/debug.keystore -ks-pass pass:android /tmp/androidbuild/bin/skeleton.tosign.apk
mv /tmp/androidbuild/bin/skeleton.tosign.apk /tmp/androidbuild/bin/skeleton.debug.apk
ls -l /tmp/androidbuild/bin/skeleton.debug.apk
-rw-r--r-- 1 guilty guilty 9108 Jan 30 21:01 /tmp/androidbuild/bin/skeleton.debug.apk
guilty@ftl:~/src/android/skel$ exit
```
