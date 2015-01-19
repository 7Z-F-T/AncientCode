D_SEG segment
    AUGEND dd 99251
    SUM dd ?
D_SEG ends

E_SEG segment
    ADDEND dd -15962
E_SEG ends

C_SEG segment
    main proc far
    assume cs:C_SEG,ds:D_SEG,es:E_SEG
start:
    push ds
    sub ax,ax
    push ax
    mov ax,D_SEG
    mov ds,ax
    mov ax,E_SEG
    mov es,ax
    ;start calculation
    mov ax,word ptr AUGEND
    mov dx,word ptr AUGEND+2
    mov cx,word ptr ADDEND
    mov bx,word ptr ADDEND+2
    add ax,cx
    adc dx,bx
    mov word ptr SUM,ax
    mov word ptr SUM+2,dx
    
    ret
    main endp
C_SEG ends
    end start