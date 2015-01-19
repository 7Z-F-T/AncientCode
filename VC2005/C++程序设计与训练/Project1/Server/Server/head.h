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
	bool Display;//���ݴ��жϴ����Ƿ��������������ʾ
	string StringOfLine;//�洢���е��ַ���
	vector<string> WordOfLine;//�洢���еĵ���
	void GetWordAmount();//��Line������ͨ��ʱʹ��
	void GetSearchWordAmount();//��Line�����������ʱʹ��
	void GetWordFromString();//��Line������ͨ��ʱʹ��
	void GetSearchWordFromString();//��Line�����������ʱʹ��
	void ShowString();
	void Initialize();
	Line ChangeCap();
	Line(){Initialize();}
	Line(int& n,string& str,vector<string>& vec,bool is=true):nWord(n),StringOfLine(str),WordOfLine(vec),Display(is){}
};
bool GetReady(char*&);
int GetLinesAmount(const char* filename);
void Search(string &inputstr);
void GeneralSearch(string &str);//��ͨ����
void StringSearch(string &str);//��"������
void DeleteSearch(string &str);//��-������



#endif
