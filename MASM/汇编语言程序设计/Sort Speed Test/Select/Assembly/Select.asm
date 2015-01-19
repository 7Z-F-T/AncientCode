data1 segment
    include data1.inc
    n1 dw 4000
data1 ends

data2 segment
    include data2.inc
    n2 dw 4000
data2 ends

data3 segment
    include data3.inc
    n3 dw 4000
data3 ends

data4 segment
    include data4.inc
    n4 dw 4000
data4 ends

data5 segment
    include data5.inc
    n5 dw 4000
data5 ends

code segment
    main proc far
    assume cs:code,ds:data1
start:
    ;Group1
    mov ax,data1
    mov ds,ax
    mov ax,offset dat1
    push ax;压数组元素首地址
    push n1;压需要排序的元素个数
    call Select;调用选择排序子程序,由大到小排序
     ;Group2
    assume cs:code,ds:data2
    mov ax,data2
    mov ds,ax
    mov ax,offset dat2
    push ax;压数组元素首地址
    push n2;压需要排序的元素个数
    call Select;调用选择排序子程序,由大到小排序
     ;Group3
    assume cs:code,ds:data3
    mov ax,data3
    mov ds,ax
    mov ax,offset dat3
    push ax;压数组元素首地址
    push n3;压需要排序的元素个数
    call Select;调用选择排序子程序,由大到小排序
     ;Group4
    assume cs:code,ds:data4
    mov ax,data4
    mov ds,ax
    mov ax,offset dat4
    push ax;压数组元素首地址
    push n4;压需要排序的元素个数
    call Select;调用选择排序子程序,由大到小排序
     ;Group5
    assume cs:code,ds:data5
    mov ax,data5
    mov ds,ax
    mov ax,offset dat5
    push ax;压数组元素首地址
    push n5;压需要排序的元素个数
    call Select;调用选择排序子程序,由大到小排序
    
    ;exit
    mov ax,4c00h
    int 21h
    main endp
    
    Select proc near
    mov bp,sp
    mov dx,[bp+2];元素个数存于dx
    mov bx,[bp+4];数组首地址存于bx
    cmp dx,1
    je exit;如果只剩一个元素就直接返回
    mov si,bx;从现在bx用来存此轮发现的最小值的元素的地址
    mov cx,dx
next:
    mov ax,[si]
    cmp ax,[bx]
    jae cont1;si所指向的元素比bx所指向的元素值大，不管它
    mov bx,si;否则要更新bx
cont1:
    add si,2
    loop next
    ;循环退出后，si指向最后一个元素的后面空间
    sub si,2;si重新指向最后一个元素
    mov ax,[si]
    mov cx,[bx]
    cmp ax,cx;看最小的是不是已经在最后面
    je cont2;如果是就无所谓了
    xchg ax,cx;否则将最小的与最后面的交换
    mov [si],ax
    mov [bx],cx;写回内存
cont2:
    dec dx;这一轮排n个数，下一轮排n-1个数
    push [bp+4];向下一次递归压数组首地址
    push dx;以及需要排序的元素个数
    call Select
exit:
    ret 4
    Select endp
code ends
    end start