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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.vafeen.presentation.R
import ru.vafeen.presentation.ui.common.components.TagRow
import ru.vafeen.presentation.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FiltersBottomSheet(
    initialState: FiltersState,
    applyFilters: (FiltersState) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val viewModel = hiltViewModel<FiltersViewModel, FiltersViewModel.Factory>(
        creationCallback = { factory ->
            factory.create(
                initialState = initialState,
                onDismissRequest = onDismissRequest,
                applyFilters = applyFilters
            )
        })
    val state by viewModel.state.collectAsState()
    val standardHeight = 27.dp
    ModalBottomSheet(
        onDismissRequest = { viewModel.handleIntent(FiltersIntent.OnDismissRequest) },
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
                if (state.city != null) {
                    state.city?.let {
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
                    currentGender = state.gender,
                    text = "Любой"
                ) { viewModel.handleIntent(FiltersIntent.SetGender(Gender.Every)) }
                GenderButton(
                    modifier = Modifier.weight(1f),
                    gender = Gender.Male,
                    currentGender = state.gender,
                    text = "Мужской"
                ) { viewModel.handleIntent(FiltersIntent.SetGender(Gender.Male)) }
                GenderButton(
                    modifier = Modifier.weight(1f),
                    gender = Gender.Female,
                    currentGender = state.gender,
                    text = "Женский"
                ) { viewModel.handleIntent(FiltersIntent.SetGender(Gender.Female)) }
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
                    currentAge = state.ageFrom,
                    title = "От"
                ) { viewModel.handleIntent(FiltersIntent.SetAgeFrom(it)) }
                Spacer(modifier = Modifier.width(11.dp))
                AgeButton(
                    modifier = Modifier.weight(1f),
                    currentAge = state.ageTo,
                    title = "До"
                ) { viewModel.handleIntent(FiltersIntent.SetAgeTo(it)) }
            }

            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Теги",
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(5.dp))
            // поле ввода тегов
            // строчка с тегами (скопировать, уже такое есть)
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        BorderStroke(1.dp, AppTheme.colors.serviceNames),
                        shape = RoundedCornerShape(20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(AppTheme.colors.buttonColor)
                        .clickable { viewModel.handleIntent(FiltersIntent.AddCurrentTagToTags) },
                    painter = painterResource(R.drawable.plus),
                    tint = AppTheme.colors.backgroundText,
                    contentDescription = stringResource(R.string.add_this_tag)
                )
                BasicTextField(
                    value = state.currentTag,
                    onValueChange = { viewModel.handleIntent(FiltersIntent.SetCurrentTag(it)) },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = AppTheme.colors.text
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            if (state.currentTag.isEmpty()) {
                                Text(
                                    text = "Введите тег...",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    },
                    modifier = Modifier
                        .height(27.dp) // точная высота под текст
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            state.tags.TagRow()

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    enabled = initialState != state,
                    onClick = { viewModel.handleIntent(FiltersIntent.ApplyFilters) },
                    colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colors.buttonColor)
                ) {
                    Text(text = "Сохранить", color = Color.White, fontSize = 14.sp)
                }

                TextButton(
                    modifier = Modifier.weight(1f),
                    enabled = initialState != state,
                    onClick = { viewModel.handleIntent(FiltersIntent.ClearFilters) },
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
