#include <iostream>
using namespace std;

class queen {
  const int col;		// 自己所在列
  //int pos;			// 自己所在位置
  queen *prev;		// 前一个皇后
  bool Attack(int, int);	// 能否攻击某位置
  bool TryNextPos();		// 尝试下一可能位置
public:
	int pos;
  queen(int c) : col(c), pos(0), prev(0){}  // 初始化属于哪一列
  bool FindPos(); 		  	 	    // 找到可能位置
  int GetPos() const {return pos;}   	    // 输出自己的位置
  void SetPrev(queen *pQueen) {prev = pQueen;}// 设置前一个皇后
};

  bool queen::FindPos() {
  while (pos < 8)
    if (!prev || !prev->Attack(pos, col)) return true;
    else pos ++;	// 尝试下一位置
  if (!prev || !prev->TryNextPos()) // 要求前一个移动
    return false;
  pos = 0;		// 前一个移动了，重新从位置0开始尝试
  return FindPos();
}

bool queen::Attack(int x, int y) {
  if (x == pos) return true;		// 同一行
  if (x + y == pos + col) return true;  // 对角线1
  if (x - y == pos - col) return true;  // 对角线2
  if (prev) return prev->Attack(x, y);  // 前一个皇后能否攻击
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
		{cout<<"以上为全部解，共"<<count<<"组"<<endl;
	    return 0;}
	}
    
    for (int i = 0; i < 8; i++)
    cout << Queens[i].GetPos() << ' ';
    cout << endl;count++;
    Queens[7].pos++;
  }
  



  return 0;
}
