VTABLE(_Stack) {
    _Stack.Init;
    _Stack.Push;
    _Stack.Pop;
    _Stack.NumElems;
}

FUNCTION(_Stack_New) {
memo ''
_Stack_New:
    _T5 = 12
    parm _T5
    _T6 =  call _Alloc
    _T7 = 0
    *(_T6 + 4) = _T7
    *(_T6 + 8) = _T7
    _T8 = VTBL <_Stack>
    *(_T6 + 0) = _T8
    return _T6
}

FUNCTION(_Stack.Init) {
memo '_T0:4'
_Stack.Init:
    _T10 = *(_T0 + 8)
    _T11 = 100
    _T12 = 0
    _T13 = (_T11 < _T12)
    if (_T13 == 0) jump _L14
    _T14 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T14
    call _PrintString
    call _Halt
_L14:
    _T15 = 4
    _T16 = (_T15 * _T11)
    _T17 = (_T15 + _T16)
    parm _T17
    _T18 =  call _Alloc
    *(_T18 + 0) = _T11
    _T19 = 0
    _T18 = (_T18 + _T17)
_L15:
    _T17 = (_T17 - _T15)
    if (_T17 == 0) jump _L16
    _T18 = (_T18 - _T15)
    *(_T18 + 0) = _T19
    jump _L15
_L16:
    *(_T0 + 8) = _T18
    _T20 = *(_T0 + 4)
    _T21 = 0
    *(_T0 + 4) = _T21
    _T22 = *(_T0 + 0)
    _T23 = *(_T22 + 4)
    _T24 = 3
    parm _T0
    parm _T24
    call _T23
}

FUNCTION(_Stack.Push) {
memo '_T1:4 _T2:8'
_Stack.Push:
    _T25 = *(_T1 + 8)
    _T26 = *(_T1 + 4)
    _T27 = *(_T25 - 4)
    _T28 = (_T26 < _T27)
    if (_T28 == 0) jump _L17
    _T29 = 0
    _T30 = (_T26 < _T29)
    if (_T30 == 0) jump _L18
_L17:
    _T31 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T31
    call _PrintString
    call _Halt
_L18:
    _T32 = 4
    _T33 = (_T26 * _T32)
    _T34 = (_T25 + _T33)
    *(_T34 + 0) = _T2
    _T35 = *(_T1 + 4)
    _T36 = *(_T1 + 4)
    _T37 = 1
    _T38 = (_T36 + _T37)
    *(_T1 + 4) = _T38
}

FUNCTION(_Stack.Pop) {
memo '_T3:4'
_Stack.Pop:
    _T40 = *(_T3 + 8)
    _T41 = *(_T3 + 4)
    _T42 = 1
    _T43 = (_T41 - _T42)
    _T44 = *(_T40 - 4)
    _T45 = (_T43 < _T44)
    if (_T45 == 0) jump _L19
    _T46 = 0
    _T47 = (_T43 < _T46)
    if (_T47 == 0) jump _L20
_L19:
    _T48 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T48
    call _PrintString
    call _Halt
_L20:
    _T49 = 4
    _T50 = (_T43 * _T49)
    _T51 = (_T40 + _T50)
    _T52 = *(_T51 + 0)
    _T39 = _T52
    _T53 = *(_T3 + 4)
    _T54 = *(_T3 + 4)
    _T55 = 1
    _T56 = (_T54 - _T55)
    *(_T3 + 4) = _T56
    return _T39
}

FUNCTION(_Stack.NumElems) {
memo '_T4:4'
_Stack.NumElems:
    _T57 = *(_T4 + 4)
    return _T57
}

FUNCTION(main) {
memo ''
main:
    _T59 = 12
    parm _T59
    _T63 =  call _Alloc
    _T64 = VTBL <_Stack>
    *(_T63 + 0) = _T64
    _T58 = _T63
    _T65 = *(_T58 + 0)
    _T66 = *(_T65 + 0)
    parm _T58
    call _T66
    _T67 = *(_T58 + 0)
    _T68 = *(_T67 + 4)
    _T69 = 3
    parm _T58
    parm _T69
    call _T68
    _T70 = *(_T58 + 0)
    _T71 = *(_T70 + 4)
    _T72 = 7
    parm _T58
    parm _T72
    call _T71
    _T73 = *(_T58 + 0)
    _T74 = *(_T73 + 4)
    _T75 = 4
    parm _T58
    parm _T75
    call _T74
    _T76 = *(_T58 + 0)
    _T77 = *(_T76 + 12)
    parm _T58
    _T78 =  call _T77
    _T79 = " "
    _T80 = *(_T58 + 0)
    _T81 = *(_T80 + 8)
    parm _T58
    _T82 =  call _T81
    _T83 = " "
    _T84 = *(_T58 + 0)
    _T85 = *(_T84 + 8)
    parm _T58
    _T86 =  call _T85
    _T87 = " "
    _T88 = *(_T58 + 0)
    _T89 = *(_T88 + 8)
    parm _T58
    _T90 =  call _T89
    _T91 = " "
    _T92 = *(_T58 + 0)
    _T93 = *(_T92 + 12)
    parm _T58
    _T94 =  call _T93
    parm _T78
    call _PrintInt
    parm _T79
    call _PrintString
    parm _T82
    call _PrintInt
    parm _T83
    call _PrintString
    parm _T86
    call _PrintInt
    parm _T87
    call _PrintString
    parm _T90
    call _PrintInt
    parm _T91
    call _PrintString
    parm _T94
    call _PrintInt
}

