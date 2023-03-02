package gov.et.ets.feature_users.presentation.lock.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PasscodePadKeysLayout(
    modifier: Modifier = Modifier,
    onPasscodePadKeyClicked: (String) -> Unit,
    onBackClicked: () -> Unit = {},
    onBackLongClicked: () -> Unit = {},
    useFingerPrint: Boolean,
    onLaunchBiometricPressed: () -> Unit
) {
    return Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PasscodePadKey(
                modifier = Modifier,
                key = "1",
                onClick = onPasscodePadKeyClicked
            )
            PasscodePadKey(
                modifier = Modifier,
                key = "2",
                onClick = onPasscodePadKeyClicked
            )
            PasscodePadKey(
                modifier = Modifier,
                key = "3",
                onClick = onPasscodePadKeyClicked
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PasscodePadKey(
                modifier = Modifier,
                key = "4",
                onClick = onPasscodePadKeyClicked
            )
            PasscodePadKey(
                modifier = Modifier,
                key = "5",
                onClick = onPasscodePadKeyClicked
            )
            PasscodePadKey(
                modifier = Modifier,
                key = "6",
                onClick = onPasscodePadKeyClicked
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PasscodePadKey(
                modifier = Modifier,
                key = "7",
                onClick = onPasscodePadKeyClicked
            )
            PasscodePadKey(
                modifier = Modifier,
                key = "8",
                onClick = onPasscodePadKeyClicked
            )
            PasscodePadKey(
                modifier = Modifier,
                key = "9",
                onClick = onPasscodePadKeyClicked
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FingerPrintButton(
                useFingerPrint = useFingerPrint,
                onLaunchBiometricPressed = onLaunchBiometricPressed
            )
            PasscodePadKey(
                modifier = Modifier,
                key = "0",
                onClick = onPasscodePadKeyClicked
            )
            BackButton(onBackClicked, onBackLongClicked)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BackButton(
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clip(CircleShape)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(8.dp)
            .size(PAD_KEY_SIZE.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            Icons.Default.Backspace,
            contentDescription = null
        )
    }
}

@Composable
fun FingerPrintButton(
    useFingerPrint: Boolean,
    onLaunchBiometricPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(CircleShape)
            .padding(8.dp)
            .size(PAD_KEY_SIZE.dp)
            .alpha(if (useFingerPrint) 1f else 0f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        IconButton(
            onClick = onLaunchBiometricPressed,
            enabled = useFingerPrint
        ) {
            Icon(
                Icons.Default.Fingerprint,
                contentDescription = null
            )
        }
    }
}