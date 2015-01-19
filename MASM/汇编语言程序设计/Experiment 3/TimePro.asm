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
    
	  ;显示时钟（准备状态）
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
    
    ;等待键盘输入，摁键即开始
    in al,60h
    or al,80h
    out 60h,al
key_start:
    in al,60h
    test al,80h
    jnz key_start
 
    or al,80h
    out 60h,al 
    
    call far ptr START;开始计时
    
    ;等待键盘输入，摁键即结束
key_end:
    in al,60h
    test al,80h
    jnz key_end
    
    call far ptr STOP;结束计时
    
    or al,80h
    out 60h,al
    
    ;退出
    mov ax,4c00h
    int 21h
    KEYCLOCK endp
;---------------------------------------------------------      
    START proc far;启动计时
    push ds
    push ax
    push bx
    push cx
    push dx
    assume cs:code_time,ds:data_time
    mov ax,data_time
    mov ds,ax
    
    ;备份旧的中断向量
    mov al,1ch
    mov ah,35h
    int 21h
    mov save_seg,es
    mov save_offset,bx
    
    
    
    ;写入新的中断向量
    push ds
    mov dx,offset TIMING
    mov ax,seg TIMING
    mov ds,ax
    mov al,1ch
    mov ah,25h
    int 21h
    pop ds
    
    ;设置屏蔽位
    in al,21h
    and al,11111110b
    out 21h,al
    
    ;完成设置新的中断向量
    pop dx
    pop cx
    pop bx
    pop ax
    pop ds
    ret
    START endp
    
;---------------------------------------------------------     
    STOP proc far;停止计时
    push ds
    push ax
    push bx
    push cx
    push dx
    assume cs:code_time,ds:data_time
    mov ax,data_time
    mov ds,ax
    
    ;恢复原始的中断向量
    push ds
    mov dx,save_offset
    mov ax,save_seg
    mov ds,ax
    mov al,1ch
    mov ah,25h
    int 21h
    pop ds
    
    ;完成恢复中断向量
    pop dx
    pop cx
    pop bx
    pop ax
    pop ds
    ret
    STOP endp
;--------------------------------------------------------- 
    TIMING proc near;时钟中断(1ch)程序
    push ds
    push ax
    push bx
    push cx
    push dx
    
    assume cs:code_time,ds:data_time
    mov ax,data_time
    mov ds,ax
     ;显示时钟
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
    ;计数加，根据需要判断是否需要进位
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
    BIN_DEC proc near;将bx中的数以十进制输出
    ;要转换输出的数必须放在bx中
    mov  cx,10d
    call DEC_DIV
    mov  dl,bl
    add  dl,30h
    mov  ah,2
    int  21h
    ret
    BIN_DEC endp
;---------------------------------------------------------
    BIN_DEC2 proc near;将bx中的数以十进制输出
    ;要转换输出的数必须放在bx中
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
    DEC_DIV proc near;除法运算
    ;要转换输出的数必须放在bx中
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
    COLON proc near;输出冒号
    mov dl,3ah
    mov ah,2
    int 21h
    ret
    COLON endp
;---------------------------------------------------------
    RETURN proc near;回行左端
    mov dl,0dh
    mov ah,2
    int 21h
    ret
    RETURN endp
;---------------------------------------------------------
    DOT proc near;输出点
    mov dl,2eh
    mov ah,2
    int 21h
    ret
    DOT endp
code_time ends
;*********************************************************
    end begin