#include <iostream>
#include <time.h> 
#include <stdlib.h> 
#include "MySortTest.h"
using namespace std;

template <class T> bool Less(T a, T b) { return a < b; }
template <class T> bool More(T a, T b) { return a > b; }
template <class T> bool Random(T a, T b)
{
	srand((unsigned)time(NULL)); 
    return(bool(rand()%2));
}

template <class T> void myswap(T &x, T &y)
{ T temp = x; x = y; y = temp; }

template <class iterator, class fun>
void mysort(iterator pa, iterator pb, fun pFun)
{  // ����ѡ������ÿ����ѡһ����ǰ����С�ġ�
   for (iterator pi = pa; pi + 1 != pb; ++pi)
      for (iterator pj = pi + 1; pj != pb; ++pj)
	  if (pFun(*pj, *pi))
	     myswap(*pj, *pi);
}

int main() 
{
   int x[10] = {2, 5, 12, 4, 1, 56, 11, 23, 67, 89};
   int choice;
   cout<<"��ѡ������ʽ��1������С���� 2�����Ӵ�С 3�������";
   cin>>choice;
   switch(choice)
   {
   case 1:
   mysort(x, x + 10, Less<int>);
   for (int i = 0; i < 10; ++i)
      cout << x[i] << ' ';
   cout << endl;break;
   case 2:
   mysort(x, x + 10, More<int>);
   for (int i = 0; i < 10; ++i)
      cout << x[i] << ' ';
   cout << endl;break;
   case 3:
   mysort(x, x + 10, Random<int>);
   for (int i = 0; i < 10; ++i)
      cout << x[i] << ' ';
   cout << endl;break;
   }
   return 0;
}
