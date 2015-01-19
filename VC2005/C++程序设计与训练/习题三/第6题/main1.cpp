int Func(int num);
int Func(int num,int num2);

int Func(int num)
{
	return num;
}

int Func(int num,int num2)
{
	return num+num2;
}
int main()
{
	Func ( int(3.14), int('Y') );
	Func ( int(2004.0824) );
	return 0;
}
