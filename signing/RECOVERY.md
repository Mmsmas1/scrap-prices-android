# Signing key recovery (no secrets in this repo)

APK signer certificate SHA-256: `e3e94818aef2eab5797942b8222bfa1dac3dbfb7f62237e811d97545410494ea`
Public certificate: `scrap-cert.pem` (this folder).

The private key is not stored here. The RSA private key is rebuilt from one secret prime `p` plus the public certificate.
`p` (base64, 172 characters) is kept in the owner's vault as two login entries:

- "Scrap app signing key part 1 of 2" (first 86 characters)
- "Scrap app signing key part 2 of 2" (last 86 characters)

Recovery:

1. Get the two entries' passwords into a private file, part 1 followed by part 2 on one line, no spaces (`p.b64`).
2. `python3 rebuild.py p.b64 scrap-cert.pem key.der` (needs openssl on PATH; it checks that p matches the certificate).
3. Convert the certificate: `openssl x509 -in scrap-cert.pem -outform DER -out cert.der`
4. Sign: `apksigner sign --key key.der --cert cert.der --min-sdk-version 24 --out signed.apk unsigned.apk`
5. Check: `apksigner verify --print-certs signed.apk` must show the SHA-256 above, or Android will refuse to update the installed app.

Delete `p.b64` and `key.der` afterwards.

# استرجاع مفتاح التوقيع
المفتاح الخاص غير موجود في هذا المشروع. يتم تركيبه من العدد الأولي p المحفوظ في خزنة المالك (محمد أبو سليمان) مع الشهادة العامة هنا. اتبع الخطوات بالإنجليزية أعلاه.
