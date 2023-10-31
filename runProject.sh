#!/bin/bash

echo "Nome ficheiro de import: "
read ficheiro;

javac -cp po-uilib*:. `find xxl -name "*.java"`

if [ -n "$ficheiro" ]; then
  java -cp po-uilib*:. -Dimport="$ficheiro" xxl.app.App
else
  java -cp po-uilib*:. xxl.app.App
fi
