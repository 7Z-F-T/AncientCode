package org.thu.core.complier;

public class IRCode {  
    static int LW = 0x23;  
    static int SW = 0x2b;  
    static  int J = 0x2;  
    static  int BEQZ = 0x4;  
    static int BNEZ = 0x5;  
    static int ADDI = 0x8;  
    static int ADDUI = 0x9;  
    static  int SUBI = 0xa;  
    static  int SUBUI = 0xb;  
    static int ANDI = 0xc;  
    static int ORI = 0xd;  
    static int XORI = 0xe;  
    static  int SLLI = 0x14;  
    static  int SRLI = 0x16;  
    static int SRAI = 0x17;  
    static  int SEQI = 0x18;  
    static  int SNEI = 0x19;  
    static int SLTI = 0x1a;  
    static int SGTI = 0x1b;  
    static int SLEI = 0x1c;  
    static int RR1 = 0x0;  
    static int RR2 = 0x1;  
    static int HALT = 0x3f;  
}  
