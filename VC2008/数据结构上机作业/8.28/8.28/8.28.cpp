#include"Graphpath.h"
#include<limits>

template<class T, class E>
int centre(Graphlnk<T,E>& gl, E& biasdist)
{
	const int vertices = gl.NumberOfVertices();
	int* tmpath = new int [vertices];
	int i,j;
	int** distance = new int *[vertices];
	for(i=0;i<vertices;i++)
		distance[i] = new int [vertices+1];
	E min = maxWeight;int nomin = -1;
	for(i=0;i<vertices;i++)
	{
		distance[i][vertices] = 0;
		Dijkstra(gl,i,distance[i],tmpath);
		for(j=0;j<vertices;j++)
		{
			if(j != i)
				distance[i][vertices] += distance[i][j];
		}
		if(distance[i][vertices] < min)
		{
			min = distance[i][vertices];
			nomin = i;
		}
	}
	for(i=0;i<vertices;i++)
		delete [] distance[i];
	delete [] distance;
	delete [] tmpath;
	biasdist = min;
	return nomin;
}

int main()
{
	Graphlnk<char,int> gl;
	cin>>gl;
	int rad;
	int nodeid = centre(gl,rad);
	cout<<"The centre node is "<<gl.getValue(nodeid)<<endl;
	//cout<<"\nThe total distance is:"<<rad<<endl;
	cout<<"End of Algorithm"<<endl;
	char tmp;cin>>tmp;
	return 0;
}