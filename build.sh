#!/bin/bash
# Build without Gradle. Needs JDK 17 and Android SDK (platform 34, build-tools 34.0.0).
set -e
BT=$ANDROID_SDK/build-tools/34.0.0; PL=$ANDROID_SDK/platforms/android-34/android.jar
rm -rf build; mkdir -p build/gen build/cls
$BT/aapt2 compile --dir res -o build/res.zip
$BT/aapt2 link -o build/base.apk -I $PL --manifest AndroidManifest.xml -R build/res.zip -A assets --java build/gen --min-sdk-version 24 --target-sdk-version 34 --version-code 2 --version-name 1.0.1
javac --release 8 -encoding UTF-8 -d build/cls -cp $PL src/eg/scrap/Main.java build/gen/eg/scrap/prices/R.java
$BT/d8 --lib $PL --min-api 24 --output build $(find build/cls -name '*.class')
cp build/base.apk build/app.apk; (cd build && zip -q -j app.apk classes.dex)
$BT/zipalign -f 4 build/app.apk build/aligned.apk
$BT/apksigner sign --ks "$KEYSTORE" --ks-pass pass:"$KS_PASS" --out scrap-prices.apk build/aligned.apk
