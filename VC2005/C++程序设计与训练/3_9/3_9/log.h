#ifndef LOG_H
#define LOG_H
#include <string>
using namespace std;
class Print
{
private:
	string FuncName;
public:
	Print(char *pName);
	~Print();
};
#endif

	