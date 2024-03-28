package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable

fun ArtSpace(){
    var page by remember { mutableStateOf(1) }

    when(page){
        1->{
            Art(
                image = R.drawable.adrianna_geo_1rbg5ysi00c_unsplash,
                title = R.string.art1tittle,
                artist = R.string.art1artist,
                ArtYear = R.string.art1year,
                modifier = Modifier,
                current = 1,
                total = 3,
                Nextfunc = {page=2},
                Prevfunc = {}

            )


        } 2->{
            Art(
                image = R.drawable.europeana_5tk1f5vfdik_unsplash,
                title = R.string.art2tittle,
                artist = R.string.art2artist,
                ArtYear = R.string.art2year,
                current = 2,
                total = 3,
                modifier = Modifier,
                Nextfunc = {page=3},
                Prevfunc = {page = 1}
            )
        } 3->{
            Art(
                image = R.drawable.maria_orlova_bu8texhspcy_unsplash,
                title = R.string.art3tittle,
                artist = R.string.art3artist,
                ArtYear = R.string.art3year,
                current = 3,
                total=3,
                Nextfunc = {},
                Prevfunc = {page = 2},
                modifier = Modifier
            )
        }
    }

}


@Composable
fun Controller(
    modifier: Modifier= Modifier,
    currentpage:Int,
    totalPage:Int,
    nextFn :()->Unit,
    PrevfN:()->Unit

){

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if(currentpage >1){
            Button(onClick = PrevfN) {
                Text("Previous")
            }

        }

        Spacer(modifier = Modifier.width(16.dp))
        if(currentpage <= totalPage){
            Button(onClick = nextFn) {
                Text("Next")
            }
        }


    }



}



@Composable
fun Art(image: Int, modifier: Modifier = Modifier,title : Int , artist:Int,ArtYear:Int,current:Int,total:Int,Prevfunc:()->Unit,Nextfunc:()->Unit) {


    Surface(
        modifier=Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {

            ArtImage(image = image)

            Spacer(modifier = Modifier.height(60.dp))

            ArtText(title=title,ArtYear=ArtYear, artist = artist)

            Controller(currentpage = current, totalPage = total, nextFn = Nextfunc , PrevfN = Prevfunc)
        }

    }

}

@Composable
fun ArtImage(image: Int, modifier: Modifier = Modifier){
    Box (
        modifier= Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 26.dp)
            .background(Color(201, 201, 201))

    ){
        Image(
            painter = painterResource(image) ,
            contentDescription = null,
            modifier=Modifier
                .padding(38.dp))
    }
}


@Composable
fun ArtText(title : Int , artist:Int,ArtYear:Int,modifier: Modifier=Modifier){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(236, 235, 244))

    ) {
        Column(
            modifier=Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 18.dp)

        ) {
            Text(text = stringResource(title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal)
            Row {
                Text(text = stringResource(artist),
                    fontWeight = FontWeight.Bold)
                Text(" (${stringResource(ArtYear)})")
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
       ArtSpace()
    }
}