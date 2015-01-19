VTABLE(_rndModule) {
    _rndModule.Init;
    _rndModule.Random;
    _rndModule.RndInt;
}

VTABLE(_Deck) {
    _Deck.Init;
    _Deck.Shuffle;
    _Deck.GetCard;
}

VTABLE(_BJDeck) {
    _BJDeck.Init;
    _BJDeck.DealCard;
    _BJDeck.Shuffle;
    _BJDeck.NumCardsRemaining;
}

VTABLE(_Player) {
    _Player.Init;
    _Player.Hit;
    _Player.DoubleDown;
    _Player.TakeTurn;
    _Player.HasMoney;
    _Player.PrintMoney;
    _Player.PlaceBet;
    _Player.GetTotal;
    _Player.Resolve;
    _Player.GetYesOrNo;
}

VTABLE(_Dealer) {
    _Dealer.Init;
    _Player.Hit;
    _Player.DoubleDown;
    _Dealer.TakeTurn;
    _Player.HasMoney;
    _Player.PrintMoney;
    _Player.PlaceBet;
    _Player.GetTotal;
    _Player.Resolve;
    _Player.GetYesOrNo;
}

VTABLE(_House) {
    _House.SetupGame;
    _House.SetupPlayers;
    _House.TakeAllBets;
    _House.TakeAllTurns;
    _House.ResolveAllPlayers;
    _House.PrintAllMoney;
    _House.PlayOneGame;
}

VTABLE(_Main) {
}

FUNCTION(_rndModule_New) {
memo ''
_rndModule_New:
    _T5 = 8
    parm _T5
    _T6 =  call _Alloc
    _T7 = 0
    *(_T6 + 4) = _T7
    _T8 = VTBL <_rndModule>
    *(_T6 + 0) = _T8
    return _T6
}

FUNCTION(_Deck_New) {
memo ''
_Deck_New:
    _T13 = 16
    parm _T13
    _T14 =  call _Alloc
    _T15 = 0
    *(_T14 + 4) = _T15
    *(_T14 + 8) = _T15
    *(_T14 + 12) = _T15
    _T16 = VTBL <_Deck>
    *(_T14 + 0) = _T16
    return _T14
}

FUNCTION(_BJDeck_New) {
memo ''
_BJDeck_New:
    _T22 = 16
    parm _T22
    _T23 =  call _Alloc
    _T24 = 0
    *(_T23 + 4) = _T24
    *(_T23 + 8) = _T24
    *(_T23 + 12) = _T24
    _T25 = VTBL <_BJDeck>
    *(_T23 + 0) = _T25
    return _T23
}

FUNCTION(_Player_New) {
memo ''
_Player_New:
    _T42 = 28
    parm _T42
    _T43 =  call _Alloc
    _T44 = 0
    _T45 = 4
    _T46 = (_T43 + _T42)
_L32:
    _T47 = (_T46 - _T45)
    _T46 = _T47
    _T48 = (_T42 - _T45)
    _T42 = _T48
    if (_T42 == 0) jump _L33
    *(_T46 + 0) = _T44
    jump _L32
_L33:
    _T49 = VTBL <_Player>
    *(_T46 + 0) = _T49
    return _T46
}

FUNCTION(_Dealer_New) {
memo ''
_Dealer_New:
    _T54 = 28
    parm _T54
    _T55 =  call _Alloc
    _T56 = 0
    _T57 = 4
    _T58 = (_T55 + _T54)
_L37:
    _T59 = (_T58 - _T57)
    _T58 = _T59
    _T60 = (_T54 - _T57)
    _T54 = _T60
    if (_T54 == 0) jump _L38
    *(_T58 + 0) = _T56
    jump _L37
_L38:
    _T61 = VTBL <_Dealer>
    *(_T58 + 0) = _T61
    return _T58
}

FUNCTION(_House_New) {
memo ''
_House_New:
    _T69 = 16
    parm _T69
    _T70 =  call _Alloc
    _T71 = 0
    *(_T70 + 4) = _T71
    *(_T70 + 8) = _T71
    *(_T70 + 12) = _T71
    _T72 = VTBL <_House>
    *(_T70 + 0) = _T72
    return _T70
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T74 = 4
    parm _T74
    _T75 =  call _Alloc
    _T76 = VTBL <_Main>
    *(_T75 + 0) = _T76
    return _T75
}

FUNCTION(_rndModule.Init) {
memo '_T0:4 _T1:8'
_rndModule.Init:
    _T78 = *(_T0 + 4)
    *(_T0 + 4) = _T1
}

FUNCTION(_rndModule.Random) {
memo '_T2:4'
_rndModule.Random:
    _T79 = *(_T2 + 4)
    _T80 = 15625
    _T81 = *(_T2 + 4)
    _T82 = 10000
    _T83 = (_T81 % _T82)
    _T84 = (_T80 * _T83)
    _T85 = 22221
    _T86 = (_T84 + _T85)
    _T87 = 65536
    _T88 = (_T86 % _T87)
    *(_T2 + 4) = _T88
    _T89 = *(_T2 + 4)
    return _T89
}

FUNCTION(_rndModule.RndInt) {
memo '_T3:4 _T4:8'
_rndModule.RndInt:
    _T90 = *(_T3 + 0)
    _T91 = *(_T90 + 4)
    parm _T3
    _T92 =  call _T91
    _T93 = (_T92 % _T4)
    return _T93
}

FUNCTION(_Deck.Init) {
memo '_T9:4 _T10:8'
_Deck.Init:
    _T94 = *(_T9 + 8)
    _T95 = 52
    _T96 = 0
    _T97 = (_T95 < _T96)
    if (_T97 == 0) jump _L50
    _T98 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T98
    call _PrintString
    call _Halt
_L50:
    _T99 = 4
    _T100 = (_T99 * _T95)
    _T101 = (_T99 + _T100)
    parm _T101
    _T102 =  call _Alloc
    *(_T102 + 0) = _T95
    _T103 = 0
    _T102 = (_T102 + _T101)
_L51:
    _T101 = (_T101 - _T99)
    if (_T101 == 0) jump _L52
    _T102 = (_T102 - _T99)
    *(_T102 + 0) = _T103
    jump _L51
_L52:
    *(_T9 + 8) = _T102
    _T104 = *(_T9 + 12)
    *(_T9 + 12) = _T10
}

FUNCTION(_Deck.Shuffle) {
memo '_T11:4'
_Deck.Shuffle:
    _T105 = *(_T11 + 4)
    _T106 = 1
    *(_T11 + 4) = _T106
_L54:
    _T107 = *(_T11 + 4)
    _T108 = 52
    _T109 = (_T107 <= _T108)
    if (_T109 == 0) jump _L53
    _T110 = *(_T11 + 8)
    _T111 = *(_T11 + 4)
    _T112 = 1
    _T113 = (_T111 - _T112)
    _T114 = *(_T110 - 4)
    _T115 = (_T113 < _T114)
    if (_T115 == 0) jump _L55
    _T116 = 0
    _T117 = (_T113 < _T116)
    if (_T117 == 0) jump _L56
_L55:
    _T118 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T118
    call _PrintString
    call _Halt
_L56:
    _T119 = *(_T11 + 4)
    _T120 = 13
    _T121 = (_T119 % _T120)
    _T122 = 4
    _T123 = (_T113 * _T122)
    _T124 = (_T110 + _T123)
    *(_T124 + 0) = _T121
    _T125 = *(_T11 + 4)
    _T126 = *(_T11 + 4)
    _T127 = 1
    _T128 = (_T126 + _T127)
    *(_T11 + 4) = _T128
    jump _L54
_L53:
    _T129 = *(_T11 + 4)
    _T130 = *(_T11 + 4)
    _T131 = 1
    _T132 = (_T130 - _T131)
    *(_T11 + 4) = _T132
_L58:
    _T133 = *(_T11 + 4)
    _T134 = 0
    _T135 = (_T133 > _T134)
    if (_T135 == 0) jump _L57
    _T138 = *(_T11 + 12)
    _T139 = *(_T138 + 0)
    _T140 = *(_T139 + 8)
    _T141 = *(_T11 + 4)
    parm _T138
    parm _T141
    _T142 =  call _T140
    _T136 = _T142
    _T143 = *(_T11 + 4)
    _T144 = *(_T11 + 4)
    _T145 = 1
    _T146 = (_T144 - _T145)
    *(_T11 + 4) = _T146
    _T147 = *(_T11 + 8)
    _T148 = *(_T11 + 4)
    _T149 = *(_T147 - 4)
    _T150 = (_T148 < _T149)
    if (_T150 == 0) jump _L59
    _T151 = 0
    _T152 = (_T148 < _T151)
    if (_T152 == 0) jump _L60
_L59:
    _T153 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T153
    call _PrintString
    call _Halt
_L60:
    _T154 = 4
    _T155 = (_T148 * _T154)
    _T156 = (_T147 + _T155)
    _T157 = *(_T156 + 0)
    _T137 = _T157
    _T158 = *(_T11 + 8)
    _T159 = *(_T11 + 4)
    _T160 = *(_T158 - 4)
    _T161 = (_T159 < _T160)
    if (_T161 == 0) jump _L61
    _T162 = 0
    _T163 = (_T159 < _T162)
    if (_T163 == 0) jump _L62
_L61:
    _T164 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T164
    call _PrintString
    call _Halt
_L62:
    _T165 = *(_T11 + 8)
    _T166 = *(_T165 - 4)
    _T167 = (_T136 < _T166)
    if (_T167 == 0) jump _L63
    _T168 = 0
    _T169 = (_T136 < _T168)
    if (_T169 == 0) jump _L64
_L63:
    _T170 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T170
    call _PrintString
    call _Halt
_L64:
    _T171 = 4
    _T172 = (_T136 * _T171)
    _T173 = (_T165 + _T172)
    _T174 = *(_T173 + 0)
    _T175 = 4
    _T176 = (_T159 * _T175)
    _T177 = (_T158 + _T176)
    *(_T177 + 0) = _T174
    _T178 = *(_T11 + 8)
    _T179 = *(_T178 - 4)
    _T180 = (_T136 < _T179)
    if (_T180 == 0) jump _L65
    _T181 = 0
    _T182 = (_T136 < _T181)
    if (_T182 == 0) jump _L66
_L65:
    _T183 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T183
    call _PrintString
    call _Halt
_L66:
    _T184 = 4
    _T185 = (_T136 * _T184)
    _T186 = (_T178 + _T185)
    *(_T186 + 0) = _T137
    jump _L58
_L57:
}

FUNCTION(_Deck.GetCard) {
memo '_T12:4'
_Deck.GetCard:
    _T188 = *(_T12 + 4)
    _T189 = 52
    _T190 = (_T188 >= _T189)
    if (_T190 == 0) jump _L67
    _T191 = 0
    return _T191
_L67:
    _T192 = *(_T12 + 8)
    _T193 = *(_T12 + 4)
    _T194 = *(_T192 - 4)
    _T195 = (_T193 < _T194)
    if (_T195 == 0) jump _L68
    _T196 = 0
    _T197 = (_T193 < _T196)
    if (_T197 == 0) jump _L69
_L68:
    _T198 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T198
    call _PrintString
    call _Halt
_L69:
    _T199 = 4
    _T200 = (_T193 * _T199)
    _T201 = (_T192 + _T200)
    _T202 = *(_T201 + 0)
    _T187 = _T202
    _T203 = *(_T12 + 4)
    _T204 = *(_T12 + 4)
    _T205 = 1
    _T206 = (_T204 + _T205)
    *(_T12 + 4) = _T206
    return _T187
}

FUNCTION(_BJDeck.Init) {
memo '_T17:4 _T18:8'
_BJDeck.Init:
    _T208 = *(_T17 + 4)
    _T209 = 8
    _T210 = 0
    _T211 = (_T209 < _T210)
    if (_T211 == 0) jump _L70
    _T212 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T212
    call _PrintString
    call _Halt
_L70:
    _T213 = 4
    _T214 = (_T213 * _T209)
    _T215 = (_T213 + _T214)
    parm _T215
    _T216 =  call _Alloc
    *(_T216 + 0) = _T209
    _T217 = 0
    _T216 = (_T216 + _T215)
_L71:
    _T215 = (_T215 - _T213)
    if (_T215 == 0) jump _L72
    _T216 = (_T216 - _T213)
    *(_T216 + 0) = _T217
    jump _L71
_L72:
    *(_T17 + 4) = _T216
    _T218 = 0
    _T207 = _T218
_L74:
    _T219 = 8
    _T220 = (_T207 < _T219)
    if (_T220 == 0) jump _L73
    _T221 = *(_T17 + 4)
    _T222 = *(_T221 - 4)
    _T223 = (_T207 < _T222)
    if (_T223 == 0) jump _L75
    _T224 = 0
    _T225 = (_T207 < _T224)
    if (_T225 == 0) jump _L76
_L75:
    _T226 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T226
    call _PrintString
    call _Halt
_L76:
    _T227 = 16
    parm _T227
    _T231 =  call _Alloc
    _T232 = VTBL <_Deck>
    *(_T231 + 0) = _T232
    _T233 = 4
    _T234 = (_T207 * _T233)
    _T235 = (_T221 + _T234)
    *(_T235 + 0) = _T231
    _T236 = *(_T17 + 4)
    _T237 = *(_T236 - 4)
    _T238 = (_T207 < _T237)
    if (_T238 == 0) jump _L77
    _T239 = 0
    _T240 = (_T207 < _T239)
    if (_T240 == 0) jump _L78
_L77:
    _T241 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T241
    call _PrintString
    call _Halt
_L78:
    _T242 = 4
    _T243 = (_T207 * _T242)
    _T244 = (_T236 + _T243)
    _T245 = *(_T244 + 0)
    _T246 = *(_T245 + 0)
    _T247 = *(_T246 + 0)
    parm _T245
    parm _T18
    call _T247
    _T248 = 1
    _T249 = (_T207 + _T248)
    _T207 = _T249
    jump _L74
_L73:
    _T250 = *(_T17 + 12)
    *(_T17 + 12) = _T18
}

FUNCTION(_BJDeck.DealCard) {
memo '_T19:4'
_BJDeck.DealCard:
    _T252 = 0
    _T251 = _T252
    _T253 = *(_T19 + 8)
    _T254 = 8
    _T255 = 52
    _T256 = (_T254 * _T255)
    _T257 = (_T253 >= _T256)
    if (_T257 == 0) jump _L79
    _T258 = 11
    return _T258
_L79:
_L81:
    _T259 = 0
    _T260 = (_T251 == _T259)
    if (_T260 == 0) jump _L80
    _T262 = *(_T19 + 12)
    _T263 = *(_T262 + 0)
    _T264 = *(_T263 + 8)
    _T265 = 8
    parm _T262
    parm _T265
    _T266 =  call _T264
    _T261 = _T266
    _T267 = *(_T19 + 4)
    _T268 = *(_T267 - 4)
    _T269 = (_T261 < _T268)
    if (_T269 == 0) jump _L82
    _T270 = 0
    _T271 = (_T261 < _T270)
    if (_T271 == 0) jump _L83
_L82:
    _T272 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T272
    call _PrintString
    call _Halt
_L83:
    _T273 = 4
    _T274 = (_T261 * _T273)
    _T275 = (_T267 + _T274)
    _T276 = *(_T275 + 0)
    _T277 = *(_T276 + 0)
    _T278 = *(_T277 + 8)
    parm _T276
    _T279 =  call _T278
    _T251 = _T279
    jump _L81
_L80:
    _T280 = 10
    _T281 = (_T251 > _T280)
    if (_T281 == 0) jump _L84
    _T282 = 10
    _T251 = _T282
    jump _L85
_L84:
    _T283 = 1
    _T284 = (_T251 == _T283)
    if (_T284 == 0) jump _L86
    _T285 = 11
    _T251 = _T285
_L86:
_L85:
    _T286 = *(_T19 + 8)
    _T287 = *(_T19 + 8)
    _T288 = 1
    _T289 = (_T287 + _T288)
    *(_T19 + 8) = _T289
    return _T251
}

FUNCTION(_BJDeck.Shuffle) {
memo '_T20:4'
_BJDeck.Shuffle:
    _T291 = "Shuffling..."
    parm _T291
    call _PrintString
    _T292 = 0
    _T290 = _T292
_L88:
    _T293 = 8
    _T294 = (_T290 < _T293)
    if (_T294 == 0) jump _L87
    _T295 = *(_T20 + 4)
    _T296 = *(_T295 - 4)
    _T297 = (_T290 < _T296)
    if (_T297 == 0) jump _L89
    _T298 = 0
    _T299 = (_T290 < _T298)
    if (_T299 == 0) jump _L90
_L89:
    _T300 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T300
    call _PrintString
    call _Halt
_L90:
    _T301 = 4
    _T302 = (_T290 * _T301)
    _T303 = (_T295 + _T302)
    _T304 = *(_T303 + 0)
    _T305 = *(_T304 + 0)
    _T306 = *(_T305 + 4)
    parm _T304
    call _T306
    _T307 = 1
    _T308 = (_T290 + _T307)
    _T290 = _T308
    jump _L88
_L87:
    _T309 = *(_T20 + 8)
    _T310 = 0
    *(_T20 + 8) = _T310
    _T311 = "done.\n"
    parm _T311
    call _PrintString
}

FUNCTION(_BJDeck.NumCardsRemaining) {
memo '_T21:4'
_BJDeck.NumCardsRemaining:
    _T312 = 8
    _T313 = 52
    _T314 = (_T312 * _T313)
    _T315 = *(_T21 + 8)
    _T316 = (_T314 - _T315)
    return _T316
}

FUNCTION(_Player.Init) {
memo '_T26:4 _T27:8'
_Player.Init:
    _T317 = *(_T26 + 20)
    _T318 = 1000
    *(_T26 + 20) = _T318
    _T319 = "What is the name of player #"
    _T320 = "? "
    parm _T319
    call _PrintString
    parm _T27
    call _PrintInt
    parm _T320
    call _PrintString
    _T321 = *(_T26 + 24)
    _T322 =  call _ReadLine
    *(_T26 + 24) = _T322
}

FUNCTION(_Player.Hit) {
memo '_T28:4 _T29:8'
_Player.Hit:
    _T324 = *(_T29 + 0)
    _T325 = *(_T324 + 4)
    parm _T29
    _T326 =  call _T325
    _T323 = _T326
    _T327 = *(_T28 + 24)
    _T328 = " was dealt a "
    _T329 = ".\n"
    parm _T327
    call _PrintString
    parm _T328
    call _PrintString
    parm _T323
    call _PrintInt
    parm _T329
    call _PrintString
    _T330 = *(_T28 + 4)
    _T331 = *(_T28 + 4)
    _T332 = (_T331 + _T323)
    *(_T28 + 4) = _T332
    _T333 = *(_T28 + 12)
    _T334 = *(_T28 + 12)
    _T335 = 1
    _T336 = (_T334 + _T335)
    *(_T28 + 12) = _T336
    _T337 = 11
    _T338 = (_T323 == _T337)
    if (_T338 == 0) jump _L91
    _T339 = *(_T28 + 8)
    _T340 = *(_T28 + 8)
    _T341 = 1
    _T342 = (_T340 + _T341)
    *(_T28 + 8) = _T342
_L91:
_L93:
    _T343 = *(_T28 + 4)
    _T344 = 21
    _T345 = (_T343 > _T344)
    _T346 = *(_T28 + 8)
    _T347 = 0
    _T348 = (_T346 > _T347)
    _T349 = (_T345 && _T348)
    if (_T349 == 0) jump _L92
    _T350 = *(_T28 + 4)
    _T351 = *(_T28 + 4)
    _T352 = 10
    _T353 = (_T351 - _T352)
    *(_T28 + 4) = _T353
    _T354 = *(_T28 + 8)
    _T355 = *(_T28 + 8)
    _T356 = 1
    _T357 = (_T355 - _T356)
    *(_T28 + 8) = _T357
    jump _L93
_L92:
}

FUNCTION(_Player.DoubleDown) {
memo '_T30:4 _T31:8'
_Player.DoubleDown:
    _T359 = *(_T30 + 4)
    _T360 = 10
    _T361 = (_T359 != _T360)
    _T362 = *(_T30 + 4)
    _T363 = 11
    _T364 = (_T362 != _T363)
    _T365 = (_T361 && _T364)
    if (_T365 == 0) jump _L94
    _T366 = 0
    return _T366
_L94:
    _T367 = *(_T30 + 0)
    _T368 = *(_T367 + 36)
    _T369 = "Would you like to double down?"
    parm _T30
    parm _T369
    _T370 =  call _T368
    if (_T370 == 0) jump _L95
    _T371 = *(_T30 + 16)
    _T372 = *(_T30 + 16)
    _T373 = 2
    _T374 = (_T372 * _T373)
    *(_T30 + 16) = _T374
    _T375 = *(_T30 + 0)
    _T376 = *(_T375 + 4)
    parm _T30
    parm _T31
    call _T376
    _T377 = *(_T30 + 24)
    _T378 = ", your total is "
    _T379 = *(_T30 + 4)
    _T380 = ".\n"
    parm _T377
    call _PrintString
    parm _T378
    call _PrintString
    parm _T379
    call _PrintInt
    parm _T380
    call _PrintString
    _T381 = 1
    return _T381
    jump _L96
_L95:
    _T382 = 0
    return _T382
_L96:
}

FUNCTION(_Player.TakeTurn) {
memo '_T32:4 _T33:8'
_Player.TakeTurn:
    _T384 = "\n"
    _T385 = *(_T32 + 24)
    _T386 = "'s turn.\n"
    parm _T384
    call _PrintString
    parm _T385
    call _PrintString
    parm _T386
    call _PrintString
    _T387 = *(_T32 + 4)
    _T388 = 0
    *(_T32 + 4) = _T388
    _T389 = *(_T32 + 8)
    _T390 = 0
    *(_T32 + 8) = _T390
    _T391 = *(_T32 + 12)
    _T392 = 0
    *(_T32 + 12) = _T392
    _T393 = *(_T32 + 0)
    _T394 = *(_T393 + 4)
    parm _T32
    parm _T33
    call _T394
    _T395 = *(_T32 + 0)
    _T396 = *(_T395 + 4)
    parm _T32
    parm _T33
    call _T396
    _T397 = *(_T32 + 0)
    _T398 = *(_T397 + 8)
    parm _T32
    parm _T33
    _T399 =  call _T398
    _T400 = ! _T399
    if (_T400 == 0) jump _L97
    _T401 = 1
    _T383 = _T401
_L99:
    _T402 = *(_T32 + 4)
    _T403 = 21
    _T404 = (_T402 <= _T403)
    _T405 = (_T404 && _T383)
    if (_T405 == 0) jump _L98
    _T406 = *(_T32 + 24)
    _T407 = ", your total is "
    _T408 = *(_T32 + 4)
    _T409 = ".\n"
    parm _T406
    call _PrintString
    parm _T407
    call _PrintString
    parm _T408
    call _PrintInt
    parm _T409
    call _PrintString
    _T410 = *(_T32 + 0)
    _T411 = *(_T410 + 36)
    _T412 = "Would you like a hit?"
    parm _T32
    parm _T412
    _T413 =  call _T411
    _T383 = _T413
    if (_T383 == 0) jump _L100
    _T414 = *(_T32 + 0)
    _T415 = *(_T414 + 4)
    parm _T32
    parm _T33
    call _T415
_L100:
    jump _L99
_L98:
_L97:
    _T416 = *(_T32 + 4)
    _T417 = 21
    _T418 = (_T416 > _T417)
    if (_T418 == 0) jump _L101
    _T419 = *(_T32 + 24)
    _T420 = " busts with the big "
    _T421 = *(_T32 + 4)
    _T422 = "!\n"
    parm _T419
    call _PrintString
    parm _T420
    call _PrintString
    parm _T421
    call _PrintInt
    parm _T422
    call _PrintString
    jump _L102
_L101:
    _T423 = *(_T32 + 24)
    _T424 = " stays at "
    _T425 = *(_T32 + 4)
    _T426 = ".\n"
    parm _T423
    call _PrintString
    parm _T424
    call _PrintString
    parm _T425
    call _PrintInt
    parm _T426
    call _PrintString
_L102:
}

FUNCTION(_Player.HasMoney) {
memo '_T34:4'
_Player.HasMoney:
    _T427 = *(_T34 + 20)
    _T428 = 0
    _T429 = (_T427 > _T428)
    return _T429
}

FUNCTION(_Player.PrintMoney) {
memo '_T35:4'
_Player.PrintMoney:
    _T430 = *(_T35 + 24)
    _T431 = ", you have $"
    _T432 = *(_T35 + 20)
    _T433 = ".\n"
    parm _T430
    call _PrintString
    parm _T431
    call _PrintString
    parm _T432
    call _PrintInt
    parm _T433
    call _PrintString
}

FUNCTION(_Player.PlaceBet) {
memo '_T36:4'
_Player.PlaceBet:
    _T434 = *(_T36 + 16)
    _T435 = 0
    *(_T36 + 16) = _T435
    _T436 = *(_T36 + 0)
    _T437 = *(_T436 + 20)
    parm _T36
    call _T437
_L104:
    _T438 = *(_T36 + 16)
    _T439 = 0
    _T440 = (_T438 <= _T439)
    _T441 = *(_T36 + 16)
    _T442 = *(_T36 + 20)
    _T443 = (_T441 > _T442)
    _T444 = (_T440 || _T443)
    if (_T444 == 0) jump _L103
    _T445 = "How much would you like to bet? "
    parm _T445
    call _PrintString
    _T446 = *(_T36 + 16)
    _T447 =  call _ReadInteger
    *(_T36 + 16) = _T447
    jump _L104
_L103:
}

FUNCTION(_Player.GetTotal) {
memo '_T37:4'
_Player.GetTotal:
    _T448 = *(_T37 + 4)
    return _T448
}

FUNCTION(_Player.Resolve) {
memo '_T38:4 _T39:8'
_Player.Resolve:
    _T451 = 0
    _T449 = _T451
    _T452 = 0
    _T450 = _T452
    _T453 = *(_T38 + 4)
    _T454 = 21
    _T455 = (_T453 == _T454)
    _T456 = *(_T38 + 12)
    _T457 = 2
    _T458 = (_T456 == _T457)
    _T459 = (_T455 && _T458)
    if (_T459 == 0) jump _L105
    _T460 = 2
    _T449 = _T460
    jump _L106
_L105:
    _T461 = *(_T38 + 4)
    _T462 = 21
    _T463 = (_T461 > _T462)
    if (_T463 == 0) jump _L107
    _T464 = 1
    _T450 = _T464
    jump _L108
_L107:
    _T465 = 21
    _T466 = (_T39 > _T465)
    if (_T466 == 0) jump _L109
    _T467 = 1
    _T449 = _T467
    jump _L110
_L109:
    _T468 = *(_T38 + 4)
    _T469 = (_T468 > _T39)
    if (_T469 == 0) jump _L111
    _T470 = 1
    _T449 = _T470
    jump _L112
_L111:
    _T471 = *(_T38 + 4)
    _T472 = (_T39 > _T471)
    if (_T472 == 0) jump _L113
    _T473 = 1
    _T450 = _T473
_L113:
_L112:
_L110:
_L108:
_L106:
    _T474 = 1
    _T475 = (_T449 >= _T474)
    if (_T475 == 0) jump _L114
    _T476 = *(_T38 + 24)
    _T477 = ", you won $"
    _T478 = *(_T38 + 16)
    _T479 = ".\n"
    parm _T476
    call _PrintString
    parm _T477
    call _PrintString
    parm _T478
    call _PrintInt
    parm _T479
    call _PrintString
    jump _L115
_L114:
    _T480 = 1
    _T481 = (_T450 >= _T480)
    if (_T481 == 0) jump _L116
    _T482 = *(_T38 + 24)
    _T483 = ", you lost $"
    _T484 = *(_T38 + 16)
    _T485 = ".\n"
    parm _T482
    call _PrintString
    parm _T483
    call _PrintString
    parm _T484
    call _PrintInt
    parm _T485
    call _PrintString
    jump _L117
_L116:
    _T486 = *(_T38 + 24)
    _T487 = ", you push!\n"
    parm _T486
    call _PrintString
    parm _T487
    call _PrintString
_L117:
_L115:
    _T488 = *(_T38 + 16)
    _T489 = (_T449 * _T488)
    _T449 = _T489
    _T490 = *(_T38 + 16)
    _T491 = (_T450 * _T490)
    _T450 = _T491
    _T492 = *(_T38 + 20)
    _T493 = *(_T38 + 20)
    _T494 = (_T493 + _T449)
    _T495 = (_T494 - _T450)
    *(_T38 + 20) = _T495
}

FUNCTION(_Player.GetYesOrNo) {
memo '_T40:4 _T41:8'
_Player.GetYesOrNo:
    _T496 = " (0=No/1=Yes) "
    parm _T41
    call _PrintString
    parm _T496
    call _PrintString
    _T497 =  call _ReadInteger
    _T498 = 0
    _T499 = (_T497 != _T498)
    return _T499
}

FUNCTION(_Dealer.Init) {
memo '_T50:4 _T51:8'
_Dealer.Init:
    _T501 = *(_T50 + 4)
    _T502 = 0
    *(_T50 + 4) = _T502
    _T503 = *(_T50 + 8)
    _T504 = 0
    *(_T50 + 8) = _T504
    _T505 = *(_T50 + 12)
    _T506 = 0
    *(_T50 + 12) = _T506
    _T507 = "Dealer"
    _T500 = _T507
    _T508 = *(_T50 + 24)
    *(_T50 + 24) = _T500
}

FUNCTION(_Dealer.TakeTurn) {
memo '_T52:4 _T53:8'
_Dealer.TakeTurn:
    _T509 = "\n"
    _T510 = *(_T52 + 24)
    _T511 = "'s turn.\n"
    parm _T509
    call _PrintString
    parm _T510
    call _PrintString
    parm _T511
    call _PrintString
_L119:
    _T512 = *(_T52 + 4)
    _T513 = 16
    _T514 = (_T512 <= _T513)
    if (_T514 == 0) jump _L118
    _T515 = *(_T52 + 0)
    _T516 = *(_T515 + 4)
    parm _T52
    parm _T53
    call _T516
    jump _L119
_L118:
    _T517 = *(_T52 + 4)
    _T518 = 21
    _T519 = (_T517 > _T518)
    if (_T519 == 0) jump _L120
    _T520 = *(_T52 + 24)
    _T521 = " busts with the big "
    _T522 = *(_T52 + 4)
    _T523 = "!\n"
    parm _T520
    call _PrintString
    parm _T521
    call _PrintString
    parm _T522
    call _PrintInt
    parm _T523
    call _PrintString
    jump _L121
_L120:
    _T524 = *(_T52 + 24)
    _T525 = " stays at "
    _T526 = *(_T52 + 4)
    _T527 = ".\n"
    parm _T524
    call _PrintString
    parm _T525
    call _PrintString
    parm _T526
    call _PrintInt
    parm _T527
    call _PrintString
_L121:
}

FUNCTION(_House.SetupGame) {
memo '_T62:4'
_House.SetupGame:
    _T528 = "\nWelcome to CS143 BlackJack!\n"
    parm _T528
    call _PrintString
    _T529 = "---------------------------\n"
    parm _T529
    call _PrintString
    _T531 = 8
    parm _T531
    _T535 =  call _Alloc
    _T536 = VTBL <_rndModule>
    *(_T535 + 0) = _T536
    _T530 = _T535
    _T537 = "Please enter a random number seed: "
    parm _T537
    call _PrintString
    _T538 = *(_T530 + 0)
    _T539 = *(_T538 + 0)
    _T540 =  call _ReadInteger
    parm _T530
    parm _T540
    call _T539
    _T541 = *(_T62 + 12)
    _T542 = 16
    parm _T542
    _T546 =  call _Alloc
    _T547 = VTBL <_BJDeck>
    *(_T546 + 0) = _T547
    *(_T62 + 12) = _T546
    _T548 = *(_T62 + 8)
    _T549 = 28
    parm _T549
    _T553 =  call _Alloc
    _T554 = VTBL <_Dealer>
    *(_T553 + 0) = _T554
    *(_T62 + 8) = _T553
    _T555 = *(_T62 + 12)
    _T556 = *(_T555 + 0)
    _T557 = *(_T556 + 0)
    parm _T555
    parm _T530
    call _T557
    _T558 = *(_T62 + 12)
    _T559 = *(_T558 + 0)
    _T560 = *(_T559 + 8)
    parm _T558
    call _T560
}

FUNCTION(_House.SetupPlayers) {
memo '_T63:4'
_House.SetupPlayers:
    _T563 = "How many players do we have today? "
    parm _T563
    call _PrintString
    _T564 =  call _ReadInteger
    _T562 = _T564
    _T565 = *(_T63 + 4)
    _T566 = 0
    _T567 = (_T562 < _T566)
    if (_T567 == 0) jump _L122
    _T568 = "Decaf runtime error: Cannot create negative-sized array\n"
    parm _T568
    call _PrintString
    call _Halt
_L122:
    _T569 = 4
    _T570 = (_T569 * _T562)
    _T571 = (_T569 + _T570)
    parm _T571
    _T572 =  call _Alloc
    *(_T572 + 0) = _T562
    _T573 = 0
    _T572 = (_T572 + _T571)
_L123:
    _T571 = (_T571 - _T569)
    if (_T571 == 0) jump _L124
    _T572 = (_T572 - _T569)
    *(_T572 + 0) = _T573
    jump _L123
_L124:
    *(_T63 + 4) = _T572
    _T574 = 0
    _T561 = _T574
_L126:
    _T575 = *(_T63 + 4)
    _T576 = *(_T575 - 4)
    _T577 = (_T561 < _T576)
    if (_T577 == 0) jump _L125
    _T578 = *(_T63 + 4)
    _T579 = *(_T578 - 4)
    _T580 = (_T561 < _T579)
    if (_T580 == 0) jump _L127
    _T581 = 0
    _T582 = (_T561 < _T581)
    if (_T582 == 0) jump _L128
_L127:
    _T583 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T583
    call _PrintString
    call _Halt
_L128:
    _T584 = 28
    parm _T584
    _T588 =  call _Alloc
    _T589 = VTBL <_Player>
    *(_T588 + 0) = _T589
    _T590 = 4
    _T591 = (_T561 * _T590)
    _T592 = (_T578 + _T591)
    *(_T592 + 0) = _T588
    _T593 = *(_T63 + 4)
    _T594 = *(_T593 - 4)
    _T595 = (_T561 < _T594)
    if (_T595 == 0) jump _L129
    _T596 = 0
    _T597 = (_T561 < _T596)
    if (_T597 == 0) jump _L130
_L129:
    _T598 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T598
    call _PrintString
    call _Halt
_L130:
    _T599 = 4
    _T600 = (_T561 * _T599)
    _T601 = (_T593 + _T600)
    _T602 = *(_T601 + 0)
    _T603 = *(_T602 + 0)
    _T604 = *(_T603 + 0)
    _T605 = 1
    _T606 = (_T561 + _T605)
    parm _T602
    parm _T606
    call _T604
    _T607 = 1
    _T608 = (_T561 + _T607)
    _T561 = _T608
    jump _L126
_L125:
}

FUNCTION(_House.TakeAllBets) {
memo '_T64:4'
_House.TakeAllBets:
    _T610 = "\nFirst, let's take bets.\n"
    parm _T610
    call _PrintString
    _T611 = 0
    _T609 = _T611
_L132:
    _T612 = *(_T64 + 4)
    _T613 = *(_T612 - 4)
    _T614 = (_T609 < _T613)
    if (_T614 == 0) jump _L131
    _T615 = *(_T64 + 4)
    _T616 = *(_T615 - 4)
    _T617 = (_T609 < _T616)
    if (_T617 == 0) jump _L133
    _T618 = 0
    _T619 = (_T609 < _T618)
    if (_T619 == 0) jump _L134
_L133:
    _T620 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T620
    call _PrintString
    call _Halt
_L134:
    _T621 = 4
    _T622 = (_T609 * _T621)
    _T623 = (_T615 + _T622)
    _T624 = *(_T623 + 0)
    _T625 = *(_T624 + 0)
    _T626 = *(_T625 + 16)
    parm _T624
    _T627 =  call _T626
    if (_T627 == 0) jump _L135
    _T628 = *(_T64 + 4)
    _T629 = *(_T628 - 4)
    _T630 = (_T609 < _T629)
    if (_T630 == 0) jump _L136
    _T631 = 0
    _T632 = (_T609 < _T631)
    if (_T632 == 0) jump _L137
_L136:
    _T633 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T633
    call _PrintString
    call _Halt
_L137:
    _T634 = 4
    _T635 = (_T609 * _T634)
    _T636 = (_T628 + _T635)
    _T637 = *(_T636 + 0)
    _T638 = *(_T637 + 0)
    _T639 = *(_T638 + 24)
    parm _T637
    call _T639
_L135:
    _T640 = 1
    _T641 = (_T609 + _T640)
    _T609 = _T641
    jump _L132
_L131:
}

FUNCTION(_House.TakeAllTurns) {
memo '_T65:4'
_House.TakeAllTurns:
    _T643 = 0
    _T642 = _T643
_L139:
    _T644 = *(_T65 + 4)
    _T645 = *(_T644 - 4)
    _T646 = (_T642 < _T645)
    if (_T646 == 0) jump _L138
    _T647 = *(_T65 + 4)
    _T648 = *(_T647 - 4)
    _T649 = (_T642 < _T648)
    if (_T649 == 0) jump _L140
    _T650 = 0
    _T651 = (_T642 < _T650)
    if (_T651 == 0) jump _L141
_L140:
    _T652 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T652
    call _PrintString
    call _Halt
_L141:
    _T653 = 4
    _T654 = (_T642 * _T653)
    _T655 = (_T647 + _T654)
    _T656 = *(_T655 + 0)
    _T657 = *(_T656 + 0)
    _T658 = *(_T657 + 16)
    parm _T656
    _T659 =  call _T658
    if (_T659 == 0) jump _L142
    _T660 = *(_T65 + 4)
    _T661 = *(_T660 - 4)
    _T662 = (_T642 < _T661)
    if (_T662 == 0) jump _L143
    _T663 = 0
    _T664 = (_T642 < _T663)
    if (_T664 == 0) jump _L144
_L143:
    _T665 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T665
    call _PrintString
    call _Halt
_L144:
    _T666 = 4
    _T667 = (_T642 * _T666)
    _T668 = (_T660 + _T667)
    _T669 = *(_T668 + 0)
    _T670 = *(_T669 + 0)
    _T671 = *(_T670 + 12)
    _T672 = *(_T65 + 12)
    parm _T669
    parm _T672
    call _T671
_L142:
    _T673 = 1
    _T674 = (_T642 + _T673)
    _T642 = _T674
    jump _L139
_L138:
}

FUNCTION(_House.ResolveAllPlayers) {
memo '_T66:4'
_House.ResolveAllPlayers:
    _T676 = "\nTime to resolve bets.\n"
    parm _T676
    call _PrintString
    _T677 = 0
    _T675 = _T677
_L146:
    _T678 = *(_T66 + 4)
    _T679 = *(_T678 - 4)
    _T680 = (_T675 < _T679)
    if (_T680 == 0) jump _L145
    _T681 = *(_T66 + 4)
    _T682 = *(_T681 - 4)
    _T683 = (_T675 < _T682)
    if (_T683 == 0) jump _L147
    _T684 = 0
    _T685 = (_T675 < _T684)
    if (_T685 == 0) jump _L148
_L147:
    _T686 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T686
    call _PrintString
    call _Halt
_L148:
    _T687 = 4
    _T688 = (_T675 * _T687)
    _T689 = (_T681 + _T688)
    _T690 = *(_T689 + 0)
    _T691 = *(_T690 + 0)
    _T692 = *(_T691 + 16)
    parm _T690
    _T693 =  call _T692
    if (_T693 == 0) jump _L149
    _T694 = *(_T66 + 4)
    _T695 = *(_T694 - 4)
    _T696 = (_T675 < _T695)
    if (_T696 == 0) jump _L150
    _T697 = 0
    _T698 = (_T675 < _T697)
    if (_T698 == 0) jump _L151
_L150:
    _T699 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T699
    call _PrintString
    call _Halt
_L151:
    _T700 = 4
    _T701 = (_T675 * _T700)
    _T702 = (_T694 + _T701)
    _T703 = *(_T702 + 0)
    _T704 = *(_T703 + 0)
    _T705 = *(_T704 + 32)
    _T706 = *(_T66 + 8)
    _T707 = *(_T706 + 0)
    _T708 = *(_T707 + 28)
    parm _T706
    _T709 =  call _T708
    parm _T703
    parm _T709
    call _T705
_L149:
    _T710 = 1
    _T711 = (_T675 + _T710)
    _T675 = _T711
    jump _L146
_L145:
}

FUNCTION(_House.PrintAllMoney) {
memo '_T67:4'
_House.PrintAllMoney:
    _T713 = 0
    _T712 = _T713
_L153:
    _T714 = *(_T67 + 4)
    _T715 = *(_T714 - 4)
    _T716 = (_T712 < _T715)
    if (_T716 == 0) jump _L152
    _T717 = *(_T67 + 4)
    _T718 = *(_T717 - 4)
    _T719 = (_T712 < _T718)
    if (_T719 == 0) jump _L154
    _T720 = 0
    _T721 = (_T712 < _T720)
    if (_T721 == 0) jump _L155
_L154:
    _T722 = "Decaf runtime error: Array subscript out of bounds\n"
    parm _T722
    call _PrintString
    call _Halt
_L155:
    _T723 = 4
    _T724 = (_T712 * _T723)
    _T725 = (_T717 + _T724)
    _T726 = *(_T725 + 0)
    _T727 = *(_T726 + 0)
    _T728 = *(_T727 + 20)
    parm _T726
    call _T728
    _T729 = 1
    _T730 = (_T712 + _T729)
    _T712 = _T730
    jump _L153
_L152:
}

FUNCTION(_House.PlayOneGame) {
memo '_T68:4'
_House.PlayOneGame:
    _T731 = *(_T68 + 12)
    _T732 = *(_T731 + 0)
    _T733 = *(_T732 + 12)
    parm _T731
    _T734 =  call _T733
    _T735 = 26
    _T736 = (_T734 < _T735)
    if (_T736 == 0) jump _L156
    _T737 = *(_T68 + 12)
    _T738 = *(_T737 + 0)
    _T739 = *(_T738 + 8)
    parm _T737
    call _T739
_L156:
    _T740 = *(_T68 + 0)
    _T741 = *(_T740 + 8)
    parm _T68
    call _T741
    _T742 = "\nDealer starts. "
    parm _T742
    call _PrintString
    _T743 = *(_T68 + 8)
    _T744 = *(_T743 + 0)
    _T745 = *(_T744 + 0)
    _T746 = 0
    parm _T743
    parm _T746
    call _T745
    _T747 = *(_T68 + 8)
    _T748 = *(_T747 + 0)
    _T749 = *(_T748 + 4)
    _T750 = *(_T68 + 12)
    parm _T747
    parm _T750
    call _T749
    _T751 = *(_T68 + 0)
    _T752 = *(_T751 + 12)
    parm _T68
    call _T752
    _T753 = *(_T68 + 8)
    _T754 = *(_T753 + 0)
    _T755 = *(_T754 + 12)
    _T756 = *(_T68 + 12)
    parm _T753
    parm _T756
    call _T755
    _T757 = *(_T68 + 0)
    _T758 = *(_T757 + 16)
    parm _T68
    call _T758
}

FUNCTION(main) {
memo ''
main:
    _T760 = 1
    _T759 = _T760
    _T762 = 16
    parm _T762
    _T766 =  call _Alloc
    _T767 = VTBL <_House>
    *(_T766 + 0) = _T767
    _T761 = _T766
    _T768 = *(_T761 + 0)
    _T769 = *(_T768 + 0)
    parm _T761
    call _T769
    _T770 = *(_T761 + 0)
    _T771 = *(_T770 + 4)
    parm _T761
    call _T771
_L158:
    if (_T759 == 0) jump _L157
    _T772 = *(_T761 + 0)
    _T773 = *(_T772 + 24)
    parm _T761
    call _T773
    _T774 = "\nDo you want to play another hand?"
    parm _T774
    _T775 =  call _Main.GetYesOrNo
    _T759 = _T775
    jump _L158
_L157:
    _T776 = *(_T761 + 0)
    _T777 = *(_T776 + 20)
    parm _T761
    call _T777
    _T778 = "Thank you for playing...come again soon.\n"
    parm _T778
    call _PrintString
    _T779 = "\nCS143 BlackJack Copyright (c) 1999 by Peter Mork.\n"
    parm _T779
    call _PrintString
    _T780 = "(2001 mods by jdz)\n"
    parm _T780
    call _PrintString
}

FUNCTION(_Main.GetYesOrNo) {
memo '_T73:4'
_Main.GetYesOrNo:
    _T781 = " (0=No/1=Yes) "
    parm _T73
    call _PrintString
    parm _T781
    call _PrintString
    _T782 =  call _ReadInteger
    _T783 = 0
    _T784 = (_T782 != _T783)
    return _T784
}

