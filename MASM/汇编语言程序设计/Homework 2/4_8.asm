data_seg segment
    SOURCE db 23h,24h,25h,26h
data_seg ends

extra_seg segment
    DEST db 80 dup (?)
    DEST_END label byte
extra_seg ends

code_seg segment
    main proc far
    assume cs:code_seg,ds:data_seg,es:extra_seg
start:
    mov ax,data_seg
    mov ds,ax
    mov ax,extra_seg
    mov es,ax
    ;start copying
    cld
    mov si,0
    mov di,0
    mov cx,4
    lea si,SOURCE
    lea di,DEST
    rep movsb
again: 
    mov cx,4
    lea si,SOURCE
    rep movsb
    cmp di,offset DEST+80
    jne again		;if not finish, do it again
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code_seg ends
    end start