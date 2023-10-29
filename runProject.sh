#!/bin/bash

echo "Nome ficheiro de import: "
read ficheiro;
javac -cp po-uilib*:. `find xxl -name "*.java"`
java -cp po-uilib*:. -Dimport=$ficheiro xxl.app.App

