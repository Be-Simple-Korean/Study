package study.test.discountcalc.ui

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import study.test.discountcalc.Greeting
import study.test.discountcalc.ui.theme.StudyTestDiscountCalcTheme


@Composable
fun DiscountScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = "",// TODO
            onValueChange = {
                // TODO
            },
            label = { Text("원가") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("originalPriceInput"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "", // TODO
            onValueChange = { // TODO
            },
            label = { Text("할인율(%)") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("discountPercentInput"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Todo
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("calculateButton")
        ) {
            Text("계산하기")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "최종 금액:  원", // todo
            modifier = Modifier.testTag("resultText"),
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DiscountScreenPreview() {
    DiscountScreen()
}