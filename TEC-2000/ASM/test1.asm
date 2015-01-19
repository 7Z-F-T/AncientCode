;重复输出ASCII码中95个可打印字符

org 2000h
start:
  MVRD R1,7Eh     ;向寄存器传送直接数
  MVRD R0,20h     
output:  
  OUT 80h         ;通过串行接口输出R0低位字节内容到显示器屏幕
  PUSH R0         ;保存R0寄存器的内容到堆栈中
input:  
  IN 81h          ;读串行接口的状态寄存器的内容
  SHR R0          ;R0寄存器的内容右移一位，最低位的值移入标志位C
  JRNC input      ;条件转移指令，当标志位C不是1时就转移到2006地址
  POP R0          ;从堆栈中恢复R0寄存器的原内容
  CMP R0,R1       ;比较两个寄存器的内容是否相同，相同则标志位Z=1
  JRZ start       ;条件转移指令，当标志位Z为1时就转移到2000地址
  INC R0          ;把R0寄存器的内容增加1
  JR output       ;无条件转移指令，一定转移到2004地址
  RET 
end