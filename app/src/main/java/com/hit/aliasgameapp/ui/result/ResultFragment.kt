package com.hit.aliasgameapp.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R
import com.hit.aliasgameapp.databinding.FragmentResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class ScoreData(val teamName: String, val score: Int)
data class ScoreResponse(val id: String, val createdAt: String)

interface ScoreApiService {
    @POST("api/users")
    fun uploadScore(@Body score: ScoreData): Call<ScoreResponse>
}

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val winnerName = arguments?.getString("winnerName") ?: "Unknown Team"
        val score = arguments?.getInt("score") ?: 0

        binding.textViewWinnerName.text = winnerName

        binding.buttonHome.setOnClickListener {
            findNavController().navigate(R.id.action_gameBoardFragment_to_mainListFragment)
        }

        binding.buttonUploadScore.setOnClickListener {
            uploadScoreToServer(winnerName, score)
        }
    }

    private fun uploadScoreToServer(name: String, score: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ScoreApiService::class.java)
        val request = ScoreData(name, score)

        Toast.makeText(context, "Sending score...", Toast.LENGTH_SHORT).show()

        api.uploadScore(request).enqueue(object : Callback<ScoreResponse> {
            override fun onResponse(call: Call<ScoreResponse>, response: Response<ScoreResponse>) {
                if (response.isSuccessful) {
                    val id = response.body()?.id
                    Toast.makeText(context, "Success! Server ID: $id", Toast.LENGTH_LONG).show()
                    binding.buttonUploadScore.isEnabled = false
                    binding.buttonUploadScore.text = "Shared Successfully"
                } else {
                    Toast.makeText(context, "Upload Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ScoreResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}