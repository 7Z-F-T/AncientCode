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
public final static short UMINUS=288;
public final static short EMPTY=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    4,    5,    5,    5,    5,    5,
    5,    5,    2,    6,    6,    7,    7,    7,    9,    9,
   10,   10,    8,    8,   11,   12,   12,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   14,   14,   14,   24,
   24,   21,   21,   23,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   22,   22,
   22,   22,   22,   22,   22,   22,   22,   22,   26,   26,
   26,   26,   26,   25,   25,   27,   27,   16,   17,   20,
   15,   28,   28,   18,   18,   19,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    1,    1,    1,    1,
    2,    3,    6,    2,    0,    2,    2,    0,    1,    0,
    3,    1,    7,    6,    3,    2,    0,    1,    2,    1,
    1,    1,    2,    2,    2,    1,    3,    1,    0,    2,
    0,    2,    4,    5,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    2,    2,    3,    3,    1,    4,    5,    1,    1,
    1,    1,    1,    1,    0,    3,    1,    5,    9,    1,
    6,    2,    0,    2,    1,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    3,    0,    2,    0,    0,   14,   18,
    0,    7,    8,    6,   10,    9,    0,    0,   13,   16,
    0,    0,   17,   11,    0,    4,    0,    0,    0,    0,
   12,    0,   22,    0,    0,    0,    0,    5,    0,    0,
    0,   27,   24,   21,   23,    0,   73,   66,    0,    0,
    0,    0,   80,    0,    0,    0,    0,   71,   69,   70,
   72,    0,    0,    0,   25,   28,   36,   26,    0,   30,
   31,   32,    0,    0,    0,    0,    0,    0,    0,   47,
    0,    0,    0,   45,    0,   46,    0,    0,    0,    0,
    0,    0,    0,    0,   29,   33,   34,   35,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   40,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   64,   65,   61,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   67,    0,    0,   86,   43,    0,
    0,   78,    0,    0,   68,    0,   44,    0,    0,   81,
    0,   82,    0,   79,
};
final static short yydgoto[] = {                          2,
    3,    4,   66,   21,   34,    8,   11,   23,   35,   36,
   67,   46,   68,   69,   70,   71,   72,   73,   74,   75,
   84,   77,   86,   79,  150,   80,  122,  160,
};
final static short yysindex[] = {                      -244,
 -251,    0, -244,    0, -216,    0, -230,  -60,    0,    0,
   75,    0,    0,    0,    0,    0, -221,   40,    0,    0,
   14,  -78,    0,    0,  -64,    0,   42,   -5,   52,   40,
    0,   40,    0,  -54,   74,   65,   76,    0,    2,   40,
    2,    0,    0,    0,    0,  385,    0,    0,   83,   86,
   90,  263,    0,   68,   94,  103,  105,    0,    0,    0,
    0,  263,  263,  263,    0,    0,    0,    0,   87,    0,
    0,    0,   88,   89,   92,   91,  231,    0, -131,    0,
  263,  263,  263,    0,  231,    0,  116,   67,  263,  119,
  120,  -44,  -44,   95,    0,    0,    0,    0,  263,  263,
  263,  263,  263,  263,  263,  263,  263,  263,  263,  263,
  263,  263,    0,  263,  137,  122,  121,  129,  138,  351,
  231,   11,    0,    0,    0,  231,  429,  418,  637,  637,
  440,  440,   53,   53,  -44,  -44,  -44,  637,  637,  150,
  263,  474,  263,  474,    0,  161,  263,    0,    0,  140,
  134,    0,  172,  -86,    0,  231,    0,  263,  474,    0,
  144,    0,  474,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,  188,    0,   71,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  131,    0,    0,  158,
    0,  158,    0,    0,    0,  160,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -47,    0,    0,    0,    0,
    0,  -45,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -75,  -75,  -75,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  326,    0,  -26,    0,    0,
  -75,  -47,  -75,    0,  152,    0,    0,    0,  -75,    0,
    0,   -2,   25,    0,    0,    0,    0,    0,  -75,  -75,
  -75,  -75,  -75,  -75,  -75,  -75,  -75,  -75,  -75,  -75,
  -75,  -75,    0,  -75,  -37,    0,    0,    0,    0,  -75,
   39,    0,    0,    0,    0,  -15,    5,  580,   80,  408,
  576,  578,  489,  540,   34,   60,   69,  503,  547,    0,
  -40,  -47,  -75,  -47,    0,    0,  -75,    0,    0,    0,
  181,    0,    0,  298,    0,   45,    0,  -38,  -47,    0,
    0,    0,  -47,    0,
};
final static short yygindex[] = {                         0,
    0,  221,  214,   21,   20,    0,    0,    0,  195,    0,
  -11,    0,   -9,  -50,    0,    0,    0,    0,    0,    0,
  533,  780,  619,    0,    0,    0,   96,    0,
};
final static int YYTABLESIZE=927;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
   75,  113,   39,   42,   42,   42,   42,   42,   42,   42,
   46,   39,   28,   85,   38,   46,   46,    1,   46,   46,
   46,   42,   42,   42,   42,   37,   28,   43,    5,   45,
   22,  117,   38,   46,   62,   46,   28,   25,   62,   62,
   62,   62,   62,   37,   62,   59,  114,    7,   59,    9,
   33,  148,   33,   42,  147,   42,   62,   62,   24,   62,
   44,   63,   10,   59,   46,   63,   63,   63,   63,   63,
   50,   63,   26,   88,   50,   50,   50,   50,   50,   77,
   50,   30,   77,   63,   63,   76,   63,   31,   76,  110,
   62,   32,   50,   50,  108,   50,   51,   59,  113,  109,
   51,   51,   51,   51,   51,   52,   51,  161,   40,   52,
   52,   52,   52,   52,   39,   52,   41,   63,   51,   51,
   57,   51,   81,   57,   42,   82,   50,   52,   52,   83,
   52,  110,  152,   89,  154,  125,  108,  106,   57,  107,
  113,  109,   90,  114,   91,   95,   96,   97,  115,  162,
   98,   99,   51,  164,  112,  119,  111,  120,  110,  123,
  124,   52,  142,  108,  106,  110,  107,  113,  109,  144,
  108,  106,   57,  107,  113,  109,  141,  147,  145,  143,
  157,  112,  159,  111,  163,  114,  110,    1,  112,    5,
  111,  108,  106,   15,  107,  113,  109,  110,   20,   19,
   19,   27,  108,  106,   41,  107,  113,  109,  110,  112,
   84,  111,  114,  108,  106,   29,  107,  113,  109,  114,
  112,   74,  111,    6,   20,   38,   37,    0,    0,    0,
  158,  112,   41,  111,   41,    0,  151,    0,    0,   41,
  114,   41,  149,   42,   42,    0,   42,   42,   42,   42,
    0,  114,    0,  155,   46,   46,    0,   46,   46,   46,
   46,    0,  114,    0,    0,    0,    0,  110,    0,    0,
    0,    0,  108,  106,    0,  107,  113,  109,   62,   62,
    0,   62,   62,   62,   62,   59,   59,    0,    0,    0,
  112,    0,  111,    0,    0,   63,   12,   13,   14,   15,
   16,   17,   64,    0,    0,   63,   63,   62,   63,   63,
   63,   63,    0,    0,   50,   50,    0,   50,   50,   50,
   50,  114,    0,    0,   12,   13,   14,   15,   16,   17,
   83,   12,   13,   14,   15,   16,   17,   83,    0,    0,
   51,   51,   83,   51,   51,   51,   51,   87,    0,   52,
   52,    0,   52,   52,   52,   52,   83,   18,    0,    0,
   57,   57,   45,    0,    0,   57,   57,   45,   45,    0,
   45,   45,   45,    0,    0,  100,  101,    0,  102,  103,
  104,  105,    0,   63,    0,   45,    0,   45,    0,    0,
   64,    0,    0,    0,    0,   62,    0,    0,    0,    0,
    0,    0,  100,  101,    0,  102,  103,  104,  105,  100,
  101,    0,  102,  103,  104,  105,   45,   63,    0,    0,
   83,    0,   83,    0,   64,    0,    0,    0,    0,   62,
  100,  101,    0,  102,  103,  104,  105,    0,    0,    0,
    0,  100,  101,   31,  102,  103,  104,  105,   58,    0,
    0,   58,  100,  101,  110,  102,  103,  104,  105,  108,
  106,    0,  107,  113,  109,  110,   58,    0,    0,    0,
  108,  106,    0,  107,  113,  109,  110,  112,    0,  111,
    0,  108,  106,    0,  107,  113,  109,    0,  112,    0,
  111,    0,    0,    0,    0,    0,    0,    0,    0,  112,
   58,  111,    0,    0,    0,    0,   63,   42,  114,   65,
    0,  100,  101,   64,  102,  103,  104,  105,   62,  114,
    0,    0,    0,    0,    0,   47,    0,   48,    0,   48,
  114,   48,   48,   48,   54,    0,   56,   57,   58,   59,
   60,   61,    0,   56,    0,    0,   56,   48,   48,    0,
   48,    0,    0,    0,   83,   83,   83,   83,   83,   83,
   83,   56,   83,   83,   83,   83,    0,   83,   83,   83,
   83,   83,   83,   83,   83,   83,   83,   83,   76,    0,
   49,   48,   49,   49,   49,    0,    0,   55,    0,    0,
   55,    0,    0,    0,    0,   56,   42,    0,   49,   49,
    0,   49,    0,    0,    0,   55,   45,   45,    0,   45,
   45,   45,   45,   47,   76,   48,   53,    0,   54,   53,
   60,   54,   54,   60,   56,   57,   58,   59,   60,   61,
    0,    0,   49,    0,   53,    0,   54,    0,   60,   55,
    0,   12,   13,   14,   15,   16,   17,   47,    0,   48,
   49,   50,   51,    0,   52,   53,   54,   55,   56,   57,
   58,   59,   60,   61,   78,    0,    0,    0,   53,    0,
   54,    0,   60,  110,   76,    0,   76,    0,  108,  106,
    0,  107,  113,  109,    0,    0,    0,    0,   58,   58,
   76,   76,    0,   58,   58,   76,    0,    0,  100,    0,
   78,  102,  103,  104,  105,    0,    0,    0,    0,    0,
    0,    0,  102,  103,  104,  105,    0,    0,    0,    0,
    0,    0,    0,  102,  103,    0,    0,  114,    0,    0,
   12,   13,   14,   15,   16,   17,   47,    0,   48,   49,
   50,   51,    0,   52,   53,   54,   55,   56,   57,   58,
   59,   60,   61,    0,    0,    0,    0,    0,    0,    0,
   78,    0,   78,    0,    0,    0,    0,    0,    0,   48,
   48,    0,   48,   48,   48,   48,   78,   78,    0,    0,
    0,   78,    0,   56,   56,    0,    0,    0,   56,   56,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   49,   49,    0,   49,   49,   49,   49,   55,   55,    0,
    0,   85,   55,   55,    0,    0,    0,    0,    0,    0,
    0,   92,   93,   94,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   53,   53,   54,   54,
  116,   60,  118,    0,    0,    0,    0,    0,  121,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  126,  127,
  128,  129,  130,  131,  132,  133,  134,  135,  136,  137,
  138,  139,    0,  140,    0,    0,    0,    0,    0,  146,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  121,    0,  153,    0,    0,    0,  156,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   41,   46,   41,   41,   42,   43,   44,   45,   46,   47,
   37,   59,   91,   59,   41,   42,   43,  262,   45,   46,
   47,   59,   60,   61,   62,   41,   91,   39,  280,   41,
   11,   82,   59,   60,   37,   62,   91,   18,   41,   42,
   43,   44,   45,   59,   47,   41,   91,  264,   44,  280,
   30,   41,   32,   91,   44,   93,   59,   60,  280,   62,
   40,   37,  123,   59,   91,   41,   42,   43,   44,   45,
   37,   47,   59,   54,   41,   42,   43,   44,   45,   41,
   47,   40,   44,   59,   60,   41,   62,   93,   44,   37,
   93,   40,   59,   60,   42,   62,   37,   93,   46,   47,
   41,   42,   43,   44,   45,   37,   47,  158,   44,   41,
   42,   43,   44,   45,   41,   47,   41,   93,   59,   60,
   41,   62,   40,   44,  123,   40,   93,   59,   60,   40,
   62,   37,  142,   40,  144,   41,   42,   43,   59,   45,
   46,   47,   40,   91,   40,   59,   59,   59,  280,  159,
   59,   61,   93,  163,   60,   40,   62,   91,   37,   41,
   41,   93,   41,   42,   43,   37,   45,   46,   47,   41,
   42,   43,   93,   45,   46,   47,   40,   44,   41,   59,
   41,   60,  269,   62,   41,   91,   37,    0,   60,   59,
   62,   42,   43,  123,   45,   46,   47,   37,   41,  125,
   41,  280,   42,   43,  280,   45,   46,   47,   37,   60,
   59,   62,   91,   42,   43,  280,   45,   46,   47,   91,
   60,   41,   62,    3,   11,  280,   32,   -1,   -1,   -1,
   59,   60,  280,   62,  280,   -1,  141,   -1,   -1,  280,
   91,  280,   93,  281,  282,   -1,  284,  285,  286,  287,
   -1,   91,   -1,   93,  281,  282,   -1,  284,  285,  286,
  287,   -1,   91,   -1,   -1,   -1,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,  281,  282,
   -1,  284,  285,  286,  287,  281,  282,   -1,   -1,   -1,
   60,   -1,   62,   -1,   -1,   33,  257,  258,  259,  260,
  261,  262,   40,   -1,   -1,  281,  282,   45,  284,  285,
  286,  287,   -1,   -1,  281,  282,   -1,  284,  285,  286,
  287,   91,   -1,   -1,  257,  258,  259,  260,  261,  262,
   33,  257,  258,  259,  260,  261,  262,   40,   -1,   -1,
  281,  282,   45,  284,  285,  286,  287,  280,   -1,  281,
  282,   -1,  284,  285,  286,  287,   59,  283,   -1,   -1,
  281,  282,   37,   -1,   -1,  286,  287,   42,   43,   -1,
   45,   46,   47,   -1,   -1,  281,  282,   -1,  284,  285,
  286,  287,   -1,   33,   -1,   60,   -1,   62,   -1,   -1,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,
   -1,   -1,  281,  282,   -1,  284,  285,  286,  287,  281,
  282,   -1,  284,  285,  286,  287,   91,   33,   -1,   -1,
  123,   -1,  125,   -1,   40,   -1,   -1,   -1,   -1,   45,
  281,  282,   -1,  284,  285,  286,  287,   -1,   -1,   -1,
   -1,  281,  282,   93,  284,  285,  286,  287,   41,   -1,
   -1,   44,  281,  282,   37,  284,  285,  286,  287,   42,
   43,   -1,   45,   46,   47,   37,   59,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   37,   60,   -1,   62,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   60,   -1,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,
   93,   62,   -1,   -1,   -1,   -1,   33,  123,   91,  125,
   -1,  281,  282,   40,  284,  285,  286,  287,   45,   91,
   -1,   -1,   -1,   -1,   -1,  263,   -1,  265,   -1,   41,
   91,   43,   44,   45,  272,   -1,  274,  275,  276,  277,
  278,  279,   -1,   41,   -1,   -1,   44,   59,   60,   -1,
   62,   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,   59,  265,  266,  267,  268,   -1,  270,  271,  272,
  273,  274,  275,  276,  277,  278,  279,  280,   46,   -1,
   41,   93,   43,   44,   45,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   93,  123,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,   59,  281,  282,   -1,  284,
  285,  286,  287,  263,   82,  265,   41,   -1,   41,   44,
   41,   44,  272,   44,  274,  275,  276,  277,  278,  279,
   -1,   -1,   93,   -1,   59,   -1,   59,   -1,   59,   93,
   -1,  257,  258,  259,  260,  261,  262,  263,   -1,  265,
  266,  267,  268,   -1,  270,  271,  272,  273,  274,  275,
  276,  277,  278,  279,   46,   -1,   -1,   -1,   93,   -1,
   93,   -1,   93,   37,  142,   -1,  144,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  281,  282,
  158,  159,   -1,  286,  287,  163,   -1,   -1,  281,   -1,
   82,  284,  285,  286,  287,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,  287,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  284,  285,   -1,   -1,   91,   -1,   -1,
  257,  258,  259,  260,  261,  262,  263,   -1,  265,  266,
  267,  268,   -1,  270,  271,  272,  273,  274,  275,  276,
  277,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  142,   -1,  144,   -1,   -1,   -1,   -1,   -1,   -1,  281,
  282,   -1,  284,  285,  286,  287,  158,  159,   -1,   -1,
   -1,  163,   -1,  281,  282,   -1,   -1,   -1,  286,  287,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  281,  282,   -1,  284,  285,  286,  287,  281,  282,   -1,
   -1,   52,  286,  287,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   62,   63,   64,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  281,  282,  281,  282,
   81,  282,   83,   -1,   -1,   -1,   -1,   -1,   89,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   99,  100,
  101,  102,  103,  104,  105,  106,  107,  108,  109,  110,
  111,  112,   -1,  114,   -1,   -1,   -1,   -1,   -1,  120,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  141,   -1,  143,   -1,   -1,   -1,  147,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=289;
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
"GREATER_EQUAL","EQUAL","NOT_EQUAL","UMINUS","EMPTY",
};
final static String yyrule[] = {
"$accept : Program",
"Program : ClassList",
"ClassList : ClassList ClassDefn",
"ClassList : ClassDefn",
"VariableDecl : Variable ';'",
"Variable : Type IDENTIFIER",
"Type : INT",
"Type : VOID",
"Type : BOOL",
"Type : STRING",
"Type : DOUBLE",
"Type : CLASS IDENTIFIER",
"Type : Type '[' ']'",
"ClassDefn : CLASS IDENTIFIER ExtendsClause '{' FieldList '}'",
"ExtendsClause : EXTENDS IDENTIFIER",
"ExtendsClause :",
"FieldList : FieldList VariableDecl",
"FieldList : FieldList FunctionDefn",
"FieldList :",
"Formals : VariableList",
"Formals :",
"VariableList : VariableList ',' Variable",
"VariableList : Variable",
"FunctionDefn : STATIC Type IDENTIFIER '(' Formals ')' StmtBlock",
"FunctionDefn : Type IDENTIFIER '(' Formals ')' StmtBlock",
"StmtBlock : '{' StmtList '}'",
"StmtList : StmtList Stmt",
"StmtList :",
"Stmt : VariableDecl",
"Stmt : SimpleStmt ';'",
"Stmt : IfStmt",
"Stmt : WhileStmt",
"Stmt : ForStmt",
"Stmt : ReturnStmt ';'",
"Stmt : PrintStmt ';'",
"Stmt : BreakStmt ';'",
"Stmt : StmtBlock",
"SimpleStmt : LValue '=' Expr",
"SimpleStmt : Call",
"SimpleStmt :",
"Receiver : Expr '.'",
"Receiver :",
"LValue : Receiver IDENTIFIER",
"LValue : Expr '[' Expr ']'",
"Call : Receiver IDENTIFIER '(' Actuals ')'",
"Expr : LValue",
"Expr : Call",
"Expr : Constant",
"Expr : Expr '+' Expr",
"Expr : Expr '-' Expr",
"Expr : Expr '*' Expr",
"Expr : Expr '/' Expr",
"Expr : Expr '%' Expr",
"Expr : Expr EQUAL Expr",
"Expr : Expr NOT_EQUAL Expr",
"Expr : Expr '<' Expr",
"Expr : Expr '>' Expr",
"Expr : Expr LESS_EQUAL Expr",
"Expr : Expr GREATER_EQUAL Expr",
"Expr : Expr AND Expr",
"Expr : Expr OR Expr",
"Expr : '(' Expr ')'",
"Expr : '-' Expr",
"Expr : '!' Expr",
"Expr : READ_INTEGER '(' ')'",
"Expr : READ_LINE '(' ')'",
"Expr : THIS",
"Expr : NEW IDENTIFIER '(' ')'",
"Expr : NEW Type '[' Expr ']'",
"Constant : INT_CONST",
"Constant : BOOL_CONST",
"Constant : STRING_CONST",
"Constant : DOUBLE_CONST",
"Constant : NULL",
"Actuals : ExprList",
"Actuals :",
"ExprList : ExprList ',' Expr",
"ExprList : Expr",
"WhileStmt : WHILE '(' Expr ')' Stmt",
"ForStmt : FOR '(' SimpleStmt ';' Expr ';' SimpleStmt ')' Stmt",
"BreakStmt : BREAK",
"IfStmt : IF '(' Expr ')' Stmt ElseClause",
"ElseClause : ELSE Stmt",
"ElseClause :",
"ReturnStmt : RETURN Expr",
"ReturnStmt : RETURN",
"PrintStmt : PRINT '(' ExprList ')'",
};

//#line 425 "Parser.y"
    
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
//#line 563 "Parser.java"
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
//#line 51 "Parser.y"
{
						tree = new Program(val_peek(0).clist, val_peek(0).loc);
					}
break;
case 2:
//#line 57 "Parser.y"
{
						yyval.clist.add(val_peek(0).cdef);
					}
break;
case 3:
//#line 61 "Parser.y"
{
                		yyval.clist = new ArrayList<ClassDefn>();
                		yyval.clist.add(val_peek(0).cdef);
                	}
break;
case 5:
//#line 71 "Parser.y"
{
						yyval.vdecl = new VarDecl(val_peek(0).sval, val_peek(1).type, val_peek(0).loc);
					}
break;
case 6:
//#line 77 "Parser.y"
{
						yyval.type = new IntType(val_peek(0).loc);
					}
break;
case 7:
//#line 81 "Parser.y"
{
                		yyval.type = new VoidType(val_peek(0).loc);
                	}
break;
case 8:
//#line 85 "Parser.y"
{
                		yyval.type = new BoolType(val_peek(0).loc);
                	}
break;
case 9:
//#line 89 "Parser.y"
{
                		yyval.type = new StringType(val_peek(0).loc);
                	}
break;
case 10:
//#line 93 "Parser.y"
{
                		yyval.type = new DoubleType(val_peek(0).loc);
                		/*issueError(new DoubleNotSupport($1.loc));*/
                	}
break;
case 11:
//#line 98 "Parser.y"
{
                		yyval.type = new ClassType(val_peek(0).sval, val_peek(1).loc);
                	}
break;
case 12:
//#line 102 "Parser.y"
{
                		yyval.type = new ArrayType(val_peek(2).type, val_peek(2).loc);
                	}
break;
case 13:
//#line 108 "Parser.y"
{
						yyval.cdef = new ClassDefn(val_peek(4).sval, val_peek(3).sval, val_peek(1).flist, val_peek(5).loc);
					}
break;
case 14:
//#line 114 "Parser.y"
{
						yyval.sval = val_peek(0).sval;
					}
break;
case 15:
//#line 118 "Parser.y"
{
                		yyval = new SemValue();
                	}
break;
case 16:
//#line 124 "Parser.y"
{
						yyval.flist.add(val_peek(0).vdecl);
					}
break;
case 17:
//#line 128 "Parser.y"
{
						yyval.flist.add(val_peek(0).fdef);
					}
break;
case 18:
//#line 132 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.flist = new ArrayList<Field>();
                	}
break;
case 20:
//#line 140 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.vlist = new ArrayList<VarDecl>(); 
                	}
break;
case 21:
//#line 147 "Parser.y"
{
						yyval.vlist.add(val_peek(0).vdecl);
					}
break;
case 22:
//#line 151 "Parser.y"
{
                		yyval.vlist = new ArrayList<VarDecl>();
						yyval.vlist.add(val_peek(0).vdecl);
                	}
break;
case 23:
//#line 158 "Parser.y"
{
						yyval.fdef = new FuncDefn(true, val_peek(4).sval, val_peek(5).type, val_peek(2).vlist, (StmtBlock) val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 24:
//#line 162 "Parser.y"
{
						yyval.fdef = new FuncDefn(false, val_peek(4).sval, val_peek(5).type, val_peek(2).vlist, (StmtBlock) val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 25:
//#line 168 "Parser.y"
{
						yyval.stmt = new StmtBlock(val_peek(1).slist, val_peek(2).loc);
					}
break;
case 26:
//#line 174 "Parser.y"
{
						yyval.slist.add(val_peek(0).stmt);
					}
break;
case 27:
//#line 178 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.slist = new ArrayList<Statement>();
                	}
break;
case 28:
//#line 185 "Parser.y"
{
						yyval.stmt = new VarDeclStmt(val_peek(0).vdecl, val_peek(0).loc);
					}
break;
case 37:
//#line 198 "Parser.y"
{
						yyval.stmt = new AssignStmt(val_peek(2).lvalue, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 38:
//#line 202 "Parser.y"
{
                		yyval.stmt = new ExprStmt(val_peek(0).expr, val_peek(0).loc);
                	}
break;
case 39:
//#line 206 "Parser.y"
{
                		yyval = new SemValue();
                	}
break;
case 41:
//#line 213 "Parser.y"
{
                		yyval = new SemValue();
                	}
break;
case 42:
//#line 219 "Parser.y"
{
						yyval.lvalue = new VarRef(val_peek(1).expr, val_peek(0).sval, val_peek(0).loc);
						if (val_peek(1).loc == null) {
							yyval.loc = val_peek(0).loc;
						}
					}
break;
case 43:
//#line 226 "Parser.y"
{
                		yyval.lvalue = new ArrayRef(val_peek(3).expr, val_peek(1).expr, val_peek(3).loc);
                	}
break;
case 44:
//#line 232 "Parser.y"
{
						yyval.expr = new CallExpr(val_peek(4).expr, val_peek(3).sval, val_peek(1).elist, val_peek(3).loc);
						if (val_peek(4).loc == null) {
							yyval.loc = val_peek(3).loc;
						}
					}
break;
case 45:
//#line 241 "Parser.y"
{
						yyval.expr = new LValueExpr(val_peek(0).lvalue, val_peek(0).loc);
					}
break;
case 48:
//#line 247 "Parser.y"
{
                		yyval.expr = new AddExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 49:
//#line 251 "Parser.y"
{
                		yyval.expr = new SubExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 50:
//#line 255 "Parser.y"
{
                		yyval.expr = new MulExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 51:
//#line 259 "Parser.y"
{
                		yyval.expr = new DivExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 52:
//#line 263 "Parser.y"
{
                		yyval.expr = new ModExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 53:
//#line 267 "Parser.y"
{
                		yyval.expr = new EquExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 54:
//#line 271 "Parser.y"
{
                		yyval.expr = new NeqExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 55:
//#line 275 "Parser.y"
{
                		yyval.expr = new LesExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 56:
//#line 279 "Parser.y"
{
                		yyval.expr = new GtrExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 57:
//#line 283 "Parser.y"
{
                		yyval.expr = new LeqExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 58:
//#line 287 "Parser.y"
{
                		yyval.expr = new GeqExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 59:
//#line 291 "Parser.y"
{
                		yyval.expr = new AndExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 60:
//#line 295 "Parser.y"
{
                		yyval.expr = new OrExpr(val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 61:
//#line 299 "Parser.y"
{
                		yyval = val_peek(1);
                	}
break;
case 62:
//#line 303 "Parser.y"
{
                		yyval.expr = new NegExpr(val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 63:
//#line 307 "Parser.y"
{
                		yyval.expr = new NotExpr(val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 64:
//#line 311 "Parser.y"
{
                		yyval.expr = new ReadIntExpr(val_peek(2).loc);
                	}
break;
case 65:
//#line 315 "Parser.y"
{
                		yyval.expr = new ReadLineExpr(val_peek(2).loc);
                	}
break;
case 66:
//#line 319 "Parser.y"
{
                		yyval.expr = new ThisExpr(val_peek(0).loc);
                	}
break;
case 67:
//#line 323 "Parser.y"
{
                		yyval.expr = new NewObjExpr(val_peek(2).sval, val_peek(3).loc);
                	}
break;
case 68:
//#line 327 "Parser.y"
{
                		yyval.expr = new NewArrayExpr(val_peek(3).type, val_peek(1).expr, val_peek(4).loc);
                	}
break;
case 69:
//#line 333 "Parser.y"
{
						yyval.expr = new IntConst(val_peek(0).ival, val_peek(0).loc);
					}
break;
case 70:
//#line 337 "Parser.y"
{
						yyval.expr = new BoolConst(val_peek(0).bval, val_peek(0).loc);
					}
break;
case 71:
//#line 341 "Parser.y"
{
						yyval.expr = new StringConst(val_peek(0).sval, val_peek(0).loc);
					}
break;
case 72:
//#line 345 "Parser.y"
{
						yyval.expr = new DoubleConst(val_peek(0).dval, val_peek(0).loc);
						/*issueError(new DoubleNotSupport($1.loc));*/
					}
break;
case 73:
//#line 350 "Parser.y"
{
						yyval.expr = new NullExpr(val_peek(0).loc);
					}
break;
case 75:
//#line 357 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.elist = new ArrayList<Expr>();
                	}
break;
case 76:
//#line 364 "Parser.y"
{
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 77:
//#line 368 "Parser.y"
{
                		yyval.elist = new ArrayList<Expr>();
						yyval.elist.add(val_peek(0).expr);
                	}
break;
case 78:
//#line 375 "Parser.y"
{
						yyval.stmt = new WhileStmt(val_peek(2).expr, val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 79:
//#line 381 "Parser.y"
{
						yyval.stmt = new ForStmt(val_peek(6).stmt, val_peek(4).expr, val_peek(2).stmt, val_peek(0).stmt, val_peek(8).loc);
					}
break;
case 80:
//#line 387 "Parser.y"
{
						yyval.stmt = new BreakStmt(val_peek(0).loc);
					}
break;
case 81:
//#line 393 "Parser.y"
{
						yyval.stmt = new IfStmt(val_peek(3).expr, val_peek(1).stmt, val_peek(0).stmt, val_peek(5).loc);
					}
break;
case 82:
//#line 399 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 83:
//#line 403 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 84:
//#line 409 "Parser.y"
{
						yyval.stmt = new ReturnStmt(val_peek(0).expr, val_peek(1).loc);
					}
break;
case 85:
//#line 413 "Parser.y"
{
                		yyval.stmt = new ReturnStmt(null, val_peek(0).loc);
                	}
break;
case 86:
//#line 419 "Parser.y"
{
						yyval.stmt = new PrintStmt(val_peek(1).elist, val_peek(3).loc);
					}
break;
//#line 1156 "Parser.java"
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
