package es.rafapuig.pmdm.compose.exercises.screens.exercise1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import es.rafapuig.pmdm.compose.exercises.R

object AppColor {
    val Purple = Color(0xFF521C98)
    val LightPurple = Color(0xFFF0E9FA)
    val BgGray = Color(0xFFF8F8F8)
    val TextGray = Color(0xFF5E5E5E)
    val OverlayBlack = Color(0x90000000)
    val GoldYellow = Color(0xFFFFC107)
}

data class CourseItem(
    val title: String,
    val name: String,
    val price: Int,
    val score: Double,
    val picUrl: Int
)

data class CategoryItem(val title: String, val icon: Int)
data class BottomMenuItem(val label: String, val icon: Int)

@Preview(showSystemUi = true)
@Composable
fun MyUI() {
    Scaffold(
        bottomBar = { MyBottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SearchRow()
            Banner()
            Categories()
            PopularCoursesHeader()
            ItemList()

            // Espacio extra para el scroll
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

}

@Preview
@Composable
fun ItemList() {
    val courses = listOf(
        CourseItem(
            "Diseño de UI", name = "Sofia", price = 45, score = 4.5,
            picUrl = R.drawable.ratatouille
        ),
        CourseItem(
            title = "Marketing Digital",
            name = "Juan",
            price = 50,
            score = 4.9,
            picUrl = R.drawable.ratatouille
        ),
        CourseItem(
            title = "Programación Avanzada",
            name = "María",
            price = 60,
            score = 6.8,
            picUrl = R.drawable.ratatouille
        )
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(courses) { course ->
            CourseItemCard(course)
        }
    }
}

@Composable
fun CourseItemCard(course: CourseItem) {
    // Como una card
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .shadow(3.dp, RoundedCornerShape(10.dp))
            .background(Color.White, RoundedCornerShape(10.dp))
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (
                image,
                title,
                profile,
                name,
                price,
                scoreRow
            ) = createRefs()

            Image(
                painter = painterResource(id = course.picUrl),
                contentDescription = course.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = course.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.OverlayBlack)
                    .padding(8.dp)
                    // Overlay el título en la imagen
                    .constrainAs(title) {
                        bottom.linkTo(image.bottom)
                    }
            )
            Image(
                painter = painterResource(R.drawable.hapy_boy_with_books),
                contentDescription = course.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .
                constrainAs(profile){
                    top.linkTo(image.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
            )

            Text(
                text = course.name,
                color = AppColor.TextGray,
                fontSize = 12.sp,
                modifier = Modifier
                    .constrainAs(name) {
                        top.linkTo(profile.top)
                        start.linkTo(profile.end, margin = 8.dp)
                        bottom.linkTo(profile.bottom)
                    }
            )

            // Precio
            Text(
                text = "${course.price}€",
                color = AppColor.Purple,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .constrainAs(price) {
                        top.linkTo(profile.bottom, margin = 4.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                        bottom.linkTo(parent.bottom, margin = 8.dp)
                    }
            )

            //Score : Bottom-end
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .constrainAs(scoreRow) {
                        bottom.linkTo(parent.bottom, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
            ) {
                Text(
                    text = course.score.toString(),
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    tint = AppColor.GoldYellow,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PopularCoursesHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Cursos populares",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Ver todos",
            fontSize = 14.sp,
            color = AppColor.Purple
        )
    }
}

@Preview
@Composable
fun Categories() {
    val categories = listOf(
        CategoryItem("Marketing", R.drawable.megafono),
        CategoryItem("Diseño", R.drawable.tablet),
        CategoryItem("Juegos", R.drawable.games),
        CategoryItem("Programación", R.drawable.coding)
    )
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categorías",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                //color = Color.Black
            )
            Text(
                text = "Ver todas",
                fontSize = 14.sp,
                color = AppColor.Purple
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            categories.forEach { category ->
                Column(
                    modifier = Modifier.weight(.25f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                AppColor.LightPurple,
                                RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = category.icon),
                            contentDescription = category.title,
                            modifier = Modifier.size(30.dp),
                            tint = AppColor.Purple
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = category.title,
                        fontSize = 12.sp,
                        color = AppColor.TextGray
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun Banner() {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(160.dp)
            .background(
                AppColor.Purple,
                RoundedCornerShape(10.dp)
            )
    ) {
        /**
         * Crear las referencias para cada composable dentro del ConstraintLayout
         */
        val (image, title, button) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.hapy_boy_with_books),
            contentDescription = "Banner Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = "Programa de certificación\navanzada para ti",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                contentColor = AppColor.Purple,
                containerColor = AppColor.LightPurple
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .constrainAs(button) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                }
        ) {
            Text("Comprar ahora")
        }

    }
}

@Composable
fun SearchRow() {
}

@Preview
@Composable
fun MyBottomBar() {
    val bottomMenuItems = listOf(
        BottomMenuItem("Explorar", R.drawable.coding),
        BottomMenuItem("Lista deseos", R.drawable.megafono),
        BottomMenuItem("Mi curso", R.drawable.tablet),
        BottomMenuItem("Cuenta", R.drawable.games)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    NavigationBar {
        bottomMenuItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.label, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColor.Purple,
                    selectedTextColor = AppColor.Purple,
                    unselectedIconColor = AppColor.TextGray,
                    unselectedTextColor = AppColor.TextGray
                )
            )
        }
    }

}