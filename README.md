# valyra-hotels

### Ejecutar proyecto

```
javac -d target\classes (Get-ChildItem -Recurse -Filter *.java src\main\java | ForEach-Object { $_.FullName }); if ($?) { java -cp target\classes com.tdea.ValyraHotelsApp }
```