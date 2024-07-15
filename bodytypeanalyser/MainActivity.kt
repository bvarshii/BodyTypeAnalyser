package com.example.bodytypeanalyser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bodytypeanalyser.ui.theme.BodyTypeAnalyserTheme
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
            } else {
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodyTypeAnalyserTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf<Screen>(Screen.Main) }
                    var bodyType by remember { mutableStateOf("") }

                    val context = LocalContext.current

                    LaunchedEffect(Unit) {
                        when {
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            ) == PackageManager.PERMISSION_GRANTED -> {
                            }
                            else -> {
                                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }
                        }
                    }

                    when (currentScreen) {
                        Screen.Main -> MainScreen(onSelectQuiz = {
                            currentScreen = Screen.Quiz
                        })
                        Screen.Quiz -> QuizScreen(onFinishQuiz = {
                            bodyType = it
                            currentScreen = Screen.Result
                        })
                        Screen.Result -> ResultScreen(result = bodyType, onFindMyFitClick = {
                            currentScreen = Screen.Clothing
                        })
                        Screen.Clothing -> ClothingScreen(bodyType)
                    }
                }
            }
        }
    }
}

enum class Screen {
    Main, Quiz, Result, Clothing
}

@Composable
fun MainScreen(onSelectQuiz: (String) -> Unit) {
    val quizzes = listOf(
        "Body Type Analyser",
        "Skin Analyser",
        "Hair Analyser",
        "Lipstick Finder"
    )

    val descriptions = listOf(
        "Most flattering fit on you",
        "Products for your skin type",
        "Products for your hair type",
        "Lipsticks that go with your style"
    )

    val imageResources = listOf(
        R.drawable.body_type_icon,
        R.drawable.skin_icon,
        R.drawable.hair_icon,
        R.drawable.lipstick_icon
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "← Recommended Features",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ad_image), // replace with your actual resource
            contentDescription = "Ad Image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .padding(vertical = 8.dp) // Adjust vertical padding as needed
                .align(Alignment.Start) // Align image to the left
        )
        Spacer(modifier = Modifier.height(16.dp)) // Adjust spacing here if needed
        quizzes.forEachIndexed { index, quiz ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        if (quiz == "Body Type Analyser") {
                            onSelectQuiz(quiz)
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                ) {
                    Image(
                        painter = painterResource(id = imageResources[index]),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { if (quiz == "Body Type Analyser") onSelectQuiz(quiz) },
                    enabled = quiz == "Body Type Analyser",
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC0CB)), // Baby pink color
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = quiz, fontWeight = FontWeight.Bold)
                        Text(text = descriptions[index])
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BodyTypeAnalyserTheme {
        MainScreen(onSelectQuiz = {})
    }
}
