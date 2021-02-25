package info.xiaomo.core.untils

import java.util.*

/**
 * @author : xiaomo (https://xiaomo.info) (https://github.com/xiaomoinfo)
 * @version : 2017/1/14 9:59
 */

object LunarCalendarUtil {
    /**
     * 支持转换的最小农历年份
     */
    val MIN_YEAR = 1900
    /**
     * 支持转换的最大农历年份
     */
    val MAX_YEAR = 2099

    /**
     * 公历每月前的天数
     */
    private val DAYS_BEFORE_MONTH = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365)

    /**
     * 用来表示1900年到2099年间农历年份的相关信息，共24位bit的16进制表示，其中：
     * 1. 前4位表示该年闰哪个月；
     * 2. 5-17位表示农历年份13个月的大小月分布，0表示小，1表示大；
     * 3. 最后7位表示农历年首（正月初一）对应的公历日期。
     *
     *
     * 以2014年的数据0x955ABF为例说明：
     * 1001 0101 0101 1010 1011 1111
     * 闰九月                                  农历正月初一对应公历1月31号
     */
    private val LUNAR_INFO = intArrayOf(0x84B6BF,
            /*1901-1910*/
            0x04AE53, 0x0A5748, 0x5526BD, 0x0D2650, 0x0D9544, 0x46AAB9, 0x056A4D, 0x09AD42, 0x24AEB6, 0x04AE4A,
            /*1911-1920*/
            0x6A4DBE, 0x0A4D52, 0x0D2546, 0x5D52BA, 0x0B544E, 0x0D6A43, 0x296D37, 0x095B4B, 0x749BC1, 0x049754,
            /*1921-1930*/
            0x0A4B48, 0x5B25BC, 0x06A550, 0x06D445, 0x4ADAB8, 0x02B64D, 0x095742, 0x2497B7, 0x04974A, 0x664B3E,
            /*1931-1940*/
            0x0D4A51, 0x0EA546, 0x56D4BA, 0x05AD4E, 0x02B644, 0x393738, 0x092E4B, 0x7C96BF, 0x0C9553, 0x0D4A48,
            /*1941-1950*/
            0x6DA53B, 0x0B554F, 0x056A45, 0x4AADB9, 0x025D4D, 0x092D42, 0x2C95B6, 0x0A954A, 0x7B4ABD, 0x06CA51,
            /*1951-1960*/
            0x0B5546, 0x555ABB, 0x04DA4E, 0x0A5B43, 0x352BB8, 0x052B4C, 0x8A953F, 0x0E9552, 0x06AA48, 0x6AD53C,
            /*1961-1970*/
            0x0AB54F, 0x04B645, 0x4A5739, 0x0A574D, 0x052642, 0x3E9335, 0x0D9549, 0x75AABE, 0x056A51, 0x096D46,
            /*1971-1980*/
            0x54AEBB, 0x04AD4F, 0x0A4D43, 0x4D26B7, 0x0D254B, 0x8D52BF, 0x0B5452, 0x0B6A47, 0x696D3C, 0x095B50,
            /*1981-1990*/
            0x049B45, 0x4A4BB9, 0x0A4B4D, 0xAB25C2, 0x06A554, 0x06D449, 0x6ADA3D, 0x0AB651, 0x095746, 0x5497BB,
            /*1991-2000*/
            0x04974F, 0x064B44, 0x36A537, 0x0EA54A, 0x86B2BF, 0x05AC53, 0x0AB647, 0x5936BC, 0x092E50, 0x0C9645,
            /*2001-2010*/
            0x4D4AB8, 0x0D4A4C, 0x0DA541, 0x25AAB6, 0x056A49, 0x7AADBD, 0x025D52, 0x092D47, 0x5C95BA, 0x0A954E,
            /*2011-2020*/
            0x0B4A43, 0x4B5537, 0x0AD54A, 0x955ABF, 0x04BA53, 0x0A5B48, 0x652BBC, 0x052B50, 0x0A9345, 0x474AB9,
            /*2021-2030*/
            0x06AA4C, 0x0AD541, 0x24DAB6, 0x04B64A, 0x6a573D, 0x0A4E51, 0x0D2646, 0x5E933A, 0x0D534D, 0x05AA43,
            /*2031-2040*/
            0x36B537, 0x096D4B, 0xB4AEBF, 0x04AD53, 0x0A4D48, 0x6D25BC, 0x0D254F, 0x0D5244, 0x5DAA38, 0x0B5A4C,
            /*2041-2050*/
            0x056D41, 0x24ADB6, 0x049B4A, 0x7A4BBE, 0x0A4B51, 0x0AA546, 0x5B52BA, 0x06D24E, 0x0ADA42, 0x355B37,
            /*2051-2060*/
            0x09374B, 0x8497C1, 0x049753, 0x064B48, 0x66A53C, 0x0EA54F, 0x06AA44, 0x4AB638, 0x0AAE4C, 0x092E42,
            /*2061-2070*/
            0x3C9735, 0x0C9649, 0x7D4ABD, 0x0D4A51, 0x0DA545, 0x55AABA, 0x056A4E, 0x0A6D43, 0x452EB7, 0x052D4B,
            /*2071-2080*/
            0x8A95BF, 0x0A9553, 0x0B4A47, 0x6B553B, 0x0AD54F, 0x055A45, 0x4A5D38, 0x0A5B4C, 0x052B42, 0x3A93B6,
            /*2081-2090*/
            0x069349, 0x7729BD, 0x06AA51, 0x0AD546, 0x54DABA, 0x04B64E, 0x0A5743, 0x452738, 0x0D264A, 0x8E933E,
            /*2091-2099*/
            0x0D5252, 0x0DAA47, 0x66B53B, 0x056D4F, 0x04AE45, 0x4A4EB9, 0x0A4D4C, 0x0D1541, 0x2D92B5)
    private val monthString = arrayOf("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月")
    private val dayString = arrayOf("一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七", "二十八", "二十九", "三十", "三十一")
    private val weekString = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")

    /**
     * 将农历日期转换为公历日期
     *
     * @param year        农历年份
     * @param month       农历月
     * @param monthDay    农历日
     * @param isLeapMonth 该月是否是闰月
     * [url=home.php?mod=space&uid=7300]@return[/url] 返回农历日期对应的公历日期，year0, month1, day2.
     */
    fun lunarToSolar(year: Int, month: Int, monthDay: Int,
                     isLeapMonth: Boolean): IntArray {
        var inputYear = year
        var dayOffset: Int
        val leapMonth: Int
        var i = 1

        val maxMonth = 12
        if (inputYear < MIN_YEAR || inputYear > MAX_YEAR || month < 1 || month > maxMonth
                || monthDay < 1 || monthDay > 30) {
            throw IllegalArgumentException(
                    "Illegal lunar date, must be like that:\n\t" +
                            "inputYear : 1900~2099\n\t" +
                            "month : 1~12\n\t" +
                            "day : 1~30")
        }

        dayOffset = (LUNAR_INFO[inputYear - MIN_YEAR] and 0x001F) - 1

        val five = 5
        val two = 2
        if (LUNAR_INFO[inputYear - MIN_YEAR] and 0x0060 shr five == two) {
            dayOffset += 31
        }

        while (i < month) {
            dayOffset += if (LUNAR_INFO[inputYear - MIN_YEAR] and (0x80000 shr i - 1) == 0) {
                29
            } else {
                30
            }
            i++
        }

        dayOffset += monthDay
        leapMonth = LUNAR_INFO[inputYear - MIN_YEAR] and 0xf00000 shr 20

        // 这一年有闰月
        if (leapMonth != 0) {
            val res = month > leapMonth || month == leapMonth && isLeapMonth
            if (res) {
                dayOffset += if (LUNAR_INFO[inputYear - MIN_YEAR] and (0x80000 shr month - 1) == 0) {
                    29
                } else {
                    30
                }
            }
        }

        val four = 4
        val res = dayOffset > 366 || inputYear % four != 0 && dayOffset > 365
        if (res) {
            inputYear += 1
            dayOffset -= if (inputYear % four == 1) {
                366
            } else {
                365
            }
        }

        val solarInfo = IntArray(3)
        val oneThree = 13
        i = 1
        while (i < oneThree) {
            var iPos = DAYS_BEFORE_MONTH[i]
            if (inputYear % four == 0 && i > two) {
                iPos += 1
            }

            if (inputYear % four == 0 && i == two && iPos + 1 == dayOffset) {
                solarInfo[1] = i
                solarInfo[two] = dayOffset - 31
                break
            }

            if (iPos >= dayOffset) {
                solarInfo[1] = i
                iPos = DAYS_BEFORE_MONTH[i - 1]
                if (inputYear % four == 0 && i > two) {
                    iPos += 1
                }
                if (dayOffset > iPos) {
                    solarInfo[two] = dayOffset - iPos
                } else if (dayOffset == iPos) {
                    if (inputYear % four == 0 && i == two) {
                        solarInfo[two] = DAYS_BEFORE_MONTH[i] - DAYS_BEFORE_MONTH[i - 1] + 1
                    } else {
                        solarInfo[two] = DAYS_BEFORE_MONTH[i] - DAYS_BEFORE_MONTH[i - 1]
                    }

                } else {
                    solarInfo[two] = dayOffset
                }
                break
            }
            i++
        }
        solarInfo[0] = inputYear

        return solarInfo
    }


    private fun solarToLunar(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val week = calendar.get(Calendar.DAY_OF_WEEK)
        val ints = solarToLunar(year, month, day)
        val monthStr = monthString[ints[1]]
        val dayStr = dayString[ints[2]]
        val weekStr = weekString[week - 1]
        return monthStr + dayStr + "  " + weekStr
    }

    /**
     * 将公历日期转换为农历日期，且标识是否是闰月
     *
     * @param year
     * @param month
     * @param monthDay
     * @return 返回公历日期对应的农历日期，year0，month1，day2，leap3
     */
    fun solarToLunar(year: Int, month: Int, monthDay: Int): IntArray {
        val lunarDate = IntArray(4)
        val baseDate = GregorianCalendar(1900, 0, 31).time
        val objDate = GregorianCalendar(year, month - 1, monthDay).time
        var offset = ((objDate.time - baseDate.time) / 86400000L).toInt()

        // 用offset减去每农历年的天数计算当天是农历第几天
        // iYear最终结果是农历的年份, offset是当年的第几天
        var iYear: Int = MIN_YEAR
        var daysOfYear = 0
        while (iYear <= MAX_YEAR && offset > 0) {
            daysOfYear = daysInLunarYear(iYear)
            offset -= daysOfYear
            iYear++
        }
        if (offset < 0) {
            offset += daysOfYear
            iYear--
        }

        // 农历年份
        lunarDate[0] = iYear
        // 闰哪个月,1-12
        val leapMonth = leapMonth(iYear)
        var isLeap = false
        // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
        var iMonth = 1
        var daysOfMonth = 0
        val i = 13
        while (iMonth <= i && offset > 0) {
            daysOfMonth = daysInLunarMonth(iYear, iMonth)
            offset -= daysOfMonth
            iMonth++
        }
        // 当前月超过闰月，要校正
        if (leapMonth != 0 && iMonth > leapMonth) {
            --iMonth

            if (iMonth == leapMonth) {
                isLeap = true
            }
        }
        // offset小于0时，也要校正
        if (offset < 0) {
            offset += daysOfMonth
            --iMonth
        }

        lunarDate[1] = iMonth
        lunarDate[2] = offset + 1
        lunarDate[3] = if (isLeap) 1 else 0

        return lunarDate
    }

    /**
     * 传回农历year年month月的总天数
     *
     * @param year  要计算的年份
     * @param month 要计算的月
     * @param leap  当月是否是闰月
     * @return 传回天数，如果闰月是错误的，返回0.
     */
    @JvmOverloads
    fun daysInMonth(year: Int, month: Int, leap: Boolean = false): Int {
        val leapMonth = leapMonth(year)
        var offset = 0

        // 如果本年有闰月且month大于闰月时，需要校正
        if (leapMonth != 0 && month > leapMonth) {
            offset = 1
        }

        // 不考虑闰月
        if (!leap) {
            return daysInLunarMonth(year, month + offset)
        } else {
            // 传入的闰月是正确的月份
            if (leapMonth != 0 && leapMonth == month) {
                return daysInLunarMonth(year, month + 1)
            }
        }

        return 0
    }

    /**
     * 传回农历 year年的总天数
     *
     * @param year 将要计算的年份
     * @return 返回传入年份的总天数
     */
    private fun daysInLunarYear(year: Int): Int {
        var i: Int
        var sum = 348
        if (leapMonth(year) != 0) {
            sum = 377
        }
        val monthInfo = LUNAR_INFO[year - MIN_YEAR] and 0x0FFF80
        i = 0x80000
        while (i > 0x7) {
            if (monthInfo and i != 0) {
                sum += 1
            }
            i = i shr 1
        }
        return sum
    }

    /**
     * 传回农历 year年month月的总天数，总共有13个月包括闰月
     *
     * @param year  将要计算的年份
     * @param month 将要计算的月份
     * @return 传回农历 year年month月的总天数
     */
    private fun daysInLunarMonth(year: Int, month: Int): Int {
        return if (LUNAR_INFO[year - MIN_YEAR] and (0x100000 shr month) == 0) {
            29
        } else {
            30
        }
    }

    /**
     * 传回农历 year年闰哪个月 1-12 , 没闰传回 0
     *
     * @param year 将要计算的年份
     * @return 传回农历 year年闰哪个月1-12, 没闰传回 0
     */
    private fun leapMonth(year: Int): Int {
        return LUNAR_INFO[year - MIN_YEAR] and 0xF00000 shr 20
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val s = solarToLunar()
        println(s)
    }

}
/**
 * 传回农历year年month月的总天数
 *
 * @param year  要计算的年份
 * @param month 要计算的月
 * @return 传回天数
 */
