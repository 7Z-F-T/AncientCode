;功能是首先把字符‘A’~‘F’写到内存的2140~2145几个单元，读出内存中的字符，将其显示到显示器的屏幕上，转换为小写字母后再写回存储器原存储区域。


org 2020h

  MVRD R3,06h      ;给出写内存操作的次数
  MVRD R2,213Fh
  MVRD R1, 40h
writedata:
  INC R2           ;给出写内存操作的内存地址
  INC R1           ;给出写内存操作的数据内容

  STRR  [R2],R1    ;写寄存器R1的内容到由R2指定地址的内存单元中
  LDRR  R0,[R2]    ;读出内存单元的数据到R0寄存器

  DEC   R3         ;检查6次写内存操作是否完成
  JRNZ  writedata  ;未完则开始下一次写内存操作


  MVRD  R3, 06h	   ;指定被读数据的个数
  MVRD  R2, 2140h  ;指定被读、写数据内存区首地址
readdata:
  LDRR  R0,[R2]	   ;读内存中的一个字符到R0寄存器
  CALA  output     ;调用子程序，完成显示、转换并写回的功能
  DEC   R3	   ;检查输出的字符个数  
  JRZ   exit       ;完成输出则结束程序的执行过程
  INC   R2	   ;未完成，修改内存地址
  JR    readdata   ;循环执行规定的处理

exit:
  RET              ;程序结束


output:            ;输入用到的子程序
  OUT   80h	   ;输出保存在R0寄存器中的字符
  MVRD  R1,20h	
  ADD   R0,R1      ;将保存在R0中的大写字母转换为小写字母
  STRR  [R2],R0	   ;写R0中的字符到内存，地址同LOD所用的地址
check:
  IN    81h	   ;测试串行接口是否完成输出过程
  SHR   R0
  JRNC  check	   ;未完成输出过程则循环测试
RET		   ;结束子程序执行过程，返回主程序

end
