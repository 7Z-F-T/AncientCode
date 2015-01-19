//### This file created by BYACC 1.8(/Java extension  1.13)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//###           14 Sep 06  -- Keltin Leung-- ReduceListener support, eliminate underflow report in error recovery
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 11 "Parser.y"
package decaf.frontend;

import decaf.ast.*;
import decaf.error.*;
import java.util.*;
//#line 24 "Parser.java"
interface ReduceListener {
  public boolean onReduce(String rule);
}




public class Parser
             extends BaseParser
             implements ReduceListener
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

ReduceListener reduceListener = null;
void yyclearin ()       {yychar = (-1);}
void yyerrok ()         {yyerrflag=0;}
void addReduceListener(ReduceListener l) {
  reduceListener = l;}


//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:SemValue
String   yytext;//user variable to return contextual strings
SemValue yyval; //used to return semantic vals from action routines
SemValue yylval;//the 'lval' (result) I got from yylex()
SemValue valstk[] = new SemValue[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new SemValue();
  yylval=new SemValue();
  valptr=-1;
}
final void val_push(SemValue val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    SemValue[] newstack = new SemValue[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final SemValue val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final SemValue val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short VOID=257;
public final static short BOOL=258;
public final static short INT=259;
public final static short DOUBLE=260;
public final static short STRING=261;
public final static short CLASS=262;
public final static short NULL=263;
public final static short EXTENDS=264;
public final static short THIS=265;
public final static short WHILE=266;
public final static short FOR=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short RETURN=270;
public final static short BREAK=271;
public final static short NEW=272;
public final static short PRINT=273;
public final static short READ_INTEGER=274;
public final static short READ_LINE=275;
public final static short STRING_CONST=276;
public final static short INT_CONST=277;
public final static short BOOL_CONST=278;
public final static short DOUBLE_CONST=279;
public final static short IDENTIFIER=280;
public final static short AND=281;
public final static short OR=282;
public final static short STATIC=283;
public final static short LESS_EQUAL=284;
public final static short GREATER_EQUAL=285;
public final static short EQUAL=286;
public final static short NOT_EQUAL=287;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    4,    5,    5,    5,    5,    5,
    5,    5,    6,    6,    6,    7,    7,    8,    2,   10,
   10,   11,   11,   11,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    9,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   20,   20,   20,   20,   20,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,   21,   21,
   21,   21,   24,   24,   13,   14,   15,   15,   23,   23,
   25,   25,   22,   22,   22,   12,   12,   12,   26,   16,
   17,   18,   18,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    1,    1,    1,    1,
    2,    3,    3,    1,    0,    7,    6,    3,    6,    2,
    0,    2,    2,    0,    2,    2,    3,    3,    3,    3,
    2,    2,    2,    0,    1,    1,    2,    2,    2,    2,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    3,    3,    3,    4,    5,    3,    3,    3,
    3,    3,    2,    3,    3,    3,    3,    3,    3,    3,
    3,    2,    3,    1,    4,    1,    2,    1,    6,    4,
    1,    0,    3,    1,    4,    3,    1,    0,    1,    5,
    9,    7,    5,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    3,    0,    2,    0,    0,   20,   24,
    0,   10,    8,    6,    7,    9,    0,    0,   19,   22,
    0,    0,   23,   11,    0,    4,    0,    0,    0,    0,
   12,    0,   14,    0,    0,    0,    5,    0,    0,    0,
   13,   34,   17,   16,    0,   48,   50,    0,    0,    0,
    0,   76,    0,    0,    0,    0,   47,   44,   46,   45,
    0,    0,    0,    0,   18,   25,   26,    0,    0,    0,
    0,   31,   32,   33,   49,    0,    0,    0,    0,    0,
    0,    0,   51,   52,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   27,   28,   29,   30,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   54,   55,    0,    0,   53,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   56,    0,    0,   75,   80,
    0,   85,   35,   36,    0,    0,    0,    0,   41,   42,
   43,   90,    0,    0,   57,    0,    0,   37,   38,   39,
   40,    0,    0,   79,    0,   92,    0,   91,
};
final static short yydgoto[] = {                          2,
    3,    4,  153,   21,   34,   35,   23,  154,   45,    8,
   11,  155,  156,  157,  158,  159,  160,  161,  162,   75,
   76,   83,   84,  124,  125,  115,
};
final static short yysindex[] = {                      -261,
 -250,    0, -261,    0, -233,    0, -248,  -82,    0,    0,
   80,    0,    0,    0,    0,    0, -227,  -21,    0,    0,
   17,  -89,    0,    0,  -78,    0,   48,   -3,   61,  -21,
    0,  -21,    0,  -63,  -15,   11,    0,  -21,  -13,  -13,
    0,    0,    0,    0,  321,    0,    0,   77,   95,   96,
  259,    0,  310,  105,  110,  120,    0,    0,    0,    0,
  123,  259,  259,  259,    0,    0,    0,   43,   52,   78,
  108,    0,    0,    0,    0,  166,   85,    0,  259,  259,
  259,  166,    0,    0,  130,   81,  259,  142,  147,  259,
  -32,  -32,  106,    0,    0,    0,    0,  259,  259,  259,
  259,  259,  259,  259,  259,  259,  259,  259,  259,  259,
 -109,  259,  259,  166,  152,  114,  155,  159,  389,  166,
   41,    0,    0,  154,  163,    0,  419,  233,  394,  394,
  430,  430,    3,    3,  -32,  -32,  -32,  394,  394,  167,
  132,  166,  361,  259,  361,    0,  139,  259,    0,    0,
  259,    0,    0,    0,  162,  165,  168,  170,    0,    0,
    0,    0,  172,  -47,    0,  166,  193,    0,    0,    0,
    0,  259,  361,    0,  201,    0,  361,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,  206,    0,  128,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  184,    0,    0,   56,
    0,   56,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  187,    0,    0,    0,    0,    0,
  194,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -37,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  173,    1,    0,  187,
    0,  195,    0,    0,    0,    0,    0,    0,    0,  211,
   27,   36,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   -2,    0,    0,    0,    0,    0,   84,
    0,    0,    0,  221,    0,    0,  292,  643,  121,  222,
  482,  642,  614,  634,   62,   71,   97,  439,  447,  -26,
    0,   25,  187,    0,  187,    0,    0,    0,    0,    0,
  211,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  286,    0,  117,    0,    0,    0,    0,
    0,  224,  187,    0,    0,    0,  187,    0,
};
final static short yygindex[] = {                         0,
    0,  264,   -8,  157,   40,  236,    0,   87,    0,    0,
    0,  -18,  226,  228,  229,  232,  239,  244,  -54,    0,
  668,  227,  470,  182,  140,  -69,
};
final static int YYTABLESIZE=925;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         84,
    1,   28,   20,   84,   84,   84,   84,   84,   84,   84,
   83,  117,   28,  111,   83,   83,   83,   83,   83,   83,
   83,   84,   84,   84,   84,   39,   68,   28,   38,    5,
    7,    9,   83,   83,   83,   83,   66,   52,   89,  108,
   10,   87,   52,   52,  106,   52,   52,   52,  111,  107,
   22,   40,   24,   84,   38,   84,   89,   25,  112,   87,
   52,  116,   52,   63,   83,   86,   83,   63,   63,   63,
   63,   63,   72,   63,  163,   26,   72,   72,   72,   72,
   72,  149,   72,   86,  148,   63,   63,   30,   63,   31,
  164,   52,   86,  112,   72,   72,   15,   72,   60,   15,
   32,   94,   60,   60,   60,   60,   60,   61,   60,   42,
   95,   61,   61,   61,   61,   61,   79,   61,  176,   63,
   60,   60,  178,   60,   74,   43,   44,   74,   72,   61,
   61,   67,   61,   62,   80,   81,   96,   62,   62,   62,
   62,   62,  108,   62,   87,  113,  126,  106,  104,   88,
  105,  111,  107,  175,   60,   62,   62,   73,   62,   89,
   73,   65,   90,   61,   65,  110,   97,  109,  108,  118,
  140,  119,  144,  106,  104,  108,  105,  111,  107,   65,
  106,  104,  122,  105,  111,  107,   33,  123,   33,   62,
   27,  110,  143,  109,   41,  145,  112,  148,  110,  146,
  109,   29,  108,  150,   19,    1,  151,  106,  104,   51,
  105,  111,  107,   65,   51,   51,   37,   51,   51,   51,
  168,  173,  112,  169,  152,  110,  170,  109,  171,  112,
  172,  165,   51,  174,   51,   12,   13,   14,   15,   16,
   17,  177,    5,   84,   84,   88,   84,   84,   84,   84,
   21,   82,   78,   77,   83,   83,  112,   83,   83,   83,
   83,   81,   67,   51,   88,   67,    6,   36,  121,  108,
   69,   77,   70,   71,  106,  104,   72,  105,  111,  107,
   67,   52,   52,   73,   52,   52,   52,   52,   74,    0,
  167,   63,  110,    0,  109,    0,    0,    0,   64,    0,
    0,    0,    0,   62,    0,    0,   77,   63,   63,    0,
   63,   63,   63,   63,   67,    0,   72,   72,   93,   72,
   72,   72,   72,  112,    0,   93,    0,    0,    0,    0,
   93,    0,   70,    0,    0,   70,   12,   13,   14,   15,
   16,   17,   60,   60,   93,   60,   60,   60,   60,    0,
   70,   61,   61,   63,   61,   61,   61,   61,    0,    0,
   64,    0,   18,    0,    0,   62,    0,    0,    0,   77,
    0,   77,    0,    0,    0,    0,    0,   62,   62,    0,
   62,   62,   62,   62,   70,    0,   98,   99,    0,  100,
  101,  102,  103,   63,    0,    0,    0,    0,   77,   77,
   64,   65,   65,   77,    0,   62,   65,   65,   93,    0,
   93,    0,   98,   99,    0,  100,  101,  102,  103,   98,
   99,   63,  100,  101,  102,  103,    0,    0,   64,    0,
  108,    0,    0,   62,    0,  106,  104,    0,  105,  111,
  107,    0,    0,   42,    0,   65,   98,   99,    0,  100,
  101,  102,  103,   51,   51,  108,   51,   51,   51,   51,
  106,  104,    0,  105,  111,  107,  108,    0,    0,    0,
    0,  106,  104,    0,  105,  111,  107,    0,  110,   66,
  109,   31,   66,   42,  112,    0,    0,   64,    0,  110,
   64,  109,    0,    0,    0,    0,    0,   66,    0,    0,
    0,    0,   67,   67,    0,   64,    0,   67,   67,  112,
    0,    0,    0,   98,   78,    0,  100,  101,  102,  103,
  112,   46,   68,   47,    0,   68,    0,    0,    0,    0,
   53,   66,   55,   56,   57,   58,   59,   60,   61,   64,
   68,    0,   93,   93,   93,   93,   93,   93,   93,   78,
   93,   93,   93,   93,    0,   93,   93,   93,   93,   93,
   93,   93,   93,   93,   93,   93,   12,   13,   14,   15,
   16,   17,   70,   70,   68,    0,    0,   12,   13,   14,
   15,   16,   17,   46,    0,   47,   48,   49,   50,   85,
   51,   52,   53,   54,   55,   56,   57,   58,   59,   60,
   61,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   78,    0,   78,    0,    0,   12,   13,   14,
   15,   16,   17,   46,    0,   47,   48,   49,   50,    0,
   51,   52,   53,   54,   55,   56,   57,   58,   59,   60,
   61,   78,   78,    0,    0,    0,   78,    0,    0,    0,
    0,   46,    0,   47,   58,    0,   58,   58,   58,    0,
   53,    0,   55,   56,   57,   58,   59,   60,   61,    0,
    0,    0,   58,   58,   59,   58,   59,   59,   59,    0,
    0,    0,   69,   71,    0,   69,   71,    0,    0,    0,
    0,    0,   59,   59,    0,   59,    0,    0,    0,    0,
   69,   71,  100,  101,  102,  103,   58,    0,    0,    0,
    0,    0,    0,  100,  101,    0,    0,    0,   82,   66,
   66,    0,    0,    0,   66,   66,   59,   64,   64,   91,
   92,   93,   64,   64,   69,   71,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  114,    0,  114,    0,
    0,    0,    0,    0,  120,    0,    0,  120,    0,    0,
    0,    0,   68,   68,    0,  127,  128,  129,  130,  131,
  132,  133,  134,  135,  136,  137,  138,  139,    0,  141,
  142,    0,    0,    0,    0,    0,  147,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  114,    0,    0,    0,  166,    0,    0,  120,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   58,   58,    0,   58,   58,   58,
   58,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   59,   59,    0,   59,   59,   59,
   59,    0,   69,   69,   71,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  262,   91,   11,   41,   42,   43,   44,   45,   46,   47,
   37,   81,   91,   46,   41,   42,   43,   44,   45,   46,
   47,   59,   60,   61,   62,   41,   45,   91,   44,  280,
  264,  280,   59,   60,   61,   62,   45,   37,   41,   37,
  123,   41,   42,   43,   42,   45,   46,   47,   46,   47,
   11,   41,  280,   91,   44,   93,   59,   18,   91,   59,
   60,   80,   62,   37,   91,   41,   93,   41,   42,   43,
   44,   45,   37,   47,  144,   59,   41,   42,   43,   44,
   45,   41,   47,   59,   44,   59,   60,   40,   62,   93,
  145,   91,   53,   91,   59,   60,   41,   62,   37,   44,
   40,   59,   41,   42,   43,   44,   45,   37,   47,  123,
   59,   41,   42,   43,   44,   45,   40,   47,  173,   93,
   59,   60,  177,   62,   41,   39,   40,   44,   93,   59,
   60,   45,   62,   37,   40,   40,   59,   41,   42,   43,
   44,   45,   37,   47,   40,   61,   41,   42,   43,   40,
   45,   46,   47,  172,   93,   59,   60,   41,   62,   40,
   44,   41,   40,   93,   44,   60,   59,   62,   37,   40,
  280,   91,   59,   42,   43,   37,   45,   46,   47,   59,
   42,   43,   41,   45,   46,   47,   30,   41,   32,   93,
  280,   60,   41,   62,   38,   41,   91,   44,   60,   41,
   62,  280,   37,   41,  125,    0,   40,   42,   43,   37,
   45,   46,   47,   93,   42,   43,  280,   45,   46,   47,
   59,  269,   91,   59,   93,   60,   59,   62,   59,   91,
   59,   93,   60,   41,   62,  257,  258,  259,  260,  261,
  262,   41,   59,  281,  282,   59,  284,  285,  286,  287,
  123,   41,   59,   59,  281,  282,   91,  284,  285,  286,
  287,   41,   41,   91,   41,   44,    3,   32,   87,   37,
   45,   45,   45,   45,   42,   43,   45,   45,   46,   47,
   59,  281,  282,   45,  284,  285,  286,  287,   45,   -1,
  151,   33,   60,   -1,   62,   -1,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   45,   -1,   -1,   80,  281,  282,   -1,
  284,  285,  286,  287,   93,   -1,  281,  282,   33,  284,
  285,  286,  287,   91,   -1,   40,   -1,   -1,   -1,   -1,
   45,   -1,   41,   -1,   -1,   44,  257,  258,  259,  260,
  261,  262,  281,  282,   59,  284,  285,  286,  287,   -1,
   59,  281,  282,   33,  284,  285,  286,  287,   -1,   -1,
   40,   -1,  283,   -1,   -1,   45,   -1,   -1,   -1,  143,
   -1,  145,   -1,   -1,   -1,   -1,   -1,  281,  282,   -1,
  284,  285,  286,  287,   93,   -1,  281,  282,   -1,  284,
  285,  286,  287,   33,   -1,   -1,   -1,   -1,  172,  173,
   40,  281,  282,  177,   -1,   45,  286,  287,  123,   -1,
  125,   -1,  281,  282,   -1,  284,  285,  286,  287,  281,
  282,   33,  284,  285,  286,  287,   -1,   -1,   40,   -1,
   37,   -1,   -1,   45,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,  123,   -1,  125,  281,  282,   -1,  284,
  285,  286,  287,  281,  282,   37,  284,  285,  286,  287,
   42,   43,   -1,   45,   46,   47,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   60,   41,
   62,   93,   44,  123,   91,   -1,   -1,   41,   -1,   60,
   44,   62,   -1,   -1,   -1,   -1,   -1,   59,   -1,   -1,
   -1,   -1,  281,  282,   -1,   59,   -1,  286,  287,   91,
   -1,   -1,   -1,  281,   45,   -1,  284,  285,  286,  287,
   91,  263,   41,  265,   -1,   44,   -1,   -1,   -1,   -1,
  272,   93,  274,  275,  276,  277,  278,  279,  280,   93,
   59,   -1,  257,  258,  259,  260,  261,  262,  263,   80,
  265,  266,  267,  268,   -1,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,  257,  258,  259,  260,
  261,  262,  281,  282,   93,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,  280,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  143,   -1,  145,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,  266,  267,  268,   -1,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  172,  173,   -1,   -1,   -1,  177,   -1,   -1,   -1,
   -1,  263,   -1,  265,   41,   -1,   43,   44,   45,   -1,
  272,   -1,  274,  275,  276,  277,  278,  279,  280,   -1,
   -1,   -1,   59,   60,   41,   62,   43,   44,   45,   -1,
   -1,   -1,   41,   41,   -1,   44,   44,   -1,   -1,   -1,
   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   59,   59,  284,  285,  286,  287,   93,   -1,   -1,   -1,
   -1,   -1,   -1,  284,  285,   -1,   -1,   -1,   51,  281,
  282,   -1,   -1,   -1,  286,  287,   93,  281,  282,   62,
   63,   64,  286,  287,   93,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   79,   -1,   81,   -1,
   -1,   -1,   -1,   -1,   87,   -1,   -1,   90,   -1,   -1,
   -1,   -1,  281,  282,   -1,   98,   99,  100,  101,  102,
  103,  104,  105,  106,  107,  108,  109,  110,   -1,  112,
  113,   -1,   -1,   -1,   -1,   -1,  119,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  144,   -1,   -1,   -1,  148,   -1,   -1,  151,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  281,  282,   -1,  284,  285,  286,
  287,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  281,  282,   -1,  284,  285,  286,
  287,   -1,  281,  282,  282,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=287;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"VOID","BOOL","INT","DOUBLE",
"STRING","CLASS","NULL","EXTENDS","THIS","WHILE","FOR","IF","ELSE","RETURN",
"BREAK","NEW","PRINT","READ_INTEGER","READ_LINE","STRING_CONST","INT_CONST",
"BOOL_CONST","DOUBLE_CONST","IDENTIFIER","AND","OR","STATIC","LESS_EQUAL",
"GREATER_EQUAL","EQUAL","NOT_EQUAL",
};
final static String yyrule[] = {
"$accept : Program",
"Program : ClassDefnList",
"ClassDefnList : ClassDefnList ClassDefn",
"ClassDefnList : ClassDefn",
"VariableDecl : Variable ';'",
"Variable : Type IDENTIFIER",
"Type : INT",
"Type : DOUBLE",
"Type : BOOL",
"Type : STRING",
"Type : VOID",
"Type : CLASS IDENTIFIER",
"Type : Type '[' ']'",
"FormalsList : FormalsList ',' Variable",
"FormalsList : Variable",
"FormalsList :",
"FunctionDefn : STATIC Type IDENTIFIER '(' FormalsList ')' StmtBlock",
"FunctionDefn : Type IDENTIFIER '(' FormalsList ')' StmtBlock",
"StmtBlock : '{' StmtList '}'",
"ClassDefn : CLASS IDENTIFIER ExtendsClause '{' FieldList '}'",
"ExtendsClause : EXTENDS IDENTIFIER",
"ExtendsClause :",
"FieldList : FieldList VariableDecl",
"FieldList : FieldList FunctionDefn",
"FieldList :",
"StmtList : StmtList VariableDecl",
"StmtList : StmtList StmtBlock",
"StmtList : StmtList SimpleStmt ';'",
"StmtList : StmtList PrintStmt ';'",
"StmtList : StmtList BreakStmt ';'",
"StmtList : StmtList ReturnStmt ';'",
"StmtList : StmtList WhileStmt",
"StmtList : StmtList ForStmt",
"StmtList : StmtList IfStmt",
"StmtList :",
"Stmt : VariableDecl",
"Stmt : StmtBlock",
"Stmt : SimpleStmt ';'",
"Stmt : PrintStmt ';'",
"Stmt : BreakStmt ';'",
"Stmt : ReturnStmt ';'",
"Stmt : WhileStmt",
"Stmt : ForStmt",
"Stmt : IfStmt",
"Constant : INT_CONST",
"Constant : DOUBLE_CONST",
"Constant : BOOL_CONST",
"Constant : STRING_CONST",
"Constant : NULL",
"Expr : Constant",
"Expr : THIS",
"Expr : LValue",
"Expr : Call",
"Expr : '(' Expr ')'",
"Expr : READ_INTEGER '(' ')'",
"Expr : READ_LINE '(' ')'",
"Expr : NEW IDENTIFIER '(' ')'",
"Expr : NEW Type '[' Expr ']'",
"Expr : Expr '+' Expr",
"Expr : Expr '-' Expr",
"Expr : Expr '*' Expr",
"Expr : Expr '/' Expr",
"Expr : Expr '%' Expr",
"Expr : '-' Expr",
"Expr : Expr '<' Expr",
"Expr : Expr LESS_EQUAL Expr",
"Expr : Expr '>' Expr",
"Expr : Expr GREATER_EQUAL Expr",
"Expr : Expr EQUAL Expr",
"Expr : Expr NOT_EQUAL Expr",
"Expr : Expr AND Expr",
"Expr : Expr OR Expr",
"Expr : '!' Expr",
"ExprList : ExprList ',' Expr",
"ExprList : Expr",
"PrintStmt : PRINT '(' ExprList ')'",
"BreakStmt : BREAK",
"ReturnStmt : RETURN Expr",
"ReturnStmt : RETURN",
"Call : Expr '.' IDENTIFIER '(' Actuals ')'",
"Call : IDENTIFIER '(' Actuals ')'",
"Actuals : ExprList",
"Actuals :",
"LValue : Expr '.' IDENTIFIER",
"LValue : IDENTIFIER",
"LValue : Expr '[' Expr ']'",
"SimpleStmt : LValue '=' Expr",
"SimpleStmt : Call",
"SimpleStmt :",
"BoolExpr : Expr",
"WhileStmt : WHILE '(' BoolExpr ')' Stmt",
"ForStmt : FOR '(' SimpleStmt ';' BoolExpr ';' SimpleStmt ')' Stmt",
"IfStmt : IF '(' BoolExpr ')' Stmt ELSE Stmt",
"IfStmt : IF '(' BoolExpr ')' Stmt",
};

//#line 468 "Parser.y"
    
	/**
	 * 打印当前归约所用的语法规则<br>
	 * 请勿修改。
	 */
    public boolean onReduce(String rule) {
		if (rule.startsWith("$$"))
			return false;
		else
			rule = rule.replaceAll(" \\$\\$\\d+", "");

   	    if (rule.endsWith(":"))
    	    System.out.println(rule + " <empty>");
   	    else
			System.out.println(rule);
		return false;
    }
    
    public void diagnose() {
		addReduceListener(this);
		yyparse();
	}
//#line 573 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    //if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      //if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        //if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        //if (yychar < 0)    //it it didn't work/error
        //  {
        //  yychar = 0;      //change it to default string (no -1!)
          //if (yydebug)
          //  yylexdebug(yystate,yychar);
        //  }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        //if (yydebug)
          //debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      //if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0 || valptr<0)   //check for under & overflow here
            {
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            //if (yydebug)
              //debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            //if (yydebug)
              //debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0 || valptr<0)   //check for under & overflow here
              {
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        //if (yydebug)
          //{
          //yys = null;
          //if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          //if (yys == null) yys = "illegal-symbol";
          //debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          //}
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    //if (yydebug)
      //debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    if (reduceListener == null || reduceListener.onReduce(yyrule[yyn])) // if intercepted!
      switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 52 "Parser.y"
{
						tree = new Program(val_peek(0).clist, val_peek(0).loc);
					}
break;
case 2:
//#line 58 "Parser.y"
{
						yyval.clist.add(val_peek(0).cdef);
					}
break;
case 3:
//#line 62 "Parser.y"
{
                		yyval.clist = new ArrayList<ClassDefn>();
                		yyval.clist.add(val_peek(0).cdef);
                	}
break;
case 4:
//#line 69 "Parser.y"
{
						yyval.vdecl = val_peek(1).vdecl;
					}
break;
case 5:
//#line 75 "Parser.y"
{
						yyval.vdecl = new VarDecl(val_peek(0).sval, val_peek(1).type, val_peek(0).loc);
					}
break;
case 6:
//#line 81 "Parser.y"
{
						yyval.type = new IntType(val_peek(0).loc);
					}
break;
case 7:
//#line 86 "Parser.y"
{
						yyval.type = new DoubleType(val_peek(0).loc);
					}
break;
case 8:
//#line 90 "Parser.y"
{
						yyval.type = new BoolType(val_peek(0).loc);
					}
break;
case 9:
//#line 94 "Parser.y"
{
						yyval.type = new StringType(val_peek(0).loc);
					}
break;
case 10:
//#line 98 "Parser.y"
{
						yyval.type = new VoidType(val_peek(0).loc);
					}
break;
case 11:
//#line 102 "Parser.y"
{
						yyval.type = new ClassType(val_peek(0).sval,val_peek(1).loc);
					}
break;
case 12:
//#line 106 "Parser.y"
{
						yyval.type = new ArrayType(val_peek(2).type,val_peek(2).loc);
					}
break;
case 13:
//#line 112 "Parser.y"
{
						yyval.vlist.add(val_peek(0).vdecl);
					}
break;
case 14:
//#line 116 "Parser.y"
{
						/*$$=new SemValue();*/
						yyval.vlist=new ArrayList<VarDecl>();
						yyval.vlist.add(val_peek(0).vdecl);
					}
break;
case 15:
//#line 122 "Parser.y"
{
						yyval=new SemValue();
						yyval.vlist=new ArrayList<VarDecl>();
					}
break;
case 16:
//#line 128 "Parser.y"
{
						yyval.fdef=new FuncDefn(true, val_peek(4).sval, val_peek(5).type, val_peek(2).vlist, (StmtBlock)val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 17:
//#line 132 "Parser.y"
{
						yyval.fdef=new FuncDefn(false, val_peek(4).sval, val_peek(5).type, val_peek(2).vlist, (StmtBlock)val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 18:
//#line 137 "Parser.y"
{
						/*$$=new SemValue();*/
						yyval.stmt=new StmtBlock(val_peek(1).slist,val_peek(1).loc);
					}
break;
case 19:
//#line 144 "Parser.y"
{
						yyval.cdef = new ClassDefn(val_peek(4).sval, val_peek(3).sval, val_peek(1).flist, val_peek(5).loc);
					}
break;
case 20:
//#line 150 "Parser.y"
{
						yyval.sval = val_peek(0).sval;
					}
break;
case 21:
//#line 154 "Parser.y"
{
                		yyval = new SemValue();
                	}
break;
case 22:
//#line 160 "Parser.y"
{
						yyval.flist.add(val_peek(0).vdecl);
					}
break;
case 23:
//#line 165 "Parser.y"
{
						yyval.flist.add(val_peek(0).fdef);
					}
break;
case 24:
//#line 169 "Parser.y"
{
						yyval = new SemValue();
						yyval.flist = new ArrayList<Field>();
					}
break;
case 25:
//#line 175 "Parser.y"
{
					/*$$=new SemValue();*/
					/*$$.slist=new ArrayList<Statement>();*/
					yyval.slist.add(new VarDeclStmt(val_peek(0).vdecl,val_peek(0).loc));
				}
break;
case 26:
//#line 181 "Parser.y"
{
					yyval.slist.add(val_peek(0).stmt);
				}
break;
case 27:
//#line 185 "Parser.y"
{
					if(val_peek(1).stmt!=null)
						yyval.slist.add(val_peek(1).stmt);
				}
break;
case 28:
//#line 190 "Parser.y"
{
					yyval.slist.add(val_peek(1).stmt);
				}
break;
case 29:
//#line 194 "Parser.y"
{
					yyval.slist.add(val_peek(1).stmt);
				}
break;
case 30:
//#line 198 "Parser.y"
{
					yyval.slist.add(val_peek(1).stmt);
				}
break;
case 31:
//#line 202 "Parser.y"
{	
					yyval.slist.add(val_peek(0).stmt);
				}
break;
case 32:
//#line 206 "Parser.y"
{	
					yyval.slist.add(val_peek(0).stmt);
				}
break;
case 33:
//#line 210 "Parser.y"
{	
					yyval.slist.add(val_peek(0).stmt);
				}
break;
case 34:
//#line 214 "Parser.y"
{
					yyval=new SemValue();
					yyval.slist=new ArrayList<Statement>();
				}
break;
case 35:
//#line 220 "Parser.y"
{
					/*$$=new SemValue();*/
					/*$$.slist=new ArrayList<Statement>();*/
					yyval.stmt=new VarDeclStmt(val_peek(0).vdecl,val_peek(0).loc);
				}
break;
case 36:
//#line 226 "Parser.y"
{
					yyval.stmt=val_peek(0).stmt;
				}
break;
case 37:
//#line 230 "Parser.y"
{
					if(val_peek(1).stmt!=null)
						yyval.stmt=val_peek(1).stmt;
				}
break;
case 38:
//#line 235 "Parser.y"
{
					yyval.stmt=val_peek(1).stmt;
				}
break;
case 39:
//#line 239 "Parser.y"
{
					yyval.stmt=val_peek(1).stmt;
				}
break;
case 40:
//#line 243 "Parser.y"
{
					yyval.stmt=val_peek(1).stmt;
				}
break;
case 41:
//#line 247 "Parser.y"
{
					yyval.stmt=val_peek(0).stmt;
				}
break;
case 42:
//#line 251 "Parser.y"
{
					yyval.stmt=val_peek(0).stmt;
				}
break;
case 43:
//#line 255 "Parser.y"
{
					yyval.stmt=val_peek(0).stmt;
				}
break;
case 44:
//#line 262 "Parser.y"
{
					yyval.expr=new IntConst(val_peek(0).ival,val_peek(0).loc);
				}
break;
case 45:
//#line 266 "Parser.y"
{
					yyval.expr=new DoubleConst(val_peek(0).dval,val_peek(0).loc);
				}
break;
case 46:
//#line 270 "Parser.y"
{
					yyval.expr=new BoolConst(val_peek(0).bval,val_peek(0).loc);
				}
break;
case 47:
//#line 274 "Parser.y"
{
					yyval.expr=new StringConst(val_peek(0).sval,val_peek(0).loc);
				}
break;
case 48:
//#line 278 "Parser.y"
{
					yyval.expr=new NullExpr(val_peek(0).loc);
				}
break;
case 49:
//#line 283 "Parser.y"
{
					yyval.expr=val_peek(0).expr;
				}
break;
case 50:
//#line 287 "Parser.y"
{
					yyval.expr=new ThisExpr(val_peek(0).loc);
				}
break;
case 51:
//#line 291 "Parser.y"
{
					yyval.expr=new LValueExpr(val_peek(0).lvalue,val_peek(0).loc);
				}
break;
case 52:
//#line 295 "Parser.y"
{
					yyval.expr=val_peek(0).expr;
				}
break;
case 53:
//#line 299 "Parser.y"
{
					yyval.expr=val_peek(1).expr;
				}
break;
case 54:
//#line 303 "Parser.y"
{
					yyval.expr=new ReadIntExpr(val_peek(2).loc);
				}
break;
case 55:
//#line 307 "Parser.y"
{
					yyval.expr=new ReadLineExpr(val_peek(2).loc);
				}
break;
case 56:
//#line 311 "Parser.y"
{
					yyval.expr=new NewObjExpr(val_peek(2).sval,val_peek(2).loc);
				}
break;
case 57:
//#line 315 "Parser.y"
{
					yyval.expr=new NewArrayExpr(val_peek(3).type,val_peek(1).expr,val_peek(3).loc);
				}
break;
case 58:
//#line 319 "Parser.y"
{
					yyval.expr=new AddExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 59:
//#line 323 "Parser.y"
{
					yyval.expr=new SubExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 60:
//#line 327 "Parser.y"
{
					yyval.expr=new MulExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 61:
//#line 331 "Parser.y"
{
					yyval.expr=new DivExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 62:
//#line 335 "Parser.y"
{
					yyval.expr=new ModExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 63:
//#line 339 "Parser.y"
{
					yyval.expr=new NegExpr(val_peek(0).expr,val_peek(0).loc);
				}
break;
case 64:
//#line 343 "Parser.y"
{
					yyval.expr=new LesExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 65:
//#line 347 "Parser.y"
{
					yyval.expr=new LeqExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 66:
//#line 351 "Parser.y"
{
					yyval.expr=new GtrExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 67:
//#line 355 "Parser.y"
{
					yyval.expr=new GeqExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 68:
//#line 359 "Parser.y"
{
					yyval.expr=new EquExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 69:
//#line 363 "Parser.y"
{
					yyval.expr=new NeqExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 70:
//#line 367 "Parser.y"
{
					yyval.expr=new AndExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 71:
//#line 371 "Parser.y"
{
					yyval.expr=new OrExpr(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 72:
//#line 375 "Parser.y"
{
					yyval.expr=new NotExpr(val_peek(0).expr,val_peek(0).loc);
				}
break;
case 73:
//#line 381 "Parser.y"
{
					yyval.elist.add(val_peek(0).expr);
				}
break;
case 74:
//#line 385 "Parser.y"
{
					yyval.elist=new ArrayList<Expr>();
					yyval.elist.add(val_peek(0).expr);
				}
break;
case 75:
//#line 391 "Parser.y"
{
					yyval.stmt=new PrintStmt(val_peek(1).elist,val_peek(1).loc);
				}
break;
case 76:
//#line 395 "Parser.y"
{
					yyval.stmt=new BreakStmt(val_peek(0).loc);
				}
break;
case 77:
//#line 399 "Parser.y"
{
					yyval.stmt=new ReturnStmt(val_peek(0).expr,val_peek(1).loc);
				}
break;
case 78:
//#line 403 "Parser.y"
{
					yyval.stmt=new ReturnStmt(null,val_peek(0).loc);
				}
break;
case 79:
//#line 407 "Parser.y"
{
					yyval.expr=new CallExpr(val_peek(5).expr,val_peek(3).sval,val_peek(1).elist,val_peek(3).loc);
				}
break;
case 80:
//#line 411 "Parser.y"
{
					yyval.expr=new CallExpr(null,val_peek(3).sval,val_peek(1).elist,val_peek(1).loc);
				}
break;
case 81:
//#line 415 "Parser.y"
{
					yyval.elist=val_peek(0).elist;
				}
break;
case 82:
//#line 419 "Parser.y"
{
					yyval=new SemValue();
					yyval.elist=new ArrayList<Expr>();
				}
break;
case 83:
//#line 424 "Parser.y"
{
					yyval.lvalue=new VarRef(val_peek(2).expr,val_peek(0).sval,val_peek(0).loc);
				}
break;
case 84:
//#line 428 "Parser.y"
{
					yyval.lvalue=new VarRef(null,val_peek(0).sval,val_peek(0).loc);
				}
break;
case 85:
//#line 432 "Parser.y"
{
					yyval.lvalue=new ArrayRef(val_peek(3).expr,val_peek(1).expr,val_peek(3).loc);
				}
break;
case 86:
//#line 436 "Parser.y"
{
					yyval.stmt=new AssignStmt(val_peek(2).lvalue,val_peek(0).expr,val_peek(2).loc);
				}
break;
case 87:
//#line 440 "Parser.y"
{
					val_peek(0).stmt=new ExprStmt(val_peek(0).expr,val_peek(0).loc);
				}
break;
case 88:
//#line 444 "Parser.y"
{
					yyval=new SemValue();
				}
break;
case 89:
//#line 448 "Parser.y"
{
					yyval.expr=val_peek(0).expr;
				}
break;
case 90:
//#line 452 "Parser.y"
{
					yyval.stmt=new WhileStmt(val_peek(2).expr,val_peek(0).stmt,val_peek(4).loc);
				}
break;
case 91:
//#line 456 "Parser.y"
{
					yyval.stmt=new ForStmt(val_peek(6).stmt,val_peek(4).expr,val_peek(2).stmt,val_peek(0).stmt,val_peek(8).loc);
				}
break;
case 92:
//#line 460 "Parser.y"
{
					yyval.stmt=new IfStmt(val_peek(4).expr,val_peek(2).stmt,val_peek(0).stmt,val_peek(6).loc);
				}
break;
case 93:
//#line 464 "Parser.y"
{
					yyval.stmt=new IfStmt(val_peek(2).expr,val_peek(0).stmt,null,val_peek(4).loc);
				}
break;
//#line 1292 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    //if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      //if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        //if (yychar<0) yychar=0;  //clean, if necessary
        //if (yydebug)
          //yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      //if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
