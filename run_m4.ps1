if (!(Test-Path "out")) { New-Item -ItemType Directory -Path "out" | Out-Null }
javac -d out src/*.java
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
java -cp out M4Test
