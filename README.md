# أسعار الخردة - تطبيق أندرويد

تطبيق من تطوير **محمد أبو سليمان**.

تطبيق بسيط يفتح لوحة أسعار الخردة في مصر: https://mmsmas1.github.io/scrap-prices-egypt/
وفي حالة عدم وجود إنترنت يفتح نسخة محفوظة داخل التطبيق.

- مصدر الأسعار: موقع khurda.net. هو موقع تجاري غير رسمي، والأسعار استرشادية.
- التحديث التلقائي يتم عبر مشروع آخر: https://github.com/Mmsmas1/scrap-prices-egypt
- لا يرسل التطبيق أي بيانات شخصية ولا يطلب سوى إذن الإنترنت.
- الحد الأدنى: أندرويد 7.0 (API 24).

## البناء
`build.sh` يبني ملف APK بدون Gradle. المطلوب: JDK 17 وAndroid SDK (platform 34 وbuild-tools 34.0.0) ومفتاح توقيع خاص بك:

    ANDROID_SDK=/path/to/sdk KEYSTORE=my.jks KS_PASS=... ./build.sh

# Scrap Prices Egypt - Android app

By Mohamed Abu Soliman. A thin WebView wrapper around the live dashboard, with a bundled offline copy. Prices come from khurda.net (unofficial, indicative).
