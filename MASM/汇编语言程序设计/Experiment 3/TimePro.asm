public START,STOP
;*********************************************************
data_time segment
    hour dw 0
    minute dw 0
    second dw 0
    millisecond dw 0
    save_seg dw 0
    save_offset dw 0
data_time ends
;*********************************************************
code_time segment 
	  KEYCLOCK proc far
	  assume cs:code_time,ds:data_time
begin: 	  
    mov ax,data_time
    mov ds,ax
    
	  ;��ʾʱ�ӣ�׼��״̬��
    mov bx,hour
    call BIN_DEC
    call COLON
    mov bx,minute
    call BIN_DEC
    call COLON
    mov bx,second
    call BIN_DEC
    call DOT
    mov bx,millisecond
    call BIN_DEC2
    call RETURN
    
    ;�ȴ��������룬��������ʼ
    in al,60h
    or al,80h
    out 60h,al
key_start:
    in al,60h
    test al,80h
    jnz key_start
 
    or al,80h
    out 60h,al 
    
    call far ptr START;��ʼ��ʱ
    
    ;�ȴ��������룬����������
key_end:
    in al,60h
    test al,80h
    jnz key_end
    
    call far ptr STOP;������ʱ
    
    or al,80h
    out 60h,al
    
    ;�˳�
    mov ax,4c00h
    int 21h
    KEYCLOCK endp
;---------------------------------------------------------      
    START proc far;������ʱ
    push ds
    push ax
    push bx
    push cx
    push dx
    assume cs:code_time,ds:data_time
    mov ax,data_time
    mov ds,ax
    
    ;���ݾɵ��ж�����
    mov al,1ch
    mov ah,35h
    int 21h
    mov save_seg,es
    mov save_offset,bx
    
    
    
    ;д���µ��ж�����
    push ds
    mov dx,offset TIMING
    mov ax,seg TIMING
    mov ds,ax
    mov al,1ch
    mov ah,25h
    int 21h
    pop ds
    
    ;��������λ
    in al,21h
    and al,11111110b
    out 21h,al
    
    ;��������µ��ж�����
    pop dx
    pop cx
    pop bx
    pop ax
    pop ds
    ret
    START endp
    
;---------------------------------------------------------     
    STOP proc far;ֹͣ��ʱ
    push ds
    push ax
    push bx
    push cx
    push dx
    assume cs:code_time,ds:data_time
    mov ax,data_time
    mov ds,ax
    
    ;�ָ�ԭʼ���ж�����
    push ds
    mov dx,save_offset
    mov ax,save_seg
    mov ds,ax
    mov al,1ch
    mov ah,25h
    int 21h
    pop ds
    
    ;��ɻָ��ж�����
    pop dx
    pop cx
    pop bx
    pop ax
    pop ds
    ret
    STOP endp
;--------------------------------------------------------- 
    TIMING proc near;ʱ���ж�(1ch)����
    push ds
    push ax
    push bx
    push cx
    push dx
    
    assume cs:code_time,ds:data_time
    mov ax,data_time
    mov ds,ax
     ;��ʾʱ��
    mov bx,hour
    call BIN_DEC
    call COLON
    mov bx,minute
    call BIN_DEC
    call COLON
    mov bx,second
    call BIN_DEC
    call DOT
    mov bx,millisecond
    call BIN_DEC2
    call RETURN
    ;�����ӣ�������Ҫ�ж��Ƿ���Ҫ��λ
    add millisecond,55
    cmp millisecond,1000
    jb exit_TIMING
    sub millisecond,1000
    inc second
    cmp second,60
    jne exit_TIMING
    mov second,0
    inc minute
    cmp minute,60
    jne exit_TIMING
    mov minute,0
    inc hour
    cmp hour,24
    jne exit_TIMING
    mov hour,0
    
exit_TIMING:
    pop dx
    pop cx
    pop bx
    pop ax
    pop ds
    iret
    TIMING endp
;---------------------------------------------------------
    BIN_DEC proc near;��bx�е�����ʮ�������
    ;Ҫת����������������bx��
    mov  cx,10d
    call DEC_DIV
    mov  dl,bl
    add  dl,30h
    mov  ah,2
    int  21h
    ret
    BIN_DEC endp
;---------------------------------------------------------
    BIN_DEC2 proc near;��bx�е�����ʮ�������
    ;Ҫת����������������bx��
    mov  cx,100d
    call DEC_DIV
    mov  cx,10d
    call DEC_DIV
    mov  dl,bl
    add  dl,30h
    mov  ah,2
    int  21h
    ret
    BIN_DEC2 endp
;---------------------------------------------------------
    DEC_DIV proc near;��������
    ;Ҫת����������������bx��
    mov ax,bx
    mov dx,0
    div cx 			
    mov bx,dx 	
    mov dl,al
    add dl,30h
    mov ah,2
    int 21h
    ret
    DEC_DIV endp
;---------------------------------------------------------
    COLON proc near;���ð��
    mov dl,3ah
    mov ah,2
    int 21h
    ret
    COLON endp
;---------------------------------------------------------
    RETURN proc near;�������
    mov dl,0dh
    mov ah,2
    int 21h
    ret
    RETURN endp
;---------------------------------------------------------
    DOT proc near;�����
    mov dl,2eh
    mov ah,2
    int 21h
    ret
    DOT endp
code_time ends
;*********************************************************
    end begin