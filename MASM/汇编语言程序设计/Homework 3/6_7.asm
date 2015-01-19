data segment
    score db 76,69,84,90,73,88,99,63,100,80
    count db 10
    S6 db 0
    S7 db 0
    S8 db 0
    S9 db 0
    S10 db 0
data ends

code segment

    main proc far
    assume cs:code,ds:data
start:
    ;initialize
    mov ax,data
    mov ds,ax
    ;call subroutine
    call calc
    ;exit
    mov ax,4c00h
    int 21h
    main endp
    
    calc proc near
    mov cx,10		;count of the number of persons
    mov si,0			
startCalc:
    mov ax,0
    mov al,score[si]
    mov dl,10
    div dl
    cmp al,10
    je  AddToS10	;100
    cmp al,9
    je  AddToS9		;90-99
    cmp al,8
    je  AddToS8		;80-89
    cmp al,7
    je  AddToS7		;70-79
    cmp al,6
    je  AddToS6		;60-69
    jmp next			;else do nothing
AddToS10:
    add S10,1
    jmp next
AddToS9:
    add S9,1
    jmp next
AddToS8:
    add S8,1
    jmp next
AddToS7:
    add S7,1
    jmp next
AddToS6:
    add S6,1
    jmp next
next:
    inc si
    dec cx
    jz  exit
    jmp startCalc
exit:
    ret
    calc endp
    
code ends

    end start