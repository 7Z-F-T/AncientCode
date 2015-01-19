#include "log.h"
void Apple();
void Banana();
void Orange();

int Print::Display=0;

int main()
{
	Print::SetDisplay(false);
	Apple();
	Print::SetDisplay(true);
	Banana();
	Print::SetDisplay(false);
	Orange();
}

void Apple()
{
	Print print("Apple");
}

void Banana()
{
	Print print("Bababa");
}
void Orange()
{
	Print print("Orange");
}
