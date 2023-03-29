package teka.droid.kenyancelebsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import teka.droid.kenyancelebsapplication.ui.theme.KenyanCelebsApplicationTheme

@OptIn(ExperimentalCoilApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KenyanCelebsApplicationTheme {
                Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val celeb = viewModel.state.value.celeb
                    val isLoading = viewModel.state.value.isLoading
                    celeb?.let {
                        Image(painter = rememberImagePainter(
                            data = celeb.imageUrl,
                            builder = {crossfade(true)}
                        ),
                            contentDescription = "Celeb"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = celeb.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = celeb.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = viewModel::getRandomCeleb,
                        modifier = Modifier.align(Alignment.End)
                    ){
                        Text(text = "Next Celeb!")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if(isLoading){
                        CircularProgressIndicator()
                    }
                  

                }
            }
        }
    }
}

