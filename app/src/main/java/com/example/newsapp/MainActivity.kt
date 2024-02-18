package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.viewmodel.HomePageViewModel
import com.example.newsapp.viewmodel.LatestNewsAllViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageTransations()
                }
            }
        }
    }
}
@Composable
fun PageTransations() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "homepage") {
        composable("homepage") { backStackEntry ->
            val viewModel = hiltViewModel<HomePageViewModel>()
            HomePage(navController, viewModel)
        }
        composable("latestnewsall") { backStackEntry ->
            val viewModel = hiltViewModel<LatestNewsAllViewModel>()
            LatestNewsAll(navController,viewModel)
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun HomePage(navController: NavController,viewModel: HomePageViewModel) {
    //val viewModel: HomePageViewModel = viewModel()
    val latestNewsList = viewModel.latestNewsList.observeAsState(listOf())
    val categoryNewsList = viewModel.categoryNewsList.observeAsState(listOf())
    val categories = listOf("health", "technology", "sports", "science", "business", "entertainment")
    var selectedButtonIndex = remember { mutableStateOf(0) }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "NEWS", fontSize = 24.sp, fontWeight = FontWeight.Bold,modifier = Modifier
                .fillMaxWidth(), fontFamily = FontFamily.Serif
            )},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.white),
                    titleContentColor = colorResource(id = R.color.primary)
                ), modifier = Modifier.height(50.dp))
        },
        content = {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp))
            {
                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically)
                {
                    Text(text = "Latest News", fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif)
                    Spacer(modifier = Modifier.size(10.dp))
                    Row (modifier = Modifier.clickable {

                    }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically)
                    {
                        TextButton(onClick = {
                           navController.navigate("latestnewsall")
                        }) {
                            Text(text = "See all", fontSize = 12.sp, color = Color(0xFF0080FF))
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Image(painter = painterResource(id = R.drawable.combined_shape), contentDescription ="" , modifier = Modifier.size(12.dp))
                    }

                }
                LazyRow{
                    items(
                        count = latestNewsList.value!!.count(),
                        itemContent = {
                            val new = latestNewsList.value!![it]
                            Card(modifier = Modifier
                                .size(345.dp, 215.dp)
                                .padding(start = 10.dp)) {
                                Row(modifier = Modifier
                                    .weight(1f)
                                    .clickable {}) {
                                    Box(modifier = Modifier.weight(1f)){
                                        val url = new.urlToImage
                                        GlideImage(model = url, contentDescription = "", modifier = Modifier.fillParentMaxSize())
                                        Column (modifier = Modifier
                                            .align(Alignment.Center)
                                            .padding(8.dp)){
                                            Text(text = "by ${new.author}", fontSize = 10.sp, color = Color.White)
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(text = new.title,
                                                fontSize = 16.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                )
                                        }

                                    }
                                }
                            }
                        }
                    )
                }
                LazyRow{
                    items(
                        count = 6,
                        itemContent = {
                            val ctgry = categories[it]
                            Card(modifier = Modifier
                                .padding(start = 10.dp, top = 20.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                )
                            ) {
                                ElevatedButton(onClick = {
                                    viewModel.category.value = ctgry
                                    viewModel.homePageCategoryNews()
                                    selectedButtonIndex.value = it
                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButtonIndex.value == it) colorResource(
                                        id = R.color.primary
                                    ) else Color.White,
                                )) {
                                    Text(text = ctgry, fontSize = 12.sp, color = if (selectedButtonIndex.value == it) Color.White else Color.Black,)
                                }
                            }
                        }
                    )
                }
                LazyColumn{
                    items(
                        count = categoryNewsList.value!!.count(),
                        itemContent = {
                            val new = categoryNewsList.value!![it]
                            Card(modifier = Modifier
                                .height(150.dp)
                                .padding(all = 10.dp)) {
                                Row(modifier = Modifier
                                    .weight(1f)
                                    .clickable {}) {
                                    Box(modifier = Modifier.weight(1f)){
                                        val url = new.urlToImage
                                        GlideImage(model = url, contentDescription = "", modifier = Modifier.fillParentMaxSize())
                                        Column (modifier = Modifier.padding(all = 8.dp)) {
                                            Text(text = new.title, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.Bold,)
                                            Spacer(modifier = Modifier.weight(1f))
                                            Row {
                                                Text(text = new.author?: "remove title", fontSize = 12.sp, color = Color.White)
                                                Spacer(modifier = Modifier.weight(1f))
                                                Text(text = new.publishedAt.dropLast(10)?: "remove title", fontSize = 12.sp, color = Color.White)
                                            }

                                        }

                                    }
                                }
                            }
                        }
                    )
                }



            }

        }
    )



}
