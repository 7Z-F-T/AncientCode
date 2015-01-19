#include "stdafx.h"
#include "DibSpatialOper.h"
#define pi 3.1415926

CDibSpatialOper::CDibSpatialOper()
{
	m_nSize	 = 0;	
}
CDibSpatialOper::~CDibSpatialOper()
{
}

BOOL CDibSpatialOper::Convolute(CDib * pOldDib, CDib *& pNewDib, CConvType convType)
{
	SetCursor(LoadCursor(0, IDC_WAIT));
	DecideTempl(convType);
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 1; i < nW - 1; i++){
		for(int j = 1; j < nH - 1; j++){
			nValue = 0;
			for(int ii = 0; ii < m_nSize; ii++){
				for(int jj = 0; jj < m_nSize; jj++){
					nValue += (int)(m_pTempl[ii][jj] * pOldDib->GetAt(i+m_nSize/2-jj, j-m_nSize/2+ii));
				}
			}
			if(convType == CONVOLUTE_LAPLACIAN)
				pNewDib->SetAt(i,j,nValue-127);
			else
				pNewDib->SetAt(i,j,nValue);
		}
	}
	SetCursor(LoadCursor(0, IDC_ARROW));
	return TRUE;
}

BOOL CDibSpatialOper::Convolute2(CDib * pOldDib, CDib *& pNewDib, float* h, int nSize)
{
	SetCursor(LoadCursor(0, IDC_WAIT));
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue;
	for(int i = 1; i < nW - 1; i++){
		for(int j = 1; j < nH - 1; j++){
			nValue = 0;
			for(int ii = 0; ii < nSize; ii++){
				for(int jj = 0; jj < nSize; jj++){
					nValue += (int)(h[ii*nSize+jj] * pOldDib->GetAt(i+nSize/2-jj, j-nSize/2+ii));
				}
			}
			pNewDib->SetAt(i,j,nValue/2+126);
		}
	}
	SetCursor(LoadCursor(0, IDC_ARROW));
	return TRUE;
}


BOOL CDibSpatialOper::EdgeDetect(CDib *pOldDib, CDib *&pNewDib, CConvType convType)
{
	SetCursor(LoadCursor(0, IDC_WAIT));
	DecideTempl(convType);
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pOldDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue1,nValue2;
	for(int i = 1; i < nW - 1; i++){
		for(int j = 1; j < nH - 1; j++){
			nValue1 = 0;
			nValue2 = 0;
			for(int ii = 0; ii < m_nSize; ii++){
				for(int jj = 0; jj < m_nSize; jj++){
					nValue1 += (int)(m_pTempl[ii][jj] * pOldDib->GetAt(i+m_nSize/2-jj, j-m_nSize/2+ii));//g(x)
					nValue2 += (int)(m_pTempl[ii+m_nSize][jj] * pOldDib->GetAt(i+m_nSize/2-jj, j-m_nSize/2+ii));//g(y)
				}
			}
			pNewDib->SetAt(i,j,abs(nValue1)+abs(nValue2));//这里用求绝对值和代替求均方值，减少计算量
		}
	}
	SetCursor(LoadCursor(0, IDC_ARROW));
	return TRUE;
}

void CDibSpatialOper::DecideTempl(CConvType convType)
{
	switch(convType)
	{
	case CONVOLUTE_SMOOTH:
		m_nSize = 3;
		m_pTempl[0][0] = m_pTempl[0][1] = m_pTempl[0][2] = (float)1.0/9;
		m_pTempl[1][0] = m_pTempl[1][1] = m_pTempl[1][2] = (float)1.0/9;
		m_pTempl[2][0] = m_pTempl[2][1] = m_pTempl[2][2] = (float)1.0/9;
		break;
	case CONVOLUTE_GAUSSIAN:
		m_nSize = 3;
		m_pTempl[0][0] = 0.0625; m_pTempl[0][1] = 0.125;  m_pTempl[0][2] = 0.0625;
		m_pTempl[1][0] = 0.125; m_pTempl[1][1] = 0.25; m_pTempl[1][2] = 0.125;
		m_pTempl[2][0] = 0.0625; m_pTempl[2][1] = 0.125; m_pTempl[2][2] = 0.0625;
		break;
	case CONVOLUTE_LAPLACIAN:
		m_nSize = 3;
		m_pTempl[0][0] = 0; m_pTempl[0][1] = -1;  m_pTempl[0][2] = 0;
		m_pTempl[1][0] = -1; m_pTempl[1][1] = 4; m_pTempl[1][2] = -1;
		m_pTempl[2][0] = 0; m_pTempl[2][1] = -1; m_pTempl[2][2] = 0;
		break;

	case CONVOLUTE_ROBERTS:
		m_nSize = 2;
		m_pTempl[0][0] = 0;m_pTempl[0][1] = 1;
		m_pTempl[1][0] =-1;m_pTempl[1][1] = 0;
		m_pTempl[2][0] = 1;m_pTempl[2][1] = 0;
		m_pTempl[3][0] =0;m_pTempl[3][1] = -1;
		break;

	case CONVOLUTE_PREWITT:
		m_nSize = 3;
		m_pTempl[0][0] =-1;m_pTempl[0][1] = 0;m_pTempl[0][2] = 1;
		m_pTempl[1][0] =-1;m_pTempl[1][1] = 0;m_pTempl[1][2] = 1;
		m_pTempl[2][0] =-1;m_pTempl[2][1] = 0;m_pTempl[2][2] = 1;
		m_pTempl[3][0] =-1;m_pTempl[3][1] = -1;m_pTempl[3][2] = -1;
		m_pTempl[4][0] = 0;m_pTempl[4][1] = 0;m_pTempl[4][2] = 0;
		m_pTempl[5][0] = 1;m_pTempl[5][1] = 1;m_pTempl[5][2] = 1;
		break;

	case CONVOLUTE_SOBEL:
		m_nSize = 3;
		m_pTempl[0][0] =-1;m_pTempl[0][1] = 0 ;m_pTempl[0][2] = 1;
		m_pTempl[1][0] =-2;m_pTempl[1][1] = 0 ;m_pTempl[1][2] = 2;
		m_pTempl[2][0] =-1;m_pTempl[2][1] = 0 ;m_pTempl[2][2] = 1;
		m_pTempl[3][0] =-1;m_pTempl[3][1] = -2;m_pTempl[3][2] = -1;
		m_pTempl[4][0] = 0;m_pTempl[4][1] = 0 ;m_pTempl[4][2] = 0;
		m_pTempl[5][0] = 1;m_pTempl[5][1] = 2 ;m_pTempl[5][2] = 1;
		break;

	case CONVOLUTE_ISOBEL:
		m_nSize = 3;
		m_pTempl[0][0] =-1;	m_pTempl[0][1] = 0;	m_pTempl[0][2] = 1;
		m_pTempl[1][0] =-(float)sqrt(2.0);	m_pTempl[1][1] = 0;	m_pTempl[1][2] =(float)sqrt(2.0);
		m_pTempl[2][0] =-1;			m_pTempl[2][1] = 0;	m_pTempl[2][2] = 1;
		m_pTempl[3][0] =-1;	m_pTempl[3][1] = -(float)sqrt(2.0);	m_pTempl[3][2] = -1;
		m_pTempl[4][0] = 0;	m_pTempl[4][1] = 0;	m_pTempl[4][2] = 0;
		m_pTempl[5][0] = 1;	m_pTempl[5][1] = (float)sqrt(2.0);	m_pTempl[5][2] = 1;
		break;
	}
}

BOOL CDibSpatialOper::Susan(CDib *pOldDib, CDib *&pNewDib)
{

	return TRUE;
}

BOOL CDibSpatialOper::Canny(CDib *pOldDib, CDib *&pNewDib)
{
	int Nx1=10, Nx2=10;
	float Sigmax1=1, Sigmax2=1, Theta1=pi/2;
	int Ny1=10, Ny2=10;
	float Sigmay1=1, Sigmay2=1, Theta2=0;
	float h1[100];
	float h2[100];

	CDib* pTempXDib = NULL;
	CDib* pTempYDib = NULL;
	//计算x方向差值
	Dim2Gauss(Nx1,Sigmax1,Nx2,Sigmax2,Theta1,h1);
	Convolute2(pOldDib,pTempXDib,h1,10);
	//计算y方向差值
	Dim2Gauss(Ny1,Sigmay1,Ny2,Sigmay2,Theta2,h2);
	Convolute2(pOldDib,pTempYDib,h2,10);
	//组合x、y方向结果
	CombineXY(pTempXDib,pTempYDib,pNewDib);
	
	return TRUE;
}

float CDibSpatialOper::Gauss(float x,float std)
{
	return (float)(exp(-x*x/(2*std*std))/(std*sqrt(2*PI)));
}
float CDibSpatialOper::Dim1Gauss(float x,float std)
{
	return(-x * Gauss(x,std)/std*std);
}

void CDibSpatialOper::Dim2Gauss(int n1,float sigma1,int n2,float sigma2,float theta,float *h)
{
	double r[2][2]={cos(theta),-sin(theta),sin(theta),cos(theta)};
	float u[2],sum=0.0,*p = h;

	for(int i = 1;i<=n2;i++)
		for(int j = 1;j<=n1;j++){
	        u[0] = (float)(r[0][0]*(j-(n1+1)/2)+r[0][1]*(i-(n2+1)/2));
	        u[1] = (float)(r[1][0]*(j-(n1+1)/2)+r[1][1]*(i-(n2+1)/2)); 
			h[(i-1)*n1+(j-1)]  = Gauss(u[0],sigma1)*(-u[1])*Gauss(u[1],sigma2)/(sigma2*sigma2);
		}
	for(int i=0; i<n1*n2; i++)	sum+=h[i]*h[i]; sum =(float)sqrt(sum);
	for(int i=0; i<n1*n2; i++)	h[i]/=sum;
}

void CDibSpatialOper::CombineXY(CDib * pTempXDib, CDib * pTempYDib, CDib *& pNewDib){
	if(pNewDib == NULL)
		pNewDib = new CDib();
	pNewDib->CopyFromDib(pTempXDib);
	int nW = pNewDib->Width();
	int nH = pNewDib->Height();
	int nValue1,nValue2;
	for(int i = 1; i < nW - 1; i++){
		for(int j = 1; j < nH - 1; j++){
			nValue1 = (pTempXDib->GetAt(i,j)-126)*2;
			nValue2 = (pTempYDib->GetAt(i,j)-126)*2;
			pNewDib->SetAt(i,j,sqrt(0.0 + nValue1*nValue1+nValue2*nValue2));
		}
	}
}