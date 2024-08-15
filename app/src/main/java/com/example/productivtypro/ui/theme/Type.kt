package com.example.productivtypro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.productivtypro.R

val tilliumweb_light = FontFamily(
    Font(R.font.titilliumweb_light)
)
val tilliumweb_extralight = FontFamily(
    Font(R.font.titilliumweb_extralight)
)

val tilliumweb_extralight_it = FontFamily(
    Font(R.font.titilliumweb_extralightitalic)
)

val tilliumweb_bold_it = FontFamily(
    Font(R.font.titilliumweb_semibolditalic)
)

val tilliumweb_bold = FontFamily(
    Font(R.font.titilliumweb_bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = tilliumweb_light,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)