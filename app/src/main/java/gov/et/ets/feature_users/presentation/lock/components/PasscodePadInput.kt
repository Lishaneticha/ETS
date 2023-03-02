package gov.et.ets.feature_users.presentation.lock.components

import android.text.InputType
import android.widget.EditText
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PasscodePadInput(
    text: String = "",
    editable: Boolean,
    onTextChanged: (text: String) -> Unit = {},
) {

    return Row(verticalAlignment = Alignment.CenterVertically) {
        if (editable) {
            val textLength = text.length
            val size = (392 / textLength.coerceAtLeast(1)).coerceIn(21..28)
            PasscodeTextField(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp),
                text = text,
                showSoftInputOnFocus = false,
                inputType = InputType.TYPE_CLASS_PHONE,
                clearBackground = true,
                onTextChanged = onTextChanged,
                textSize = size.sp,
                textAlignment = EditText.TEXT_ALIGNMENT_CENTER,
                applySelections = false,
            )
        } else {
            SelectionContainer(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                )
            }
        }
    }
}
