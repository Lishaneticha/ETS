package gov.et.ets.feature_users.presentation.lock.components

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText

class MyPasscodeEditText(context: Context) : AppCompatEditText(context) {
    override fun onSelectionChanged(start: Int, end: Int) {
        val text = text
        if (text != null) {
            if (start != text.length || end != text.length) {
                setSelection(text.length, text.length);
                return;
            }
        }
        super.onSelectionChanged(start, end)
    }
}