REM emulator -avd intel2 -gpu on
adb wait-for-device

# ..\workspace\Settings\bin\Settings.apk 
:begin
adb uninstall tru.ruinevpo.test
java -jar signapk.jar platform.x509.pem platform.pk8 Settings_test.apk Settings_test_signed.apk
adb install Settings_test_signed.apk
adb shell am instrument -w tru.ruinevpo.test/android.test.InstrumentationTestRunner
pause
goto begin