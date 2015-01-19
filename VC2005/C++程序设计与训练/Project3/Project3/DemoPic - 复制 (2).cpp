#include "Picture.h"

int main()
{
	char *init[] = {"Paris", "in the", "Spring"};

	Picture pic1(init,3);
	cout<<"原始\n"<<pic1<<endl;

	Picture pic2=frame(pic1);
	cout<<"加框\n"<<pic2<<endl;

	Picture pic3=pic1|pic2;
	cout<<"横向连接\n"<<pic3<<endl;
	
	Picture pic4=pic2&pic3;
	cout<<"纵向连接\n"<<pic4<<endl;

	Picture pic5=frame(pic4);
	cout<<"再加框\n"<<pic5<<endl;

	Picture pic6=reframe(pic5);
	cout<<"重加框\n"<<pic6<<endl;

	

	return 0;
}