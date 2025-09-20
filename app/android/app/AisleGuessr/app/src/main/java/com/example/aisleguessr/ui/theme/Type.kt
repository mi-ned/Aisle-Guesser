package com.example.aisleguessr.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.aisleguessr.R

val PoppinsFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val RobotoCondensedFamily = FontFamily(
    Font(R.font.roboto_condensed_regular, FontWeight.Normal),
    Font(R.font.roboto_condensed_bold, FontWeight.Bold)
)

val KameronFamily = FontFamily(
    Font(R.font.kameron_regular, FontWeight.Normal),
    Font(R.font.kameron_bold, FontWeight.Bold)
)

val CardoFamily = FontFamily(
    Font(R.font.cardo_regular, FontWeight.Normal),
    Font(R.font.cardo_bold, FontWeight.Bold)
)

val InterFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val Typography = Typography(

    //App Title
    displayLarge = TextStyle(
        fontFamily = KameronFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),

    //Headers
    headlineSmall = TextStyle(
        fontFamily = CardoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = KameronFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    //small body text
    bodySmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    ),

    //main body text
    bodyMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    //body text
    bodyLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    //Hints
    labelSmall = TextStyle(
        fontFamily = RobotoCondensedFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),

    //Back Buttons
    labelMedium = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    //Main Menu buttons
    labelLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp
    )

    //TODO
    //sort out the naming. it's pathetic
)
