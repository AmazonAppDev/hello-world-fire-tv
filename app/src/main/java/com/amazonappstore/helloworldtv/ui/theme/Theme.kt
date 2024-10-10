// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0


package com.amazonappstore.helloworldtv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme

@Composable
fun HelloWorldTVTheme(
    isInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isInDarkTheme) {
        darkColorScheme(
            primary = Purple80,
            secondary = PurpleGrey80,
            tertiary = Pink80,
            background = Black,
            surfaceVariant = Color.LightGray
        )
    } else {
        lightColorScheme(
            primary = Purple40,
            secondary = PurpleGrey40,
            tertiary = Pink40,
            background = Gray400,
            surfaceVariant = Black
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}