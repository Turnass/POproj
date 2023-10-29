#!/bin/bash

let total=0;
let correct=0;
poUilib=( ./po-uilib*.jar );
firstEl=${poUilib[0]};

for x in tests/*.in; do
    if [ -e ${x%.in}.import ]; then
      java -cp $firstEl:. -Dimport=${x%.in}.import -Din=$x -Dout=${x%.in}.outhyp xxl.app.App;
    else
      java -cp $firstEl:. -Din=$x -Dout=${x%.in}.outhyp xxl.app.App;
    fi

    diff -cwB ${x%.in}.out ${x%.in}.outhyp > ${x%.in}.diff ;
    if [ -s ${x%.in}.diff ]; then
        echo -n "F"
        failures=$failures"Fail: $x: See file ${x%.in}.diff\n" ;
#        echo "FAIL: $x. See file ${x%.in}.diff " ;
    else
        let correct++;
        echo -n "."
        rm -f ${x%.in}.diff ${x%.in}.outhyp ; 
    fi
    let total++;
done

rm -f saved*
let res=100*$correct/$total
echo ""
echo "Tests passed = " $correct
echo "Total Tests = " $total
echo "Passed = " $res"%"
printf "$failures"
echo "Done."

