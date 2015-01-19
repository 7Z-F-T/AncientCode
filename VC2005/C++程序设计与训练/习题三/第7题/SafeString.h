#ifndef SAFESTRING_H
#define SAFESTRING_H
class SafeString
{
	char *a;
	int size;
public:
	SafeString();
	SafeString(char * string);
	~SafeString();
	void Show();					// ��ʾ����
	int Length();						// �����ַ�������
	void Assign(char * string);		// ��ֵ
	void Append(char * string);		// ������
	int Find(char ch, int begin = 0);	// ��begin����ʼ����ĳ���ַ�
									// ����-1��ʾû�ҵ�
	void Set(char ch, int index);		// ���õ�index�ַ�Ϊch
	char Get(int index);				// �õ���index���ַ�
};
#endif