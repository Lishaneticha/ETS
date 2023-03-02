package gov.et.ets.feature_users.presentation.lock

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gov.et.ets.feature_users.presentation.lock.components.PasscodePadInput
import gov.et.ets.feature_users.presentation.lock.components.PasscodePadKeysLayout
import gov.et.ets.ui.theme.ETSTheme

data class LockPageUIModel(
    val padText: String,
    val selectionStart: Int,
    val selectionEnd: Int,
    val useFingerPrint: Boolean
)
//    : TelebirrPageUIModel

interface LockPageController
//    : UIController
{
    fun onTextChanged(text: String)
    fun onBackspaceClicked()
    fun onBackspaceLongClicked()
    fun onPasscodePadKeyClicked(key: String)
    fun onLaunchBiometricPressed()
}

@Composable
fun PasscodePad(
    text: String,
    keysPadding: PaddingValues = PaddingValues(0.dp),
    onTextChanged: (text: String) -> Unit,
    onBackspaceClicked: () -> Unit,
    onBackspaceLongClicked: () -> Unit,
    onPasscodePadKeyClicked: (String) -> Unit,
    useFingerPrint: Boolean,
    onLaunchBiometricPressed: () -> Unit
) {
    return Column(
        Modifier
            .background(MaterialTheme.colors.surface)
            .pointerInput(Unit) {
                detectTapGestures {

                }
            }
    ) {
        Divider(color = MaterialTheme.colors.onBackground.copy(alpha = 0.08f))
        PasscodePadInput(
            text = text,
            onTextChanged = onTextChanged,
            editable = true
        )
        PasscodePadKeysLayout(
            modifier = Modifier.padding(keysPadding),
            onPasscodePadKeyClicked = onPasscodePadKeyClicked,
            onBackClicked = onBackspaceClicked,
            onBackLongClicked = onBackspaceLongClicked,
            useFingerPrint = useFingerPrint,
            onLaunchBiometricPressed = onLaunchBiometricPressed
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun LockPage(
    model: LockPageUIModel,
    controller: LockPageController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier
                .height(100.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Filled.Lock,
                contentDescription = "Lock"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            text = "Enter your passcode"
        )
        Spacer(modifier = Modifier.height(32.dp))
        PasscodePad(
            text = model.padText,
            keysPadding = PaddingValues(horizontal = 42.dp),
            onTextChanged = controller::onTextChanged,
            onBackspaceClicked = controller::onBackspaceClicked,
            onBackspaceLongClicked = controller::onBackspaceLongClicked,
            onPasscodePadKeyClicked = controller::onPasscodePadKeyClicked,
            useFingerPrint = model.useFingerPrint,
            onLaunchBiometricPressed = controller::onLaunchBiometricPressed
        )
    }
}

@Preview(showBackground = true)
@Composable
fun lockKeyPreview() {
    ETSTheme {
        LockPage(
            model = LockPageUIModel(
                padText = "",
                selectionStart = 0,
                selectionEnd = 0,
                useFingerPrint = true
            ),
            controller = object : LockPageController {
                override fun onTextChanged(text: String) {

                }

                override fun onBackspaceClicked() {

                }

                override fun onBackspaceLongClicked() {

                }

                override fun onPasscodePadKeyClicked(key: String) {

                }

                override fun onLaunchBiometricPressed() {

                }

            }
        )
    }
}