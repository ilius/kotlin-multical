import java.time.LocalDate


fun formatDate(d: LocalDate): String {
    return kotlin.String.format("%04d/%02d/%02d", d.year, d.monthValue, d.dayOfMonth)
}