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
	void Show() const;					// 显示内容
	int Length() const;						// 返回字符串长度
	void Assign(char * string);		// 赋值
	void Append(char * string);		// 串连接
	int Find(char ch, int begin = 0) const;	// 从begin处开始查找某个字符
									// 返回-1表示没找到
	void Set(char ch, int index);		// 设置第index字符为ch
	char Get(int index) const;				// 得到第index个字符
	SafeString& operator=(const SafeString &x);
	SafeString& operator=(char *p);
	SafeString& operator+=(const SafeString &x);
	SafeString& operator+=(char *p);
	friend SafeString operator+(const SafeString &x1,const SafeString &x2);

};
SafeString operator+(const SafeString &x1,const SafeString &x2);
#endif