package gov.et.ets.feature_users.presentation.lock

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class LockPageTooManyTriesUIModel(
    val totalTime: Long,
    val remainingTime: Long
)
//    : TelebirrPageUIModel
interface LockPageTooManyTriesUIController

//    : UIController
{
    fun onCountDownEnded()
    fun onCheckRemainingTime()
}

@Composable
fun LockPageTooManyTries(
    model: LockPageTooManyTriesUIModel,
    controller: LockPageTooManyTriesUIController
){
    var currentTime by remember {
        mutableStateOf(model.totalTime)
    }

    LaunchedEffect(key1 = currentTime) {
        if(currentTime > 0) {
            delay(1000L)
            currentTime -= 1000L
            controller.onCheckRemainingTime()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = Icons.Filled.Lock,
            contentDescription = "Lock"
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Too many tries",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.alpha(ContentAlpha.medium),
            text = "Please try again in ${model.remainingTime} seconds",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
        if(model.remainingTime <= 0L) {
            controller.onCountDownEnded()
        }
    }
}