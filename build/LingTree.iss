;This file will be executed next to the application bundle image
;I.e. current directory will contain folder Asheninka Syllable Parser with application files
[Setup]
AppId={{fxApplication}}
AppName=LingTree
AppVersion=0.999.1 Alpha
AppVerName=LingTree 0.999.1 Alpha
AppPublisher=SIL International
AppComments=LingTree
AppCopyright=© 2018 SIL International
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={localappdata}\LingTree
DisableStartupPrompt=Yes
DisableDirPage=Yes
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=SIL International
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=LingTree-0.999.1
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=LingTree\LingTree.ico
UninstallDisplayIcon={app}\LingTree.ico
UninstallDisplayName=LingTree
WizardImageStretch=No
WizardSmallImageFile=LingTree-setup-icon.bmp   
ArchitecturesInstallIn64BitMode=


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
Filename: "{app}\LingTree.exe"; Parameters: "-install -svcName ""LingTree"" -svcDesc ""LingTree is a tool that lets you easily describe a linguistic tree and produce a graphic image of it."" -mainExe ""LingTree.exe""  "; Check: returnFalse()

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
