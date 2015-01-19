rename blackjack.tac blackjack
for %%m in (*.tac) do java -jar tac.jar %%m > %%~nm.out
rename blackjack blackjack.tac