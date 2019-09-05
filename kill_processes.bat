@echo off
SetLocal
echo.
echo.
echo ======================================================
echo.
echo This script will try to kill first process which can
echo be found listening on following port range: 8080..8085
echo.
echo Uses commands: netstat and taskkill
echo ------------------------------------------------------
echo   netstat arguments
echo     -a List all connections and listening ports
echo     -n List all addresses and ports as numbers
echo     -o List all process-ids belonging to a connection
echo.
echo ======================================================

echo.
echo.
echo START GHOST BUSTING ...

FOR /L %%G IN (0,1,5) DO (
  echo check port 808%%G
  FOR /F "tokens=5 delims= " %%P IN (
    'netstat -ano ^| find "LISTENING" ^| find ":808%%G "'
  ) DO (
    IF NOT "%%P"=="" (
      TASKKILL /F /PID %%P
      break;
    )
  )
)

FOR /L %%G IN (0,1,1) DO (
  echo check port 300%%G
  FOR /F "tokens=5 delims= " %%P IN (
    'netstat -ano ^| find "LISTENING" ^| find ":300%%G "'
  ) DO (
    IF NOT "%%P"=="" (
      TASKKILL /F /PID %%P
      break;
    )
  )
)
echo   !no ghosts found!

:FINISHED
echo ... FINISHED GHOST BUSTING
echo.
EndLocal