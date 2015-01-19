program segment
main proc far
    assume cs:program
start:
    ;initialize
    push ds
    sub ax,ax
    push ax
    ;main part of program
    
    mov bx,0f9ebh
    mov ch,4
rotate:
    mov cl,4
    rol bx,cl
    mov al,bl
    and al,0fh
    add al,30h
    cmp al,3ah	;compare to '9'
    jl printit	;if <'9', print it directly
    add al,27h		;else, add 7h to be a letter
printit:
    mov dh,al 	;backup for al, for int 21h will change ax
    cmp ch,4
    jl printit_normal
    ;(now it seems that this is the first char)
    cmp al,3ah	
    jl printit_normal
    ;(now it seems that this is the first char and it's letter)
    mov dl,30h	;output '0'
    mov ah,2		
    int 21h
printit_normal:
	  mov al,dh 	;restore al
    mov dl,al
    mov ah,2
    int 21h
    dec ch
    jnz rotate	;if having not finished outputing 4 chars, continue
    mov dl,48h	;else
    mov ah,2
    int 21h
    ret
main endp
program ends
    end start
    
    
    