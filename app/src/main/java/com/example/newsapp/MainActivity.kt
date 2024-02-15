package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.viewmodel.HomePageViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun HomePage() {
    val viewModel: HomePageViewModel = viewModel()
    val latestNewsList = viewModel.latestNewsList.observeAsState(listOf())


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "NEWS", fontSize = 24.sp, fontWeight = FontWeight.Bold,modifier = Modifier
                .fillMaxWidth(), fontFamily = FontFamily.Serif
            )},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.white),
                    titleContentColor = colorResource(id = R.color.black)
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
                        Text(text = "See all", fontSize = 12.sp, color = Color(0xFF0080FF))
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
                                .size(345.dp, 215.dp).padding(start = 10.dp)) {
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
                                            Text(text = new.title, fontSize = 16.sp, color = Color.White,fontWeight = FontWeight.Bold)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppTheme {
        HomePage()
    }
}