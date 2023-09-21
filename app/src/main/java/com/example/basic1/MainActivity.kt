package com.example.basic1


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.example.basic1.screens.QuoteDetailScreen
import com.example.basic1.screens.QuoteListScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssetsFromFile(applicationContext)
        }
        setContent {
            App()
        }
    }
}

@Composable
fun  App(){
    if(DataManager.isDataLoaded.value){
        if (DataManager.currentPage.value==Pages.LISTING){
        QuoteListScreen(data = DataManager.data){
            DataManager.switchPages(it)
        }
        }
        else{
            DataManager.currentQuote?.let { QuoteDetailScreen(quote = it) }
        }
    }



}
enum class Pages{
    LISTING,
    DETAIL
}