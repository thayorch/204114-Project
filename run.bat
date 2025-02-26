@echo off
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Java is not installed or not in the PATH.
    pause
    exit /b
)

java src/client/Main.java
if %errorlevel% neq 0 (
    echo Failed to run the Java program.
)
pause
