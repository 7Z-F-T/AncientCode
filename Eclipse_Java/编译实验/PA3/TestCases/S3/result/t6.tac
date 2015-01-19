VTABLE(_Main) {
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T3 = 4
    parm _T3
    _T4 =  call _Alloc
    _T5 = VTBL <_Main>
    *(_T4 + 0) = _T5
    return _T4
}

FUNCTION(_Main.Binky) {
memo '_T0:4 _T1:8 _T2:12'
_Main.Binky:
    _T7 = 0
    _T8 = *(_T2 - 4)
    _T9 = (_T7 < _T8)
    if (_T9 == 0) jump _L11
    _T10 = 0
    _T11 = (_T7 < _T10)
    if (_T11 == 0) jump _L12
_L11:
    _T12 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T12
    call _PrintString
    call _Halt
_L12:
    _T13 = 4
    _T14 = (_T7 * _T13)
    _T15 = (_T2 + _T14)
    _T16 = *(_T15 + 0)
    _T17 = *(_T1 - 4)
    _T18 = (_T16 < _T17)
    if (_T18 == 0) jump _L13
    _T19 = 0
    _T20 = (_T16 < _T19)
    if (_T20 == 0) jump _L14
_L13:
    _T21 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T21
    call _PrintString
    call _Halt
_L14:
    _T22 = 4
    _T23 = (_T16 * _T22)
    _T24 = (_T1 + _T23)
    _T25 = *(_T24 + 0)
    return _T25
}

FUNCTION(main) {
memo ''
main:
    _T28 = 5
    _T29 = 0
    _T30 = (_T28 < _T29)
    if (_T30 == 0) jump _L15
    _T31 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T31
    call _PrintString
    call _Halt
_L15:
    _T32 = 4
    _T33 = (_T32 * _T28)
    _T34 = (_T32 + _T33)
    parm _T34
    _T35 =  call _Alloc
    *(_T35 + 0) = _T28
    _T36 = 0
    _T35 = (_T35 + _T34)
_L16:
    _T34 = (_T34 - _T32)
    if (_T34 == 0) jump _L17
    _T35 = (_T35 - _T32)
    *(_T35 + 0) = _T36
    jump _L16
_L17:
    _T27 = _T35
    _T37 = 0
    _T38 = *(_T27 - 4)
    _T39 = (_T37 < _T38)
    if (_T39 == 0) jump _L18
    _T40 = 0
    _T41 = (_T37 < _T40)
    if (_T41 == 0) jump _L19
_L18:
    _T42 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T42
    call _PrintString
    call _Halt
_L19:
    _T43 = 12
    _T44 = 0
    _T45 = (_T43 < _T44)
    if (_T45 == 0) jump _L20
    _T46 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T46
    call _PrintString
    call _Halt
_L20:
    _T47 = 4
    _T48 = (_T47 * _T43)
    _T49 = (_T47 + _T48)
    parm _T49
    _T50 =  call _Alloc
    *(_T50 + 0) = _T43
    _T51 = 0
    _T50 = (_T50 + _T49)
_L21:
    _T49 = (_T49 - _T47)
    if (_T49 == 0) jump _L22
    _T50 = (_T50 - _T47)
    *(_T50 + 0) = _T51
    jump _L21
_L22:
    _T52 = 4
    _T53 = (_T37 * _T52)
    _T54 = (_T27 + _T53)
    *(_T54 + 0) = _T50
    _T55 = 10
    _T56 = 0
    _T57 = (_T55 < _T56)
    if (_T57 == 0) jump _L23
    _T58 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T58
    call _PrintString
    call _Halt
_L23:
    _T59 = 4
    _T60 = (_T59 * _T55)
    _T61 = (_T59 + _T60)
    parm _T61
    _T62 =  call _Alloc
    *(_T62 + 0) = _T55
    _T63 = 0
    _T62 = (_T62 + _T61)
_L24:
    _T61 = (_T61 - _T59)
    if (_T61 == 0) jump _L25
    _T62 = (_T62 - _T59)
    *(_T62 + 0) = _T63
    jump _L24
_L25:
    _T26 = _T62
    _T64 = 0
    _T65 = *(_T26 - 4)
    _T66 = (_T64 < _T65)
    if (_T66 == 0) jump _L26
    _T67 = 0
    _T68 = (_T64 < _T67)
    if (_T68 == 0) jump _L27
_L26:
    _T69 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T69
    call _PrintString
    call _Halt
_L27:
    _T70 = 4
    _T71 = 5
    _T72 = 3
    _T73 = (_T71 * _T72)
    _T74 = 4
    _T75 = (_T73 / _T74)
    _T76 = 2
    _T77 = (_T75 % _T76)
    _T78 = (_T70 + _T77)
    _T79 = 4
    _T80 = (_T64 * _T79)
    _T81 = (_T26 + _T80)
    *(_T81 + 0) = _T78
    _T82 = 0
    _T83 = *(_T27 - 4)
    _T84 = (_T82 < _T83)
    if (_T84 == 0) jump _L28
    _T85 = 0
    _T86 = (_T82 < _T85)
    if (_T86 == 0) jump _L29
_L28:
    _T87 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T87
    call _PrintString
    call _Halt
_L29:
    _T88 = 4
    _T89 = (_T82 * _T88)
    _T90 = (_T27 + _T89)
    _T91 = *(_T90 + 0)
    _T92 = 0
    _T93 = *(_T26 - 4)
    _T94 = (_T92 < _T93)
    if (_T94 == 0) jump _L30
    _T95 = 0
    _T96 = (_T92 < _T95)
    if (_T96 == 0) jump _L31
_L30:
    _T97 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T97
    call _PrintString
    call _Halt
_L31:
    _T98 = 4
    _T99 = (_T92 * _T98)
    _T100 = (_T26 + _T99)
    _T101 = *(_T100 + 0)
    _T102 = *(_T91 - 4)
    _T103 = (_T101 < _T102)
    if (_T103 == 0) jump _L32
    _T104 = 0
    _T105 = (_T101 < _T104)
    if (_T105 == 0) jump _L33
_L32:
    _T106 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T106
    call _PrintString
    call _Halt
_L33:
    _T107 = 55
    _T108 = 4
    _T109 = (_T101 * _T108)
    _T110 = (_T91 + _T109)
    *(_T110 + 0) = _T107
    _T111 = 0
    _T112 = *(_T26 - 4)
    _T113 = (_T111 < _T112)
    if (_T113 == 0) jump _L34
    _T114 = 0
    _T115 = (_T111 < _T114)
    if (_T115 == 0) jump _L35
_L34:
    _T116 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T116
    call _PrintString
    call _Halt
_L35:
    _T117 = 4
    _T118 = (_T111 * _T117)
    _T119 = (_T26 + _T118)
    _T120 = *(_T119 + 0)
    _T121 = " "
    _T122 = 2
    _T123 = 100
    _T124 = 0
    _T125 = *(_T27 - 4)
    _T126 = (_T124 < _T125)
    if (_T126 == 0) jump _L36
    _T127 = 0
    _T128 = (_T124 < _T127)
    if (_T128 == 0) jump _L37
_L36:
    _T129 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T129
    call _PrintString
    call _Halt
_L37:
    _T130 = 4
    _T131 = (_T124 * _T130)
    _T132 = (_T27 + _T131)
    _T133 = *(_T132 + 0)
    parm _T123
    parm _T133
    parm _T26
    _T134 =  call _Main.Binky
    _T135 = (_T122 * _T134)
    parm _T120
    call _PrintInt
    parm _T121
    call _PrintString
    parm _T135
    call _PrintInt
}

