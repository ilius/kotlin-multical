

fun formatDate(d: Date): String {
    return kotlin.String.format("%04d/%02d/%02d", d.year, d.month, d.day)
}
