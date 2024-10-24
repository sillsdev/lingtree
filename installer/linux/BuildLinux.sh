#! /usr/bin/bash
# Build Linux installer; see ReadmeLinuxInstaller.txt
echo PrepInput
./PrepInput.sh
echo CreateLinuxApp
./CreateLinuxApp.sh
echo LinuxInstaller
./LinuxInstaller.sh

