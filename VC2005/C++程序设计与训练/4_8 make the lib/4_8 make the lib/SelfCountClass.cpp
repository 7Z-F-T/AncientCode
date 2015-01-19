#include "SelfCountClass.h"

int SelfCountClass::count=0;

SelfCountClass::SelfCountClass()
{
	count++;
}
SelfCountClass::~SelfCountClass()
{
	count--;
}
int SelfCountClass::GetObjectCount()
{
	return count;
}
