package com.example.squash.game

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.squash.R
import com.example.squash.databinding.FragmentGameBinding
import com.example.squash.datasource.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Game : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var getData: String
    private lateinit var timer: CountDownTimer

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false)
        navController = NavHostFragment.findNavController(this)

        getArgument()

        val args = this.arguments
        getData = args?.getString("cat").toString()
        val getTimer = args?.getString("time").toString()

        if (getData == "Medicine") {
            viewModel.getNextWord(medicine)
        } else if (getData == "Sport") {
            viewModel.getNextWord(sports)
        }

//        timer = object : CountDownTimer(0, 0) {
//            override fun onTick(p0: Long) {
//                binding.timer.text = p0.toString()
//            }
//
//            override fun onFinish() {
//                Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()
//            }
//
//        }

        when (getTimer) {
            "1 minute" -> aMinuteTimer()
            "2 minutes" -> twoMinutesTimer()
            "3 minutes" -> threeMinutesTimer()
            "4 minutes" -> fourMinuteTimer()
            "5 minutes" -> fiveMinuteTimer()
            "Set timer" ->{

            }

            else ->{
                twoMinutesTimer()
            }
        }



        return binding.root
    }

    private fun aMinuteTimer() {
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(p0: Long) {
                val seconds = p0 / 1000
                binding.timer.text = seconds.toString()
            }

            override fun onFinish() {
                finishedDialog()
            }

        }
    }

    private fun twoMinutesTimer() {
        timer = object : CountDownTimer(120000, 1000) {
            override fun onTick(p0: Long) {
                val seconds = p0 / 1000
                binding.timer.text = seconds.toString()
            }

            override fun onFinish() {
                finishedDialog()
            }

        }
    }

    private fun threeMinutesTimer() {
        timer = object : CountDownTimer(180000, 1000) {
            override fun onTick(p0: Long) {
                val seconds = p0 / 1000
                binding.timer.text = seconds.toString()
            }

            override fun onFinish() {
                finishedDialog()
            }

        }
    }

    private fun fourMinuteTimer() {
        timer = object : CountDownTimer(240000, 1000) {
            override fun onTick(p0: Long) {
                val seconds = p0 / 1000
                binding.timer.text = seconds.toString()
            }

            override fun onFinish() {
                finishedDialog()
            }

        }
    }

    private fun fiveMinuteTimer() {
        timer = object : CountDownTimer(300000, 1000) {
            override fun onTick(p0: Long) {
                val seconds = p0 / 1000
                binding.timer.text = seconds.toString()
            }

            override fun onFinish() {
                finishedDialog()
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.currentWordCount.observe(viewLifecycleOwner) { newWordCount ->
            binding.wordCount.text = getString(R.string.wordcount, newWordCount, MAX_NO_WORDS)
        }

        viewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.score.text = getString(R.string.score, newScore)
        }

        binding.quit.setOnClickListener {

            showDialog()
        }

        binding.hint.setOnClickListener {
          helpDialog()
        }
        viewModel.currentScrambledWord.observe(viewLifecycleOwner) { newWord ->
            binding.word.text = newWord
        }

        binding.submit.setOnClickListener {
            binding.textMeaning.visibility = View.GONE
            submitWord()
        }

        binding.skip.setOnClickListener {
            binding.textMeaning.visibility = View.GONE
            skip()
        }
    }


    private fun submitWord() {
        if (viewModel.isWordCorrect(binding.userWord.text.toString())) {
            setErrorTextField(false)
            binding.userWord.text?.clear()
            if (viewModel.nextWord()) {

            } else {
                finishedDialog()
            }
        } else {
            setErrorTextField(true)
        }
    }

    private fun getArgument(){
        val args = this.arguments
        val getData = args?.get("name")
        when(getData){
            "Medicine" -> {
                viewModel.getNextWord(medicine)

            }
            "Sport" -> {
                viewModel.getNextWord(sports)

            }
            "Random" -> {
                viewModel.getNextWord(allWordsList)

            }
            "Countries" -> {
                viewModel.getNextWord(countries)
            }

            "Finance" -> {
                viewModel.getNextWord(finance)
            }
        }
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Are you sure?")
            .setMessage("Your Current Score ${viewModel.score.value}")
            .setCancelable(true)
            .setNegativeButton("Exit") { _, _ -> exitGame() }
            .setPositiveButton("Restart Game") { _, _ -> restartGame() }
            .show()
    }



    private fun helpDialog(){
        val v = View.inflate(requireContext(), R.layout.help_modal, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(v)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val close = v.findViewById<AppCompatButton>(R.id.close)
        val text = v.findViewById<TextView>(R.id.text1)
        text.text =  getString(R.string.hint, viewModel.hint().toCharArray()[0].toString().toUpperCase())

        close.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun finishedDialog(){
        val v = View.inflate(requireContext(), R.layout.finished_game_modal, null)

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(v)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        val playAgain = v.findViewById<MaterialButton>(R.id.play_again)
        val score = v.findViewById<TextView>(R.id.You)
        val exit = v.findViewById<AppCompatButton>(R.id.exitGame)

        score.text = "You scored ${viewModel.score.value}"
        playAgain.setOnClickListener {
            view?.let { it1 ->
                restartGame()
            }
            dialog.dismiss()
        }

        exit.setOnClickListener {
            view?.let { it1 ->
                exitGame()
            }
            dialog.dismiss()
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun restartGame() {
        viewModel.reinitializeData()
        setErrorTextField(false)
        timer.start()
    }

    private fun skip() {
        if (viewModel.nextWord()) {
            setErrorTextField(false)
        } else {
            finishedDialog()
        }
    }

    private fun exitGame() {
        navController.navigate(R.id.action_game_to_homeFragment)
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.layout.isErrorEnabled = true
            binding.layout.error = "Try Again"
        } else {
            binding.layout.isErrorEnabled = false
            binding.layout.error = null
        }
    }


}