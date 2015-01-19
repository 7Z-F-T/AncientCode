#ifndef SELFCOUNTCLASS_H
#define SELFCOUNTCLASS_H

class SelfCountClass
{
	static int count;
public:
	SelfCountClass();
	~SelfCountClass();
	static int GetObjectCount();
};
#endif