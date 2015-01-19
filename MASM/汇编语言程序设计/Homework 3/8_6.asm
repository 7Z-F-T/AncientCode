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
    ;测试设备 1
    in al,0024h
    test al,08h
    jne exit;第三位为1，整个过程结束
    test al,01h
    je next2;第0位为0，不输入，试下一个设备
    in al,0026h;否则输入
    mov BUFF1[si],al
    inc si
next2:
    ;测试设备 2
    in al,0036h
    test al,08h
    jne exit;第三位为1，整个过程结束
    test al,01h
    je next1;第0位为0，不输入，试下一个设备
    in al,0038h;否则输入
    mov BUFF2[di],al
    inc di
    jmp next1
exit:
    mov ax,4c00h
    int 21h
    main endp
code ends
    end start
    
    