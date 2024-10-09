// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0


package com.amazonappstore.helloworldtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.StandardCardContainer
import androidx.tv.material3.Text
import com.amazonappstore.helloworldtv.ui.theme.BlueGray300
import com.amazonappstore.helloworldtv.ui.theme.HelloWorldTVTheme
import com.amazonappstore.helloworldtv.ui.theme.Red300

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            HelloWorldTVTheme {

                NavHost(
                    navController = navController,
                    startDestination = Screens.Home.name,
                    builder = {
                        composable(
                            route = Screens.Home.name
                        ) {
                            HomeDrawer(content = {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(5),
                                    contentPadding = PaddingValues(
                                        start = 24.dp,
                                        top = 24.dp,
                                        end = 24.dp,
                                        bottom = 48.dp
                                    ),
                                ) {
                                    item(span = {
                                        GridItemSpan(5)
                                    }) {
                                        GridHeader()
                                    }
                                    items(30) {
                                        var isFocused by remember { mutableStateOf(false) }

                                        StandardCardContainer(
                                            imageCard = {
                                                Card(
                                                    shape = CardDefaults.shape(shape = ShapeDefaults.ExtraSmall),
                                                    border = CardDefaults.border(
                                                        focusedBorder = Border(
                                                            border = BorderStroke(
                                                                width = 3.dp,
                                                                color = MaterialTheme.colorScheme.onSurface
                                                            ),
                                                            shape = ShapeDefaults.ExtraSmall
                                                        ),
                                                        pressedBorder = Border(
                                                            border = BorderStroke(
                                                                width = 3.dp,
                                                                color = MaterialTheme.colorScheme.border
                                                            ),
                                                            shape = ShapeDefaults.ExtraSmall
                                                        )
                                                    ),
                                                    scale = CardDefaults.scale(focusedScale = 1f),
                                                    onClick = { },
                                                    interactionSource = it
                                                ) {
                                                    val itemAlpha by animateFloatAsState(
                                                        targetValue = if (isFocused) .6f else 0.2f,
                                                        label = ""
                                                    )
                                                    val textColor =
                                                        if (isFocused) Color.White else Color.White

                                                    Box(contentAlignment = Alignment.Center) {
                                                        Box(modifier = Modifier.alpha(itemAlpha)) {
                                                            GradientBg()
                                                        }
                                                        Text(
                                                            text = "Movie Name",
                                                            style = MaterialTheme.typography.titleMedium.copy(
                                                                color = textColor,
                                                            )
                                                        )
                                                    }
                                                }
                                            },
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .aspectRatio(16 / 9f)
                                                .onFocusChanged {
                                                    isFocused = it.isFocused || it.hasFocus
                                                },
                                            title = {}
                                        )
                                    }
                                }
                            }, onMenuSelected = {})
                        }
                    })

            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun GridHeader() {
    Text(
        text = "Movies",
        style = MaterialTheme.typography.titleLarge,
        color = Red300,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp),
    )
}

@Composable
fun GradientBg() {
    Box(
        modifier = Modifier
            .background(Brush.radialGradient((Red300 to BlueGray300).toList()))
            .fillMaxWidth()
            .height(200.dp)
    )
}