#include <iostream>
using namespace std;

int col_left[4]={2,2,2,2};//ÿ�л����Դ򼸸���
int taken[4][4]={{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
int count=0;//�ܾ���
int planNO=1;//�������

void recursion(int i)//iΪ�к�
{
	if(i==4)//�Ѿ������ˣ���ʼ��⣬�ϸ�����
	{
		if(count==8)
		{
			cout<<planNO<<endl;
			for(int m=0;m<4;m++)
			{
				for(int n=0;n<4;n++)
					if(taken[m][n]==1) cout<<"#";
					else cout<<"*";
				cout<<endl;
			}
			planNO++;
		}
	}
	else
	{
		for(int s=0;s<3;s++)
		{
			if(col_left[s]>0)
			{
				taken[i][s]=1;
				col_left[s]--;
				count++;

	            for(int j=s+1;j<4;j++)
	            {
    		        if(col_left[j]>0)
                    {
		                taken[i][j]=1;
        		        col_left[j]--;
        		        count++;

        		        recursion(i+1);
       
        		        taken[i][j]=0;
    		            col_left[j]++;
    		            count--;
					}
	    		}

				taken[i][s]=0;
				col_left[s]++;
				count--;
		    }
		}
			
	}
}



int main()
{

	recursion(0);

	return 0;
}