@echo off
REM Work-around to get exe to find resource and doc files
xcopy output\LingTree\app\resources output\LingTree\resources /E/s/i > nul
rmdir output\LingTree\app\resources /S /q > nul
xcopy output\LingTree\app\doc output\LingTree\doc /E/s/i > nul
rmdir output\LingTree\app\doc /S /q > nul
copy LTBatch.bat output\LingTree\app /Y  > nul
dir %JAVA_HOME%\bin\java.*
copy %JAVA_HOME%\bin\java.exe output\LingTree /Y > nul
dir output\LingTree
copy %JAVA_HOME%\bin\java.exe output /Y > nul
dir output
