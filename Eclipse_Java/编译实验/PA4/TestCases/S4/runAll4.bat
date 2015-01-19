for %%m in (*.decaf) do java -jar decaf.jar -l 4 %%m > my_result\%%~nm.s
java -jar decaf.jar stack.decaf -l 4 -m Stack > my_result\stack.s