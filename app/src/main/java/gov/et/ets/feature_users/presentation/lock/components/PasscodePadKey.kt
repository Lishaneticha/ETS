package gov.et.ets.feature_users.presentation.lock.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val PAD_KEY_SIZE = 64

@Composable
fun PasscodePadKey(
    modifier: Modifier = Modifier,
    key: String,
    onClick: (String) -> Unit = {},
) {
    return Column(
        modifier
            .clip(CircleShape)
            .clickable { onClick(key) }
            .padding(8.dp)
            .size(PAD_KEY_SIZE.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = key, fontSize = 26.sp, fontWeight = FontWeight.Normal)
    }
}