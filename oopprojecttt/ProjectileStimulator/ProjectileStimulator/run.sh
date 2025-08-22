#!/bin/bash

echo "🔄 Cleaning previous build..."
rm -rf out
mkdir -p out

echo "🧱 Compiling Java sources..."
javac -d out -sourcepath src src/app/Main.java
if [ $? -ne 0 ]; then
    echo "❌ Compilation failed."
    exit 1
fi

echo "📂 Copying assets..."
cp -r assets out/

echo "🚀 Running program..."
java -cp out app.Main
