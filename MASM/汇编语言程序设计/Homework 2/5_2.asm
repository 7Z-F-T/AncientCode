code_seg segment
    main proc far
    assume cs:code_seg
start:
    mov ah,1
    int 21h
    sub al,30h	;from ACSII to binary
    cmp al,10		;see if the input number is <10
    jnb exit		;if the input number >=10 then exit
    mov cl,al		;count bell
    mov dl,07h
    mov ah,2
bell:
    int 21h
    dec cl
    jnz bell
exit:
    mov ax,4c00h
    int 21h
    main endp
code_seg ends
    end start