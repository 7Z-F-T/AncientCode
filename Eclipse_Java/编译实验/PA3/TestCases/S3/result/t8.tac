VTABLE(_Animal) {
    _Animal.InitAnimal;
    _Animal.GetHeight;
    _Animal.GetMom;
}

VTABLE(_Cow) {
    _Animal.InitAnimal;
    _Animal.GetHeight;
    _Animal.GetMom;
    _Cow.InitCow;
    _Cow.IsSpottedCow;
}

VTABLE(_Main) {
}

FUNCTION(_Animal_New) {
memo ''
_Animal_New:
    _T5 = 12
    parm _T5
    _T6 =  call _Alloc
    _T7 = 0
    *(_T6 + 4) = _T7
    *(_T6 + 8) = _T7
    _T8 = VTBL <_Animal>
    *(_T6 + 0) = _T8
    return _T6
}

FUNCTION(_Cow_New) {
memo ''
_Cow_New:
    _T14 = 16
    parm _T14
    _T15 =  call _Alloc
    _T16 = 0
    *(_T15 + 4) = _T16
    *(_T15 + 8) = _T16
    *(_T15 + 12) = _T16
    _T17 = VTBL <_Cow>
    *(_T15 + 0) = _T17
    return _T15
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T18 = 4
    parm _T18
    _T19 =  call _Alloc
    _T20 = VTBL <_Main>
    *(_T19 + 0) = _T20
    return _T19
}

FUNCTION(_Animal.InitAnimal) {
memo '_T0:4 _T1:8 _T2:12'
_Animal.InitAnimal:
    _T22 = *(_T0 + 4)
    *(_T0 + 4) = _T1
    _T23 = *(_T0 + 8)
    *(_T0 + 8) = _T2
}

FUNCTION(_Animal.GetHeight) {
memo '_T3:4'
_Animal.GetHeight:
    _T24 = *(_T3 + 4)
    return _T24
}

FUNCTION(_Animal.GetMom) {
memo '_T4:4'
_Animal.GetMom:
    _T25 = *(_T4 + 8)
    return _T25
}

FUNCTION(_Cow.InitCow) {
memo '_T9:4 _T10:8 _T11:12 _T12:16'
_Cow.InitCow:
    _T26 = *(_T9 + 12)
    *(_T9 + 12) = _T12
    _T27 = *(_T9 + 0)
    _T28 = *(_T27 + 0)
    parm _T9
    parm _T10
    parm _T11
    call _T28
}

FUNCTION(_Cow.IsSpottedCow) {
memo '_T13:4'
_Cow.IsSpottedCow:
    _T29 = *(_T13 + 12)
    return _T29
}

FUNCTION(main) {
memo ''
main:
    _T32 = 16
    parm _T32
    _T36 =  call _Alloc
    _T37 = VTBL <_Cow>
    *(_T36 + 0) = _T37
    _T30 = _T36
    _T38 = *(_T30 + 0)
    _T39 = *(_T38 + 12)
    _T40 = 5
    _T41 = 0
    _T42 = 1
    parm _T30
    parm _T40
    parm _T41
    parm _T42
    call _T39
    _T31 = _T30
    _T43 = *(_T31 + 0)
    _T44 = *(_T43 + 8)
    parm _T31
    _T45 =  call _T44
    _T46 = "spots: "
    _T47 = *(_T30 + 0)
    _T48 = *(_T47 + 16)
    parm _T30
    _T49 =  call _T48
    _T50 = "    height: "
    _T51 = *(_T31 + 0)
    _T52 = *(_T51 + 4)
    parm _T31
    _T53 =  call _T52
    parm _T46
    call _PrintString
    parm _T49
    call _PrintBool
    parm _T50
    call _PrintString
    parm _T53
    call _PrintInt
}

