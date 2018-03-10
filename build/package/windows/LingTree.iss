;This file will be executed next to the application bundle image
;I.e. current directory will contain folder LingTree with application files
[Setup]
AppId={{fxApplication}}
AppName=LingTree
AppVersion=0.999.3.0 Beta
AppVerName=LingTree 0.999.3.0 Beta
AppPublisher=SIL International
AppComments=LingTree
AppCopyright=Copyright © 2018 SIL International
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={pf}\LingTree
DisableStartupPrompt=Yes
DisableDirPage=No
DisableProgramGroupPage=Yes
DisableReadyPage=No
DisableFinishedPage=No
DisableWelcomePage=No
DefaultGroupName=SIL International
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=LingTree-0.999.3.0 Beta
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
SetupIconFile=LingTree\LingTree.ico
UninstallDisplayIcon={app}\LingTree.ico
UninstallDisplayName=LingTree
WizardImageStretch=No
WizardSmallImageFile=LingTree-setup-icon.bmp   
ArchitecturesInstallIn64BitMode=x64


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "LingTree\LingTree.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "LingTree\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\LingTree"; Filename: "{app}\LingTree.exe"; IconFilename: "{app}\LingTree.ico"; Check: returnTrue()
Name: "{commondesktop}\LingTree"; Filename: "{app}\LingTree.exe";  IconFilename: "{app}\LingTree.ico"; Check: returnTrue()


[Run]
Filename: "{app}\LingTree.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\LingTree.exe"; Description: "{cm:LaunchProgram,LingTree}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\LingTree.exe"; Parameters: "-install -svcName ""LingTree"" -svcDesc ""A tool for creating linguistic tree diagrams."" -mainExe ""LingTree.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\LingTree.exe "; Parameters: "-uninstall -svcName LingTree -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support? 
  Result := True;
end;  
