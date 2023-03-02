package gov.et.ets.feature_users.presentation.lock.components

import android.app.Activity
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.doOnTextChanged


@Composable
fun PasscodeTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String = "",
    autoFocus: Boolean = false,
    clearBackground: Boolean = false,
    showSoftInputOnFocus: Boolean = true,
    inputType: Int = InputType.TYPE_CLASS_TEXT,
    applySelections: Boolean = true,
    textSize: TextUnit = 16.sp,
    textAlignment: Int = EditText.TEXT_ALIGNMENT_TEXT_START,
    onTextChanged: (text: String) -> Unit = {},
) {
    var watcher: TextWatcher? by remember {
        mutableStateOf(null)
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            MyPasscodeEditText(context).apply {
                setText(text)
                this.showSoftInputOnFocus = showSoftInputOnFocus
                this.inputType = inputType
                if (!autoFocus) clearFocus() else requestFocus()
                watcher = doOnTextChanged { changedText, _, _, _ ->
                    changedText.toString().takeIf { it != text }?.let {
                        onTextChanged(it)
                    }
                }
                if (clearBackground) background = null
                this.hint = placeHolder
                this.textSize = textSize.value
                this.textAlignment = textAlignment
                this.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        },
        update = { view ->
            try {
                if(!showSoftInputOnFocus){
                    val imm: InputMethodManager =
                        view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }

                view.removeTextChangedListener(watcher)
                view.textSize = textSize.value
                if (view.text.toString() != text) {
                    if (applySelections) view.setTextKeepState(text)
                    else view.setText(text)
                }
                view.addTextChangedListener(watcher)
                if (clearBackground) view.background = null
            } catch (e: Exception) { }
        }
    )
}