VTABLE(_Main) {
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T0 = 4
    parm _T0
    _T1 =  call _Alloc
    _T2 = VTBL <_Main>
    *(_T1 + 0) = _T2
    return _T1
}

FUNCTION(main) {
memo ''
main:
    _T7 = 1
    _T6 = _T7
    _T8 = "wow!"
    _T5 = _T8
    _T9 = 3
    _T4 = _T9
    if (_T6 == 0) jump _L10
    _T10 = 5
    _T11 = (_T4 * _T10)
    _T4 = _T11
_L10:
    _T12 = " "
    parm _T6
    call _PrintBool
    parm _T12
    call _PrintString
    parm _T4
    call _PrintInt
}

