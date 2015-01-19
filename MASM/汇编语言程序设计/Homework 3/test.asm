
 
 STORE		MACRO		X,N
			MOV		X+I,I
I=I+1
			IF			I-N
			STORE		X,N
			ENDIF
			ENDM


 
    


data segment
    OPERAND dw 100,50,10,15,24,2
    COUNT dw 6
    RESULT dw ?
    TAB dw ?
    DAT dw ?
data ends

code segment
    main proc far
    assume cs:code,ds:data
start:
    mov ax,data
    mov ds,ax
    ;main part
    I=0
    STORE TAB,7
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code ends
    end start
    