VTABLE(_Main) {
    _Main.tester;
    _Main.start;
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T3 = 12
    parm _T3
    _T4 =  call _Alloc
    _T5 = 0
    *(_T4 + 4) = _T5
    *(_T4 + 8) = _T5
    _T6 = VTBL <_Main>
    *(_T4 + 0) = _T6
    return _T4
}

FUNCTION(_Main.tester) {
memo '_T0:4 _T1:8'
_Main.tester:
    _T8 = *(_T0 + 8)
    _T9 = 1
    _T10 = 0
    _T11 = (_T9 < _T10)
    if (_T11 == 0) jump _L12
    _T12 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T12
    call _PrintString
    call _Halt
_L12:
    _T13 = 4
    _T14 = (_T13 * _T9)
    _T15 = (_T13 + _T14)
    parm _T15
    _T16 =  call _Alloc
    *(_T16 + 0) = _T9
    _T17 = 0
    _T16 = (_T16 + _T15)
_L13:
    _T15 = (_T15 - _T13)
    if (_T15 == 0) jump _L14
    _T16 = (_T16 - _T13)
    *(_T16 + 0) = _T17
    jump _L13
_L14:
    *(_T0 + 8) = _T16
    _T18 = 0
    _T19 = (_T1 < _T18)
    if (_T19 == 0) jump _L15
    _T20 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T20
    call _PrintString
    call _Halt
_L15:
    _T21 = 4
    _T22 = (_T21 * _T1)
    _T23 = (_T21 + _T22)
    parm _T23
    _T24 =  call _Alloc
    *(_T24 + 0) = _T1
    _T25 = 0
    _T24 = (_T24 + _T23)
_L16:
    _T23 = (_T23 - _T21)
    if (_T23 == 0) jump _L17
    _T24 = (_T24 - _T21)
    *(_T24 + 0) = _T25
    jump _L16
_L17:
    return _T24
}

FUNCTION(_Main.start) {
memo '_T2:4'
_Main.start:
    _T29 = 1
    _T26 = _T29
_L19:
    _T30 = 5
    _T31 = (_T26 < _T30)
    if (_T31 == 0) jump _L18
    _T32 = 2
    _T33 = (_T26 % _T32)
    _T34 = 0
    _T35 = (_T33 == _T34)
    if (_T35 == 0) jump _L20
    _T36 = *(_T2 + 0)
    _T37 = *(_T36 + 0)
    parm _T2
    parm _T26
    _T38 =  call _T37
    _T28 = _T38
    jump _L18
_L20:
    _T39 = "Loop "
    _T40 = "\n"
    parm _T39
    call _PrintString
    parm _T26
    call _PrintInt
    parm _T40
    call _PrintString
    _T41 = 1
    _T42 = (_T26 + _T41)
    _T26 = _T42
    jump _L19
_L18:
    _T43 = 0
    _T44 = *(_T28 - 4)
    _T45 = (_T43 < _T44)
    if (_T45 == 0) jump _L21
    _T46 = 0
    _T47 = (_T43 < _T46)
    if (_T47 == 0) jump _L22
_L21:
    _T48 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T48
    call _PrintString
    call _Halt
_L22:
    _T49 = 0
    _T50 = 4
    _T51 = (_T43 * _T50)
    _T52 = (_T28 + _T51)
    *(_T52 + 0) = _T49
    _T53 = 0
    _T54 = *(_T28 - 4)
    _T55 = (_T53 < _T54)
    if (_T55 == 0) jump _L23
    _T56 = 0
    _T57 = (_T53 < _T56)
    if (_T57 == 0) jump _L24
_L23:
    _T58 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T58
    call _PrintString
    call _Halt
_L24:
    _T59 = 4
    _T60 = (_T53 * _T59)
    _T61 = (_T28 + _T60)
    _T62 = *(_T61 + 0)
    _T63 = *(_T28 - 4)
    _T64 = (_T62 < _T63)
    if (_T64 == 0) jump _L25
    _T65 = 0
    _T66 = (_T62 < _T65)
    if (_T66 == 0) jump _L26
_L25:
    _T67 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T67
    call _PrintString
    call _Halt
_L26:
    _T68 = 4
    _T69 = (_T62 * _T68)
    _T70 = (_T28 + _T69)
    _T71 = *(_T70 + 0)
    _T72 = "\n"
    _T73 = *(_T28 - 4)
    _T74 = "\n"
    parm _T71
    call _PrintInt
    parm _T72
    call _PrintString
    parm _T73
    call _PrintInt
    parm _T74
    call _PrintString
}

FUNCTION(main) {
memo ''
main:
    _T75 = 12
    parm _T75
    _T79 =  call _Alloc
    _T80 = VTBL <_Main>
    *(_T79 + 0) = _T80
    _T81 = *(_T79 + 0)
    _T82 = *(_T81 + 4)
    parm _T79
    call _T82
}

