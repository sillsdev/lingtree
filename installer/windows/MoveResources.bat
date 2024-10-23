@echo off
REM Work-around to get exe to find resource and doc files
xcopy output\LingTree\app\resources output\LingTree\resources /E/s/i > nul
rmdir output\LingTree\app\resources /S /q > nul
xcopy output\LingTree\app\doc output\LingTree\doc /E/s/i > nul
rmdir output\LingTree\app\doc /S /q > nul
copy ..\..\build\package\windows\LTBatch.bat output\LingTree /Y  > nul
