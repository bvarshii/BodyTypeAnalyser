package com.example.bodytypeanalyser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip

@Composable
fun QuizScreen(onFinishQuiz: (String) -> Unit) {
    val questions = listOf(
        "Is your waist smaller than your hips?",
        "Which is wider, your hips or shoulders?",
        "How would you describe your upper body in comparison to your lower body?",
        "How defined is your waistline?",
        "When you gain weight, where do you notice it most?",
        "How would you describe your overall body shape?"
    )

    val options = listOf(
        listOf("Yes, significantly smaller", "Yes, slightly smaller", "No, they are about the same", "No, my waist is larger"),
        listOf("My hips are much wider", "My hips are slightly wider", "They are about the same", "My shoulders are wider"),
        listOf("My lower body is much larger", "My lower body is slightly larger", "They are about the same", "My upper body is larger"),
        listOf("Very defined", "Moderately defined", "Slightly defined", "Not defined at all"),
        listOf("Hips and thighs", "Evenly distributed", "Waist and midsection", "Upper body and stomach"),
        listOf("Bottom-heavy (pear-shaped)", "Balanced (hourglass)", "Straight up and down (rectangle)", "Top-heavy (inverted triangle)")
    )

    val imageResources = listOf(
        R.drawable.image1, R.drawable.image2, R.drawable.image3,
        R.drawable.image4, R.drawable.image5, R.drawable.image6
    )

    var currentQuestion by remember { mutableStateOf(0) }
    val answers = remember { mutableStateListOf<Int>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)) // Beige color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Add header gap
            Spacer(modifier = Modifier.height(16.dp))

            if (currentQuestion < questions.size) {
                Text(text = questions[currentQuestion])
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = imageResources[currentQuestion]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                options[currentQuestion].forEachIndexed { index, option ->
                    Button(
                        onClick = {
                            if (answers.size > currentQuestion) {
                                answers[currentQuestion] = index
                            } else {
                                answers.add(index)
                            }
                            if (currentQuestion < questions.size - 1) {
                                currentQuestion++
                            } else {
                                val result = determineBodyType(answers)
                                onFinishQuiz(result)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC0CB)), // Baby pink color
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = option)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

fun determineBodyType(answers: List<Int>): String {
    val frequency = mutableMapOf('A' to 0, 'B' to 0, 'C' to 0, 'D' to 0)

    answers.forEach {
        when (it) {
            0 -> frequency['A'] = frequency['A']!! + 1
            1 -> frequency['B'] = frequency['B']!! + 1
            2 -> frequency['C'] = frequency['C']!! + 1
            3 -> frequency['D'] = frequency['D']!! + 1
        }
    }

    val mostFrequent = frequency.maxByOrNull { it.value }?.key

    return when (mostFrequent) {
        'A' -> "Pear"
        'B' -> "Hourglass"
        'C' -> "Rectangle"
        'D' -> "Inverted Triangle"
        else -> "Unknown"
    }
}
