for %%m in (*.decaf) do java -jar decaf.jar -l 3 %%m >my_result\%%~nm.tac
java -jar decaf.jar stack.decaf -l 3 -m Stack > my_result\stack.tac