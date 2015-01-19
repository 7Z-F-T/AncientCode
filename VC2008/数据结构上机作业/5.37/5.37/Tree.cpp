#include "head.h"

template<class T>
TreeNode<T>* Tree<T>::FirstChild()
{
	if(current!=NULL) return current->firstChild;
	else return NULL;
}

template<class T>
TreeNode<T>* Tree<T>::NextSibling()
{
	if(current!=NULL) return current->nextSibling;
	else return NULL;
}

template<class T>
void Tree<T>::CreateTree(ifstream &fin)
{
	root=createTree(fin);
}
template<class T>
TreeNode<T>* Tree<T>::createTree(ifstream &fin)
{
	char ctrChar;
	T input;
	fin>>input;
	TreeNode<T>* p=new TreeNode<T>(input);
	fin>>ctrChar;
	if(ctrChar=='(') 
	{   p->firstChild=createTree(fin);
		fin>>ctrChar;
	    if(ctrChar==',') 
	    {
		    p->nextSibling=createTree(fin);
	    }
		else if(ctrChar!=')') fin.unget();
	}
	else if(ctrChar==',') p->nextSibling=createTree(fin);
	return p;
}
template<class T>
void Tree<T>::OutputTree(ofstream &fout)
{
	outputTree(fout,root);
}
template<class T>
void Tree<T>::outputTree(ofstream &fout,TreeNode<T>* p)
{
	if(p==NULL) return;
	else
	{
		
		while(1)
	    {
			fout<<p->data;
	        if(p->firstChild!=NULL)
	        {
		    fout<<'(';
		    outputTree(fout,p->firstChild);
		    fout<<')';
	        }
	    if((p=p->nextSibling)!=NULL) fout<<',';
		else break;
	    }
	}
}
template<class T>
void Tree<T>::IndentedOutput(ofstream &fout)
{
	indentedOutput(fout,root,0);
}
template<class T>
void Tree<T>::indentedOutput(ofstream &fout, TreeNode<T>* p, int space)
{
    if(p==NULL) return;
	else
	{
		while(1)
		{
			for(int i=0;i<space;i++)
				fout<<" ";
			fout<<p->data<<endl;
			if(p->firstChild!=NULL)
			{
				indentedOutput(fout,p->firstChild,space+4);
			}
			if((p=p->nextSibling)==NULL) break;
		}

	}
}
