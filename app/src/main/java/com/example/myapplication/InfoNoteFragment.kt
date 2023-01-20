package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoNoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_note, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoNoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(index: Int) =
            InfoNoteFragment().apply {
                arguments = Bundle().apply {
                    putInt("index", index)

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayData = arrayOf(Note("note1", "Some text1", "01.01.2023"),
            Note("note2", "Some text2", "02.01.2023"),
            Note("note3", "Some text3", "03.01.2023"),)

        if(arguments != null){
            val index:Int = requireArguments().getInt("index")
            val header :TextView = view.findViewById(R.id.header_title)
            header.text = arrayData[index].name
            val date :TextView = view.findViewById(R.id.time)
            date.text = arrayData[index].date
            val text :TextView = view.findViewById(R.id.text_note)
            text.text = arrayData[index].text
        }
    }
}