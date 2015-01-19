;计算机组成与系统结构 第二次实验 结52 侯杰 2005010130

;程序简介
;输入四位十进制数，先得到各位数字的ASCII码，再转成各位数字的二进制码，根据十进制与二进制的数学关系求得整个四位数的二进制代码
;(例 十进制数1234＝1×1024－1×32＋1×8＋2×64＋2×32＋2×4＋3×8＋3×2＋4×1而其中的乘法运算都可以用二进制移位来实现)
;再将整个数字的二进制码转化成每位数字的二进制码，再转成ASCII码，输出


org 2040h
  MVRD   R14,0030h	;用于判数字符的下界值
  MVRD   R15,0039h     ;用于判数字符的上界值
  MVRD   R13,0000h     ;计数器
  MVRD   R12,0030h     ;去除ASCII码高位所用的值
  MVRD   R11,0004h     
  MVRD   R10,0003h     
  MVRD   R9,0002h 
  MVRD   R8,0001h 

input:
  IN    81h             ;判键盘上是否按了一个键, 
  SHR   R0              ;即串行口是否有了输入的字符
  SHR   R0      
  JRNC  input           ;没有输入则循环测试
  IN    80h             ;输入字符到R0
  INC   R13
  OUT   80h             ;输出刚输入的数字符
  CMP   R13,R11         ;判断输入的是哪一位的值，并存入相应寄存器
  JRC   storeR6
  CMP   R13,R10
  JRC   storeR3
  CMP   R13,R9
  JRC   storeR2
  CMP   R13,R8
  JRC   storeR1

 
exit:
  RET

storeR1:                ;将千位数字的二进制值存入R1
  SUB   R0,R12
MVRR  R1,R0
JMPA  input

storeR2:                ;将百位数字的二进制值存入R2
  SUB   R0,R12
MVRR  R2,R0
JMPA  input

storeR3:                ;将十位数字的二进制值存入R3
  SUB   R0,R12
MVRR  R3,R0
JMPA  input

storeR6:                ;将个位数字的二进制值存入R6
  SUB   R0,R12
MVRR  R6,R0
JMPA  ConvertToBIN

ConvertToBIN:               ;转化成整个四位数的二进制值,存入R15

MVRD R14,0000h
MVRD R15,0000h

MVRR R14,R1       ;千位处理
CALA MoveLeft10
MVRR R14,R1
CALA MoveLeft5B
MVRR R14,R1
CALA MoveLeft3
MVRR R14,R2       ;百位处理
CALA MoveLeft6
MVRR R14,R2
CALA MoveLeft5A
MVRR R14,R2
CALA MoveLeft2
MVRR R14,R3       ;十位处理
CALA MoveLeft3
MVRR R14,R3
CALA MoveLeft1
ADD R15,R6        ;个位处理
JMPA ConvertToDec

MoveLeft1:        ;左移1位并累加至R15
SHL R14
ADD R15,R14
RET

MoveLeft2:        ;左移2位并累加至R15
SHL R14
SHL R14
ADD R15,R14
RET

MoveLeft3:        ;左移3位并累加至R15
SHL R14
SHL R14
SHL R14
ADD R15,R14
RET

MoveLeft5A:       ;左移5位并累加至R15
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
ADD R15,R14
RET

MoveLeft5B:       ;左移5位取相反数并累加至R15
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
SUB R15,R14
RET

MoveLeft6:        ;左移6位并累加至R15
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
ADD R15,R14
RET

MoveLeft10:       ;左移10位并累加至R15
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
SHL R14
ADD R15,R14
RET


ConvertToDec:     ;将R15中的二进制数据转化成十进制数据输出

MVRD R7,3E8h
MVRD R8,64h
MVRD R9,000ah
MVRD R10,1h

MVRD R1,0000h
MVRD R2,0000h
MVRD R3,0000h
MVRD R6,0000h

MVRD R13,0030h

MVRR R12,R15      ;保留R15值，对R12操作
CALA GetR1        ;获得千位数字
CALA GetR2        ;获得百位数字
CALA GetR3        ;获得十位数字
CALA GetR6        ;获得各位数字
ADD R1,R13        ;转化成ACSII码
ADD R2,R13      
ADD R3,R13
ADD R6,R13
MVRR R0,R1        ;输出
OUT 80h
MVRR R0,R2
OUT 80h
MVRR R0,R3
OUT 80h
MVRR R0,R6
OUT 80h
RET



GetR1:
INC R1
SUB R12,R7
JRC GetR1
ADD R12,R7
DEC R1
RET

GetR2:
INC R2
SUB R12,R8
JRC GetR2
ADD R12,R8
DEC R2
RET

GetR3:
INC R3
SUB R12,R9
JRC GetR3
ADD R12,R9
DEC R3
RET

GetR6:
INC R6
SUB R12,R10
JRC GetR6
ADD R12,R10
DEC R6
RET


end
