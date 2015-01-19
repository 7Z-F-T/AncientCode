VTABLE(_Matrix) {
    _Matrix.Init;
    _Matrix.Set;
    _Matrix.Get;
    _Matrix.PrintMatrix;
    _Matrix.SeedMatrix;
}

VTABLE(_DenseMatrix) {
    _DenseMatrix.Init;
    _DenseMatrix.Set;
    _DenseMatrix.Get;
    _Matrix.PrintMatrix;
    _Matrix.SeedMatrix;
}

VTABLE(_SparseItem) {
    _SparseItem.Init;
    _SparseItem.GetNext;
    _SparseItem.GetY;
    _SparseItem.GetData;
    _SparseItem.SetData;
}

VTABLE(_SparseMatrix) {
    _SparseMatrix.Init;
    _SparseMatrix.Set;
    _SparseMatrix.Get;
    _Matrix.PrintMatrix;
    _Matrix.SeedMatrix;
    _SparseMatrix.Find;
}

VTABLE(_Main) {
}

FUNCTION(_Matrix_New) {
memo ''
_Matrix_New:
    _T10 = 4
    parm _T10
    _T11 =  call _Alloc
    _T12 = VTBL <_Matrix>
    *(_T11 + 0) = _T12
    return _T11
}

FUNCTION(_DenseMatrix_New) {
memo ''
_DenseMatrix_New:
    _T21 = 8
    parm _T21
    _T22 =  call _Alloc
    _T23 = 0
    *(_T22 + 4) = _T23
    _T24 = VTBL <_DenseMatrix>
    *(_T22 + 0) = _T24
    return _T22
}

FUNCTION(_SparseItem_New) {
memo ''
_SparseItem_New:
    _T34 = 16
    parm _T34
    _T35 =  call _Alloc
    _T36 = 0
    *(_T35 + 4) = _T36
    *(_T35 + 8) = _T36
    *(_T35 + 12) = _T36
    _T37 = VTBL <_SparseItem>
    *(_T35 + 0) = _T37
    return _T35
}

FUNCTION(_SparseMatrix_New) {
memo ''
_SparseMatrix_New:
    _T49 = 8
    parm _T49
    _T50 =  call _Alloc
    _T51 = 0
    *(_T50 + 4) = _T51
    _T52 = VTBL <_SparseMatrix>
    *(_T50 + 0) = _T52
    return _T50
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T53 = 4
    parm _T53
    _T54 =  call _Alloc
    _T55 = VTBL <_Main>
    *(_T54 + 0) = _T55
    return _T54
}

FUNCTION(_Matrix.Init) {
memo '_T0:4'
_Matrix.Init:
}

FUNCTION(_Matrix.Set) {
memo '_T1:4 _T2:8 _T3:12 _T4:16'
_Matrix.Set:
}

FUNCTION(_Matrix.Get) {
memo '_T5:4 _T6:8 _T7:12'
_Matrix.Get:
}

FUNCTION(_Matrix.PrintMatrix) {
memo '_T8:4'
_Matrix.PrintMatrix:
    _T59 = 0
    _T57 = _T59
_L32:
    _T60 = 10
    _T61 = (_T57 < _T60)
    if (_T61 == 0) jump _L31
    _T62 = 0
    _T58 = _T62
_L34:
    _T63 = 10
    _T64 = (_T58 < _T63)
    if (_T64 == 0) jump _L33
    _T65 = *(_T8 + 0)
    _T66 = *(_T65 + 8)
    parm _T8
    parm _T57
    parm _T58
    _T67 =  call _T66
    _T68 = "\t"
    parm _T67
    call _PrintInt
    parm _T68
    call _PrintString
    _T69 = 1
    _T70 = (_T58 + _T69)
    _T58 = _T70
    jump _L34
_L33:
    _T71 = 1
    _T72 = (_T57 + _T71)
    _T57 = _T72
    _T73 = "\n"
    parm _T73
    call _PrintString
    jump _L32
_L31:
}

FUNCTION(_Matrix.SeedMatrix) {
memo '_T9:4'
_Matrix.SeedMatrix:
    _T76 = 0
    _T74 = _T76
_L36:
    _T77 = 5
    _T78 = (_T74 < _T77)
    if (_T78 == 0) jump _L35
    _T79 = 0
    _T75 = _T79
_L38:
    _T80 = 5
    _T81 = (_T75 < _T80)
    if (_T81 == 0) jump _L37
    _T82 = *(_T9 + 0)
    _T83 = *(_T82 + 4)
    _T84 = (_T74 + _T75)
    parm _T9
    parm _T74
    parm _T75
    parm _T84
    call _T83
    _T85 = 1
    _T86 = (_T75 + _T85)
    _T75 = _T86
    jump _L38
_L37:
    _T87 = 1
    _T88 = (_T74 + _T87)
    _T74 = _T88
    jump _L36
_L35:
    _T89 = *(_T9 + 0)
    _T90 = *(_T89 + 4)
    _T91 = 2
    _T92 = 3
    _T93 = 4
    parm _T9
    parm _T91
    parm _T92
    parm _T93
    call _T90
    _T94 = *(_T9 + 0)
    _T95 = *(_T94 + 4)
    _T96 = 4
    _T97 = 6
    _T98 = 2
    parm _T9
    parm _T96
    parm _T97
    parm _T98
    call _T95
    _T99 = *(_T9 + 0)
    _T100 = *(_T99 + 4)
    _T101 = 2
    _T102 = 3
    _T103 = 5
    parm _T9
    parm _T101
    parm _T102
    parm _T103
    call _T100
    _T104 = *(_T9 + 0)
    _T105 = *(_T104 + 4)
    _T106 = 0
    _T107 = 0
    _T108 = 1
    parm _T9
    parm _T106
    parm _T107
    parm _T108
    call _T105
    _T109 = *(_T9 + 0)
    _T110 = *(_T109 + 4)
    _T111 = 1
    _T112 = 6
    _T113 = 3
    parm _T9
    parm _T111
    parm _T112
    parm _T113
    call _T110
    _T114 = *(_T9 + 0)
    _T115 = *(_T114 + 4)
    _T116 = 7
    _T117 = 7
    _T118 = 7
    parm _T9
    parm _T116
    parm _T117
    parm _T118
    call _T115
}

FUNCTION(_DenseMatrix.Init) {
memo '_T13:4'
_DenseMatrix.Init:
    _T121 = 0
    _T119 = _T121
    _T122 = *(_T13 + 4)
    _T123 = 10
    _T124 = 0
    _T125 = (_T123 < _T124)
    if (_T125 == 0) jump _L39
    _T126 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T126
    call _PrintString
    call _Halt
_L39:
    _T127 = 4
    _T128 = (_T127 * _T123)
    _T129 = (_T127 + _T128)
    parm _T129
    _T130 =  call _Alloc
    *(_T130 + 0) = _T123
    _T131 = 0
    _T130 = (_T130 + _T129)
_L40:
    _T129 = (_T129 - _T127)
    if (_T129 == 0) jump _L41
    _T130 = (_T130 - _T127)
    *(_T130 + 0) = _T131
    jump _L40
_L41:
    *(_T13 + 4) = _T130
_L43:
    _T132 = 10
    _T133 = (_T119 < _T132)
    if (_T133 == 0) jump _L42
    _T134 = *(_T13 + 4)
    _T135 = *(_T134 - 4)
    _T136 = (_T119 < _T135)
    if (_T136 == 0) jump _L44
    _T137 = 0
    _T138 = (_T119 < _T137)
    if (_T138 == 0) jump _L45
_L44:
    _T139 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T139
    call _PrintString
    call _Halt
_L45:
    _T140 = 10
    _T141 = 0
    _T142 = (_T140 < _T141)
    if (_T142 == 0) jump _L46
    _T143 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T143
    call _PrintString
    call _Halt
_L46:
    _T144 = 4
    _T145 = (_T144 * _T140)
    _T146 = (_T144 + _T145)
    parm _T146
    _T147 =  call _Alloc
    *(_T147 + 0) = _T140
    _T148 = 0
    _T147 = (_T147 + _T146)
_L47:
    _T146 = (_T146 - _T144)
    if (_T146 == 0) jump _L48
    _T147 = (_T147 - _T144)
    *(_T147 + 0) = _T148
    jump _L47
_L48:
    _T149 = 4
    _T150 = (_T119 * _T149)
    _T151 = (_T134 + _T150)
    *(_T151 + 0) = _T147
    _T152 = 1
    _T153 = (_T119 + _T152)
    _T119 = _T153
    jump _L43
_L42:
    _T154 = 0
    _T119 = _T154
_L50:
    _T155 = 10
    _T156 = (_T119 < _T155)
    if (_T156 == 0) jump _L49
    _T157 = 0
    _T120 = _T157
_L52:
    _T158 = 10
    _T159 = (_T120 < _T158)
    if (_T159 == 0) jump _L51
    _T160 = *(_T13 + 4)
    _T161 = *(_T160 - 4)
    _T162 = (_T119 < _T161)
    if (_T162 == 0) jump _L53
    _T163 = 0
    _T164 = (_T119 < _T163)
    if (_T164 == 0) jump _L54
_L53:
    _T165 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T165
    call _PrintString
    call _Halt
_L54:
    _T166 = 4
    _T167 = (_T119 * _T166)
    _T168 = (_T160 + _T167)
    _T169 = *(_T168 + 0)
    _T170 = *(_T169 - 4)
    _T171 = (_T120 < _T170)
    if (_T171 == 0) jump _L55
    _T172 = 0
    _T173 = (_T120 < _T172)
    if (_T173 == 0) jump _L56
_L55:
    _T174 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T174
    call _PrintString
    call _Halt
_L56:
    _T175 = 0
    _T176 = 4
    _T177 = (_T120 * _T176)
    _T178 = (_T169 + _T177)
    *(_T178 + 0) = _T175
    _T179 = 1
    _T180 = (_T120 + _T179)
    _T120 = _T180
    jump _L52
_L51:
    _T181 = 1
    _T182 = (_T119 + _T181)
    _T119 = _T182
    jump _L50
_L49:
}

FUNCTION(_DenseMatrix.Set) {
memo '_T14:4 _T15:8 _T16:12 _T17:16'
_DenseMatrix.Set:
    _T183 = *(_T14 + 4)
    _T184 = *(_T183 - 4)
    _T185 = (_T15 < _T184)
    if (_T185 == 0) jump _L57
    _T186 = 0
    _T187 = (_T15 < _T186)
    if (_T187 == 0) jump _L58
_L57:
    _T188 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T188
    call _PrintString
    call _Halt
_L58:
    _T189 = 4
    _T190 = (_T15 * _T189)
    _T191 = (_T183 + _T190)
    _T192 = *(_T191 + 0)
    _T193 = *(_T192 - 4)
    _T194 = (_T16 < _T193)
    if (_T194 == 0) jump _L59
    _T195 = 0
    _T196 = (_T16 < _T195)
    if (_T196 == 0) jump _L60
_L59:
    _T197 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T197
    call _PrintString
    call _Halt
_L60:
    _T198 = 4
    _T199 = (_T16 * _T198)
    _T200 = (_T192 + _T199)
    *(_T200 + 0) = _T17
}

FUNCTION(_DenseMatrix.Get) {
memo '_T18:4 _T19:8 _T20:12'
_DenseMatrix.Get:
    _T201 = *(_T18 + 4)
    _T202 = *(_T201 - 4)
    _T203 = (_T19 < _T202)
    if (_T203 == 0) jump _L61
    _T204 = 0
    _T205 = (_T19 < _T204)
    if (_T205 == 0) jump _L62
_L61:
    _T206 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T206
    call _PrintString
    call _Halt
_L62:
    _T207 = 4
    _T208 = (_T19 * _T207)
    _T209 = (_T201 + _T208)
    _T210 = *(_T209 + 0)
    _T211 = *(_T210 - 4)
    _T212 = (_T20 < _T211)
    if (_T212 == 0) jump _L63
    _T213 = 0
    _T214 = (_T20 < _T213)
    if (_T214 == 0) jump _L64
_L63:
    _T215 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T215
    call _PrintString
    call _Halt
_L64:
    _T216 = 4
    _T217 = (_T20 * _T216)
    _T218 = (_T210 + _T217)
    _T219 = *(_T218 + 0)
    return _T219
}

FUNCTION(_SparseItem.Init) {
memo '_T25:4 _T26:8 _T27:12 _T28:16'
_SparseItem.Init:
    _T220 = *(_T25 + 4)
    *(_T25 + 4) = _T26
    _T221 = *(_T25 + 8)
    *(_T25 + 8) = _T27
    _T222 = *(_T25 + 12)
    *(_T25 + 12) = _T28
}

FUNCTION(_SparseItem.GetNext) {
memo '_T29:4'
_SparseItem.GetNext:
    _T223 = *(_T29 + 12)
    return _T223
}

FUNCTION(_SparseItem.GetY) {
memo '_T30:4'
_SparseItem.GetY:
    _T224 = *(_T30 + 8)
    return _T224
}

FUNCTION(_SparseItem.GetData) {
memo '_T31:4'
_SparseItem.GetData:
    _T225 = *(_T31 + 4)
    return _T225
}

FUNCTION(_SparseItem.SetData) {
memo '_T32:4 _T33:8'
_SparseItem.SetData:
    _T226 = *(_T32 + 4)
    *(_T32 + 4) = _T33
}

FUNCTION(_SparseMatrix.Init) {
memo '_T38:4'
_SparseMatrix.Init:
    _T228 = 0
    _T227 = _T228
    _T229 = *(_T38 + 4)
    _T230 = 10
    _T231 = 0
    _T232 = (_T230 < _T231)
    if (_T232 == 0) jump _L65
    _T233 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T233
    call _PrintString
    call _Halt
_L65:
    _T234 = 4
    _T235 = (_T234 * _T230)
    _T236 = (_T234 + _T235)
    parm _T236
    _T237 =  call _Alloc
    *(_T237 + 0) = _T230
    _T238 = 0
    _T237 = (_T237 + _T236)
_L66:
    _T236 = (_T236 - _T234)
    if (_T236 == 0) jump _L67
    _T237 = (_T237 - _T234)
    *(_T237 + 0) = _T238
    jump _L66
_L67:
    *(_T38 + 4) = _T237
_L69:
    _T239 = 10
    _T240 = (_T227 < _T239)
    if (_T240 == 0) jump _L68
    _T241 = *(_T38 + 4)
    _T242 = *(_T241 - 4)
    _T243 = (_T227 < _T242)
    if (_T243 == 0) jump _L70
    _T244 = 0
    _T245 = (_T227 < _T244)
    if (_T245 == 0) jump _L71
_L70:
    _T246 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T246
    call _PrintString
    call _Halt
_L71:
    _T247 = 0
    _T248 = 4
    _T249 = (_T227 * _T248)
    _T250 = (_T241 + _T249)
    *(_T250 + 0) = _T247
    _T251 = 1
    _T252 = (_T227 + _T251)
    _T227 = _T252
    jump _L69
_L68:
}

FUNCTION(_SparseMatrix.Find) {
memo '_T39:4 _T40:8 _T41:12'
_SparseMatrix.Find:
    _T254 = *(_T39 + 4)
    _T255 = *(_T254 - 4)
    _T256 = (_T40 < _T255)
    if (_T256 == 0) jump _L72
    _T257 = 0
    _T258 = (_T40 < _T257)
    if (_T258 == 0) jump _L73
_L72:
    _T259 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T259
    call _PrintString
    call _Halt
_L73:
    _T260 = 4
    _T261 = (_T40 * _T260)
    _T262 = (_T254 + _T261)
    _T263 = *(_T262 + 0)
    _T253 = _T263
_L75:
    _T264 = 0
    _T265 = (_T253 != _T264)
    if (_T265 == 0) jump _L74
    _T266 = *(_T253 + 0)
    _T267 = *(_T266 + 8)
    parm _T253
    _T268 =  call _T267
    _T269 = (_T268 == _T41)
    if (_T269 == 0) jump _L76
    return _T253
_L76:
    _T270 = *(_T253 + 0)
    _T271 = *(_T270 + 4)
    parm _T253
    _T272 =  call _T271
    _T253 = _T272
    jump _L75
_L74:
    _T273 = 0
    return _T273
}

FUNCTION(_SparseMatrix.Set) {
memo '_T42:4 _T43:8 _T44:12 _T45:16'
_SparseMatrix.Set:
    _T275 = *(_T42 + 0)
    _T276 = *(_T275 + 20)
    parm _T42
    parm _T43
    parm _T44
    _T277 =  call _T276
    _T274 = _T277
    _T278 = 0
    _T279 = (_T274 != _T278)
    if (_T279 == 0) jump _L77
    _T280 = *(_T274 + 0)
    _T281 = *(_T280 + 16)
    parm _T274
    parm _T45
    call _T281
    jump _L78
_L77:
    _T282 = 16
    parm _T282
    _T286 =  call _Alloc
    _T287 = VTBL <_SparseItem>
    *(_T286 + 0) = _T287
    _T274 = _T286
    _T288 = *(_T274 + 0)
    _T289 = *(_T288 + 0)
    _T290 = *(_T42 + 4)
    _T291 = *(_T290 - 4)
    _T292 = (_T43 < _T291)
    if (_T292 == 0) jump _L79
    _T293 = 0
    _T294 = (_T43 < _T293)
    if (_T294 == 0) jump _L80
_L79:
    _T295 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T295
    call _PrintString
    call _Halt
_L80:
    _T296 = 4
    _T297 = (_T43 * _T296)
    _T298 = (_T290 + _T297)
    _T299 = *(_T298 + 0)
    parm _T274
    parm _T45
    parm _T44
    parm _T299
    call _T289
    _T300 = *(_T42 + 4)
    _T301 = *(_T300 - 4)
    _T302 = (_T43 < _T301)
    if (_T302 == 0) jump _L81
    _T303 = 0
    _T304 = (_T43 < _T303)
    if (_T304 == 0) jump _L82
_L81:
    _T305 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T305
    call _PrintString
    call _Halt
_L82:
    _T306 = 4
    _T307 = (_T43 * _T306)
    _T308 = (_T300 + _T307)
    *(_T308 + 0) = _T274
_L78:
}

FUNCTION(_SparseMatrix.Get) {
memo '_T46:4 _T47:8 _T48:12'
_SparseMatrix.Get:
    _T310 = *(_T46 + 0)
    _T311 = *(_T310 + 20)
    parm _T46
    parm _T47
    parm _T48
    _T312 =  call _T311
    _T309 = _T312
    _T313 = 0
    _T314 = (_T309 != _T313)
    if (_T314 == 0) jump _L83
    _T315 = *(_T309 + 0)
    _T316 = *(_T315 + 12)
    parm _T309
    _T317 =  call _T316
    return _T317
    jump _L84
_L83:
    _T318 = 0
    return _T318
_L84:
}

FUNCTION(main) {
memo ''
main:
    _T320 = "Dense Rep \n"
    parm _T320
    call _PrintString
    _T321 = 8
    parm _T321
    _T325 =  call _Alloc
    _T326 = VTBL <_DenseMatrix>
    *(_T325 + 0) = _T326
    _T319 = _T325
    _T327 = *(_T319 + 0)
    _T328 = *(_T327 + 0)
    parm _T319
    call _T328
    _T329 = *(_T319 + 0)
    _T330 = *(_T329 + 16)
    parm _T319
    call _T330
    _T331 = *(_T319 + 0)
    _T332 = *(_T331 + 12)
    parm _T319
    call _T332
    _T333 = "Sparse Rep \n"
    parm _T333
    call _PrintString
    _T334 = 8
    parm _T334
    _T338 =  call _Alloc
    _T339 = VTBL <_SparseMatrix>
    *(_T338 + 0) = _T339
    _T319 = _T338
    _T340 = *(_T319 + 0)
    _T341 = *(_T340 + 0)
    parm _T319
    call _T341
    _T342 = *(_T319 + 0)
    _T343 = *(_T342 + 16)
    parm _T319
    call _T343
    _T344 = *(_T319 + 0)
    _T345 = *(_T344 + 12)
    parm _T319
    call _T345
}

