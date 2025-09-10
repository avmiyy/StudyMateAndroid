package ru.vafeen.presentation.ui.common.components.filters

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vafeen.presentation.R
import ru.vafeen.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FiltersBottomSheet(
    state: FiltersState,
    onChangeFiltersState: (FiltersState) -> Unit,
    onDismissRequest: () -> Unit
) {
    var newState by remember { mutableStateOf(state) }
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = AppTheme.colors.backgroundText
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "Фильтры",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Город",
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {}
                    .border(
                        BorderStroke(1.dp, AppTheme.colors.serviceNames),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 11.dp)
                    .height(27.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (newState.city != null) {
                    newState.city?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            color = AppTheme.colors.text
                        )
                    }
                } else {
                    Text(
                        text = "Выберите город",
                        fontSize = 14.sp,
                        color = AppTheme.colors.serviceNames
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.arrow_down), // Замените на вашу иконку стрелки
                    contentDescription = "Раскрыть список",
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Пол",
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        BorderStroke(1.dp, AppTheme.colors.serviceNames),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .height(27.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                GenderButton(
                    modifier = Modifier.weight(1f),
                    gender = Gender.Every,
                    currentGender = newState.gender,
                    text = "Любой"
                ) { newState = newState.copy(gender = Gender.Every) }
                GenderButton(
                    modifier = Modifier.weight(1f),
                    gender = Gender.Male,
                    currentGender = newState.gender,
                    text = "Мужской"
                ) { newState = newState.copy(gender = Gender.Male) }
                GenderButton(
                    modifier = Modifier.weight(1f),
                    gender = Gender.Female,
                    currentGender = newState.gender,
                    text = "Женский"
                ) { newState = newState.copy(gender = Gender.Female) }
            }

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Возраст",
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                AgeButton(
                    modifier = Modifier.weight(1f),
                    currentAge = newState.ageFrom,
                    title = "От"
                ) { newState = newState.copy(ageFrom = it) }
                Spacer(modifier = Modifier.width(11.dp))
                AgeButton(
                    modifier = Modifier.weight(1f),
                    currentAge = newState.ageTo,
                    title = "До"
                ) { newState = newState.copy(ageTo = it) }
            }

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Теги",
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    enabled = state != newState,
                    onClick = { onChangeFiltersState(newState) },
                    colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colors.buttonColor)
                ) {
                    Text(text = "Сохранить", color = Color.White, fontSize = 14.sp)
                }
                TextButton(
                    enabled = state != newState,
                    onClick = { newState = state },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = AppTheme.colors.buttonColor,
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = AppTheme.colors.buttonColor,
                    )
                ) {
                    Text(text = "Очистить", fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
internal fun GenderButton(
    modifier: Modifier = Modifier,
    gender: Gender,
    currentGender: Gender,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .height(27.dp)
            .let {
                if (currentGender == gender) it.background(AppTheme.colors.buttonColor)
                else it.clickable(onClick = onClick)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = if (currentGender == gender) AppTheme.colors.backgroundText
            else AppTheme.colors.serviceNames,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
internal fun AgeDropDownMenuButton(text: String, onClick: () -> Unit) =
    DropdownMenuItem(
        text = { Text(text = text, fontSize = 14.sp, color = AppTheme.colors.text) },
        onClick = onClick
    )

@Composable
internal fun AgeButton(
    modifier: Modifier = Modifier,
    currentAge: Int?,
    title: String,
    onAgeChange: (Int?) -> Unit
) {
    var isDropdownMenuVisible by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .border(
                BorderStroke(1.dp, AppTheme.colors.serviceNames),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { isDropdownMenuVisible = true }
            .padding(horizontal = 11.dp)
            .height(27.dp)
    ) {
        if (isDropdownMenuVisible) {
            DropdownMenu(
                modifier = Modifier.height(200.dp),
                onDismissRequest = { isDropdownMenuVisible = false },
                expanded = isDropdownMenuVisible
            ) {
                AgeDropDownMenuButton(title) {
                    onAgeChange(null)
                    isDropdownMenuVisible = false
                }
                (0..99).forEach {
                    AgeDropDownMenuButton("$it") {
                        onAgeChange(it)
                        isDropdownMenuVisible = false
                    }
                }
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = title,
            fontSize = 14.sp,
            color = AppTheme.colors.serviceNames
        )
        if (currentAge != null) {
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "$currentAge",
                fontSize = 14.sp,
                color = AppTheme.colors.text
            )
        }


    }


}
