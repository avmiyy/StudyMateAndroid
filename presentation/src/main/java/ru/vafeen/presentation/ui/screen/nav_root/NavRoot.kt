package ru.vafeen.presentation.ui.screen.nav_root

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vafeen.presentation.R
import ru.vafeen.presentation.ui.screen.advertisements.AdvertisementsScreen
import ru.vafeen.presentation.ui.theme.AppTheme
import ru.vafeen.presentation.ui.theme.MainTheme

/**
 * Восходящая навигационная точка входа приложения,
 * оформленная в тему и с основным Scaffold.
 *
 * Внутри отображается экран объявлений.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NavRoot() {
    MainTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = AppTheme.colors.background,
            topBar = {
                TopAppBar(
                    modifier = Modifier.shadow(10.dp),
                    title = {
                        Image(
                            modifier = Modifier
                                .size(width = 121.dp, height = 30.dp),
                            painter = painterResource(R.drawable.studymeet),
                            contentDescription = "StudyMeet icon"
                        )
                    }, actions = {
                        TextButton(onClick = {}) {
                            Text("Войти", color = AppTheme.colors.buttonColor, fontSize = 16.sp)
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                AdvertisementsScreen()
            }
        }
    }
}
