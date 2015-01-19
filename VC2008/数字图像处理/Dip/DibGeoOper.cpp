// DibGeoOper1.cpp: implementation of the CDibGeoOper class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "Dip.h"
#include "DibGeoOper.h"

#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CDibGeoOper::CDibGeoOper()
{

}

CDibGeoOper::~CDibGeoOper()
{

}

BOOL CDibGeoOper::Vertical(CDib *pOldDib, CDib *&pNewDib)
{
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			nValue = pOldDib->GetAt(i,j);
			pNewDib->SetAt(i,nH-1-j,nValue);//翻转
		}
	}
	return TRUE;
}

BOOL CDibGeoOper::Horizon(CDib *pOldDib, CDib *&pNewDib)
{
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 0; i < nW - 1; i++){
		for(int j = 0; j < nH - 1; j++){
			nValue = pOldDib->GetAt(i,j);
			pNewDib->SetAt(nW-1-i,j,nValue);//翻转
		}
	}
	return TRUE;
}

BOOL CDibGeoOper::Affine(CDib *pOldDib, CDib *&pNewDib, float s, float t, float u, float a)
{
	if(pNewDib == NULL)
		pNewDib = new CDib();
	
	CPoint points[4];//原图像四角点
	CPoint newPoints[4];//变换后图像四角点
	CPoint centre = pOldDib->GetCentre();
	//计算原图形四角点
	points[0] = CPoint(0-centre.x,0-centre.y);
	points[1] = CPoint(0-centre.x,pOldDib->Height()-1-centre.y);
	points[2] = CPoint(pOldDib->Width()-1-centre.x,0-centre.y);
	points[3] = CPoint(pOldDib->Width()-1-centre.x,pOldDib->Height()-1-centre.y);
	//计算变换后图形四角点,并求变化后的边界大小
	int maxX = 0, minX = 999999, maxY = 0, minY = 999999;
	for(int i = 0; i < 4; i++){
		newPoints[i] = CalcAffine(points[i].x,points[i].y,s,t,u,a);
		if(newPoints[i].x > maxX)
			maxX = newPoints[i].x;
		if(newPoints[i].x < minX)
			minX = newPoints[i].x;
		if(newPoints[i].y > maxY)
			maxY = newPoints[i].y;
		if(newPoints[i].y < minY)
			minY = newPoints[i].y;
	}
	//建立新的画布
	pNewDib->CopyFromDib(pOldDib,TRUE,maxX-minX+1,maxY-minY+1);
	//pNewDib->CopyFromDib(pOldDib,1,900,600);

	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nOldW = pOldDib->Width();
	int nOldH = pOldDib->Height();
	int nValue;
	CPoint pt = pNewDib->GetCentre();
	for(int i = -nW/2; i < nW/2; i++){
		for(int j = -nH/2; j < nH/2; j++){
			pt = CalcAffineInverse(i,j,s,t,u,a);
			if(!(pt.x<-nOldW/2 || pt.x>=nOldW/2 || pt.y<-nOldH/2 || pt.y>=nOldH/2 )){
				nValue = pOldDib->Get2At(pt.x,pt.y);
				pNewDib->Set2At(i,j,nValue);
			}
		}
	}

	return TRUE;
}

BOOL CDibGeoOper::Perspective(CDib *pOldDib, CDib *&pNewDib, float d, float t, float p, float f)
{
	CPoint pp = CPoint(123,321);
	//pp = CalcPerspective(pp.x,pp.y,1.2,4.3,5.3,1.9);
	pp = CalcPerspectiveInverse(pp.x,pp.y,1,0,0,1);

	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pOldDib);
	
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nOldW = pOldDib->Width();
	int nOldH = pOldDib->Height();
	CPoint pt;
	int nValue;
	for(int i = -nW/2; i < nW/2; i++){
		for(int j = -nH/2; j < nH/2; j++){
			pt = CalcPerspectiveInverse(i,j,d,t,p,f);
			if(!(pt.x<-nOldW/2 || pt.x>=nOldW/2 || pt.y<-nOldH/2 || pt.y>=nOldH/2)){
				nValue = pOldDib->Get2At(pt.x,pt.y);
				pNewDib->Set2At(i,j,nValue);
			}
			else
				pNewDib->Set2At(i,j,0);
		}
	}
	//定轴为画布左和下边缘，而不是中央（可选方案）
	/*for(int i = 0; i < nW; i++){
		for(int j = 0; j < nH; j++){
			pt = CalcPerspectiveInverse(i,j,d,t,p,f);
			if(!(pt.x<0 || pt.x>nOldW-1 || pt.y<0 || pt.y>=nOldH-1)){
				nValue = pOldDib->GetAt(pt.x,pt.y);
				pNewDib->SetAt(i,j,nValue);
			}
			else
				pNewDib->SetAt(i,j,0);
		}
	}*/


	return TRUE;
}

CPoint CDibGeoOper::CalcAffine(int x, int y, float s, float t, float u, float a){
	int new_x = (int)(s*cos(a)*x + (s*t*u*cos(a)-s*t*sin(a))*y + 0.5);
	int new_y = (int)(s*sin(a)*x + (s*t*u*sin(a)+s*t*cos(a))*y + 0.5);
	return CPoint(new_x,new_y);
}
CPoint CDibGeoOper::CalcAffineInverse(int x, int y, float s, float t, float u, float a){
	int new_x = (int)(1/s*(u*sin(a)+cos(a))*x - 1/s*(u*cos(a)-sin(a))*y + 0.5) ;
	int new_y = (int)(-1/s*sin(a)/t*x + 1/s*cos(a)/t*y + 0.5) ;
	return CPoint(new_x,new_y);
}
CPoint CDibGeoOper::CalcPerspective(int x, int y, float d, float t, float p, float f){
	//d = distance, t = theta, p = phai, f = focus
	float x1,y1,z1;
	int x2,y2;
	x1 = cos(p)*x;
	y1 = sin(p)*sin(t)*x + cos(t)*y;
	z1 = sin(p)*cos(t)*x - sin(t)*y;
	x2 = (int)(f*x1/(d+z1) + 0.5);
	y2 = (int)(f*y1/(d+z1) + 0.5);
	return CPoint(x2,y2);
}
CPoint CDibGeoOper::CalcPerspectiveInverse(int x2, int y2, float d, float t, float p, float f){
	float D = x2*f*sin(p) + y2*f*sin(t)*cos(p) + f*f*cos(t)*cos(p);
	float Dx = x2*d*f*cos(t);
	float Dy = x2*d*f*sin(p)*sin(t) + y2*d*f*cos(p);
	int x = (int)(Dx/D + 0.5);
	int y = (int)(Dy/D + 0.5);

	return CPoint(x,y);
}


