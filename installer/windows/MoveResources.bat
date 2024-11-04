@echo off
REM Work-around to get exe to find resource and doc files
xcopy output\LingTree\app\resources output\LingTree\resources /E/s/i > nul
rmdir output\LingTree\app\resources /S /q > nul
xcopy output\LingTree\app\doc output\LingTree\doc /E/s/i > nul
rmdir output\LingTree\app\doc /S /q > nul
copy LTBatch.bat output\LingTree\app /Y  > nul
echo java home="%JAVA_HOME%"
copy %JAVA_HOME%\bin\java.exe output\LingTree\runtime\bin /Y > nul
