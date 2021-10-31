
import java.util.Arrays

class Jalali {
    companion object {
        const val name = "jalali"
        const val desc = "Jalali"
        const val epoch = 1948321
        const val minMonthLen = 29
        const val maxMonthLen = 31
        const val avgYearLen = 365.2425 // FIXME

        val monthNames = arrayOf(
            "Farvardin", "Ordibehesht", "Khordad", "Teer", "Mordad", "Shahrivar",
            "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand"
        )
        val monthNamesAb = arrayOf(
            "Far", "Ord", "Khr", "Tir", "Mor", "Shr",
            "Meh", "Abn", "Azr", "Dey", "Bah", "Esf"
        )
        val monthLen = arrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30)
        val monthLenSum = arrayOf(0, 31, 62, 93, 124, 155, 186, 216, 246, 276, 306, 336, 366)

        fun isLeap(year: Int): Boolean {
            // return true if year is leap, false otherwise
            // using 2820-years algorithm
            var y: Int = year
            if (y > 0) {
                y -= 1
            }
            return (((y - 473) % 2820) * 682) % 2816 < 682
        }

        fun toJd(d: Date): Int {
            // calculate Julian day from Jalali date
            // using 2820-years algorithm
            var epbase: Int
            if (d.year >= 0) {
                epbase = d.year - 474
            } else {
                epbase = 473
            }
            val epyear = 474 + epbase % 2820
            var tmpMonth = d.month - 1
            if (tmpMonth > 6) {
                tmpMonth = 6
            }
            return d.day +
                (d.month - 1) * 30 + tmpMonth +
                (epyear * 682 - 110) / 2816 +
                (epyear - 1) * 365 +
                epbase / 2820 * 1029983 +
                epoch - 1
        }
        fun jdTo(jd: Int): Date {
            // calculate Jalali date from Julian day
            // using 2820-years algorithm
            var deltaDays = jd - toJd(Date(475, 1, 1))
            var cycle = deltaDays / 1029983
            var cyear = deltaDays % 1029983
            var ycycle: Int
            if (cyear == 1029982) {
                ycycle = 2820
            } else {
                ycycle = (2134 * (cyear / 366) + 2816 * (cyear % 366) + 2815) / 1028522 + cyear / 366 + 1
            }
            var year = 2820 * cycle + ycycle + 474
            if (year <= 0) {
                year--
            }
            var yday = jd - toJd(Date(year, 1, 1)) + 1
            var month = Arrays.binarySearch(monthLenSum, yday) // FIXME: correct?
            if (month < 0) {
                // month == -insertion_point - 1
                // insertion_point == -month - 1
                month = -month - 1
            }
            // println(kotlin.String.format("month = %d", month))
            var day = yday - monthLenSum[month - 1]
            return Date(year, month, day)
        }
    }
}

fun main(args: Array<String>) {
    val d0 = Date(1397, 1, 1)
    val jd0 = Jalali.toJd(d0)
    for (jd in jd0..jd0 + 1000) {
        val d = Jalali.jdTo(jd)
        println(formatDate(d))
        var jd2 = Jalali.toJd(d)
        if (jd2 != jd) {
            println(kotlin.String.format("ERROR: jd=%d  ->  date=%s  ->  jd=%d", jd, formatDate(d), jd2))
        }
    }
}
