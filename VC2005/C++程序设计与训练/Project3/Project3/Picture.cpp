#include "Picture.h"
#include <iostream>
//全局变量，存储边框符号
char c='+';
char s='|';
char t='-';

//Picture
Picture::Picture(const char* const* init, int n):p(new String_Pic(init, n)) {}//构造函数
int Picture::height() const {return p->height();}
int Picture::width() const {return p->width();}
void Picture::display(ostream& out, int x, int y) const
{p->display(out, x, y);}


//String_Pic
String_Pic::String_Pic(const char* const* init , int n)
{
	for(int i=0;i<n;i++)
		data.push_back(init[i]);
}
int String_Pic::width() const
{
	int MaxWidth=0;
	vector<string>::const_iterator iter=data.begin();
	for(;iter<data.end();iter++)
		if(iter->size()>MaxWidth) MaxWidth=iter->size();
	return MaxWidth;
}
int String_Pic::height() const
{
	return data.size();
}
void String_Pic::display(ostream& out, int x, int y) const
{
	int start = 0;
	if (x >=0 && x < height())
	{
		out<<data[x];
		start = data[x].size();
	}
	for (int i = start; i < y; i++)
		out << ' ';
}


//Frame_Pic

Frame_Pic::Frame_Pic(const Picture& pic) : pic(pic) {}
int Frame_Pic::height() const
{
	return pic.height()+2;
}
int Frame_Pic::width() const
{
	return pic.width()+2;
}
void Frame_Pic::display(ostream& out, int x, int y) const
{
	if(x==0)//第一行
	{

		out<<c;
		for(int i=0;i<width()-2;i++)
			cout<<t;
		cout<<c;
		if(width()<y)
			for(int i=1;i<=y-width();i++)
				cout<<' ';
		
	}
	else if(x==(height()-1))//最后一行
	{
		out<<c;
		for(int i=0;i<width()-2;i++)
		    cout<<t;
		cout<<c;
		if(width()<y)
			for(int i=1;i<=y-width();i++)
				cout<<' ';

	}
	else //中间某一行
	{
		cout<<s;
		pic.display(out,x-1,width()-2);
		cout<<s;
		if(width()<y)
			for(int i=1;i<=y-width();i++)
				cout<<' ';

	}
}

//VCat_Pic

VCat_Pic::VCat_Pic(const Picture& pic1, const Picture& pic2):top(pic1),bottom(pic2){}
int VCat_Pic::width() const
{
	return (top.width()>bottom.width()?top.width():bottom.width());
}
int VCat_Pic::height() const
{
	return top.height()+bottom.height();
}
void VCat_Pic::display(ostream& out, int x,int y) const
{
	if(x>=0&&x<top.height()) top.display(out,x,y);
	else if(x>=top.height()&&x<top.height()+bottom.height()) bottom.display(cout,x-top.height(),y);
}

//HCat_Pic

HCat_Pic::HCat_Pic(const Picture & pic1,const Picture & pic2):left(pic1),right(pic2){}
int HCat_Pic::width() const
{
    return left.width()+right.width();
}
int HCat_Pic::height() const
{
	return (left.height()>right.height()?left.height():right.height());
}
void HCat_Pic::display(ostream& out, int x,int y) const
{
	if(x<left.height()) left.display(out,x,left.width());
	else 
		for(int i=0;i<left.width();i++)
			out<<' ';

	if(x<right.height()) right.display(out,x,right.width());
	else
		for(int i=0;i<right.width();i++)
			out<<' ';
}



//Reframe_Pic

Reframe_Pic::Reframe_Pic(const Picture& pic,const char & c1,const char& s1 ,const char& t1):pic(pic),cc(c1),ss(s1),tt(t1){}
void Reframe_Pic::display(ostream& out, int x,int y) const
{
	c=cc;s=ss;t=tt;
	pic.display(out,x,y);
}
int Reframe_Pic::height() const
{
	return pic.height();
}
int Reframe_Pic::width() const
{
	return pic.width();
}

//其他操作函数

ostream& operator << (ostream &out, const Picture &pic)//重载"<<"
{
	int height=pic.height();
	int width=pic.width();
	for (int i = 0; i < height; i++)
	{
		pic.display(out, i, width);
		out << endl;
	}
	c='+';s='|';t='-';
	return out;
}
Picture frame(const Picture& pic)//加边框
{
	return new Frame_Pic(pic);
}
Picture operator&(const Picture& pic1, const Picture& pic2)//纵向连接
{
	return new VCat_Pic(pic1,pic2);
}
Picture operator|(const Picture& pic1, const Picture& pic2)//横向连接
{
	return new HCat_Pic(pic1,pic2);
}
Picture reframe(const Picture& pic,const char& c1,const char& s1,const char& t1)//改变边框输出
{
	return new Reframe_Pic(pic,c1,s1,t1);
}

