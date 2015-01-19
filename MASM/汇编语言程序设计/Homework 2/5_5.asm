data segment
    n dw 11
    result dw ?
data ends

code segment
    main proc far
    assume cs:code,ds:data
start:
    ;initialize
    mov ax,data
    mov ds,ax
    ;start
    mov cx,n
    sub cx,2;We need to loop for n-2 times in order to calculate FIB(n)
    mov ax,1
    mov bx,1
calc:
    mov dx,bx;temporarily storing previous bx
    add bx,ax
    mov ax,dx
    loop calc
    
    mov result,bx;store in to result
    
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code ends
    end start