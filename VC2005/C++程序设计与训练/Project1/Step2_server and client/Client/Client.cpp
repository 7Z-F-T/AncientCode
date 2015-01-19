#include "soapProxy.h"
#include <iostream>
#include <string>
#include <vector>
#include "pthread.h"
#include "sched.h"
#include "soapStub.h"
using namespace std;

int main(int argc,char* argv[])
{
	Service serv;
	serv.endpoint=argv[1];
	SearchService__Result result;
	string input;
	for(;;)
	{
	    cout<<"--Please input words or strings to search(type exit to quit)--:"<<endl;
	    getline(cin,input,'\n');
		if(input=="exit") return 0;
		if(serv.SearchService__Search(input,result)==SOAP_OK)
		{
			if(result.m_vecItem.size()==0) cout<<"--Found 0 line(s)--\n"<<endl;
			else cout<<"--Found "<<result.m_vecItem.size()<<" line(s)\n"<<endl;
			vector<struct SearchService__Item>::iterator iter=result.m_vecItem.begin();
			for(;iter<result.m_vecItem.end();iter++)
			{
				cout<<"Line Number:"<<iter->m_nLine<<endl;
				cout<<"  Content:"<<iter->m_strContent<<endl;
			}
		}
		else soap_print_fault(serv.soap, stderr);
	}
	return 0;
}

struct Namespace namespaces[] = 
{
	{"SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/", "http://www.w3.org/*/soap-envelope", NULL},
	{"SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/", "http://www.w3.org/*/soap-encoding", NULL},
	{"xsi", "http://www.w3.org/2001/XMLSchema-instance", "http://www.w3.org/*/XMLSchema-instance", NULL},
	{"xsd", "http://www.w3.org/2001/XMLSchema", "http://www.w3.org/*/XMLSchema", NULL},
	{"SearchService", "http://tempuri.org/SearchService.xsd", NULL, NULL},
	{NULL, NULL, NULL, NULL}
};