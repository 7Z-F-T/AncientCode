data segment
       org 100h
       a dw 'ab'
       b dd 1
       c db 3 dup (1,2,3)
       org $+9
       x label word
       y db 'ABCD'
       z dw x+4,y
       w label byte
data ends

code   segment
main   proc   far
       assume cs:code,ds:data
start:   
       push ds
       sub ax,ax
       push ax
        
       mov ax,data
       mov ds,ax
       
       lea di,c
       mov ax,a
       lea bx,x
       mov cx,z
       lea di,z
       mov si,[di+2]
       mov al,2[si]
       mov x+1,35h
       mov ax,x+2
       mov y+2,2
       mov ax,word ptr y+2
       lea bx,w
       
        
       mov ax,4c00h
       int 21h
        

		
main   endp
code   ends
          end   start