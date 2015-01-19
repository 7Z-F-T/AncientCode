data segment
    VAL1 dw -9527
    spaces db '        ','$'
data ends

code segment
    BANDO proc far
    assume cs:code,ds:data
start:
    mov ax,data
    mov ds,ax
    push VAL1 		;push the operating number to stack
    call PAIRS
    ;exit
    mov ax,4c00h
    int 21h
    BANDO endp
    
    PAIRS proc near
    mov bp,sp
    mov bx,[bp+2] 	;load the operating number to bx
    call OUTBIN
    lea dx,spaces 	;output 8 spaces
    mov ah,9
    int 21h
    mov bx,[bp+2] 	;load the operating number to bx
    call OUTOCT
    call RETURN
    ret
    PAIRS endp
    
    OUTBIN proc near
    mov cx,16 		;count
next:
    rol bx,1
    mov ax,bx
    and ax,0001 	;mask
    mov dl,al
    add dl,30h
    mov ah,2
    int 21h
    dec cx
    jnz next
    ret
    OUTBIN endp
    
    OUTOCT proc near
    rol bx,1
    mov ax,bx
    and ax,0001 	;mask
    mov dl,al
    add dl,30h
    mov ah,2
    int 21h 			;finish outputing the first number
    mov ch,5 			;count
    mov cl,3
next2:
    rol bx,cl
    mov ax,bx
    and ax,0007h 	;mask
    mov dl,al
    add dl,30h
    mov ah,2
    int 21h
    dec ch
    jnz next2
    ret
    OUTOCT endp
    
    RETURN proc near
    mov dl,0ah
    mov ah,2
    int 21h
    mov dl,0dh
    mov ah,2
    int 21h
    ret
    RETURN endp
    
code ends

     end start