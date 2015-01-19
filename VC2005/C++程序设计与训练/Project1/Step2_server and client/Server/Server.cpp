#include "soapH.h" 
#include "head.h"
#include <iostream>
#include "pthread.h"
#include "sched.h"


#include "pthread.h" 
#define BACKLOG (100) // Max. request backlog

extern int nLine;
extern vector<Line> Text;

int main(int argc,char* argv[])
{
	if(argc!=3) {cout<<"--Wrong command format！--";return -1;}
    struct soap soap; 	
	soap_init(&soap); 
	soap.send_timeout = soap.recv_timeout = 60; // 60 seconds 
	soap.max_keep_alive = 100; // max keep-alive sequence 
	void *process_request(void*); 
	struct soap *tsoap; 
	pthread_t tid; 
	int port = atoi(argv[2]); // nPort是soap服务的端口号
	SOAP_SOCKET m, s; 
	m = soap_bind(&soap, NULL, port, BACKLOG); 
    // strIP是std::string类型，存有soap服务的IP地址（或localhost）
    // BACKLOG 是 Max. request backlog，可以在main外定义为常量100
	if (!soap_valid_socket(m)) 
		// 有错误，不能启动监听
	{
		cout<<"Service error!"<<endl;
		exit(1);
	}
	char* filename=argv[1];
	if(GetReady(filename)==true)
	cout<<"Service started.Listening..."<<endl;

	for (;;) 
	{ 	// 已成功启动
		s = soap_accept(&soap); 
		if (!soap_valid_socket(s)) 
		{ 
			if (soap.errnum)
			{ 
				soap_print_fault(&soap, stderr); 
				exit(1); 
			} 
			fprintf(stderr, "server timed out\n"); 
			break; 
		} 
		fprintf(stderr, "Query from IP %d.%d.%d.%d\n",  
			(soap.ip >> 24)&0xFF, (soap.ip >> 16)&0xFF, 
			(soap.ip >> 8)&0xFF, soap.ip&0xFF); 
		tsoap = soap_copy(&soap); // make a safe copy 
		if (!tsoap) 
			break; 
		pthread_create(&tid, NULL, (void*(*)(void*))process_request, (void*)tsoap); 
	} 
	soap_done(&soap); // detach soap struct
	return 0;
}


void *process_request(void *soap) 
{ 
   pthread_detach(pthread_self()); 
   soap_serve((struct soap*)soap); 
   soap_destroy((struct soap*)soap); // dealloc C++ data 
   soap_end((struct soap*)soap); // dealloc data and clean up 
   soap_done((struct soap*)soap); // detach soap struct 
   free(soap); 
   return NULL; 
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


int SearchService__Search (struct soap *soap,string query,struct SearchService__Result &result)
{
	for(int i=0;i<nLine;i++)
		Text[i].Display=true;
	for(string::iterator iter=query.begin();iter<query.end();iter++)
		if((*iter)>='A'&&(*iter)<='Z') (*iter)+=32;
    Line input;
	input.Initialize();
	input.StringOfLine=query;
	input.GetWordAmount();
	input.GetSearchWordFromString();
	result.m_vecItem.clear();

	for(int i=0;i<input.nWord;i++)
	{
		
		string::iterator iter1,iter2;
		iter1=input.WordOfLine[i].begin();
		iter2=--input.WordOfLine[i].end();
		if(*iter1=='\"'&&*iter2=='\"')
			StringSearch(input.WordOfLine[i]);
		else if(*iter1=='-')
			DeleteSearch(input.WordOfLine[i]);
		else 
			GeneralSearch(input.WordOfLine[i]);
	}
	for(int i=0;i<nLine;i++)
		if(Text[i].Display==true)
			result.m_vecItem.push_back(SearchService__Item(i+1,Text[i].StringOfLine));
	return SOAP_OK;	
}