function BarrettMu(e) {
    this.modulus = biCopy(e),
        this.k = biHighIndex(this.modulus) + 1;
    var t = new BigInt;
    t.digits[2 * this.k] = 1,
        this.mu = biDivide(t, this.modulus),
        this.bkplus1 = new BigInt,
        this.bkplus1.digits[this.k + 1] = 1,
        this.modulo = BarrettMu_modulo,
        this.multiplyMod = BarrettMu_multiplyMod,
        this.powMod = BarrettMu_powMod
}
function BarrettMu_modulo(e) {
    var t = biDivideByRadixPower(e, this.k - 1),
        n = biMultiply(t, this.mu),
        o = biDivideByRadixPower(n, this.k + 1),
        r = biModuloByRadixPower(e, this.k + 1),
        i = biMultiply(o, this.modulus),
        a = biModuloByRadixPower(i, this.k + 1),
        s = biSubtract(r, a);
    s.isNeg && (s = biAdd(s, this.bkplus1));
    for (var f = biCompare(s, this.modulus) >= 0; f;) s = biSubtract(s, this.modulus),
        f = biCompare(s, this.modulus) >= 0;
    return s
}
function BarrettMu_multiplyMod(e, t) {
    var n = biMultiply(e, t);
    return this.modulo(n)
}
function BarrettMu_powMod(e, t) {
    var n = new BigInt;
    n.digits[0] = 1;
    for (var o = e,
             r = t;;) {
        if (0 != (1 & r.digits[0]) && (n = this.multiplyMod(n, o)), r = biShiftRight(r, 1), 0 == r.digits[0] && 0 == biHighIndex(r)) break;
        o = this.multiplyMod(o, o)
    }
    return n
}
function setMaxDigits(e) {
    maxDigits = e,
        ZERO_ARRAY = new Array(maxDigits);
    for (var t = 0; t < ZERO_ARRAY.length; t++) ZERO_ARRAY[t] = 0;
    bigZero = new BigInt,
        bigOne = new BigInt,
        bigOne.digits[0] = 1
}
function BigInt(e) {
    "boolean" == typeof e && 1 == e ? this.digits = null: this.digits = ZERO_ARRAY.slice(0),
        this.isNeg = !1
}
function biFromDecimal(e) {
    for (var t, n = "-" == e.charAt(0), o = n ? 1 : 0; o < e.length && "0" == e.charAt(o);)++o;
    if (o == e.length) t = new BigInt;
    else {
        var r = e.length - o,
            i = r % dpl10;
        for (0 == i && (i = dpl10), t = biFromNumber(Number(e.substr(o, i))), o += i; o < e.length;) t = biAdd(biMultiply(t, lr10), biFromNumber(Number(e.substr(o, dpl10)))),
            o += dpl10;
        t.isNeg = n
    }
    return t
}
function biCopy(e) {
    var t = new BigInt((!0));
    return t.digits = e.digits.slice(0),
        t.isNeg = e.isNeg,
        t
}
function biFromNumber(e) {
    var t = new BigInt;
    t.isNeg = e < 0,
        e = Math.abs(e);
    for (var n = 0; e > 0;) t.digits[n++] = e & maxDigitVal,
        e >>= biRadixBits;
    return t
}
function reverseStr(e) {
    for (var t = "",
             n = e.length - 1; n > -1; --n) t += e.charAt(n);
    return t
}
function biToString(e, t) {
    var n = new BigInt;
    n.digits[0] = t;
    for (var o = biDivideModulo(e, n), r = hexatrigesimalToChar[o[1].digits[0]]; 1 == biCompare(o[0], bigZero);) o = biDivideModulo(o[0], n),
        digit = o[1].digits[0],
        r += hexatrigesimalToChar[o[1].digits[0]];
    return (e.isNeg ? "-": "") + reverseStr(r)
}
function biToDecimal(e) {
    var t = new BigInt;
    t.digits[0] = 10;
    for (var n = biDivideModulo(e, t), o = String(n[1].digits[0]); 1 == biCompare(n[0], bigZero);) n = biDivideModulo(n[0], t),
        o += String(n[1].digits[0]);
    return (e.isNeg ? "-": "") + reverseStr(o)
}
function digitToHex(e) {
    var t = 15,
        n = "";
    for (i = 0; i < 4; ++i) n += hexToChar[e & t],
        e >>>= 4;
    return reverseStr(n)
}
function biToHex(e) {
    for (var t = "",
             n = (biHighIndex(e), biHighIndex(e)); n > -1; --n) t += digitToHex(e.digits[n]);
    return t
}
function charToHex(e) {
    var t, n = 48,
        o = n + 9,
        r = 97,
        i = r + 25,
        a = 65,
        s = 90;
    return t = e >= n && e <= o ? e - n: e >= a && e <= s ? 10 + e - a: e >= r && e <= i ? 10 + e - r: 0
}
function hexToDigit(e) {
    for (var t = 0,
             n = Math.min(e.length, 4), o = 0; o < n; ++o) t <<= 4,
        t |= charToHex(e.charCodeAt(o));
    return t
}
function biFromHex(e) {
    for (var t = new BigInt,
             n = e.length,
             o = n,
             r = 0; o > 0; o -= 4, ++r) t.digits[r] = hexToDigit(e.substr(Math.max(o - 4, 0), Math.min(o, 4)));
    return t
}
function biFromString(e, t) {
    var n = "-" == e.charAt(0),
        o = n ? 1 : 0,
        r = new BigInt,
        i = new BigInt;
    i.digits[0] = 1;
    for (var a = e.length - 1; a >= o; a--) {
        var s = e.charCodeAt(a),
            f = charToHex(s),
            A = biMultiplyDigit(i, f);
        r = biAdd(r, A),
            i = biMultiplyDigit(i, t)
    }
    return r.isNeg = n,
        r
}
function biToBytes(e) {
    for (var t = "",
             n = biHighIndex(e); n > -1; --n) t += digitToBytes(e.digits[n]);
    return t
}
function digitToBytes(e) {
    var t = String.fromCharCode(255 & e);
    e >>>= 8;
    var n = String.fromCharCode(255 & e);
    return n + t
}
function biDump(e) {
    return (e.isNeg ? "-": "") + e.digits.join(" ")
}
function biAdd(e, t) {
    var n;
    if (e.isNeg != t.isNeg) t.isNeg = !t.isNeg,
        n = biSubtract(e, t),
        t.isNeg = !t.isNeg;
    else {
        n = new BigInt;
        for (var o, r = 0,
                 i = 0; i < e.digits.length; ++i) o = e.digits[i] + t.digits[i] + r,
            n.digits[i] = 65535 & o,
            r = Number(o >= biRadix);
        n.isNeg = e.isNeg
    }
    return n
}
function biSubtract(e, t) {
    var n;
    if (e.isNeg != t.isNeg) t.isNeg = !t.isNeg,
        n = biAdd(e, t),
        t.isNeg = !t.isNeg;
    else {
        n = new BigInt;
        var o, r;
        r = 0;
        for (var i = 0; i < e.digits.length; ++i) o = e.digits[i] - t.digits[i] + r,
            n.digits[i] = 65535 & o,
        n.digits[i] < 0 && (n.digits[i] += biRadix),
            r = 0 - Number(o < 0);
        if (r == -1) {
            r = 0;
            for (var i = 0; i < e.digits.length; ++i) o = 0 - n.digits[i] + r,
                n.digits[i] = 65535 & o,
            n.digits[i] < 0 && (n.digits[i] += biRadix),
                r = 0 - Number(o < 0);
            n.isNeg = !e.isNeg
        } else n.isNeg = e.isNeg
    }
    return n
}
function biHighIndex(e) {
    for (var t = e.digits.length - 1; t > 0 && 0 == e.digits[t];)--t;
    return t
}
function biNumBits(e) {
    var t, n = biHighIndex(e),
        o = e.digits[n],
        r = (n + 1) * bitsPerDigit;
    for (t = r; t > r - bitsPerDigit && 0 == (32768 & o); --t) o <<= 1;
    return t
}
function biMultiply(e, t) {
    for (var n, o, r, i = new BigInt,
             a = biHighIndex(e), s = biHighIndex(t), f = 0; f <= s; ++f) {
        for (n = 0, r = f, j = 0; j <= a; ++j, ++r) o = i.digits[r] + e.digits[j] * t.digits[f] + n,
            i.digits[r] = o & maxDigitVal,
            n = o >>> biRadixBits;
        i.digits[f + a + 1] = n
    }
    return i.isNeg = e.isNeg != t.isNeg,
        i
}
function biMultiplyDigit(e, t) {
    var n, o, r;
    result = new BigInt,
        n = biHighIndex(e),
        o = 0;
    for (var i = 0; i <= n; ++i) r = result.digits[i] + e.digits[i] * t + o,
        result.digits[i] = r & maxDigitVal,
        o = r >>> biRadixBits;
    return result.digits[1 + n] = o,
        result
}
function arrayCopy(e, t, n, o, r) {
    for (var i = Math.min(t + r, e.length), a = t, s = o; a < i; ++a, ++s) n[s] = e[a]
}
function biShiftLeft(e, t) {
    var n = Math.floor(t / bitsPerDigit),
        o = new BigInt;
    arrayCopy(e.digits, 0, o.digits, n, o.digits.length - n);
    for (var r = t % bitsPerDigit,
             i = bitsPerDigit - r,
             a = o.digits.length - 1,
             s = a - 1; a > 0; --a, --s) o.digits[a] = o.digits[a] << r & maxDigitVal | (o.digits[s] & highBitMasks[r]) >>> i;
    return o.digits[0] = o.digits[a] << r & maxDigitVal,
        o.isNeg = e.isNeg,
        o
}
function biShiftRight(e, t) {
    var n = Math.floor(t / bitsPerDigit),
        o = new BigInt;
    arrayCopy(e.digits, n, o.digits, 0, e.digits.length - n);
    for (var r = t % bitsPerDigit,
             i = bitsPerDigit - r,
             a = 0,
             s = a + 1; a < o.digits.length - 1; ++a, ++s) o.digits[a] = o.digits[a] >>> r | (o.digits[s] & lowBitMasks[r]) << i;
    return o.digits[o.digits.length - 1] >>>= r,
        o.isNeg = e.isNeg,
        o
}
function biMultiplyByRadixPower(e, t) {
    var n = new BigInt;
    return arrayCopy(e.digits, 0, n.digits, t, n.digits.length - t),
        n
}
function biDivideByRadixPower(e, t) {
    var n = new BigInt;
    return arrayCopy(e.digits, t, n.digits, 0, n.digits.length - t),
        n
}
function biModuloByRadixPower(e, t) {
    var n = new BigInt;
    return arrayCopy(e.digits, 0, n.digits, 0, t),
        n
}
function biCompare(e, t) {
    if (e.isNeg != t.isNeg) return 1 - 2 * Number(e.isNeg);
    for (var n = e.digits.length - 1; n >= 0; --n) if (e.digits[n] != t.digits[n]) return e.isNeg ? 1 - 2 * Number(e.digits[n] > t.digits[n]) : 1 - 2 * Number(e.digits[n] < t.digits[n]);
    return 0
}
function biDivideModulo(e, t) {
    var n, o, r = biNumBits(e),
        i = biNumBits(t),
        a = t.isNeg;
    if (r < i) return e.isNeg ? (n = biCopy(bigOne), n.isNeg = !t.isNeg, e.isNeg = !1, t.isNeg = !1, o = biSubtract(t, e), e.isNeg = !0, t.isNeg = a) : (n = new BigInt, o = biCopy(e)),
        new Array(n, o);
    n = new BigInt,
        o = e;
    for (var s = Math.ceil(i / bitsPerDigit) - 1, f = 0; t.digits[s] < biHalfRadix;) t = biShiftLeft(t, 1),
        ++f,
        ++i,
        s = Math.ceil(i / bitsPerDigit) - 1;
    o = biShiftLeft(o, f),
        r += f;
    for (var A = Math.ceil(r / bitsPerDigit) - 1, l = biMultiplyByRadixPower(t, A - s); biCompare(o, l) != -1;)++n.digits[A - s],
        o = biSubtract(o, l);
    for (var u = A; u > s; --u) {
        var c = u >= o.digits.length ? 0 : o.digits[u],
            p = u - 1 >= o.digits.length ? 0 : o.digits[u - 1],
            g = u - 2 >= o.digits.length ? 0 : o.digits[u - 2],
            d = s >= t.digits.length ? 0 : t.digits[s],
            h = s - 1 >= t.digits.length ? 0 : t.digits[s - 1];
        c == d ? n.digits[u - s - 1] = maxDigitVal: n.digits[u - s - 1] = Math.floor((c * biRadix + p) / d);
        for (var v = n.digits[u - s - 1] * (d * biRadix + h), w = c * biRadixSquared + (p * biRadix + g); v > w;)--n.digits[u - s - 1],
            v = n.digits[u - s - 1] * (d * biRadix | h),
            w = c * biRadix * biRadix + (p * biRadix + g);
        l = biMultiplyByRadixPower(t, u - s - 1),
            o = biSubtract(o, biMultiplyDigit(l, n.digits[u - s - 1])),
        o.isNeg && (o = biAdd(o, l), --n.digits[u - s - 1])
    }
    return o = biShiftRight(o, f),
        n.isNeg = e.isNeg != a,
    e.isNeg && (n = a ? biAdd(n, bigOne) : biSubtract(n, bigOne), t = biShiftRight(t, f), o = biSubtract(t, o)),
    0 == o.digits[0] && 0 == biHighIndex(o) && (o.isNeg = !1),
        new Array(n, o)
}
function biDivide(e, t) {
    return biDivideModulo(e, t)[0]
}
function biModulo(e, t) {
    return biDivideModulo(e, t)[1]
}
function biMultiplyMod(e, t, n) {
    return biModulo(biMultiply(e, t), n)
}
function biPow(e, t) {
    for (var n = bigOne,
             o = e;;) {
        if (0 != (1 & t) && (n = biMultiply(n, o)), t >>= 1, 0 == t) break;
        o = biMultiply(o, o)
    }
    return n
}
function biPowMod(e, t, n) {
    for (var o = bigOne,
             r = e,
             i = t;;) {
        if (0 != (1 & i.digits[0]) && (o = biMultiplyMod(o, r, n)), i = biShiftRight(i, 1), 0 == i.digits[0] && 0 == biHighIndex(i)) break;
        r = biMultiplyMod(r, r, n)
    }
    return o
}
function RSAKeyPair(e, t, n, o) {
    this.e = biFromHex(e),
        this.d = biFromHex(t),
        this.m = biFromHex(n),
        "number" != typeof o ? this.chunkSize = 2 * biHighIndex(this.m) : this.chunkSize = o / 8,
        this.radix = 16,
        this.barrett = new BarrettMu(this.m)
}
function encryptedString(e, t, n, o) {
    var r, i, a, s, f, A, l, u, c, p, g = new Array,
        d = t.length,
        h = "";
    for (s = "string" == typeof n ? n == RSAAPP.NoPadding ? 1 : n == RSAAPP.PKCS1Padding ? 2 : 0 : 0, f = "string" == typeof o && o == RSAAPP.RawEncoding ? 1 : 0, 1 == s ? d > e.chunkSize && (d = e.chunkSize) : 2 == s && d > e.chunkSize - 11 && (d = e.chunkSize - 11), r = 0, i = 2 == s ? d - 1 : e.chunkSize - 1; r < d;) s ? g[i] = t.charCodeAt(r) : g[r] = t.charCodeAt(r),
        r++,
        i--;
    for (1 == s && (r = 0), i = e.chunkSize - d % e.chunkSize; i > 0;) {
        if (2 == s) {
            for (A = Math.floor(256 * Math.random()); ! A;) A = Math.floor(256 * Math.random());
            g[r] = A
        } else g[r] = 0;
        r++,
            i--
    }
    for (2 == s && (g[d] = 0, g[e.chunkSize - 2] = 2, g[e.chunkSize - 1] = 0), l = g.length, r = 0; r < l; r += e.chunkSize) {
        for (u = new BigInt, i = 0, a = r; a < r + e.chunkSize; ++i) u.digits[i] = g[a++],
            u.digits[i] += g[a++] << 8;
        c = e.barrett.powMod(u, e.e),
            p = 1 == f ? biToBytes(c) : 16 == e.radix ? biToHex(c) : biToString(c, e.radix),
            h += p
    }
    return h
}
function decryptedString(e, t) {
    var n, o, r, i, a = t.split(" "),
        s = "";
    for (o = 0; o < a.length; ++o) for (i = 16 == e.radix ? biFromHex(a[o]) : biFromString(a[o], e.radix), n = e.barrett.powMod(i, e.d), r = 0; r <= biHighIndex(n); ++r) s += String.fromCharCode(255 & n.digits[r], n.digits[r] >> 8);
    return 0 == s.charCodeAt(s.length - 1) && (s = s.substring(0, s.length - 1)),
        s
}
function zCharCount(e, t) {
    t.warning || (t.warning = 0);
    var n = t.allowed - t.warning;
    n < 10 ? e.css("padding-right", "30px") : n < 100 ? e.css("padding-right", "40px") : n < 1e3 ? e.css("padding-right", "50px") : n < 1e4 && e.css("padding-right", "60px");
    var o = {
            allowed: 2e3,
            warning: 0,
            css: "count"
        },
        t = $.extend(o, t);
    e.charCount(t)
}
function zCharCount_withExceedCount(e, t) {
    var n = 1e4;
    t.exceed && (n = t.exceed),
        t.useWarningCount = !0,
        t.allowed = t.allowed + n,
        t.warning = n,
        zCharCount(e, t)
}
function writeUserInfoToCookie(e) {
    document.cookie = zui + "=" + encodeURI(e) + ";expires=1;path=/;domain=." + zRootDomain
}
function equals(e, t) {
    return e == t
}
function syncUserInfoToCookie(e) {
    var t = getUserInfoFromCookie(),
        e = JSON.stringify(e);
    equals(t, e) ? console.debug("syncUserInfoToCookie nothingtodo") : (writeUserInfoToCookie(e), console.debug("syncUserInfoToCookie needupdate"))
}
function update_logined_nav(e) {
    function t() {
        $.ajax({
            type: "GET",
            url: proMyZDomain + "/nav/getUserInfo?_t=" + n,
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(t) {
                if (!$("#user-info").length) return ! 1;
                var n = template("user-info", t);
                if (n.indexOf("{Template Error}") >= 0) {
                    $(".user-center>.login").addClass("hide"),
                        $(".user-center>.unlogin").removeClass("hide");
                    var o = serverZLog + "/error.gif?type=zc_nav_getuserinfo_TemplateError_" + (new Date).getTime(); (new Image).src = o
                } else $(".user").html(n),
                    countMessage();
                0 == t.code && (syncUserInfoToCookie(t.data), e && e())
            },
            error: function(e, t, n) {
                if (reTtime <= 10) {
                    reTtime += 1,
                        update_logined_nav();
                    var o = serverZLog + "/error.gif?type=zc_nav_getuserinfo_" + (new Date).getTime(); (new Image).src = o
                }
            }
        })
    }
    $(".user").html(""),
        $(".user-center>.login").removeClass("hide"),
        $(".user-center>.unlogin").addClass("hide"),
        $("header .user-center").removeClass("user-center-unlogin"),
        $("header .search").removeClass("search-unlogin");
    var n = (new Date).getTime();
    t(),
        getNavMsgCenterInfo(),
        document.cookie = "r_drefresh_count=0;expires=1;path=/;domain=" + zRootDomain
}
function subnavDotsupAppend(e, t, n) {
    0 == e.length && t.append(n)
}
function countMessage() {
    $.getJSON(proMyZDomain + "/messages/countmessages.do?callback=?",
        function(e) {
            e.total && subnavDotsupAppend($(".user-center .message .message-list .subnav-num-sup"), $(".user-center .message .message-list"), '<sup class="subnav-num-sup">' + e.total + "</sup>"),
                e.a12,
            (e.a14 || e.a13 || e.a15 || e.a28) && (subnavDotsupAppend($("#header-logined-user-face .subnav-dot-sup"), $("#header-logined-user-face"), '<sup class="subnav-dot-sup"></sup>'),
                subnavDotsupAppend($("#usercenter-menu-focus .subnav-dot-sup"), $("#usercenter-menu-focus"), '<sup class="subnav-dot-sup"></sup>'), subnavDotsupAppend($("#usercenter-menu-focus-left .subnav-dot-sup"), $("#usercenter-menu-focus-left"), '<sup class="subnav-dot-sup"></sup>')),
            e.a14 && subnavDotsupAppend($("#usercenter-focus-tab-activity .subnav-dot-sup"), $("#usercenter-focus-tab-activity"), '<sup class="subnav-dot-sup"></sup>'),
                e.a15 ? subnavDotsupAppend($("#usercenter-focus-tab-moment-activity .subnav-num-sup"), $("#usercenter-focus-tab-moment-activity"), '<sup class="subnav-num-sup">' + e.a15 + "</sup>") : e.a13 && subnavDotsupAppend($("#usercenter-focus-tab-moment-activity .subnav-dot-sup"), $("#usercenter-focus-tab-moment-activity"), '<sup class="subnav-dot-sup"></sup>'),
            e.a101 && (subnavDotsupAppend($("#header-logined-user-face .subnav-dot-sup"), $("#header-logined-user-face"), '<sup class="subnav-dot-sup"></sup>'), subnavDotsupAppend($("#left-data-account-focus .subnav-dot-sup"), $("#left-data-account-focus"), '<sup class="subnav-dot-sup"></sup>'), subnavDotsupAppend($("#usercenter-menu-account-focus .subnav-dot-sup"), $("#usercenter-menu-account-focus"), '<sup class="subnav-dot-sup"></sup>'), subnavDotsupAppend($("#profile-preferences-focus .subnav-dot-sup"), $("#profile-preferences-focus"), '<sup class="subnav-dot-sup"></sup>')),
            e.a28 && subnavDotsupAppend($("#usercenter-focus-tab-fav-folder .subnav-dot-sup"), $("#usercenter-focus-tab-fav-folder"), '<sup class="subnav-dot-sup"></sup>')
        })
}
function getNavMsgCenterInfo() {
    function e(json) {
        var t = "";
        return $.each(json,
            function(i, n) {
                var o = "";
                1 == n.type ? o = messagesWeb.nav_message_typeStr1: 2 == n.type ? o = messagesWeb.nav_message_typeStr2: 3 == n.type || 4 == n.type ? o = messagesWeb.nav_message_typeStr3: 5 == n.type ? o = messagesWeb.nav_message_typeStr5: 6 == n.type ? o = messagesWeb.nav_message_typeStr6: 8 == n.type ? o = messagesWeb.nav_message_typeStr8: 9 == n.type ? o = messagesWeb.nav_message_typeStr9: 11 == n.type && (o = messagesWeb.nav_message_typeStr11);
                var r = "";
                n.num > 1 && (r = '<span class="msg-box-num">' + n.num + "</span>");
                var i = "";
                0 == n.num && (i = 'class="readed"');
                var a = "";
                a = n.num > 1 ? 'class="news-circle"': 'class="no-news-circle"',
                    t += "<li " + a + "><a " + i + 'href="' + proMyZDomain + n.pageUrl + '"><b>[' + o + "]</b> " + n.msg + r + "</a></li>"
            }),
            t
    }
    var t = (new Date).getTime();
    $.ajax({
        type: "GET",
        url: proMyZDomain + "/nav/getMsgBox.json?_t=" + t,
        xhrFields: {
            withCredentials: !0
        },
        crossDomain: !0,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(t) {
            0 == t.code && t.data.length > 0 ? ($(".message-box-list").append(e(t.data)), $(".message-box-list").mCustomScrollbar({
                theme: "dark",
                scrollButtons: {
                    scrollSpeed: 100,
                    enable: !0
                },
                autoHideScrollbar: !0,
                scrollInertia: 100,
                horizontalScroll: !1,
                autoDraggerLength: !0,
                callbacks: {
                    onScrollStart: function() {
                        $(".mCS-autoHide .mCustomScrollBox .mCSB_scrollTools").css({
                            opacity: "1",
                            "-webkit-animation": "none"
                        })
                    },
                    whileScrolling: function() {
                        $(".mCS-autoHide .mCustomScrollBox .mCSB_scrollTools").css({
                            opacity: "1",
                            "-webkit-animation": "none"
                        })
                    },
                    onScroll: function() {
                        $(".mCS-autoHide .mCustomScrollBox .mCSB_scrollTools").removeAttr("style")
                    }
                }
            })) : ($("#contetn-2").append($("#empty_message_box")), $("#clean-box-msg").hide())
        }
    })
}
function uploadPositionTips() {
    var e = '<div class="community-upicon-location-prompt"><div class="community-tag-clip"></div><div class="upicon-location-tips">从这里发布你的创作 <i class="upicon-close-prompt closebtn"></i></div></div>';
    $(".community-upicon-location-prompt").length || getCookieKey("up_location_prompt") || $("#body .user-center .js-header_upload").append(e),
        setCookies_withDomain("up_location_prompt", "1", zRootDomain),
        $(".upicon-close-prompt").on("click",
            function() {
                $(".community-upicon-location-prompt").fadeOut("fast",
                    function() {
                        $(this).remove()
                    })
            })
}
function unloginedUpdateStatus() {
    $(".author-info-card .attention").each(function(e, t) {
        settingFollow(0, $(t))
    }),
    ZunloginedUpdateStatusCbs.length && $.each(ZunloginedUpdateStatusCbs,
        function(e, t) {
            t()
        })
}
function logout_update_unlogined_nav() {
    $(".user-center>.login").addClass("hide"),
        $(".user-center>.unlogin").removeClass("hide"),
        $("header .user-center").addClass("user-center-unlogin"),
        $("header .search").addClass("search-unlogin"),
        $(".js-header_upload a").attr("href", "javascript:;"),
        unloginedUpdateStatus()
}
function update_unlogined_nav() {
    logout_update_unlogined_nav(),
        setTimeout(function() {
                window.location.reload()
            },
            800)
}
function getUserInfoFromCookie() {
    return getCookieKey("zui")
}
function getZid() {
    for (var e = document.cookie.split("; "), t = "0", n = 0; n < e.length; n++) {
        var o = e[n].split("="),
            r = o[0],
            i = o[1];
        if ("zid" == r) {
            t = i;
            break
        }
    }
    return t
}
function getUid() {
    for (var e = document.cookie.split("; "), t = "0", n = 0; n < e.length; n++) {
        var o = e[n].split("="),
            r = o[0],
            i = o[1];
        if ("zcool_logon_new" == r) {
            var a = decodeURI(i),
                s = a.split("|"),
                f = s[0];
            t = f;
            break
        }
    }
    return t
}
function islogin() {
    return getUid() > 0
}
function getUGender() {
    if (null != getUserInfoFromCookie()) return JSON.parse(getUserInfoFromCookie()).memberGender
}
function getUProfession() {
    if (null != getUserInfoFromCookie()) return JSON.parse(getUserInfoFromCookie()).memberProfession
}
function getUserInformationFromCookie() {
    if (null != getUserInfoFromCookie()) return JSON.parse(getUserInfoFromCookie())
}
function asyncRequestIsExistPhone(e, t) {
    $.ajax({
        type: "GET",
        url: proMyZDomain + "/nav/isExistMailOrPhone",
        data: {},
        xhrFields: {
            withCredentials: !0
        },
        crossDomain: !0,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(t) {
            e(t)
        },
        error: function() {
            t && t()
        }
    })
}
function publishedbindLayerShow() {
    '<p class="remind-bind-copy">' + messagesWeb.unbindPhoneOrEmail_Pop2 + "</p>";
    $(".pop-up").hide(),
    $(".shade").is(":visible") || showGlobalMaskLayer(),
            zcoolAlert("为确保您账户的安全及正常使用，依《网络安全法》相关要求，2017年6月1日起会员发布信息需绑定手机。",
            function() {
                hideGlobalMaskLayer(),
                    window.open(proPassportZDomain + "/modifyphone_view.do?type=1&appId=" + appId),
                    $(".alert-confirm .pop-confirm").val("我知道了")
            },
            function() {
                hideGlobalMaskLayer(),
                    $(".alert-confirm .pop-confirm").val("我知道了")
            }),
        $(".alert-confirm").addClass("remind-bind-pop"),
        $(".alert-confirm .pop-confirm").val("去绑定")
}
function showRemindBindLayer(e, t) {
    getUid() > 0 ? remindBindFlag && (remindBindFlag = !1, asyncRequestIsExistPhone(function(t) {
            0 == t.data ? publishedbindLayerShow() : e && e(),
                remindBindFlag = !0
        },
        function() {
            remindBindFlag = !0
        })) : (cb = function() {
        "function" == typeof t && t()
    },
        doLoginWindow(cb))
}
function pageToastSuccess(e, t) {
    $(".toast-success").find(".toast-tips-text .toast-first-text").html(e),
    t && $(".toast-tips-text .toast-socend-text").html(t),
        $(".toast-success").addClass("scale"),
        setTimeout(function() {
                $(".toast-success").removeClass("scale")
            },
            1e3)
}
function pageToastFail(e, t) {
    $(".toast-fail").find(".toast-tips-text .toast-first-text").html(e),
    t && $(".toast-tips-text .toast-socend-text").html(t),
        $(".toast-fail").addClass("scale"),
        setTimeout(function() {
                $(".toast-fail").removeClass("scale")
            },
            1e3)
}
function hideConfirmMaskLayer() {
    $("html").find(".confirm-shade").remove(),
        $("html").removeClass("confirm-body-fixed"),
    isWindows() && !$(".shade:not([data-discriminate])").is(":visible") && $("html").removeClass("scroll-fixed")
}
function showConfirmMaskLayer() {
    $("html").append($('<div class="confirm-shade shade" data-discriminate="second"></div>')),
        $("html").addClass("confirm-body-fixed"),
    isWindows() && $("html").addClass("scroll-fixed")
}
function pageConfirm(e, t, n, o) {
    showConfirmMaskLayer();
    var r = $(".pop-up-confirm");
    $(".pop-up-confirm").removeClass("hide").find(".tips-text").html(e),
        $(".pop-up-confirm").css("margin-top", -(r.innerHeight() / 2)),
        $(".pop-up-confirm .pop-confirm").unbind(),
        $(".pop-up-confirm .pop-confirm").on("click",
            function() {
                $(".pop-up-confirm").addClass("hide"),
                    hideConfirmMaskLayer(),
                n && n()
            }),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").unbind(),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").on("click",
            function() {
                $(".pop-up-confirm").addClass("hide"),
                    hideConfirmMaskLayer(),
                o && o()
            })
}
function pageConfirmSeconds(e, t, n, o) {
    showConfirmMaskLayer(),
        $(".pop-up-confirm").removeClass("hide").find(".tips-text").html(e),
        $(".pop-up-confirm .pop-confirm").unbind(),
        $(".pop-up-confirm .pop-confirm").on("click",
            function() {
                n && (loaddingBtnDis($(this)), n())
            }),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").unbind(),
        $(".pop-up-confirm .pop-cancel,.pop-up-confirm .pop-close").on("click",
            function() {
                $(".pop-up-confirm").addClass("hide"),
                    hideConfirmMaskLayer(),
                    cancelLoaddingBtnDis($(".pop-up-confirm .pop-confirm")),
                o && o()
            })
}
function zcoolAlert(e, t, n) {
    $(".alert-confirm").removeClass("hide"),
        $(".alert-confirm .tips-alert-text").html(e);
    var o = $(".alert-confirm").width() / 2,
        r = $(".alert-confirm").innerHeight() / 2;
    $(".alert-confirm").css({
        "margin-left": -o,
        "margin-top": -r
    }),
        $(".alert-confirm .pop-confirm").on("click",
            function() {
                $(this).parents(".alert-confirm").addClass("hide"),
                    $(this).unbind("click"),
                t && t()
            }),
        $(".alert-confirm .pop-close").on("click",
            function() {
                $(this).parents(".alert-confirm").addClass("hide"),
                    $(this).unbind("click"),
                n && n()
            })
}
function hideGlobalMaskLayer() {
    $(".js-seconds-shade").is(":visible") ? $(".js-seconds-shade").remove() : ($(".shade").hide().removeClass("project-view"), $("html").removeClass("body-fixed"), $(".mask-layer").addClass("hide"), isWindows() && $("html").removeClass("scroll-fixed"))
}
function showGlobalMaskLayer() {
    $(".shade").is(":visible") ? $("html").append($('<div class="js-seconds-shade"></div>')) : ($(".shade").show().addClass("project-view"), $("html").addClass("body-fixed"), isWindows() && $("html").addClass("scroll-fixed"))
}
function checkThis(e) {
    $(e).removeClass("check-c").addClass("check-cd"),
        $(e).find("input").prop("checked", !0)
}
function halfCheckThis(e) {
    unCheckThis(e)
}
function unCheckThis(e) {
    $(e).addClass("check-c").removeClass("check-cd"),
        $(e).find("input").prop("checked", !1)
}
function hasCheckedThis(e) {
    return $(e).find("input").prop("checked")
}
function checkboxChecked(e) {
    e.find("input[type='checkbox']").parent().removeClass("check-cd").addClass("check-c"),
        e.find("input[type='checkbox']:checked").parent().removeClass("check-c").addClass("check-cd")
}
function navTypeTopFix() {
    var e = $(document),
        t = $(".subnav-content-wrap"),
        n = t.position().top;
    t.height();
    $(".subnav-content-wrap").css("height", t.height()),
        window.onresize = function() {
            n = t.position().top
        },
        $(window).on("scroll",
            function(t) {
                var o = document.body || document.documentElement && document.documentElement,
                    r = window.pageXOffset || o.scrollLeft,
                    i = window.pageYOffset || o.scrollTop;
                i >= 0 && (0 == $("#confighome").is(":visible") ? e.scrollTop() >= n ? ($(".subnav-wrap").addClass("tab-nav-fixed"), $(".temporary-entrance").fadeOut("fast")) : ($(".subnav-wrap").removeClass("tab-nav-fixed"), $(".temporary-entrance").fadeIn("fast")) : e.scrollTop() >= n + $("#confighome").height() ? $(".subnav-wrap").addClass("tab-nav-fixed") : $(".subnav-wrap").removeClass("tab-nav-fixed")),
                r >= 0 && $(".tab-nav-fixed").css("left", -window.pageXOffset)
            })
}
function asyncAttentionCall(e) {
    e.on("click", ".attention",
        function() {
            attentionClickAfterCaozuo($(this))
        })
}
function settingFollow(e, t) {
    1 == e ? (t.addClass("btn-default-secondary").addClass("following").removeClass("notfollow").removeClass("btn-default-main"), t.val(messagesWeb.common_followed), t.attr("title", messagesWeb.common_followed_title)) :
        2 == e ? (t.addClass("btn-default-secondary").removeClass("following").removeClass("notfollow").removeClass("btn-default-main"), t.val(messagesWeb.common_mutual_concern), t.attr("title", messagesWeb.common_mutual_concern_title)) :
            3 == e || 0 == e ? (t.removeClass("btn-default-secondary").addClass("btn-default-main").addClass("notfollow").removeClass("following"), t.val(messagesWeb.common_follow), t.attr("title", messagesWeb.common_follow_title)) :
                (alert("error2"), pageToastFail(messagesWeb.common_no_state_concern)),
        cancelLoadingFocusDis(t)
}
function doLoginWindow(e) {
    var t = e,
        n = [t];
    openLoginWindow(n)
}
function prepareSendMessage(e, t) {
    function n() {
        var n = new PrivatePOP({
            memberId: e,
            memberName: t
        });
        n.openPrivateWindow()
    }
    1 == islogin() ? n() : doLoginWindow(n)
}
function loadimg(e, t, n, o) {
    function r(r, a) {
        var f = new Image;
        f.onload = function() {
            i++,
            t && t(i, e.length, r, a),
            n && i == e.length && n(s)
        },
            f.onerror = function() {
                i++,
                    s++,
                o && o(i, e.length, r, a)
            },
            f.src = r
    }
    var i = 0,
        s = 0,
        f = "[object Object]" === Object.prototype.toString.call(e),
        e = f ? e.get() : e;
    for (a in e) {
        var A = f ? $(e[a]).attr("data-src") : e[a];
        r(A, e[a])
    }
}
function funloading_obj(e, t, n, o) {
    o.src = n
}
function inputKeyDownBtnUseable(e, t, n) {
    $("body").on("change keyup paste", e,
        function() {
            $(this);
            "" != e.val() && e.val().length >= n ? (t.addClass("btn-default-main").removeClass("btn-disabled").removeAttr("disabled"), e.next(".count").length && (e.next(".count").text() < 0 ? t.removeClass("btn-default-main").addClass("btn-disabled").attr("disabled", !0) : t.addClass("btn-default-main").removeClass("btn-disabled").removeAttr("disabled"))) : t.removeClass("btn-default-main").addClass("btn-disabled").attr("disabled", !0)
        })
}
function loadingFocusDis(e) {
    return !! e.val() && (e.attr({
        disabled: !0,
        "data-val": e.val()
    }), e.val(""), void(e.hasClass("btn-default-main") ? e.parents(".js-project-focus-btn,.js-c-loading-cover").find(".js-focus-loadding-btn").addClass("focus-loadding-y").removeClass("focus-loadding-s hide") : e.parents(".js-project-focus-btn,.js-c-loading-cover").find(".js-focus-loadding-btn").addClass("focus-loadding-s").removeClass("focus-loadding-y hide")))
}
function cancelLoadingFocusDis(e) {
    e.removeAttr("disabled"),
        e.parents(".js-project-focus-btn,.js-c-loading-cover").find(".js-focus-loadding-btn").addClass("hide")
}
function btnGifLoaddingShow(e) {
    e.attr({
        disabled: !0,
        "data-val": e.val()
    }),
        e.val(""),
        e.hasClass("btn-default-main") ? e.parents(".js-project-focus-btn,.js-c-loading-cover").find(".js-focus-loadding-btn").addClass("focus-loadding-y").removeClass("focus-loadding-s hide") : e.parents(".js-project-focus-btn,.js-c-loading-cover").find(".js-focus-loadding-btn").addClass("focus-loadding-s").removeClass("focus-loadding-y hide")
}
function btnGifLoaddingHide(e) {
    e.attr("data-val") && e.val(e.attr("data-val")),
        e.removeAttr("disabled"),
        e.parents(".js-project-focus-btn,.js-c-loading-cover").find(".js-focus-loadding-btn").addClass("hide")
}
function loaddingDomAdd(e) {
    $.each($(e),
        function(e, t) {
            0 == $(t).find(".js-focus-loadding-btn").length && $(t).append(zcoolFocusLoadingTpl)
        })
}
function loaddingBtnDis(e) {
    e.attr({
        disabled: !0,
        "data-val": e.val()
    }),
        e.val(messagesWeb.common_submitting).addClass("btn-disabled").removeClass("btn-default-main")
}
function cancelLoaddingBtnDis(e) {
    e.attr("data-val") && e.val(e.attr("data-val")),
        e.removeAttr("disabled").removeClass("btn-disabled").addClass("btn-default-main")
}
function pDefaultHFixed(e, t) {
    var n;
    $(window).on("scroll",
        function() {
            var o = document.body || document.documentElement && document.documentElement,
                r = window.pageXOffset || o.scrollLeft;
            window.pageYOffset || o.scrollTop;
            e() ? (t.addClass("js-H-fixed-show"), n = setTimeout(function() {
                    $(".js-H-fixed-show").css("overflow", "visible")
                },
                1e3)) : (t.removeClass("js-H-fixed-show"), t.removeAttr("style"), clearTimeout(n)),
            r >= 0 && $(".js-H-fixed-show").css("left", -window.pageXOffset)
        })
}
function getCookieKey(e) {
    var t, n = new RegExp("(^| )" + e + "=([^;]*)(;|$)");
    return (t = document.cookie.match(n)) ? decodeURI(t[2]) : null
}
function setCookies_withDomain(e, t, n) {
    var o = new Date;
    o.setTime(o.getTime() + 2592e6),
        document.cookie = e + "=" + escape(t) + ";expires=" + o.toGMTString() + ";path=/;domain=" + (n || zRootDomain)
}
function z_delCookie(e) {
    var t = new Date;
    t.setTime(t.getTime() - 1),
    null !== getCookieKey(e) && (document.cookie = e + getCookieKey(e) + ";expires=" + t.toGMTString() + ";path=/;domain=." + zRootDomain)
}
function zcustomScrollbarFadeout(e) {
    e.mCustomScrollbar({
        theme: "dark",
        scrollButtons: {
            scrollSpeed: 100,
            enable: !0
        },
        autoHideScrollbar: !0,
        scrollInertia: 100,
        horizontalScroll: !1,
        autoDraggerLength: !0,
        callbacks: {
            onScrollStart: function() {
                $(".mCS-autoHide .mCustomScrollBox .mCSB_scrollTools").css({
                    opacity: "1",
                    "-webkit-animation": "none"
                })
            },
            whileScrolling: function() {
                $(".mCS-autoHide .mCustomScrollBox .mCSB_scrollTools").css({
                    opacity: "1",
                    "-webkit-animation": "none"
                })
            },
            onScroll: function() {
                $(".mCS-autoHide .mCustomScrollBox .mCSB_scrollTools").removeAttr("style")
            }
        }
    })
}
function setCityDefault(e, t) {
    0 == e.val() || "undefined" == e.val() ? (e.val("1"), t.attr("value", "47")) : 0 != t.attr("value") && "undefined" != e.attr("value") || (e.val("1"), t.attr("value", "47")),
        e.parents(".city-box").find("a[data-code=" + e.val() + "]").click(),
        t.parents(".city-box").find("a[data-code=" + t.attr("value") + "]").click()
}
function popFirstInputFocus(e) {
    var t = e.val();
    e.focus(),
        e.val(""),
        e.val(t)
}
function radioCheck(e) {
    e.parents(".radio").find("input[name=radio]").parent().removeClass().addClass("radio-1"),
        e.parents(".radio").find("input[name=radio]:checked").parent().removeClass().addClass("radio-0")
}
function bindCloseTplPopEv() {
    $("body .js-tpl-pop").on("click", ".pop-close,.pop-cancel",
        function() {
            $(this).parents(".js-tpl-pop").hide(),
                hideGlobalMaskLayer()
        })
}
function relatedLikeAsync() {
    function e(e) {
        var t = template("related-recommend-tpl", e);
        $("#related-like").html(t),
            $("#related-like").showCardInfo(),
            asyncAttentionCall($("#related-like"))
    }
    $("#dataInput").attr("data-objid"),
        $("#dataInput").attr("data-objtype");
    $.ajax({
        type: "GET",
        url: proMainZDomain + "/show/recommend",
        xhrFields: {
            withCredentials: !0
        },
        crossDomain: !0,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(t) {
            0 == t.code && 0 != t.data.length && (t.data.slice(0, 4), e(t))
        },
        error: function() {
            console.error("error:")
        }
    })
}
function getUsername() {
    return getUserInformationFromCookie().username
}
function getUPageurl() {
    return getUserInformationFromCookie().pageUrl
}
function getUAvatar() {
    return getUserInformationFromCookie().avatar
}
function AEleUnbindPhoneTransfer(e) {
    function t() {
        window.location.href = e
    }
    showRemindBindLayer(t)
}
function loginUploadEditEntryLocation(e) {
    function t() {
        AEleUnbindPhoneTransfer(e)
    }
    if (islogin()) t();
    else {
        var n = new Array;
        n.push(t),
            openLoginWindow(n)
    }
}
function nullStatusBtnUnbindPhone() {
    $(".js-null-unbind-phone").on("click",
        function() {
            AEleUnbindPhoneTransfer($(this).attr("data-url"))
        })
}
function determineWhetherBind(e) {
    $.ajax({
        type: "GET",
        url: proMyZDomain + "/setting/wxMpInfo",
        xhrFields: {
            withCredentials: !0
        },
        crossDomain: !0,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(t) {
            if (0 == t.code) {
                JSON.stringify(t.data);
                e(t)
            }
        }
    })
}
function interAjax(e) {
    $.ajax({
        type: "GET",
        url: proMyZDomain + "/setting/wxMpInfoDelay",
        xhrFields: {
            withCredentials: !0
        },
        crossDomain: !0,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(t) {
            e(t)
        }
    })
}
function Post(e, t) {
    var n = document.createElement("form");
    n.action = e,
        n.target = "_self",
        n.method = "post",
        n.style.display = "none";
    for (var o in t) {
        var r = document.createElement("textarea");
        r.name = t[o].name,
            r.value = t[o].value,
            n.appendChild(r)
    }
    document.body.appendChild(n),
        n.submit()
}
function Popup(e, t, n, o, r, i, a, s, f) {
    console.log(s),
        this.tit = e,
        this.content = t,
        this.richText = o,
        this.component = n,
        this.btnInfo1 = r,
        this.btnInfo2 = i,
        f ? this.popupWidth = f: this.popupWidth = 440,
        this.methis = a,
        this.fn = function(e) {
            "function" == typeof s && s(e)
        }
}
function Tip(e) {
    this.content = e
}
function selectRefresh(e, t) {
    for (var n = ($(".filter-wrap .select-box>li"), 0); n < $(".filter-wrap").length; n++) for (var o = $(".filter-wrap").eq(n).find(".select-box>li"), r = 0; r < o.length; r++) {
        if (e && o.eq(r).attr("data-sign") == e) {
            console.log(e);
            var i = o.eq(r),
                a = $(".filter-wrap").eq(n).find(".filter-selected>span");
            i.addClass("active").siblings().removeClass("active"),
                a.html(i.html());
            break
        }
        if (t && t && o.eq(r).attr("data-sign") == t) {
            var i = o.eq(r),
                a = $(".filter-wrap").eq(n).find(".filter-selected>span");
            i.addClass("active").siblings().removeClass("active"),
                a.html(i.html());
            break
        }
    }
}
function selectFilter() {
    $(".filter-wrap .filter-selected").on("mouseover",
        function() {
            $(this).find(".select-bigwrap").show(),
                $(this).parents(".filter-wrap").siblings(".filter-wrap").find(".select-bigwrap").hide()
        }),
        $(".filter-wrap .filter-selected").on("mouseout",
            function() {
                $(".filter-wrap").find(".select-bigwrap").hide()
            }),
        $(".select-bigwrap .select-box>li").on("click",
            function() {
                var e = $(this).find("a").html();
                $(this).parents(".filter-selected").find("span").html(e),
                    $(this).addClass("active").siblings().removeClass("active"),
                    $(this).parents(".select-bigwrap").hide()
            })
}
function ShowInfo(e, t, n) {
    this.domParent = e,
        this.domChildren = t,
        this.domStr = n
}
function openLoginWindow(e) {
    $(".pop-login").length || $('<div class="pop-login hide" id="popLogin"><iframe allowTransparency="true" id="loginChild" width="400" height="500" name="loginChild" scrolling="no" src="' + proPassportZDomain + "/login.do?loginType=1&appId=" + appId + '" style="background-color:transparent" marginwidth="0" frameborder="0" width="100%" height="100%"></iframe></div>').appendTo("body"),
        $(".pop-login").show(),
        showGlobalMaskLayer(),
    $(".login-close").length || $(".pop-login").append('<div class="login-close"></div>'),
        $("#popLogin").off("click"),
        $(".pop-login").on("click", ".login-close",
            function() {
                $(".pop-login").hide(),
                    hideGlobalMaskLayer()
            }),
        window.zcoolLoginSuccessUpdatePageCb = e
}
function initLoginSuccessPage() {
    var e, t = window.zcoolLoginSuccessUpdatePageCb;
    t ? (e = function() {
        hideGlobalMaskLayer(),
            $(".pop-login").hide(),
            update_logined_nav(),
            $.each(t,
                function(e, t) {
                    t()
                });
        var e = $(".upload a").attr("data-click-login");
        e && "1" === e && $(".upload a").attr("data-click-login", "0")
    })() : (hideGlobalMaskLayer(), $(".pop-login").hide(), update_logined_nav())
}
function getPassportEC() {
    $z && $z.getServerCookie(ZProtocol + passportDomain,
        function(e) {
            null != e.passportServerEc && e.passportServerEc.length > 0 && $.getJSON(proMyZDomain + "/login_cb?isajax=true&TOKEN=" + e.passportServerEc + "&jsonpCallback=?",
                function(e) {
                    e && 0 === e.code && "true" === e.success && initLoginSuccessPage()
                })
        })
}
function MaxYou(e, t) { !
    function(e) {
        e.fn.autoTextarea = function(t) {
            var n = {
                    maxHeight: null,
                    minHeight: e(this).height()
                },
                o = e.extend({},
                    n, t);
            return e(this).each(function() {
                e(this).bind("paste cut keydown keyup focus blur",
                    function() {
                        var e, t = this.style;
                        this.style.height = o.minHeight + "px",
                        this.scrollHeight > o.minHeight && (o.maxHeight && this.scrollHeight > o.maxHeight ? (e = o.maxHeight, t.overflowY = "scroll") : (e = this.scrollHeight, t.overflowY = "hidden"), t.height = e + "px")
                    })
            })
        }
    } (jQuery),
    $(e).autoTextarea({
        maxHeight: 130,
        minHeight: t || 42
    })
}
function MaxMe(e, t) {
    MaxYou(e, t)
}
function createFollow(e, t, n, o, r) {
    this.objectId = e,
        this.objectType = t,
        this.succesCb = n,
        this.errorCb = o,
        this.isUnFollowStatus = r
}
function getFocusStatus(e, t, n, o) {
    $.ajax({
        type: "GET",
        url: proMyZDomain + "/follow/getFollowStatus.json",
        data: {
            objType: t,
            objId: e
        },
        xhrFields: {
            withCredentials: !0
        },
        crossDomain: !0,
        headers: {
            "X-Requested-With": "XMLHttpRequest"
        },
        dataType: "json",
        success: function(e) {
            0 == e.code && ("function" == typeof o ? o(e.data, n) : settingFollow(e.data, n))
        }
    })
}
function PrivatePOP(e) {
    this.memberId = e.memberId,
        this.memberName = e.memberName,
        this.getUserListCallback = e.getUserListCallback,
        this.sendSuccessCallback = e.sendSuccessCallback
}
/*! jQuery v1.10.2 | (c) 2005, 2013 jQuery Foundation, Inc. | jquery.org/license
//@ sourceMappingURL=jquery-1.10.2.min.map
*/
!
    function(e, t) {
        function n(e) {
            var t = e.length,
                n = le.type(e);
            return ! le.isWindow(e) && (!(1 !== e.nodeType || !t) || ("array" === n || "function" !== n && (0 === t || "number" == typeof t && t > 0 && t - 1 in e)))
        }
        function o(e) {
            var t = Ne[e] = {};
            return le.each(e.match(ce) || [],
                function(e, n) {
                    t[n] = !0
                }),
                t
        }
        function r(e, n, o, r) {
            if (le.acceptData(e)) {
                var i, a, s = le.expando,
                    f = e.nodeType,
                    A = f ? le.cache: e,
                    l = f ? e[s] : e[s] && s;
                if (l && A[l] && (r || A[l].data) || o !== t || "string" != typeof n) return l || (l = f ? e[s] = te.pop() || le.guid++:s),
                A[l] || (A[l] = f ? {}: {
                    toJSON: le.noop
                }),
                ("object" == typeof n || "function" == typeof n) && (r ? A[l] = le.extend(A[l], n) : A[l].data = le.extend(A[l].data, n)),
                    a = A[l],
                r || (a.data || (a.data = {}), a = a.data),
                o !== t && (a[le.camelCase(n)] = o),
                    "string" == typeof n ? (i = a[n], null == i && (i = a[le.camelCase(n)])) : i = a,
                    i
            }
        }
        function i(e, t, n) {
            if (le.acceptData(e)) {
                var o, r, i = e.nodeType,
                    a = i ? le.cache: e,
                    f = i ? e[le.expando] : le.expando;
                if (a[f]) {
                    if (t && (o = n ? a[f] : a[f].data)) {
                        le.isArray(t) ? t = t.concat(le.map(t, le.camelCase)) : t in o ? t = [t] : (t = le.camelCase(t), t = t in o ? [t] : t.split(" ")),
                            r = t.length;
                        for (; r--;) delete o[t[r]];
                        if (n ? !s(o) : !le.isEmptyObject(o)) return
                    } (n || (delete a[f].data, s(a[f]))) && (i ? le.cleanData([e], !0) : le.support.deleteExpando || a != a.window ? delete a[f] : a[f] = null)
                }
            }
        }
        function a(e, n, o) {
            if (o === t && 1 === e.nodeType) {
                var r = "data-" + n.replace(Pe, "-$1").toLowerCase();
                if (o = e.getAttribute(r), "string" == typeof o) {
                    try {
                        o = "true" === o || "false" !== o && ("null" === o ? null: +o + "" === o ? +o: Ce.test(o) ? le.parseJSON(o) : o)
                    } catch(i) {}
                    le.data(e, n, o)
                } else o = t
            }
            return o
        }
        function s(e) {
            var t;
            for (t in e) if (("data" !== t || !le.isEmptyObject(e[t])) && "toJSON" !== t) return ! 1;
            return ! 0
        }
        function f() {
            return ! 0
        }
        function A() {
            return ! 1
        }
        function l() {
            try {
                return J.activeElement
            } catch(e) {}
        }
        function u(e, t) {
            do e = e[t];
            while (e && 1 !== e.nodeType);
            return e
        }
        function c(e, t, n) {
            if (le.isFunction(t)) return le.grep(e,
                function(e, o) {
                    return !! t.call(e, o, e) !== n
                });
            if (t.nodeType) return le.grep(e,
                function(e) {
                    return e === t !== n
                });
            if ("string" == typeof t) {
                if (ke.test(t)) return le.filter(t, e, n);
                t = le.filter(t, e)
            }
            return le.grep(e,
                function(e) {
                    return le.inArray(e, t) >= 0 !== n
                })
        }
        function p(e) {
            var t = Ge.split("|"),
                n = e.createDocumentFragment();
            if (n.createElement) for (; t.length;) n.createElement(t.pop());
            return n
        }
        function g(e, t) {
            return le.nodeName(e, "table") && le.nodeName(1 === t.nodeType ? t: t.firstChild, "tr") ? e.getElementsByTagName("tbody")[0] || e.appendChild(e.ownerDocument.createElement("tbody")) : e
        }
        function d(e) {
            return e.type = (null !== le.find.attr(e, "type")) + "/" + e.type,
                e
        }
        function h(e) {
            var t = rt.exec(e.type);
            return t ? e.type = t[1] : e.removeAttribute("type"),
                e
        }
        function v(e, t) {
            for (var n, o = 0; null != (n = e[o]); o++) le._data(n, "globalEval", !t || le._data(t[o], "globalEval"))
        }
        function w(e, t) {
            if (1 === t.nodeType && le.hasData(e)) {
                var n, o, r, i = le._data(e),
                    a = le._data(t, i),
                    s = i.events;
                if (s) {
                    delete a.handle,
                        a.events = {};
                    for (n in s) for (o = 0, r = s[n].length; r > o; o++) le.event.add(t, n, s[n][o])
                }
                a.data && (a.data = le.extend({},
                    a.data))
            }
        }
        function D(e, t) {
            var n, o, r;
            if (1 === t.nodeType) {
                if (n = t.nodeName.toLowerCase(), !le.support.noCloneEvent && t[le.expando]) {
                    r = le._data(t);
                    for (o in r.events) le.removeEvent(t, o, r.handle);
                    t.removeAttribute(le.expando)
                }
                "script" === n && t.text !== e.text ? (d(t).text = e.text, h(t)) : "object" === n ? (t.parentNode && (t.outerHTML = e.outerHTML), le.support.html5Clone && e.innerHTML && !le.trim(t.innerHTML) && (t.innerHTML = e.innerHTML)) : "input" === n && tt.test(e.type) ? (t.defaultChecked = t.checked = e.checked, t.value !== e.value && (t.value = e.value)) : "option" === n ? t.defaultSelected = t.selected = e.defaultSelected: ("input" === n || "textarea" === n) && (t.defaultValue = e.defaultValue)
            }
        }
        function m(e, n) {
            var o, r, i = 0,
                a = typeof e.getElementsByTagName !== Y ? e.getElementsByTagName(n || "*") : typeof e.querySelectorAll !== Y ? e.querySelectorAll(n || "*") : t;
            if (!a) for (a = [], o = e.childNodes || e; null != (r = o[i]); i++) ! n || le.nodeName(r, n) ? a.push(r) : le.merge(a, m(r, n));
            return n === t || n && le.nodeName(e, n) ? le.merge([e], a) : a
        }
        function M(e) {
            tt.test(e.type) && (e.defaultChecked = e.checked)
        }
        function y(e, t) {
            if (t in e) return t;
            for (var n = t.charAt(0).toUpperCase() + t.slice(1), o = t, r = xt.length; r--;) if (t = xt[r] + n, t in e) return t;
            return o
        }
        function j(e, t) {
            return e = t || e,
            "none" === le.css(e, "display") || !le.contains(e.ownerDocument, e)
        }
        function x(e, t) {
            for (var n, o, r, i = [], a = 0, s = e.length; s > a; a++) o = e[a],
            o.style && (i[a] = le._data(o, "olddisplay"), n = o.style.display, t ? (i[a] || "none" !== n || (o.style.display = ""), "" === o.style.display && j(o) && (i[a] = le._data(o, "olddisplay", F(o.nodeName)))) : i[a] || (r = j(o), (n && "none" !== n || !r) && le._data(o, "olddisplay", r ? n: le.css(o, "display"))));
            for (a = 0; s > a; a++) o = e[a],
            o.style && (t && "none" !== o.style.display && "" !== o.style.display || (o.style.display = t ? i[a] || "": "none"));
            return e
        }
        function N(e, t, n) {
            var o = vt.exec(t);
            return o ? Math.max(0, o[1] - (n || 0)) + (o[2] || "px") : t
        }
        function C(e, t, n, o, r) {
            for (var i = n === (o ? "border": "content") ? 4 : "width" === t ? 1 : 0, a = 0; 4 > i; i += 2)"margin" === n && (a += le.css(e, n + jt[i], !0, r)),
                o ? ("content" === n && (a -= le.css(e, "padding" + jt[i], !0, r)), "margin" !== n && (a -= le.css(e, "border" + jt[i] + "Width", !0, r))) : (a += le.css(e, "padding" + jt[i], !0, r), "padding" !== n && (a += le.css(e, "border" + jt[i] + "Width", !0, r)));
            return a
        }
        function P(e, t, n) {
            var o = !0,
                r = "width" === t ? e.offsetWidth: e.offsetHeight,
                i = lt(e),
                a = le.support.boxSizing && "border-box" === le.css(e, "boxSizing", !1, i);
            if (0 >= r || null == r) {
                if (r = ut(e, t, i), (0 > r || null == r) && (r = e.style[t]), wt.test(r)) return r;
                o = a && (le.support.boxSizingReliable || r === e.style[t]),
                    r = parseFloat(r) || 0
            }
            return r + C(e, t, n || (a ? "border": "content"), o, i) + "px"
        }
        function F(e) {
            var t = J,
                n = mt[e];
            return n || (n = U(e, t), "none" !== n && n || (At = (At || le("<iframe frameborder='0' width='0' height='0'/>").css("cssText", "display:block !important")).appendTo(t.documentElement), t = (At[0].contentWindow || At[0].contentDocument).document, t.write("<!doctype html><html><body>"), t.close(), n = U(e, t), At.detach()), mt[e] = n),
                n
        }
        function U(e, t) {
            var n = le(t.createElement(e)).appendTo(t.body),
                o = le.css(n[0], "display");
            return n.remove(),
                o
        }
        function T(e, t, n, o) {
            var r;
            if (le.isArray(t)) le.each(t,
                function(t, r) {
                    n || Ct.test(e) ? o(e, r) : T(e + "[" + ("object" == typeof r ? t: "") + "]", r, n, o)
                });
            else if (n || "object" !== le.type(t)) o(e, t);
            else for (r in t) T(e + "[" + r + "]", t[r], n, o)
        }
        function b(e) {
            return function(t, n) {
                "string" != typeof t && (n = t, t = "*");
                var o, r = 0,
                    i = t.toLowerCase().match(ce) || [];
                if (le.isFunction(n)) for (; o = i[r++];)"+" === o[0] ? (o = o.slice(1) || "*", (e[o] = e[o] || []).unshift(n)) : (e[o] = e[o] || []).push(n)
            }
        }
        function B(e, n, o, r) {
            function i(f) {
                var A;
                return a[f] = !0,
                    le.each(e[f] || [],
                        function(e, f) {
                            var l = f(n, o, r);
                            return "string" != typeof l || s || a[l] ? s ? !(A = l) : t: (n.dataTypes.unshift(l), i(l), !1)
                        }),
                    A
            }
            var a = {},
                s = e === Xt;
            return i(n.dataTypes[0]) || !a["*"] && i("*")
        }
        function z(e, n) {
            var o, r, i = le.ajaxSettings.flatOptions || {};
            for (r in n) n[r] !== t && ((i[r] ? e: o || (o = {}))[r] = n[r]);
            return o && le.extend(!0, e, o),
                e
        }
        function R(e, n, o) {
            for (var r, i, a, s, f = e.contents,
                     A = e.dataTypes;
                 "*" === A[0];) A.shift(),
            i === t && (i = e.mimeType || n.getResponseHeader("Content-Type"));
            if (i) for (s in f) if (f[s] && f[s].test(i)) {
                A.unshift(s);
                break
            }
            if (A[0] in o) a = A[0];
            else {
                for (s in o) {
                    if (!A[0] || e.converters[s + " " + A[0]]) {
                        a = s;
                        break
                    }
                    r || (r = s)
                }
                a = a || r
            }
            return a ? (a !== A[0] && A.unshift(a), o[a]) : t
        }
        function H(e, t, n, o) {
            var r, i, a, s, f, A = {},
                l = e.dataTypes.slice();
            if (l[1]) for (a in e.converters) A[a.toLowerCase()] = e.converters[a];
            for (i = l.shift(); i;) if (e.responseFields[i] && (n[e.responseFields[i]] = t), !f && o && e.dataFilter && (t = e.dataFilter(t, e.dataType)), f = i, i = l.shift()) if ("*" === i) i = f;
            else if ("*" !== f && f !== i) {
                if (a = A[f + " " + i] || A["* " + i], !a) for (r in A) if (s = r.split(" "), s[1] === i && (a = A[f + " " + s[0]] || A["* " + s[0]])) {
                    a === !0 ? a = A[r] : A[r] !== !0 && (i = s[0], l.unshift(s[1]));
                    break
                }
                if (a !== !0) if (a && e["throws"]) t = a(t);
                else try {
                        t = a(t)
                    } catch(u) {
                        return {
                            state: "parsererror",
                            error: a ? u: "No conversion from " + f + " to " + i
                        }
                    }
            }
            return {
                state: "success",
                data: t
            }
        }
        function K() {
            try {
                return new e.XMLHttpRequest
            } catch(t) {}
        }
        function O() {
            try {
                return new e.ActiveXObject("Microsoft.XMLHTTP")
            } catch(t) {}
        }
        function L() {
            return setTimeout(function() {
                Zt = t
            }),
                Zt = le.now()
        }
        function I(e, t, n) {
            for (var o, r = (rn[t] || []).concat(rn["*"]), i = 0, a = r.length; a > i; i++) if (o = r[i].call(n, t, e)) return o
        }
        function E(e, t, n) {
            var o, r, i = 0,
                a = on.length,
                s = le.Deferred().always(function() {
                    delete f.elem
                }),
                f = function() {
                    if (r) return ! 1;
                    for (var t = Zt || L(), n = Math.max(0, A.startTime + A.duration - t), o = n / A.duration || 0, i = 1 - o, a = 0, f = A.tweens.length; f > a; a++) A.tweens[a].run(i);
                    return s.notifyWith(e, [A, i, n]),
                        1 > i && f ? n: (s.resolveWith(e, [A]), !1)
                },
                A = s.promise({
                    elem: e,
                    props: le.extend({},
                        t),
                    opts: le.extend(!0, {
                            specialEasing: {}
                        },
                        n),
                    originalProperties: t,
                    originalOptions: n,
                    startTime: Zt || L(),
                    duration: n.duration,
                    tweens: [],
                    createTween: function(t, n) {
                        var o = le.Tween(e, A.opts, t, n, A.opts.specialEasing[t] || A.opts.easing);
                        return A.tweens.push(o),
                            o
                    },
                    stop: function(t) {
                        var n = 0,
                            o = t ? A.tweens.length: 0;
                        if (r) return this;
                        for (r = !0; o > n; n++) A.tweens[n].run(1);
                        return t ? s.resolveWith(e, [A, t]) : s.rejectWith(e, [A, t]),
                            this
                    }
                }),
                l = A.props;
            for (Q(l, A.opts.specialEasing); a > i; i++) if (o = on[i].call(A, e, l, A.opts)) return o;
            return le.map(l, I, A),
            le.isFunction(A.opts.start) && A.opts.start.call(e, A),
                le.fx.timer(le.extend(f, {
                    elem: e,
                    anim: A,
                    queue: A.opts.queue
                })),
                A.progress(A.opts.progress).done(A.opts.done, A.opts.complete).fail(A.opts.fail).always(A.opts.always)
        }
        function Q(e, t) {
            var n, o, r, i, a;
            for (n in e) if (o = le.camelCase(n), r = t[o], i = e[n], le.isArray(i) && (r = i[1], i = e[n] = i[0]), n !== o && (e[o] = i, delete e[n]), a = le.cssHooks[o], a && "expand" in a) {
                i = a.expand(i),
                    delete e[o];
                for (n in i) n in e || (e[n] = i[n], t[n] = r)
            } else t[o] = r
        }
        function k(e, t, n) {
            var o, r, i, a, s, f, A = this,
                l = {},
                u = e.style,
                c = e.nodeType && j(e),
                p = le._data(e, "fxshow");
            n.queue || (s = le._queueHooks(e, "fx"), null == s.unqueued && (s.unqueued = 0, f = s.empty.fire, s.empty.fire = function() {
                s.unqueued || f()
            }), s.unqueued++, A.always(function() {
                A.always(function() {
                    s.unqueued--,
                    le.queue(e, "fx").length || s.empty.fire()
                })
            })),
            1 === e.nodeType && ("height" in t || "width" in t) && (n.overflow = [u.overflow, u.overflowX, u.overflowY], "inline" === le.css(e, "display") && "none" === le.css(e, "float") && (le.support.inlineBlockNeedsLayout && "inline" !== F(e.nodeName) ? u.zoom = 1 : u.display = "inline-block")),
            n.overflow && (u.overflow = "hidden", le.support.shrinkWrapBlocks || A.always(function() {
                u.overflow = n.overflow[0],
                    u.overflowX = n.overflow[1],
                    u.overflowY = n.overflow[2]
            }));
            for (o in t) if (r = t[o], en.exec(r)) {
                if (delete t[o], i = i || "toggle" === r, r === (c ? "hide": "show")) continue;
                l[o] = p && p[o] || le.style(e, o)
            }
            if (!le.isEmptyObject(l)) {
                p ? "hidden" in p && (c = p.hidden) : p = le._data(e, "fxshow", {}),
                i && (p.hidden = !c),
                    c ? le(e).show() : A.done(function() {
                        le(e).hide()
                    }),
                    A.done(function() {
                        var t;
                        le._removeData(e, "fxshow");
                        for (t in l) le.style(e, t, l[t])
                    });
                for (o in l) a = I(c ? p[o] : 0, o, A),
                o in p || (p[o] = a.start, c && (a.end = a.start, a.start = "width" === o || "height" === o ? 1 : 0))
            }
        }
        function X(e, t, n, o, r) {
            return new X.prototype.init(e, t, n, o, r)
        }
        function S(e, t) {
            var n, o = {
                    height: e
                },
                r = 0;
            for (t = t ? 1 : 0; 4 > r; r += 2 - t) n = jt[r],
                o["margin" + n] = o["padding" + n] = e;
            return t && (o.opacity = o.width = e),
                o
        }
        function _(e) {
            return le.isWindow(e) ? e: 9 === e.nodeType && (e.defaultView || e.parentWindow)
        }
        var G, V, Y = typeof t,
            q = e.location,
            J = e.document,
            W = J.documentElement,
            Z = e.jQuery,
            $ = e.$,
            ee = {},
            te = [],
            ne = "1.10.2",
            oe = te.concat,
            re = te.push,
            ie = te.slice,
            ae = te.indexOf,
            se = ee.toString,
            fe = ee.hasOwnProperty,
            Ae = ne.trim,
            le = function(e, t) {
                return new le.fn.init(e, t, V)
            },
            ue = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source,
            ce = /\S+/g,
            pe = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,
            ge = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/,
            de = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,
            he = /^[\],:{}\s]*$/,
            ve = /(?:^|:|,)(?:\s*\[)+/g,
            we = /\\(?:["\\\/bfnrt]|u[\da-fA-F]{4})/g,
            De = /"[^"\\\r\n]*"|true|false|null|-?(?:\d+\.|)\d+(?:[eE][+-]?\d+|)/g,
            me = /^-ms-/,
            Me = /-([\da-z])/gi,
            ye = function(e, t) {
                return t.toUpperCase()
            },
            je = function(e) { (J.addEventListener || "load" === e.type || "complete" === J.readyState) && (xe(), le.ready())
            },
            xe = function() {
                J.addEventListener ? (J.removeEventListener("DOMContentLoaded", je, !1), e.removeEventListener("load", je, !1)) : (J.detachEvent("onreadystatechange", je), e.detachEvent("onload", je))
            };
        le.fn = le.prototype = {
            jquery: ne,
            constructor: le,
            init: function(e, n, o) {
                var r, i;
                if (!e) return this;
                if ("string" == typeof e) {
                    if (r = "<" === e.charAt(0) && ">" === e.charAt(e.length - 1) && e.length >= 3 ? [null, e, null] : ge.exec(e), !r || !r[1] && n) return ! n || n.jquery ? (n || o).find(e) : this.constructor(n).find(e);
                    if (r[1]) {
                        if (n = n instanceof le ? n[0] : n, le.merge(this, le.parseHTML(r[1], n && n.nodeType ? n.ownerDocument || n: J, !0)), de.test(r[1]) && le.isPlainObject(n)) for (r in n) le.isFunction(this[r]) ? this[r](n[r]) : this.attr(r, n[r]);
                        return this
                    }
                    if (i = J.getElementById(r[2]), i && i.parentNode) {
                        if (i.id !== r[2]) return o.find(e);
                        this.length = 1,
                            this[0] = i
                    }
                    return this.context = J,
                        this.selector = e,
                        this
                }
                return e.nodeType ? (this.context = this[0] = e, this.length = 1, this) : le.isFunction(e) ? o.ready(e) : (e.selector !== t && (this.selector = e.selector, this.context = e.context), le.makeArray(e, this))
            },
            selector: "",
            length: 0,
            toArray: function() {
                return ie.call(this)
            },
            get: function(e) {
                return null == e ? this.toArray() : 0 > e ? this[this.length + e] : this[e]
            },
            pushStack: function(e) {
                var t = le.merge(this.constructor(), e);
                return t.prevObject = this,
                    t.context = this.context,
                    t
            },
            each: function(e, t) {
                return le.each(this, e, t)
            },
            ready: function(e) {
                return le.ready.promise().done(e),
                    this
            },
            slice: function() {
                return this.pushStack(ie.apply(this, arguments))
            },
            first: function() {
                return this.eq(0)
            },
            last: function() {
                return this.eq( - 1)
            },
            eq: function(e) {
                var t = this.length,
                    n = +e + (0 > e ? t: 0);
                return this.pushStack(n >= 0 && t > n ? [this[n]] : [])
            },
            map: function(e) {
                return this.pushStack(le.map(this,
                    function(t, n) {
                        return e.call(t, n, t)
                    }))
            },
            end: function() {
                return this.prevObject || this.constructor(null)
            },
            push: re,
            sort: [].sort,
            splice: [].splice
        },
            le.fn.init.prototype = le.fn,
            le.extend = le.fn.extend = function() {
                var e, n, o, r, i, a, s = arguments[0] || {},
                    f = 1,
                    A = arguments.length,
                    l = !1;
                for ("boolean" == typeof s && (l = s, s = arguments[1] || {},
                    f = 2), "object" == typeof s || le.isFunction(s) || (s = {}), A === f && (s = this, --f); A > f; f++) if (null != (i = arguments[f])) for (r in i) e = s[r],
                    o = i[r],
                s !== o && (l && o && (le.isPlainObject(o) || (n = le.isArray(o))) ? (n ? (n = !1, a = e && le.isArray(e) ? e: []) : a = e && le.isPlainObject(e) ? e: {},
                    s[r] = le.extend(l, a, o)) : o !== t && (s[r] = o));
                return s
            },
            le.extend({
                expando: "jQuery" + (ne + Math.random()).replace(/\D/g, ""),
                noConflict: function(t) {
                    return e.$ === le && (e.$ = $),
                    t && e.jQuery === le && (e.jQuery = Z),
                        le
                },
                isReady: !1,
                readyWait: 1,
                holdReady: function(e) {
                    e ? le.readyWait++:le.ready(!0)
                },
                ready: function(e) {
                    if (e === !0 ? !--le.readyWait: !le.isReady) {
                        if (!J.body) return setTimeout(le.ready);
                        le.isReady = !0,
                        e !== !0 && --le.readyWait > 0 || (G.resolveWith(J, [le]), le.fn.trigger && le(J).trigger("ready").off("ready"))
                    }
                },
                isFunction: function(e) {
                    return "function" === le.type(e)
                },
                isArray: Array.isArray ||
                    function(e) {
                        return "array" === le.type(e)
                    },
                isWindow: function(e) {
                    return null != e && e == e.window
                },
                isNumeric: function(e) {
                    return ! isNaN(parseFloat(e)) && isFinite(e)
                },
                type: function(e) {
                    return null == e ? e + "": "object" == typeof e || "function" == typeof e ? ee[se.call(e)] || "object": typeof e
                },
                isPlainObject: function(e) {
                    var n;
                    if (!e || "object" !== le.type(e) || e.nodeType || le.isWindow(e)) return ! 1;
                    try {
                        if (e.constructor && !fe.call(e, "constructor") && !fe.call(e.constructor.prototype, "isPrototypeOf")) return ! 1
                    } catch(o) {
                        return ! 1
                    }
                    if (le.support.ownLast) for (n in e) return fe.call(e, n);
                    for (n in e);
                    return n === t || fe.call(e, n)
                },
                isEmptyObject: function(e) {
                    var t;
                    for (t in e) return ! 1;
                    return ! 0
                },
                error: function(e) {
                    throw Error(e)
                },
                parseHTML: function(e, t, n) {
                    if (!e || "string" != typeof e) return null;
                    "boolean" == typeof t && (n = t, t = !1),
                        t = t || J;
                    var o = de.exec(e),
                        r = !n && [];
                    return o ? [t.createElement(o[1])] : (o = le.buildFragment([e], t, r), r && le(r).remove(), le.merge([], o.childNodes))
                },
                parseJSON: function(n) {
                    return e.JSON && e.JSON.parse ? e.JSON.parse(n) : null === n ? n: "string" == typeof n && (n = le.trim(n), n && he.test(n.replace(we, "@").replace(De, "]").replace(ve, ""))) ? Function("return " + n)() : (le.error("Invalid JSON: " + n), t)
                },
                parseXML: function(n) {
                    var o, r;
                    if (!n || "string" != typeof n) return null;
                    try {
                        e.DOMParser ? (r = new DOMParser, o = r.parseFromString(n, "text/xml")) : (o = new ActiveXObject("Microsoft.XMLDOM"), o.async = "false", o.loadXML(n))
                    } catch(i) {
                        o = t
                    }
                    return o && o.documentElement && !o.getElementsByTagName("parsererror").length || le.error("Invalid XML: " + n),
                        o
                },
                noop: function() {},
                globalEval: function(t) {
                    t && le.trim(t) && (e.execScript ||
                        function(t) {
                            e.eval.call(e, t)
                        })(t)
                },
                camelCase: function(e) {
                    return e.replace(me, "ms-").replace(Me, ye)
                },
                nodeName: function(e, t) {
                    return e.nodeName && e.nodeName.toLowerCase() === t.toLowerCase()
                },
                each: function(e, t, o) {
                    var r, i = 0,
                        a = e.length,
                        s = n(e);
                    if (o) {
                        if (s) for (; a > i && (r = t.apply(e[i], o), r !== !1); i++);
                        else for (i in e) if (r = t.apply(e[i], o), r === !1) break
                    } else if (s) for (; a > i && (r = t.call(e[i], i, e[i]), r !== !1); i++);
                    else for (i in e) if (r = t.call(e[i], i, e[i]), r === !1) break;
                    return e
                },
                trim: Ae && !Ae.call("\ufeff ") ?
                    function(e) {
                        return null == e ? "": Ae.call(e)
                    }: function(e) {
                        return null == e ? "": (e + "").replace(pe, "")
                    },
                makeArray: function(e, t) {
                    var o = t || [];
                    return null != e && (n(Object(e)) ? le.merge(o, "string" == typeof e ? [e] : e) : re.call(o, e)),
                        o
                },
                inArray: function(e, t, n) {
                    var o;
                    if (t) {
                        if (ae) return ae.call(t, e, n);
                        for (o = t.length, n = n ? 0 > n ? Math.max(0, o + n) : n: 0; o > n; n++) if (n in t && t[n] === e) return n
                    }
                    return - 1
                },
                merge: function(e, n) {
                    var o = n.length,
                        r = e.length,
                        i = 0;
                    if ("number" == typeof o) for (; o > i; i++) e[r++] = n[i];
                    else for (; n[i] !== t;) e[r++] = n[i++];
                    return e.length = r,
                        e
                },
                grep: function(e, t, n) {
                    var o, r = [],
                        i = 0,
                        a = e.length;
                    for (n = !!n; a > i; i++) o = !!t(e[i], i),
                    n !== o && r.push(e[i]);
                    return r
                },
                map: function(e, t, o) {
                    var r, i = 0,
                        a = e.length,
                        s = n(e),
                        f = [];
                    if (s) for (; a > i; i++) r = t(e[i], i, o),
                    null != r && (f[f.length] = r);
                    else for (i in e) r = t(e[i], i, o),
                    null != r && (f[f.length] = r);
                    return oe.apply([], f)
                },
                guid: 1,
                proxy: function(e, n) {
                    var o, r, i;
                    return "string" == typeof n && (i = e[n], n = e, e = i),
                        le.isFunction(e) ? (o = ie.call(arguments, 2), r = function() {
                            return e.apply(n || this, o.concat(ie.call(arguments)))
                        },
                            r.guid = e.guid = e.guid || le.guid++, r) : t
                },
                access: function(e, n, o, r, i, a, s) {
                    var f = 0,
                        A = e.length,
                        l = null == o;
                    if ("object" === le.type(o)) {
                        i = !0;
                        for (f in o) le.access(e, n, f, o[f], !0, a, s)
                    } else if (r !== t && (i = !0, le.isFunction(r) || (s = !0), l && (s ? (n.call(e, r), n = null) : (l = n, n = function(e, t, n) {
                        return l.call(le(e), n)
                    })), n)) for (; A > f; f++) n(e[f], o, s ? r: r.call(e[f], f, n(e[f], o)));
                    return i ? e: l ? n.call(e) : A ? n(e[0], o) : a
                },
                now: function() {
                    return (new Date).getTime()
                },
                swap: function(e, t, n, o) {
                    var r, i, a = {};
                    for (i in t) a[i] = e.style[i],
                        e.style[i] = t[i];
                    r = n.apply(e, o || []);
                    for (i in t) e.style[i] = a[i];
                    return r
                }
            }),
            le.ready.promise = function(t) {
                if (!G) if (G = le.Deferred(), "complete" === J.readyState) setTimeout(le.ready);
                else if (J.addEventListener) J.addEventListener("DOMContentLoaded", je, !1),
                    e.addEventListener("load", je, !1);
                else {
                    J.attachEvent("onreadystatechange", je),
                        e.attachEvent("onload", je);
                    var n = !1;
                    try {
                        n = null == e.frameElement && J.documentElement
                    } catch(o) {}
                    n && n.doScroll &&
                    function r() {
                        if (!le.isReady) {
                            try {
                                n.doScroll("left")
                            } catch(e) {
                                return setTimeout(r, 50)
                            }
                            xe(),
                                le.ready()
                        }
                    } ()
                }
                return G.promise(t)
            },
            le.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),
                function(e, t) {
                    ee["[object " + t + "]"] = t.toLowerCase()
                }),
            V = le(J),
            function(e, t) {
                function n(e, t, n, o) {
                    var r, i, a, s, f, A, l, u, g, d;
                    if ((t ? t.ownerDocument || t: E) !== B && b(t), t = t || B, n = n || [], !e || "string" != typeof e) return n;
                    if (1 !== (s = t.nodeType) && 9 !== s) return [];
                    if (R && !o) {
                        if (r = De.exec(e)) if (a = r[1]) {
                            if (9 === s) {
                                if (i = t.getElementById(a), !i || !i.parentNode) return n;
                                if (i.id === a) return n.push(i),
                                    n
                            } else if (t.ownerDocument && (i = t.ownerDocument.getElementById(a)) && L(t, i) && i.id === a) return n.push(i),
                                n
                        } else {
                            if (r[2]) return ee.apply(n, t.getElementsByTagName(e)),
                                n;
                            if ((a = r[3]) && j.getElementsByClassName && t.getElementsByClassName) return ee.apply(n, t.getElementsByClassName(a)),
                                n
                        }
                        if (j.qsa && (!H || !H.test(e))) {
                            if (u = l = I, g = t, d = 9 === s && e, 1 === s && "object" !== t.nodeName.toLowerCase()) {
                                for (A = c(e), (l = t.getAttribute("id")) ? u = l.replace(ye, "\\$&") : t.setAttribute("id", u), u = "[id='" + u + "'] ", f = A.length; f--;) A[f] = u + p(A[f]);
                                g = pe.test(e) && t.parentNode || t,
                                    d = A.join(",")
                            }
                            if (d) try {
                                return ee.apply(n, g.querySelectorAll(d)),
                                    n
                            } catch(h) {} finally {
                                l || t.removeAttribute("id")
                            }
                        }
                    }
                    return M(e.replace(Ae, "$1"), t, n, o)
                }
                function o() {
                    function e(n, o) {
                        return t.push(n += " ") > N.cacheLength && delete e[t.shift()],
                            e[n] = o
                    }
                    var t = [];
                    return e
                }
                function r(e) {
                    return e[I] = !0,
                        e
                }
                function i(e) {
                    var t = B.createElement("div");
                    try {
                        return !! e(t)
                    } catch(n) {
                        return ! 1
                    } finally {
                        t.parentNode && t.parentNode.removeChild(t),
                            t = null
                    }
                }
                function a(e, t) {
                    for (var n = e.split("|"), o = e.length; o--;) N.attrHandle[n[o]] = t
                }
                function s(e, t) {
                    var n = t && e,
                        o = n && 1 === e.nodeType && 1 === t.nodeType && (~t.sourceIndex || q) - (~e.sourceIndex || q);
                    if (o) return o;
                    if (n) for (; n = n.nextSibling;) if (n === t) return - 1;
                    return e ? 1 : -1
                }
                function f(e) {
                    return function(t) {
                        var n = t.nodeName.toLowerCase();
                        return "input" === n && t.type === e
                    }
                }
                function A(e) {
                    return function(t) {
                        var n = t.nodeName.toLowerCase();
                        return ("input" === n || "button" === n) && t.type === e
                    }
                }
                function l(e) {
                    return r(function(t) {
                        return t = +t,
                            r(function(n, o) {
                                for (var r, i = e([], n.length, t), a = i.length; a--;) n[r = i[a]] && (n[r] = !(o[r] = n[r]))
                            })
                    })
                }
                function u() {}
                function c(e, t) {
                    var o, r, i, a, s, f, A, l = S[e + " "];
                    if (l) return t ? 0 : l.slice(0);
                    for (s = e, f = [], A = N.preFilter; s;) { (!o || (r = ue.exec(s))) && (r && (s = s.slice(r[0].length) || s), f.push(i = [])),
                        o = !1,
                    (r = ce.exec(s)) && (o = r.shift(), i.push({
                        value: o,
                        type: r[0].replace(Ae, " ")
                    }), s = s.slice(o.length));
                        for (a in N.filter) ! (r = ve[a].exec(s)) || A[a] && !(r = A[a](r)) || (o = r.shift(), i.push({
                            value: o,
                            type: a,
                            matches: r
                        }), s = s.slice(o.length));
                        if (!o) break
                    }
                    return t ? s.length: s ? n.error(e) : S(e, f).slice(0)
                }
                function p(e) {
                    for (var t = 0,
                             n = e.length,
                             o = ""; n > t; t++) o += e[t].value;
                    return o
                }
                function g(e, t, n) {
                    var o = t.dir,
                        r = n && "parentNode" === o,
                        i = k++;
                    return t.first ?
                        function(t, n, i) {
                            for (; t = t[o];) if (1 === t.nodeType || r) return e(t, n, i)
                        }: function(t, n, a) {
                            var s, f, A, l = Q + " " + i;
                            if (a) {
                                for (; t = t[o];) if ((1 === t.nodeType || r) && e(t, n, a)) return ! 0
                            } else for (; t = t[o];) if (1 === t.nodeType || r) if (A = t[I] || (t[I] = {}), (f = A[o]) && f[0] === l) {
                                if ((s = f[1]) === !0 || s === x) return s === !0
                            } else if (f = A[o] = [l], f[1] = e(t, n, a) || x, f[1] === !0) return ! 0
                        }
                }
                function d(e) {
                    return e.length > 1 ?
                        function(t, n, o) {
                            for (var r = e.length; r--;) if (!e[r](t, n, o)) return ! 1;
                            return ! 0
                        }: e[0]
                }
                function h(e, t, n, o, r) {
                    for (var i, a = [], s = 0, f = e.length, A = null != t; f > s; s++)(i = e[s]) && (!n || n(i, o, r)) && (a.push(i), A && t.push(s));
                    return a
                }
                function v(e, t, n, o, i, a) {
                    return o && !o[I] && (o = v(o)),
                    i && !i[I] && (i = v(i, a)),
                        r(function(r, a, s, f) {
                            var A, l, u, c = [],
                                p = [],
                                g = a.length,
                                d = r || m(t || "*", s.nodeType ? [s] : s, []),
                                v = !e || !r && t ? d: h(d, c, e, s, f),
                                w = n ? i || (r ? e: g || o) ? [] : a: v;
                            if (n && n(v, w, s, f), o) for (A = h(w, p), o(A, [], s, f), l = A.length; l--;)(u = A[l]) && (w[p[l]] = !(v[p[l]] = u));
                            if (r) {
                                if (i || e) {
                                    if (i) {
                                        for (A = [], l = w.length; l--;)(u = w[l]) && A.push(v[l] = u);
                                        i(null, w = [], A, f)
                                    }
                                    for (l = w.length; l--;)(u = w[l]) && (A = i ? ne.call(r, u) : c[l]) > -1 && (r[A] = !(a[A] = u))
                                }
                            } else w = h(w === a ? w.splice(g, w.length) : w),
                                i ? i(null, a, w, f) : ee.apply(a, w)
                        })
                }
                function w(e) {
                    for (var t, n, o, r = e.length,
                             i = N.relative[e[0].type], a = i || N.relative[" "], s = i ? 1 : 0, f = g(function(e) {
                                return e === t
                            },
                            a, !0), A = g(function(e) {
                                return ne.call(t, e) > -1
                            },
                            a, !0), l = [function(e, n, o) {
                            return ! i && (o || n !== U) || ((t = n).nodeType ? f(e, n, o) : A(e, n, o))
                        }]; r > s; s++) if (n = N.relative[e[s].type]) l = [g(d(l), n)];
                    else {
                        if (n = N.filter[e[s].type].apply(null, e[s].matches), n[I]) {
                            for (o = ++s; r > o && !N.relative[e[o].type]; o++);
                            return v(s > 1 && d(l), s > 1 && p(e.slice(0, s - 1).concat({
                                value: " " === e[s - 2].type ? "*": ""
                            })).replace(Ae, "$1"), n, o > s && w(e.slice(s, o)), r > o && w(e = e.slice(o)), r > o && p(e))
                        }
                        l.push(n)
                    }
                    return d(l)
                }
                function D(e, t) {
                    var o = 0,
                        i = t.length > 0,
                        a = e.length > 0,
                        s = function(r, s, f, A, l) {
                            var u, c, p, g = [],
                                d = 0,
                                v = "0",
                                w = r && [],
                                D = null != l,
                                m = U,
                                M = r || a && N.find.TAG("*", l && s.parentNode || s),
                                y = Q += null == m ? 1 : Math.random() || .1;
                            for (D && (U = s !== B && s, x = o); null != (u = M[v]); v++) {
                                if (a && u) {
                                    for (c = 0; p = e[c++];) if (p(u, s, f)) {
                                        A.push(u);
                                        break
                                    }
                                    D && (Q = y, x = ++o)
                                }
                                i && ((u = !p && u) && d--, r && w.push(u))
                            }
                            if (d += v, i && v !== d) {
                                for (c = 0; p = t[c++];) p(w, g, s, f);
                                if (r) {
                                    if (d > 0) for (; v--;) w[v] || g[v] || (g[v] = Z.call(A));
                                    g = h(g)
                                }
                                ee.apply(A, g),
                                D && !r && g.length > 0 && d + t.length > 1 && n.uniqueSort(A)
                            }
                            return D && (Q = y, U = m),
                                w
                        };
                    return i ? r(s) : s
                }
                function m(e, t, o) {
                    for (var r = 0,
                             i = t.length; i > r; r++) n(e, t[r], o);
                    return o
                }
                function M(e, t, n, o) {
                    var r, i, a, s, f, A = c(e);
                    if (!o && 1 === A.length) {
                        if (i = A[0] = A[0].slice(0), i.length > 2 && "ID" === (a = i[0]).type && j.getById && 9 === t.nodeType && R && N.relative[i[1].type]) {
                            if (t = (N.find.ID(a.matches[0].replace(je, xe), t) || [])[0], !t) return n;
                            e = e.slice(i.shift().value.length)
                        }
                        for (r = ve.needsContext.test(e) ? 0 : i.length; r--&&(a = i[r], !N.relative[s = a.type]);) if ((f = N.find[s]) && (o = f(a.matches[0].replace(je, xe), pe.test(i[0].type) && t.parentNode || t))) {
                            if (i.splice(r, 1), e = o.length && p(i), !e) return ee.apply(n, o),
                                n;
                            break
                        }
                    }
                    return F(e, A)(o, t, !R, n, pe.test(e)),
                        n
                }
                var y, j, x, N, C, P, F, U, T, b, B, z, R, H, K, O, L, I = "sizzle" + -new Date,
                    E = e.document,
                    Q = 0,
                    k = 0,
                    X = o(),
                    S = o(),
                    _ = o(),
                    G = !1,
                    V = function(e, t) {
                        return e === t ? (G = !0, 0) : 0
                    },
                    Y = typeof t,
                    q = 1 << 31,
                    J = {}.hasOwnProperty,
                    W = [],
                    Z = W.pop,
                    $ = W.push,
                    ee = W.push,
                    te = W.slice,
                    ne = W.indexOf ||
                        function(e) {
                            for (var t = 0,
                                     n = this.length; n > t; t++) if (this[t] === e) return t;
                            return - 1
                        },
                    oe = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped",
                    re = "[\\x20\\t\\r\\n\\f]",
                    ie = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+",
                    ae = ie.replace("w", "w#"),
                    se = "\\[" + re + "*(" + ie + ")" + re + "*(?:([*^$|!~]?=)" + re + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + ae + ")|)|)" + re + "*\\]",
                    fe = ":(" + ie + ")(?:\\(((['\"])((?:\\\\.|[^\\\\])*?)\\3|((?:\\\\.|[^\\\\()[\\]]|" + se.replace(3, 8) + ")*)|.*)\\)|)",
                    Ae = RegExp("^" + re + "+|((?:^|[^\\\\])(?:\\\\.)*)" + re + "+$", "g"),
                    ue = RegExp("^" + re + "*," + re + "*"),
                    ce = RegExp("^" + re + "*([>+~]|" + re + ")" + re + "*"),
                    pe = RegExp(re + "*[+~]"),
                    ge = RegExp("=" + re + "*([^\\]'\"]*)" + re + "*\\]", "g"),
                    de = RegExp(fe),
                    he = RegExp("^" + ae + "$"),
                    ve = {
                        ID: RegExp("^#(" + ie + ")"),
                        CLASS: RegExp("^\\.(" + ie + ")"),
                        TAG: RegExp("^(" + ie.replace("w", "w*") + ")"),
                        ATTR: RegExp("^" + se),
                        PSEUDO: RegExp("^" + fe),
                        CHILD: RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + re + "*(even|odd|(([+-]|)(\\d*)n|)" + re + "*(?:([+-]|)" + re + "*(\\d+)|))" + re + "*\\)|)", "i"),
                        bool: RegExp("^(?:" + oe + ")$", "i"),
                        needsContext: RegExp("^" + re + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + re + "*((?:-\\d)?\\d*)" + re + "*\\)|)(?=[^-]|$)", "i")
                    },
                    we = /^[^{]+\{\s*\[native \w/,
                    De = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/,
                    me = /^(?:input|select|textarea|button)$/i,
                    Me = /^h\d$/i,
                    ye = /'|\\/g,
                    je = RegExp("\\\\([\\da-f]{1,6}" + re + "?|(" + re + ")|.)", "ig"),
                    xe = function(e, t, n) {
                        var o = "0x" + t - 65536;
                        return o !== o || n ? t: 0 > o ? String.fromCharCode(o + 65536) : String.fromCharCode(55296 | o >> 10, 56320 | 1023 & o)
                    };
                try {
                    ee.apply(W = te.call(E.childNodes), E.childNodes),
                        W[E.childNodes.length].nodeType
                } catch(Ne) {
                    ee = {
                        apply: W.length ?
                            function(e, t) {
                                $.apply(e, te.call(t))
                            }: function(e, t) {
                                for (var n = e.length,
                                         o = 0; e[n++] = t[o++];);
                                e.length = n - 1
                            }
                    }
                }
                P = n.isXML = function(e) {
                    var t = e && (e.ownerDocument || e).documentElement;
                    return !! t && "HTML" !== t.nodeName
                },
                    j = n.support = {},
                    b = n.setDocument = function(e) {
                        var n = e ? e.ownerDocument || e: E,
                            o = n.defaultView;
                        return n !== B && 9 === n.nodeType && n.documentElement ? (B = n, z = n.documentElement, R = !P(n), o && o.attachEvent && o !== o.top && o.attachEvent("onbeforeunload",
                            function() {
                                b()
                            }), j.attributes = i(function(e) {
                            return e.className = "i",
                                !e.getAttribute("className")
                        }), j.getElementsByTagName = i(function(e) {
                            return e.appendChild(n.createComment("")),
                                !e.getElementsByTagName("*").length
                        }), j.getElementsByClassName = i(function(e) {
                            return e.innerHTML = "<div class='a'></div><div class='a i'></div>",
                                e.firstChild.className = "i",
                            2 === e.getElementsByClassName("i").length
                        }), j.getById = i(function(e) {
                            return z.appendChild(e).id = I,
                            !n.getElementsByName || !n.getElementsByName(I).length
                        }), j.getById ? (N.find.ID = function(e, t) {
                            if (typeof t.getElementById !== Y && R) {
                                var n = t.getElementById(e);
                                return n && n.parentNode ? [n] : []
                            }
                        },
                            N.filter.ID = function(e) {
                                var t = e.replace(je, xe);
                                return function(e) {
                                    return e.getAttribute("id") === t
                                }
                            }) : (delete N.find.ID, N.filter.ID = function(e) {
                            var t = e.replace(je, xe);
                            return function(e) {
                                var n = typeof e.getAttributeNode !== Y && e.getAttributeNode("id");
                                return n && n.value === t
                            }
                        }), N.find.TAG = j.getElementsByTagName ?
                            function(e, n) {
                                return typeof n.getElementsByTagName !== Y ? n.getElementsByTagName(e) : t
                            }: function(e, t) {
                                var n, o = [],
                                    r = 0,
                                    i = t.getElementsByTagName(e);
                                if ("*" === e) {
                                    for (; n = i[r++];) 1 === n.nodeType && o.push(n);
                                    return o
                                }
                                return i
                            },
                            N.find.CLASS = j.getElementsByClassName &&
                                function(e, n) {
                                    return typeof n.getElementsByClassName !== Y && R ? n.getElementsByClassName(e) : t
                                },
                            K = [], H = [], (j.qsa = we.test(n.querySelectorAll)) && (i(function(e) {
                            e.innerHTML = "<select><option selected=''></option></select>",
                            e.querySelectorAll("[selected]").length || H.push("\\[" + re + "*(?:value|" + oe + ")"),
                            e.querySelectorAll(":checked").length || H.push(":checked")
                        }), i(function(e) {
                            var t = n.createElement("input");
                            t.setAttribute("type", "hidden"),
                                e.appendChild(t).setAttribute("t", ""),
                            e.querySelectorAll("[t^='']").length && H.push("[*^$]=" + re + "*(?:''|\"\")"),
                            e.querySelectorAll(":enabled").length || H.push(":enabled", ":disabled"),
                                e.querySelectorAll("*,:x"),
                                H.push(",.*:")
                        })), (j.matchesSelector = we.test(O = z.webkitMatchesSelector || z.mozMatchesSelector || z.oMatchesSelector || z.msMatchesSelector)) && i(function(e) {
                            j.disconnectedMatch = O.call(e, "div"),
                                O.call(e, "[s!='']:x"),
                                K.push("!=", fe)
                        }), H = H.length && RegExp(H.join("|")), K = K.length && RegExp(K.join("|")), L = we.test(z.contains) || z.compareDocumentPosition ?
                            function(e, t) {
                                var n = 9 === e.nodeType ? e.documentElement: e,
                                    o = t && t.parentNode;
                                return e === o || !(!o || 1 !== o.nodeType || !(n.contains ? n.contains(o) : e.compareDocumentPosition && 16 & e.compareDocumentPosition(o)))
                            }: function(e, t) {
                                if (t) for (; t = t.parentNode;) if (t === e) return ! 0;
                                return ! 1
                            },
                            V = z.compareDocumentPosition ?
                                function(e, t) {
                                    if (e === t) return G = !0,
                                        0;
                                    var o = t.compareDocumentPosition && e.compareDocumentPosition && e.compareDocumentPosition(t);
                                    return o ? 1 & o || !j.sortDetached && t.compareDocumentPosition(e) === o ? e === n || L(E, e) ? -1 : t === n || L(E, t) ? 1 : T ? ne.call(T, e) - ne.call(T, t) : 0 : 4 & o ? -1 : 1 : e.compareDocumentPosition ? -1 : 1
                                }: function(e, t) {
                                    var o, r = 0,
                                        i = e.parentNode,
                                        a = t.parentNode,
                                        f = [e],
                                        A = [t];
                                    if (e === t) return G = !0,
                                        0;
                                    if (!i || !a) return e === n ? -1 : t === n ? 1 : i ? -1 : a ? 1 : T ? ne.call(T, e) - ne.call(T, t) : 0;
                                    if (i === a) return s(e, t);
                                    for (o = e; o = o.parentNode;) f.unshift(o);
                                    for (o = t; o = o.parentNode;) A.unshift(o);
                                    for (; f[r] === A[r];) r++;
                                    return r ? s(f[r], A[r]) : f[r] === E ? -1 : A[r] === E ? 1 : 0
                                },
                            n) : B
                    },
                    n.matches = function(e, t) {
                        return n(e, null, null, t)
                    },
                    n.matchesSelector = function(e, t) {
                        if ((e.ownerDocument || e) !== B && b(e), t = t.replace(ge, "='$1']"), !(!j.matchesSelector || !R || K && K.test(t) || H && H.test(t))) try {
                            var o = O.call(e, t);
                            if (o || j.disconnectedMatch || e.document && 11 !== e.document.nodeType) return o
                        } catch(r) {}
                        return n(t, B, null, [e]).length > 0
                    },
                    n.contains = function(e, t) {
                        return (e.ownerDocument || e) !== B && b(e),
                            L(e, t)
                    },
                    n.attr = function(e, n) { (e.ownerDocument || e) !== B && b(e);
                        var o = N.attrHandle[n.toLowerCase()],
                            r = o && J.call(N.attrHandle, n.toLowerCase()) ? o(e, n, !R) : t;
                        return r === t ? j.attributes || !R ? e.getAttribute(n) : (r = e.getAttributeNode(n)) && r.specified ? r.value: null: r
                    },
                    n.error = function(e) {
                        throw Error("Syntax error, unrecognized expression: " + e)
                    },
                    n.uniqueSort = function(e) {
                        var t, n = [],
                            o = 0,
                            r = 0;
                        if (G = !j.detectDuplicates, T = !j.sortStable && e.slice(0), e.sort(V), G) {
                            for (; t = e[r++];) t === e[r] && (o = n.push(r));
                            for (; o--;) e.splice(n[o], 1)
                        }
                        return e
                    },
                    C = n.getText = function(e) {
                        var t, n = "",
                            o = 0,
                            r = e.nodeType;
                        if (r) {
                            if (1 === r || 9 === r || 11 === r) {
                                if ("string" == typeof e.textContent) return e.textContent;
                                for (e = e.firstChild; e; e = e.nextSibling) n += C(e)
                            } else if (3 === r || 4 === r) return e.nodeValue
                        } else for (; t = e[o]; o++) n += C(t);
                        return n
                    },
                    N = n.selectors = {
                        cacheLength: 50,
                        createPseudo: r,
                        match: ve,
                        attrHandle: {},
                        find: {},
                        relative: {
                            ">": {
                                dir: "parentNode",
                                first: !0
                            },
                            " ": {
                                dir: "parentNode"
                            },
                            "+": {
                                dir: "previousSibling",
                                first: !0
                            },
                            "~": {
                                dir: "previousSibling"
                            }
                        },
                        preFilter: {
                            ATTR: function(e) {
                                return e[1] = e[1].replace(je, xe),
                                    e[3] = (e[4] || e[5] || "").replace(je, xe),
                                "~=" === e[2] && (e[3] = " " + e[3] + " "),
                                    e.slice(0, 4)
                            },
                            CHILD: function(e) {
                                return e[1] = e[1].toLowerCase(),
                                    "nth" === e[1].slice(0, 3) ? (e[3] || n.error(e[0]), e[4] = +(e[4] ? e[5] + (e[6] || 1) : 2 * ("even" === e[3] || "odd" === e[3])), e[5] = +(e[7] + e[8] || "odd" === e[3])) : e[3] && n.error(e[0]),
                                    e
                            },
                            PSEUDO: function(e) {
                                var n, o = !e[5] && e[2];
                                return ve.CHILD.test(e[0]) ? null: (e[3] && e[4] !== t ? e[2] = e[4] : o && de.test(o) && (n = c(o, !0)) && (n = o.indexOf(")", o.length - n) - o.length) && (e[0] = e[0].slice(0, n), e[2] = o.slice(0, n)), e.slice(0, 3))
                            }
                        },
                        filter: {
                            TAG: function(e) {
                                var t = e.replace(je, xe).toLowerCase();
                                return "*" === e ?
                                    function() {
                                        return ! 0
                                    }: function(e) {
                                        return e.nodeName && e.nodeName.toLowerCase() === t
                                    }
                            },
                            CLASS: function(e) {
                                var t = X[e + " "];
                                return t || (t = RegExp("(^|" + re + ")" + e + "(" + re + "|$)")) && X(e,
                                    function(e) {
                                        return t.test("string" == typeof e.className && e.className || typeof e.getAttribute !== Y && e.getAttribute("class") || "")
                                    })
                            },
                            ATTR: function(e, t, o) {
                                return function(r) {
                                    var i = n.attr(r, e);
                                    return null == i ? "!=" === t: !t || (i += "", "=" === t ? i === o: "!=" === t ? i !== o: "^=" === t ? o && 0 === i.indexOf(o) : "*=" === t ? o && i.indexOf(o) > -1 : "$=" === t ? o && i.slice( - o.length) === o: "~=" === t ? (" " + i + " ").indexOf(o) > -1 : "|=" === t && (i === o || i.slice(0, o.length + 1) === o + "-"))
                                }
                            },
                            CHILD: function(e, t, n, o, r) {
                                var i = "nth" !== e.slice(0, 3),
                                    a = "last" !== e.slice( - 4),
                                    s = "of-type" === t;
                                return 1 === o && 0 === r ?
                                    function(e) {
                                        return !! e.parentNode
                                    }: function(t, n, f) {
                                        var A, l, u, c, p, g, d = i !== a ? "nextSibling": "previousSibling",
                                            h = t.parentNode,
                                            v = s && t.nodeName.toLowerCase(),
                                            w = !f && !s;
                                        if (h) {
                                            if (i) {
                                                for (; d;) {
                                                    for (u = t; u = u[d];) if (s ? u.nodeName.toLowerCase() === v: 1 === u.nodeType) return ! 1;
                                                    g = d = "only" === e && !g && "nextSibling"
                                                }
                                                return ! 0
                                            }
                                            if (g = [a ? h.firstChild: h.lastChild], a && w) {
                                                for (l = h[I] || (h[I] = {}), A = l[e] || [], p = A[0] === Q && A[1], c = A[0] === Q && A[2], u = p && h.childNodes[p]; u = ++p && u && u[d] || (c = p = 0) || g.pop();) if (1 === u.nodeType && ++c && u === t) {
                                                    l[e] = [Q, p, c];
                                                    break
                                                }
                                            } else if (w && (A = (t[I] || (t[I] = {}))[e]) && A[0] === Q) c = A[1];
                                            else for (; (u = ++p && u && u[d] || (c = p = 0) || g.pop()) && ((s ? u.nodeName.toLowerCase() !== v: 1 !== u.nodeType) || !++c || (w && ((u[I] || (u[I] = {}))[e] = [Q, c]), u !== t)););
                                            return c -= r,
                                            c === o || 0 === c % o && c / o >= 0
                                        }
                                    }
                            },
                            PSEUDO: function(e, t) {
                                var o, i = N.pseudos[e] || N.setFilters[e.toLowerCase()] || n.error("unsupported pseudo: " + e);
                                return i[I] ? i(t) : i.length > 1 ? (o = [e, e, "", t], N.setFilters.hasOwnProperty(e.toLowerCase()) ? r(function(e, n) {
                                    for (var o, r = i(e, t), a = r.length; a--;) o = ne.call(e, r[a]),
                                        e[o] = !(n[o] = r[a])
                                }) : function(e) {
                                    return i(e, 0, o)
                                }) : i
                            }
                        },
                        pseudos: {
                            not: r(function(e) {
                                var t = [],
                                    n = [],
                                    o = F(e.replace(Ae, "$1"));
                                return o[I] ? r(function(e, t, n, r) {
                                    for (var i, a = o(e, null, r, []), s = e.length; s--;)(i = a[s]) && (e[s] = !(t[s] = i))
                                }) : function(e, r, i) {
                                    return t[0] = e,
                                        o(t, null, i, n),
                                        !n.pop()
                                }
                            }),
                            has: r(function(e) {
                                return function(t) {
                                    return n(e, t).length > 0
                                }
                            }),
                            contains: r(function(e) {
                                return function(t) {
                                    return (t.textContent || t.innerText || C(t)).indexOf(e) > -1
                                }
                            }),
                            lang: r(function(e) {
                                return he.test(e || "") || n.error("unsupported lang: " + e),
                                    e = e.replace(je, xe).toLowerCase(),
                                    function(t) {
                                        var n;
                                        do
                                            if (n = R ? t.lang: t.getAttribute("xml:lang") || t.getAttribute("lang")) return n = n.toLowerCase(),
                                            n === e || 0 === n.indexOf(e + "-");
                                        while ((t = t.parentNode) && 1 === t.nodeType);
                                        return ! 1
                                    }
                            }),
                            target: function(t) {
                                var n = e.location && e.location.hash;
                                return n && n.slice(1) === t.id
                            },
                            root: function(e) {
                                return e === z
                            },
                            focus: function(e) {
                                return e === B.activeElement && (!B.hasFocus || B.hasFocus()) && !!(e.type || e.href || ~e.tabIndex)
                            },
                            enabled: function(e) {
                                return e.disabled === !1
                            },
                            disabled: function(e) {
                                return e.disabled === !0
                            },
                            checked: function(e) {
                                var t = e.nodeName.toLowerCase();
                                return "input" === t && !!e.checked || "option" === t && !!e.selected
                            },
                            selected: function(e) {
                                return e.parentNode && e.parentNode.selectedIndex,
                                e.selected === !0
                            },
                            empty: function(e) {
                                for (e = e.firstChild; e; e = e.nextSibling) if (e.nodeName > "@" || 3 === e.nodeType || 4 === e.nodeType) return ! 1;
                                return ! 0
                            },
                            parent: function(e) {
                                return ! N.pseudos.empty(e)
                            },
                            header: function(e) {
                                return Me.test(e.nodeName)
                            },
                            input: function(e) {
                                return me.test(e.nodeName)
                            },
                            button: function(e) {
                                var t = e.nodeName.toLowerCase();
                                return "input" === t && "button" === e.type || "button" === t
                            },
                            text: function(e) {
                                var t;
                                return "input" === e.nodeName.toLowerCase() && "text" === e.type && (null == (t = e.getAttribute("type")) || t.toLowerCase() === e.type)
                            },
                            first: l(function() {
                                return [0]
                            }),
                            last: l(function(e, t) {
                                return [t - 1]
                            }),
                            eq: l(function(e, t, n) {
                                return [0 > n ? n + t: n]
                            }),
                            even: l(function(e, t) {
                                for (var n = 0; t > n; n += 2) e.push(n);
                                return e
                            }),
                            odd: l(function(e, t) {
                                for (var n = 1; t > n; n += 2) e.push(n);
                                return e
                            }),
                            lt: l(function(e, t, n) {
                                for (var o = 0 > n ? n + t: n; --o >= 0;) e.push(o);
                                return e
                            }),
                            gt: l(function(e, t, n) {
                                for (var o = 0 > n ? n + t: n; t > ++o;) e.push(o);
                                return e
                            })
                        }
                    },
                    N.pseudos.nth = N.pseudos.eq;
                for (y in {
                    radio: !0,
                    checkbox: !0,
                    file: !0,
                    password: !0,
                    image: !0
                }) N.pseudos[y] = f(y);
                for (y in {
                    submit: !0,
                    reset: !0
                }) N.pseudos[y] = A(y);
                u.prototype = N.filters = N.pseudos,
                    N.setFilters = new u,
                    F = n.compile = function(e, t) {
                        var n, o = [],
                            r = [],
                            i = _[e + " "];
                        if (!i) {
                            for (t || (t = c(e)), n = t.length; n--;) i = w(t[n]),
                                i[I] ? o.push(i) : r.push(i);
                            i = _(e, D(r, o))
                        }
                        return i
                    },
                    j.sortStable = I.split("").sort(V).join("") === I,
                    j.detectDuplicates = G,
                    b(),
                    j.sortDetached = i(function(e) {
                        return 1 & e.compareDocumentPosition(B.createElement("div"))
                    }),
                i(function(e) {
                    return e.innerHTML = "<a href='#'></a>",
                    "#" === e.firstChild.getAttribute("href")
                }) || a("type|href|height|width",
                    function(e, n, o) {
                        return o ? t: e.getAttribute(n, "type" === n.toLowerCase() ? 1 : 2)
                    }),
                j.attributes && i(function(e) {
                    return e.innerHTML = "<input/>",
                        e.firstChild.setAttribute("value", ""),
                    "" === e.firstChild.getAttribute("value")
                }) || a("value",
                    function(e, n, o) {
                        return o || "input" !== e.nodeName.toLowerCase() ? t: e.defaultValue
                    }),
                i(function(e) {
                    return null == e.getAttribute("disabled")
                }) || a(oe,
                    function(e, n, o) {
                        var r;
                        return o ? t: (r = e.getAttributeNode(n)) && r.specified ? r.value: e[n] === !0 ? n.toLowerCase() : null
                    }),
                    le.find = n,
                    le.expr = n.selectors,
                    le.expr[":"] = le.expr.pseudos,
                    le.unique = n.uniqueSort,
                    le.text = n.getText,
                    le.isXMLDoc = n.isXML,
                    le.contains = n.contains
            } (e);
        var Ne = {};
        le.Callbacks = function(e) {
            e = "string" == typeof e ? Ne[e] || o(e) : le.extend({},
                e);
            var n, r, i, a, s, f, A = [],
                l = !e.once && [],
                u = function(t) {
                    for (r = e.memory && t, i = !0, s = f || 0, f = 0, a = A.length, n = !0; A && a > s; s++) if (A[s].apply(t[0], t[1]) === !1 && e.stopOnFalse) {
                        r = !1;
                        break
                    }
                    n = !1,
                    A && (l ? l.length && u(l.shift()) : r ? A = [] : c.disable())
                },
                c = {
                    add: function() {
                        if (A) {
                            var t = A.length; !
                                function o(t) {
                                    le.each(t,
                                        function(t, n) {
                                            var r = le.type(n);
                                            "function" === r ? e.unique && c.has(n) || A.push(n) : n && n.length && "string" !== r && o(n)
                                        })
                                } (arguments),
                                n ? a = A.length: r && (f = t, u(r))
                        }
                        return this
                    },
                    remove: function() {
                        return A && le.each(arguments,
                            function(e, t) {
                                for (var o; (o = le.inArray(t, A, o)) > -1;) A.splice(o, 1),
                                n && (a >= o && a--, s >= o && s--)
                            }),
                            this
                    },
                    has: function(e) {
                        return e ? le.inArray(e, A) > -1 : !(!A || !A.length)
                    },
                    empty: function() {
                        return A = [],
                            a = 0,
                            this
                    },
                    disable: function() {
                        return A = l = r = t,
                            this
                    },
                    disabled: function() {
                        return ! A
                    },
                    lock: function() {
                        return l = t,
                        r || c.disable(),
                            this
                    },
                    locked: function() {
                        return ! l
                    },
                    fireWith: function(e, t) {
                        return ! A || i && !l || (t = t || [], t = [e, t.slice ? t.slice() : t], n ? l.push(t) : u(t)),
                            this
                    },
                    fire: function() {
                        return c.fireWith(this, arguments),
                            this
                    },
                    fired: function() {
                        return !! i
                    }
                };
            return c
        },
            le.extend({
                Deferred: function(e) {
                    var t = [["resolve", "done", le.Callbacks("once memory"), "resolved"], ["reject", "fail", le.Callbacks("once memory"), "rejected"], ["notify", "progress", le.Callbacks("memory")]],
                        n = "pending",
                        o = {
                            state: function() {
                                return n
                            },
                            always: function() {
                                return r.done(arguments).fail(arguments),
                                    this
                            },
                            then: function() {
                                var e = arguments;
                                return le.Deferred(function(n) {
                                    le.each(t,
                                        function(t, i) {
                                            var a = i[0],
                                                s = le.isFunction(e[t]) && e[t];
                                            r[i[1]](function() {
                                                var e = s && s.apply(this, arguments);
                                                e && le.isFunction(e.promise) ? e.promise().done(n.resolve).fail(n.reject).progress(n.notify) : n[a + "With"](this === o ? n.promise() : this, s ? [e] : arguments)
                                            })
                                        }),
                                        e = null
                                }).promise()
                            },
                            promise: function(e) {
                                return null != e ? le.extend(e, o) : o
                            }
                        },
                        r = {};
                    return o.pipe = o.then,
                        le.each(t,
                            function(e, i) {
                                var a = i[2],
                                    s = i[3];
                                o[i[1]] = a.add,
                                s && a.add(function() {
                                        n = s
                                    },
                                    t[1 ^ e][2].disable, t[2][2].lock),
                                    r[i[0]] = function() {
                                        return r[i[0] + "With"](this === r ? o: this, arguments),
                                            this
                                    },
                                    r[i[0] + "With"] = a.fireWith
                            }),
                        o.promise(r),
                    e && e.call(r, r),
                        r
                },
                when: function(e) {
                    var t, n, o, r = 0,
                        i = ie.call(arguments),
                        a = i.length,
                        s = 1 !== a || e && le.isFunction(e.promise) ? a: 0,
                        f = 1 === s ? e: le.Deferred(),
                        A = function(e, n, o) {
                            return function(r) {
                                n[e] = this,
                                    o[e] = arguments.length > 1 ? ie.call(arguments) : r,
                                    o === t ? f.notifyWith(n, o) : --s || f.resolveWith(n, o)
                            }
                        };
                    if (a > 1) for (t = Array(a), n = Array(a), o = Array(a); a > r; r++) i[r] && le.isFunction(i[r].promise) ? i[r].promise().done(A(r, o, i)).fail(f.reject).progress(A(r, n, t)) : --s;
                    return s || f.resolveWith(o, i),
                        f.promise()
                }
            }),
            le.support = function(t) {
                var n, o, r, i, a, s, f, A, l, u = J.createElement("div");
                if (u.setAttribute("className", "t"), u.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", n = u.getElementsByTagName("*") || [], o = u.getElementsByTagName("a")[0], !o || !o.style || !n.length) return t;
                i = J.createElement("select"),
                    s = i.appendChild(J.createElement("option")),
                    r = u.getElementsByTagName("input")[0],
                    o.style.cssText = "top:1px;float:left;opacity:.5",
                    t.getSetAttribute = "t" !== u.className,
                    t.leadingWhitespace = 3 === u.firstChild.nodeType,
                    t.tbody = !u.getElementsByTagName("tbody").length,
                    t.htmlSerialize = !!u.getElementsByTagName("link").length,
                    t.style = /top/.test(o.getAttribute("style")),
                    t.hrefNormalized = "/a" === o.getAttribute("href"),
                    t.opacity = /^0.5/.test(o.style.opacity),
                    t.cssFloat = !!o.style.cssFloat,
                    t.checkOn = !!r.value,
                    t.optSelected = s.selected,
                    t.enctype = !!J.createElement("form").enctype,
                    t.html5Clone = "<:nav></:nav>" !== J.createElement("nav").cloneNode(!0).outerHTML,
                    t.inlineBlockNeedsLayout = !1,
                    t.shrinkWrapBlocks = !1,
                    t.pixelPosition = !1,
                    t.deleteExpando = !0,
                    t.noCloneEvent = !0,
                    t.reliableMarginRight = !0,
                    t.boxSizingReliable = !0,
                    r.checked = !0,
                    t.noCloneChecked = r.cloneNode(!0).checked,
                    i.disabled = !0,
                    t.optDisabled = !s.disabled;
                try {
                    delete u.test
                } catch(c) {
                    t.deleteExpando = !1
                }
                r = J.createElement("input"),
                    r.setAttribute("value", ""),
                    t.input = "" === r.getAttribute("value"),
                    r.value = "t",
                    r.setAttribute("type", "radio"),
                    t.radioValue = "t" === r.value,
                    r.setAttribute("checked", "t"),
                    r.setAttribute("name", "t"),
                    a = J.createDocumentFragment(),
                    a.appendChild(r),
                    t.appendChecked = r.checked,
                    t.checkClone = a.cloneNode(!0).cloneNode(!0).lastChild.checked,
                u.attachEvent && (u.attachEvent("onclick",
                    function() {
                        t.noCloneEvent = !1
                    }), u.cloneNode(!0).click());
                for (l in {
                    submit: !0,
                    change: !0,
                    focusin: !0
                }) u.setAttribute(f = "on" + l, "t"),
                    t[l + "Bubbles"] = f in e || u.attributes[f].expando === !1;
                u.style.backgroundClip = "content-box",
                    u.cloneNode(!0).style.backgroundClip = "",
                    t.clearCloneStyle = "content-box" === u.style.backgroundClip;
                for (l in le(t)) break;
                return t.ownLast = "0" !== l,
                    le(function() {
                        var n, o, r, i = "padding:0;margin:0;border:0;display:block;box-sizing:content-box;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;",
                            a = J.getElementsByTagName("body")[0];
                        a && (n = J.createElement("div"), n.style.cssText = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px;margin-top:1px", a.appendChild(n).appendChild(u), u.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", r = u.getElementsByTagName("td"), r[0].style.cssText = "padding:0;margin:0;border:0;display:none", A = 0 === r[0].offsetHeight, r[0].style.display = "", r[1].style.display = "none", t.reliableHiddenOffsets = A && 0 === r[0].offsetHeight, u.innerHTML = "", u.style.cssText = "box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;padding:1px;border:1px;display:block;width:4px;margin-top:1%;position:absolute;top:1%;", le.swap(a, null != a.style.zoom ? {
                                zoom: 1
                            }: {},
                            function() {
                                t.boxSizing = 4 === u.offsetWidth
                            }), e.getComputedStyle && (t.pixelPosition = "1%" !== (e.getComputedStyle(u, null) || {}).top, t.boxSizingReliable = "4px" === (e.getComputedStyle(u, null) || {
                            width: "4px"
                        }).width, o = u.appendChild(J.createElement("div")), o.style.cssText = u.style.cssText = i, o.style.marginRight = o.style.width = "0", u.style.width = "1px", t.reliableMarginRight = !parseFloat((e.getComputedStyle(o, null) || {}).marginRight)), typeof u.style.zoom !== Y && (u.innerHTML = "", u.style.cssText = i + "width:1px;padding:1px;display:inline;zoom:1", t.inlineBlockNeedsLayout = 3 === u.offsetWidth, u.style.display = "block", u.innerHTML = "<div></div>", u.firstChild.style.width = "5px", t.shrinkWrapBlocks = 3 !== u.offsetWidth, t.inlineBlockNeedsLayout && (a.style.zoom = 1)), a.removeChild(n), n = u = r = o = null)
                    }),
                    n = i = a = s = o = r = null,
                    t
            } ({});
        var Ce = /(?:\{[\s\S]*\}|\[[\s\S]*\])$/,
            Pe = /([A-Z])/g;
        le.extend({
            cache: {},
            noData: {
                applet: !0,
                embed: !0,
                object: "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
            },
            hasData: function(e) {
                return e = e.nodeType ? le.cache[e[le.expando]] : e[le.expando],
                !!e && !s(e)
            },
            data: function(e, t, n) {
                return r(e, t, n)
            },
            removeData: function(e, t) {
                return i(e, t)
            },
            _data: function(e, t, n) {
                return r(e, t, n, !0)
            },
            _removeData: function(e, t) {
                return i(e, t, !0)
            },
            acceptData: function(e) {
                if (e.nodeType && 1 !== e.nodeType && 9 !== e.nodeType) return ! 1;
                var t = e.nodeName && le.noData[e.nodeName.toLowerCase()];
                return ! t || t !== !0 && e.getAttribute("classid") === t
            }
        }),
            le.fn.extend({
                data: function(e, n) {
                    var o, r, i = null,
                        s = 0,
                        f = this[0];
                    if (e === t) {
                        if (this.length && (i = le.data(f), 1 === f.nodeType && !le._data(f, "parsedAttrs"))) {
                            for (o = f.attributes; o.length > s; s++) r = o[s].name,
                            0 === r.indexOf("data-") && (r = le.camelCase(r.slice(5)), a(f, r, i[r]));
                            le._data(f, "parsedAttrs", !0)
                        }
                        return i
                    }
                    return "object" == typeof e ? this.each(function() {
                        le.data(this, e)
                    }) : arguments.length > 1 ? this.each(function() {
                        le.data(this, e, n)
                    }) : f ? a(f, e, le.data(f, e)) : null
                },
                removeData: function(e) {
                    return this.each(function() {
                        le.removeData(this, e)
                    })
                }
            }),
            le.extend({
                queue: function(e, n, o) {
                    var r;
                    return e ? (n = (n || "fx") + "queue", r = le._data(e, n), o && (!r || le.isArray(o) ? r = le._data(e, n, le.makeArray(o)) : r.push(o)), r || []) : t
                },
                dequeue: function(e, t) {
                    t = t || "fx";
                    var n = le.queue(e, t),
                        o = n.length,
                        r = n.shift(),
                        i = le._queueHooks(e, t),
                        a = function() {
                            le.dequeue(e, t)
                        };
                    "inprogress" === r && (r = n.shift(), o--),
                    r && ("fx" === t && n.unshift("inprogress"), delete i.stop, r.call(e, a, i)),
                    !o && i && i.empty.fire()
                },
                _queueHooks: function(e, t) {
                    var n = t + "queueHooks";
                    return le._data(e, n) || le._data(e, n, {
                        empty: le.Callbacks("once memory").add(function() {
                            le._removeData(e, t + "queue"),
                                le._removeData(e, n)
                        })
                    })
                }
            }),
            le.fn.extend({
                queue: function(e, n) {
                    var o = 2;
                    return "string" != typeof e && (n = e, e = "fx", o--),
                        o > arguments.length ? le.queue(this[0], e) : n === t ? this: this.each(function() {
                            var t = le.queue(this, e, n);
                            le._queueHooks(this, e),
                            "fx" === e && "inprogress" !== t[0] && le.dequeue(this, e)
                        })
                },
                dequeue: function(e) {
                    return this.each(function() {
                        le.dequeue(this, e)
                    })
                },
                delay: function(e, t) {
                    return e = le.fx ? le.fx.speeds[e] || e: e,
                        t = t || "fx",
                        this.queue(t,
                            function(t, n) {
                                var o = setTimeout(t, e);
                                n.stop = function() {
                                    clearTimeout(o)
                                }
                            })
                },
                clearQueue: function(e) {
                    return this.queue(e || "fx", [])
                },
                promise: function(e, n) {
                    var o, r = 1,
                        i = le.Deferred(),
                        a = this,
                        s = this.length,
                        f = function() {--r || i.resolveWith(a, [a])
                        };
                    for ("string" != typeof e && (n = e, e = t), e = e || "fx"; s--;) o = le._data(a[s], e + "queueHooks"),
                    o && o.empty && (r++, o.empty.add(f));
                    return f(),
                        i.promise(n)
                }
            });
        var Fe, Ue, Te = /[\t\r\n\f]/g,
            be = /\r/g,
            Be = /^(?:input|select|textarea|button|object)$/i,
            ze = /^(?:a|area)$/i,
            Re = /^(?:checked|selected)$/i,
            He = le.support.getSetAttribute,
            Ke = le.support.input;
        le.fn.extend({
            attr: function(e, t) {
                return le.access(this, le.attr, e, t, arguments.length > 1)
            },
            removeAttr: function(e) {
                return this.each(function() {
                    le.removeAttr(this, e)
                })
            },
            prop: function(e, t) {
                return le.access(this, le.prop, e, t, arguments.length > 1)
            },
            removeProp: function(e) {
                return e = le.propFix[e] || e,
                    this.each(function() {
                        try {
                            this[e] = t,
                                delete this[e]
                        } catch(n) {}
                    })
            },
            addClass: function(e) {
                var t, n, o, r, i, a = 0,
                    s = this.length,
                    f = "string" == typeof e && e;
                if (le.isFunction(e)) return this.each(function(t) {
                    le(this).addClass(e.call(this, t, this.className))
                });
                if (f) for (t = (e || "").match(ce) || []; s > a; a++) if (n = this[a], o = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(Te, " ") : " ")) {
                    for (i = 0; r = t[i++];) 0 > o.indexOf(" " + r + " ") && (o += r + " ");
                    n.className = le.trim(o)
                }
                return this
            },
            removeClass: function(e) {
                var t, n, o, r, i, a = 0,
                    s = this.length,
                    f = 0 === arguments.length || "string" == typeof e && e;
                if (le.isFunction(e)) return this.each(function(t) {
                    le(this).removeClass(e.call(this, t, this.className))
                });
                if (f) for (t = (e || "").match(ce) || []; s > a; a++) if (n = this[a], o = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(Te, " ") : "")) {
                    for (i = 0; r = t[i++];) for (; o.indexOf(" " + r + " ") >= 0;) o = o.replace(" " + r + " ", " ");
                    n.className = e ? le.trim(o) : ""
                }
                return this
            },
            toggleClass: function(e, t) {
                var n = typeof e;
                return "boolean" == typeof t && "string" === n ? t ? this.addClass(e) : this.removeClass(e) : le.isFunction(e) ? this.each(function(n) {
                    le(this).toggleClass(e.call(this, n, this.className, t), t)
                }) : this.each(function() {
                    if ("string" === n) for (var t, o = 0,
                                                 r = le(this), i = e.match(ce) || []; t = i[o++];) r.hasClass(t) ? r.removeClass(t) : r.addClass(t);
                    else(n === Y || "boolean" === n) && (this.className && le._data(this, "__className__", this.className), this.className = this.className || e === !1 ? "": le._data(this, "__className__") || "")
                })
            },
            hasClass: function(e) {
                for (var t = " " + e + " ",
                         n = 0,
                         o = this.length; o > n; n++) if (1 === this[n].nodeType && (" " + this[n].className + " ").replace(Te, " ").indexOf(t) >= 0) return ! 0;
                return ! 1
            },
            val: function(e) {
                var n, o, r, i = this[0];
                return arguments.length ? (r = le.isFunction(e), this.each(function(n) {
                    var i;
                    1 === this.nodeType && (i = r ? e.call(this, n, le(this).val()) : e, null == i ? i = "": "number" == typeof i ? i += "": le.isArray(i) && (i = le.map(i,
                        function(e) {
                            return null == e ? "": e + ""
                        })), o = le.valHooks[this.type] || le.valHooks[this.nodeName.toLowerCase()], o && "set" in o && o.set(this, i, "value") !== t || (this.value = i))
                })) : i ? (o = le.valHooks[i.type] || le.valHooks[i.nodeName.toLowerCase()], o && "get" in o && (n = o.get(i, "value")) !== t ? n: (n = i.value, "string" == typeof n ? n.replace(be, "") : null == n ? "": n)) : void 0
            }
        }),
            le.extend({
                valHooks: {
                    option: {
                        get: function(e) {
                            var t = le.find.attr(e, "value");
                            return null != t ? t: e.text
                        }
                    },
                    select: {
                        get: function(e) {
                            for (var t, n, o = e.options,
                                     r = e.selectedIndex,
                                     i = "select-one" === e.type || 0 > r,
                                     a = i ? null: [], s = i ? r + 1 : o.length, f = 0 > r ? s: i ? r: 0; s > f; f++) if (n = o[f], !(!n.selected && f !== r || (le.support.optDisabled ? n.disabled: null !== n.getAttribute("disabled")) || n.parentNode.disabled && le.nodeName(n.parentNode, "optgroup"))) {
                                if (t = le(n).val(), i) return t;
                                a.push(t)
                            }
                            return a
                        },
                        set: function(e, t) {
                            for (var n, o, r = e.options,
                                     i = le.makeArray(t), a = r.length; a--;) o = r[a],
                            (o.selected = le.inArray(le(o).val(), i) >= 0) && (n = !0);
                            return n || (e.selectedIndex = -1),
                                i
                        }
                    }
                },
                attr: function(e, n, o) {
                    var r, i, a = e.nodeType;
                    if (e && 3 !== a && 8 !== a && 2 !== a) return typeof e.getAttribute === Y ? le.prop(e, n, o) : (1 === a && le.isXMLDoc(e) || (n = n.toLowerCase(), r = le.attrHooks[n] || (le.expr.match.bool.test(n) ? Ue: Fe)), o === t ? r && "get" in r && null !== (i = r.get(e, n)) ? i: (i = le.find.attr(e, n), null == i ? t: i) : null !== o ? r && "set" in r && (i = r.set(e, o, n)) !== t ? i: (e.setAttribute(n, o + ""), o) : (le.removeAttr(e, n), t))
                },
                removeAttr: function(e, t) {
                    var n, o, r = 0,
                        i = t && t.match(ce);
                    if (i && 1 === e.nodeType) for (; n = i[r++];) o = le.propFix[n] || n,
                        le.expr.match.bool.test(n) ? Ke && He || !Re.test(n) ? e[o] = !1 : e[le.camelCase("default-" + n)] = e[o] = !1 : le.attr(e, n, ""),
                        e.removeAttribute(He ? n: o)
                },
                attrHooks: {
                    type: {
                        set: function(e, t) {
                            if (!le.support.radioValue && "radio" === t && le.nodeName(e, "input")) {
                                var n = e.value;
                                return e.setAttribute("type", t),
                                n && (e.value = n),
                                    t
                            }
                        }
                    }
                },
                propFix: {
                    "for": "htmlFor",
                    "class": "className"
                },
                prop: function(e, n, o) {
                    var r, i, a, s = e.nodeType;
                    if (e && 3 !== s && 8 !== s && 2 !== s) return a = 1 !== s || !le.isXMLDoc(e),
                    a && (n = le.propFix[n] || n, i = le.propHooks[n]),
                        o !== t ? i && "set" in i && (r = i.set(e, o, n)) !== t ? r: e[n] = o: i && "get" in i && null !== (r = i.get(e, n)) ? r: e[n]
                },
                propHooks: {
                    tabIndex: {
                        get: function(e) {
                            var t = le.find.attr(e, "tabindex");
                            return t ? parseInt(t, 10) : Be.test(e.nodeName) || ze.test(e.nodeName) && e.href ? 0 : -1
                        }
                    }
                }
            }),
            Ue = {
                set: function(e, t, n) {
                    return t === !1 ? le.removeAttr(e, n) : Ke && He || !Re.test(n) ? e.setAttribute(!He && le.propFix[n] || n, n) : e[le.camelCase("default-" + n)] = e[n] = !0,
                        n
                }
            },
            le.each(le.expr.match.bool.source.match(/\w+/g),
                function(e, n) {
                    var o = le.expr.attrHandle[n] || le.find.attr;
                    le.expr.attrHandle[n] = Ke && He || !Re.test(n) ?
                        function(e, n, r) {
                            var i = le.expr.attrHandle[n],
                                a = r ? t: (le.expr.attrHandle[n] = t) != o(e, n, r) ? n.toLowerCase() : null;
                            return le.expr.attrHandle[n] = i,
                                a
                        }: function(e, n, o) {
                            return o ? t: e[le.camelCase("default-" + n)] ? n.toLowerCase() : null
                        }
                }),
        Ke && He || (le.attrHooks.value = {
            set: function(e, n, o) {
                return le.nodeName(e, "input") ? (e.defaultValue = n, t) : Fe && Fe.set(e, n, o)
            }
        }),
        He || (Fe = {
            set: function(e, n, o) {
                var r = e.getAttributeNode(o);
                return r || e.setAttributeNode(r = e.ownerDocument.createAttribute(o)),
                    r.value = n += "",
                    "value" === o || n === e.getAttribute(o) ? n: t
            }
        },
            le.expr.attrHandle.id = le.expr.attrHandle.name = le.expr.attrHandle.coords = function(e, n, o) {
                var r;
                return o ? t: (r = e.getAttributeNode(n)) && "" !== r.value ? r.value: null
            },
            le.valHooks.button = {
                get: function(e, n) {
                    var o = e.getAttributeNode(n);
                    return o && o.specified ? o.value: t
                },
                set: Fe.set
            },
            le.attrHooks.contenteditable = {
                set: function(e, t, n) {
                    Fe.set(e, "" !== t && t, n)
                }
            },
            le.each(["width", "height"],
                function(e, n) {
                    le.attrHooks[n] = {
                        set: function(e, o) {
                            return "" === o ? (e.setAttribute(n, "auto"), o) : t
                        }
                    }
                })),
        le.support.hrefNormalized || le.each(["href", "src"],
            function(e, t) {
                le.propHooks[t] = {
                    get: function(e) {
                        return e.getAttribute(t, 4)
                    }
                }
            }),
        le.support.style || (le.attrHooks.style = {
            get: function(e) {
                return e.style.cssText || t
            },
            set: function(e, t) {
                return e.style.cssText = t + ""
            }
        }),
        le.support.optSelected || (le.propHooks.selected = {
            get: function(e) {
                var t = e.parentNode;
                return t && (t.selectedIndex, t.parentNode && t.parentNode.selectedIndex),
                    null
            }
        }),
            le.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"],
                function() {
                    le.propFix[this.toLowerCase()] = this
                }),
        le.support.enctype || (le.propFix.enctype = "encoding"),
            le.each(["radio", "checkbox"],
                function() {
                    le.valHooks[this] = {
                        set: function(e, n) {
                            return le.isArray(n) ? e.checked = le.inArray(le(e).val(), n) >= 0 : t
                        }
                    },
                    le.support.checkOn || (le.valHooks[this].get = function(e) {
                        return null === e.getAttribute("value") ? "on": e.value
                    })
                });
        var Oe = /^(?:input|select|textarea)$/i,
            Le = /^key/,
            Ie = /^(?:mouse|contextmenu)|click/,
            Ee = /^(?:focusinfocus|focusoutblur)$/,
            Qe = /^([^.]*)(?:\.(.+)|)$/;
        le.event = {
            global: {},
            add: function(e, n, o, r, i) {
                var a, s, f, A, l, u, c, p, g, d, h, v = le._data(e);
                if (v) {
                    for (o.handler && (A = o, o = A.handler, i = A.selector), o.guid || (o.guid = le.guid++), (s = v.events) || (s = v.events = {}), (u = v.handle) || (u = v.handle = function(e) {
                        return typeof le === Y || e && le.event.triggered === e.type ? t: le.event.dispatch.apply(u.elem, arguments)
                    },
                        u.elem = e), n = (n || "").match(ce) || [""], f = n.length; f--;) a = Qe.exec(n[f]) || [],
                        g = h = a[1],
                        d = (a[2] || "").split(".").sort(),
                    g && (l = le.event.special[g] || {},
                        g = (i ? l.delegateType: l.bindType) || g, l = le.event.special[g] || {},
                        c = le.extend({
                                type: g,
                                origType: h,
                                data: r,
                                handler: o,
                                guid: o.guid,
                                selector: i,
                                needsContext: i && le.expr.match.needsContext.test(i),
                                namespace: d.join(".")
                            },
                            A), (p = s[g]) || (p = s[g] = [], p.delegateCount = 0, l.setup && l.setup.call(e, r, d, u) !== !1 || (e.addEventListener ? e.addEventListener(g, u, !1) : e.attachEvent && e.attachEvent("on" + g, u))), l.add && (l.add.call(e, c), c.handler.guid || (c.handler.guid = o.guid)), i ? p.splice(p.delegateCount++, 0, c) : p.push(c), le.event.global[g] = !0);
                    e = null
                }
            },
            remove: function(e, t, n, o, r) {
                var i, a, s, f, A, l, u, c, p, g, d, h = le.hasData(e) && le._data(e);
                if (h && (l = h.events)) {
                    for (t = (t || "").match(ce) || [""], A = t.length; A--;) if (s = Qe.exec(t[A]) || [], p = d = s[1], g = (s[2] || "").split(".").sort(), p) {
                        for (u = le.event.special[p] || {},
                                 p = (o ? u.delegateType: u.bindType) || p, c = l[p] || [], s = s[2] && RegExp("(^|\\.)" + g.join("\\.(?:.*\\.|)") + "(\\.|$)"), f = i = c.length; i--;) a = c[i],
                        !r && d !== a.origType || n && n.guid !== a.guid || s && !s.test(a.namespace) || o && o !== a.selector && ("**" !== o || !a.selector) || (c.splice(i, 1), a.selector && c.delegateCount--, u.remove && u.remove.call(e, a));
                        f && !c.length && (u.teardown && u.teardown.call(e, g, h.handle) !== !1 || le.removeEvent(e, p, h.handle), delete l[p])
                    } else for (p in l) le.event.remove(e, p + t[A], n, o, !0);
                    le.isEmptyObject(l) && (delete h.handle, le._removeData(e, "events"))
                }
            },
            trigger: function(n, o, r, i) {
                var a, s, f, A, l, u, c, p = [r || J],
                    g = fe.call(n, "type") ? n.type: n,
                    d = fe.call(n, "namespace") ? n.namespace.split(".") : [];
                if (f = u = r = r || J, 3 !== r.nodeType && 8 !== r.nodeType && !Ee.test(g + le.event.triggered) && (g.indexOf(".") >= 0 && (d = g.split("."), g = d.shift(), d.sort()), s = 0 > g.indexOf(":") && "on" + g, n = n[le.expando] ? n: new le.Event(g, "object" == typeof n && n), n.isTrigger = i ? 2 : 3, n.namespace = d.join("."), n.namespace_re = n.namespace ? RegExp("(^|\\.)" + d.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, n.result = t, n.target || (n.target = r), o = null == o ? [n] : le.makeArray(o, [n]), l = le.event.special[g] || {},
                i || !l.trigger || l.trigger.apply(r, o) !== !1)) {
                    if (!i && !l.noBubble && !le.isWindow(r)) {
                        for (A = l.delegateType || g, Ee.test(A + g) || (f = f.parentNode); f; f = f.parentNode) p.push(f),
                            u = f;
                        u === (r.ownerDocument || J) && p.push(u.defaultView || u.parentWindow || e)
                    }
                    for (c = 0; (f = p[c++]) && !n.isPropagationStopped();) n.type = c > 1 ? A: l.bindType || g,
                        a = (le._data(f, "events") || {})[n.type] && le._data(f, "handle"),
                    a && a.apply(f, o),
                        a = s && f[s],
                    a && le.acceptData(f) && a.apply && a.apply(f, o) === !1 && n.preventDefault();
                    if (n.type = g, !i && !n.isDefaultPrevented() && (!l._default || l._default.apply(p.pop(), o) === !1) && le.acceptData(r) && s && r[g] && !le.isWindow(r)) {
                        u = r[s],
                        u && (r[s] = null),
                            le.event.triggered = g;
                        try {
                            r[g]()
                        } catch(h) {}
                        le.event.triggered = t,
                        u && (r[s] = u)
                    }
                    return n.result
                }
            },
            dispatch: function(e) {
                e = le.event.fix(e);
                var n, o, r, i, a, s = [],
                    f = ie.call(arguments),
                    A = (le._data(this, "events") || {})[e.type] || [],
                    l = le.event.special[e.type] || {};
                if (f[0] = e, e.delegateTarget = this, !l.preDispatch || l.preDispatch.call(this, e) !== !1) {
                    for (s = le.event.handlers.call(this, e, A), n = 0; (i = s[n++]) && !e.isPropagationStopped();) for (e.currentTarget = i.elem, a = 0; (r = i.handlers[a++]) && !e.isImmediatePropagationStopped();)(!e.namespace_re || e.namespace_re.test(r.namespace)) && (e.handleObj = r, e.data = r.data, o = ((le.event.special[r.origType] || {}).handle || r.handler).apply(i.elem, f), o !== t && (e.result = o) === !1 && (e.preventDefault(), e.stopPropagation()));
                    return l.postDispatch && l.postDispatch.call(this, e),
                        e.result
                }
            },
            handlers: function(e, n) {
                var o, r, i, a, s = [],
                    f = n.delegateCount,
                    A = e.target;
                if (f && A.nodeType && (!e.button || "click" !== e.type)) for (; A != this; A = A.parentNode || this) if (1 === A.nodeType && (A.disabled !== !0 || "click" !== e.type)) {
                    for (i = [], a = 0; f > a; a++) r = n[a],
                        o = r.selector + " ",
                    i[o] === t && (i[o] = r.needsContext ? le(o, this).index(A) >= 0 : le.find(o, this, null, [A]).length),
                    i[o] && i.push(r);
                    i.length && s.push({
                        elem: A,
                        handlers: i
                    })
                }
                return n.length > f && s.push({
                    elem: this,
                    handlers: n.slice(f)
                }),
                    s
            },
            fix: function(e) {
                if (e[le.expando]) return e;
                var t, n, o, r = e.type,
                    i = e,
                    a = this.fixHooks[r];
                for (a || (this.fixHooks[r] = a = Ie.test(r) ? this.mouseHooks: Le.test(r) ? this.keyHooks: {}), o = a.props ? this.props.concat(a.props) : this.props, e = new le.Event(i), t = o.length; t--;) n = o[t],
                    e[n] = i[n];
                return e.target || (e.target = i.srcElement || J),
                3 === e.target.nodeType && (e.target = e.target.parentNode),
                    e.metaKey = !!e.metaKey,
                    a.filter ? a.filter(e, i) : e
            },
            props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
            fixHooks: {},
            keyHooks: {
                props: "char charCode key keyCode".split(" "),
                filter: function(e, t) {
                    return null == e.which && (e.which = null != t.charCode ? t.charCode: t.keyCode),
                        e
                }
            },
            mouseHooks: {
                props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
                filter: function(e, n) {
                    var o, r, i, a = n.button,
                        s = n.fromElement;
                    return null == e.pageX && null != n.clientX && (r = e.target.ownerDocument || J, i = r.documentElement, o = r.body, e.pageX = n.clientX + (i && i.scrollLeft || o && o.scrollLeft || 0) - (i && i.clientLeft || o && o.clientLeft || 0), e.pageY = n.clientY + (i && i.scrollTop || o && o.scrollTop || 0) - (i && i.clientTop || o && o.clientTop || 0)),
                    !e.relatedTarget && s && (e.relatedTarget = s === e.target ? n.toElement: s),
                    e.which || a === t || (e.which = 1 & a ? 1 : 2 & a ? 3 : 4 & a ? 2 : 0),
                        e
                }
            },
            special: {
                load: {
                    noBubble: !0
                },
                focus: {
                    trigger: function() {
                        if (this !== l() && this.focus) try {
                            return this.focus(),
                                !1
                        } catch(e) {}
                    },
                    delegateType: "focusin"
                },
                blur: {
                    trigger: function() {
                        return this === l() && this.blur ? (this.blur(), !1) : t
                    },
                    delegateType: "focusout"
                },
                click: {
                    trigger: function() {
                        return le.nodeName(this, "input") && "checkbox" === this.type && this.click ? (this.click(), !1) : t
                    },
                    _default: function(e) {
                        return le.nodeName(e.target, "a")
                    }
                },
                beforeunload: {
                    postDispatch: function(e) {
                        e.result !== t && (e.originalEvent.returnValue = e.result)
                    }
                }
            },
            simulate: function(e, t, n, o) {
                var r = le.extend(new le.Event, n, {
                    type: e,
                    isSimulated: !0,
                    originalEvent: {}
                });
                o ? le.event.trigger(r, null, t) : le.event.dispatch.call(t, r),
                r.isDefaultPrevented() && n.preventDefault()
            }
        },
            le.removeEvent = J.removeEventListener ?
                function(e, t, n) {
                    e.removeEventListener && e.removeEventListener(t, n, !1)
                }: function(e, t, n) {
                    var o = "on" + t;
                    e.detachEvent && (typeof e[o] === Y && (e[o] = null), e.detachEvent(o, n))
                },
            le.Event = function(e, n) {
                return this instanceof le.Event ? (e && e.type ? (this.originalEvent = e, this.type = e.type, this.isDefaultPrevented = e.defaultPrevented || e.returnValue === !1 || e.getPreventDefault && e.getPreventDefault() ? f: A) : this.type = e, n && le.extend(this, n), this.timeStamp = e && e.timeStamp || le.now(), this[le.expando] = !0, t) : new le.Event(e, n)
            },
            le.Event.prototype = {
                isDefaultPrevented: A,
                isPropagationStopped: A,
                isImmediatePropagationStopped: A,
                preventDefault: function() {
                    var e = this.originalEvent;
                    this.isDefaultPrevented = f,
                    e && (e.preventDefault ? e.preventDefault() : e.returnValue = !1)
                },
                stopPropagation: function() {
                    var e = this.originalEvent;
                    this.isPropagationStopped = f,
                    e && (e.stopPropagation && e.stopPropagation(), e.cancelBubble = !0)
                },
                stopImmediatePropagation: function() {
                    this.isImmediatePropagationStopped = f,
                        this.stopPropagation()
                }
            },
            le.each({
                    mouseenter: "mouseover",
                    mouseleave: "mouseout"
                },
                function(e, t) {
                    le.event.special[e] = {
                        delegateType: t,
                        bindType: t,
                        handle: function(e) {
                            var n, o = this,
                                r = e.relatedTarget,
                                i = e.handleObj;
                            return (!r || r !== o && !le.contains(o, r)) && (e.type = i.origType, n = i.handler.apply(this, arguments), e.type = t),
                                n
                        }
                    }
                }),
        le.support.submitBubbles || (le.event.special.submit = {
            setup: function() {
                return ! le.nodeName(this, "form") && (le.event.add(this, "click._submit keypress._submit",
                    function(e) {
                        var n = e.target,
                            o = le.nodeName(n, "input") || le.nodeName(n, "button") ? n.form: t;
                        o && !le._data(o, "submitBubbles") && (le.event.add(o, "submit._submit",
                            function(e) {
                                e._submit_bubble = !0
                            }), le._data(o, "submitBubbles", !0))
                    }), t)
            },
            postDispatch: function(e) {
                e._submit_bubble && (delete e._submit_bubble, this.parentNode && !e.isTrigger && le.event.simulate("submit", this.parentNode, e, !0))
            },
            teardown: function() {
                return ! le.nodeName(this, "form") && (le.event.remove(this, "._submit"), t)
            }
        }),
        le.support.changeBubbles || (le.event.special.change = {
            setup: function() {
                return Oe.test(this.nodeName) ? (("checkbox" === this.type || "radio" === this.type) && (le.event.add(this, "propertychange._change",
                    function(e) {
                        "checked" === e.originalEvent.propertyName && (this._just_changed = !0)
                    }), le.event.add(this, "click._change",
                    function(e) {
                        this._just_changed && !e.isTrigger && (this._just_changed = !1),
                            le.event.simulate("change", this, e, !0)
                    })), !1) : (le.event.add(this, "beforeactivate._change",
                    function(e) {
                        var t = e.target;
                        Oe.test(t.nodeName) && !le._data(t, "changeBubbles") && (le.event.add(t, "change._change",
                            function(e) { ! this.parentNode || e.isSimulated || e.isTrigger || le.event.simulate("change", this.parentNode, e, !0)
                            }), le._data(t, "changeBubbles", !0))
                    }), t)
            },
            handle: function(e) {
                var n = e.target;
                return this !== n || e.isSimulated || e.isTrigger || "radio" !== n.type && "checkbox" !== n.type ? e.handleObj.handler.apply(this, arguments) : t
            },
            teardown: function() {
                return le.event.remove(this, "._change"),
                    !Oe.test(this.nodeName)
            }
        }),
        le.support.focusinBubbles || le.each({
                focus: "focusin",
                blur: "focusout"
            },
            function(e, t) {
                var n = 0,
                    o = function(e) {
                        le.event.simulate(t, e.target, le.event.fix(e), !0)
                    };
                le.event.special[t] = {
                    setup: function() {
                        0 === n++&&J.addEventListener(e, o, !0)
                    },
                    teardown: function() {
                        0 === --n && J.removeEventListener(e, o, !0)
                    }
                }
            }),
            le.fn.extend({
                on: function(e, n, o, r, i) {
                    var a, s;
                    if ("object" == typeof e) {
                        "string" != typeof n && (o = o || n, n = t);
                        for (a in e) this.on(a, n, o, e[a], i);
                        return this
                    }
                    if (null == o && null == r ? (r = n, o = n = t) : null == r && ("string" == typeof n ? (r = o, o = t) : (r = o, o = n, n = t)), r === !1) r = A;
                    else if (!r) return this;
                    return 1 === i && (s = r, r = function(e) {
                        return le().off(e),
                            s.apply(this, arguments)
                    },
                        r.guid = s.guid || (s.guid = le.guid++)),
                        this.each(function() {
                            le.event.add(this, e, r, o, n)
                        })
                },
                one: function(e, t, n, o) {
                    return this.on(e, t, n, o, 1)
                },
                off: function(e, n, o) {
                    var r, i;
                    if (e && e.preventDefault && e.handleObj) return r = e.handleObj,
                        le(e.delegateTarget).off(r.namespace ? r.origType + "." + r.namespace: r.origType, r.selector, r.handler),
                        this;
                    if ("object" == typeof e) {
                        for (i in e) this.off(i, n, e[i]);
                        return this
                    }
                    return (n === !1 || "function" == typeof n) && (o = n, n = t),
                    o === !1 && (o = A),
                        this.each(function() {
                            le.event.remove(this, e, o, n)
                        })
                },
                trigger: function(e, t) {
                    return this.each(function() {
                        le.event.trigger(e, t, this)
                    })
                },
                triggerHandler: function(e, n) {
                    var o = this[0];
                    return o ? le.event.trigger(e, n, o, !0) : t
                }
            });
        var ke = /^.[^:#\[\.,]*$/,
            Xe = /^(?:parents|prev(?:Until|All))/,
            Se = le.expr.match.needsContext,
            _e = {
                children: !0,
                contents: !0,
                next: !0,
                prev: !0
            };
        le.fn.extend({
            find: function(e) {
                var t, n = [],
                    o = this,
                    r = o.length;
                if ("string" != typeof e) return this.pushStack(le(e).filter(function() {
                    for (t = 0; r > t; t++) if (le.contains(o[t], this)) return ! 0
                }));
                for (t = 0; r > t; t++) le.find(e, o[t], n);
                return n = this.pushStack(r > 1 ? le.unique(n) : n),
                    n.selector = this.selector ? this.selector + " " + e: e,
                    n
            },
            has: function(e) {
                var t, n = le(e, this),
                    o = n.length;
                return this.filter(function() {
                    for (t = 0; o > t; t++) if (le.contains(this, n[t])) return ! 0
                })
            },
            not: function(e) {
                return this.pushStack(c(this, e || [], !0))
            },
            filter: function(e) {
                return this.pushStack(c(this, e || [], !1))
            },
            is: function(e) {
                return !! c(this, "string" == typeof e && Se.test(e) ? le(e) : e || [], !1).length
            },
            closest: function(e, t) {
                for (var n, o = 0,
                         r = this.length,
                         i = [], a = Se.test(e) || "string" != typeof e ? le(e, t || this.context) : 0; r > o; o++) for (n = this[o]; n && n !== t; n = n.parentNode) if (11 > n.nodeType && (a ? a.index(n) > -1 : 1 === n.nodeType && le.find.matchesSelector(n, e))) {
                    n = i.push(n);
                    break
                }
                return this.pushStack(i.length > 1 ? le.unique(i) : i)
            },
            index: function(e) {
                return e ? "string" == typeof e ? le.inArray(this[0], le(e)) : le.inArray(e.jquery ? e[0] : e, this) : this[0] && this[0].parentNode ? this.first().prevAll().length: -1
            },
            add: function(e, t) {
                var n = "string" == typeof e ? le(e, t) : le.makeArray(e && e.nodeType ? [e] : e),
                    o = le.merge(this.get(), n);
                return this.pushStack(le.unique(o))
            },
            addBack: function(e) {
                return this.add(null == e ? this.prevObject: this.prevObject.filter(e))
            }
        }),
            le.each({
                    parent: function(e) {
                        var t = e.parentNode;
                        return t && 11 !== t.nodeType ? t: null
                    },
                    parents: function(e) {
                        return le.dir(e, "parentNode")
                    },
                    parentsUntil: function(e, t, n) {
                        return le.dir(e, "parentNode", n)
                    },
                    next: function(e) {
                        return u(e, "nextSibling")
                    },
                    prev: function(e) {
                        return u(e, "previousSibling")
                    },
                    nextAll: function(e) {
                        return le.dir(e, "nextSibling");
                    },
                    prevAll: function(e) {
                        return le.dir(e, "previousSibling")
                    },
                    nextUntil: function(e, t, n) {
                        return le.dir(e, "nextSibling", n)
                    },
                    prevUntil: function(e, t, n) {
                        return le.dir(e, "previousSibling", n)
                    },
                    siblings: function(e) {
                        return le.sibling((e.parentNode || {}).firstChild, e)
                    },
                    children: function(e) {
                        return le.sibling(e.firstChild)
                    },
                    contents: function(e) {
                        return le.nodeName(e, "iframe") ? e.contentDocument || e.contentWindow.document: le.merge([], e.childNodes)
                    }
                },
                function(e, t) {
                    le.fn[e] = function(n, o) {
                        var r = le.map(this, t, n);
                        return "Until" !== e.slice( - 5) && (o = n),
                        o && "string" == typeof o && (r = le.filter(o, r)),
                        this.length > 1 && (_e[e] || (r = le.unique(r)), Xe.test(e) && (r = r.reverse())),
                            this.pushStack(r)
                    }
                }),
            le.extend({
                filter: function(e, t, n) {
                    var o = t[0];
                    return n && (e = ":not(" + e + ")"),
                        1 === t.length && 1 === o.nodeType ? le.find.matchesSelector(o, e) ? [o] : [] : le.find.matches(e, le.grep(t,
                            function(e) {
                                return 1 === e.nodeType
                            }))
                },
                dir: function(e, n, o) {
                    for (var r = [], i = e[n]; i && 9 !== i.nodeType && (o === t || 1 !== i.nodeType || !le(i).is(o));) 1 === i.nodeType && r.push(i),
                        i = i[n];
                    return r
                },
                sibling: function(e, t) {
                    for (var n = []; e; e = e.nextSibling) 1 === e.nodeType && e !== t && n.push(e);
                    return n
                }
            });
        var Ge = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video",
            Ve = / jQuery\d+="(?:null|\d+)"/g,
            Ye = RegExp("<(?:" + Ge + ")[\\s/>]", "i"),
            qe = /^\s+/,
            Je = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,
            We = /<([\w:]+)/,
            Ze = /<tbody/i,
            $e = /<|&#?\w+;/,
            et = /<(?:script|style|link)/i,
            tt = /^(?:checkbox|radio)$/i,
            nt = /checked\s*(?:[^=]|=\s*.checked.)/i,
            ot = /^$|\/(?:java|ecma)script/i,
            rt = /^true\/(.*)/,
            it = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g,
            at = {
                option: [1, "<select multiple='multiple'>", "</select>"],
                legend: [1, "<fieldset>", "</fieldset>"],
                area: [1, "<map>", "</map>"],
                param: [1, "<object>", "</object>"],
                thead: [1, "<table>", "</table>"],
                tr: [2, "<table><tbody>", "</tbody></table>"],
                col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
                td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
                _default: le.support.htmlSerialize ? [0, "", ""] : [1, "X<div>", "</div>"]
            },
            st = p(J),
            ft = st.appendChild(J.createElement("div"));
        at.optgroup = at.option,
            at.tbody = at.tfoot = at.colgroup = at.caption = at.thead,
            at.th = at.td,
            le.fn.extend({
                text: function(e) {
                    return le.access(this,
                        function(e) {
                            return e === t ? le.text(this) : this.empty().append((this[0] && this[0].ownerDocument || J).createTextNode(e))
                        },
                        null, e, arguments.length)
                },
                append: function() {
                    return this.domManip(arguments,
                        function(e) {
                            if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                                var t = g(this, e);
                                t.appendChild(e)
                            }
                        })
                },
                prepend: function() {
                    return this.domManip(arguments,
                        function(e) {
                            if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                                var t = g(this, e);
                                t.insertBefore(e, t.firstChild)
                            }
                        })
                },
                before: function() {
                    return this.domManip(arguments,
                        function(e) {
                            this.parentNode && this.parentNode.insertBefore(e, this)
                        })
                },
                after: function() {
                    return this.domManip(arguments,
                        function(e) {
                            this.parentNode && this.parentNode.insertBefore(e, this.nextSibling)
                        })
                },
                remove: function(e, t) {
                    for (var n, o = e ? le.filter(e, this) : this, r = 0; null != (n = o[r]); r++) t || 1 !== n.nodeType || le.cleanData(m(n)),
                    n.parentNode && (t && le.contains(n.ownerDocument, n) && v(m(n, "script")), n.parentNode.removeChild(n));
                    return this
                },
                empty: function() {
                    for (var e, t = 0; null != (e = this[t]); t++) {
                        for (1 === e.nodeType && le.cleanData(m(e, !1)); e.firstChild;) e.removeChild(e.firstChild);
                        e.options && le.nodeName(e, "select") && (e.options.length = 0)
                    }
                    return this
                },
                clone: function(e, t) {
                    return e = null != e && e,
                        t = null == t ? e: t,
                        this.map(function() {
                            return le.clone(this, e, t)
                        })
                },
                html: function(e) {
                    return le.access(this,
                        function(e) {
                            var n = this[0] || {},
                                o = 0,
                                r = this.length;
                            if (e === t) return 1 === n.nodeType ? n.innerHTML.replace(Ve, "") : t;
                            if (! ("string" != typeof e || et.test(e) || !le.support.htmlSerialize && Ye.test(e) || !le.support.leadingWhitespace && qe.test(e) || at[(We.exec(e) || ["", ""])[1].toLowerCase()])) {
                                e = e.replace(Je, "<$1></$2>");
                                try {
                                    for (; r > o; o++) n = this[o] || {},
                                    1 === n.nodeType && (le.cleanData(m(n, !1)), n.innerHTML = e);
                                    n = 0
                                } catch(i) {}
                            }
                            n && this.empty().append(e)
                        },
                        null, e, arguments.length)
                },
                replaceWith: function() {
                    var e = le.map(this,
                        function(e) {
                            return [e.nextSibling, e.parentNode]
                        }),
                        t = 0;
                    return this.domManip(arguments,
                        function(n) {
                            var o = e[t++],
                                r = e[t++];
                            r && (o && o.parentNode !== r && (o = this.nextSibling), le(this).remove(), r.insertBefore(n, o))
                        },
                        !0),
                        t ? this: this.remove()
                },
                detach: function(e) {
                    return this.remove(e, !0)
                },
                domManip: function(e, t, n) {
                    e = oe.apply([], e);
                    var o, r, i, a, s, f, A = 0,
                        l = this.length,
                        u = this,
                        c = l - 1,
                        p = e[0],
                        g = le.isFunction(p);
                    if (g || !(1 >= l || "string" != typeof p || le.support.checkClone) && nt.test(p)) return this.each(function(o) {
                        var r = u.eq(o);
                        g && (e[0] = p.call(this, o, r.html())),
                            r.domManip(e, t, n)
                    });
                    if (l && (f = le.buildFragment(e, this[0].ownerDocument, !1, !n && this), o = f.firstChild, 1 === f.childNodes.length && (f = o), o)) {
                        for (a = le.map(m(f, "script"), d), i = a.length; l > A; A++) r = f,
                        A !== c && (r = le.clone(r, !0, !0), i && le.merge(a, m(r, "script"))),
                            t.call(this[A], r, A);
                        if (i) for (s = a[a.length - 1].ownerDocument, le.map(a, h), A = 0; i > A; A++) r = a[A],
                        ot.test(r.type || "") && !le._data(r, "globalEval") && le.contains(s, r) && (r.src ? le._evalUrl(r.src) : le.globalEval((r.text || r.textContent || r.innerHTML || "").replace(it, "")));
                        f = o = null
                    }
                    return this
                }
            }),
            le.each({
                    appendTo: "append",
                    prependTo: "prepend",
                    insertBefore: "before",
                    insertAfter: "after",
                    replaceAll: "replaceWith"
                },
                function(e, t) {
                    le.fn[e] = function(e) {
                        for (var n, o = 0,
                                 r = [], i = le(e), a = i.length - 1; a >= o; o++) n = o === a ? this: this.clone(!0),
                            le(i[o])[t](n),
                            re.apply(r, n.get());
                        return this.pushStack(r)
                    }
                }),
            le.extend({
                clone: function(e, t, n) {
                    var o, r, i, a, s, f = le.contains(e.ownerDocument, e);
                    if (le.support.html5Clone || le.isXMLDoc(e) || !Ye.test("<" + e.nodeName + ">") ? i = e.cloneNode(!0) : (ft.innerHTML = e.outerHTML, ft.removeChild(i = ft.firstChild)), !(le.support.noCloneEvent && le.support.noCloneChecked || 1 !== e.nodeType && 11 !== e.nodeType || le.isXMLDoc(e))) for (o = m(i), s = m(e), a = 0; null != (r = s[a]); ++a) o[a] && D(r, o[a]);
                    if (t) if (n) for (s = s || m(e), o = o || m(i), a = 0; null != (r = s[a]); a++) w(r, o[a]);
                    else w(e, i);
                    return o = m(i, "script"),
                    o.length > 0 && v(o, !f && m(e, "script")),
                        o = s = r = null,
                        i
                },
                buildFragment: function(e, t, n, o) {
                    for (var r, i, a, s, f, A, l, u = e.length,
                             c = p(t), g = [], d = 0; u > d; d++) if (i = e[d], i || 0 === i) if ("object" === le.type(i)) le.merge(g, i.nodeType ? [i] : i);
                    else if ($e.test(i)) {
                        for (s = s || c.appendChild(t.createElement("div")), f = (We.exec(i) || ["", ""])[1].toLowerCase(), l = at[f] || at._default, s.innerHTML = l[1] + i.replace(Je, "<$1></$2>") + l[2], r = l[0]; r--;) s = s.lastChild;
                        if (!le.support.leadingWhitespace && qe.test(i) && g.push(t.createTextNode(qe.exec(i)[0])), !le.support.tbody) for (i = "table" !== f || Ze.test(i) ? "<table>" !== l[1] || Ze.test(i) ? 0 : s: s.firstChild, r = i && i.childNodes.length; r--;) le.nodeName(A = i.childNodes[r], "tbody") && !A.childNodes.length && i.removeChild(A);
                        for (le.merge(g, s.childNodes), s.textContent = ""; s.firstChild;) s.removeChild(s.firstChild);
                        s = c.lastChild
                    } else g.push(t.createTextNode(i));
                    for (s && c.removeChild(s), le.support.appendChecked || le.grep(m(g, "input"), M), d = 0; i = g[d++];) if ((!o || -1 === le.inArray(i, o)) && (a = le.contains(i.ownerDocument, i), s = m(c.appendChild(i), "script"), a && v(s), n)) for (r = 0; i = s[r++];) ot.test(i.type || "") && n.push(i);
                    return s = null,
                        c
                },
                cleanData: function(e, t) {
                    for (var n, o, r, i, a = 0,
                             s = le.expando,
                             f = le.cache,
                             A = le.support.deleteExpando,
                             l = le.event.special; null != (n = e[a]); a++) if ((t || le.acceptData(n)) && (r = n[s], i = r && f[r])) {
                        if (i.events) for (o in i.events) l[o] ? le.event.remove(n, o) : le.removeEvent(n, o, i.handle);
                        f[r] && (delete f[r], A ? delete n[s] : typeof n.removeAttribute !== Y ? n.removeAttribute(s) : n[s] = null, te.push(r))
                    }
                },
                _evalUrl: function(e) {
                    return le.ajax({
                        url: e,
                        type: "GET",
                        dataType: "script",
                        async: !1,
                        global: !1,
                        "throws": !0
                    })
                }
            }),
            le.fn.extend({
                wrapAll: function(e) {
                    if (le.isFunction(e)) return this.each(function(t) {
                        le(this).wrapAll(e.call(this, t))
                    });
                    if (this[0]) {
                        var t = le(e, this[0].ownerDocument).eq(0).clone(!0);
                        this[0].parentNode && t.insertBefore(this[0]),
                            t.map(function() {
                                for (var e = this; e.firstChild && 1 === e.firstChild.nodeType;) e = e.firstChild;
                                return e
                            }).append(this)
                    }
                    return this
                },
                wrapInner: function(e) {
                    return le.isFunction(e) ? this.each(function(t) {
                        le(this).wrapInner(e.call(this, t))
                    }) : this.each(function() {
                        var t = le(this),
                            n = t.contents();
                        n.length ? n.wrapAll(e) : t.append(e)
                    })
                },
                wrap: function(e) {
                    var t = le.isFunction(e);
                    return this.each(function(n) {
                        le(this).wrapAll(t ? e.call(this, n) : e)
                    })
                },
                unwrap: function() {
                    return this.parent().each(function() {
                        le.nodeName(this, "body") || le(this).replaceWith(this.childNodes)
                    }).end()
                }
            });
        var At, lt, ut, ct = /alpha\([^)]*\)/i,
            pt = /opacity\s*=\s*([^)]*)/,
            gt = /^(top|right|bottom|left)$/,
            dt = /^(none|table(?!-c[ea]).+)/,
            ht = /^margin/,
            vt = RegExp("^(" + ue + ")(.*)$", "i"),
            wt = RegExp("^(" + ue + ")(?!px)[a-z%]+$", "i"),
            Dt = RegExp("^([+-])=(" + ue + ")", "i"),
            mt = {
                BODY: "block"
            },
            Mt = {
                position: "absolute",
                visibility: "hidden",
                display: "block"
            },
            yt = {
                letterSpacing: 0,
                fontWeight: 400
            },
            jt = ["Top", "Right", "Bottom", "Left"],
            xt = ["Webkit", "O", "Moz", "ms"];
        le.fn.extend({
            css: function(e, n) {
                return le.access(this,
                    function(e, n, o) {
                        var r, i, a = {},
                            s = 0;
                        if (le.isArray(n)) {
                            for (i = lt(e), r = n.length; r > s; s++) a[n[s]] = le.css(e, n[s], !1, i);
                            return a
                        }
                        return o !== t ? le.style(e, n, o) : le.css(e, n)
                    },
                    e, n, arguments.length > 1)
            },
            show: function() {
                return x(this, !0)
            },
            hide: function() {
                return x(this)
            },
            toggle: function(e) {
                return "boolean" == typeof e ? e ? this.show() : this.hide() : this.each(function() {
                    j(this) ? le(this).show() : le(this).hide()
                })
            }
        }),
            le.extend({
                cssHooks: {
                    opacity: {
                        get: function(e, t) {
                            if (t) {
                                var n = ut(e, "opacity");
                                return "" === n ? "1": n
                            }
                        }
                    }
                },
                cssNumber: {
                    columnCount: !0,
                    fillOpacity: !0,
                    fontWeight: !0,
                    lineHeight: !0,
                    opacity: !0,
                    order: !0,
                    orphans: !0,
                    widows: !0,
                    zIndex: !0,
                    zoom: !0
                },
                cssProps: {
                    "float": le.support.cssFloat ? "cssFloat": "styleFloat"
                },
                style: function(e, n, o, r) {
                    if (e && 3 !== e.nodeType && 8 !== e.nodeType && e.style) {
                        var i, a, s, f = le.camelCase(n),
                            A = e.style;
                        if (n = le.cssProps[f] || (le.cssProps[f] = y(A, f)), s = le.cssHooks[n] || le.cssHooks[f], o === t) return s && "get" in s && (i = s.get(e, !1, r)) !== t ? i: A[n];
                        if (a = typeof o, "string" === a && (i = Dt.exec(o)) && (o = (i[1] + 1) * i[2] + parseFloat(le.css(e, n)), a = "number"), !(null == o || "number" === a && isNaN(o) || ("number" !== a || le.cssNumber[f] || (o += "px"), le.support.clearCloneStyle || "" !== o || 0 !== n.indexOf("background") || (A[n] = "inherit"), s && "set" in s && (o = s.set(e, o, r)) === t))) try {
                            A[n] = o
                        } catch(l) {}
                    }
                },
                css: function(e, n, o, r) {
                    var i, a, s, f = le.camelCase(n);
                    return n = le.cssProps[f] || (le.cssProps[f] = y(e.style, f)),
                        s = le.cssHooks[n] || le.cssHooks[f],
                    s && "get" in s && (a = s.get(e, !0, o)),
                    a === t && (a = ut(e, n, r)),
                    "normal" === a && n in yt && (a = yt[n]),
                        "" === o || o ? (i = parseFloat(a), o === !0 || le.isNumeric(i) ? i || 0 : a) : a
                }
            }),
            e.getComputedStyle ? (lt = function(t) {
                return e.getComputedStyle(t, null)
            },
                ut = function(e, n, o) {
                    var r, i, a, s = o || lt(e),
                        f = s ? s.getPropertyValue(n) || s[n] : t,
                        A = e.style;
                    return s && ("" !== f || le.contains(e.ownerDocument, e) || (f = le.style(e, n)), wt.test(f) && ht.test(n) && (r = A.width, i = A.minWidth, a = A.maxWidth, A.minWidth = A.maxWidth = A.width = f, f = s.width, A.width = r, A.minWidth = i, A.maxWidth = a)),
                        f
                }) : J.documentElement.currentStyle && (lt = function(e) {
                return e.currentStyle
            },
                ut = function(e, n, o) {
                    var r, i, a, s = o || lt(e),
                        f = s ? s[n] : t,
                        A = e.style;
                    return null == f && A && A[n] && (f = A[n]),
                    wt.test(f) && !gt.test(n) && (r = A.left, i = e.runtimeStyle, a = i && i.left, a && (i.left = e.currentStyle.left), A.left = "fontSize" === n ? "1em": f, f = A.pixelLeft + "px", A.left = r, a && (i.left = a)),
                        "" === f ? "auto": f
                }),
            le.each(["height", "width"],
                function(e, n) {
                    le.cssHooks[n] = {
                        get: function(e, o, r) {
                            return o ? 0 === e.offsetWidth && dt.test(le.css(e, "display")) ? le.swap(e, Mt,
                                function() {
                                    return P(e, n, r)
                                }) : P(e, n, r) : t
                        },
                        set: function(e, t, o) {
                            var r = o && lt(e);
                            return N(e, t, o ? C(e, n, o, le.support.boxSizing && "border-box" === le.css(e, "boxSizing", !1, r), r) : 0)
                        }
                    }
                }),
        le.support.opacity || (le.cssHooks.opacity = {
            get: function(e, t) {
                return pt.test((t && e.currentStyle ? e.currentStyle.filter: e.style.filter) || "") ? .01 * parseFloat(RegExp.$1) + "": t ? "1": ""
            },
            set: function(e, t) {
                var n = e.style,
                    o = e.currentStyle,
                    r = le.isNumeric(t) ? "alpha(opacity=" + 100 * t + ")": "",
                    i = o && o.filter || n.filter || "";
                n.zoom = 1,
                (t >= 1 || "" === t) && "" === le.trim(i.replace(ct, "")) && n.removeAttribute && (n.removeAttribute("filter"), "" === t || o && !o.filter) || (n.filter = ct.test(i) ? i.replace(ct, r) : i + " " + r)
            }
        }),
            le(function() {
                le.support.reliableMarginRight || (le.cssHooks.marginRight = {
                    get: function(e, n) {
                        return n ? le.swap(e, {
                                display: "inline-block"
                            },
                            ut, [e, "marginRight"]) : t
                    }
                }),
                !le.support.pixelPosition && le.fn.position && le.each(["top", "left"],
                    function(e, n) {
                        le.cssHooks[n] = {
                            get: function(e, o) {
                                return o ? (o = ut(e, n), wt.test(o) ? le(e).position()[n] + "px": o) : t
                            }
                        }
                    })
            }),
        le.expr && le.expr.filters && (le.expr.filters.hidden = function(e) {
            return 0 >= e.offsetWidth && 0 >= e.offsetHeight || !le.support.reliableHiddenOffsets && "none" === (e.style && e.style.display || le.css(e, "display"))
        },
            le.expr.filters.visible = function(e) {
                return ! le.expr.filters.hidden(e)
            }),
            le.each({
                    margin: "",
                    padding: "",
                    border: "Width"
                },
                function(e, t) {
                    le.cssHooks[e + t] = {
                        expand: function(n) {
                            for (var o = 0,
                                     r = {},
                                     i = "string" == typeof n ? n.split(" ") : [n]; 4 > o; o++) r[e + jt[o] + t] = i[o] || i[o - 2] || i[0];
                            return r
                        }
                    },
                    ht.test(e) || (le.cssHooks[e + t].set = N)
                });
        var Nt = /%20/g,
            Ct = /\[\]$/,
            Pt = /\r?\n/g,
            Ft = /^(?:submit|button|image|reset|file)$/i,
            Ut = /^(?:input|select|textarea|keygen)/i;
        le.fn.extend({
            serialize: function() {
                return le.param(this.serializeArray())
            },
            serializeArray: function() {
                return this.map(function() {
                    var e = le.prop(this, "elements");
                    return e ? le.makeArray(e) : this
                }).filter(function() {
                    var e = this.type;
                    return this.name && !le(this).is(":disabled") && Ut.test(this.nodeName) && !Ft.test(e) && (this.checked || !tt.test(e))
                }).map(function(e, t) {
                    var n = le(this).val();
                    return null == n ? null: le.isArray(n) ? le.map(n,
                        function(e) {
                            return {
                                name: t.name,
                                value: e.replace(Pt, "\r\n")
                            }
                        }) : {
                        name: t.name,
                        value: n.replace(Pt, "\r\n")
                    }
                }).get()
            }
        }),
            le.param = function(e, n) {
                var o, r = [],
                    i = function(e, t) {
                        t = le.isFunction(t) ? t() : null == t ? "": t,
                            r[r.length] = encodeURIComponent(e) + "=" + encodeURIComponent(t)
                    };
                if (n === t && (n = le.ajaxSettings && le.ajaxSettings.traditional), le.isArray(e) || e.jquery && !le.isPlainObject(e)) le.each(e,
                    function() {
                        i(this.name, this.value)
                    });
                else for (o in e) T(o, e[o], n, i);
                return r.join("&").replace(Nt, "+")
            },
            le.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "),
                function(e, t) {
                    le.fn[t] = function(e, n) {
                        return arguments.length > 0 ? this.on(t, null, e, n) : this.trigger(t)
                    }
                }),
            le.fn.extend({
                hover: function(e, t) {
                    return this.mouseenter(e).mouseleave(t || e)
                },
                bind: function(e, t, n) {
                    return this.on(e, null, t, n)
                },
                unbind: function(e, t) {
                    return this.off(e, null, t)
                },
                delegate: function(e, t, n, o) {
                    return this.on(t, e, n, o)
                },
                undelegate: function(e, t, n) {
                    return 1 === arguments.length ? this.off(e, "**") : this.off(t, e || "**", n)
                }
            });
        var Tt, bt, Bt = le.now(),
            zt = /\?/,
            Rt = /#.*$/,
            Ht = /([?&])_=[^&]*/,
            Kt = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm,
            Ot = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/,
            Lt = /^(?:GET|HEAD)$/,
            It = /^\/\//,
            Et = /^([\w.+-]+:)(?:\/\/([^\/?#:]*)(?::(\d+)|)|)/,
            Qt = le.fn.load,
            kt = {},
            Xt = {},
            St = "*/".concat("*");
        try {
            bt = q.href
        } catch(_t) {
            bt = J.createElement("a"),
                bt.href = "",
                bt = bt.href
        }
        Tt = Et.exec(bt.toLowerCase()) || [],
            le.fn.load = function(e, n, o) {
                if ("string" != typeof e && Qt) return Qt.apply(this, arguments);
                var r, i, a, s = this,
                    f = e.indexOf(" ");
                return f >= 0 && (r = e.slice(f, e.length), e = e.slice(0, f)),
                    le.isFunction(n) ? (o = n, n = t) : n && "object" == typeof n && (a = "POST"),
                s.length > 0 && le.ajax({
                    url: e,
                    type: a,
                    dataType: "html",
                    data: n
                }).done(function(e) {
                    i = arguments,
                        s.html(r ? le("<div>").append(le.parseHTML(e)).find(r) : e)
                }).complete(o &&
                    function(e, t) {
                        s.each(o, i || [e.responseText, t, e])
                    }),
                    this
            },
            le.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"],
                function(e, t) {
                    le.fn[t] = function(e) {
                        return this.on(t, e)
                    }
                }),
            le.extend({
                active: 0,
                lastModified: {},
                etag: {},
                ajaxSettings: {
                    url: bt,
                    type: "GET",
                    isLocal: Ot.test(Tt[1]),
                    global: !0,
                    processData: !0,
                    async: !0,
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    accepts: {
                        "*": St,
                        text: "text/plain",
                        html: "text/html",
                        xml: "application/xml, text/xml",
                        json: "application/json, text/javascript"
                    },
                    contents: {
                        xml: /xml/,
                        html: /html/,
                        json: /json/
                    },
                    responseFields: {
                        xml: "responseXML",
                        text: "responseText",
                        json: "responseJSON"
                    },
                    converters: {
                        "* text": String,
                        "text html": !0,
                        "text json": le.parseJSON,
                        "text xml": le.parseXML
                    },
                    flatOptions: {
                        url: !0,
                        context: !0
                    }
                },
                ajaxSetup: function(e, t) {
                    return t ? z(z(e, le.ajaxSettings), t) : z(le.ajaxSettings, e)
                },
                ajaxPrefilter: b(kt),
                ajaxTransport: b(Xt),
                ajax: function(e, n) {
                    function o(e, n, o, r) {
                        var i, u, w, D, M, j = n;
                        2 !== m && (m = 2, f && clearTimeout(f), l = t, s = r || "", y.readyState = e > 0 ? 4 : 0, i = e >= 200 && 300 > e || 304 === e, o && (D = R(c, y, o)), D = H(c, D, y, i), i ? (c.ifModified && (M = y.getResponseHeader("Last-Modified"), M && (le.lastModified[a] = M), M = y.getResponseHeader("etag"), M && (le.etag[a] = M)), 204 === e || "HEAD" === c.type ? j = "nocontent": 304 === e ? j = "notmodified": (j = D.state, u = D.data, w = D.error, i = !w)) : (w = j, (e || !j) && (j = "error", 0 > e && (e = 0))), y.status = e, y.statusText = (n || j) + "", i ? d.resolveWith(p, [u, j, y]) : d.rejectWith(p, [y, j, w]), y.statusCode(v), v = t, A && g.trigger(i ? "ajaxSuccess": "ajaxError", [y, c, i ? u: w]), h.fireWith(p, [y, j]), A && (g.trigger("ajaxComplete", [y, c]), --le.active || le.event.trigger("ajaxStop")))
                    }
                    "object" == typeof e && (n = e, e = t),
                        n = n || {};
                    var r, i, a, s, f, A, l, u, c = le.ajaxSetup({},
                        n),
                        p = c.context || c,
                        g = c.context && (p.nodeType || p.jquery) ? le(p) : le.event,
                        d = le.Deferred(),
                        h = le.Callbacks("once memory"),
                        v = c.statusCode || {},
                        w = {},
                        D = {},
                        m = 0,
                        M = "canceled",
                        y = {
                            readyState: 0,
                            getResponseHeader: function(e) {
                                var t;
                                if (2 === m) {
                                    if (!u) for (u = {}; t = Kt.exec(s);) u[t[1].toLowerCase()] = t[2];
                                    t = u[e.toLowerCase()]
                                }
                                return null == t ? null: t
                            },
                            getAllResponseHeaders: function() {
                                return 2 === m ? s: null
                            },
                            setRequestHeader: function(e, t) {
                                var n = e.toLowerCase();
                                return m || (e = D[n] = D[n] || e, w[e] = t),
                                    this
                            },
                            overrideMimeType: function(e) {
                                return m || (c.mimeType = e),
                                    this
                            },
                            statusCode: function(e) {
                                var t;
                                if (e) if (2 > m) for (t in e) v[t] = [v[t], e[t]];
                                else y.always(e[y.status]);
                                return this
                            },
                            abort: function(e) {
                                var t = e || M;
                                return l && l.abort(t),
                                    o(0, t),
                                    this
                            }
                        };
                    if (d.promise(y).complete = h.add, y.success = y.done, y.error = y.fail, c.url = ((e || c.url || bt) + "").replace(Rt, "").replace(It, Tt[1] + "//"), c.type = n.method || n.type || c.method || c.type, c.dataTypes = le.trim(c.dataType || "*").toLowerCase().match(ce) || [""], null == c.crossDomain && (r = Et.exec(c.url.toLowerCase()), c.crossDomain = !(!r || r[1] === Tt[1] && r[2] === Tt[2] && (r[3] || ("http:" === r[1] ? "80": "443")) === (Tt[3] || ("http:" === Tt[1] ? "80": "443")))), c.data && c.processData && "string" != typeof c.data && (c.data = le.param(c.data, c.traditional)), B(kt, c, n, y), 2 === m) return y;
                    A = c.global,
                    A && 0 === le.active++&&le.event.trigger("ajaxStart"),
                        c.type = c.type.toUpperCase(),
                        c.hasContent = !Lt.test(c.type),
                        a = c.url,
                    c.hasContent || (c.data && (a = c.url += (zt.test(a) ? "&": "?") + c.data, delete c.data), c.cache === !1 && (c.url = Ht.test(a) ? a.replace(Ht, "$1_=" + Bt++) : a + (zt.test(a) ? "&": "?") + "_=" + Bt++)),
                    c.ifModified && (le.lastModified[a] && y.setRequestHeader("If-Modified-Since", le.lastModified[a]), le.etag[a] && y.setRequestHeader("If-None-Match", le.etag[a])),
                    (c.data && c.hasContent && c.contentType !== !1 || n.contentType) && y.setRequestHeader("Content-Type", c.contentType),
                        y.setRequestHeader("Accept", c.dataTypes[0] && c.accepts[c.dataTypes[0]] ? c.accepts[c.dataTypes[0]] + ("*" !== c.dataTypes[0] ? ", " + St + "; q=0.01": "") : c.accepts["*"]);
                    for (i in c.headers) y.setRequestHeader(i, c.headers[i]);
                    if (c.beforeSend && (c.beforeSend.call(p, y, c) === !1 || 2 === m)) return y.abort();
                    M = "abort";
                    for (i in {
                        success: 1,
                        error: 1,
                        complete: 1
                    }) y[i](c[i]);
                    if (l = B(Xt, c, n, y)) {
                        y.readyState = 1,
                        A && g.trigger("ajaxSend", [y, c]),
                        c.async && c.timeout > 0 && (f = setTimeout(function() {
                                y.abort("timeout")
                            },
                            c.timeout));
                        try {
                            m = 1,
                                l.send(w, o)
                        } catch(j) {
                            if (! (2 > m)) throw j;
                            o( - 1, j)
                        }
                    } else o( - 1, "No Transport");
                    return y
                },
                getJSON: function(e, t, n) {
                    return le.get(e, t, n, "json")
                },
                getScript: function(e, n) {
                    return le.get(e, t, n, "script")
                }
            }),
            le.each(["get", "post"],
                function(e, n) {
                    le[n] = function(e, o, r, i) {
                        return le.isFunction(o) && (i = i || r, r = o, o = t),
                            le.ajax({
                                url: e,
                                type: n,
                                dataType: i,
                                data: o,
                                success: r
                            })
                    }
                }),
            le.ajaxSetup({
                accepts: {
                    script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"
                },
                contents: {
                    script: /(?:java|ecma)script/
                },
                converters: {
                    "text script": function(e) {
                        return le.globalEval(e),
                            e
                    }
                }
            }),
            le.ajaxPrefilter("script",
                function(e) {
                    e.cache === t && (e.cache = !1),
                    e.crossDomain && (e.type = "GET", e.global = !1)
                }),
            le.ajaxTransport("script",
                function(e) {
                    if (e.crossDomain) {
                        var n, o = J.head || le("head")[0] || J.documentElement;
                        return {
                            send: function(t, r) {
                                n = J.createElement("script"),
                                    n.async = !0,
                                e.scriptCharset && (n.charset = e.scriptCharset),
                                    n.src = e.url,
                                    n.onload = n.onreadystatechange = function(e, t) { (t || !n.readyState || /loaded|complete/.test(n.readyState)) && (n.onload = n.onreadystatechange = null, n.parentNode && n.parentNode.removeChild(n), n = null, t || r(200, "success"))
                                    },
                                    o.insertBefore(n, o.firstChild)
                            },
                            abort: function() {
                                n && n.onload(t, !0)
                            }
                        }
                    }
                });
        var Gt = [],
            Vt = /(=)\?(?=&|$)|\?\?/;
        le.ajaxSetup({
            jsonp: "callback",
            jsonpCallback: function() {
                var e = Gt.pop() || le.expando + "_" + Bt++;
                return this[e] = !0,
                    e
            }
        }),
            le.ajaxPrefilter("json jsonp",
                function(n, o, r) {
                    var i, a, s, f = n.jsonp !== !1 && (Vt.test(n.url) ? "url": "string" == typeof n.data && !(n.contentType || "").indexOf("application/x-www-form-urlencoded") && Vt.test(n.data) && "data");
                    return f || "jsonp" === n.dataTypes[0] ? (i = n.jsonpCallback = le.isFunction(n.jsonpCallback) ? n.jsonpCallback() : n.jsonpCallback, f ? n[f] = n[f].replace(Vt, "$1" + i) : n.jsonp !== !1 && (n.url += (zt.test(n.url) ? "&": "?") + n.jsonp + "=" + i), n.converters["script json"] = function() {
                        return s || le.error(i + " was not called"),
                            s[0]
                    },
                        n.dataTypes[0] = "json", a = e[i], e[i] = function() {
                        s = arguments
                    },
                        r.always(function() {
                            e[i] = a,
                            n[i] && (n.jsonpCallback = o.jsonpCallback, Gt.push(i)),
                            s && le.isFunction(a) && a(s[0]),
                                s = a = t
                        }), "script") : t
                });
        var Yt, qt, Jt = 0,
            Wt = e.ActiveXObject &&
                function() {
                    var e;
                    for (e in Yt) Yt[e](t, !0)
                };
        le.ajaxSettings.xhr = e.ActiveXObject ?
            function() {
                return ! this.isLocal && K() || O()
            }: K,
            qt = le.ajaxSettings.xhr(),
            le.support.cors = !!qt && "withCredentials" in qt,
            qt = le.support.ajax = !!qt,
        qt && le.ajaxTransport(function(n) {
            if (!n.crossDomain || le.support.cors) {
                var o;
                return {
                    send: function(r, i) {
                        var a, s, f = n.xhr();
                        if (n.username ? f.open(n.type, n.url, n.async, n.username, n.password) : f.open(n.type, n.url, n.async), n.xhrFields) for (s in n.xhrFields) f[s] = n.xhrFields[s];
                        n.mimeType && f.overrideMimeType && f.overrideMimeType(n.mimeType),
                        n.crossDomain || r["X-Requested-With"] || (r["X-Requested-With"] = "XMLHttpRequest");
                        try {
                            for (s in r) f.setRequestHeader(s, r[s])
                        } catch(A) {}
                        f.send(n.hasContent && n.data || null),
                            o = function(e, r) {
                                var s, A, l, u;
                                try {
                                    if (o && (r || 4 === f.readyState)) if (o = t, a && (f.onreadystatechange = le.noop, Wt && delete Yt[a]), r) 4 !== f.readyState && f.abort();
                                    else {
                                        u = {},
                                            s = f.status,
                                            A = f.getAllResponseHeaders(),
                                        "string" == typeof f.responseText && (u.text = f.responseText);
                                        try {
                                            l = f.statusText
                                        } catch(c) {
                                            l = ""
                                        }
                                        s || !n.isLocal || n.crossDomain ? 1223 === s && (s = 204) : s = u.text ? 200 : 404
                                    }
                                } catch(p) {
                                    r || i( - 1, p)
                                }
                                u && i(s, l, u, A)
                            },
                            n.async ? 4 === f.readyState ? setTimeout(o) : (a = ++Jt, Wt && (Yt || (Yt = {},
                                le(e).unload(Wt)), Yt[a] = o), f.onreadystatechange = o) : o()
                    },
                    abort: function() {
                        o && o(t, !0)
                    }
                }
            }
        });
        var Zt, $t, en = /^(?:toggle|show|hide)$/,
            tn = RegExp("^(?:([+-])=|)(" + ue + ")([a-z%]*)$", "i"),
            nn = /queueHooks$/,
            on = [k],
            rn = {
                "*": [function(e, t) {
                    var n = this.createTween(e, t),
                        o = n.cur(),
                        r = tn.exec(t),
                        i = r && r[3] || (le.cssNumber[e] ? "": "px"),
                        a = (le.cssNumber[e] || "px" !== i && +o) && tn.exec(le.css(n.elem, e)),
                        s = 1,
                        f = 20;
                    if (a && a[3] !== i) {
                        i = i || a[3],
                            r = r || [],
                            a = +o || 1;
                        do s = s || ".5",
                            a /= s,
                            le.style(n.elem, e, a + i);
                        while (s !== (s = n.cur() / o) && 1 !== s && --f)
                    }
                    return r && (a = n.start = +a || +o || 0, n.unit = i, n.end = r[1] ? a + (r[1] + 1) * r[2] : +r[2]),
                        n
                }]
            };
        le.Animation = le.extend(E, {
            tweener: function(e, t) {
                le.isFunction(e) ? (t = e, e = ["*"]) : e = e.split(" ");
                for (var n, o = 0,
                         r = e.length; r > o; o++) n = e[o],
                    rn[n] = rn[n] || [],
                    rn[n].unshift(t)
            },
            prefilter: function(e, t) {
                t ? on.unshift(e) : on.push(e)
            }
        }),
            le.Tween = X,
            X.prototype = {
                constructor: X,
                init: function(e, t, n, o, r, i) {
                    this.elem = e,
                        this.prop = n,
                        this.easing = r || "swing",
                        this.options = t,
                        this.start = this.now = this.cur(),
                        this.end = o,
                        this.unit = i || (le.cssNumber[n] ? "": "px")
                },
                cur: function() {
                    var e = X.propHooks[this.prop];
                    return e && e.get ? e.get(this) : X.propHooks._default.get(this)
                },
                run: function(e) {
                    var t, n = X.propHooks[this.prop];
                    return this.pos = t = this.options.duration ? le.easing[this.easing](e, this.options.duration * e, 0, 1, this.options.duration) : e,
                        this.now = (this.end - this.start) * t + this.start,
                    this.options.step && this.options.step.call(this.elem, this.now, this),
                        n && n.set ? n.set(this) : X.propHooks._default.set(this),
                        this
                }
            },
            X.prototype.init.prototype = X.prototype,
            X.propHooks = {
                _default: {
                    get: function(e) {
                        var t;
                        return null == e.elem[e.prop] || e.elem.style && null != e.elem.style[e.prop] ? (t = le.css(e.elem, e.prop, ""), t && "auto" !== t ? t: 0) : e.elem[e.prop]
                    },
                    set: function(e) {
                        le.fx.step[e.prop] ? le.fx.step[e.prop](e) : e.elem.style && (null != e.elem.style[le.cssProps[e.prop]] || le.cssHooks[e.prop]) ? le.style(e.elem, e.prop, e.now + e.unit) : e.elem[e.prop] = e.now
                    }
                }
            },
            X.propHooks.scrollTop = X.propHooks.scrollLeft = {
                set: function(e) {
                    e.elem.nodeType && e.elem.parentNode && (e.elem[e.prop] = e.now)
                }
            },
            le.each(["toggle", "show", "hide"],
                function(e, t) {
                    var n = le.fn[t];
                    le.fn[t] = function(e, o, r) {
                        return null == e || "boolean" == typeof e ? n.apply(this, arguments) : this.animate(S(t, !0), e, o, r)
                    }
                }),
            le.fn.extend({
                fadeTo: function(e, t, n, o) {
                    return this.filter(j).css("opacity", 0).show().end().animate({
                            opacity: t
                        },
                        e, n, o)
                },
                animate: function(e, t, n, o) {
                    var r = le.isEmptyObject(e),
                        i = le.speed(t, n, o),
                        a = function() {
                            var t = E(this, le.extend({},
                                e), i); (r || le._data(this, "finish")) && t.stop(!0)
                        };
                    return a.finish = a,
                        r || i.queue === !1 ? this.each(a) : this.queue(i.queue, a)
                },
                stop: function(e, n, o) {
                    var r = function(e) {
                        var t = e.stop;
                        delete e.stop,
                            t(o)
                    };
                    return "string" != typeof e && (o = n, n = e, e = t),
                    n && e !== !1 && this.queue(e || "fx", []),
                        this.each(function() {
                            var t = !0,
                                n = null != e && e + "queueHooks",
                                i = le.timers,
                                a = le._data(this);
                            if (n) a[n] && a[n].stop && r(a[n]);
                            else for (n in a) a[n] && a[n].stop && nn.test(n) && r(a[n]);
                            for (n = i.length; n--;) i[n].elem !== this || null != e && i[n].queue !== e || (i[n].anim.stop(o), t = !1, i.splice(n, 1)); (t || !o) && le.dequeue(this, e)
                        })
                },
                finish: function(e) {
                    return e !== !1 && (e = e || "fx"),
                        this.each(function() {
                            var t, n = le._data(this),
                                o = n[e + "queue"],
                                r = n[e + "queueHooks"],
                                i = le.timers,
                                a = o ? o.length: 0;
                            for (n.finish = !0, le.queue(this, e, []), r && r.stop && r.stop.call(this, !0), t = i.length; t--;) i[t].elem === this && i[t].queue === e && (i[t].anim.stop(!0), i.splice(t, 1));
                            for (t = 0; a > t; t++) o[t] && o[t].finish && o[t].finish.call(this);
                            delete n.finish
                        })
                }
            }),
            le.each({
                    slideDown: S("show"),
                    slideUp: S("hide"),
                    slideToggle: S("toggle"),
                    fadeIn: {
                        opacity: "show"
                    },
                    fadeOut: {
                        opacity: "hide"
                    },
                    fadeToggle: {
                        opacity: "toggle"
                    }
                },
                function(e, t) {
                    le.fn[e] = function(e, n, o) {
                        return this.animate(t, e, n, o)
                    }
                }),
            le.speed = function(e, t, n) {
                var o = e && "object" == typeof e ? le.extend({},
                    e) : {
                    complete: n || !n && t || le.isFunction(e) && e,
                    duration: e,
                    easing: n && t || t && !le.isFunction(t) && t
                };
                return o.duration = le.fx.off ? 0 : "number" == typeof o.duration ? o.duration: o.duration in le.fx.speeds ? le.fx.speeds[o.duration] : le.fx.speeds._default,
                (null == o.queue || o.queue === !0) && (o.queue = "fx"),
                    o.old = o.complete,
                    o.complete = function() {
                        le.isFunction(o.old) && o.old.call(this),
                        o.queue && le.dequeue(this, o.queue)
                    },
                    o
            },
            le.easing = {
                linear: function(e) {
                    return e
                },
                swing: function(e) {
                    return.5 - Math.cos(e * Math.PI) / 2
                }
            },
            le.timers = [],
            le.fx = X.prototype.init,
            le.fx.tick = function() {
                var e, n = le.timers,
                    o = 0;
                for (Zt = le.now(); n.length > o; o++) e = n[o],
                e() || n[o] !== e || n.splice(o--, 1);
                n.length || le.fx.stop(),
                    Zt = t
            },
            le.fx.timer = function(e) {
                e() && le.timers.push(e) && le.fx.start()
            },
            le.fx.interval = 13,
            le.fx.start = function() {
                $t || ($t = setInterval(le.fx.tick, le.fx.interval))
            },
            le.fx.stop = function() {
                clearInterval($t),
                    $t = null
            },
            le.fx.speeds = {
                slow: 600,
                fast: 200,
                _default: 400
            },
            le.fx.step = {},
        le.expr && le.expr.filters && (le.expr.filters.animated = function(e) {
            return le.grep(le.timers,
                function(t) {
                    return e === t.elem
                }).length
        }),
            le.fn.offset = function(e) {
                if (arguments.length) return e === t ? this: this.each(function(t) {
                    le.offset.setOffset(this, e, t)
                });
                var n, o, r = {
                        top: 0,
                        left: 0
                    },
                    i = this[0],
                    a = i && i.ownerDocument;
                return a ? (n = a.documentElement, le.contains(n, i) ? (typeof i.getBoundingClientRect !== Y && (r = i.getBoundingClientRect()), o = _(a), {
                    top: r.top + (o.pageYOffset || n.scrollTop) - (n.clientTop || 0),
                    left: r.left + (o.pageXOffset || n.scrollLeft) - (n.clientLeft || 0)
                }) : r) : void 0
            },
            le.offset = {
                setOffset: function(e, t, n) {
                    var o = le.css(e, "position");
                    "static" === o && (e.style.position = "relative");
                    var r, i, a = le(e),
                        s = a.offset(),
                        f = le.css(e, "top"),
                        A = le.css(e, "left"),
                        l = ("absolute" === o || "fixed" === o) && le.inArray("auto", [f, A]) > -1,
                        u = {},
                        c = {};
                    l ? (c = a.position(), r = c.top, i = c.left) : (r = parseFloat(f) || 0, i = parseFloat(A) || 0),
                    le.isFunction(t) && (t = t.call(e, n, s)),
                    null != t.top && (u.top = t.top - s.top + r),
                    null != t.left && (u.left = t.left - s.left + i),
                        "using" in t ? t.using.call(e, u) : a.css(u)
                }
            },
            le.fn.extend({
                position: function() {
                    if (this[0]) {
                        var e, t, n = {
                                top: 0,
                                left: 0
                            },
                            o = this[0];
                        return "fixed" === le.css(o, "position") ? t = o.getBoundingClientRect() : (e = this.offsetParent(), t = this.offset(), le.nodeName(e[0], "html") || (n = e.offset()), n.top += le.css(e[0], "borderTopWidth", !0), n.left += le.css(e[0], "borderLeftWidth", !0)),
                            {
                                top: t.top - n.top - le.css(o, "marginTop", !0),
                                left: t.left - n.left - le.css(o, "marginLeft", !0)
                            }
                    }
                },
                offsetParent: function() {
                    return this.map(function() {
                        for (var e = this.offsetParent || W; e && !le.nodeName(e, "html") && "static" === le.css(e, "position");) e = e.offsetParent;
                        return e || W
                    })
                }
            }),
            le.each({
                    scrollLeft: "pageXOffset",
                    scrollTop: "pageYOffset"
                },
                function(e, n) {
                    var o = /Y/.test(n);
                    le.fn[e] = function(r) {
                        return le.access(this,
                            function(e, r, i) {
                                var a = _(e);
                                return i === t ? a ? n in a ? a[n] : a.document.documentElement[r] : e[r] : (a ? a.scrollTo(o ? le(a).scrollLeft() : i, o ? i: le(a).scrollTop()) : e[r] = i, t)
                            },
                            e, r, arguments.length, null)
                    }
                }),
            le.each({
                    Height: "height",
                    Width: "width"
                },
                function(e, n) {
                    le.each({
                            padding: "inner" + e,
                            content: n,
                            "": "outer" + e
                        },
                        function(o, r) {
                            le.fn[r] = function(r, i) {
                                var a = arguments.length && (o || "boolean" != typeof r),
                                    s = o || (r === !0 || i === !0 ? "margin": "border");
                                return le.access(this,
                                    function(n, o, r) {
                                        var i;
                                        return le.isWindow(n) ? n.document.documentElement["client" + e] : 9 === n.nodeType ? (i = n.documentElement, Math.max(n.body["scroll" + e], i["scroll" + e], n.body["offset" + e], i["offset" + e], i["client" + e])) : r === t ? le.css(n, o, s) : le.style(n, o, r, s)
                                    },
                                    n, a ? r: t, a, null)
                            }
                        })
                }),
            le.fn.size = function() {
                return this.length
            },
            le.fn.andSelf = le.fn.addBack,
            "object" == typeof module && module && "object" == typeof module.exports ? module.exports = le: (e.jQuery = e.$ = le, "function" == typeof define && define.amd && define("jquery", [],
                function() {
                    return le
                }))
    } (window),
    function(e) {
        window.PassPortUtil = {
            loginActive: function(e, t, n, o, r, i, a, s, f) {
                $z.encryptFn(e, t, n, o, r, i, a, s, f)
            },
            loginPhone: function(t, n, o, r, i, a, s, f) {
                e.ajax({
                    url: a + "/login_jsonp_phonecode.do?jsonpCallback=?",
                    dataType: "jsonp",
                    data: {
                        appId: t,
                        phonenum: n,
                        code: o,
                        service: i,
                        appLogin: s,
                        countryCode: f
                    },
                    jsonp: "callback",
                    success: function(e) {
                        r(e)
                    },
                    error: function(e) {
                        var t = {
                            result: !1,
                            success: !1,
                            msg: "登录失败,请稍后再试"
                        };
                        r(t)
                    },
                    timeout: 3e3
                })
            },
            logout: function(t, n, o, r, i) {
                var a = o + "/logout.do?appId=" + n + "&isRedirect=" + r + "&callbackName=$z.logoutPassportServer";
                i && (a += "&cbUrl=" + i),
                    e.when($z.logoutAppOne(o), $z.logoutAppThree(o), $z.logoutAppGogoup(o), $z.logoutProHellorf(o), $z.logoutBlogHellorf(o), $z.logoutHellorfPre(o), $z.logoutZDo(o)).done($z.logoutServer(a)).fail(function() {
                        $z.logoutAppOne(o),
                            $z.logoutAppThree(o),
                            $z.logoutAppGogoup(o),
                            $z.logoutProHellorf(o),
                            $z.logoutBlogHellorf(o),
                            $z.logoutHellorfPre(o),
                            $z.logoutZDo(o),
                            $z.logoutServer(a)
                    })
            },
            logoutAppOne: function(e) {
                null != e && e.indexOf("dev") > -1 ? ($z.addScriptTag("http://www.dev.zcool.com.cn/sso/logout_jsonp.do?callback=$z.logoutCallBack&scriptId=ssoscript10061", "ssoscript10061"), $z.addScriptTag("http://my.2017.dev.zcool.com.cn/logout?callback=$z.logoutCallBack&scriptId=ssoscript10171", "ssoscript10171")) : null != e && e.indexOf("test") > -1 ? ($z.addScriptTag("https://my.old.test.zcool.cn/logout?callback=$z.logoutCallBack&scriptId=ssoscript10172", "ssoscript10172"), $z.addScriptTag("https://www-test.zcool.cn/sso/logout_jsonp.do?callback=$z.logoutCallBack&scriptId=ssoscript1006", "ssoscript1006")) : ($z.addScriptTag("https://my.zcool.com.cn/logout?callback=$z.logoutCallBack&scriptId=ssoscript1017", "ssoscript1017"), $z.addScriptTag("https://www.zcool.com.cn/sso/logout_jsonp.do?callback=$z.logoutCallBack&scriptId=ssoscript1006", "ssoscript1006"))
            },
            logoutAppThree: function(e) {
                $z.addScriptTag("https://www.hellorf.com/user/ssologout/?callback=$z.logoutCallBack&scriptId=ssoscript1007", "ssoscript1007")
            },
            logoutAppGogoup: function(e) {
                null != e && e.indexOf("test") > -1 || $z.addScriptTag("http://www.gogoup.com/sso/logout_jsonp?callback=$z.logoutCallBack&scriptId=ssoscript1010", "ssoscript1010")
            },
            logoutProHellorf: function(e) {
                null != e && e.indexOf("test") > -1 || $z.addScriptTag("https://contributor.hellorf.com/login/dologout/?callback=$z.logoutCallBack&scriptId=ssoscript1011", "ssoscript1011")
            },
            logoutBlogHellorf: function(e) {
                null != e && e.indexOf("test") > -1
            },
            logoutHellorfPre: function(e) {
                null != e && e.indexOf("test") > -1
            },
            logoutZDo: function(e) {
                null != e && e.indexOf("test") > -1 || null != e && e.indexOf("dev") > -1;
            },
            logoutServer: function(e) {
                $z.addScriptTag(e)
            },
            logoutCallBack: function(t) {
                var n = !1;
                return t && ("1" == t.retcode ? (n = !0, e("#" + t.scriptId).remove()) : n = !1),
                    n
            },
            logoutPassportServer: function(e) {
                e[0].result && e[0].isRedirect && (window.location.href = e[0].logoutSuccessUrl)
            },
            repeatLoginLogout: function(t, n, o, r, i) {
                e.when($z.logoutAppOne(o), $z.logoutAppThree(o), $z.logoutAppGogoup(o), $z.logoutProHellorf(o), $z.logoutBlogHellorf(o), $z.logoutHellorfPre(o), $z.logoutZDo(o)).done(function() {
                    i && (document.location.href = i)
                }).fail(function() {
                    $z.logoutAppOne(o),
                        $z.logoutAppThree(o),
                        $z.logoutAppGogoup(o),
                        $z.logoutProHellorf(o),
                        $z.logoutBlogHellorf(o),
                        $z.logoutHellorfPre(o),
                        $z.logoutZDo(o)
                })
            },
            repeatLoginLogoutCb: function(t, n, o, r, i, a) {
                e.when($z.logoutAppOne(o), $z.logoutAppThree(o), $z.logoutAppGogoup(o), $z.logoutProHellorf(o), $z.logoutBlogHellorf(o), $z.logoutHellorfPre(o), $z.logoutZDo(o)).done(function() {
                    a(i)
                }).fail(function() {
                    $z.logoutAppOne(o),
                        $z.logoutAppThree(o),
                        $z.logoutAppGogoup(o),
                        $z.logoutProHellorf(o),
                        $z.logoutBlogHellorf(o),
                        $z.logoutHellorfPre(o),
                        $z.logoutZDo(o)
                })
            },
            reloadPage: function() {
                window.location.reload()
            },
            addScriptTag: function(t, n) {
                var o = '<script id="' + n + '" type="text/javascript" src="' + t + '"></script>';
                e(o).appendTo(e("body"))
            },
            getServerCookie: function(t, n) {
                e.getJSON(t + "/get_server_ec_jsonp.do?jsonpCallback=?&t=" + (new Date).getTime(),
                    function(e) {
                        n(e)
                    })
            },
            getUserSession: function(t, n) {
                e.getJSON(t + "/get_user_session_jsonp.do?jsonpCallback=?&t=" + (new Date).getTime(),
                    function(e) {
                        n(e)
                    })
            },
            getCookie: function(e) {
                return document.cookie.length > 0 && (c_start = document.cookie.indexOf(e + "="), c_start != -1) ? (c_start = c_start + e.length + 1, c_end = document.cookie.indexOf(";", c_start), c_end == -1 && (c_end = document.cookie.length), unescape(document.cookie.substring(c_start, c_end))) : ""
            },
            encryptFn: function(t, n, o, r, i, a, s, f, A) {
                e.ajax({
                    url: f + "/passKey.do?jsonpCallback=?",
                    dataType: "jsonp",
                    success: function(l) {
                        if (null != l) {
                            setMaxDigits(130);
                            var u = encryptedString(new RSAKeyPair(l.a, "", l.b), o);
                            e.ajax({
                                url: f + "/login_jsonp_active.do?jsonpCallback=?",
                                dataType: "jsonp",
                                data: {
                                    appId: t,
                                    username: n,
                                    password: u,
                                    autoLogin: r,
                                    code: i,
                                    service: s,
                                    appLogin: A
                                },
                                jsonp: "callback",
                                success: function(e) {
                                    a(e)
                                },
                                error: function(e) {
                                    var t = {
                                        result: !1,
                                        success: !1,
                                        msg: "登录失败,请稍后再试"
                                    };
                                    a(t)
                                },
                                timeout: 3e3
                            })
                        }
                    },
                    error: function(e) {
                        var t = {
                            result: !1,
                            success: !1,
                            msg: "登录超时,请稍后再试"
                        };
                        a(t)
                    },
                    timeout: 6e3
                })
            }
        },
            window.$z = window.PassPortUtil
    } (window.jQuery);
var biRadixBase = 2,
    biRadixBits = 16,
    bitsPerDigit = biRadixBits,
    biRadix = 65536,
    biHalfRadix = biRadix >>> 1,
    biRadixSquared = biRadix * biRadix,
    maxDigitVal = biRadix - 1,
    maxInteger = 9999999999999998,
    maxDigits, ZERO_ARRAY, bigZero, bigOne;
setMaxDigits(20);
var dpl10 = 15,
    lr10 = biFromNumber(1e15),
    hexatrigesimalToChar = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"),
    hexToChar = new Array("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"),
    highBitMasks = new Array(0, 32768, 49152, 57344, 61440, 63488, 64512, 65024, 65280, 65408, 65472, 65504, 65520, 65528, 65532, 65534, 65535),
    lowBitMasks = new Array(0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535),
    RSAAPP = {};
RSAAPP.NoPadding = "NoPadding",
    RSAAPP.PKCS1Padding = "PKCS1Padding",
    RSAAPP.RawEncoding = "RawEncoding",
    RSAAPP.NumericEncoding = "NumericEncoding",
    /*!
* jQuery Cookie Plugin
* https://github.com/carhartl/jquery-cookie
*
* Copyright 2011, Klaus Hartl
* Dual licensed under the MIT or GPL Version 2 licenses.
* http://www.opensource.org/licenses/mit-license.php
* http://www.opensource.org/licenses/GPL-2.0
*/
var QRCode;

var html_encode = function(e) {
        return e.replace(/</g, "&lt;").replace(/>/g, "&gt;")
    },
    cancelbuble = function(e) {
        e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = !0
    },
    ZAjax = function(e, t, n) {
        return new Promise(function(o, r) {
            var i = n ? n.params: {};
            $.ajax({
                url: t,
                type: e || "get",
                data: i || "",
                xhrFields: {
                    withCredentials: !0
                },
                crossDomain: !0,
                headers: {
                    "X-Requested-With": "XMLHttpRequest"
                },
                dataType: "json",
                contentType: "application/json",
                success: function(e) {
                    0 == e.code || "SUCCESS" === e.resultCode ? o(e) : r(e)
                },
                error: function(e) {
                    r(e),
                        console.log(e)
                }
            })
        })
    },
    reTtime = 0;
$(function() {
    function e(e) {
        var t = (new Date).getTime();
        i = t;
        var n = "",
            o = "";
        $.ajax({
            type: "GET",
            url: proMainZDomain + "/nav/getMatchedWordsAndDesigners.json",
            data: {
                word: e
            },
            dataType: "json",
            success: function(a) {
                if (i == t) {
                    o = a,
                        $(".search-content-list").empty();
                    for (var s = 0; s < o.data.mws.length; s++) {
                        var f = $("<div>" + o.data.mws[s] + "</div>").text();
                        n += "<div class='hot-list search-l'><a href='" + r.searchContentUrl + "?&word=" + f + "' z-st='nav_searchbox_word'>" + o.data.mws[s] + "</a></div>"
                    }
                    o.data.mds.length > 0 && (n += "<div class='search-title'>" + messagesWeb.nav_search_designer + "</div>");
                    for (var s = 0; s < o.data.mds.length; s++) n += "<div class='hot-list search-l hot-list-designer'><a href=" + o.data.mds[s].pageUrl + " z-st='nav_searchbox_designer'><img src=" + o.data.mds[s].avatar + " />" + o.data.mds[s].username + "</a></div>";
                    n += "<div class='search-l'><a href='" + r.searchContentUrl + "?&word=" + e + "' data-search='" + e + "' z-st='nav_searchbox_all' class='check-all'>" + messagesWeb.nav_see_search_result + "</a></div>",
                        $(".search-content-list").append(n),
                        r.enter = 0,
                        r.derail = 0,
                        r.keyboardControlIndex = -1
                } else console.log("ignore")
            }
        }),
            $.each($(".header-topbar-searchbar-about"),
                function(t, n) {
                    $(n).attr("href", $(n).attr("href") + e)
                })
    }
    function t(e) {
        $.ajax({
            type: "GET",
            url: proMainZDomain + "/nav/getRecentWordAndHotWords.json",
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(t) {
                e(t)
            }
        })
    }
    function n(e) {
        var t = $.trim($("#nav-search-ipt").val());
        "" !== t && window.open(e, "_self")
    }
    function o() {
        var e = (new Date).getTime();
        $.ajax({
            type: "GET",
            url: proMainZDomain + "/nav/topTs?_t=" + e,
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(e) {
                var t = e.data,
                    n = $.cookie("top_ts1");
                window.location.href.indexOf("top") != -1 ? $.cookie("top_ts1", escape(t), {
                    expires: 30,
                    path: "/",
                    domain: "." + zRootDomain
                }) : t && t > n && ($("#header-more").append('<sup class="subnav-dot-sup"></sup>'), $("#header-more-top").append('<sup class="subnav-dot-sup"></sup>'))
            }
        })
    }
    var r = {
        tagNum: null,
        derail: 0,
        keyboardControlIndex: -1,
        enter: 0,
        wordHtml: null,
        domainPath: "",
        searchContentUrl: proMainZDomain + "/search/content"
    };
    $(".search").on("click",
        function(e) {
            cancelbuble(e);
            var t = $(this);
            $("#nav-search-ipt").focus(),
            $(this).attr("disabled") || ($(".header-menu-bar .menu-list-content").fadeOut("slow"), $(".search-input-hull").fadeIn("slow"), t.fadeOut("slow"), $("#nav-search-ipt").focus(), $.ajax({
                type: "GET",
                url: proMainZDomain + "/nav/getRecentWordAndHotWords.json",
                xhrFields: {
                    withCredentials: !0
                },
                crossDomain: !0,
                headers: {
                    "X-Requested-With": "XMLHttpRequest"
                },
                dataType: "json",
                success: function(e) {
                    var n = "";
                    if (e.data.rws.length > 0) {
                        n += "<div class='search-title'>" + messagesWeb.nav_recent_s + "</div>";
                        for (var o = 0; o < e.data.rws.length; o++) n += "<div class='hot-list search-l'><a href='" + r.searchContentUrl + "?&word=" + encodeURIComponent(e.data.rws[o]) + "' z-st='nav_searchbox_hotword'>" + e.data.rws[o] + "</a></div>"
                    }
                    n += "<div class='search-title'>" + messagesWeb.nav_hot_s + "</div>";
                    for (var o = 0; o < e.data.hws.length; o++) n += "<div class='hot-list search-l'><a href='" + r.searchContentUrl + "?&word=" + encodeURIComponent(e.data.hws[o]) + "' z-st='nav_searchbox_hotdesigner'>" + e.data.hws[o] + "</a></div>";
                    $(".search-content-list").append(n),
                        t.attr("disabled", !0)
                }
            }))
        });
    var i, a = function() {
        var o = !0;
        $("#nav-search-ipt").on("keydown paste",
            function(e) {
                if (cancelbuble(e), 38 != e.keyCode && 40 != e.keyCode || e.preventDefault(), 13 == e.keyCode && o && 0 == r.enter) {
                    var t = $.trim($(this).val());
                    n(r.searchContentUrl + "?&word=" + encodeURIComponent(t))
                }
            }).on("compositionstart",
            function() {
                o = !1
            }).on("compositionend",
            function() {
                o = !0
            }).on("keyup",
            function(n) {
                if (38 != n.keyCode && 40 != n.keyCode && 37 != n.keyCode && 39 != n.keyCode) if ("" != $(this).val()) {
                    var o = $.trim($(this).val());
                    setTimeout(function() {
                            e(o)
                        },
                        10)
                } else {
                    var i = function(e) {
                        $(".search-content-list").empty();
                        var t = "";
                        if (e.data.rws.length > 0) {
                            t += "<div class='search-title'>" + messagesWeb.nav_recent_s + "</div>";
                            for (var n = 0; n < e.data.rws.length; n++) t += "<div class='hot-list search-l'><a href='" + r.searchContentUrl + "?&word=" + encodeURIComponent(e.data.rws[n]) + "'>" + e.data.rws[n] + "</a></div>"
                        }
                        t += "<div class='search-title'>" + messagesWeb.nav_hot_s + "</div>";
                        for (var n = 0; n < e.data.hws.length; n++) t += "<div class='hot-list search-l'><a href='" + r.searchContentUrl + "?&word=" + encodeURIComponent(e.data.hws[n]) + "'>" + e.data.hws[n] + "</a></div>";
                        $(".search-content-list").append(t)
                    };
                    t(i)
                }
            })
    };
    if (a(), $("#nav-search-ipt").on("keyup",
        function(e) {
            var t = e || window.event || arguments.callee.caller.arguments[0];
            switch (t.keyCode) {
                case 38:
                    r.keyboardControlIndex--,
                    r.keyboardControlIndex <= -1 && 1 == r.derail && (r.keyboardControlIndex = 0),
                        r.keyboardControlIndex >= 0 ? ($(".search-content-list .search-l").eq(r.keyboardControlIndex).addClass("active").siblings().removeClass("active"), r.wordHtml = $(".search-content-list .search-l a").eq(r.keyboardControlIndex).attr("href"), r.enter = 1, 0 != $(".search-content-list .check-all").parents(".search-l").index() && $("#nav-search-ipt").val($(".search-content-list .search-l a").eq(r.keyboardControlIndex).text())) : r.keyboardControlIndex = -1,
                        t.preventDefault();
                    break;
                case 40:
                    r.keyboardControlIndex++,
                    r.keyboardControlIndex >= $(".search-content-list .search-l").length && (r.keyboardControlIndex = $(".search-content-list .search-l").length - 1),
                        $(".search-content-list .check-all").length > 0 && r.keyboardControlIndex == $(".search-content-list .search-l").length - 1 ? (r.wordHtml = $(".search-content-list .search-l a").eq(r.keyboardControlIndex).attr("href"), $("#nav-search-ipt").val($(".search-content-list .search-l a").eq(r.keyboardControlIndex).attr("data-search"))) : (r.wordHtml = $(".search-content-list .search-l a").eq(r.keyboardControlIndex).attr("href"), $("#nav-search-ipt").val($(".search-content-list .search-l a").eq(r.keyboardControlIndex).text())),
                        $(".search-content-list .search-l").eq(r.keyboardControlIndex).addClass("active").siblings().removeClass("active"),
                        r.enter = 1,
                        r.derail = 1,
                        t.preventDefault()
            }
            13 == t.keyCode && 1 == r.enter && n(r.wordHtml)
        }), $(".search-ipt").on("click",
        function() {
            var e = $.trim($("#nav-search-ipt").val());
            n(r.searchContentUrl + "?&word=" + encodeURIComponent(e))
        }), $(".search-input-hull .search-cancel").on("click",
        function(e) {
            cancelbuble(e),
                $(this).parent().fadeOut("slow"),
                $(".header-menu-bar .menu-list-content").fadeIn("slow"),
                $(".search").fadeIn("slow").removeAttr("disabled"),
                $(".search-content-list").empty(),
                $(".correl-link").addClass("hide"),
                $("#nav-search-ipt").val(""),
                r.enter = 0,
                r.derail = 0,
                r.keyboardControlIndex = -1
        }), $("#nav-search-ipt").on("click",
        function(e) {
            cancelbuble(e)
        }), $("body").on("click",
        function() {
            $(".header-menu-bar .menu-list-content").fadeIn("slow"),
                $(".search").fadeIn("slow").removeAttr("disabled"),
                $(".search-content-list").empty(),
                $(".correl-link").addClass("hide"),
                $("#nav-search-ipt").val(""),
                $(".search-input-hull").fadeOut("slow"),
                $(".menu-list-content").fadeIn("slow")
        }), $(".js-search-keywords").attr("search-keywords")) {
        var s = function(e) {
            if (e.data.hws.length) {
                for (var t = "<span>大家都在搜：</span>",
                         n = 0; n < e.data.hws.length; n++) t += "<a href='" + r.searchContentUrl + "?&word=" + e.data.hws[n] + "'>" + e.data.hws[n] + "</a>";
                $(".js-search-keywords").html(t),
                    $("#search-word").val(e.data.hws[0])
            }
        };
        t(s)
    }
    var f = !0;
    $(".user-center .message .message-list").on("click",
        function(e) {
            cancelbuble(e),
                1 == f ? ($(this).parent(".message").addClass("current-style"), $(this).parent(".message").find(".message-box").removeClass("hide"), f = !1, $(".message-list").text() && $.ajax({
                    type: "DELETE",
                    url: proMyZDomain + "/nav/cleanMsgBoxTipNum.json",
                    xhrFields: {
                        withCredentials: !0
                    },
                    crossDomain: !0,
                    headers: {
                        "X-Requested-With": "XMLHttpRequest"
                    },
                    dataType: "json",
                    success: function(e) {
                        0 == e.code && $(".user-center .message .message-list sup").remove()
                    }
                })) : 0 == f && ($(this).parent(".message").removeClass("current-style"), $(this).parent(".message").find(".message-box").addClass("hide"), f = !0)
        }),
        $(document).on("click",
            function(e) {
                if (cancelbuble(e), "SECTION" == e.target.nodeName || "LI" == e.target.nodeName) {
                    var t = !1;
                    return $(e.target).parents().each(function(e, n) {
                        $(n).hasClass("article-content-wraper") && (t = !0)
                    }),
                        !!t
                }
                $(".message-box").addClass("hide"),
                    $(".message").removeClass("current-style"),
                    f = !0
            }),
        $(".clean-box-msg").on("click",
            function(e) {
                function t() {
                    $.ajax({
                        type: "DELETE",
                        url: proMyZDomain + "/nav/cleanMsgBox.json",
                        xhrFields: {
                            withCredentials: !0
                        },
                        crossDomain: !0,
                        headers: {
                            "X-Requested-With": "XMLHttpRequest"
                        },
                        dataType: "json",
                        success: function(e) {
                            $(".mCSB_container").empty(),
                                $("#contetn-2").append($("#empty_message_box")),
                                $("#clean-box-msg").hide()
                        }
                    })
                }
                cancelbuble(e),
                    $(".message-box").addClass("hide"),
                    pageConfirm(messagesWeb.nav_message_box_empty, "", t)
            }),
        o(),
        $(".nav-unlogin").on("click",
            function() {
                var e = $(".nav-unlogin").attr("data-href"),
                    t = window.location.href;
                $("nav li:first.current a").length ? window.location.href = e + "&cback=" + proMyZDomain + "/focus/activity": window.location.href = e + "&cback=" + t
            }),
        $(".js-header_upload a").on("click",
            function() {
                loginUploadEditEntryLocation($(".js-header_upload a").attr("data-upload")),
                islogin() || $(".js-header_upload a").attr("data-click-login", "1")
            }),
        uploadPositionTips();
    var A = function(e) {
        var t = "en_US" === zlanguage ? e.en: e.cn,
            n = t.main,
            o = n.text,
            r = n.link,
            i = n.target,
            a = t.sub;
        return '\n    <a href="' + r + '" target="' + i + '" class="menu-list-box">' + o + '</a>\n    <div class="menu-list hide">\n      ' + a.map(function(e) {
            return '<p><a href="' + e.link + '" target="' + e.target + "\" style='display: flex;'>" + e.text + "\n        " + (e.icon && '<span style="margin-left: 2px;"><img src=' + e.icon + " width=" + e.icon_width + " /></span>") + "\n      </a></p>"
        }).join("") + "\n    </div>\n  "
    };
    ZAjax("get", serverApi + "/zcoolNavigationBarHellorf").then(function(e) {
        $(".zhengban").html(A(e.data))
    }),
        ZAjax("get", serverApi + "/zcoolNavigationBarGogoup").then(function(e) {
            $(".gogoup-course").html(A(e.data))
        }),
        ZAjax("get", serverApi + "/zcoolNavigationBarZcoolip").then(function(e) {
            $(".header-copyright").html(A(e.data))
        })
});
var ZunloginedUpdateStatusCbs = new Array,
    vi = (new Date).getTime(),
    ti = (new Date).getTime(),
    zIsProd = zMainDomain.indexOf("test") === -1 && zMainDomain.indexOf("dev") === -1,
    ZProtocol = zProtocol,
    proMyZDomain = zProtocol + zMyDomain,
    proMainZDomain = zProtocol + zMainDomain,
    proStaticZDomain = zProtocol + staticDomain,
    serverZLog = zProtocol + ("undefined" != typeof logDomain ? logDomain: "log.dev.zcool.cn"),
    serverZimg = zProtocol + "img.zcool.cn/",
    proUploadZDomain = zProtocol + zUploadDomain,
    mobileDomain = zIsProd ? "https://m.zcool.com.cn": "https://m-test.zcool.cn",
    proPassportZDomain = zProtocol + passportDomain,
    proHellorfDomain = zProtocol + "www." + hellorfDomain,
    serverApi = zIsProd ? "https://api.zcool.com.cn/v2/api": "https://api-test.zcool.cn/v2/api",
    proGogoupDomain = zProtocol + "www." + gogoupDomain,
    gogoupApi = "https://api.gogoup.com",
    zcoolAwardDomain = zIsProd ? "https://awards.zcool.com.cn": "https://awards-test.zcool.cn",
    zui = "zui",
    zlanguage = "function" == typeof getLanguage ? getLanguage() : "",
    uid = getUid(),
    staticLoaddingSrc = proStaticZDomain + "/z/images/common/page_loading.gif",
    LoaddingDom = '<div id="ajaxfeed-loading" style="text-align: center;"><img src="' + staticLoaddingSrc + '"></div>';
window.wxQrcodeUrl = zMainDomain.indexOf("test") != -1 ? mobileDomain + "/wxQrcode?from=zcooltest": mobileDomain + "/wxQrcode";
var isMac = function() {
        return /macintosh|mac os x/i.test(navigator.userAgent)
    },
    isWindows = function() {
        return /windows|win32/i.test(navigator.userAgent)
    };
isMac() && $("body").addClass("mac");
var remindBindFlag = !0; !
    function(e) {
        e.fn.hoverDelay = function(t) {
            var n = {
                    hoverDuring: 500,
                    outDuring: 200,
                    clickOutDuring: 0,
                    hoverEvent: function() {
                        e.noop()
                    },
                    outEvent: function() {
                        e.noop()
                    }
                },
                o = e.extend(n, t || {});
            return e(this).each(function() {
                var t, n;
                e(this).hover(function(e) {
                        var r = e,
                            i = this;
                        clearTimeout(n),
                            t = setTimeout(function() {
                                    o.hoverEvent(r, i)
                                },
                                o.hoverDuring)
                    },
                    function(e) {
                        var r = e,
                            i = this;
                        clearTimeout(t),
                            n = setTimeout(function() {
                                    o.outEvent(r, i)
                                },
                                o.outDuring)
                    }),
                    e(".showMemberCard > a").on("click",
                        function(e) {
                            var n = e,
                                r = this;
                            clearTimeout(t),
                                o.outEvent(n, r)
                        })
            })
        }
    } (jQuery),
    jQuery.support.cors = !0,
    function(e) {
        var t = 229,
            n = '<img src="' + proStaticZDomain + '/z/images/svg/card-info-loading.svg" class="loadding-card" style="height: 150px;" >',
            o = function(e) {
                e.find(".author-info-card").removeClass("hide")
            };
        e.fn.showCardInfo = function(r) {
            var i = function(t) {
                var r = t.find(".author-info-card"),
                    i = r.html();
                if ("undefined" == typeof i || "" == e.trim(i)) {
                    r.html(n),
                        o(t);
                    var a = r.data("id");
                    ZAjax("get", proMainZDomain + "/member/card/" + a).then(function(t) {
                        var n = template("author-info-templ", {
                            data: t.data
                        });
                        r.html(n),
                            e(".author-info-card .js-project-focus-btn").append(zcoolFocusLoadingTpl)
                    })
                } else o(t)
            };
            e(this).find(".showMemberCard").hoverDelay({
                hoverEvent: function(n, o) {
                    var a = r || e(o),
                        s = a.find("> a img");
                    a.offset().top - e(window).scrollTop() < t ? a.find(".author-info-card").css({
                        top: s.position().top + s.innerHeight() + 10 + "px",
                        bottom: "auto"
                    }).addClass("triangle_top") : a.find(".author-info-card").css({
                        top: "initial",
                        bottom: "49px"
                    }).removeClass("triangle_top"),
                        i(a)
                },
                outEvent: function(t, n) {
                    var o = r || e(n);
                    o.parent().find(".author-info-card").addClass("hide")
                }
            })
        }
    } (jQuery),
    $(".js-hover-show-avainfo").showCardInfo(),
    $(".card-box, .album-card-box").showCardInfo();
var compuTitWidth = function() {
    var e = $(".card-info-title").width(),
        t = e - 42,
        n = e - 24;
    $(".card-box").each(function(o, r) {
        var i = $(r).find(".card-info-title").children(".title-icon").length;
        0 == i ? $(r).find(".title-content").css({
            width: e + "px"
        }) : 1 == i ? $(r).find(".title-content").css({
            width: n + "px"
        }) : 2 == i && $(r).find(".title-content").css({
            width: t + "px"
        })
    })
};
compuTitWidth(),
    $(window).scroll(function() {
        if ($(document).scrollTop() > document.body.scrollHeight - document.body.clientHeight - 100 && $(".work-list-box.hide").length > 0) {
            var e = 300 + parseInt(700 * Math.random());
            $("#page-loading").show(),
                setTimeout(function() {
                        $("#page-loading").hide(),
                            $(".work-list-box.hide").removeClass("hide"),
                            $(".pageturning.hide").removeClass("hide")
                    },
                    e)
        }
    }),
    window.onresize = function() {
        compuTitWidth()
    },
    $(".pop-up .pop-cancel,.pop-up .pop-close").on("click",
        function() {
            $(this).parents(".pop-up").hide(),
                hideGlobalMaskLayer()
        }),
    function(e) {
        e.zCheckbox = function(t) {
            var n = {
                    click: function() {
                        e.noop()
                    }
                },
                o = e.extend(n, t || {});
            e("body").on("click", "label.label-checkbox",
                function() {
                    checkboxChecked(e(this)),
                        o.click(e(this).find("input[type='checkbox']"))
                })
        }
    } (jQuery),
    $.zCheckbox(),
    $(".more-icon").on("click",
        function() {
            var e = $(this).parents("a").attr("href");
            window.open(e)
        });
var computed = {
    computedFnTit: function() {
        return myFunc = function() {
            $(".author-info-title-box").each(function(e, t) {
                var n = $(t).children(".author-info-title").width() + $(t).children(".author-info-fire").width(),
                    o = $(".author-info-title-box").width() - $(t).children(".author-info-fire").width() - 10 + "px";
                n >= $(".author-info-title-box").width() && $(t).children(".author-info-title").css("max-width", o).addClass("ellipsis")
            })
        }
    },
    compareWidth: function() {
        this.computedFnTit()()
    }
};
$(".card-designer-list") && computed.compareWidth();
var attentionClickAfterCaozuo = function(e, t, n) {
    var o, r, i, e = e,
        a = createFollow.followstatus(e),
        s = islogin(),
        f = 1,
        A = e.attr("data-id"),
        l = e.parents(".author-info"),
        u = e.parents(".author-info-card");
    if (l.length > 0) {
        A = A || l.attr("data-id");
        var c = l.attr("data-type");
        c && (f = c)
    } else A = u.length > 0 ? A || u.attr("data-id") : A;
    f = e.attr("data-type") || f,
        a ? (o = function(n) {
            "function" == typeof t ? t(n, e) : settingFollow(n, e)
        },
            r = function(t) {
                pageToastFail(t),
                    cancelLoadingFocusDis(e),
                e.attr("data-val") && e.val(e.attr("data-val"))
            }) : (o = function(n) {
            "function" == typeof t ? t(n, e) : settingFollow(n, e)
        },
            r = function(t) {
                cancelLoadingFocusDis(e),
                e.attr("data-val") && e.val(e.attr("data-val"))
            });
    var p = function() {
        i = new createFollow(A, f, o, r, a),
            i.doOrCancelFollow()
    };
    if (s) loadingFocusDis(e),
        p();
    else {
        var g = function() {
                p()
            },
            d = function() {
                "function" == typeof n && n()
            },
            h = [g, d];
        openLoginWindow(h)
    }
};
asyncAttentionCall($(".card-box")),
    loadimg($(".work-show-item img"), funloading_obj);
var zcoolFocusLoadingTpl = '<div class="js-focus-loadding-btn focus-loadding-y hide"><div></div><div></div><div></div></div>';
$("body .js-project-focus-btn").append(zcoolFocusLoadingTpl),
    loaddingDomAdd(".js-c-loading-cover"),
    $("body").on("click", ".js-upload",
        function() {
            loginUploadEditEntryLocation($(this).attr("data-url"))
        }),
    $("body").on("click", ".js-edit-entry",
        function() {
            var e = $("#dataInput").attr("data-objtype"),
                t = $("#dataInput").attr("data-objid"),
                n = proMyZDomain + ("3" === e ? "/editProduct?id=": "/editArticle?id=") + t;
            loginUploadEditEntryLocation(n)
        }),
    Popup.prototype = {
        organDom: function() {
            var e = "",
                t = this;
            return e += "<div class='mask-layer' id='maskLayer' class=''>",
                e += "</div>",
                e += "<div class='popup-box' style='width: " + t.popupWidth + "px'>",
                e += "<p class='popup-tit'>" + t.tit + "<span id='close_btn'></span><span id='popup-header-close'></span></p>",
                e += "<div class='popup-content'>" + t.content + "</div>",
                e += "<section class='classify-select'>" + t.component + "</section>",
            "" != t.richText && (e += "<p class='rich-text'>" + t.richText + "</p>"),
                e += "<div class='btn'>",
            "" != t.btnInfo1 && (e += "<input type='button' value='" + t.btnInfo1 + "' id='btn_yes' class='btn-default-main btn-current-middle mr-20'/>"),
            "" != t.btnInfo2 && (e += "<input type='button' value='" + t.btnInfo2 + "' id='btn_cancel' class='btn-default-secondary btn-current-middle'/>"),
                e += "</div>",
                e += "</div>"
        },
        showDom: function() {
            var e = this;
            $("body").append(e.organDom()),
                e.initEvent()
        },
        initEvent: function() {
            var e = this;
            $("#popup-header-close,#btn_cancel").add($(".mask-layer")).on("click", e.callBack),
                $("#btn_yes").on("click",
                    function(t) {
                        var n = $(this);
                        t.stopPropagation(),
                            e.callBackFn(n),
                            e.callBack()
                    })
        },
        callBack: function() {
            var e = $(this);
            e.parents().find(".popup-box").prev().find(".mask-layer").remove(),
                e.parents().find(".popup-box").remove(),
                $(".mask-layer").remove(),
                $(".popup-box").remove()
        },
        callBackFn: function(e) {
            var t = this;
            t.fn(e)
        }
    },
    Tip.prototype = {
        organDom: function() {
            var e = "",
                t = this;
            return e += "<div id='tip'>",
                e += "<p>" + t.content + "</p>",
                e += "<div>"
        },
        showDom: function() {
            var e = this;
            $("body").append(e.organDom()),
                e.initEvent()
        },
        initEvent: function() {
            var e = this;
            setTimeout(function() {
                    e.callBack()
                },
                2e3)
        },
        callBack: function() {
            $("#tip").remove(),
            $("#maskLayer").remove()
        }
    },
    function(e) {
        function t(e, t) {
            for (var n in t) e[n] = t[n];
            return e
        }
        function n(e) {
            return t(f, e)
        }
        function o(e, t) {
            return t && n(t),
            !(!e || "object" == typeof e) && ($(e).on("click",
                function() {
                    var e = $(this).find(".js-select-con");
                    return ! $(this).hasClass("disabled") && (e.is(":visible") ? e.hide() : (e.show(), "function" == typeof f.updateListClick && f.updateListClick()), void cancelbuble())
                }), $(e + " .js-select-con li").on("click",
                function() {
                    var e = $(this).parents(".js-select-con"),
                        t = $(this).parents(".js-select-container").find(".js-seleted"),
                        n = $(this).text();
                    t.text(n),
                        $(this).addClass("active").siblings("li").removeClass("active"),
                        e.hide(),
                    "function" == typeof f.click && f.click($(this)),
                        cancelbuble()
                }), void $(document).on("click",
                function() {
                    $(e + " .js-select-con").hide()
                }))
        }
        $(function() {
            selectFilter()
        }),
            ShowInfo.prototype = {
                showDom: function() {
                    function e(e) {
                        e.parents(".parents-nodes").children(o.domStr).addClass("hide")
                    }
                    var t = !0,
                        n = !0,
                        o = this;
                    o.domParent.on("mouseenter", o.domChildren,
                        function(e) {
                            e.stopPropagation();
                            var t = $(this);
                            n = setTimeout(function() {
                                    t.parents(".parents-nodes").children(o.domStr).removeClass("hide")
                                },
                                300)
                        }),
                        o.domParent.on("mouseleave", o.domChildren,
                            function(o) {
                                clearTimeout(n),
                                    o.stopPropagation();
                                var r = $(this);
                                t = setTimeout(function() {
                                        e(r)
                                    },
                                    300)
                            }),
                        $(o.domStr).on("mouseenter",
                            function(e) {
                                e.stopPropagation(),
                                    clearTimeout(t)
                            }),
                        $(o.domStr).on("mouseleave",
                            function(n) {
                                n.stopPropagation();
                                var o = $(this);
                                t = setTimeout(function() {
                                        e(o)
                                    },
                                    300)
                            })
                }
            };
        var r = $(".card-info"),
            i = ".card-info-item-recommendhover",
            a = ".recommend-menu",
            s = new ShowInfo(r, i, a);
        s.showDom();
        var f = {
            updateListClick: $.noop(),
            click: $.noop()
        };
        e.select = {
            firstHaveBgClick: o
        }
    } (window),
    window.addEventListener("message",
        function(e) {
            "loginSuccess" == e.data && getPassportEC()
        },
        !1),
    window.zcoolLoginSuccessUpdatePageCb,
    $(function() {
        getUid() <= 0 ? ("function" == typeof logout_update_unlogined_nav && logout_update_unlogined_nav(), getPassportEC()) : "function" == typeof update_logined_nav && update_logined_nav()
    }),
    function(e) {
        e.fn.initTextareaStyle = function(t) {
            var n = {
                    height: 42
                },
                o = e.extend(n, t);
            e(this).length && ("" !== e(this).val() ? e(this)[0].scrollHeight > 130 ? e(this).css({
                overflow: "auto",
                height: "130px"
            }) : e(this)[0].scrollHeight < 130 && e(this)[0].scrollHeight > 42 ? e(this).css("height", e(this)[0].scrollHeight + "px") : e(this).css("height", o.height + "px") : e(this).css("height", o.height + "px"))
        }
    } (jQuery),
    $(".textarea-style").initTextareaStyle(),
    $(".textarea-style").on("propertychange", MaxMe),
    function(e) {
        e.fn.charCount = function(t) {
            function n(n) {
                function o() {
                    for (var o = 0,
                             r = 0,
                             i = 0; i < e(n).val().length; i++) if (e(n).val().charCodeAt(i) > 255 ? (o += 2, r++) : (o++, r++), o == t.allowed) return r
                }
                for (var r = 0,
                         i = 0,
                         a = (new RegExp("[\\u4E00-\\u9FFF]+", "g"), 0); a < e(n).val().length; a++) e(n).val().charCodeAt(a) > 255 ? (r += 2, i++) : (r++, i++);
                var s = t.allowed - r;
                s < t.warning && s >= 0 ? (e(n).next().addClass(t.cssWarning), e(n).addClass("borderred")) : (e(n).next().removeClass(t.cssWarning), e(n).removeClass("borderred")),
                    s < 0 ? (e(n).next().addClass(t.cssExceeded), e(n).val(e(n).val().substr(0, o())), e(n).val().length = t.allowed, e(n).addClass("borderred")) : e(n).next().removeClass(t.cssExceeded);
                var f = 0;
                s > f && e(n).next().removeClass(t.cssExceeded),
                    t.useWarningCount ? e(n).next().html(t.counterText + t.allowed - r - t.warning) : (s < 0 && (e(n).removeClass("borderred"), e(n).next().removeClass(t.cssExceeded), s = 0), e(n).next().html(t.counterText + s))
            }
            var o = {
                    useWarningCount: !1,
                    allowed: 140,
                    warning: 25,
                    css: "counter",
                    counterElement: "span",
                    cssWarning: "warning",
                    cssExceeded: "exceeded",
                    counterText: ""
                },
                t = e.extend(o, t);
            return this.each(function() {
                e(this).after("<" + t.counterElement + ' class="' + t.css + '">' + t.counterText + "</" + t.counterElement + ">"),
                    n(this),
                    e(this).keyup(function() {
                        n(this)
                    }),
                    e(this).change(function() {
                        n(this)
                    }),
                    e(this).blur(function() {
                        n(this)
                    })
            }),
                t
        }
    } (jQuery),
    function(e) {
        function t(o) {
            if (n[o]) return n[o].exports;
            var r = n[o] = {
                i: o,
                l: !1,
                exports: {}
            };
            return e[o].call(r.exports, r, r.exports, t),
                r.l = !0,
                r.exports
        }
        var n = {};
        return t.m = e,
            t.c = n,
            t.i = function(e) {
                return e
            },
            t.d = function(e, n, o) {
                t.o(e, n) || Object.defineProperty(e, n, {
                    configurable: !1,
                    enumerable: !0,
                    get: o
                })
            },
            t.n = function(e) {
                var n = e && e.__esModule ?
                    function() {
                        return e["default"]
                    }: function() {
                        return e
                    };
                return t.d(n, "a", n),
                    n
            },
            t.o = function(e, t) {
                return Object.prototype.hasOwnProperty.call(e, t)
            },
            t.p = "",
            t(t.s = 3)
    } ([function(e, t, n) {
        "use strict";
        var o = "20190705",
            r = [{
                title: "常用表情",
                emoji: [{
                    z_daku: "z大哭"
                },
                    {
                        z_daxiao: "z大笑"
                    },
                    {
                        z_dongxin: "z动心"
                    },
                    {
                        z_fennu: "z愤怒"
                    },
                    {
                        z_guzhang: "z鼓掌"
                    },
                    {
                        z_guancha: "z观察"
                    },
                    {
                        z_huanhu: "z欢呼"
                    },
                    {
                        z_jiayou: "z加油"
                    },
                    {
                        z_jingkong: "z惊恐"
                    },
                    {
                        z_leipi: "z雷劈"
                    },
                    {
                        z_shihua: "z石化"
                    },
                    {
                        z_shuijiao: "z睡觉"
                    },
                    {
                        z_touxiao: "z偷笑"
                    },
                    {
                        z_woding: "z我顶"
                    },
                    {
                        nmw_aimu: "nmw爱慕"
                    },
                    {
                        nmw_baichi: "nmw白痴"
                    },
                    {
                        nmw_baodou: "nmw爆豆"
                    },
                    {
                        nmw_bixue: "nmw鼻血"
                    },
                    {
                        nmw_bishi: "nmw鄙视"
                    },
                    {
                        nmw_chenqie: "nmw臣妾"
                    },
                    {
                        nmw_daku: "nmw大哭"
                    },
                    {
                        nmw_dashen: "nmw大神"
                    },
                    {
                        nmw_dawu: "nmw大悟"
                    },
                    {
                        nmw_daxiao: "nmw大笑"
                    },
                    {
                        nmw_danteng: "nmw蛋疼"
                    },
                    {
                        nmw_dingni: "nmw顶你"
                    },
                    {
                        nmw_fennu: "nmw愤怒"
                    },
                    {
                        xyj_aini: "xyj爱你"
                    },
                    {
                        xyj_biaolei: "xyj飙泪"
                    },
                    {
                        xyj_deyi: "xyj得意"
                    },
                    {
                        xyj_fengle: "xyj疯了"
                    },
                    {
                        xyj_huaixiao: "xyj坏笑"
                    },
                    {
                        xyj_kelian: "xyj可怜"
                    },
                    {
                        xyj_jiuming: "xyj救命"
                    },
                    {
                        xyj_keshui: "xyj瞌睡"
                    },
                    {
                        xyj_liuhan: "xyj流汗"
                    },
                    {
                        xyj_niuyangge: "xyj扭秧歌"
                    },
                    {
                        xyj_sajiao: "xyj撒娇"
                    },
                    {
                        xyj_tuxue: "xyj吐血"
                    },
                    {
                        xyj_weinan: "xyj为难"
                    },
                    {
                        xyj_zoukai: "xyj走开"
                    },
                    {
                        Billd_bazhang: "billd巴掌"
                    },
                    {
                        Billd_benpao: "billd奔跑"
                    },
                    {
                        Billd_feiwen: "billd飞吻"
                    },
                    {
                        Billd_lalala: "billd啦啦啦"
                    },
                    {
                        Billd_lianyao: "billd练腰"
                    },
                    {
                        Billd_lingluan: "billd凌乱"
                    },
                    {
                        Billd_naoyang: "billd挠痒"
                    },
                    {
                        Billd_paidupi: "billd拍肚皮"
                    },
                    {
                        Billd_pailian: "billd拍脸"
                    },
                    {
                        Billd_paishou: "billd拍手"
                    },
                    {
                        Billd_rouyan: "billd揉眼"
                    },
                    {
                        Billd_sajiao: "billd撒娇"
                    },
                    {
                        Billd_xingfen: "billd兴奋"
                    },
                    {
                        Billd_zhuanquan: "billd转圈"
                    },
                    {
                        ali_88: "ali88"
                    },
                    {
                        ali_bainian: "ali拜年"
                    },
                    {
                        ali_baoyibao: "ali抱一抱"
                    },
                    {
                        ali_buma: "ali不是吧"
                    },
                    {
                        ali_liu: "ali溜"
                    },
                    {
                        ali_momotou: "ali摸摸头"
                    },
                    {
                        ali_paipaishou: "ali拍拍手"
                    },
                    {
                        ali_qianzou: "ali欠揍"
                    },
                    {
                        ali_qinyige: "ali亲一个"
                    },
                    {
                        ali_xianzhuozi: "ali掀桌子"
                    },
                    {
                        ali_xiaosile: "ali笑死了"
                    },
                    {
                        ali_xiu: "ali羞"
                    },
                    {
                        ali_xuxuxu: "ali嘘嘘嘘"
                    },
                    {
                        ali_yumen: "ali郁闷"
                    },
                    {
                        gst_aima: "gst挨骂"
                    },
                    {
                        gst_buhuole: "gst不活了"
                    },
                    {
                        gst_chaoxiaoni: "gst嘲笑你"
                    },
                    {
                        gst_chouniya: "gst抽你呀"
                    },
                    {
                        gst_fagongzila: "gst发工资啦"
                    },
                    {
                        gst_ganmalu: "gst认错"
                    },
                    {
                        gst_haonandong: "gst好难懂"
                    },
                    {
                        gst_rerere: "gst热热热"
                    },
                    {
                        gst_shuaibile: "gst帅毙了"
                    },
                    {
                        gst_touxiang: "gst投降"
                    },
                    {
                        gst_wabishi: "gst挖鼻屎"
                    },
                    {
                        gst_wanan: "gst晚安"
                    },
                    {
                        gst_xiabanla: "gst下班啦"
                    },
                    {
                        gst_zhuanzhuanzhuan: "gst转转转"
                    }],
                author: [{
                    url: "//zhyhappy321." + zRootDomain + "/",
                    name: "钱江盖饭"
                },
                    {
                        url: "//monki." + zRootDomain + "/",
                        name: "小猴耳朵"
                    },
                    {
                        url: "//jr1985." + zRootDomain + "/",
                        name: "牛MO王"
                    },
                    {
                        url: proMainZDomain + "/u/550599/",
                        name: "马里奥小黄"
                    },
                    {
                        url: proMainZDomain + "/u/61119/",
                        name: "comichunter"
                    },
                    {
                        url: proMainZDomain + "/u/913618/",
                        name: "Hans_阿狸"
                    },
                    {
                        url: proMainZDomain + "/u/286713/",
                        name: "viviling"
                    },
                    {
                        url: proMainZDomain + "/u/76367/",
                        name: "碳碳"
                    },
                    {
                        url: proMainZDomain + "/u/1167820/",
                        name: "郭国果过"
                    },
                    {
                        url: proMainZDomain + "/u/363414/",
                        name: "糕糕"
                    }]
            },
                {
                    title: "站酷小Z",
                    emoji: [{
                        z_daku: "z大哭"
                    },
                        {
                            z_daxiao: "z大笑"
                        },
                        {
                            z_dongxin: "z动心"
                        },
                        {
                            z_fennu: "z愤怒"
                        },
                        {
                            z_guzhang: "z鼓掌"
                        },
                        {
                            z_guancha: "z观察"
                        },
                        {
                            z_huanhu: "z欢呼"
                        },
                        {
                            z_jiayou: "z加油"
                        },
                        {
                            z_jingkong: "z惊恐"
                        },
                        {
                            z_leipi: "z雷劈"
                        },
                        {
                            z_shihua: "z石化"
                        },
                        {
                            z_shuijiao: "z睡觉"
                        },
                        {
                            z_touxiao: "z偷笑"
                        },
                        {
                            z_woding: "z我顶"
                        },
                        {
                            z_wohan: "z我汗"
                        },
                        {
                            z_wotu: "z我吐"
                        },
                        {
                            z_wozan: "z我赞"
                        },
                        {
                            z_zaijian: "z再见"
                        },
                        {
                            z_zhaoda: "z找打"
                        },
                        {
                            z_zhenniu: "z真牛"
                        },
                        {
                            z_regou: "z热狗"
                        },
                        {
                            z_xiaxue: "z下雪"
                        },
                        {
                            z_zhutou: "z猪头"
                        },
                        {
                            z_fuzi: "z福字"
                        },
                        {
                            z_sahua: "z撒花"
                        },
                        {
                            z_denglong: "z灯笼"
                        },
                        {
                            z_hongbao: "z红包"
                        },
                        {
                            z_bengdi: "z蹦迪"
                        },
                        {
                            z_hecha: "z喝茶"
                        },
                        {
                            z_zipai: "z自拍"
                        },
                        {
                            z_tongxiao: "z通宵"
                        },
                        {
                            z_xiaoku: "z笑哭"
                        },
                        {
                            z_jianshen: "z健身"
                        },
                        {
                            z_nvfen: "z女粉"
                        },
                        {
                            z_bishi: "z鄙视"
                        },
                        {
                            z_lvmao: "z绿帽"
                        },
                        {
                            z_liulei: "z流泪"
                        },
                        {
                            z_ok: "zOK"
                        },
                        {
                            z_666: "z666"
                        },
                        {
                            z_chigua: "z吃瓜"
                        },
                        {
                            z_huahua: "z画画"
                        },
                        {
                            z_mobai: "z膜拜"
                        },
                        {
                            z_zhidian: "z指点"
                        },
                        {
                            z_jinxing: "z金星"
                        },
                        {
                            z_lanping: "z蓝屏"
                        },
                        {
                            z_ps: "zPS"
                        }],
                    author: [{
                        url: "//zhyhappy321." + zRootDomain + "/",
                        name: "钱江盖饭"
                    },
                        {
                            url: "//monki." + zRootDomain + "/",
                            name: "小猴耳朵"
                        }]
                },
                {
                    title: "牛MO王",
                    emoji: [{
                        nmw_aimu: "nmw爱慕"
                    },
                        {
                            nmw_baichi: "nmw白痴"
                        },
                        {
                            nmw_baodou: "nmw爆豆"
                        },
                        {
                            nmw_bixue: "nmw鼻血"
                        },
                        {
                            nmw_bishi: "nmw鄙视"
                        },
                        {
                            nmw_chenqie: "nmw臣妾"
                        },
                        {
                            nmw_daku: "nmw大哭"
                        },
                        {
                            nmw_dashen: "nmw大神"
                        },
                        {
                            nmw_dawu: "nmw大悟"
                        },
                        {
                            nmw_daxiao: "nmw大笑"
                        },
                        {
                            nmw_danteng: "nmw蛋疼"
                        },
                        {
                            nmw_dingni: "nmw顶你"
                        },
                        {
                            nmw_fennu: "nmw愤怒"
                        },
                        {
                            nmw_fengnan: "nmw风男"
                        },
                        {
                            nmw_guihun: "nmw鬼魂"
                        },
                        {
                            nmw_guilian: "nmw鬼脸"
                        },
                        {
                            nmw_hanyan: "nmw汗颜"
                        },
                        {
                            nmw_jiyou: "nmw基友"
                        },
                        {
                            nmw_jianyi: "nmw坚毅"
                        },
                        {
                            nmw_jingdai: "nmw惊呆"
                        },
                        {
                            nmw_kuqi: "nmw哭泣"
                        },
                        {
                            nmw_lanke: "nmw揽客"
                        },
                        {
                            nmw_leidao: "nmw雷倒"
                        },
                        {
                            nmw_miantian: "nmw腼腆"
                        },
                        {
                            nmw_niubi: "nmw牛逼"
                        },
                        {
                            nmw_oke: "nmw哦可"
                        },
                        {
                            nmw_outu: "nmw呕吐"
                        },
                        {
                            nmw_pangzi: "nmw胖子"
                        },
                        {
                            nmw_rounai: "nmw揉奶"
                        },
                        {
                            nmw_sajiao: "nmw撒娇"
                        },
                        {
                            nmw_segui: "nmw色鬼"
                        },
                        {
                            nmw_shuashuai: "z真牛"
                        },
                        {
                            z_zhenniu: "nmw耍帅"
                        },
                        {
                            nmw_shuijiao: "nmw睡觉"
                        },
                        {
                            nmw_weixiao: "nmw微笑"
                        },
                        {
                            nmw_xiaban: "nmw下班"
                        },
                        {
                            nmw_yanjing: "nmw眼镜"
                        },
                        {
                            nmw_yiwen: "nmw疑问"
                        },
                        {
                            nmw_zana: "nmw赞啊"
                        },
                        {
                            nmw_zuichan: "nmw嘴馋"
                        }],
                    author: [{
                        url: "//jr1985." + zRootDomain + "/",
                        name: "牛MO王"
                    }]
                },
                {
                    title: "小幺鸡",
                    emoji: [{
                        xyj_aini: "xyj爱你"
                    },
                        {
                            xyj_aiya: "xyj哎呀"
                        },
                        {
                            xyj_biaolei: "xyj飙泪"
                        },
                        {
                            xyj_buyaoya: "xyj不要呀"
                        },
                        {
                            xyj_ceng: "xyj撑"
                        },
                        {
                            xyj_chixigua: "xyj吃西瓜"
                        },
                        {
                            xyj_chonga: "xyj冲啊"
                        },
                        {
                            xyj_deyi: "xyj得意"
                        },
                        {
                            xyj_ding: "xyj顶"
                        },
                        {
                            xyj_fengle: "xyj疯了"
                        },
                        {
                            xyj_guale: "xyj挂了"
                        },
                        {
                            xyj_huaixiao: "xyj坏笑"
                        },
                        {
                            xyj_jiuming: "xyj救命"
                        },
                        {
                            xyj_kafei: "xyj咖啡"
                        },
                        {
                            xyj_kelian: "xyj可怜"
                        },
                        {
                            xyj_keshui: "xyj瞌睡"
                        },
                        {
                            xyj_laima: "xyj来嘛"
                        },
                        {
                            xyj_langxiao: "xyj浪笑"
                        },
                        {
                            xyj_laoda: "xyj老大"
                        },
                        {
                            xyj_leng: "xyj冷"
                        },
                        {
                            xyj_liuhan: "xyj流汗"
                        },
                        {
                            xyj_longnianxyjbai: "xyj新年快乐"
                        },
                        {
                            xyj_longnianxyjhb: "xyj万事大吉"
                        },
                        {
                            xyj_longnianxyjyu: "xyj恭喜发财"
                        },
                        {
                            xyj_meishaonv: "xyj美少女"
                        },
                        {
                            xyj_meiyan: "xyj媚眼"
                        },
                        {
                            xyj_niuyangge: "xyj扭秧歌"
                        },
                        {
                            xyj_pinle: "xyj拼了"
                        },
                        {
                            xyj_sajiao: "xyj撒娇"
                        },
                        {
                            xyj_tian: "xyj舔"
                        },
                        {
                            xyj_tuxue: "xyj吐血"
                        },
                        {
                            xyj_weinan: "xyj为难"
                        },
                        {
                            xyj_xiayan: "xyj瞎眼"
                        },
                        {
                            xyj_yun: "xyj晕"
                        },
                        {
                            xyj_zhenhan: "xyj震撼"
                        },
                        {
                            xyj_zhuashafa: "xyj抓沙发"
                        },
                        {
                            xyj_zoukai: "xyj走开"
                        },
                        {
                            xyj_zuoyi: "xyj作揖"
                        }],
                    author: [{
                        url: proMainZDomain + "/u/550599/",
                        name: "马里奥小黄"
                    }]
                },
                {
                    title: "彼尔德",
                    emoji: [{
                        Billd_bazhang: "billd巴掌"
                    },
                        {
                            Billd_benpao: "billd奔跑"
                        },
                        {
                            Billd_che: "billd扯"
                        },
                        {
                            Billd_chuyu: "billd出浴"
                        },
                        {
                            Billd_dengtui: "billd蹬腿"
                        },
                        {
                            Billd_feiwen: "billd飞吻"
                        },
                        {
                            Billd_haobao: "billd好饱"
                        },
                        {
                            Billd_heiha: "billd嘿哈"
                        },
                        {
                            Billd_juyaling: "billd举哑铃"
                        },
                        {
                            Billd_lalala: "billd啦啦啦"
                        },
                        {
                            Billd_lianyao: "billd练腰"
                        },
                        {
                            Billd_lingluan: "billd凌乱"
                        },
                        {
                            Billd_naoyang: "billd挠痒"
                        },
                        {
                            Billd_paidupi: "billd拍肚皮"
                        },
                        {
                            Billd_pailian: "billd拍脸"
                        },
                        {
                            Billd_paishou: "billd拍手"
                        },
                        {
                            Billd_pao: "billd跑"
                        },
                        {
                            Billd_piaohu: "billd飘忽"
                        },
                        {
                            Billd_rouyan: "billd揉眼"
                        },
                        {
                            Billd_sajiao: "billd撒娇"
                        },
                        {
                            Billd_tabu: "billd踏步"
                        },
                        {
                            Billd_tantiao: "billd弹跳"
                        },
                        {
                            Billd_tiao: "billd跳"
                        },
                        {
                            Billd_xingfen: "billd兴奋"
                        },
                        {
                            Billd_yangwoqizuo: "billd仰卧起坐"
                        },
                        {
                            Billd_zhuanquan: "billd转圈"
                        }],
                    author: [{
                        url: proMainZDomain + "/u/61119/",
                        name: "comichunter（郑插插）"
                    }]
                },
                {
                    title: "阿狸",
                    emoji: [{
                        ali_88: "ali88"
                    },
                        {
                            ali_bainian: "ali拜年"
                        },
                        {
                            ali_baoyibao: "ali抱一抱"
                        },
                        {
                            ali_bianpao: "ali鞭炮"
                        },
                        {
                            ali_biechao: "ali别吵"
                        },
                        {
                            ali_buma: "ali不是吧"
                        },
                        {
                            ali_bunaifan: "ali不耐烦"
                        },
                        {
                            ali_cai: "ali踩"
                        },
                        {
                            ali_dachi: "ali大吃"
                        },
                        {
                            ali_dagun: "ali打滚"
                        },
                        {
                            ali_dalanqiu: "ali打篮球"
                        },
                        {
                            ali_dese: "ali得瑟"
                        },
                        {
                            ali_diantou: "ali点头"
                        },
                        {
                            ali_dingqi: "ali顶起"
                        },
                        {
                            ali_fanbaiyan: "ali翻白眼"
                        },
                        {
                            ali_fei: "ali飞"
                        },
                        {
                            ali_fengzheng: "ali风筝"
                        },
                        {
                            ali_guiqiu: "ali跪求"
                        },
                        {
                            ali_huaixiao: "ali坏笑"
                        },
                        {
                            ali_huanhu: "ali欢呼"
                        },
                        {
                            ali_hulaquan: "ali呼啦圈"
                        },
                        {
                            ali_jiangshitiao: "ali僵尸跳"
                        },
                        {
                            ali_jiayou: "ali加油"
                        },
                        {
                            ali_jing: "ali惊"
                        },
                        {
                            ali_jiu: "ali紧张"
                        },
                        {
                            ali_laiba: "ali来吧"
                        },
                        {
                            ali_laipi: "ali赖皮"
                        },
                        {
                            ali_liu: "ali溜"
                        },
                        {
                            ali_momotou: "ali摸摸头"
                        },
                        {
                            ali_naoqiang: "ali挠墙"
                        },
                        {
                            ali_ni: "ali你"
                        },
                        {
                            ali_paipaishou: "ali拍拍手"
                        },
                        {
                            ali_penti: "ali喷嚏"
                        },
                        {
                            ali_piao: "ali恳求"
                        },
                        {
                            ali_piaoguo: "ali飘过"
                        },
                        {
                            ali_pu: "ali心动"
                        },
                        {
                            ali_pudao: "ali扑倒"
                        },
                        {
                            ali_qianzou: "ali欠揍"
                        },
                        {
                            ali_qinyige: "ali亲一个"
                        },
                        {
                            ali_saqian: "ali撒钱"
                        },
                        {
                            ali_shuai: "ali帅"
                        },
                        {
                            ali_shuaishou: "ali甩手"
                        },
                        {
                            ali_shui: "ali睡"
                        },
                        {
                            ali_songliwu: "ali送礼物"
                        },
                        {
                            ali_toukan: "ali偷看"
                        },
                        {
                            ali_tuxue: "ali吐血"
                        },
                        {
                            ali_wa: "ali哇"
                        },
                        {
                            ali_xia: "ali吓"
                        },
                        {
                            ali_xiang: "ali想"
                        },
                        {
                            ali_xianhua: "ali鲜花"
                        },
                        {
                            ali_xianzhuozi: "ali掀桌子"
                        },
                        {
                            ali_xiao: "ali笑"
                        },
                        {
                            ali_xiaosile: "ali笑死了"
                        },
                        {
                            ali_xiu: "ali羞"
                        },
                        {
                            ali_xuxuxu: "ali嘘嘘嘘"
                        },
                        {
                            ali_yaohuang: "ali摇晃"
                        },
                        {
                            ali_yuanbao: "ali元宝"
                        },
                        {
                            ali_yumen: "ali郁闷"
                        },
                        {
                            ali_zhuan: "ali转"
                        },
                        {
                            ali_zhuanquanku: "ali转圈哭"
                        },
                        {
                            ali_zhui: "ali追"
                        },
                        {
                            ali_zuoguilian: "ali做鬼脸"
                        }],
                    author: [{
                        url: proMainZDomain + "/u/913618/",
                        name: "Hans_阿狸（Hans）"
                    }]
                },
                {
                    title: "郭斯特",
                    emoji: [{
                        gst_aima: "gst挨骂"
                    },
                        {
                            gst_buhuole: "gst不活了"
                        },
                        {
                            gst_chaoxiaoni: "gst嘲笑你"
                        },
                        {
                            gst_chouniya: "gst抽你呀"
                        },
                        {
                            gst_dese: "gst得瑟"
                        },
                        {
                            gst_fagongzila: "gst发工资啦"
                        },
                        {
                            gst_ganmalu: "gst认错"
                        },
                        {
                            gst_han: "gst汗"
                        },
                        {
                            gst_haonandong: "gst好难懂"
                        },
                        {
                            gst_haopaya: "gst好怕呀"
                        },
                        {
                            gst_haoxiuse: "gst好羞涩"
                        },
                        {
                            gst_heiheihei: "gst嘿嘿嘿"
                        },
                        {
                            gst_kun: "gst困"
                        },
                        {
                            gst_naini: "gst耐你"
                        },
                        {
                            gst_renjiabuyi: "gst人家不依"
                        },
                        {
                            gst_rerere: "gst热热热"
                        },
                        {
                            gst_rouroulian: "gst揉揉脸"
                        },
                        {
                            gst_shuaibile: "gst帅毙了"
                        },
                        {
                            gst_tiantian: "gst舔舔"
                        },
                        {
                            gst_touxiang: "gst投降"
                        },
                        {
                            gst_wabishi: "gst挖鼻屎"
                        },
                        {
                            gst_wanan: "gst晚安"
                        },
                        {
                            gst_xiabanla: "gst下班啦"
                        },
                        {
                            gst_yameidie: "gst雅蠛蝶"
                        },
                        {
                            gst_youwenzi: "gst有蚊子"
                        },
                        {
                            gst_zhuanzhuanzhuan: "gst转转转"
                        }],
                    author: [{
                        url: proMainZDomain + "/u/286713/",
                        name: "viviling（郭斯特）"
                    }]
                },
                {
                    title: "摩丝摩丝",
                    emoji: [{
                        moc_chelian: "moc扯脸"
                    },
                        {
                            moc_ciyaxiao: "moc呲牙笑"
                        },
                        {
                            moc_daji: "moc打击"
                        },
                        {
                            moc_dakouchi: "moc大口吃"
                        },
                        {
                            moc_daku: "moc大哭"
                        },
                        {
                            moc_ding: "moc顶"
                        },
                        {
                            moc_fuyun: "moc浮云"
                        },
                        {
                            moc_ganga: "moc尴尬"
                        },
                        {
                            moc_guilian: "moc鬼脸"
                        },
                        {
                            moc_ji: "moc挤"
                        },
                        {
                            moc_jiebing: "moc结冰"
                        },
                        {
                            moc_kanqingchu: "moc看清楚"
                        },
                        {
                            moc_luguo: "moc路过"
                        },
                        {
                            moc_maochu: "moc冒出"
                        },
                        {
                            moc_outu: "moc呕吐"
                        },
                        {
                            moc_paizhao: "moc拍照"
                        },
                        {
                            moc_qiangwen: "moc强吻"
                        },
                        {
                            moc_qinqinwen: "moc亲亲吻"
                        },
                        {
                            moc_qinqinyou: "moc亲亲右"
                        },
                        {
                            moc_qinqinzuo: "moc亲亲左"
                        },
                        {
                            moc_shengqi: "moc生气"
                        },
                        {
                            moc_shihua: "moc石化"
                        },
                        {
                            moc_tantiao: "moc弹跳"
                        },
                        {
                            moc_wanan: "moc晚安"
                        },
                        {
                            moc_weiguan: "moc围观"
                        },
                        {
                            moc_xiu: "moc羞"
                        },
                        {
                            moc_yun: "moc晕"
                        },
                        {
                            moc_zhongjian: "moc中箭"
                        },
                        {
                            moc_zhuanfa: "moc转发"
                        },
                        {
                            moc_zhuangku: "moc装酷"
                        },
                        {
                            moc_zhuangtou: "moc撞头"
                        },
                        {
                            moc_zizhong: "moc自重"
                        }],
                    author: [{
                        url: proMainZDomain + "/u/76367/",
                        name: "碳碳"
                    }]
                },
                {
                    title: "炮炮兵",
                    emoji: [{
                        ppb_bibi: "ppb哔哔"
                    },
                        {
                            ppb_buyao: "ppb不要"
                        },
                        {
                            ppb_chouyan: "ppb抽烟"
                        },
                        {
                            ppb_chuidi: "ppb捶地"
                        },
                        {
                            ppb_ele: "ppb饿了"
                        },
                        {
                            ppb_haixiu: "ppb害羞"
                        },
                        {
                            ppb_jiangshi: "ppb僵尸"
                        },
                        {
                            ppb_jianshen: "ppb健身"
                        },
                        {
                            ppb_jianxiao: "ppb贱笑"
                        },
                        {
                            ppb_kaihuo: "ppb开火"
                        },
                        {
                            ppb_kaiqiang: "ppb开枪"
                        },
                        {
                            ppb_liuzou: "ppb溜走"
                        },
                        {
                            ppb_nielian: "ppb捏脸"
                        },
                        {
                            ppb_niudong: "ppb扭动"
                        },
                        {
                            ppb_paishou: "ppb拍手"
                        },
                        {
                            ppb_paopao: "ppb泡泡"
                        },
                        {
                            ppb_piaoguo: "ppb飘过"
                        },
                        {
                            ppb_ppb_tushui: "ppb吐口水"
                        },
                        {
                            ppb_qianshui: "ppb潜水"
                        },
                        {
                            ppb_quantou: "ppb拳头"
                        },
                        {
                            ppb_re: "ppb热"
                        },
                        {
                            ppb_sahua: "ppb撒花"
                        },
                        {
                            ppb_shuaya: "ppb刷牙"
                        },
                        {
                            ppb_tuxue: "ppb吐血"
                        },
                        {
                            ppb_xiayu: "ppb下雨"
                        },
                        {
                            ppb_xuanzhuan: "ppb旋转"
                        },
                        {
                            ppb_zhuangqiang: "ppb撞墙"
                        },
                        {
                            ppb_zhuanlian: "ppb转脸"
                        },
                        {
                            ppb_zhuanquan: "ppb转圈"
                        },
                        {
                            ppb_zoulu: "ppb走路"
                        }],
                    author: [{
                        url: proMainZDomain + "/u/1167820/",
                        name: "郭国果过（郭征）"
                    }]
                },
                {
                    title: "麦拉风",
                    emoji: [{
                        mlf_chifan: "mlf吃饭"
                    },
                        {
                            mlf_dahaqian: "mlf打哈欠"
                        },
                        {
                            mlf_daku: "mlf大哭"
                        },
                        {
                            mlf_diantou: "mlf点头"
                        },
                        {
                            mlf_dun: "mlf遁"
                        },
                        {
                            mlf_fulaile: "mlf福来了"
                        },
                        {
                            mlf_gongxi: "mlf恭喜"
                        },
                        {
                            mlf_haixiu: "mlf害羞"
                        },
                        {
                            mlf_han: "mlf汗"
                        },
                        {
                            mlf_haorenka: "mlf好人卡"
                        },
                        {
                            mlf_hecha: "mlf喝茶"
                        },
                        {
                            mlf_huachi: "mlf花痴"
                        },
                        {
                            mlf_huaixiao: "mlf坏笑"
                        },
                        {
                            mlf_keshui: "mlf瞌睡"
                        },
                        {
                            mlf_nielian: "mlf捏脸"
                        },
                        {
                            mlf_nuhuo: "mlf怒火"
                        },
                        {
                            mlf_qiaoqiao: "mlf瞧瞧"
                        },
                        {
                            mlf_qinqin: "mlf亲亲"
                        },
                        {
                            mlf_tao: "mlf逃"
                        },
                        {
                            mlf_wanan: "mlf晚安"
                        },
                        {
                            mlf_weixiao: "mlf微笑"
                        },
                        {
                            mlf_wuliao: "mlf无聊"
                        },
                        {
                            mlf_yangtiandaxiao: "mlf仰天大笑"
                        },
                        {
                            mlf_zhenjing: "mlf震惊"
                        },
                        {
                            mlf_zhuakuang: "mlf抓狂"
                        },
                        {
                            mlf_zhuangsha: "mlf装傻"
                        }],
                    author: [{
                        url: proMainZDomain + "/u/363414/",
                        name: "糕糕"
                    }]
                }],
            i = {
                html: function() {
                    var e = "";
                    return e += ' <div class="popFace" id="popFace" style="outline: none;" tabindex="-1" onblur="$(this).hide();">',
                        e += '\t<div class="popFaceFlag"></div>',
                        e += '\t<div class="popFaceBox" tab="a">',
                        e += '\t<div class="popFaceTitle">',
                        e += '\t<a href="javascript:void(0)" class="right popFaceClose" onclick="$(this).parents(\'.popFace\').hide();">',
                        e += '\t<img src="' + proStaticZDomain + '/z/images/popclose.png" />',
                        e += "\t</a>",
                        e += '\t<p tabtitle="true">',
                        r.forEach(function(t, n) {
                            e += ' <a style="cursor:pointer" class="' + (0 === n ? "selected": "") + '">' + t.title + "</a>"
                        }),
                        e += "\t</p>",
                        e += "\t</div>",
                        e += '\t<div class="popFaceCon" tabcon="true">',
                        r.forEach(function(t, n) {
                            e += '\t<div class="face  ' + (0 === n ? "selected": "") + '">',
                                e += '\t<div class="faceList">',
                                t.emoji.forEach(function(t) {
                                    var n = Object.keys(t)[0];
                                    e += '  <img src="' + proStaticZDomain + "/images/face/" + n + ".gif?v=" + o + '" style="cursor:pointer" onclick="insertSmile(\'' + t[n] + '\');" title="' + t[n] + '">'
                                }),
                                e += "  </div>",
                                e += '\t<p class="c999 pt5">',
                                e += 0 === n ? "感谢酷友": "表情作者：",
                                t.author.forEach(function(n, o) {
                                    e += ' <a href="' + n.url + '" class="c009cff" target="_blank">' + n.name + "</a>" + (o !== t.author.length - 1 ? "、": "")
                                }),
                                e += 0 === n ? " 提供表情支持。": "",
                                e += "\t</p></div>"
                        }),
                        e += "\t</div>",
                        e += "\t</div>",
                        e += "\t</div>"
                }
            },
            a = function(e) {
                e.hasfocus || e.focus()
            },
            s = function() {
                $("[tab]").each(function() {
                    var e = $(this).attr("tab");
                    $(this).find("[tabtitle='true'] " + e).each(function(t) {
                        $(this).click(function() {
                            $(this).parents("[tabtitle]").find(e).removeClass("selected"),
                                $(this).addClass("selected"),
                                $(this).parents("[tab]").find("[tabcon='true']").children().removeClass("selected"),
                                $(this).parents("[tab]").find("[tabcon='true']").children().eq(t).addClass("selected")
                        })
                    })
                })
            };
        window.insertSmile = function(e) {
            var t = document.getElementById($("#popFace").attr("textareaID"));
            a(t),
                e = "[" + e + "]",
                t.value += e,
                $(t).change(),
                $("#popFace").hide()
        },
            t.popFace = function(e, t) {
                var n = $(e).offset().left - $(e).width() / 2 + 24,
                    o = $(e).offset().top + $(e).height() + 7,
                    r = $(e).offset().top - 328 - 7;
                $(e).find("span");
                $("#popFace").size() > 0 || $("body").append(i.html),
                    $(document).height() - $(e).offset().top < 350 ? $("#popFace").css({
                        left: n,
                        top: r
                    }) : $("#popFace").css({
                        left: n,
                        top: o
                    }),
                $(".shade").is(":visible") && ($(document).height() - $(e).offset().top - ($(document).height() - $(".shade").offset().top - $(window).height()) < 350 ? $("#popFace").css({
                    left: n,
                    top: r
                }) : $("#popFace").css({
                    left: n,
                    top: o
                }));
                var a = $(e).parent().parent().find('textarea,input[type="text"]').attr("id");
                t && (a = t),
                    $("#popFace").attr("textareaID", a),
                    $("#popFace").focus(),
                    $("#popFace").show(),
                    s()
            }
    },
        , ,
        function(e, t, n) {
            "use strict";
            function o(e) {
                if (e && e.__esModule) return e;
                var t = {};
                if (null != e) for (var n in e) Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n]);
                return t["default"] = e,
                    t
            }
            var r = n(0),
                i = o(r);
            window.popFace = i.popFace
        }]),
    createFollow.prototype.doOrCancelFollow = function() {
        this.isUnFollowStatus ? this.doFollow() : this.cancelFollow()
    },
    createFollow.prototype.doFollow = function() {
        var e = this;
        $.ajax({
            type: "POST",
            url: proMyZDomain + "/follow/addFollow.json",
            data: {
                objId: e.objectId,
                objType: e.objectType
            },
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(t) {
                0 == t.code ? e.succesCb(t.data) : e.errorCb(t.msg)
            }
        })
    },
    createFollow.prototype.cancelFollow = function() {
        var e = this;
        $.ajax({
            type: "POST",
            url: proMyZDomain + "/follow/cancelFollow.json",
            data: {
                objId: this.objectId,
                objType: this.objectType
            },
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(t) {
                0 == t.code ? e.succesCb(t.data) : e.errorCb("dc=" + t.code)
            }
        })
    },
    createFollow.followstatus = function(e) {
        return !! e.hasClass("notfollow") || (e.hasClass("following"), !1)
    },
    PrivatePOP.prototype.openPrivateWindowListening = function() {
        1 == PrivatePOP.islogin() ? this.openPrivateWindow() : openLoginWindow(this.openPrivateWindow)
    },
    PrivatePOP.prototype.openPrivateWindow = function() {
        function e() {
            function e(e) {
                var t = "";
                t += '<div class="contacts-list hide" style="height:400px;overflow-x:hidden;">',
                    t += "<ul>",
                    t += '<li class="contacts-title">最近联系人</li>',
                    $.each(e,
                        function(e, n) {
                            t += '<li data-name="' + n.name + '"><a href="javascript:;"><img src="' + n.img + '" width="30" height="30" alt=""></a>' + n.name + "</li>"
                        }),
                    t += "</ul>",
                    t += "</div>",
                    $("#del_message").parent().append(t),
                    $(".contacts-list").mCustomScrollbar({
                        theme: "dark"
                    }),
                    $(".contacts-list li").on("click mousedown",
                        function() {
                            var e = $(this).attr("data-name");
                            $("#del_message").val(e),
                                $(".contacts-list").addClass("hide")
                        })
            }
            $("#del_message").focus(function() {
                $(this).siblings(".contacts-list").removeClass("hide")
            }),
                $("#del_message").blur(function() {
                    $(this).siblings(".contacts-list").addClass("hide")
                });
            var n = getUid(),
                o = $("#del_message");
            return n === t.memberId ? void pageToastFail(messagesWeb.privateLetter.toast_private_error) : (!t.getUserListCallback || t.memberId || t.memberName ? (o.prop("value", t.memberName), o.prop("disabled", "disabled")) : t.getUserListCallback(e), $(".private-pop").show(), showGlobalMaskLayer(), t.doSMSListening(t), t.cancelSMSListening(t), void popFirstInputFocus($("#private-textarea")))
        }
        var t = this;
        showRemindBindLayer(e)
    },
    PrivatePOP.prototype.doSMSListening = function(e) {
        $(".private-pop .pop-confirm").on("click",
            function() {
                if (e.smsContent = $("#private-textarea").val(), e.memberName = $("#del_message").val(), "" != e.memberName && "undefined" != typeof e.smsContent && "" != e.smsContent) {
                    e.disableStyle();
                    var t = $(this);
                    e.SmsToMember(e, t)
                } else e.ableStyle(),
                    pageToastFail("" == e.memberName ? "你还没有选择发送的对象哦": messagesWeb.privateLetter.toast_private_empty)
            })
    },
    
    //发送私信
    PrivatePOP.prototype.SmsToMember = function(e, t) {
        $.ajax({
            type: "POST",
            url: proMyZDomain + "/messages/sendMessagePrivate.do",
            data: {
                content: e.smsContent,
                memberTo: e.memberName
            },
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            },
            dataType: "json",
            success: function(n) {
                e.privateCallback(n, t, e)
            }
        })
    },
    PrivatePOP.prototype.privateCallback = function(e, t, n) {
        200 == e.code ? (pageToastSuccess(messagesWeb.privateLetter.toast_private_success), n.confirmClose(t), $("#private-textarea").val(""), t.parents(".pop-up").find(".count").removeClass("exceeded").html("600"), $("#del_message").parent().find("div").remove(), this.sendSuccessCallback && setTimeout(this.sendSuccessCallback, 1700)) : pageToastFail(401 == e.code ? e.msg: 102001 == e.code ? messagesWeb.privateLetter.private_error1: 11e3 == e.code ? messagesWeb.privateLetter.private_user_nofound: e.msg),
            n.ableStyle()
    },
    PrivatePOP.prototype.disableStyle = function() {
        $(".pop-confirm").prop("value", messagesWeb.common_btn_sending),
            $(".pop-confirm").prop("disabled", !0)
    },
    PrivatePOP.prototype.ableStyle = function() {
        $(".pop-confirm").prop("value", messagesWeb.common_btn_confirm),
            $(".pop-confirm").prop("disabled", !1)
    },
    PrivatePOP.prototype.cancelSMSListening = function(e) {
        $(".private-pop .pop-cancel, .private-pop .pop-close").on("click",
            function() {
                e.confirmClose($(this))
            })
    },
    PrivatePOP.prototype.confirmClose = function(e) {
        $("#del_message").parent().find("div").remove(),
            e.parents(".pop-up").hide(),
            hideGlobalMaskLayer(),
            $(".private-pop .pop-confirm").unbind("click")
    },
$("#private-textarea").length > 0 && zCharCount($("#private-textarea"), {
    allowed: 600
});