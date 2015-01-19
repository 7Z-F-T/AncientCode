#include "log.h"
void Apple();
void Banana();
void Orange();

int main()
{
    Apple();
	Banana();
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
