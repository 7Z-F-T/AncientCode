;用次数控制在终端屏幕上输出'0'到'9'十个数字符

org 2020h
  MVRD R2,000Ah	;送入输出字符个数
  MVRD R0,0030h	;“0”字符的ASCII码送寄存器R0
output:
  OUT 80h	;输出保存在R0低位字节的字符
  DEC R2	;输出字符个数减1
  JRZ exit	;判10个字符输出完否,已完，则转到程序结束处
  PUSH R0	;未完，保存R0的值到堆栈中
input:
  IN  81h	;查询接口状态，判字符串行输出完成否,
  SHR R0		
  JRNC input	;未完成, 则循环等待   
  POP R0	;已完成, 准备输出下一字符并从堆栈恢复R0的值
  INC R0	;得到下一个要输出的字符
  JR  output	;转去输出字符
exit:
  RET
end
