package br.edu.fatecpg.calculadoradesconto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun CalculadoraDescontoScreen(modifier: Modifier = Modifier) {
    var precoTexto by remember { mutableStateOf("") }
    var descontoTexto by remember { mutableStateOf("") }
    var precoFinalTexto by remember { mutableStateOf("________") }
    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Calculadora de Desconto",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Preço do produto",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = precoTexto,
            onValueChange = { precoTexto = it },
            placeholder = { Text("Ex: 500.00") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Percentual de desconto",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = descontoTexto,
            onValueChange = { descontoTexto = it },
            placeholder = { Text("Ex: 15") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                val preco = precoTexto.replace(",", ".").toDoubleOrNull()
                val percentual = descontoTexto.replace(",", ".").toDoubleOrNull()

                if (preco == null || preco < 0) {
                    mensagemErro = "Informe um preço válido."
                    precoFinalTexto = "________"
                } else if (percentual == null || percentual < 0 || percentual > 100) {
                    mensagemErro = "Informe um desconto válido (0 a 100%)."
                    precoFinalTexto = "________"
                } else {
                    mensagemErro = null
                    val resultado = calcularPrecoComDesconto(preco, percentual)
                    precoFinalTexto = String.format(Locale("pt", "BR"), "%.2f", resultado)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "CALCULAR", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (mensagemErro != null) {
            Text(
                text = mensagemErro!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = "Preço final: R$ $precoFinalTexto",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}