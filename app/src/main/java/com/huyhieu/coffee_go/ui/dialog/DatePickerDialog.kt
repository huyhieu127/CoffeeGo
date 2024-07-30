package com.huyhieu.coffee_go.ui.dialog

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huyhieu.coffee_go.ui.common.AppPrimaryButton
import com.huyhieu.coffee_go.ui.theme.AppTypography
import com.huyhieu.coffee_go.ui.theme.Primary
import com.huyhieu.coffee_go.ui.theme.utils.brush.BrushStyle
import com.huyhieu.coffee_go.ui.theme.utils.type.FontStyle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyDatePickerDialog(
    datePickerState: DatePickerState = rememberDatePickerState(selectableDates = object :
        SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    }),
    onDateSelected: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
) {

    val selectedDate = datePickerState.selectedDateMillis?.let {
        longToDate(it)
    } ?: ""

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = Color.White,
            primary = Primary,
            //onPrimary = PrimaryLight,
        ), typography = AppTypography
    ) {
        DatePickerDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                AppPrimaryButton(
                    modifier = Modifier
                        .width(80.dp),
                    text = "OK",
                    onClick = {
                        onDateSelected(selectedDate)
                        onDismiss()
                    },
                )
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                    },
                ) {
                    Text(
                        text = "Cancel",
                        style = FontStyle.Regular.copy(
                            fontSize = 16.sp,
                            brush = BrushStyle.GradientPrimary_Horizontal,
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            },
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}

private fun longToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}