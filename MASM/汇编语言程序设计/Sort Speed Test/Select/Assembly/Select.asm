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
    push ax;ѹ����Ԫ���׵�ַ
    push n1;ѹ��Ҫ�����Ԫ�ظ���
    call Select;����ѡ�������ӳ���,�ɴ�С����
     ;Group2
    assume cs:code,ds:data2
    mov ax,data2
    mov ds,ax
    mov ax,offset dat2
    push ax;ѹ����Ԫ���׵�ַ
    push n2;ѹ��Ҫ�����Ԫ�ظ���
    call Select;����ѡ�������ӳ���,�ɴ�С����
     ;Group3
    assume cs:code,ds:data3
    mov ax,data3
    mov ds,ax
    mov ax,offset dat3
    push ax;ѹ����Ԫ���׵�ַ
    push n3;ѹ��Ҫ�����Ԫ�ظ���
    call Select;����ѡ�������ӳ���,�ɴ�С����
     ;Group4
    assume cs:code,ds:data4
    mov ax,data4
    mov ds,ax
    mov ax,offset dat4
    push ax;ѹ����Ԫ���׵�ַ
    push n4;ѹ��Ҫ�����Ԫ�ظ���
    call Select;����ѡ�������ӳ���,�ɴ�С����
     ;Group5
    assume cs:code,ds:data5
    mov ax,data5
    mov ds,ax
    mov ax,offset dat5
    push ax;ѹ����Ԫ���׵�ַ
    push n5;ѹ��Ҫ�����Ԫ�ظ���
    call Select;����ѡ�������ӳ���,�ɴ�С����
    
    ;exit
    mov ax,4c00h
    int 21h
    main endp
    
    Select proc near
    mov bp,sp
    mov dx,[bp+2];Ԫ�ظ�������dx
    mov bx,[bp+4];�����׵�ַ����bx
    cmp dx,1
    je exit;���ֻʣһ��Ԫ�ؾ�ֱ�ӷ���
    mov si,bx;������bx��������ַ��ֵ���Сֵ��Ԫ�صĵ�ַ
    mov cx,dx
next:
    mov ax,[si]
    cmp ax,[bx]
    jae cont1;si��ָ���Ԫ�ر�bx��ָ���Ԫ��ֵ�󣬲�����
    mov bx,si;����Ҫ����bx
cont1:
    add si,2
    loop next
    ;ѭ���˳���siָ�����һ��Ԫ�صĺ���ռ�
    sub si,2;si����ָ�����һ��Ԫ��
    mov ax,[si]
    mov cx,[bx]
    cmp ax,cx;����С���ǲ����Ѿ��������
    je cont2;����Ǿ�����ν��
    xchg ax,cx;������С���������Ľ���
    mov [si],ax
    mov [bx],cx;д���ڴ�
cont2:
    dec dx;��һ����n��������һ����n-1����
    push [bp+4];����һ�εݹ�ѹ�����׵�ַ
    push dx;�Լ���Ҫ�����Ԫ�ظ���
    call Select
exit:
    ret 4
    Select endp
code ends
    end start