#ifndef HEAD_H
#define HEAD_H
#include <iostream>
#include <string>
#include <vector>
#include <string.h>
#include <stdlib.h>

using namespace std;

class node
{
public:
	node(int n):num(n){}
    int num;
};
class line
{
public:
	line():num(0),start(0),end(0){}
	line(int n1,int n2,int n3):num(n1),start(n2),end(n3){}
    int num;
	int start;
	int end;
};

void guanlianTOlinjie();
void linjieTOguanlian();
void guanlianTObianlie();
void bianlieTOguanlian();
void linjieTOzhengxiang();
void zhengxiangTOlinjie();


#endif