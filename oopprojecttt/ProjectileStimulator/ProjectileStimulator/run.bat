@echo off
echo 🔄 Cleaning previous build...
rmdir /s /q out
mkdir out

echo 🧱 Compiling Java sources...
javac -d out -sourcepath src src\app\Main.java
if errorlevel 1 (
    echo ❌ Compilation failed.
    exit /b 1
)

echo 📂 Copying assets...
xcopy /e /i /y assets out\assets

echo 🚀 Running program...
java -cp out app.Main

pause
