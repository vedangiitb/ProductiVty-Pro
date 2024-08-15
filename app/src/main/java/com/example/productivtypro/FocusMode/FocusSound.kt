package com.example.productivtypro.FocusMode

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.productivtypro.R

@Composable
fun FocusSoundItem(musicId:Int,name:String){
    val mContext = LocalContext.current
    val mMediaPlayer = MediaPlayer.create(mContext, musicId)
    mMediaPlayer.setLooping(true)

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {mMediaPlayer.start()}, modifier = Modifier.weight(0.5f)) {
            Icon(painter = painterResource(id = R.drawable.play_circle), contentDescription = "Start")
        }
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(onClick = {mMediaPlayer.pause()}, modifier = Modifier.weight(0.5f)) {
            Icon(painter = painterResource(id = R.drawable.stop_circle), contentDescription = "Close")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(name, modifier = Modifier.weight(4f))
    }
}