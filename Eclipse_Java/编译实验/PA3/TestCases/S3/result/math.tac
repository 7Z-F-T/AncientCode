VTABLE(_Math) {
}

VTABLE(_Main) {
}

FUNCTION(_Math_New) {
memo ''
_Math_New:
    _T8 = 4
    parm _T8
    _T9 =  call _Alloc
    _T10 = VTBL <_Math>
    *(_T9 + 0) = _T10
    return _T9
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T11 = 4
    parm _T11
    _T12 =  call _Alloc
    _T13 = VTBL <_Main>
    *(_T12 + 0) = _T13
    return _T12
}

FUNCTION(_Math.abs) {
memo '_T0:4'
_Math.abs:
    _T15 = 0
    _T16 = (_T0 >= _T15)
    if (_T16 == 0) jump _L16
    return _T0
    jump _L17
_L16:
    _T17 = - _T0
    return _T17
_L17:
}

FUNCTION(_Math.pow) {
memo '_T1:4 _T2:8'
_Math.pow:
    _T20 = 1
    _T19 = _T20
    _T21 = 0
    _T18 = _T21
_L19:
    _T22 = (_T18 < _T2)
    if (_T22 == 0) jump _L18
    _T23 = (_T19 * _T1)
    _T19 = _T23
    _T24 = 1
    _T25 = (_T18 + _T24)
    _T18 = _T25
    jump _L19
_L18:
    return _T19
}

FUNCTION(_Math.log) {
memo '_T3:4'
_Math.log:
    _T26 = 1
    _T27 = (_T3 < _T26)
    if (_T27 == 0) jump _L20
    _T28 = 1
    _T29 = - _T28
    return _T29
_L20:
    _T31 = 0
    _T30 = _T31
_L22:
    _T32 = 1
    _T33 = (_T3 > _T32)
    if (_T33 == 0) jump _L21
    _T34 = 1
    _T35 = (_T30 + _T34)
    _T30 = _T35
    _T36 = 2
    _T37 = (_T3 / _T36)
    _T3 = _T37
    jump _L22
_L21:
    return _T30
}

FUNCTION(_Math.max) {
memo '_T4:4 _T5:8'
_Math.max:
    _T38 = (_T4 > _T5)
    if (_T38 == 0) jump _L23
    return _T4
    jump _L24
_L23:
    return _T5
_L24:
}

FUNCTION(_Math.min) {
memo '_T6:4 _T7:8'
_Math.min:
    _T39 = (_T6 < _T7)
    if (_T39 == 0) jump _L25
    return _T6
    jump _L26
_L25:
    return _T7
_L26:
}

FUNCTION(main) {
memo ''
main:
    _T40 = 1
    _T41 = - _T40
    parm _T41
    _T42 =  call _Math.abs
    _T43 = "\n"
    parm _T42
    call _PrintInt
    parm _T43
    call _PrintString
    _T44 = 2
    _T45 = 3
    parm _T44
    parm _T45
    _T46 =  call _Math.pow
    _T47 = "\n"
    parm _T46
    call _PrintInt
    parm _T47
    call _PrintString
    _T48 = 16
    parm _T48
    _T49 =  call _Math.log
    _T50 = "\n"
    parm _T49
    call _PrintInt
    parm _T50
    call _PrintString
    _T51 = 1
    _T52 = 2
    parm _T51
    parm _T52
    _T53 =  call _Math.max
    _T54 = "\n"
    parm _T53
    call _PrintInt
    parm _T54
    call _PrintString
    _T55 = 1
    _T56 = 2
    parm _T55
    parm _T56
    _T57 =  call _Math.min
    _T58 = "\n"
    parm _T57
    call _PrintInt
    parm _T58
    call _PrintString
}

