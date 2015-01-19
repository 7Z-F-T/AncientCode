#include <iostream>
using namespace std;

class Expr_node {
  friend class Expr;
  friend ostream& operator << (ostream &o,const Expr &t);
  friend int calc();

protected:
  virtual void print(ostream&) const = 0;
  virtual Expr_node* Clone() const = 0;
  virtual ~Expr_node() {}
public:
  virtual int calc() const=0;

};



class Expr {
  friend ostream& operator << (ostream&, const Expr&);
  friend class Expr_node;
  friend class Int_node;
  friend class Unary_node;
  friend class Binary_node;

  
  Expr_node* p; // 具体类型要到创建节点时才知道
public:
  Expr(int);		  // 创建 Int_node
  Expr(char, Expr);  	  // 创建 Unary_node
  Expr(char, Expr, Expr); // 创建 Binary_node
  Expr(const Expr&);
 ~Expr() { delete p; }
 
  Expr& operator = (const Expr&);
  int eval();

  
};




class Int_node: public Expr_node {
  friend class Expr;
  int n;
  Int_node(int k): n(k) { }
  Expr_node* Clone() const;
  int calc() const;
  void print(ostream &o) const { o << n; }
};
class Unary_node: public Expr_node {
  friend class Expr;
  char op;
  Expr opnd;
  Expr_node* Clone() const;
  Unary_node(char a, Expr b):op(a),opnd(b) {}
  int calc() const;
  void print(ostream &o) const 
  { o << "(" << op << opnd << ")"; }
};
class Binary_node: public Expr_node {
  friend class Expr;
  char op; 
  Expr left;
  Expr right;
  Expr_node* Clone() const;
  Binary_node(char a, Expr b, Expr c):op(a), left(b), right(c) { }
  int calc() const;
  void print(ostream &o) const 
  { o << "(" << left << op << right << ")"; }
};



Expr::Expr(int n) : p(new Int_node(n)) {}
Expr::Expr(char op, Expr t):p(new Unary_node(op, t)) {}
Expr& Expr::operator = (const Expr& exp)
{
	p=exp.p->Clone();
	return *this;
}
Expr::Expr(char op, Expr left, Expr right):p(new Binary_node(op, left, right)) {}
Expr::Expr(const Expr& exp)
{
    p=exp.p->Clone();
}
int Expr::eval()
{
	return p->calc();
}


Expr_node* Int_node::Clone() const{ return new Int_node(n); }
Expr_node* Unary_node::Clone() const{ return new Unary_node(op, opnd); }
Expr_node* Binary_node::Clone() const{ return new Binary_node(op, left, right); }

int Int_node::calc() const
{
	return n;
}
int Unary_node::calc() const
{
	if(op=='+') return opnd.p->calc();
	else if(op=='-') return -(opnd.p->calc());
	cout<<"表达式输入错误!"<<endl;return 0;
}
int Binary_node::calc() const
{
	if(op=='+') return left.p->calc()+right.p->calc();
	else if(op=='-') return left.p->calc()-right.p->calc();
	else if(op=='*') return left.p->calc()*right.p->calc();
	cout<<"表达式输入错误!"<<endl;return 0;

}




ostream& operator << (ostream &o,const Expr &t)
{ t.p->print(o); return o; }
