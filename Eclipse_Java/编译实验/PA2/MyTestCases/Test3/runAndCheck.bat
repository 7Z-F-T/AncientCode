java -jar ..\..\result\decaf.jar -l 1 test.decaf > test.myresult
fc /L /W /N /T  test.myresult test.result
pause