#!/bin/bash

echo "Para compilar projeto: "
echo "javac -cp po-uilib\(2\).jar:. \`find xxl -name "*.java"\`"
echo ""
echo "Para correr projeto sem ficheiro import: "
echo "java -cp po-uilib\(2\).jar:. xxl.app.App"
echo ""
echo "Para correr projeto com ficheiro import: "
echo "java -Dimport=ficheiro.im -cp po-uilib\(2\).jar:. xxl.app.App"
echo ""
echo "Gostou stor?"
