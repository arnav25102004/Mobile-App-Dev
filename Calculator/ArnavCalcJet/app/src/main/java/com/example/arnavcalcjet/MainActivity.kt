package com.example.arnavcalcjet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ACTIVITY - Starting point of app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp() {

    // Variables to store calculator data
    var display by remember { mutableStateOf("0") }
    var firstNum by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }

    // BOX LAYOUT - Main container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        // COLUMN LAYOUT - Arranges items vertically
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom
        ) {

            // Display Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = display,
                    fontSize = 48.sp,
                    color = Color.White,
                    textAlign = TextAlign.End
                )
            }

            // ROW 1 - Numbers 7, 8, 9 and ÷
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CalcButton("7") { display = if (display == "0") "7" else display + "7" }
                CalcButton("8") { display = if (display == "0") "8" else display + "8" }
                CalcButton("9") { display = if (display == "0") "9" else display + "9" }
                CalcButton("÷") {
                    firstNum = display
                    operation = "÷"
                    display = "0"
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ROW 2 - Numbers 4, 5, 6 and ×
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CalcButton("4") { display = if (display == "0") "4" else display + "4" }
                CalcButton("5") { display = if (display == "0") "5" else display + "5" }
                CalcButton("6") { display = if (display == "0") "6" else display + "6" }
                CalcButton("×") {
                    firstNum = display
                    operation = "×"
                    display = "0"
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ROW 3 - Numbers 1, 2, 3 and -
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CalcButton("1") { display = if (display == "0") "1" else display + "1" }
                CalcButton("2") { display = if (display == "0") "2" else display + "2" }
                CalcButton("3") { display = if (display == "0") "3" else display + "3" }
                CalcButton("-") {
                    firstNum = display
                    operation = "-"
                    display = "0"
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ROW 4 - C, 0, = and +
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CalcButton("C") {
                    display = "0"
                    firstNum = ""
                    operation = ""
                }
                CalcButton("0") { display = if (display == "0") "0" else display + "0" }
                CalcButton("=") {
                    if (firstNum.isNotEmpty()) {
                        val num1 = firstNum.toDouble()
                        val num2 = display.toDouble()
                        val result = when (operation) {
                            "+" -> num1 + num2
                            "-" -> num1 - num2
                            "×" -> num1 * num2
                            "÷" -> num1 / num2
                            else -> num2
                        }
                        display = if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
                        firstNum = ""
                        operation = ""
                    }
                }
                CalcButton("+") {
                    firstNum = display
                    operation = "+"
                    display = "0"
                }
            }
        }
    }
}

// Simple Button Component
@Composable
fun RowScope.CalcButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .height(70.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD6C8FF)
        )
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}