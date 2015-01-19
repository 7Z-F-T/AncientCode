;Experiment 1
;[Change a signed number from HEX to DEC and from DEC to HEX]
;2005010130 ¼Æ64 ºî½Ü 
;Last edited:2008.8.27
;---------------------------------------------------------
;---------------------------------------------------------
data segment
    Dec_0_Outputable db 0 	;(used when Dec output) 0:'0' unoutputable, 1:'0' outputable
    Bin_Negative db 0		;0:positive 1:negative
    Overflow db 0 		;0:not overflow 1:overflow
    Hex_0_Outputable db 0 	;0:'0' unoutputable 1:'0' outputable
    
    OverflowInfo db 'Overflow!','$'
    WrongInputInfo db 'Wrong Input!','$'
    
    MenuItem1 db '1. HEX to DEC','$'
    MenuItem2 db '2. DEC to HEX','$'
    Instruction db 'Press 1 or 2 to select a function:','$'

data ends
;---------------------------------------------------------
;---------------------------------------------------------
code segment
    assume cs:code,ds:data
start:

;---------------------------------------------------------
    main proc far
    mov ax,data
    mov ds,ax
    
    lea dx,MenuItem1
    mov ah,9
    int 21h
    call RETURN
    lea dx,MenuItem2
    mov ah,9
    int 21h
    call RETURN
    lea dx,Instruction
    mov ah,9
    int 21h
    
    mov ah,1
    int 21h
    cmp al,31h
    je HEXtoDEC
    cmp al,32h
    je DECtoHEX
    jmp WrongInputStop1
    
HEXtoDEC:
    call RETURN
    call HEX_BIN 
    call RETURN
    call BIN_DEC
    jmp FinalEnd
DECtoHEX:
    call RETURN
    call DEC_BIN
    call IFOVERFLOW 		;decide whether it's overflow
    call RETURN
    call BIN_HEX
    jmp FinalEnd

    
FinalEnd:
    mov ax,4c00h
    int 21h
    
   
    main endp
;---------------------------------------------------------
   
    HEX_BIN proc near
    ;keyboard input, and result is to be saved in bx
    mov dx,4 		;count for at most 4 times of loop
    mov bx,0
again:
    mov ah,1
    int 21h 		;input
    cmp al,0dh 	;compare to CR
    je  exit
    cmp al,66h 	;compare to 'f'
    ja  WrongInputStop1		;>'f' then exit
    cmp al,61h 	;else then compare to 'a'
    jae toBIN1	;>='a' then it's acceptable
    cmp al,46h	;else compare to 'F'
    ja 	WrongInputStop1 		;>'F' then exit
    cmp al,41h 	;else compare to 'A'
    jae toBIN2 	;>='A' then it's acceptable
    cmp al,39h 	;else compare to '9'
    ja 	WrongInputStop1 		;>'9' then exit
    cmp al,30h 	;else compare to '0'
    jae toBIN3 	;>='0' then it's acceptable
    jmp WrongInputStop1 		;else exit
toBIN1:
    sub al,57h
    jmp store
toBIN2:
    sub al,37h
    jmp store
toBIN3:
    sub al,30h
    jmp store
store:
    mov cl,4
    shl bx,cl 	;bx=bx*16
    add bl,al
    dec dx
    jz 	exit
    jmp again
exit:
    ret
WrongInputStop1:
    call RETURN
    lea dx,WrongInputInfo
    mov ah,9
    int 21h
    jmp FinalEnd

    HEX_BIN endp
;---------------------------------------------------------
    
    BIN_DEC proc near
    ;the number to be changed must be saved in bx
    add bx,0 		;in order to get sign flag
    jns next	
outputSign:
    mov dl,2dh	;if sf=1, then first output a '-'
    mov ah,2
    int 21h
    neg  bx
next:
    mov  cx,10000d
    call DEC_DIV
    mov  cx,1000d
    call DEC_DIV
    mov  cx,100d
    call DEC_DIV
    mov  cx,10d
    call DEC_DIV
    mov  dl,bl
    add  dl,30h
    mov  ah,2
    int  21h
    ret
    BIN_DEC endp
;---------------------------------------------------------
    
    DEC_DIV proc near
    ;Screen output, and the number to be divided must be saved in bx
    mov ax,bx
    mov dx,0
    div cx 			;quotient in ax, residue in dx
    mov bx,dx 	;residue is saved in bx, quontient is saved in ax(in fact, al)
    cmp al,0
    jnz output	;for non-zero number, output it directly
    cmp Dec_0_Outputable,0
    je 	exit2
output: 		
    mov dl,al
    add dl,30h
    mov ah,2
    int 21h
    mov Dec_0_Outputable,1
exit2:
    ret
    DEC_DIV endp
;---------------------------------------------------------
    RETURN proc near
    mov dl,0ah
    mov ah,2
    int 21h
    mov dl,0dh
    mov ah,2
    int 21h
    ret
    RETURN endp
;---------------------------------------------------------
    DEC_BIN proc near
    ;keyboard input, and result is to be saved in bx
    mov bx,0
    mov di,5 			;count for at most 5 times of loop
again1:
    mov ah,1
    int 21h
    cmp al,2dh 		;compare to '-'
    je 	negative	;deal with negative
    cmp al,0dh 			;compare with CR
    je  exit3
    cmp al,39h 		;compare to '9'
    ja 	WrongInputStop2	  ;if >'9' then exit
    cmp al,30h 		;compare to '0'
    jb 	WrongInputStop2 		;if <'0' then exit
    sub al,30h
    jmp store1
negative: 				;deal with '-'
    mov Bin_Negative,1
    jmp again1
store1:
    mov ah,0
    mov si,0
    mov si,ax 		;save input number to si
    mov ax,bx
    mov cx,10d
    mul cx 				;multiply 10 to existing number
    mov bx,ax
    mov ax,si 		
    add bl,al 		;add the new input number
    dec di
    jnz again1
exit3:
    cmp Bin_Negative,0
    je 	exit4 		;if the input number is positive, jump exit4
    neg bx
exit4:
    ret
WrongInputStop2:
    call RETURN
    lea dx,WrongInputInfo
    mov ah,9
    int 21h
    jmp FinalEnd
    DEC_BIN endp
;---------------------------------------------------------
    IFOVERFLOW proc near
    add bx,0
    js  negSupposed
    jmp posSupposed
negSupposed:
    cmp Bin_Negative,0
    je wrong
    ret
posSupposed:
    cmp Bin_Negative,1
    je wrong
    ret
wrong:
    mov Overflow,1
    ret
    IFOVERFLOW endp
;---------------------------------------------------------
    BIN_HEX proc near
    cmp Overflow,1
    je OverflowStop 		;overflow! Must stop!
    mov ch,4 		;count for 4 times of loop
again2:
    mov cl,4
    rol bx,cl 	;get 4 bits
    mov al,bl
    and al,0fh 	;mask
    cmp al,9
    jbe Number	;is a number
    add al,27h
Number:
    add al,30h
    cmp al,30h 	;compare to '0'
    jne next1 	;if not '0', output it directly
    cmp Hex_0_Outputable,0
    jne next1 	;'0' outputable
    dec ch
    jmp again2
next1:
    mov dl,al
    mov ah,2
    int 21h
    mov Hex_0_Outputable,1
    dec ch
    jnz again2
    ret

OverflowStop:
    lea dx,OverflowInfo
    mov ah,9
    int 21h
    jmp FinalEnd
    BIN_HEX endp
  
    
code ends
;---------------------------------------------------------
;---------------------------------------------------------
     end start