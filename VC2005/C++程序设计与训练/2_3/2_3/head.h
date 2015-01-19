#ifndef HEAD_H
#define HEAD_H

struct IntLib
{
private:
	int a;
	int b;
	int c;
public:
	void SetA(int val){a=val;}
	void SetB(int val){b=val;}
	void SetC(int val){c=val;}
	int GetA(){return a;}
	int GetB(){return b;}
	int GetC(){return c;}
};
#endif