$count = 0

while $count <> 10
$hdl = WinActivate("File Upload")

If $hdl <> 0 Then
	ControlFocus("File Upload","","Edit1")
	Sleep(500)
	ControlSetText("File Upload","","Edit1",$CmdLineRaw)
	Sleep(500)
	ControlClick("File Upload","","Button1")
	Sleep(500)
	Exit
	EndIf
Sleep(1000)
$count = $count + 1
WEnd
