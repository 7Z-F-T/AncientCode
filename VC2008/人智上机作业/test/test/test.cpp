#include <set>
#include <iostream>
using namespace std;

int main(){
	bool explorered[9][9][9][9][9][9][9][9][9];
	for(int i1=0;i1<9;i1++){
		for(int i2=0;i2<9;i2++){
			for(int i3=0;i3<9;i3++){
				for(int i4=0;i4<9;i4++){
					for(int i5=0;i5<9;i5++){
						for(int i6=0;i6<9;i6++){
							for(int i7=0;i7<9;i7++){
								for(int i8=0;i8<9;i8++){
									for(int i9=0;i9<9;i9++){
										explorered[i1][i2][i3][i4][i5][i6][i7][i8][i9]=false;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	set<int> a;
	set<int>::iterator it;
	a.insert(6);
	a.insert(7);
	a.insert(2);
	cout<<*(a.begin());
	for(it=a.begin();it!=a.end();it++)
		cout<<*it<<" ";

	return 0;
}