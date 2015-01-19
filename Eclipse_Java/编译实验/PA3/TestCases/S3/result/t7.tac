VTABLE(_Computer) {
    _Computer.Crash;
}

VTABLE(_Mac) {
    _Mac.Crash;
}

VTABLE(_Main) {
}

FUNCTION(_Computer_New) {
memo ''
_Computer_New:
    _T2 = 8
    parm _T2
    _T3 =  call _Alloc
    _T4 = 0
    *(_T3 + 4) = _T4
    _T5 = VTBL <_Computer>
    *(_T3 + 0) = _T5
    return _T3
}

FUNCTION(_Mac_New) {
memo ''
_Mac_New:
    _T8 = 12
    parm _T8
    _T9 =  call _Alloc
    _T10 = 0
    *(_T9 + 4) = _T10
    *(_T9 + 8) = _T10
    _T11 = VTBL <_Mac>
    *(_T9 + 0) = _T11
    return _T9
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T12 = 4
    parm _T12
    _T13 =  call _Alloc
    _T14 = VTBL <_Main>
    *(_T13 + 0) = _T14
    return _T13
}

FUNCTION(_Computer.Crash) {
memo '_T0:4 _T1:8'
_Computer.Crash:
    _T17 = 0
    _T16 = _T17
_L15:
    _T18 = (_T16 < _T1)
    if (_T18 == 0) jump _L14
    _T19 = "sad\n"
    parm _T19
    call _PrintString
    _T20 = 1
    _T21 = (_T16 + _T20)
    _T16 = _T21
    jump _L15
_L14:
}

FUNCTION(_Mac.Crash) {
memo '_T6:4 _T7:8'
_Mac.Crash:
    _T22 = "ack!"
    parm _T22
    call _PrintString
}

FUNCTION(main) {
memo ''
main:
    _T24 = 12
    parm _T24
    _T28 =  call _Alloc
    _T29 = VTBL <_Mac>
    *(_T28 + 0) = _T29
    _T23 = _T28
    _T30 = *(_T23 + 0)
    _T31 = *(_T30 + 0)
    _T32 = 2
    parm _T23
    parm _T32
    call _T31
}

