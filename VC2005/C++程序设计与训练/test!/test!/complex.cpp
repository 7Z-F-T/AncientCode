#include <iostream>
using namespace std;
float f1() {
  int i = 3, j = 4;
  float k = j / (i - i);
  return k;
}

int main(int argc, char *argv[]) {
  try {    // try������Ǹ������{}
    f1();
 cout<<"ok"<<endl;
  }
  catch (...)  {   // catch�����쳣
    cout << "Something error! -- f1()\n";
  }
  return 0;
}
