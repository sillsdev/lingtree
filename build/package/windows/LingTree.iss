;This file will be executed next to the application bundle image
;I.e. current directory will contain folder LingTree with application files
[Setup]
AppId={{2FFB0A36-E5AC-40A6-A398-5987B4BEEF3D}
AppName=LingTree
AppVersion=1.3.0.0
AppVerName=LingTree version 1.3.0.0
AppPublisher=SIL International
AppComments=LingTree
AppCopyright=Copyright © 2018-2024 SIL International
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
OutputBaseFilename=LingTree-1.3.0.0
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
SetupIconFile=LingTree\LingTree.ico
UninstallDisplayIcon={app}\LingTree.ico
UninstallDisplayName=LingTree version 1.3.0.0
WizardImageStretch=No
WizardSmallImageFile=LingTree-setup-icon.bmp
ArchitecturesInstallIn64BitMode=x64
ChangesAssociations=yes

[Registry]
Root: HKCR; Subkey: ".tre"; ValueType: string; ValueName: ""; ValueData: "LingTreeFile"; Flags: uninsdeletevalue
Root: HKCR; Subkey: "Mime\Database\Content Type\text/x-lingtree"; ValueType: string; ValueName: "Extension"; ValueData: ".tre"; Flags: uninsdeletevalue
Root: HKCR; Subkey: "LingTreeFile"; ValueType: string; ValueName: ""; ValueData: "LingTree file"; Flags: uninsdeletekey
Root: HKCR; Subkey: "LingTreeFile\shell\open\command"; ValueType: string; ValueName: ""; ValueData: """{app}\LingTree"" ""%1"""

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "LingTree\LingTree.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "LingTree\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
;it does not find it; sigh Source: "..\..\externalLibs\java.exe"; DestDir: "{app}\runtime\bin"; Flags: ignoreversion
Source: "C:\Users\Andy Black\Documents\Eclipse4.4Workspace\lingtree\build\externalLibs\java.exe"; DestDir: "{app}\runtime\bin"; Flags: ignoreversion

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
	