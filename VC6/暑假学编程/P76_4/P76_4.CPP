#include<iostream>

int main()
{
    int Lesson[4][3];//老师A,B,C编号1,2,3.课程语文,算术,政治,地理,音乐,美术编号1,2,3,4,5,6
	int i,j,k,l,m,n,o,p,q;
	for(i=1;i<=2;i++)//第一个老师选课,先选他教的第几课程,再选到底教什么
		for(j=1;j<=6;j++)
		{
			Lesson[1][i]=j;
			for(k=1;k<=2;k++)//第二个老师选课
				for(l=1;l<=6;l++)
				{
					Lesson[2][k]=l;
					for(m=1;m<=2;m++)
						for(n=1;n<=6,n++)
						{
							Lesson[3][m]=n
						}
				}

    

		
}