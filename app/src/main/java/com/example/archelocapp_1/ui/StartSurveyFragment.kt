package com.example.archelocapp_1.ui

import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.archelocapp_1.databinding.ActivityStartSurveyBinding
import com.example.archelocapp_1.viewmodels.StartSurveyViewModel
import kotlinx.android.synthetic.main.activity_arrow__btn.view.*
import java.text.SimpleDateFormat
import java.util.*

//import com.example.archelocapp_1.viewmodels.SuspectedNestViewModel

class StartSurveyFragment : Fragment() {

    private val TAG = "StartSurveyFragment"
    private lateinit var binding: ActivityStartSurveyBinding
    private val startSurveyViewModel: StartSurveyViewModel by viewModels()
    //the below two lines not implemented
    //private lateinit var viewModelFactory: StartSurveyViewModelFactory

    //private val suspectedNestViewModel: SuspectedNestViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityStartSurveyBinding.inflate(inflater, container, false)
        return binding.root

        //load Room Database in viewModel for persistence
        context?.let { startSurveyViewModel.loadDb(it) }

        //load fused location provider in viewModel for persistence
        context?.let { startSurveyViewModel.loadLocationProvider(it) }
        return binding.root

    }


    //@RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)

        //listeners()

    }

    @SuppressLint("MissingPermission")
    private fun init(view: View) {
        //if user clicks the submit button it will Collect the data from morning survey form
        binding.submit.setOnClickListener {

            with(startSurveyViewModel) {


                startSurveyViewModel.morningSurveyModel.area = binding.AreaText.text.toString()
                //Setup Calendar to coolect date selected from date Picker

                val day: Int = binding.datePicker.getDayOfMonth()
                val month: Int = binding.datePicker.getMonth()
                val year: Int = binding.datePicker.getYear()
                val calendar: Calendar = Calendar.getInstance()
                calendar.set(year, month, day)
                val sdf = SimpleDateFormat("dd-MM-yyyy")
                val formatedDate: String = sdf.format(calendar.getTime())
                startSurveyViewModel.morningSurveyModel.date = formatedDate
                startSurveyViewModel.morningSurveyModel.beach = binding.BeachText.text.toString()
                startSurveyViewModel.morningSurveyModel.sector = binding.SectorText.text.toString()
                startSurveyViewModel.morningSurveyModel.subsector =
                    binding.SubSectorText.text.toString()
                startSurveyViewModel.morningSurveyModel.emergence_event =
                    binding.EmergenceEventText.text.toString()
                var nest: String = binding.NestText.text.toString()
                startSurveyViewModel.morningSurveyModel.nest = nest
                binding.DistancetoSeaText.toString()
                startSurveyViewModel.morningSurveyModel.distance_to_sea =
                    binding.DistancetoSeaText.toString()
                startSurveyViewModel.morningSurveyModel.track_type =
                    binding.TrackTypeText.text.toString()
                startSurveyViewModel.morningSurveyModel.non_nesting_attempts =
                    binding.NonNestingAttemptsText.text.toString()
                startSurveyViewModel.morningSurveyModel.tags = binding.tagsText.text.toString()
                startSurveyViewModel.morningSurveyModel.comments =
                    binding.CommentsText.text.toString()
                startSurveyViewModel.morningSurveyModel.photo_id =
                    binding.photoIDText.text.toString()

                // Get location from fusded location provider for survey location

                startSurveyViewModel.fusedLocationClient?.lastLocation
                    ?.addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            startSurveyViewModel.morningSurveyModel.gps_latitude =
                                location.latitude.toString()
                            startSurveyViewModel.morningSurveyModel.gps_longitude =
                                location.longitude.toString()
                        }
                    }

                // Add form data collected to database
                startSurveyViewModel.insert(morningSurveyModel)
            }

        }// when the user click the previous button it should take to the previous page
        binding.previousButton.setOnClickListener {
            with(startSurveyViewModel) {
                findNavController().popBackStack()
            }

        }

        // when the user click the next button it should take to the next page
        binding.nextButton.setOnClickListener {
            with(startSurveyViewModel) {
                findNavController().navigate(StartSurveyFragmentDirections.actionStartSurveyFragmentToObserverFragment())
            }

        }


//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun listeners() {
//        binding.previousButton.setOnClickListener {
//            findNavController().popBackStack()
//        }
//
//        binding.nextButton.setOnClickListener {
//            findNavController().navigate(StartSurveyFragmentDirections.actionStartSurveyFragmentToObserverFragment())
//        }
        //On clicking the plus button the data to be submitted in the add morning survey method of the
        //suspectedNestViewModelclass
//        binding.plusButton.setOnClickListener {
//            // to bind the data of the calender
//            val cal = binding.calendarView2.date.toString()
//            //to bind the time
//            val time = binding.timePicker1.hour.toString() +" : "+ binding.timePicker1.minute.toString()
//
//            val spinnerdata1 = binding.spinner.selectedItem.toString()
//            val spinnerdata2 = binding.spinner2.selectedItem.toString()
//            suspectedNestViewModel.addMorningSurvey(cal, time, spinnerdata1, spinnerdata2)
//            //the timer mode
//
//        }
        // }

        //private fun init(view: View) {
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.spinnerItems,
//            android.R.layout.simple_spinner_item)
//            .also { adapter ->
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                binding.spinner.adapter = adapter
//            }
//
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.spinnerItems1,
//            android.R.layout.simple_spinner_item)
//            .also { adapter ->
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                binding.spinner2.adapter = adapter
//            }
        // }


    }
}