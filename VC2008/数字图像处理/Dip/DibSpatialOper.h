#ifndef _DIB_SPATIAL_OPERATION_HEADER
#define _DIB_SPATIAL_OPERATION_HEADER
#include "DibOper.h"

#define TEMPLATE_MAX_SIZE 10
class CDibSpatialOper : public CDibOper
{
public:
	enum CConvType
	{
		CONVOLUTE_SMOOTH = 0,
		CONVOLUTE_GAUSSIAN,
		CONVOLUTE_LAPLACIAN,
		CONVOLUTE_INVERSE_LAPLACIAN,
		CONVOLUTE_ROBERTS,
		CONVOLUTE_PREWITT,
		CONVOLUTE_SOBEL,
		CONVOLUTE_ISOBEL,
	};
public:
	BOOL Canny(CDib *pOldDib, CDib *&pNewDib);
	BOOL Susan(CDib *pOldDib, CDib* &pNewDib);
	BOOL EdgeDetect( CDib *pOldDib, CDib*& pNewDib, CConvType convType);
	CDibSpatialOper();
	~CDibSpatialOper();
	BOOL Convolute(CDib * pOldDib, CDib *& pNewDib, CConvType nConvType);
protected:
	void DecideTempl(CConvType convType);
protected:
	float m_pTempl[TEMPLATE_MAX_SIZE][TEMPLATE_MAX_SIZE];
	int m_nSize;
private:
	float Gauss(float x,float std);
	float Dim1Gauss(float x,float std);
	void Dim2Gauss(int n1,float sigma1,int n2,float sigma2,float theta,float *h);
	BOOL Convolute2(CDib * pOldDib, CDib *& pNewDib, float* h, int nSize);//根据数组h提供的模板进行卷积
	void CombineXY(CDib * pTempXDib, CDib * pTempYDib, CDib *& pNewDib);//相加两幅图像，并进行点值的平移，保证可见
};

#endif