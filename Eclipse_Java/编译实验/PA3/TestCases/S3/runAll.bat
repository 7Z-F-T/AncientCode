for %%m in (*.decaf) do java -jar decaf.jar -l 2 %%m > result\%%~nm.tac
java -jar decaf.jar stack.decaf -l 2 -m Stack > result\stack.tac