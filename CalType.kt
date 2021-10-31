
interface CalType {
    fun isLeap(year: Int): Boolean {}
    fun toJd(d: LocalDate): Int {}
    fun jdTo(jd: Int): LocalDate {}
}
