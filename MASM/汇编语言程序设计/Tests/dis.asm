data   segment
       string     db    'welcome to beijing!', 0dh , 0ah , '$'
       count      dw   21
data   ends 

code   segment
assume cs:code,ds:data,es:data
start:
    push ds
    sub ax,ax
    push ax
    
    mov ax,data
    mov ds,ax
    mov es,ax
    
    mov cx,count
    mov bx,offset string
next:
    mov dl,[bx]
    mov ah,2
    int 21h
    
    inc bx
    loop next
    
    ret
code ends
end
