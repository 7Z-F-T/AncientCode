#ifndef PICTURE_H
#define PICTURE_H

#include <iostream>
#include <string.h>
#include <vector>
#include <string>
using namespace std;



class P_Node
{
	friend class Picture;
protected:
	virtual int height() const=0;
	virtual int width() const=0;
	virtual void display(ostream& out, int x, int y) const=0;
private:
	int use;
};
class Picture
{
	P_Node *p;
	friend std::ostream& operator<<(std::ostream&, const Picture&);
	friend Picture frame(const Picture&);
	friend Picture operator& (const Picture&, const Picture&);
	friend Picture operator| (const Picture&, const Picture&);
	friend Picture reframe(const Picture& ,const char& ,const char& ,const char& );
	friend class Frame_Pic;
	friend class VCat_Pic;
	friend class HCat_Pic;
	friend class Reframe_Pic;
	Picture(P_Node* p_node) : p(p_node) {}
	int height() const;
    int width() const;
    void display(ostream&, int, int) const;
public:
	Picture();
	Picture(const char* const *, int);
	Picture(const Picture &pic):p(pic.p){}
	Picture& operator=(const Picture&);
};

class String_Pic: public P_Node
{
	friend class Picture;
	String_Pic(const char* const*, int);
	~String_Pic();
	vector<string> data;
	int height() const;
	int width() const;
	void display(ostream&, int, int) const;

};
class Frame_Pic: public P_Node
{
	friend Picture frame(const Picture&);
	Frame_Pic(const Picture&);
	int height() const;
	int width() const;
	void display(ostream&, int,int) const;
	Picture pic;
};

class VCat_Pic: public P_Node
{
	friend Picture operator& (const Picture&, const Picture&);
	VCat_Pic(const Picture&, const Picture&);
	int height() const;
	int width() const;
	void display(ostream& out, int ,int) const;
	Picture top, bottom;
};


class HCat_Pic: public P_Node
{
	friend Picture operator| (const Picture&, const Picture&);
	HCat_Pic(const Picture&, const Picture&);
	int HCat_Pic::width() const;
    int HCat_Pic::height() const;
	void display(ostream& out, int ,int) const;
	Picture left, right;
};
class Reframe_Pic:public P_Node
{
	friend Picture reframe(const Picture& ,const char& ,const char& ,const char& );
    void display(ostream& out, int ,int ) const;
	Picture pic;
	Reframe_Pic(const Picture&,const char& ,const char& ,const char& );
	int height() const;
	int width() const;
	char cc;
	char ss;
	char tt;


};



Picture frame(const Picture&);
Picture operator&(const Picture&, const Picture&);//纵向连接
Picture operator|(const Picture&, const Picture&);//横向连接
ostream& operator<<(ostream&, const Picture&);
Picture reframe(const Picture &,const char&,const char&,const char&);


#endif

