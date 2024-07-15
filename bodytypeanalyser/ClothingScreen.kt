package com.example.bodytypeanalyser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun ClothingScreen(bodytype: String) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header with Myntra logo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // Thinner header
                .background(Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Myntra logo
            Image(
                painter = painterResource(id = R.drawable.myntra_logo),
                contentDescription = "Myntra Logo",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
            )
        }

        // Tabs for tops, bottoms, and dresses
        val tabs = listOf("Tops", "Bottoms", "Dresses")

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xFFFFC0CB), // Light pink color
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                ) {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) Color.Magenta else Color.White, // Changed to white
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        // Content based on the selected tab
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))) {
            when (selectedTabIndex) {
                0 -> {
                    DisplayItems(getImageList(bodytype, "Tops"))
                }
                1 -> {
                    DisplayItems(getImageList(bodytype, "Bottoms"))
                }
                2 -> {
                    DisplayItems(getImageList(bodytype, "Dresses"))
                }
            }
        }
    }
}

@Composable
fun DisplayItems(items: List<Int>) {
    val itemPrices = remember { mutableStateMapOf<Int, Int>() }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        itemsIndexed(items.chunked(2)) { _, rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowItems.forEach { item ->
                    val price = itemPrices.getOrPut(item) { Random.nextInt(500, 1200) } // Store consistent price for each item
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .height(200.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { /* Do nothing */ }
                        ) {
                            Image(
                                painter = painterResource(id = item),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(0.dp)
                            )
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "₹$price",
                                    textAlign = TextAlign.Left,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f).padding(8.dp))
                }
            }
        }
    }
}

fun getImageList(bodytype: String, category: String): List<Int> {
    return when (bodytype) {
        "Pear" -> when (category) {
            "Tops" -> listOf(R.drawable.pt1, R.drawable.pt2, R.drawable.pt3, R.drawable.pt4, R.drawable.pt5, R.drawable.pt6)
            "Bottoms" -> listOf(R.drawable.pb1, R.drawable.pb2, R.drawable.pb3, R.drawable.pb4, R.drawable.pb5, R.drawable.pb6)
            "Dresses" -> listOf(R.drawable.pd1, R.drawable.pd2, R.drawable.pd3, R.drawable.pd4, R.drawable.pd5, R.drawable.pd6)
            else -> emptyList()
        }
        "Hourglass" -> when (category) {
            "Tops" -> listOf(R.drawable.ht1, R.drawable.ht2, R.drawable.ht3, R.drawable.ht4, R.drawable.ht5, R.drawable.ht6)
            "Bottoms" -> listOf(R.drawable.hb1, R.drawable.hb2, R.drawable.hb3, R.drawable.hb4, R.drawable.hb5, R.drawable.hb6)
            "Dresses" -> listOf(R.drawable.hd1, R.drawable.hd2, R.drawable.hd3, R.drawable.hd4, R.drawable.hd5, R.drawable.hd6)
            else -> emptyList()
        }
        "Rectangle" -> when (category) {
            "Tops" -> listOf(R.drawable.rt1, R.drawable.rt2, R.drawable.rt3, R.drawable.rt4, R.drawable.rt5, R.drawable.rt6)
            "Bottoms" -> listOf(R.drawable.rb1, R.drawable.rb2, R.drawable.rb3, R.drawable.rb4, R.drawable.rb5, R.drawable.rb6)
            "Dresses" -> listOf(R.drawable.rd1, R.drawable.rd2, R.drawable.rd3, R.drawable.rd4, R.drawable.rd5, R.drawable.rd6)
            else -> emptyList()
        }
        "Inverted Triangle" -> when (category) {
            "Tops" -> listOf(R.drawable.it1, R.drawable.it2, R.drawable.it3, R.drawable.it4, R.drawable.it5, R.drawable.it6)
            "Bottoms" -> listOf(R.drawable.ib1, R.drawable.ib2, R.drawable.ib3, R.drawable.ib4, R.drawable.ib5, R.drawable.ib6)
            "Dresses" -> listOf(R.drawable.id1, R.drawable.id2, R.drawable.id3, R.drawable.id4, R.drawable.id5, R.drawable.id6)
            else -> emptyList()
        }
        "Circle" -> when (category) {
            "Tops" -> listOf(R.drawable.ct1, R.drawable.ct2, R.drawable.ct3, R.drawable.ct4, R.drawable.ct5, R.drawable.ct6)
            "Bottoms" -> listOf(R.drawable.cb1, R.drawable.cb2, R.drawable.cb3, R.drawable.cb4, R.drawable.cb5, R.drawable.cb6)
            "Dresses" -> listOf(R.drawable.cd1, R.drawable.cd2, R.drawable.cd3, R.drawable.cd4, R.drawable.cd5, R.drawable.cd6)
            else -> emptyList()
        }
        else -> emptyList()
    }
}
