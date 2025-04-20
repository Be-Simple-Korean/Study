package study.test.discountcalc.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import study.test.discountcalc.DiscountViewModel

@Composable
fun DiscountScreen(
    modifier: Modifier = Modifier,
    viewModel : DiscountViewModel = remember { DiscountViewModel() }
) {
    val originalPrice by viewModel.originalPrice.collectAsState()
    val discountPer by viewModel.discountPer.collectAsState()
    val finalPrice by viewModel.finalPrice.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = originalPrice,
            onValueChange = {
                viewModel.setOriginalPrice(it)
            },
            label = { Text("원가") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("originalPriceInput"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = discountPer,
            onValueChange = {
                viewModel.setDiscountPer(it)
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
                viewModel.calculateFinalPrice()
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("calculateButton")
        ) {
            Text("계산하기")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "최종 금액: $finalPrice 원",
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