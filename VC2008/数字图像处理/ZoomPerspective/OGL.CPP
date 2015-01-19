#include "stdafx.h"
#include "ZoomPerspective.h"
#include "ogl.h"
#include "DlgSetup.h"


#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

COGL::COGL()
{
      m_hGLContext   = NULL;
      //
      // Set view point / looking point / up vector !
      //
      SetPerspectiveView(40.0,1.0,10000.0);
      //
      // Set view point / looking point / up vector !
      //
      m_lookingPoint.Set(  0.0f,  0.0f,  0.0f);
      m_eyePoint.Set(     15.0f, 25.0f, 22.0f);
      m_upVector.Set(      0.0f,  0.0f,  1.0f);
}

COGL::~COGL()
{
}

void  COGL::OnCreate(CView* pView)
{
      HWND  hWnd  = pView->GetSafeHwnd();
      HDC   hDC   = ::GetDC( hWnd );
      //
      if( hDC ){
         if( Create(hDC) ){
            //
            // One time initialization...
            //
		      ::wglMakeCurrent(hDC,m_hGLContext);
            //
	         ::glPolygonMode(GL_FRONT,GL_FILL);
	         ::glPolygonMode(GL_BACK,GL_FILL);
            ::glShadeModel(GL_SMOOTH);
		      ::wglMakeCurrent(NULL,NULL);
         }
         ::ReleaseDC(hWnd,hDC);
      }
}

BOOL  COGL::Create(HDC hDC)
{
	   PIXELFORMATDESCRIPTOR pixelDesc;
      //
	   pixelDesc.nSize		      = sizeof(PIXELFORMATDESCRIPTOR);
	   pixelDesc.nVersion	      = 1;
      //
      pixelDesc.dwFlags	         = PFD_DRAW_TO_WINDOW | 
				                       PFD_SUPPORT_OPENGL |
     			                       PFD_STEREO_DONTCARE|
										     PFD_SWAP_COPY      |
	    			                    PFD_DOUBLEBUFFER   ;
      //
	   pixelDesc.iPixelType	      = PFD_TYPE_RGBA;
	   pixelDesc.cColorBits	      = 32;
	   pixelDesc.cRedBits	      = 8;
	   pixelDesc.cRedShift	      = 16;
	   pixelDesc.cGreenBits	      = 8;
	   pixelDesc.cGreenShift      = 8;
	   pixelDesc.cBlueBits	      = 8;
	   pixelDesc.cBlueShift	      = 0;
	   pixelDesc.cAlphaBits	      = 0;
	   pixelDesc.cAlphaShift      = 0;
	   pixelDesc.cAccumBits	      = 64;	
	   pixelDesc.cAccumRedBits		= 16;
	   pixelDesc.cAccumGreenBits	= 16;
	   pixelDesc.cAccumBlueBits	= 16;
	   pixelDesc.cAccumAlphaBits	= 0;
	   pixelDesc.cDepthBits		   = 32;
	   pixelDesc.cStencilBits		= 8;
	   pixelDesc.cAuxBuffers		= 0;
	   pixelDesc.iLayerType		   = PFD_MAIN_PLANE;
	   pixelDesc.bReserved		   = 0;
	   pixelDesc.dwLayerMask		= 0;
	   pixelDesc.dwVisibleMask		= 0;
	   pixelDesc.dwDamageMask		= 0;
      //
	   m_GLPixelIndex = ::ChoosePixelFormat( hDC, &pixelDesc);
	   if( m_GLPixelIndex == 0 ){ // Let's choose a default index.
		   m_GLPixelIndex = 1;	
		   if( ::DescribePixelFormat(hDC,m_GLPixelIndex, 
						                 sizeof(PIXELFORMATDESCRIPTOR), 
						                 &pixelDesc) == 0 ){
			   return FALSE;
		   }
	   }
      //
	   if( ::SetPixelFormat( hDC, m_GLPixelIndex, &pixelDesc) == FALSE ){
		   return FALSE;
	   }
	   m_hGLContext = ::wglCreateContext(hDC);
	   return ( m_hGLContext != NULL );
}

void  COGL::OnDestroy()
{
	   if( m_hGLContext == NULL ) return;
      //
	   if( ::wglGetCurrentContext() != NULL ) 
		   ::wglMakeCurrent(NULL, NULL) ;
      //
	   if( m_hGLContext != NULL ){
		   ::wglDeleteContext(m_hGLContext);
		   m_hGLContext = NULL;
	   }
}

void  COGL::OnPreRenderScene(CDC* pDC,const CRect& rcClient)
{
	   if( m_hGLContext == NULL ) return;
      //
		::wglMakeCurrent(pDC->m_hDC,m_hGLContext);
      //
      ////////////////////////////
      //
      // Clear background..
      //
      float backColor[4]={ 0.0f,0.0f,0.0f,1.0f };
      //
	   ::glClearColor(backColor[0],backColor[1],backColor[2],backColor[3]);
	   ::glDrawBuffer(   GL_BACK        );
		::glDisable(      GL_DEPTH_TEST                           );       
	   ::glClear(        GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT );
    	::glEnable(       GL_DEPTH_TEST                           );
	   ::glDepthFunc(    GL_LEQUAL );
      //
      // Some setup's :
      //
      ::glFrontFace(    GL_CCW         );
      ::glCullFace(     GL_BACK        );
      ::glEnable(       GL_DEPTH_TEST  );
   	::glPolygonMode(  GL_FRONT_AND_BACK,GL_FILL);
      //
      // Setup Viewport :
      //
   	::glViewport( 0, 0, rcClient.Width() , rcClient.Height() );
      //
      SetPerspective(rcClient);
}

void  COGL::OnPostRenderScene(CDC* pDC,const CRect& rcClient)
{
	   if( m_hGLContext == NULL ) return;
	   //
		::SwapBuffers(pDC->m_hDC);
		::wglMakeCurrent(NULL,NULL);
}

void  COGL::SetPerspectiveView(const double angle  ,
                               const double zNear  ,
                               const double zFar   )
{
      double   angleRad = angle * 3.141592653589793238 / 180.0;
      //
      m_fov          = angle  ;
      m_zNear        = zNear  ;
      m_zFar         = zFar   ;
      m_rcFull.bottom= zNear * tan( angleRad * 0.5 );
      m_rcFull.top   = -m_rcFull.bottom;
      m_rcFull.left  =  m_rcFull.top   ;
      m_rcFull.right =  m_rcFull.bottom;
      //
      m_rcCurrent    =  m_rcFull;
}


void  COGL::Zoom(const CRect &rcClient,const CRect& rcZoom)
{
      CWV   trasf;
      //
      trasf.SetWindow( m_rcCurrent.left       ,
                       m_rcCurrent.top        ,
                       m_rcCurrent.right      ,
                       m_rcCurrent.bottom     );
      //
      trasf.SetVwport( (double)rcClient.left    ,
                       (double)rcClient.top     ,
                       (double)rcClient.right   ,
                       (double)rcClient.bottom  );
      //
	   double   left     = trasf.DeviceToLogicalX((double)rcZoom.left  );
	   double   top      = trasf.DeviceToLogicalY((double)rcZoom.top   );
	   double   right    = trasf.DeviceToLogicalX((double)rcZoom.right );
	   double   bottom   = trasf.DeviceToLogicalY((double)rcZoom.bottom);
      //
      m_rcCurrent.left   = min(left,right);
      m_rcCurrent.top    = min(top,bottom);
      m_rcCurrent.right  = max(left,right);
      m_rcCurrent.bottom = max(top,bottom);
}

void  COGL::SetPerspective(const CRect &rcClient)
{
      double   aspect= (double)rcClient.Width()/(double)rcClient.Height();
      //
      double   sx=1.0,sy=1.0,tx=0.0,ty=0.0;
      double   m[16] = { 1.0, 0.0, 0.0, 0.0,
                         0.0, 1.0, 0.0, 0.0,
                         0.0, 0.0, 1.0, 0.0,
                         0.0, 0.0, 0.0, 1.0};
      //
      //////////////////////
      //
      // Scaling :
      //
      double   fullSizeN   = m_rcFull.Width();  // = m_rcFull.Height()
      double   halfSizeN   = fullSizeN * 0.5;
      double   sxL         = fullSizeN / m_rcCurrent.Width()  ;
      double   syL         = fullSizeN / m_rcCurrent.Height() ;
      //
      if( (sxL*aspect) > syL )
         sx = syL/aspect;
      else
         sx = sxL;
      //
      sy    = sx * aspect;
      //
      // Translate :
      //
      int            viewport[4];
      //
      ::glGetIntegerv(  GL_VIEWPORT , viewport );
      //
      double   logCenterX  = ( m_rcCurrent.right  + m_rcCurrent.left   ) * 0.5;
      double   logCenterY  = ( m_rcCurrent.bottom + m_rcCurrent.top    ) * 0.5;
	   double   W	         = viewport[2];
	   double   H	         = viewport[3];
      double   xCenter     = ( logCenterX + halfSizeN ) * W / fullSizeN + viewport[0];
      double   yCenter     = ( logCenterY + halfSizeN ) * H / fullSizeN + viewport[1];
      double   width       = W / sx;
      double   height      = H / sy;
      //
      tx    = ( W + 2.0 * ( viewport[0] - xCenter ) ) / width ;
      ty    = ( H + 2.0 * ( viewport[1] - yCenter ) ) / height;
      //
      m[0]  = sx;
      m[12] = tx;
      m[5]  = sy;
      m[13] = ty;
      //
      ///// Ok setup projection matrix 
      //
	   ::glMatrixMode(   GL_PROJECTION     );
	   ::glLoadIdentity( );
      ::glMultMatrixd( m );
      ::glFrustum( m_rcFull.left    ,
                   m_rcFull.right   ,
                   m_rcFull.top     ,
                   m_rcFull.bottom  ,
                   m_zNear , m_zFar );
      //
      // and model matrix :
      //
	   ::glMatrixMode(   GL_MODELVIEW      );
      ::glLoadIdentity();
      ::gluLookAt(m_eyePoint[0]    ,m_eyePoint[1]     ,m_eyePoint[2]     ,
                  m_lookingPoint[0],m_lookingPoint[1] ,m_lookingPoint[2] ,
                  m_upVector[0]    ,m_upVector[1]     ,m_upVector[2]     );
}

int   COGL::SetupPerspectiveParameters()
{
      CDlgSetupPerspective dlg;
      int                  nResult;
      //
      dlg.m_fov      = m_fov              ;
      dlg.m_zNear    = m_zNear            ;
      dlg.m_zFar     = m_zFar             ;
      dlg.m_xeye     = m_eyePoint[0]      ;
      dlg.m_yeye     = m_eyePoint[1]      ;
      dlg.m_zeye     = m_eyePoint[2]      ;
      dlg.m_xcenter  = m_lookingPoint[0]  ;
      dlg.m_ycenter  = m_lookingPoint[1]  ;
      dlg.m_zcenter  = m_lookingPoint[2]  ;
      //
      if( (nResult=dlg.DoModal()) == IDOK ){
         //
         SetPerspectiveView(dlg.m_fov  ,dlg.m_zNear,dlg.m_zFar );
         //
         m_eyePoint[0]      = (float)dlg.m_xeye    ;
         m_eyePoint[1]      = (float)dlg.m_yeye    ;
         m_eyePoint[2]      = (float)dlg.m_zeye    ;
         m_lookingPoint[0]  = (float)dlg.m_xcenter ;
         m_lookingPoint[1]  = (float)dlg.m_ycenter ;
         m_lookingPoint[2]  = (float)dlg.m_zcenter ;
      }                      
      return nResult;
}
