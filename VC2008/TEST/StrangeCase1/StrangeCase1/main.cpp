#include <stdio.h>
#include <stdlib.h>

struct A{
	int a;
	int b;
};

struct A * pA;

void modify(){
	if(pA == NULL){
		struct A test;//wrong way to allocate new variable!
		test.a = 1;
		test.b = 2;
		pA = &test;
	}
}

void modify2(){
	if(pA == NULL){
		struct A * test = (struct A *)malloc(sizeof(struct A));//correct way!
		test->a = 1;
		test->b = 2;
		pA = test;
	}
}

int main(){
	pA = NULL;
	modify();//wrong
	//modify2();//correct
	printf("%d\n",pA->a);
	printf("%d\n",pA->b);//wrong output here
}