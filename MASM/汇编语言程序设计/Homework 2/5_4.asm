data_seg segment
    A_row1 db 3,6,2,4;demo array
    A_row2 db 7,1,4,10;demo array
    A_row3 db 8,2,9,17;demo array
    A_row4 db 9,11,12,7;demo array
    B_col1 db 2,21,41,13;demo array
    C_col1 dw 4 dup(?)


code_seg segment
    main proc far
    assume cs:code_seg,ds:data_seg
start:
    mov ax,data_seg
    mov ds,ax
    mov dx,0;dx is used to store the sum of mutiplication
    mov bx,0;bx is used to locate line number in A and C
    mov si,0;si is used to locate column number in A and line number in B
    mov cx,4
outer:
    push cx
    mov cl,2
    shl bx,cl;4 times of bx equals to the start location of certain line in A
    mov cx,4
inner:
    
    mov al,A_row1[bx][si]
    mul B_col1[si]
    add dx,ax
    inc si
    loop inner
    
    shr bx,1;2 times of bx equals to the start location of certain line in C
    mov C_col1[bx],dx
    shr bx,1;restore bx to line number
    inc bx;next line
    mov dx,0
    mov si,0
    pop cx
    loop outer
        
exit:
    mov ax,4c00h
    int 21h
    main endp
code_seg ends
    end start