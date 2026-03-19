package com.example.sacred_clarity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ✅ FIXED navigation imports
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// ✅ FIXED package imports
import com.example.scaredclarity.TfidfEngine
import com.example.scaredclarity.VerseRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { MainScreen() }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAE7CE))
            .clickable {
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
            }
    ) {

        BackgroundBlobs()
        Sparkles()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("ScaredClarity", fontSize = 36.sp, fontFamily = FontFamily.Serif)

            Spacer(modifier = Modifier.height(40.dp))

            BreathingCircle()

            Spacer(modifier = Modifier.height(40.dp))

            Text("Where emotions meet the Gita", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(30.dp))

            val infinite = rememberInfiniteTransition(label = "")
            val alpha by infinite.animateFloat(
                0.4f, 1f,
                infiniteRepeatable(tween(1500), RepeatMode.Reverse),
                label = ""
            )

            Text("Tap anywhere to begin", color = Color.Gray.copy(alpha = alpha))
        }
    }
}

@Composable
fun MainScreen() {

    val context = LocalContext.current
    val repo = remember { VerseRepository(context) }
    val engine = remember { TfidfEngine(repo.verses) }

    var userInput by remember { mutableStateOf("") }
    var lesson by remember { mutableStateOf("") }
    var verseNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7F6))
            .padding(20.dp)
    ) {

        Text("How are you feeling today?", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(15.dp))

        Card(shape = RoundedCornerShape(20.dp)) {

            BasicTextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(16.dp),
                decorationBox = {
                    if (userInput.isEmpty()) {
                        Text("What are you feeling?", color = Color.Gray)
                    }
                    it()
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (userInput.isNotBlank()) {
                val result = engine.findBestMatch(userInput)
                lesson = result.lesson
                verseNumber = result.verseNumber
            }
        }) {
            Text("Reflect")
        }

        Spacer(modifier = Modifier.height(40.dp))

        // ✅ FIXED drawable usage
        FloatingImage(R.drawable.mainimg)

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility(visible = lesson.isNotEmpty()) {
            Column {
                Text(lesson)
                Text("Chapter $verseNumber", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun FloatingImage(imageRes: Int) {

    val infinite = rememberInfiniteTransition(label = "")
    val offset by infinite.animateFloat(
        -8f, 8f,
        infiniteRepeatable(tween(5000), RepeatMode.Reverse),
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .offset(y = offset.dp)
        )
    }
}

@Composable
fun BreathingCircle() {

    val infinite = rememberInfiniteTransition(label = "")

    val scale by infinite.animateFloat(
        1f, 1.05f,
        infiniteRepeatable(tween(3000), RepeatMode.Reverse),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale),
        contentAlignment = Alignment.Center
    ) {

        // ✅ FIXED drawable reference
        Image(
            painter = painterResource(R.drawable.centre),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
fun BackgroundBlobs() {
    Canvas(modifier = Modifier.fillMaxSize().blur(140.dp)) {
        drawCircle(
            brush = Brush.radialGradient(listOf(Color(0xFFFFA07A), Color.Transparent)),
            radius = 400f
        )
    }
}

@Composable
fun Sparkles() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawSparkle(Offset(100f, 200f))
    }
}

fun DrawScope.drawSparkle(center: Offset) {
    drawCircle(Color.Red, radius = 10f, center = center)
}