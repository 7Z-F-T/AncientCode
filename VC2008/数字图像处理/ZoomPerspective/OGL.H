#ifndef _OGL_ZOOMPERESPECTIVE_
#define _OGL_ZOOMPERESPECTIVE_
//
class Vector3F
{
public:
      Vector3F(float x=0.0f,float y=0.0f,float z=0.0f)   {  Set(x,y,z); }
      Vector3F(const Vector3F& point)                    {  Set(point.m_v[0],point.m_v[1],point.m_v[2]); }
      //
      void  Set(float x,float y,float z)                 {  m_v[0]=x; m_v[1]=y;   m_v[2]=z; }
      //
      float       operator []( int i) const              {  return m_v[i]; }
      float&      operator []( int i)                    {  return m_v[i]; }
      //
      Vector3F&   operator +=(const Vector3F& v)         {  m_v[0]+=v.m_v[0];  m_v[1]+=v.m_v[1];  m_v[2]+=v.m_v[2];  return *this;  }
      Vector3F&   operator -=(const Vector3F& v)         {  m_v[0]-=v.m_v[0];  m_v[1]-=v.m_v[1];  m_v[2]-=v.m_v[2];  return *this;  }
      Vector3F&   operator *=(const float d)             {  m_v[0]*=d;  m_v[1]*=d;  m_v[2]*=d;  return *this;  }
      //
      float       Dot(const Vector3F& v)        const    {  return (m_v[0]*v.m_v[0]+m_v[1]*v.m_v[1]+m_v[2]*v.m_v[2]);   }
      void        Vector(const Vector3F& b,
                               Vector3F &c)     const    {  c.m_v[0]= m_v[1] * b.m_v[2] - m_v[2] * b.m_v[1];
	                                                         c.m_v[1]= m_v[2] * b.m_v[0] - m_v[0] * b.m_v[2];
	                                                         c.m_v[2]= m_v[0] * b.m_v[1] - m_v[1] * b.m_v[0]; }
      float       SquaredLenght()               const    {  return Dot(*this);   }
      float       Lenght()                      const    {  return (float)sqrt(Dot(*this));   }
      float       Distance(const Vector3F& v)
                     {
                        Vector3F d(v); d-=(*this); return d.Lenght();
                     }
      bool        Normalize()
                     {
                        float L=Lenght();
                        if( (-FLT_EPSILON) < L && L < FLT_EPSILON ) return false;
                        (*this)*=1.0f/L;
                        return true;
                     }
      //
      float m_v[3];
};
//
class CWV
{
private:
   BOOL     m_bIsotropic;
   double   m_Ax;
   double   m_Bx;
   double   m_Ay;
   double   m_By;
   //
   // Device rectangle :
   //
   double   m_W[4]; // size and window insert point
   double   m_V[4]; // size and vwport insert point
   //
   double   Scale(double xiW,double xjW,double xiV,double xjV)
               {
                  double size=xjW-xiW;
                  return ( fabs(size) > FLT_EPSILON ? ((xjV-xiV)/size) : 1.0f );
               }
   void     SetTrasformation(void)
               {
                  m_Ax     = Scale( m_W[0],m_W[2],m_V[0],m_V[2] );
                  m_Ay     = Scale( m_W[1],m_W[3],m_V[3],m_V[1] );
                  //
                  // Isotropic...
                  //
                  double fA= (float)min( fabs(m_Ax),fabs(m_Ay) );
                  m_Ax     = ( m_Ax > 0.0f ? fA : -fA );
                  m_Ay     = ( m_Ay > 0.0f ? fA : -fA );
                  //
                  m_Bx     = ( ( m_V[0] + m_V[2] ) - ( m_W[0] + m_W[2] ) * m_Ax ) * 0.5f;
                  m_By     = ( ( m_V[1] + m_V[3] ) - ( m_W[1] + m_W[3] ) * m_Ay ) * 0.5f;
               }
   //
public:
   CWV()
      {
         m_W[0]   = m_W[1] = -1.0f;
         m_W[2]   = m_W[3] =  1.0f;
         //
         m_V[0]   = m_V[1] = -1.0f;
         m_V[2]   = m_V[3] =  1.0f;
         SetTrasformation();
      }
   virtual ~CWV()      {}
   //
   void  SetWindow(double left,double top,double right,double bottom)
      {
         m_W[0]   = min( left,right  );         
         m_W[1]   = min( top ,bottom );         
         m_W[2]   = max( left,right  );         
         m_W[3]   = max( top ,bottom );         
         SetTrasformation();
      }
   //
   void  SetVwport(double left,double top,double right,double bottom)
      {
         m_V[0]   = min( left,right  );         
         m_V[1]   = min( top ,bottom );         
         m_V[2]   = max( left,right  );         
         m_V[3]   = max( top ,bottom );         
         SetTrasformation();
      }
public:
   double   LogicalToDeviceX(double x)           const { return ( m_Ax * x + m_Bx);  }
   double   LogicalToDeviceY(double y)           const { return ( m_Ay * y + m_By);  }
   double   DeviceToLogicalX(double x)           const { return ( x - m_Bx) / m_Ax;  }
   double   DeviceToLogicalY(double y)           const { return ( y - m_By) / m_Ay;  }
};
//
class CRectD   // a small class for perspective data...
{
public:
      CRectD()    {  left=top=right=bottom=0.0; }
      ~CRectD()   {}
      //
      CRectD&  operator=(const CRectD& r)
                  {
                     left  = r.left  ;
                     top   = r.top   ;
                     right = r.right ;
                     bottom= r.bottom;
                     return *this;
                  }
      double   Width()     const {  return (right-left); }
      double   Height()    const {  return (bottom-top); }
public:
      double   left,top,right,bottom;
};
//
class COGL
{
public:
      COGL();
      virtual  ~COGL();
      //
public:
      //
      // Window Interface :
      //
      virtual  void  PreCreateWindow(CREATESTRUCT& cs)   {  cs.style |= (WS_CLIPCHILDREN | WS_CLIPSIBLINGS);   }
      virtual  void  OnCreate(CView* pView);
      virtual  BOOL  OnEraseBkgnd(CDC*  pDC)             {  return TRUE;   }
      virtual  void  OnDestroy();
      //
      // OpenGL interface :
      //
      virtual  void  OnPreRenderScene(CDC* pDC,const CRect& rcClient);
      virtual  void  OnPostRenderScene(CDC* pDC,const CRect& rcClient);
      //
      // Perspective :
      //
      int         SetupPerspectiveParameters();
      //
      void        SetPerspectiveView(const double angle  ,
                                     const double zNear  ,
                                     const double zFar   );
      //
      void        Zoom(const CRect &rcClient,const CRect& rcSelection);
      void        ZoomAll()
                     {
                        m_rcCurrent = m_rcFull;
                     }
      //
protected:      
      //
      BOOL           Create(HDC hDC);
      void           SetPerspective(const CRect &rcClient);
      //
protected:      
      //
	   HGLRC          m_hGLContext   ;
	   int            m_GLPixelIndex ;
      //
      // Current Zoom rectangle :
      //
      CRectD         m_rcFull       ;
      CRectD         m_rcCurrent    ;
      double         m_fov          ;
      double         m_zNear        ;
      double         m_zFar         ;
      //
      // Perspective Parameter's
      //
      Vector3F       m_lookingPoint ;
      Vector3F       m_eyePoint     ;
      Vector3F       m_upVector     ;
};

#define _OGL_ZOOMPERESPECTIVE_
#endif
