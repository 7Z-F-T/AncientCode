#include "Picture.h"

int main()
{
	char *init[] = {"Paris", "in the", "Spring"};

	Picture pic1(init,3);
	cout<<"ԭʼ\n"<<pic1<<endl;

	Picture pic2=frame(pic1);
	cout<<"�ӿ�\n"<<pic2<<endl;

	Picture pic3=pic1|pic2;
	cout<<"��������\n"<<pic3<<endl;
	
	Picture pic4=pic2&pic3;
	cout<<"��������\n"<<pic4<<endl;

	Picture pic5=frame(pic4);
	cout<<"�ټӿ�\n"<<pic5<<endl;

	Picture pic6=reframe(pic5);
	cout<<"�ؼӿ�\n"<<pic6<<endl;

	

	return 0;
}