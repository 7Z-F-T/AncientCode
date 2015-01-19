VTABLE(_Cow) {
    _Cow.Init;
    _Cow.Moo;
}

VTABLE(_Main) {
}

FUNCTION(_Cow_New) {
memo ''
_Cow_New:
    _T4 = 12
    parm _T4
    _T5 =  call _Alloc
    _T6 = 0
    *(_T5 + 4) = _T6
    *(_T5 + 8) = _T6
    _T7 = VTBL <_Cow>
    *(_T5 + 0) = _T7
    return _T5
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T8 = 4
    parm _T8
    _T9 =  call _Alloc
    _T10 = VTBL <_Main>
    *(_T9 + 0) = _T10
    return _T9
}

FUNCTION(_Cow.Init) {
memo '_T0:4 _T1:8 _T2:12'
_Cow.Init:
    _T12 = *(_T0 + 8)
    *(_T0 + 8) = _T1
    _T13 = *(_T0 + 4)
    *(_T0 + 4) = _T2
}

FUNCTION(_Cow.Moo) {
memo '_T3:4'
_Cow.Moo:
    _T14 = *(_T3 + 4)
    _T15 = " "
    _T16 = *(_T3 + 8)
    _T17 = "\n"
    parm _T14
    call _PrintInt
    parm _T15
    call _PrintString
    parm _T16
    call _PrintInt
    parm _T17
    call _PrintString
}

FUNCTION(main) {
memo ''
main:
    _T19 = 12
    parm _T19
    _T23 =  call _Alloc
    _T24 = VTBL <_Cow>
    *(_T23 + 0) = _T24
    _T18 = _T23
    _T25 = *(_T18 + 0)
    _T26 = *(_T25 + 0)
    _T27 = 100
    _T28 = 122
    parm _T18
    parm _T27
    parm _T28
    call _T26
    _T29 = *(_T18 + 0)
    _T30 = *(_T29 + 4)
    parm _T18
    call _T30
}

