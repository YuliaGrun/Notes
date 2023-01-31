package com.example.myapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {
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

        return inflater.inflate(R.layout.fragment_note, container, false)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val objMAin = MainActivity();
        val arrayData = objMAin.getArrayNotes()

        val linearLayout = view as LinearLayout
        for ( i in 0 .. arrayData.size-1){
          /*  val textView = TextView(context)
            textView.text = arrayData[i].name
            textView.textSize = 18.0F;*/

            val textViewHeader = LayoutInflater.from(context).inflate(R.layout.note_item_layout, null) as LinearLayout

            textViewHeader.findViewById<TextView>(R.id.idHeader).text = arrayData[i].name
            textViewHeader.findViewById<TextView>(R.id.dateNote).text = arrayData[i].date

            linearLayout.addView(textViewHeader)
            textViewHeader.setOnClickListener {
                showInfoOrient(i)
            }
            textViewHeader.setOnLongClickListener {
                AlertDialog.Builder(context)
                    .setTitle(R.string.title_del)
                    .setMessage(R.string.del_quest)
                    .setPositiveButton(R.string.but_yes) { _, _ ->
                        Toast.makeText(context, R.string.but_yes, Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton(R.string.but_no) { _, _ ->
                        Toast.makeText( context, R.string.but_no, Toast.LENGTH_SHORT).show()
                    }
                    .setNeutralButton(R.string.but_cancel, null)
                    .show()
                true
            }
        }
    }
    private fun showInfoOrient(position :Int){
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showLandInfo(position)
        }
        else showPortInfo(position)



    }
    private fun showPortInfo(position :Int){
        val infoNote = InfoNoteFragment.newInstance(position)
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, infoNote)
        fragmentTransaction.addToBackStack("")
        fragmentTransaction.commit()
    }
    private fun showLandInfo(position :Int){
        val infoNote = InfoNoteFragment.newInstance(position)
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_infocontainer, infoNote)
        fragmentTransaction.commit()
    }

}