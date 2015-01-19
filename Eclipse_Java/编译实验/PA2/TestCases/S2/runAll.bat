for %%m in (*.decaf) do java -jar decaf.jar -l 1 %%m > result\%%~nm.result
java -jar decaf.jar stack.decaf -l 1 -m Stack > result\stack.result