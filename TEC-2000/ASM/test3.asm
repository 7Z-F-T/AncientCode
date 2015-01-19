;该程序的功能是首先把字符‘A’~‘F’写到内存的2040~2045几个单元，之后再读出来并显示到屏幕上。

org 2020h

  MVRD R3,06h      ;给出写内存操作的次数
  MVRD R2,203Fh
  MVRD R1, 40h
address:
  INC R2           ;给出写内存操作的内存地址
  INC R1           ;给出写内存操作的数据内容

  STRR  [R2],R1    ;写寄存器R1的内容到由R2指定地址的内存单元中
  LDRR  R0,[R2]    ;读出内存单元的数据到R0寄存器
output:
  OUT 80h
  IN 81h
  SHR R0
  JRNC  output

  DEC   R3         ;检查6次写内存操作是否完成
  JRNZ  address    ;未完则开始下一次写内存操作
  RET              ;程序结束

end