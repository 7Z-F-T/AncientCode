#ifndef SAFESTRING_H
#define SAFESTRING_H
class SafeString
{
	char *a;
	int size;
public:
	SafeString();
	SafeString(const SafeString &x);
	SafeString(char * string);
	~SafeString();
	void Show() const;					// ��ʾ����
	int Length() const;						// �����ַ�������
	void Assign(char * string);		// ��ֵ
	void Append(char * string);		// ������
	int Find(char ch, int begin = 0) const;	// ��begin����ʼ����ĳ���ַ�
									// ����-1��ʾû�ҵ�
	void Set(char ch, int index);		// ���õ�index�ַ�Ϊch
	char Get(int index) const;				// �õ���index���ַ�
	SafeString& operator=(const SafeString &x);
	SafeString& operator=(char *p);
	SafeString& operator+=(const SafeString &x);
	SafeString& operator+=(char *p);
	friend SafeString operator+(const SafeString &x1,const SafeString &x2);

};
SafeString operator+(const SafeString &x1,const SafeString &x2);
#endif