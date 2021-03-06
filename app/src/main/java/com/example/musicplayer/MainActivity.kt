package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
lateinit var binding: ActivityMainBinding
lateinit var runnable: Runnable
private var handler = Handler()
    var music_number=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_main)



        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var mediaPlayer: MediaPlayer =MediaPlayer.create(this,R.raw.music1)
        if(music_number==1)
            binding.prev.visibility=View.INVISIBLE

        binding.seekBar.progress=0
        binding.seekBar.max=mediaPlayer.duration
         binding.playstop.setOnClickListener{

             if(!mediaPlayer.isPlaying) {
                 mediaPlayer.start()
                 binding.playstop.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
             }else{
                 mediaPlayer.pause()
                 binding.playstop.setImageResource(R.drawable.ic_baseline_play_arrow_24)

             }

         }

        binding.next.setOnClickListener {

            binding.prev.visibility=View.VISIBLE
            music_number++
            mediaPlayer.stop()
            binding.playstop.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        when(music_number) {

            1 -> mediaPlayer = MediaPlayer.create(this, R.raw.music1)
            2 -> mediaPlayer = MediaPlayer.create(this, R.raw.music2)
            3->  mediaPlayer = MediaPlayer.create(this, R.raw.music3)
        }
            binding.seekBar.progress=0
            binding.seekBar.max=mediaPlayer.duration

            if(music_number==3)
                binding.next.visibility=View.INVISIBLE


        }

        binding.prev.setOnClickListener {
            binding.next.visibility=View.VISIBLE
            music_number--
            mediaPlayer.stop()
            binding.playstop.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            when(music_number) {

                1 -> mediaPlayer = MediaPlayer.create(this, R.raw.music1)
                2 -> mediaPlayer = MediaPlayer.create(this, R.raw.music2)
                3->  mediaPlayer = MediaPlayer.create(this, R.raw.music3)

            }
            binding.seekBar.progress=0
            binding.seekBar.max=mediaPlayer.duration

            if(music_number==1)
                binding.prev.visibility=View.INVISIBLE



        }

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {

                if(changed)
                    mediaPlayer.seekTo(pos)


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }


        })

        runnable= Runnable {

            binding.seekBar.progress=mediaPlayer.currentPosition
            handler.postDelayed(runnable,1000)

        }
        handler.postDelayed(runnable,1000)

mediaPlayer.setOnCompletionListener {

    if(music_number==1) {
        mediaPlayer.stop()
        binding.seekBar.progress=0
        binding.seekBar.max=mediaPlayer.duration
        mediaPlayer = MediaPlayer.create(this, R.raw.music2)
        binding.next.visibility = View.INVISIBLE
        binding.playstop.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        music_number++
    }


}


    }
}