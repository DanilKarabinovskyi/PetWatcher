package com.example.petwatcher.core.presentation.components

import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    state: ScaffoldState,
    content: @Composable () -> Unit
) {
    Scaffold(
        scaffoldState = state,
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) {
        content()
    }
}