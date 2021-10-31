/**
 * We declare a package-level function main which returns Unit and takes
 * an Array of strings as a parameter. Note that semicolons are optional.
 */

import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Arrays


class Gregorian {
    companion object {
        const val name        = "gregorian"
        const val desc        = "Gregorian"
        const val epoch       = 1721426
        const val minMonthLen = 29
        const val maxMonthLen = 31
        const val avgYearLen  = 365.2425 // FIXME
        
        const val J1970:Int   = 2440588
        
        val monthNames = arrayOf(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        )
        val monthNamesAb = arrayOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )
        
        fun isLeap(year: Int):Boolean {
            return year%4 == 0 && (year%100 != 0 || year%400 == 0)
        }
        fun toJd(d: Date):Int {
            return J1970 + LocalDate.of(d.year, d.month, d.day).toEpochDay().toInt()
        }
        fun jdTo(jd: Int): Date {
            return Date(LocalDate.ofEpochDay((jd - J1970).toLong()))
        }
    }
}



fun main() {
    val d = Date(LocalDate.now())
    val jd = Gregorian.toJd(d)
    println(kotlin.String.format("jd = %d", jd))
    val d2 = Gregorian.jdTo(jd)
    println(" d = " + formatDate(d2))
    println("d2 = " + formatDate(d2))
}
