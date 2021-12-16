package com.example.github.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.github.data.UserProfileApi
import com.example.github.data.UserProfileApi.Companion.BASE_URL
import com.example.github.data.UserProfileRepositoryImpl
import com.example.github.databinding.FragmentInputBinding
import com.example.github.domain.UserProfileUseCaseImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentInputBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentInputBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val repository = UserProfileRepositoryImpl(retrofit.create(UserProfileApi::class.java))
        val usecase = UserProfileUseCaseImpl(repository)
        viewModel = HomeViewModel(usecase)

        viewBinding.buttonStart.setOnClickListener {
            val input = viewBinding.editTextUserInput.text.toString()
            viewModel.onStartClick(input)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}