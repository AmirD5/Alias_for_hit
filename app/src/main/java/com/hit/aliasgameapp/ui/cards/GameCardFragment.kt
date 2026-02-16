package com.hit.aliasgameapp.ui.cards

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.databinding.FragmentGameCardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.content.Context

@AndroidEntryPoint
class GameCardFragment : Fragment(R.layout.fragment_game_card), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0

    // Connect to the new ViewModel
    private val viewModel: GameViewModel by viewModels()

    private var _binding: FragmentGameCardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGameCardBinding.bind(view)

        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // 1. Observe all data updates
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                // A. Words List
                launch {
                    viewModel.words.collect { wordList ->
                        if (wordList.size >= 8) {
                            binding.word1.text = wordList[0]
                            binding.word2.text = wordList[1]
                            binding.word3.text = wordList[2]
                            binding.word4.text = wordList[3]
                            binding.word5.text = wordList[4]
                            binding.word6.text = wordList[5]
                            binding.word7.text = wordList[6]
                            binding.word8.text = wordList[7]
                        }
                    }
                }

                // B. Timer
                launch {
                    viewModel.timerText.collect { timeString ->
                        binding.txtTimer.setText(timeString)
                    }
                }

                // C. Score
                launch {
                    viewModel.score.collect { currentScore ->
                        binding.scoretext.text = currentScore.toString()
                    }
                }

                // D. Game Over Logic
                launch {
                    viewModel.isGameOver.collect { isOver ->
                        if (isOver) {
                            Toast.makeText(context,
                                getString(R.string.round_is_over), Toast.LENGTH_SHORT).show()
                            val finalScore = viewModel.score.value
                            val bundle = Bundle().apply {
                                putInt("roundScore", finalScore)
                            }
                            parentFragmentManager.setFragmentResult("roundRequest", bundle)
                            // Wait 2 seconds and go back
                            binding.root.postDelayed({
                                findNavController().popBackStack()
                            }, 500)
                        }
                    }
                }
            }
        }

        // 2. Button Clicks (Calling the ViewModel functions)
        binding.correctbut.setOnClickListener {
            viewModel.onCorrect()
        }

        binding.incorrectbut.setOnClickListener {
            viewModel.onIncorrect()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = kotlin.math.sqrt((x * x + y * y + z * z).toDouble())
            val currentShakeTime = System.currentTimeMillis()

            if (acceleration > 20 && (currentShakeTime - lastShakeTime > 2000)) {
                lastShakeTime = currentShakeTime

                viewModel.onIncorrect()
                Toast.makeText(context,
                    getString(R.string.shake_detected_word_skipped), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}