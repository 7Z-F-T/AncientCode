#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	int missle[40000];
	int n;
	cin>>n;
	for(int i=0;i<n;i++)
	    cin>>missle[i];
	vector<int> system;//�洢�Ѿ����õĸ��׷���ϵͳ�ĸ���ʣ��߶�ֵ
	int max=0;//����ϵͳʣ��߶ȵ����ֵ
	for(int i=0,j=0;i<n;i++)
	{
		j=0;
		if(missle[i]>max)
		{
			system.push_back(missle[i]);
			sort(system.begin(),system.end());
			max=missle[i];
		}
		else 
		{
			while(j<system.size())
			{
				if(system[j]>missle[i])
				{
					system[j]=missle[i];
                    sort(system.begin(),system.end());
					max=system[system.size()-1];
					break;
				}
				j++;
			}
		}

	}
	cout<<system.size()<<endl;



	return 0;

}