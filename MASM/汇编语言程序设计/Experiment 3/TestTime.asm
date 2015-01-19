extrn START:far
extrn STOP:far
code segment
    main proc far
    assume cs:code
begin:
    call far ptr START;开始计时
    ;--------------------------------------------------------------
    ;此处放置待测运行时间的程序的执行代码。
    ;(注:本次作为演示，仅使用了一个二重循环语句来模拟程序执行的过程)
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
    call far ptr STOP;停止计时
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code ends
    end begin