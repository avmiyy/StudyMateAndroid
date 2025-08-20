package ru.vafeen.presentation.ui.screen.nav_root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.vafeen.presentation.ui.screen.advertisements.AdvertisementsScreen
import ru.vafeen.presentation.ui.theme.MainTheme

@Composable
internal fun NavRoot() {
    MainTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                AdvertisementsScreen()
            }
        }
    }
}