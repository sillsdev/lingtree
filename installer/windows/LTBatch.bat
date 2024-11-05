@echo off
set INSTALL_DIR = %ProgramFiles%\SIL\LingTree
if not exist %INSTALL_DIR%\runtime\bin\java.exe (
  copy %INSTALL_DIR%\java.exe %INSTALL_DIR%\runtime\bin
  )
%INSTALL_DIR%\runtime\bin\java -jar %INSTALL_DIR%\app\LingTree.jar %1
REM "%ProgramFiles%\SIL\LingTree\lingtree.exe" -b %1
