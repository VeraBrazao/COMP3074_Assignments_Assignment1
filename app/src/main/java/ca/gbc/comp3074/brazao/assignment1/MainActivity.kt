package ca.gbc.comp3074.brazao.assignment1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.R
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Scaffold
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import ca.gbc.comp3074.brazao.assignment1.ui.theme.Assignment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SalaryCalculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun SalaryCalculator(modifier: Modifier = Modifier) {
    var hoursWorked by remember { mutableStateOf("") }
    var hourlyRate by remember { mutableStateOf("") }
    var taxRate by remember { mutableStateOf("") }
    var pay by remember { mutableStateOf(0.0) }
    var overtime_pay by remember { mutableStateOf(0.0) }
    var total_pay by remember { mutableStateOf(0.0) }
    var tax by remember { mutableStateOf(0.0) }

    val context = LocalContext.current


    Column(
        modifier = modifier
            .fillMaxSize().background(Color(0xFFF2E6D8))


    ) {
        Text(
            text = "Salary Calculator",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )



        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = modifier.fillMaxWidth()) {

            Column(modifier = Modifier.weight(1.5f).padding(start = 16.dp)) {

                OutlinedTextField(
                    value = hoursWorked,
                    onValueChange = { hoursWorked = it },
                    label = { Text("Hours worked") },
                    modifier = Modifier.width(150.dp).height(64.dp)

                )

                OutlinedTextField(
                    value = hourlyRate,
                    onValueChange = { hourlyRate = it },
                    label = { Text("Hourly Rate") },
                    modifier = Modifier.width(150.dp).height(64.dp)
                )

                OutlinedTextField(
                    value = taxRate,
                    onValueChange = { taxRate = it },
                    label = { Text("Tax Rate") },
                    modifier = Modifier.width(150.dp).height(64.dp)
                )


                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        val no_of_hours = hoursWorked.toDoubleOrNull() ?: 0.0
                        val hourly_rate = hourlyRate.toDoubleOrNull() ?: 0.0
                        val tax_rate = taxRate.toDoubleOrNull() ?: 0.0

                        if (no_of_hours <= 40) {
                            pay = no_of_hours * hourly_rate
                            overtime_pay = 0.0
                        } else {
                            pay = 40 * hourly_rate
                            overtime_pay = (no_of_hours - 40) * hourly_rate * 1.5
                        }

                        total_pay = pay + overtime_pay
                        tax = pay * tax_rate
                    }
                ) {
                    Text("Calculate pay")
                }
            }
            Column(
                modifier = Modifier.weight(1f).padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Row {
                    Text(
                        text = "Pay: $%.2f".format(pay),
                        modifier = Modifier.width(130.dp)
                    )
                }

                Row {
                    Text(
                        text = "Overtime Pay: $%.2f".format(overtime_pay),
                        modifier = Modifier.width(130.dp)
                    )

                }

                Row {
                    Text(
                        text = "Total Pay: $%.2f".format(total_pay),
                        modifier = Modifier.width(130.dp)
                    )

                }

                Row {
                    Text(
                        text = "Tax: $%.2f".format(tax),
                        modifier = Modifier.width(130.dp)
                    )

                }

                Button(
                    onClick = {
                        val intent = Intent(context, AboutActivity::class.java)
                        context.startActivity(intent)
                    }
                ){
                    Text("About")
                }
            }
        }

    }
}
    @Preview(showBackground = true)
    @Composable
    fun SalaryCalculatorPreview() {
        Assignment1Theme {
            SalaryCalculator()
        }
    }
