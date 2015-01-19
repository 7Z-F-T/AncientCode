#include <iostream>
using namespace std;
float f1() {
  int i = 3, j = 4;
  float k = j / (i - i);
  return k;
}

int main(int argc, char *argv[]) {
  try {    // try后必须是复合语句{}
    f1();
 cout<<"ok"<<endl;
  }
  catch (...)  {   // catch所有异常
    cout << "Something error! -- f1()\n";
  }
  return 0;
}
