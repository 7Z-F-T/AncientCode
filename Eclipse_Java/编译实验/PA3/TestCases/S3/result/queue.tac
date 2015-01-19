VTABLE(_QueueItem) {
    _QueueItem.Init;
    _QueueItem.GetData;
    _QueueItem.GetNext;
    _QueueItem.GetPrev;
    _QueueItem.SetNext;
    _QueueItem.SetPrev;
}

VTABLE(_Queue) {
    _Queue.Init;
    _Queue.EnQueue;
    _Queue.DeQueue;
}

VTABLE(_Main) {
}

FUNCTION(_QueueItem_New) {
memo ''
_QueueItem_New:
    _T11 = 16
    parm _T11
    _T12 =  call _Alloc
    _T13 = 0
    *(_T12 + 4) = _T13
    *(_T12 + 8) = _T13
    *(_T12 + 12) = _T13
    _T14 = VTBL <_QueueItem>
    *(_T12 + 0) = _T14
    return _T12
}

FUNCTION(_Queue_New) {
memo ''
_Queue_New:
    _T19 = 12
    parm _T19
    _T20 =  call _Alloc
    _T21 = 0
    *(_T20 + 4) = _T21
    *(_T20 + 8) = _T21
    _T22 = VTBL <_Queue>
    *(_T20 + 0) = _T22
    return _T20
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T23 = 4
    parm _T23
    _T24 =  call _Alloc
    _T25 = VTBL <_Main>
    *(_T24 + 0) = _T25
    return _T24
}

FUNCTION(_QueueItem.Init) {
memo '_T0:4 _T1:8 _T2:12 _T3:16'
_QueueItem.Init:
    _T27 = *(_T0 + 4)
    *(_T0 + 4) = _T1
    _T28 = *(_T0 + 8)
    *(_T0 + 8) = _T2
    _T29 = *(_T2 + 12)
    *(_T2 + 12) = _T0
    _T30 = *(_T0 + 12)
    *(_T0 + 12) = _T3
    _T31 = *(_T3 + 8)
    *(_T3 + 8) = _T0
}

FUNCTION(_QueueItem.GetData) {
memo '_T4:4'
_QueueItem.GetData:
    _T32 = *(_T4 + 4)
    return _T32
}

FUNCTION(_QueueItem.GetNext) {
memo '_T5:4'
_QueueItem.GetNext:
    _T33 = *(_T5 + 8)
    return _T33
}

FUNCTION(_QueueItem.GetPrev) {
memo '_T6:4'
_QueueItem.GetPrev:
    _T34 = *(_T6 + 12)
    return _T34
}

FUNCTION(_QueueItem.SetNext) {
memo '_T7:4 _T8:8'
_QueueItem.SetNext:
    _T35 = *(_T7 + 8)
    *(_T7 + 8) = _T8
}

FUNCTION(_QueueItem.SetPrev) {
memo '_T9:4 _T10:8'
_QueueItem.SetPrev:
    _T36 = *(_T9 + 12)
    *(_T9 + 12) = _T10
}

FUNCTION(_Queue.Init) {
memo '_T15:4'
_Queue.Init:
    _T37 = *(_T15 + 8)
    _T38 = 16
    parm _T38
    _T42 =  call _Alloc
    _T43 = VTBL <_QueueItem>
    *(_T42 + 0) = _T43
    *(_T15 + 8) = _T42
    _T44 = *(_T15 + 8)
    _T45 = *(_T44 + 0)
    _T46 = *(_T45 + 0)
    _T47 = 0
    _T48 = *(_T15 + 8)
    _T49 = *(_T15 + 8)
    parm _T44
    parm _T47
    parm _T48
    parm _T49
    call _T46
}

FUNCTION(_Queue.EnQueue) {
memo '_T16:4 _T17:8'
_Queue.EnQueue:
    _T51 = 16
    parm _T51
    _T55 =  call _Alloc
    _T56 = VTBL <_QueueItem>
    *(_T55 + 0) = _T56
    _T50 = _T55
    _T57 = *(_T50 + 0)
    _T58 = *(_T57 + 0)
    _T59 = *(_T16 + 8)
    _T60 = *(_T59 + 0)
    _T61 = *(_T60 + 8)
    parm _T59
    _T62 =  call _T61
    _T63 = *(_T16 + 8)
    parm _T50
    parm _T17
    parm _T62
    parm _T63
    call _T58
}

FUNCTION(_Queue.DeQueue) {
memo '_T18:4'
_Queue.DeQueue:
    _T65 = *(_T18 + 8)
    _T66 = *(_T65 + 0)
    _T67 = *(_T66 + 12)
    parm _T65
    _T68 =  call _T67
    _T69 = *(_T18 + 8)
    _T70 = (_T68 == _T69)
    if (_T70 == 0) jump _L21
    _T71 = "Queue Is Empty"
    parm _T71
    call _PrintString
    _T72 = 0
    return _T72
    jump _L22
_L21:
    _T74 = *(_T18 + 8)
    _T75 = *(_T74 + 0)
    _T76 = *(_T75 + 12)
    parm _T74
    _T77 =  call _T76
    _T73 = _T77
    _T78 = *(_T73 + 0)
    _T79 = *(_T78 + 4)
    parm _T73
    _T80 =  call _T79
    _T64 = _T80
    _T81 = *(_T73 + 0)
    _T82 = *(_T81 + 12)
    parm _T73
    _T83 =  call _T82
    _T84 = *(_T83 + 0)
    _T85 = *(_T84 + 16)
    _T86 = *(_T73 + 0)
    _T87 = *(_T86 + 8)
    parm _T73
    _T88 =  call _T87
    parm _T83
    parm _T88
    call _T85
    _T89 = *(_T73 + 0)
    _T90 = *(_T89 + 8)
    parm _T73
    _T91 =  call _T90
    _T92 = *(_T91 + 0)
    _T93 = *(_T92 + 20)
    _T94 = *(_T73 + 0)
    _T95 = *(_T94 + 12)
    parm _T73
    _T96 =  call _T95
    parm _T91
    parm _T96
    call _T93
_L22:
    return _T64
}

FUNCTION(main) {
memo ''
main:
    _T99 = 12
    parm _T99
    _T103 =  call _Alloc
    _T104 = VTBL <_Queue>
    *(_T103 + 0) = _T104
    _T97 = _T103
    _T105 = *(_T97 + 0)
    _T106 = *(_T105 + 0)
    parm _T97
    call _T106
    _T107 = 0
    _T98 = _T107
_L24:
    _T108 = 10
    _T109 = (_T98 < _T108)
    if (_T109 == 0) jump _L23
    _T110 = *(_T97 + 0)
    _T111 = *(_T110 + 4)
    parm _T97
    parm _T98
    call _T111
    _T112 = 1
    _T113 = (_T98 + _T112)
    _T98 = _T113
    jump _L24
_L23:
    _T114 = 0
    _T98 = _T114
_L26:
    _T115 = 4
    _T116 = (_T98 < _T115)
    if (_T116 == 0) jump _L25
    _T117 = *(_T97 + 0)
    _T118 = *(_T117 + 8)
    parm _T97
    _T119 =  call _T118
    _T120 = " "
    parm _T119
    call _PrintInt
    parm _T120
    call _PrintString
    _T121 = 1
    _T122 = (_T98 + _T121)
    _T98 = _T122
    jump _L26
_L25:
    _T123 = "\n"
    parm _T123
    call _PrintString
    _T124 = 0
    _T98 = _T124
_L28:
    _T125 = 10
    _T126 = (_T98 < _T125)
    if (_T126 == 0) jump _L27
    _T127 = *(_T97 + 0)
    _T128 = *(_T127 + 4)
    parm _T97
    parm _T98
    call _T128
    _T129 = 1
    _T130 = (_T98 + _T129)
    _T98 = _T130
    jump _L28
_L27:
    _T131 = 0
    _T98 = _T131
_L30:
    _T132 = 17
    _T133 = (_T98 < _T132)
    if (_T133 == 0) jump _L29
    _T134 = *(_T97 + 0)
    _T135 = *(_T134 + 8)
    parm _T97
    _T136 =  call _T135
    _T137 = " "
    parm _T136
    call _PrintInt
    parm _T137
    call _PrintString
    _T138 = 1
    _T139 = (_T98 + _T138)
    _T98 = _T139
    jump _L30
_L29:
    _T140 = "\n"
    parm _T140
    call _PrintString
}

