package com.example.productivtypro.Habits

import kotlinx.datetime.LocalDate


fun FormatDate(day: LocalDate):String{
    return day.toString().substring(8)+ '-' + day.toString().substring(5,7) + '-'+ day.toString().substring(0,4)
}

fun calculateDateDifference(date1Str: String, date2Str: String): Long {
    val (day1, month1, year1) = date1Str.split("-").map { it.toInt() }
    val (day2, month2, year2) = date2Str.split("-").map { it.toInt() }

    val daysInMonth = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    // Function to check if a year is a leap year
    fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    // Function to get the number of days in a month for a given year
    fun daysInMonth(year: Int, month: Int): Int {
        return if (month == 2 && isLeapYear(year)) 29 else daysInMonth[month]
    }

    // Function to calculate the number of days from the beginning of the year
    fun daysFromStart(year: Int, month: Int, day: Int): Long {
        var totalDays = day.toLong()

        for (m in 1 until month) {
            totalDays += daysInMonth(year, m)
        }

        return totalDays
    }

    // Calculate the total number of days from the start of each year
    val totalDays1 = daysFromStart(year1, month1, day1)
    val totalDays2 = daysFromStart(year2, month2, day2)

    // Calculate the difference in days
    val differenceInDays = if (year1 == year2) {
        totalDays2 - totalDays1
    } else {
        var difference = daysFromStart(year2, 1, 1) - totalDays2
        for (year in year2 - 1 downTo year1 + 1) {
            difference += if (isLeapYear(year)) 366 else 365
        }
        difference += totalDays1
        difference
    }

    return differenceInDays
}
