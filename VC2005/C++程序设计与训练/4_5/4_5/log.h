#ifndef LOG_H
#define LOG_H
#include <string>
using namespace std;
class Print
{
private:
	string FuncName;
	static int Display;//Display=1���������
public:
	Print(char *pName);
	~Print();
	static void SetDisplay(bool dis);
};
#endif

	