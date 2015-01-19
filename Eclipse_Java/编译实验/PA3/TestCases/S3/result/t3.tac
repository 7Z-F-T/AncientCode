VTABLE(_Main) {
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T2 = 4
    parm _T2
    _T3 =  call _Alloc
    _T4 = VTBL <_Main>
    *(_T3 + 0) = _T4
    return _T3
}

FUNCTION(main) {
memo ''
main:
    _T8 = "hello"
    _T7 = _T8
    _T9 = 4
    _T10 = 5
    parm _T9
    parm _T10
    _T11 =  call _Main.test
    _T6 = _T11
    parm _T6
    call _PrintInt
    parm _T7
    call _PrintString
}

FUNCTION(_Main.test) {
memo '_T0:4 _T1:8'
_Main.test:
    _T12 = (_T0 + _T1)
    return _T12
}

