extrn START:far
extrn STOP:far
code segment
    main proc far
    assume cs:code
begin:
    call far ptr START;��ʼ��ʱ
    ;--------------------------------------------------------------
    ;�˴����ô�������ʱ��ĳ����ִ�д��롣
    ;(ע:������Ϊ��ʾ����ʹ����һ������ѭ�������ģ�����ִ�еĹ���)
    ;
    mov di,62000
delay1:
    mov si,63000
delay2:
    dec si
    jnz delay2
    dec di
    jnz delay1
    ;--------------------------------------------------------------
    call far ptr STOP;ֹͣ��ʱ
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code ends
    end begin