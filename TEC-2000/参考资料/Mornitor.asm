CR      EQU     0DH
LF      EQU     0AH
TAB     EQU     09H
DEL     EQU     08H
ESC     EQU     1BH
LINE    EQU     15
UBLK    EQU     ' '*256+TAB
UCOM1   EQU     ': '
UCOM2   EQU     ','*256+TAB
CRLF    EQU     CR*256+LF
DATA    EQU     2600H
RSADR   EQU     DATA            		;Send buffer address
RSLEN   EQU     DATA+1          		;Send buffer length
RSPORT  EQU     DATA+2          		;RSPORT bit0=0 COM1(CRT)
                                		;       bit0=1 COM2(PC)
DADR    EQU     DATA+3          		;display address
EADR    EQU     DATA+4          		;input address
AADR    EQU     DATA+5          		;ASM address
UADR    EQU     DATA+6          		;UASM address
BRKINS1	EQU     DATA+7          		;save break instruction 1
BRKINS2	EQU	DATA+8	         		;save break instruction 2
BRKINS3	EQU     DATA+9          		;save break instruction 1
BRKINS4	EQU	DATA+10         		;save break instruction 2
BRKPT12	EQU     DATA+11				;save break point address 1
BRKPT34	EQU	DATA+12				;save break point address 2
SAVSP   EQU     DATA+13         		;save system stack pointer
MAPREG  EQU     DATA+14         		;(For MAP OF R0-R15,Flags)
JRFLAG	EQU	DATA+31				;1 FOR JRXX AND 0 FOR JR
STR     EQU     DATA+32	         		;4 words
BUFF    EQU     DATA+37				;buffer for input and output
STRBUF  EQU     BUFF
USSP    EQU     2780H           		;User Stack Pointer
SYSP    EQU     27FEH           		;System Stack Pointer
PORT1D  EQU     80H
PORT1C  EQU     81H
PORT2D  EQU     82H
PORT2C  EQU     83H
	ORG     0000H
START:  	
	MVRD	R0,     4EH
	OUT     PORT1C                  ;RS232 port1
	MVRD	R0,     37H
	OUT     PORT1C

	MVRD	R4,     UADR+1
	MVRD	R0,     2000H
	PUSH    R0                      ;UADR=2000H
	PUSH    R0                      ;AADR=2000H
	PUSH    R0                      ;EADR=2000H
	PUSH    R0                      ;DADR=2000H
	MVRD	R1,	MAPREG+5
	STRR	[R1],	R0           	;set user PC=2000H
	SUB	R0,	R0
	PUSH    R0                      ;RSPORT=0 console to COM1(CRT)

	MVRD	R0,     USSP
	MVRD	R1,	MAPREG+4
	STRR   	[R1],	R0           	;set user SP=2000H

	MVRD    R4,     SYSP            ;set system stack

MAIN:	
	MVRD    R2,     TITLE  		;write a string
	CALA    WSTR1CH
WAIT:   
	MVRD    R0,     '>'
	CALA    OUT1CH
	MVRD    R2,     BUFF
	CALA    INLNE		;read a string from CRT
;	PUSH	R2
;	MVRD	R2,	BUFF
;	CALA	WSTR1CH
;	POP	R2
	JRZ	WAIT
	MVRD    R10,    BUFF
	CALA    LDBYTE
	MVRR	R7,	R0
	MVRD    R3,     JCOM-1
	MVRD    R1,     'R'
	CALA	LBYTE
	CMP     R0,     R1
	JRNZ	INTERP
	DEC     R2
	JRNZ	MODR
	CALA    DISPR                   ;R command
	JR      WAIT
MODR:  	
	CALA    SCHREG
	JRZ	ERRCOM
	CALA    CHGR
	JR      WAIT
INTERP:	
	INC     R3
	LDRR    R1,     [R3]
	TEST    R1,     R1
	JRZ     ERRCOM
	CMP     R0,     R1
	JRNZ    INTERP
	PUSH	R1
	MVRD	R1,	JTAB-JCOM
	ADD	R1,	R3
	LDRR	R3,	[R1]
	POP	R1
	
	CALA    ASCNUM            	;ASCII to number(in R15)
	JRZ	NUMOK
	CALA    TESTCR
	JRNZ    ERRCOM
NUMOK: 	
	DEC     R2                	;Z for only one character
	PSHF
	PUSH	R0
	PUSH	R1
	PUSH	R2
	MVRD	R1,	27FEH
	MVRD	R2,	8000H
	STRR	[R1],	R2
	INC	R1
	STRR	[R1],	R3
	POP	R2
	POP	R1
	POP	R0
	POPF
	PUSH	R7
	CALA	27FEH
	POP	R7
	MVRD	R0,	'G'
	CMP	R0,	R7
	JRNZ	WAIT
	CALA	RETURN
	CALA	SHOWR
	JR      WAIT
ERRCOM:	
	MVRD	R2,     ERRS      	;write error message
	CALA    WSTR1CH
	JR      WAIT;--------------------------------------------------------------------
;COMMAND U - UnAssemble - MAIN
;--------------------------------------------------------------------
UASM:  	
	JRNZ	DISASM

	PUSH	R1
	MVRD	R1,	UADR
	LDRR	R15,	[R1]
	POP	R1
DISASM:	
	MVRR	R14,    R15
	MVRD	R12,    LINE
UASML: 	
	MVRR	R15,    R14
	CALA    NUMASC
	MVRD	R0,     UCOM1
	CALA    OUT2CH
	LDRR	R15,    [R14]
	CALA    NUMASC
	MVRD	R0,     ' '
	CALA	OUT1CH
	LDRR	R0,     [R14]
	INC     R14
	MVRR	R13,    R0		;SAVE R0 = INSTRUCTION WORD
	CALA    HBYTE                   ;R0=IR15-8= INSTRUCTION CODE
	CALA    USCHOP
	JRNZ	INS
	CALA    DSP6B
	MVRD	R0,     'DW'
	CALA    OUT2CH
	MVRD	R0,     UBLK
	CALA    OUT2CH
	MVRR	R15,    R13
	CALA    NUMASC
	JR      ONERET
INS:   	
	MVRD	R0,     UBLK
	CALA    OUT2CH
	TEST    R2,     R2		;直接返回，type 1
	JRZ	ONERET
	MVRR	R0,     R13
	CALA    LBYTE
	DEC	R2
	JRZ	UASM1			;Type 2
	DEC	R2
	JRZ	UASM2			;Type 3
	DEC	R2
	JRZ	UASM3			;Type 4
	DEC	R2
	JRZ	UASM4			;Type 5
	DEC	R2
	JRZ	UASM5			;Type 6
	DEC	R2
	JRZ	UASM6			;Type 7
	DEC	R2
	JRZ	UASM7			;Type 8
	DEC	R2
	JRZ	UASM8			;Type 9
	DEC	R2
	JR	UASM9			;Type 10
	
ONERET:	
	PUSH	R0
	CALA    RETURN
	POP	R0
	CALA    PAUSE
	JRZ	UARET
	DEC     R12
	JRZ	UARET
	JMPA	UASML
UARET:
	PUSH	R1
	MVRD	R1,	UADR
	STRR	[R1],	R14
	POP	R1
	RET                             ;end of UASM

;--------------------------------------------------------------------
;COMMAND U - UnAssemble - Do U
;--------------------------------------------------------------------
UASM1: 	
	MVRR	R0,	R13
	CALA	DSPREG
	JR	ONERET
UASM2: 	
	MVRR	R0,	R13
	CALA	DSPDR
	MVRR	R0,	R13
	CALA	DSPREG
	JR	ONERET
UASM3: 	
	MVRR	R0,	R13
	ADD	R0,	R14
	CALA	LBYTE
	PUSH	R14
	PUSH	R0
	MVRR	R0,	R14
	CALA	HBYTE
	POP	R14
	ADD	R0,	R14
	POP	R14
	MVRR	R15,	R0
	CALA	NUMASC
	JR	ONERET
UASM4: 	
	CALA	DSPDAT			;取下一个字
	JR	ONERET
UASM5: 	
	MVRR	R0,	R13
	CALA	LBYTE
	MVRR	R15,	R0
	CALA	NUMASC
	JR	ONERET
UASM6: 	
	MVRR	R0,	R13
	CALA	LBYTE
	CALA	SHD4
	CALA	DSPREG
	CALA	DSPBLK
	CALA	DSPDAT			;取下一个字
	JR	ONERET
UASM7: 	
	CALA	DSPLB
	PUSH	R13
	MVRR	R0,	R13
	CALA	LBYTE
	CALA	SHD4
	CALA	DSPREG
	CALA	DSPRB
	CALA	DSPBLK
	POP	R13
	MVRR	R0,	R13
	CALA	LBYTE
	CALA	DSPREG
	JR	ONERET
UASM8: 	
	PUSH	R13
	MVRR	R0,	R13
	CALA	LBYTE
	CALA	SHD4
	CALA	DSPREG
	CALA	DSPBLK
	CALA	LBYTE
	CALA	DSPLB
	POP	R13
	MVRR	R0,	R13
	CALA	DSPREG
	CALA	DSPRB
	JR	ONERET
UASM9: 	
	MVRR	R0,	R13
	CALA	SHD4
	CALA	DSPREG
	JR	ONERET
;--------------------------------------------------------------------
;COMMAND R - Display Registers - MAIN
;--------------------------------------------------------------------
DISPR: 		
		MVRD	R8,     RSTR
		MVRD	R3,     MAPREG
;输出R0~R9
		CALA    DISR
		MVRD	R2,     8
DR1:   		CALA    DISREG
		DEC     R2
		JRNZ	DR1
		PUSH	R0
		CALA    RETURN
		POP	R0
		CALA    DISR

;输出R10~R15
		MVRD	R2,     6
DR2:   		MVRD	R0,     ' R'
		CALA    DISRG
		DEC     R2
		JRNZ	DR2
		CALA    DSP2B



;输出标志寄存器
		MVRD	R0,     ' F'
		CALA	OUT2CH
		MVRD	R0,     '='
		CALA    OUT1CH
		LDRR	R1,     [R3]
		MVRD	R2,     8

DR3:		SHL	R1
		JRNC	OUT0
		MVRD	R0,	49
		CALA	OUT1CH
		JR	NEXT
OUT0:		MVRD	R0,	48
		CALA	OUT1CH
NEXT:		DEC     R2
		JRNZ	DR3
		CALA    RETURN

		MVRD	R0,	MAPREG+5
		LDRR	R14,	[R0]

		MVRD	R12,    1		;置1行
		CALA    UASML

		MVRD	R0,	MAPREG+5
		LDRR	R14,	[R0]

		MVRD	R0,	UADR
		STRR	[R0],	R14

		RET

;--------------------------------------------------------------------
;COMMAND R - Display Registers - Display a single register
;--------------------------------------------------------------------
DISREG:		MVRD	R0,     '  '

DISRG: 		
		CALA	OUT2CH

DISR:  		LDRR	R0,     [R8]
		CALA	OUT1CH
		INC	R8
  		LDRR	R0,     [R8]
		CALA	OUT1CH
		MVRD	R0,     '='
		CALA    OUT1CH
		LDRR	R15,    [R3]
		CALA    NUMASC
		INC	R3
		INC     R8
		RET

;--------------------------------------------------------------------
;COMMAND R - Display Registers - Modify a single register
;--------------------------------------------------------------------
CHGR:  		MVRD	R15,    MAPREG          ;modify registers
		ADD     R15,    R0
		PUSH    R15
		LDRR	R15,    [R15]
		CALA    NUMASC
		MVRD	R0,     ':-'
		CALA    OUT2CH
		POP     R15
		CALA    INDAT
		CALA    RETURN
		RET
;--------------------------------------------------------------------
;COMMAND E - Edit memory - MAIN
;--------------------------------------------------------------------
INPUT: 		
		JRNZ	ENTER

		PUSH	R1
		MVRD	R1,	EADR
		LDRR	R15,	[R1]
		POP	R1

ENTER: 		CALA    NUMASC                  ;display address
		MVRD	R2,     5
		CALA    DSP2B
NXTW:   	CALA    DSP2B
		PUSH    R15
		LDRR	R15,    [R15]
		CALA    NUMASC                  ;display data
		POP     R15
		MVRD	R0,     ':'
		CALA    OUT1CH
		CALA    INDAT
		JRZ	EIN
		INC     R15
		TEST    R3,     R3
		JRZ	NEXT2
		MVRD	R0,     ' '
NEXT1: 		CALA    OUT1CH
		DEC     R3
		JRNZ	NEXT1
NEXT2: 		DEC     R2
		JRNZ	NXTW
		PUSH	R0
		CALA    RETURN
		POP	R0
		JR      ENTER
EIN:   		PUSH	R0
		CALA    RETURN
		POP	R0
		PUSH	R1
		MVRD	R1,	EADR
		STRR	[R1],	R15
		POP	R1

		RET
;--------------------------------------------------------------------
;COMMAND D - DISPLAY memory - MAIN
;--------------------------------------------------------------------
DISPLAY:
	JRNZ	DUMP
	
	PUSH	R1
	MVRD	R1,	DADR
	LDRR	R15,	[R1]
	POP	R1
	
DUMP:  	
	MVRD	R3,     LINE
DSPLP: 	
	CALA    NUMASC
	CALA    DSP2B
	MVRD	R12,    STRBUF		;R12 = word address STBYTE
	MVRD	R2,     08H
DIP:   	
	CALA    DSP2B
	PUSH    R15
	LDRR	R15,    [R15]
	MVRR	R0,     R15
	CALA    SHDW
	CALA    CHAR
	MVRR	R0,     R15
	CALA    CHAR
	CALA    NUMASC
	POP     R15
	INC     R15
	DEC     R2
	JRNZ	DIP
	CALA    DSP2B
	SUB     R0,     R0
	STRR	[R12],  R0		;R12 = word address
	MVRD	R2,     STRBUF
	CALA    WSTR1CH
	PUSH	R0
	CALA    RETURN
	POP	R0
	CALA    PAUSE
	JRZ	DSPRET
	DEC     R3
	JRNZ	DSPLP
DSPRET:	
	PUSH	R1
	MVRD	R1,	DADR
	STRR	[R1],	R15
	POP	R1

	RET
;--------------------------------------------------------------------
;COMMAND G - Execute a program - MAIN
;--------------------------------------------------------------------
EXEC:		JRZ	DEFLPC

		PUSH	R1
		MVRD	R1,	MAPREG+5
		STRR	[R1],	R15
		POP	R1
         					;execute a program
DEFLPC:		CALA    RESREG

		MVRD	R14,	SAVSP
		STRR	[R14],	R4

		MVRD	R14,	MAPREG+4
		LDRR	R4,	[R14]

		MVRD	R0,     RETMON
		PUSH    R0

		MVRD	R14,	MAPREG
		LDRR	R0,	[R14]
						;restore R0
;这里没有恢复R14
		MVRD	R14,	MAPREG+5
		LDRR	R5,	[R14]
						;Execution
RETMON:		MVRD	R14,	MAPREG+4
		STRR	[R14],	R4

		JMPA	SAVR

SAVREG:		MVRD	R14,	SAVSP
		STRR	[R14],	R4

SAVR:  		MVRD	R4,     MAPREG+17
		PSHF
		PUSH    R15
		PUSH    R14
		PUSH    R13
		PUSH    R12
		PUSH    R11
		PUSH    R10
		PUSH    R9
		PUSH    R8
		PUSH    R7
		PUSH    R6			
		DEC     R4                   	;PC
		DEC     R4                    	;SP
		PUSH    R3
		PUSH    R2
		PUSH    R1
		PUSH    R0

		MVRD	R14,	SAVSP
		LDRR	R4,	[R14]

		RET

RESREG:		MVRD	R14,	SAVSP
		STRR	[R14],	R4

		MVRD	R4,     MAPREG
		POP     R0
		POP     R1
		POP     R2
		POP     R3
;只增加SP，但不出栈
		INC     R4                	;SP
		INC     R4                      ;PC
		POP	R6
		POP     R7
		POP     R8
		POP     R9
		POP     R10
		POP     R11
		POP     R12
		POP     R13
		POP     R14
		POP     R15
		POPF

		MVRD	R14,	SAVSP
		LDRR	R4,	[R14]

		RET
;--------------------------------------------------------------------
;COMMAND A - Assemble - MAIN
;--------------------------------------------------------------------
ASM:   	
	JRNZ	ASMLNE

	PUSH	R1
	MVRD	R1,	AADR
	LDRR	R15,	[R1]
	POP	R1

ASMLNE:	
	PUSH	R1
	MVRD	R1,	SAVSP
	STRR	[R1],	R4
	POP	R1
	
	MVRR	R13,    R15		;R13 is the write address
ASML: 	
	MVRR	R14,    R13             ;save R13
	MVRR	R15,    R13
	CALA    NUMASC
	MVRD	R0,     UCOM1
	CALA    OUT2CH
	MVRD	R2,     BUFF
	CALA    INSINLNE
	JRNZ	ASM1

	PUSH	R1
	MVRD	R1,	AADR
	STRR	[R1],	R13
	POP	R1

	RET                             ;end of ASM

;--------------------------------------------------------------------
;COMMAND A - Assemble - Do A
;--------------------------------------------------------------------
ASM1:  	
	MVRD	R10,    BUFF		;R10 = word address
	CALA    MOVSTR			;Move 4 words to STR
	SUB     R2,     R2              ;Search instruction
	MVRD	R11,    TYPE1           ;R2=type No. R3=POS; R0=OPCODE
	CALA    SEARCH
	JRNZ	INSYES          	;found
	INC     R2
	MVRD	R11,    TYPE2
	CALA    SEARCH
	JRNZ	INSYES          	;found
	INC     R2
	MVRD	R11,    TYPE3
	CALA    SEARCH
	JRNZ	INSYES          	;found
	INC     R2
	MVRD	R11,    TYPE4
	CALA    SEARCH
	JRNZ	INSYES          	;found
	INC     R2
	MVRD	R11,    TYPE5
	CALA    SEARCH
	JRNZ	INSYES			;found
	INC	R2
	MVRD	R11,	TYPE6
	CALA	SEARCH
	JRNZ	INSYES
	INC	R2
	MVRD	R11,	TYPE7
	CALA	SEARCH
	JRNZ	INSYES
	INC	R2
	MVRD	R11,	TYPE8
	CALA	SEARCH
	JRNZ	INSYES
	INC	R2
	MVRD	R11,	TYPE9
	CALA	SEARCH
	JRNZ	INSYES
	INC	R2
	MVRD	R11,	TYPE10
	CALA	SEARCH
	JRZ	AERR            	;Z for no found
INSYES:	
	TEST    R2,     R2
	JRZ	STOBJ           	;Type 1-No Operator
	MVRR	R8,     R0		;Save R0 -- Code
	PUSH    R10
	CALA	SKPBLK                  ;Skip all blanks
	POP     R1
	CMP     R10,    R1
	JRZ	AERR			;INSTRUCTION FOLLOWED BY No Blank - ERROR
	DEC     R2			;If Type 2
	JRNZ	ASM2
	CALA	SCHREG                  ;Type 2
	JRZ	AERR
STCOD: 	
	OR      R0,     R8

;EXIT ONE
STOBJ: 	
	STRR	[R13],  R0
	CALA    LDBYTE
	CALA    TESTCR
	JRNZ	AERR
	INC     R13
	JMPA	ASML

ASM2:
	DEC     R2
	JRNZ	ASM3
	CALA    SCHREG                  ;Type 3
	JRZ	AERR
	CALA    SHU4
	OR      R8,     R0
	CALA    TESTCM
	JRNC	AERR
	CALA    SCHREG
	JRZ	AERR
	JR      STCOD
ASM3:  	
	DEC     R2
	JRNZ	ASM4
	CALA	MOVSTR			;Type 4
	CALA	GETVAL
	JRZ	AERR
	DEC	R0
	SUB     R0,     R13
	MVRD	R1,     0FF80H
	TEST    R0,     R1
	JRZ	JROK
	DEC	R1
	CMP     R1,     R0
	JRC	AERR
JROK:
	CALA	LBYTE
	JR      STCOD
ASM4:
	DEC	R2
	JRNZ	ASM5			
	STRR	[R13],  R8
	INC	R13
	CALA    MOVSTR                  ;Type 5
	CALA    GETVAL
	JRZ	AERR
	JR	STOBJ
ASM5:
	DEC	R2
	JRNZ	ASM6
	CALA    MOVSTR                  ;Type 6
	CALA    GETVAL
	JRZ	AERR
	MVRD	R1,     100H-1		;port address<=FFH
	CMP     R1,     R0
	JRNC	AERR
	JR	STCOD
AERR:  	
	JMPA	ERRASM
ASM6:
	DEC	R2
	JRNZ	ASM7
	CALA    SCHREG                  ;Type 7
	JRZ	AERR
	CALA    SHU4
	OR      R8,     R0
	STRR	[R13],  R8
	INC	R13	
	CALA    TESTCM
	JRNC	AERR
	CALA	MOVSTR
	CALA    GETVAL
	JRZ	AERR
	JR      STOBJ
ASM7:
	DEC	R2
	JRNZ	ASM8
	CALA    TESTLC			;Type 8
	JRNZ	AERR
	CALA    SCHREG
	JRZ	AERR
	CALA    SHU4
	OR      R8,     R0
	CALA    TESTRC
	JRNZ	AERR
	CALA	TESTCM
	JRNC	AERR
	CALA    SCHREG
	JRZ	AERR
	JR	STCOD
ASM8:
	DEC	R2
	JRNZ	ASM9			
	CALA	SCHREG			;Type 9
	JRZ	AERR
	JMPA	GAICUO
	MVRR	R0,	R0
JIESHU:	
	JRNC	AERR
	CALA	TESTLC
	JRNZ	AERR
	CALA	SCHREG
	JRZ	AERR
	CALA	TESTRC
	JRNZ	AERR
	JMPA	STCOD

ASM9:
	DEC	R2
	JRNZ	AERR
	CALA	SCHREG			;Type 10
	JRZ	AERR
	CALA	SHU4
	JMPA	STCOD

;EXIT TWO
ERRASM:
	PUSH	R0
	CALA	RETURN			;应该是换行输出
	POP	R0
	MVRD	R9,     BUFF		;buffer word address
	SUB     R10,    R9
	MVRD	R1,     6
	ADD     R10,    R1
	MVRD	R0,     ' '
ERRLP: 	
	CALA    OUT1CH
	DEC     R10
	JRNZ	ERRLP
	MVRD	R2,     ERRMSG
	CALA    WSTR1CH
	MVRR	R13,    R14             ;restore R13
	PUSH	R1
	MVRD	R1,	SAVSP
	LDRR	R4,	[R1]
	POP	R1
					;restore stack
	JMPA	ASML
;--------------------------------------------------------------------
;COMMAND T - Step - MAIN
;--------------------------------------------------------------------
STEP:
	JRZ	FOX1
	MVRD	R14,	MAPREG+5
	STRR	[R14],	R15
	
FOX1:
	MVRD	R14,	MAPREG+5
	LDRR	R15,	[R14]

	LDRR	R0,     [R15]
	MVRR	R13,	R0		;SAVE R0 = INSTRUCTION CODE
	CALA	HBYTE
	MVRR	R8,	R0		;IR15-IR8
	INC	R15
	CALA    TSTINS
	JRNZ	TCOM
	INC     R15

TCOM:
	MVRD	R1,     08F00H		;RET
	MVRD	R14,	MAPREG+4
	LDRR	R2,	[R14]		;R2 = SP
	CMP     R8,     R1
	JRNZ	IADR3

	LDRR	R15,	[R2]		;R15 = [SP]
	
	INC	R2
	MVRD	R13,	2780H
	CMP	R2,	R13
	JRC	TFOX
	MVRD	R2,	2780H
TFOX:
	STRR	[R14],	R2		;INC SP
	
	MVRD	R14,	MAPREG+5	;PC = R15
	
	STRR	[R14],	R15

;--------------------------------------------------------------------
;COMMAND R - Display Registers - MAIN
;--------------------------------------------------------------------
	CALA	RETURN
	JMPA	DISPR

IADR3:
	MVRD	R1,     4100H		;JR ADR
	CMP     R8,     R1
	JRZ	IADR7

	MVRD	R1,     8000H		;JMPA ADR
	CMP     R8,     R1
	JRZ	IADR8

	MVRD	R1,     0CE00H		;CALA ADR
	CMP     R8,     R1
	JRZ	IADR8

	MVRD	R14,	JRFLAG		;Test if JRXX
	MVRD	R0,	0
	STRR	[R14],	R0

GJRC:
	MVRD	R0,	4400H
	CMP	R8,	R0
	JRZ	SAVEBK34
GJRNC:
	MVRD	R0,	4500H
	CMP	R8,	R0
	JRZ	SAVEBK34
GJRZ:
	MVRD	R0,	4600H
	CMP	R8,	R0
	JRZ	SAVEBK34
GJRNZ:
	MVRD	R0,	4700H
	CMP	R8,	R0
	JRNZ	STEP1

SAVEBK34:
	MVRD	R14,	JRFLAG		;It is JRXX
	MVRD	R0,	1
	STRR	[R14],	R0

	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS3
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     8000H
	STRR	[R15],  R1
	
	INC	R15

	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS4
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     STEP3
	STRR	[R15],  R1
	
	DEC	R15
	
	MVRD	R1,	BRKPT34
	STRR	[R1],	R15		;save break address

IADR7:
	MVRR	R0,	R13
	CALA    LBYTE                  	;R15=PC+OFF
	CALA    SIGEXT
	ADD     R15,    R0

	JR	STEP1
IADR8: 	
	DEC     R15                    	;R15=ADR

	LDRR	R15,    [R15]

;--------------------------------------------------------------------
;EXECUTE MAIN PART
;--------------------------------------------------------------------
STEP1:
	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS1
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     8000H
	STRR	[R15],  R1
	
	INC	R15

	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS2
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     STEP2
	STRR	[R15],  R1
	
	DEC	R15
	
	MVRD	R1,	BRKPT12
	STRR	[R1],	R15		;save break address

	CALA    RESREG
	
	MVRD	R14,	SAVSP
	STRR	[R14],	R4

	MVRD	R14,	MAPREG+4
	LDRR	R4,	[R14]

	MVRD	R14,	MAPREG+5
	LDRR	R5,	[R14]
;Step executed

STEP2: 	MVRD	R14,	MAPREG+4
	STRR	[R14],	R4

	MVRD	R14,	SAVSP
	LDRR	R4,	[R14]
	
	CALA    SAVREG

	MVRD	R14,	JRFLAG
	LDRR	R0,	[R14]
	MVRD	R14,	1
	CMP	R14,	R0
	
	JRNZ	STEP4

	MVRD	R14,	BRKPT34
	LDRR	R15,	[R14]

	MVRD	R14,	BRKINS3
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS4
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

STEP4:
	MVRD	R14,	BRKPT12
	LDRR	R15,	[R14]

	MVRD	R14,	MAPREG+5
	STRR	[R14],	R15
	
	MVRD	R14,	BRKINS1
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS2
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

;--------------------------------------------------------------------
;COMMAND R - Display Registers - MAIN
;--------------------------------------------------------------------
	CALA	RETURN
	JMPA	DISPR

STEP3:
	MVRD	R14,	MAPREG+4
	STRR	[R14],	R4

	MVRD	R14,	SAVSP
	LDRR	R4,	[R14]
	
	CALA    SAVREG

	MVRD	R14,	BRKPT12
	LDRR	R15,	[R14]

	MVRD	R14,	BRKINS1
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS2
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

	MVRD	R14,	BRKPT34
	LDRR	R15,	[R14]

	MVRD	R14,	MAPREG+5
	STRR	[R14],	R15

	MVRD	R14,	BRKINS3
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS4
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

;--------------------------------------------------------------------
;COMMAND R - Display Registers - MAIN
;--------------------------------------------------------------------
	CALA	RETURN
	JMPA	DISPR
;--------------------------------------------------------------------
;COMMAND P - PStep - MAIN
;--------------------------------------------------------------------
PSTEP:
	JRZ	PFOX1

	MVRD	R14,	MAPREG+5
	STRR	[R14],	R15
PFOX1:
	MVRD	R14,	MAPREG+5
	LDRR	R15,	[R14]

	LDRR	R0,     [R15]
	MVRR	R13,	R0		;SAVE R0 = INSTRUCTION CODE
	CALA	HBYTE
	MVRR	R8,	R0		;IR15-IR8
	INC	R15
	CALA    TSTINS
	JRNZ	PTCOM
	INC     R15

PTCOM:
	MVRD	R1,     08F00H		;RET
	MVRD	R14,	MAPREG+4
	LDRR	R2,	[R14]		;R2 = SP
	CMP     R8,     R1
	JRNZ	PIADR3

	LDRR	R15,	[R2]		;R15 = [SP]
	
	INC	R2
	MVRD	R13,	2780H
	CMP	R2,	R13
	JRC	PTFOX
	MVRD	R2,	2780H
PTFOX:
	STRR	[R14],	R2		;INC SP
	
	MVRD	R14,	MAPREG+5	;PC = R15
	
	STRR	[R14],	R15

;--------------------------------------------------------------------
;COMMAND R - Display Registers - MAIN
;--------------------------------------------------------------------
	CALA	RETURN
	JMPA	DISPR

PIADR3:
	MVRD	R1,     4100H		;JR ADR
	CMP     R8,     R1
	JRZ	PIADR7

	MVRD	R1,     8000H		;JMPA ADR
	CMP     R8,     R1
	JRZ	PIADR8

	MVRD	R14,	JRFLAG		;Test if JRXX
	MVRD	R0,	0
	STRR	[R14],	R0

PGJRC:
	MVRD	R0,	4400H
	CMP	R8,	R0
	JRZ	PSAVBK34
PGJRNC:
	MVRD	R0,	4500H
	CMP	R8,	R0
	JRZ	PSAVBK34
PGJRZ:
	MVRD	R0,	4600H
	CMP	R8,	R0
	JRZ	PSAVBK34
PGJRNZ:
	MVRD	R0,	4700H
	CMP	R8,	R0
	JRNZ	PSTEP1

PSAVBK34:
	MVRD	R14,	JRFLAG		;It is JRXX
	MVRD	R0,	1
	STRR	[R14],	R0

	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS3
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     8000H
	STRR	[R15],  R1
	
	INC	R15

	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS4
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     PSTEP3
	STRR	[R15],  R1
	
	DEC	R15
	
	MVRD	R1,	BRKPT34
	STRR	[R1],	R15		;save break address

PIADR7:
	MVRR	R0,	R13
	CALA    LBYTE                  	;R15=PC+OFF
	CALA    SIGEXT
	ADD     R15,    R0

	JR	PSTEP1
PIADR8:
	DEC     R15                    	;R15=ADR

	LDRR	R15,    [R15]

;--------------------------------------------------------------------
;EXECUTE MAIN PART
;--------------------------------------------------------------------
PSTEP1:
	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS1
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     8000H
	STRR	[R15],  R1
	
	INC	R15

	LDRR	R1,     [R15]
	
	MVRD	R0,	BRKINS2
	STRR	[R0],	R1		;save break instruction
	
	MVRD	R1,     PSTEP2
	STRR	[R15],  R1
	
	DEC	R15
	
	MVRD	R1,	BRKPT12
	STRR	[R1],	R15		;save break address

	CALA    RESREG
	
	MVRD	R14,	SAVSP
	STRR	[R14],	R4

	MVRD	R14,	MAPREG+4
	LDRR	R4,	[R14]

	MVRD	R14,	MAPREG+5
	LDRR	R5,	[R14]
;Step executed

PSTEP2: 	
	MVRD	R14,	MAPREG+4
	STRR	[R14],	R4

	MVRD	R14,	SAVSP
	LDRR	R4,	[R14]
	
	CALA    SAVREG

	MVRD	R14,	JRFLAG
	LDRR	R0,	[R14]
	MVRD	R14,	1
	CMP	R14,	R0
	
	JRNZ	PSTEP4

	MVRD	R14,	BRKPT34
	LDRR	R15,	[R14]

	MVRD	R14,	BRKINS3
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS4
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

PSTEP4:
	MVRD	R14,	BRKPT12
	LDRR	R15,	[R14]

	MVRD	R14,	MAPREG+5
	STRR	[R14],	R15
	
	MVRD	R14,	BRKINS1
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS2
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

;--------------------------------------------------------------------
;COMMAND R - Display Registers - MAIN
;--------------------------------------------------------------------
	CALA	RETURN
	JMPA	DISPR

PSTEP3:
	MVRD	R14,	MAPREG+4
	STRR	[R14],	R4

	MVRD	R14,	SAVSP
	LDRR	R4,	[R14]
	
	CALA    SAVREG

	MVRD	R14,	BRKPT12
	LDRR	R15,	[R14]

	MVRD	R14,	BRKINS1
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS2
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

	MVRD	R14,	BRKPT34
	LDRR	R15,	[R14]

	MVRD	R14,	MAPREG+5
	STRR	[R14],	R15

	MVRD	R14,	BRKINS3
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	INC	R15
	
	MVRD	R14,	BRKINS4
	LDRR	R1,	[R14]

	STRR	[R15],  R1		;restore break instruction
	DEC	R15

;--------------------------------------------------------------------
;COMMAND R - Display Registers - MAIN
;--------------------------------------------------------------------
	CALA	RETURN
	JMPA	DISPR
;--------------------------------------------------------------------
;Read a character from console
;--------------------------------------------------------------------
INCH:
	CALA    TESTIN
	JRNC	INCH

	PUSH	R1
	MVRD	R1,	RSPORT
	LDRR	R0,	[R1]
	POP	R1

	SHR     R0
	JRC	PCIN
	IN      PORT1D
	JR      LBYTE
PCIN:
	IN      PORT2D
LBYTE:
	PUSH    R1
	MVRD    R1,     00FFH
	JR      HLB
HBYTE:
	PUSH    R1
	MVRD	R1,     0FF00H
HLB:
	AND     R0,     R1
	POP     R1
	RET

;--------------------------------------------------------------------
;Test which rsport connect to CRT
;--------------------------------------------------------------------
TESTIN:
	PUSH	R1
	MVRD	R1,	RSPORT
	LDRR	R0,	[R1]
	POP	R1
	SHR     R0
	JRC	TPC
	IN      PORT1C
	JR      TCRTPC
TPC:	
	IN      PORT2C
TCRTPC:	
	SHR     R0
	SHR     R0
	RET

;--------------------------------------------------------------------
;Delete A Char From Console
;--------------------------------------------------------------------
DELCH:	
	MVRD	R0,     0820H    	;delete a character
	CALA    OUT2CH          	;modify R0
	MVRD	R0,     08H
	JR	OUT1CH
DSPDR:	
	CALA    SHD4
	CALA    DSPREG
DSPBLK:
	MVRD	R0,     UCOM2
	JR      OUT2CH
DSPLB:	
	MVRD	R0,     '['
	JR      OUT1CH
DSPRB:
	MVRD	R0,     ']'
	JR      OUT1CH
;--------------------------------------------------------------------
;WRITE SIX blanks TO console
;--------------------------------------------------------------------
DSP6B: 	
	CALA	DSP2B
	CALA	DSP2B
;--------------------------------------------------------------------
;WRITE TWO blanks TO console
;--------------------------------------------------------------------
DSP2B:
	MVRD	R0,     '  '
	JR      OUT2CH
;--------------------------------------------------------------------
;WRITE a CRLF TO console
;--------------------------------------------------------------------
RETURN:
	MVRD	R0,     CRLF

;--------------------------------------------------------------------
;WRITE TWO characters TO console
;R0
;--------------------------------------------------------------------
OUT2CH:	
	PUSH    R0
	CALA    SHDW
	CALA    OUT1CH
	JR      LOWCH
;--------------------------------------------------------------------
;WRITE a character TO console
;R0
;--------------------------------------------------------------------
OUT1CH:	
	PUSH    R0
LOWCH: 	
	PUSH	R1
	MVRD	R1,	RSPORT
	LDRR	R0,	[R1]
	POP	R1
		
CRTOUT:	
	IN      PORT1C
	SHR     R0
	JRNC	CRTOUT
	POP     R0
	OUT     PORT1D
	RET
GAICUO: 	
	CALA	SHU4
	OR	R8,	R0
	CALA	TESTCM
	JMPA	JIESHU
	RET

;--------------------------------------------------------------------
;WRITE a STRING end with 0 TO console
;String Address In R2
;--------------------------------------------------------------------
WSTR1CH:
	PUSH    R0
WS1CH:
	LDRR	R0,     [R2]     	;string address in R2
	TEST    R0,     R0
	JRZ	EOW1CH
	CALA    OUT1CH
	INC     R2
	JR      WS1CH
EOW1CH:
	POP     R0
	RET

;--------------------------------------------------------------------
;Read a string from console
;--------------------------------------------------------------------
INLNE: 		
	PUSH    R12
	PUSH    R1
	PUSH    R0
	PUSH	R3
	MVRR	R12,    R2		;R12 -- 当前位置
	SUB     R2,     R2		;R2 -- 不带空格
	SUB	R3,	R3		;R3 -- 带空格
INL1:	
	CALA    INCH
	CALA    TESTCR
	JRZ	EOL
	MVRD	R1,     DEL
	CMP     R1,     R0
	JRNZ	NDEL
	TEST    R3,     R3
	JRZ	INL1
	CALA	DELCH
	DEC	R12
	DEC	R3
	LDRR	R0,	[R12]
	MVRD	R1,	' '
	CMP	R1,	R0
	JRZ	INL1
	DEC	R2
	JR      INL1
NDEL:  	
	CALA    OUT1CH
	INC     R3
	MVRD	R1,     ' '
	CMP     R1,     R0
	JRZ	ISSP
	INC	R2
ISSP:	CALA    UPCASE
	CALA    STBYTE
	JR      INL1
EOL:   	
	MVRD	R0,     CR
	CALA	STBYTE
;	MVRD	R0,	0
;	CALA	STBYTE
	CALA    RETURN

	CALA	REMOVESP

	POP	R3
	POP     R0
	POP     R1
	POP     R12
	TEST    R2,     R2              ;R2 the number of characters
					;Z for R2=0
	RET                             ;end with CR

;--------------------------------------------------------------------
;Read a INSTRUCTION from console
;--------------------------------------------------------------------
INSINLNE: 		
	PUSH    R12
	PUSH    R1
	PUSH    R0
	MVRR	R12,    R2
	SUB     R2,     R2
INSINL1:	
	CALA    INCH
	CALA    TESTCR
	JRZ	INSEOL
	MVRD	R1,     DEL
	CMP     R1,     R0
	JRNZ	INSNDEL
	TEST    R2,     R2
	JRZ	INSINL1
	CALA    DELCH
	DEC     R2
	DEC	R12
	JR      INSINL1
INSNDEL:  	
	CALA    OUT1CH
	CALA    UPCASE
	CALA    STBYTE
	INC     R2
	JR      INSINL1
INSEOL:   	
	MVRD	R0,     CR
	CALA    STBYTE
	CALA    RETURN
	POP     R0
	POP     R1
	POP     R12
	TEST    R2,     R2              ;R2 the number of characters
					;Z for R2=0
	RET                             ;end with CR

;--------------------------------------------------------------------
;Upcase a character in R0
;--------------------------------------------------------------------
UPCASE:		
	PUSH    R1
	MVRD	R1,     'a'-1
	CMP     R1,     R0
	JRC	UPCH
	MVRD	R1,     'z'+1
	CMP     R0,     R1
	JRC	UPCH
	MVRD	R1,     00DFH
	AND     R0,     R1
UPCH:  	
	POP     R1
	RET

;--------------------------------------------------------------------
;INPUT DATA to modify memory
;--------------------------------------------------------------------
INDAT: 	
	MVRD	R11,    4
	MVRD	R12,    STR		;R12 = word address
	MVRR	R3,     R11
LOOP:  	
	CALA    INCH
	CALA    TESTCR
	JRZ	EOIND
	MVRD	R1,     ' '
	CMP     R1,     R0
	JRZ	EOIND
	MVRD	R1,     DEL
	CMP     R1,     R0              ;DEL key
	JRZ	ERS
	TEST    R3,     R3
	JRZ	LOOP
	PUSH    R0
	CALA    CHRNUM
	POP     R0
	JRNC	LOOP            	;not a hex digit
	CALA    STBYTE
	CALA    OUT1CH
	DEC     R3
	JR      LOOP
ERS:   	
	CMP     R11,     R3
	JRZ	LOOP
	INC     R3
	CALA    DELCH
	DEC     R12
	JR      LOOP
EOIND: 	
	CALA    STBYTE
	CMP     R11,     R3
	JRZ	TESTCR
	MVRD	R10,    STR		;R10 = word address
	PUSH    R15
	PUSH    R0
	CALA    ASCNUM
	POP     R0
	MVRR	R14,    R15
	POP     R15
	STRR	[R15],  R14
;--------------------------------------------------------------------
;TEST IF R0 = CR
;--------------------------------------------------------------------
TESTCR:	
	MVRD	R1,     CR              ;modify R1
	CMP     R1,     R0
	RET

;--------------------------------------------------------------------
;Judge If it is a printable character, if no print '.'
;--------------------------------------------------------------------
CHAR:
	CALA    LBYTE
	MVRR	R8,     R0
	MVRD	R1,     07FH
	INC	R1
	CMP     R8,     R1
	JRC	BLCH
	MVRD	R1,     ' '-1
	CMP     R1,     R8
	JRNC	STBYTE
BLCH:
	MVRD	R0,     '.'
;--------------------------------------------------------------------
;Store A BYTE TO Memory
;--------------------------------------------------------------------
STBYTE:	
	STRR	[R12],  R0
	INC     R12
	RET

;--------------------------------------------------------------------
;Load A BYTE FROM Memory
;--------------------------------------------------------------------
LDBYTE:
	LDRR	R0,     [R10]
	INC     R10
ASC:
	PUSH    R1                      ;test R0=0-9,A-Z
	MVRD	R1,     '0'-1
	CMP     R1,     R0
	JRC	NASC
	MVRD	R1,     '9'+1
	CMP     R0,     R1
	JRNC	NASC            	;R0<='9'
	MVRD	R1,     'A'-1
	CMP     R1,     R0
	JRC	NASC
	MVRD	R1,     'Z'+1
	CMP     R0,     R1
NASC:
	POP     R1                      ;C --R0 is not a letter
	RET                             ;NC--R0 is a letter

;--------------------------------------------------------------------
;SHR 8 bits
;--------------------------------------------------------------------
SHDW:  		
	CALA    SHD4
;--------------------------------------------------------------------
;SHR 4 bits
;--------------------------------------------------------------------
SHD4:  	
	SHR     R0
	SHR     R0
	SHR     R0
	SHR     R0
	RET

;--------------------------------------------------------------------
;SHL 8 bits
;--------------------------------------------------------------------
SHUP:  	
	CALA    SHU4
;--------------------------------------------------------------------
;SHL 4 bits
;--------------------------------------------------------------------
SHU4:  	
	SHL     R0
	SHL     R0
	SHL     R0
	SHL     R0
	RET

;--------------------------------------------------------------------
;Display [R14] - Used by UASM
;--------------------------------------------------------------------
DSPDAT:	
	LDRR	R15,    [R14]
	INC     R14
;--------------------------------------------------------------------
;Number To ASCII	R15 - CRT
;--------------------------------------------------------------------
NUMASC:	
	PUSH    R15                 	;DATA in R15(unchange)
	PUSH    R2                 	
	PUSH    R0			;ASCII to CRT
	MVRD	R2,     4          	;the number of bits
NUMA:  	
	SUB     R0,     R0
;	SHL     R15
;	RCL     R0
;	SHL     R15
;	RCL     R0
;	SHL     R15
;	RCL     R0
;	SHL     R15
;	RCL     R0
	PUSH	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	SHR	R15
	MVRR	R0,	R15
	POP	R15

	SHL	R15
	SHL	R15
	SHL	R15
	SHL	R15
	CALA    DISPCH
	DEC     R2
	JRNZ	NUMA
	POP     R0
	POP     R2
	POP     R15
	RET

;--------------------------------------------------------------------
;Display a char
;--------------------------------------------------------------------
DISPCH:	
	PUSH    R1
	CALA    LBYTE
	MVRD	R1,     '0'
	ADD     R0,     R1
	MVRD	R1,     '9'+1
	CMP     R0,     R1
	JRNC	DIPCH
	MVRD	R1,     7
	ADD     R0,     R1
DIPCH: 	
	CALA	OUT1CH
	POP     R1
	RET

;--------------------------------------------------------------------
;ASCII To Number 	[R10] to R15
;[R10][R10+1][R10+2][R10+3] to R15
;four char
;--------------------------------------------------------------------
ASCNUM:	
	SUB     R15,    R15             ;ASCII in [R10]
	PUSH    R2                      ;DATA  in R15
	PUSH    R1
	MVRD	R2,     4
ASCN:  	
	CALA    LDBYTE
	CALA	LBYTE
	MVRR	R1,     R0
	CALA	CHRNUM
	JRNC	ERRAN
	SHL     R15
	SHL     R15
	SHL     R15
	SHL     R15
	OR      R15,    R0
	DEC     R2
	JRNZ	ASCN
ERRAN: 	
	DEC     R10
	MVRR	R0,     R1
	TEST    R2,     R2
	POP     R1
	POP     R2             		;Z(R2=0)---value in R15
	RET                    		;NZ ---error(R0 error CHAR)

;--------------------------------------------------------------------
;Character To Number	R0[LOW BYTE] to R0	
;--------------------------------------------------------------------
CHRNUM:	
	PUSH    R1
	CALA    UPCASE
	MVRD	R1,     '0'        	;ASCII in R0(bit7-0)
	SUB     R0,     R1         	;DATA  in R0
	JRNC	ERR
	MVRD	R1,     0AH-1
	CMP     R1,     R0
	JRC	OK
	MVRD	R1,     7
	SUB     R0,     R1
	MVRD	R1,     0AH-1
	CMP     R1,     R0
	JRC	ERR
	MVRD	R1,     10H-1
	CMP     R1,     R0
	JRC	OK
ERR:   	
	SUB     R0,     R0
	SHR	R0			;NC--error
OK:    	
	POP     R1
	RET                             ;C---DATA in R0

;--------------------------------------------------------------------
;Search R12 in R11 for 4 words
;Return R3 - Pos
;--------------------------------------------------------------------
SCHREG:	
	CALA    MOVSTR
	MVRD	R11,    REG
SEARCH:	
	SUB     R3,     R3
SCH:   	
	MVRD	R12,    STR             ;R12--STR
	LDRR	R0,     [R11]           ;R11--LIB
	TEST    R0,     R0              ;R3 --POS
	JRZ	NOF
	CALA	COMP
	JRZ	SCH1
	INC	R11
	INC	R11
	INC	R11
	JR	SCH2
SCH1:  	
	CALA    COMP
	JRZ	SCH12
	INC	R11
	INC	R11
	JR	SCH2
SCH12:
	CALA    COMP
	JRZ	SCH13
	INC	R11
	JR	SCH2
SCH13:
	CALA    COMP
	JRZ	SCH3
SCH2:  	
	INC     R11
	INC     R3
	JR      SCH
SCH3:  	
	LDRR	R0,     [R11]
	TEST    R11,    R11             ;set NZ, R0=value
NOF:   	
	RET                             ;     Z, no found

;--------------------------------------------------------------------
;Compare Data in Buffer
;--------------------------------------------------------------------
COMP:  	
	LDRR	R0,     [R12]
	LDRR	R1,     [R11]
	INC     R12
	INC     R11
	CMP     R1,     R0              ;NZ--unequal
	RET                             ;Z --equal

;--------------------------------------------------------------------
;Test if there is a comma followed
;--------------------------------------------------------------------
TESTCM:	
	CALA	SKPBLK			;清空逗号前的所有空格
	CALA    LDBYTE
	MVRD	R1,     ','
	CALA	LBYTE
	CMP     R1,     R0		;判断是不是逗号
	JRZ	SKPBLK			;继续清空逗号后的所有空格
	SUB	R1,	R1
	SHR	R1
	JR	ERRCM			;不是，清C，返回
SKPBLK:	
	MVRD	R1,     ' '
SKPALL:	
	CALA    LDBYTE                  ;Skip all blanks
	CMP     R1,     R0
	JRZ	SKPALL
	DEC     R10
	MVRD	R1,	1
	SHR	R1
ERRCM:
	RET				;NC - NOT FIND
					;C - FIND AND SKIP

;-------------------------------------------------------------------
;Test if '['
;-------------------------------------------------------------------
TESTLC:	
	PUSH    R0
	CALA    LDBYTE
	MVRD	R1,     '['
	CALA	LBYTE
	CMP     R1,     R0
	JRZ	RETLC
	DEC     R10
RETLC: 	
	POP     R0			;Z-YES
	RET                             ;NZ-NO

;-------------------------------------------------------------------
;Test if ']'
;-------------------------------------------------------------------
TESTRC:	
	PUSH    R0
	CALA	LDBYTE
	MVRD	R1,     ']'
	CALA	LBYTE
	CMP     R1,     R0
	JRZ	RETRC
	DEC	R10
RETRC:
	POP     R0			;Z-YES
	RET				;NZ-NO

;-------------------------------------------------------------------
;Get value followed
;-------------------------------------------------------------------
GETVAL:	
	PUSH    R10
	MVRD	R10,    STR
	MVRR	R1,     R10
	CALA    ASCNUM
	JRZ	VALR15
	CALA	ASC
	JRC	VALR15
	POP     R10
	SUB	R1,	R1
	JR	VALRET
VALR15:	
	MVRR	R0,	R15
	CMP     R1,	R10
	POP     R10
VALRET:
	RET				;NZ-value in R0

;-------------------------------------------------------------------
;MOVE 4 words from [R10] to STR
;-------------------------------------------------------------------
MOVSTR:	
	MVRD	R12,    STR		;word address

	MVRD	R0,     ' '		;清空串
	PUSH	R1
	MVRD	R1,	STR
	STRR	[R1],	R0
	INC	R1
	STRR	[R1],	R0
	INC	R1
	STRR	[R1],	R0
	INC	R1
	STRR	[R1],	R0
	POP	R1

	CALA    LDBYTE
	JRC	NOCHR
	CALA    STBYTE
	CALA    LDBYTE
	JRC	NOCHR
	CALA    STBYTE
	CALA    LDBYTE
	JRC	NOCHR
	CALA    STBYTE
	CALA    LDBYTE
	JRC	NOCHR
	CALA    STBYTE
	JR      MVSEND
NOCHR: 	
	DEC     R10
MVSEND:	
	RET

;-------------------------------------------------------------------
;Pause until press a key
;-------------------------------------------------------------------
PAUSE: 	
	MVRD	R1,     ESC
	CALA    TESTIN
	JRNC	NOKEY
	CALA    INCH
	CMP     R1,     R0
	JRZ	PSRET
	CALA    INCH
NOKEY: 	
	CMP     R1,     R0
PSRET: 	
	RET                          	;Z=1 for [ESC]
                               		;Z=0 for other key

;-------------------------------------------------------------------
;------ procedure SEND() -------
;-------------------------------------------------------------------
SEND:	
	PUSH	R1
	MVRD	R1,	RSADR
	LDRR	R3,	[R1]
	POP	R1			;start address
	PUSH	R1
	MVRD	R1,	RSLEN
	LDRR	R2,	[R1]
	POP	R1			;file length
	MVRR	R0,     R3
	CALA    SENDCH                  ;send start address
	MVRR	R0,     R2
	CALA    SENDCH                  ;send file length
TXD:   	
	LDRR	R0,     [R3]
	CALA    SENDCH
	INC     R3
	DEC     R2
	JRNZ	TXD
	RET

SENDCH:	
	PUSH    R0
	CALA    SHDW
	CALA    SNDCH
	POP     R0
SNDCH: 	
	CALA    OUT1CH
	CALA    INCH
	RET

;-------------------------------------------------------------------
;------ Procedure RECV() -------
;-------------------------------------------------------------------
RECV:  	
	CALA    RECW
	MVRR	R3,     R14     	;Receive start address

	PUSH	R1
	MVRD	R1,	MAPREG+5
	STRR	[R1],	R14
	POP	R1		 	;Set default execution address
	CALA    RECW
	MVRR	R2,     R14     	;Receive file length
RXD:   	
	CALA    RECW
	STRR	[R3],   R14
	INC     R3
	DEC     R2
	JRNZ	RXD
	RET

RECW:  	
	CALA    INCH
	CALA    OUT1CH
	CALA    SHUP
	MVRR	R14,    R0
	CALA    INCH
	CALA    OUT1CH
	OR      R14,    R0
	RET
	
;-------------------------------------------------------------------
;Test if it is a two words instruction Z-Yes NZ-No
;-------------------------------------------------------------------
TSTINS:	
	MVRD	R1,     8000H
	CMP     R0,     R1
	JRZ	ENDINS
	MVRD	R1,     8800H
	CMP     R0,     R1
	JRZ	ENDINS
	MVRD	R1,     0CE00H
	CMP     R0,     R1
ENDINS:
	RET                             ;Z-two words
					;NZ--one word

;-------------------------------------------------------------------
;Search and Display Register & Instruction
;-------------------------------------------------------------------
DSPREG:	
	SUB     R9,     R9
	MVRD	R1,     000FH
	AND     R0,     R1
	MVRD	R11,    REG+1
USCHC:
	SUB     R3,     R3
REPSCD:	
	LDRR	R1,     [R11]
	TEST    R1,     R1
	JRZ	SCDRET
	INC     R11
	INC	R11
	INC	R11
	LDRR	R1,     [R11]
	CMP     R1,     R0
	JRZ	FCODE
NFCOD: 	
	INC     R11
	INC	R11
	INC     R3
	JR      REPSCD
FCODE: 	
	DEC	R11
	DEC	R11
	DEC     R11
	DEC     R11                     ;R11 point to ASCII
DIPNM: 	
	TEST    R9,     R9
	JRZ	DSPCD2
	CALA	TSTINS			;IF TWO WORDS, NEXT WORD , ELSE PRINT INSTRUCTION
	JRNZ	DSPCD1
	LDRR	R15,    [R14]		;DISPLAY NEXT WORD
	CALA    NUMASC
	CALA    DSP2B
	JR	DSPCD2
DSPCD1:
	CALA    DSP6B
DSPCD2:	
	MVRR	R10,    R11		;R10=word address
	MVRD	R11,    4
DIPCHR:	
	CALA    LDBYTE
	JRC	EODSP
	CALA    OUT1CH
	DEC     R11
	JRNZ	DIPCHR
EODSP: 	
	TEST    R10,    R10             ;set NZ for found
SCDRET:	
	RET

;-------------------------------------------------------------------
;Search Instruction using code
;-------------------------------------------------------------------
USCHOP: 
	MVRD	R9,     1		;Search Instruction
	SUB     R2,     R2
	MVRD	R11,    TYPE1+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=0
	INC     R2
	MVRD	R11,    TYPE2+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=1
	INC     R2
	MVRD	R11,    TYPE3+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=2
	INC     R2
	MVRD	R11,    TYPE4+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=3
	INC     R2
	MVRD	R11,    TYPE5+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=4
	INC     R2
	MVRD	R11,    TYPE6+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=5
	INC     R2
	MVRD	R11,    TYPE7+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=6
	INC     R2
	MVRD	R11,    TYPE8+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=7
	INC     R2
	MVRD	R11,    TYPE9+1
	CALA    USCHC
	JRNZ	OPCRET          	;R2=8
	INC     R2
	MVRD	R11,    TYPE10+1
	CALA    USCHC
					;R2=9

OPCRET:
	RET                    		;NZ found, R2=Type No., R3=POS.
					;Z  not found

;-------------------------------------------------------------------
;Decide +/- offset
;-------------------------------------------------------------------
SIGEXT:	
	MVRD	R1,     80H
	TEST    R0,     R1
	JRZ	PLUS
	MVRD	R1,     0FF00H
	OR      R0,     R1
PLUS:  	
	RET

;-------------------------------------------------------------------
;Remove spaces in string
;-------------------------------------------------------------------
REMOVESP:
	PUSH	R8
	PUSH	R9
	PUSH	R1
	INC	R3
	MVRD	R8,	BUFF
	MVRD	R9,	BUFF
LOOPSP:
	LDRR	R0,	[R8]
	MVRD	R1,	' '
	CMP	R0,	R1
	JRNZ	NOSP
	INC	R8
	DEC	R3
	JRNZ	LOOPSP
	JR	RMRET
NOSP:
	STRR	[R9],	R0
	INC	R8
	INC	R9
	DEC	R3
	JRNZ	LOOPSP
RMRET:
	POP	R1
	POP	R9
	POP	R8
	RET

;-------------------------------------------------------------------
;SHOW REGISTER
;-------------------------------------------------------------------
SHOWR:		MVRD	R8,     RSTR
		MVRD	R3,     MAPREG
;输出R0~R9
		CALA    DISR
		MVRD	R2,     8
GDR1:   	CALA    DISREG
		DEC     R2
		JRNZ	GDR1
		PUSH	R0
		CALA    RETURN
		POP	R0
		CALA    DISR

;输出R10~R15
		MVRD	R2,     6
GDR2:  		MVRD	R0,     ' R'
		CALA    DISRG
		DEC     R2
		JRNZ	GDR2
		CALA    DSP2B



;输出标志寄存器
		MVRD	R0,     ' F'
		CALA	OUT2CH
		MVRD	R0,     '='
		CALA    OUT1CH
		LDRR	R1,     [R3]
		MVRD	R2,     8

GDR3:		SHL	R1
		JRNC	GOUT0
		MVRD	R0,	49
		CALA	OUT1CH
		JR	GNEXT
GOUT0:		MVRD	R0,	48
		CALA	OUT1CH
GNEXT:		DEC     R2
		JRNZ	GDR3
		CALA    RETURN
		RET
JCOM   	DW    	'E',  'D',  'G',  'A',  'U',  'T',  'P',  0

JTAB   	DW   	INPUT, DISPLAY, EXEC, ASM, UASM, STEP, PSTEP
	
;1 - Follow None
;1 WORD
TYPE1   DW   	'PSHF', 8400H,'POPF', 8C00H, 'RET ', 8F00H, 0


;2 - Follow By A Register
;1 WORD
TYPE2  	DW 	'PUSH', 8500H, 0
	
;3 - Follow By Two Registers
;1 WORD
TYPE3  	DW 	'ADD ', 0000H,'SUB ', 0100H,

	DW  	'CMP ', 0300H,'AND ', 0200H,'OR  ', 0600H,'XOR ', 0400H

	DW  	'TEST', 0500H,'MVRR', 0700H, 0
	
;4 - Follow By An Offset
;1 WORD
TYPE4   DW   	'JR  ', 4100H,'JRZ ', 4600H,'JRNZ', 4700H,'JRC ', 4400H

	DW	'JRNC', 4500H, 0

;5 - Follow By Any Address
;2 WORD
TYPE5   DW   	'JMPA', 8000H,'CALA', 0CE00H, 0

;6 - Follow By A Port <= FFH
;1 WORD
TYPE6	DW	'IN  ', 8200H,'OUT ', 8600H, 0

;7 - Follow By A Register And A Adress
;2 WORD
TYPE7	DW	'MVRD', 8800H, 0

;8 - Follow By A [R] and R
;1 WORD
TYPE8	DW	'STRR', 8300H, 0

;9 - Follow By A R and [R]
;1 WORD
TYPE9	DW	'LDRR', 8100H, 0

;10 - Follow By A Register
;1 WORD
TYPE10  DW	'POP ',	8700H,'INC ', 0900H,'DEC ', 0800H,
	
	DW  	'SHL ', 0A00H,'SHR ', 0B00H,  0

REG  	DW   	'R0  ',00,'R1  ',01,'R2  ',02,'R3  ',03

	DW  	'R4  ',04,'R5  ',05,'R6  ',06,'R6  ',06

	DW  	'R7  ',07,'R8  ',08,'R9  ',09,'R10 ',10,'R11 ',11

	DW  	'R12 ',12,'R13 ',13,'R14 ',14,'R15 ',15, 0

RSTR  	DW   	'R0R1R2R3SPPCR6R7R8R9101112131415'

ERRMSG  DW      '^Error',CR,LF,0

TITLE   DW	CR,LF,CR,LF

	DW      'TEC-2000 CRT MONITOR ',CR,LF

	DW      'Version 2.0   2001.10',CR,LF

	DW  	'Computer Architectur Lab.,Tsinghua University',CR,LF
	
	DW	'Copyright Jason He',CR,LF,0
	
ERRS	DW      'Unknown command!',CR,LF,0

END