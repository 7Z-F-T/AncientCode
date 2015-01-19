#include "head.h"

int main()
{
	int n;//树叶的数量
	int temp;
	int numberNOW=0;//标志当前编号编到几了
	const int MAXIMUM=999999999;
	vector<leaf> tree;
	cout<<"Input the amount of the leaves"<<endl;
	cin>>n;
	cout<<"Input the leaves"<<endl;
	for(int i=1;i<=n;i++)
	{
        cin>>temp;
        numberNOW++;
        tree.push_back(leaf(1,numberNOW,temp));
	}
    sort(tree.begin(),tree.end(),sortleaf());
	for(int i=1;i<=n-1;i++)
	{
		numberNOW++;
		tree.push_back(leaf(0,numberNOW,tree[0].value+tree[1].value,tree[0].number,tree[1].number ));
		tree[0].valueBackup=tree[0].value;
		tree[0].value=MAXIMUM;
		tree[1].valueBackup=tree[1].value;
		tree[1].value=MAXIMUM;
		sort(tree.begin(),tree.end(),sortleaf());

	}
	for(vector<leaf>::reverse_iterator it=tree.rbegin();it<tree.rend();it++)
	{
			it->output();
	}

}