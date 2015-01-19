data segment
    TABLE db 100 dup (?)	;maximal characters that can be received:100
    N dw ?		;length of string
    Info1 db 'Please input a string:','$'
    Info2 db 'Please input the char you want to search for:','$'
    Info3 db ' times','$'
data ends

code segment
    main proc far
    assume cs:code,ds:data,es:data
start:
    ;initialize
    mov ax,data
    mov ds,ax
    mov es,ax
    ;main procedure
    ;input a string
    mov si,0
    lea dx,Info1
    mov ah,9
    int 21h
input:
    mov ah,1
    int 21h
    cmp al,0dh		;compare to return
    je  startSearch	;stop input if receiving a return
    mov TABLE[si],al
    inc si
    mov N,si			;update character count
    jmp input
startSearch:
    mov si,0
next:
    call RETURN
    lea dx,Info2
    mov ah,9
    int 21h
    mov bx,0
    mov ah,1			;get the char to be searched
    int 21h
    and ax,00ffh
    push ax 			;pass the parameter by stack
    push N
    mov ax,offset TABLE
    push ax
    call search
    call RETURN
    pop ax
    mov dl,al
    mov ah,2
    int 21h
    mov dl,3ah
    mov ah,2
    int 21h
    mov dl,bl
    add dl,30h
    mov ah,2 			;output the times of appearence
    int 21h
    lea dx,Info3
    mov ah,9
    int 21h
    call RETURN
    inc si
    jmp next
    ;exit
    mov ax,4c00h
    int 21h
    main endp
    
    search proc near
    mov bp,sp
    add bp,2		
    mov di,[bp]		
    push cx
    push ax
    push dx
    mov dx,0
    mov cx,[bp+2]
    mov al,[bp+4]
again:								;search
    cmp al,[di]
    jne continue
    inc dx
continue:
    inc di
    dec cx
    jz  exit
    jmp again
exit:
    mov bx,dx 				;restore the stack
    pop dx
    pop ax
    pop cx
    ret 4
    search endp
    
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