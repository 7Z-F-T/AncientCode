data segment
    BUFF1 db 100 dup (?)
    BUFF2 db 100 dup (?)
data ends

code segment
    main proc far
    assume cs:code,ds:data
start:
    mov ax,data
    mov ds,ax
    mov es,ax
    mov si,0
    mov di,0
next1:
    ;�����豸 1
    in al,0024h
    test al,08h
    jne exit;����λΪ1���������̽���
    test al,01h
    je next2;��0λΪ0�������룬����һ���豸
    in al,0026h;��������
    mov BUFF1[si],al
    inc si
next2:
    ;�����豸 2
    in al,0036h
    test al,08h
    jne exit;����λΪ1���������̽���
    test al,01h
    je next1;��0λΪ0�������룬����һ���豸
    in al,0038h;��������
    mov BUFF2[di],al
    inc di
    jmp next1
exit:
    mov ax,4c00h
    int 21h
    main endp
code ends
    end start
    
    