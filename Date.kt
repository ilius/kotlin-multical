import java.time.LocalDate

class Date(_year: Int, _month: Int, _day: Int) {
    val year: Int = _year
    val month: Int = _month
    val day: Int = _day

    constructor(d: LocalDate) : this(d.year, d.monthValue, d.dayOfMonth)

    init {
    }
}
