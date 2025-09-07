package ru.vafeen.presentation.ui.screen.nav_root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.vafeen.presentation.ui.screen.advertisements.AdvertisementsScreen
import ru.vafeen.presentation.ui.theme.AppTheme
import ru.vafeen.presentation.ui.theme.MainTheme

/**
 * Восходящая навигационная точка входа приложения,
 * оформленная в тему и с основным Scaffold.
 *
 * Внутри отображается экран объявлений.
 */
@Composable
internal fun NavRoot() {
    MainTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = AppTheme.colors.background
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                AdvertisementsScreen()
            }
        }
    }
}
