#ifndef HEAD_H
#define HEAD_H
#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

class Line
{
public:	
	int nWord;
	bool Display;//根据此判断此行是否在搜索结果中显示
	string StringOfLine;//存储此行的字符串
	vector<string> WordOfLine;//存储此行的单词
	void GetWordAmount();//当Line储存普通行时使用
	void GetSearchWordAmount();//当Line储存待搜索行时使用
	void GetWordFromString();//当Line储存普通行时使用
	void GetSearchWordFromString();//当Line储存待搜索行时使用
	void ShowString();
	void Initialize();
	Line ChangeCap();
	Line(){Initialize();}
	Line(int& n,string& str,vector<string>& vec,bool is=true):nWord(n),StringOfLine(str),WordOfLine(vec),Display(is){}
};
bool GetReady(char*&);
int GetLinesAmount(const char* filename);
void Search(string &inputstr);
void GeneralSearch(string &str);//普通搜索
void StringSearch(string &str);//带"的搜索
void DeleteSearch(string &str);//带-的搜索



#endif
