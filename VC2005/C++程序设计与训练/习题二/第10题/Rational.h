#ifndef RATIONAL_H
#define RATIONAL_H
class Rational
{
private:
	int num;	// ·Ö×Ó
	int den;	// ·ÖÄ¸
public:
	int  GetNum();
	void SetNum(int num);
	int  GetDen();
	bool SetDen(int den);
	bool Assign(int num, int den);
	void Copy(Rational r);	// *this = r
	void Add(Rational r);	// *this += r
	void Sub(Rational r);   // *this -= r
	void Mul(Rational r);	// *this *= r
	bool Div(Rational r);	// *this /= r
	bool Equal(Rational r);	// *this == r
};
#endif