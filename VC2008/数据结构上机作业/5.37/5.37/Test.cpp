#include "Tree.cpp"
#include <fstream>

int main()
{
	Tree<char> t;
	ifstream fin("data.txt");
	//ofstream fout("output.txt");
	ofstream fout2("indented output.txt");
	t.CreateTree(fin);
    //t.OutputTree(fout);
	t.IndentedOutput(fout2);
	return 0;
}