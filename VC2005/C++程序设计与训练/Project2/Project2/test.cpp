#include "head.h"
int main(int argc,char* argv[])
{
	/*string str1="xxx";
    Node n1(str1);
	RepeatNode op(n1);
	Node no(&op);

	string str2="123";
	string str3="abc";
	Node n2(str2);
	Node n3(str3);
	vector<Node> NodeV;
	NodeV.push_back (n2);
	NodeV.push_back (n3);
	NodeV.push_back (no);
	CombineNode com(NodeV);
	Node no2(&com);
	no2.ContentOutput (cout);
	no2.NetOutput (cout);*/
    char* filename=argv[1];
	ifstream fin(filename);
	if(!fin) cout<<"Cannot open file!"<<endl;

	
    
	//读取源文件除最后一行的所有内容，入strV
	extern vector<definedstr> strV;
	string temp;
	getline(fin,temp,'\n');
	while(temp.size()!=0&&temp[0]!='(')
	{
		
		string::iterator iter;
		for(iter=temp.begin();iter<temp.end();iter++)//先清除不必要的空格
		{
			if(*iter==' ')
			{
				if(
					(*(iter-1)=='('||*(iter-1)==')'||*(iter-1)=='<'||*(iter-1)=='>'||*(iter-1)=='['||*(iter-1)==']'||*(iter-1)=='|'||*(iter-1)=='=')
					||
					(*(iter+1)==' '||*(iter+1)=='('||*(iter+1)==')'||*(iter+1)=='<'||*(iter+1)=='>'||*(iter+1)=='['||*(iter+1)==']'||*(iter+1)=='|'||*(iter+1)=='=')
					)
				{temp.erase(iter);iter--;}
			}
		}
		for(iter=temp.begin();iter<temp.end();iter++)//$配对
		{
			if(*iter=='$')
			{
				for(;;iter++)
				{
					if(*(iter)==' '||*(iter)=='('||*(iter)==')'||*(iter)=='<'||*(iter)=='>'||*(iter)=='['||*(iter)==']'||*(iter)=='|'||*(iter)=='=')
					{temp.insert(iter-temp.begin(),"$");break;}
				}
			}
		}

		char* p=new char[temp.size()+1];
		iter=temp.begin();
		int i;
		for(i=0;iter<temp.end();iter++)
		{
		    p[i]=*iter;
			i++;
		}
		p[i]='\0';
		char* left=strtok(p," =");
		char* right=strtok(NULL,";");
        strV.push_back (definedstr(left,right));
        getline(fin,temp,'\n');
	}
	//读取最后一行内容
		string::iterator iter;
		for(iter=temp.begin();iter<temp.end();iter++)
		{
			if(*iter==' ')
			{
				if(
					(*(iter-1)=='('||*(iter-1)==')'||*(iter-1)=='<'||*(iter-1)=='>'||*(iter-1)=='['||*(iter-1)==']'||*(iter-1)=='|'||*(iter-1)=='=')
					||
					(*(iter+1)==' '||*(iter+1)=='('||*(iter+1)==')'||*(iter+1)=='<'||*(iter+1)=='>'||*(iter+1)=='['||*(iter+1)==']'||*(iter+1)=='|'||*(iter+1)=='=')
					)
				{temp.erase(iter);iter--;}
			}
		}
		temp.erase(temp.begin());temp.erase (--temp.end());
		definedstr endstr("end",temp);
		for(iter=endstr.store.begin();iter<endstr.store.end();iter++)//$配对
		{
			if(*iter=='$')
			{
				for(;;iter++)
				{
					if(*(iter)==' '||*(iter)=='('||*(iter)==')'||*(iter)=='<'||*(iter)=='>'||*(iter)=='['||*(iter)==']'||*(iter)=='|'||*(iter)=='=')
					{endstr.store.insert(iter-endstr.store.begin(),"$");break;}
				}
			}
		}
		//cout<<endstr.name<<" "<<endstr.store;


		//进行每个终结符的$包装工作
		vector<definedstr>::iterator strVit;
        string::iterator storeit,storeit2;
		string::iterator storeit_rev;
		string::iterator tempit;
		string::size_type pos=0;
		string storetempstr;
		int i=0;
		bool need=true;
		char a;
		
		while(i!=strV.size())
			{
				
				need=true;
                strVit=strV.begin ();
				for(int j=0;j<i;j++)
					strVit++;
               	storeit=(*strVit).store.begin();
				for(;storeit<(*strVit).store.end();storeit++)
				{
					
					if(*storeit!='('&&*storeit!=')'&&*storeit!='['&&*storeit!=']'&&*storeit!='<'&&*storeit!='>'&&*storeit!='|'&&*storeit!='$'&&need==true)
					{
						if(storetempstr.size()!=0)
						    {if(*(--storetempstr.end())!='$') {storetempstr.push_back ('$');need=false;}}
						else storetempstr.push_back ('$');need=false;
						
					}
					else if((*storeit=='('||*storeit==')'||*storeit=='['||*storeit==']'||*storeit=='<'||*storeit=='>'||*storeit=='|')&&need==false)
					{
						if(storetempstr.size()!=0)
						    {if(*(--storetempstr.end())!='$') {storetempstr.push_back ('$');need=true;}}
						else storetempstr.push_back ('$');need=true;
					}
					storetempstr.push_back (*storeit);
				}
				//cout<<*(--(*strVit).store.end());
				if(*(--storetempstr.end())!='('&&*(--storetempstr.end())!=')'&&*(--storetempstr.end())!='['&&*(--storetempstr.end())!=']'&&*(--storetempstr.end())!='<'&&*(--storetempstr.end())!='>'&&*(--storetempstr.end())!='|'&&*(--storetempstr.end())!='$')
					storetempstr.append("$");
				(*strVit).store.assign(storetempstr);
				storetempstr.clear();
				i++;
		    }	
		i=0;
		int k=0;
		string temp1,temp2;
		while(i!=strV.size())
			{
				strVit=strV.begin ();
				for(int j=0;j<i;j++)
					strVit++;
               	storeit=(*strVit).store.begin();
				for(storeit=(*strVit).store.begin();storeit<(*strVit).store.end();storeit++)
				{
					
					if(*storeit=='$')
					{
						storeit2=storeit;
				        for(storeit++;storeit<(*strVit).store.end();storeit++)
						{
							if(*storeit=='$')
							{
								k=storeit-(*strVit).store.begin();
								for(tempit=storeit2;tempit<=storeit;tempit++)
								    temp1.push_back (*tempit);
								for(tempit=++storeit2;tempit<=--storeit;tempit++)
									temp2.push_back (*tempit);
								storeit++;
								strV.push_back (definedstr(temp1,temp2));
								strVit=strV.begin ();
				                for(int j=0;j<i;j++)
					                strVit++;
								storeit=(*strVit).store.begin();
								for(int j=0;j<k;j++)
									storeit++;
								temp1.clear();temp2.clear();

							    break;	
							}
							
						}
					}
				}
				i++;
		}
								





		
			
       /* //进行<>的处理工作
		string nametemp,storetemp;
		    
	
		
		i=0;
		while(i!=strV.size())
			{
                strVit=strV.begin ();
				for(int j=0;j<i;j++)
					strVit++;
               	storeit=(*strVit).store.begin();
				for(;storeit<(*strVit).store.end();storeit++)
				{
					if(*storeit=='<')
					{
						storeit_rev=--(*strVit).store.end();
						for(;storeit_rev>storeit;storeit_rev--)
						{
							if(*storeit_rev=='>')
							{
								*storeit='$';*storeit_rev='$';
								for(tempit=storeit;tempit<=storeit_rev;tempit++)
									nametemp.push_back (*tempit);
								for(tempit=++storeit;tempit<=--storeit_rev;tempit++)
									storetemp.push_back(*tempit);
								storeit--;storeit_rev++;
								strV.push_back (definedstr(nametemp,storetemp));
								goto next;
							}
						}
					    
					}
				}
			next:i++;
			}
		
		*/




//最后一行的<>处理
		string nametemp,storetemp;
			storeit=endstr.store.begin();
				for(;storeit<endstr.store.end();storeit++)
				{
					if(*storeit=='<')
					{
						storeit_rev=--endstr.store.end();
						for(;storeit_rev>storeit;storeit_rev--)
						{
							if(*storeit_rev=='>')
							{
								*storeit='$';*storeit_rev='$';
								for(tempit=storeit;tempit<=storeit_rev;tempit++)
									nametemp.push_back (*tempit);
                                *storeit='<';*storeit_rev='>';
								for(tempit=storeit;tempit<=storeit_rev;tempit++)
									storetemp.push_back(*tempit);
								strV.push_back (definedstr(nametemp,storetemp));
								
							}
						}
					    
					}
				}

		vector<definedstr>::iterator disit;
	    disit=strV.begin ();
		for(;disit<strV.end ();disit++)
			cout<<"name:"<<(*disit).name<<"  "<<"store:"<<(*disit).store<<endl;


		disit=--strV.end();
		Node nod=disit->createNode();
		nod.ContentOutput (cout);
		nod.NetOutput (cout);



    
}
