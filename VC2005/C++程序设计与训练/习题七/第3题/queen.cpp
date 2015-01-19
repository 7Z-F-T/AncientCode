#include <iostream>
using namespace std;

class queen {
  const int col;		// �Լ�������
  //int pos;			// �Լ�����λ��
  queen *prev;		// ǰһ���ʺ�
  bool Attack(int, int);	// �ܷ񹥻�ĳλ��
  bool TryNextPos();		// ������һ����λ��
public:
	int pos;
  queen(int c) : col(c), pos(0), prev(0){}  // ��ʼ��������һ��
  bool FindPos(); 		  	 	    // �ҵ�����λ��
  int GetPos() const {return pos;}   	    // ����Լ���λ��
  void SetPrev(queen *pQueen) {prev = pQueen;}// ����ǰһ���ʺ�
};

  bool queen::FindPos() {
  while (pos < 8)
    if (!prev || !prev->Attack(pos, col)) return true;
    else pos ++;	// ������һλ��
  if (!prev || !prev->TryNextPos()) // Ҫ��ǰһ���ƶ�
    return false;
  pos = 0;		// ǰһ���ƶ��ˣ����´�λ��0��ʼ����
  return FindPos();
}

bool queen::Attack(int x, int y) {
  if (x == pos) return true;		// ͬһ��
  if (x + y == pos + col) return true;  // �Խ���1
  if (x - y == pos - col) return true;  // �Խ���2
  if (prev) return prev->Attack(x, y);  // ǰһ���ʺ��ܷ񹥻�
  return false;
}

bool queen::TryNextPos() {
  pos ++;
  return FindPos();
}

int main() {
  queen Queens[8] = {queen(0), queen(1), queen(2),
			   queen(3), queen(4), queen(5),
			   queen(6), queen(7)};
  for (int i = 1; i < 8; i++) 
    Queens[i].SetPrev(&Queens[i-1]);

  int count=0;
    
  while(Queens[0].pos<8)
  {
	for (int i = 1; i < 8; i++)
	{
		if(!Queens[i].FindPos())
		{cout<<"����Ϊȫ���⣬��"<<count<<"��"<<endl;
	    return 0;}
	}
    
    for (int i = 0; i < 8; i++)
    cout << Queens[i].GetPos() << ' ';
    cout << endl;count++;
    Queens[7].pos++;
  }
  



  return 0;
}
