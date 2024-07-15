package com.example.bodytypeanalyser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(result: String, onFindMyFitClick: () -> Unit) {
    val bodyTypeInfo = getBodyTypeInfo(result)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = bodyTypeInfo.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your Body Type is: $result",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF5E1), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = bodyTypeInfo.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Serif
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFC0CB), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "Dos:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
                bodyTypeInfo.dos.forEach {
                    Text(
                        text = "\u2022 $it",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Don'ts:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
                bodyTypeInfo.donts.forEach {
                    Text(
                        text = "\u2022 $it",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onFindMyFitClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Find My Fit")
        }
    }
}

data class BodyTypeInfo(
    val imageRes: Int,
    val description: String,
    val dos: List<String>,
    val donts: List<String>
)

fun getBodyTypeInfo(bodyType: String): BodyTypeInfo {
    return when (bodyType) {
        "Pear" -> BodyTypeInfo(
            imageRes = R.drawable.pear_shape,
            description = "You have a classic pear shape! Your hips are wider than your shoulders, and you likely have a well-defined waist. You consider yourself to have fuller hips, narrower shoulders in comparison to your hips, and a fuller rear. Emphasize your silhouette with the right clothing choices.",
            dos = listOf(
                "V-neck tops",
                "Peplum tops",
                "Off-shoulder tops",
                "A-line skirts",
                "Bootcut jeans",
                "High-waisted pants"
            ),
            donts = listOf(
                "Cargo pants with side pockets",
                "Pencil skirts",
                "Skinny jeans",
                "Cropped tops that end at the hips",
                "Low-rise pants",
                "Tops with shoulder pads"
            )
        )
        "Hourglass" -> BodyTypeInfo(
            imageRes = R.drawable.hourglass_shape,
            description = "Congratulations, you have an hourglass figure! Your shoulders and hips are well-balanced, with a defined waistline. Your bust and hip measurements are roughly even, and you may have a fuller bust, hips, and thighs. Highlight your curves with the right clothing choices.",
            dos = listOf(
                "Wrap tops",
                "Fitted blouses",
                "Crop tops",
                "High-waisted skirts",
                "Skinny jeans",
                "Pencil skirts"
            ),
            donts = listOf(
                "Oversized tops",
                "Boxy dresses",
                "Baggy pants",
                "High-neck tops",
                "Straight-cut skirts",
                "Shapeless dresses"
            )
        )
        "Rectangle" -> BodyTypeInfo(
            imageRes = R.drawable.rectangle_shape,
            description = "You have a rectangular body shape, characterized by a straight silhouette where shoulders and hips are similar in width. You’re not particularly curvy, and your waist is straight up and down. Your weight is fairly evenly distributed throughout your body. Add curves with the right clothing choices.",
            dos = listOf(
                "Ruffled tops",
                "Empire waist tops",
                "Layered tops",
                "Pleated skirts",
                "Wide-leg pants",
                "Cargo pants"
            ),
            donts = listOf(
                "Tight, form-fitting dresses",
                "Straight-leg jeans",
                "Tube tops",
                "Shapeless sweaters",
                "Plain, fitted T-shirts",
                "Pencil skirts"
            )
        )
        "Inverted Triangle" -> BodyTypeInfo(
            imageRes = R.drawable.inverted_triangle_shape,
            description = "You have an inverted triangle shape! Your shoulders are broader than your hips, with a defined waistline. You are generally well-proportioned but not necessarily as curvy through your hips and don’t have a well-defined waistline. Balance your silhouette with the right clothing choices.",
            dos = listOf(
                "Scoop neck tops",
                "Dolman sleeves",
                "Flowy blouses",
                "Wide-leg pants",
                "Pleated skirts",
                "Bootcut jeans"
            ),
            donts = listOf(
                "Tops with shoulder pads",
                "Puffed sleeves",
                "Halter neck tops",
                "Skinny jeans",
                "Pencil skirts",
                "Tops with busy patterns around the shoulders"
            )
        )
        "Circle" -> BodyTypeInfo(
            imageRes = R.drawable.circle_shape,
            description = "You have a circle or apple shape! Your midsection is fuller than your hips and shoulders, with a less defined waistline. Your shoulders and hips are proportional, and you’re curvy through the midsection with a torso that is a wider measurement than shoulders and hips. Flatter your figure with the right clothing choices.",
            dos = listOf(
                "Tunic tops",
                "Empire waist tops",
                "Draped tops",
                "Straight-leg pants",
                "High-rise jeans",
                "A-line skirts"
            ),
            donts = listOf(
                "Crop tops",
                "Tight, fitted shirts",
                "Low-rise pants",
                "Pencil skirts",
                "Tight bodycon dresses",
                "Tops with horizontal stripes"
            )
        )
        else -> BodyTypeInfo(
            imageRes = R.drawable.unknown_shape,
            description = "Your body type is unknown. Please try the quiz again.",
            dos = listOf(),
            donts = listOf()
        )
    }
}
