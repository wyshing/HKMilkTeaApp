package shape.edu.hkmilkteaapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class NoteFragment : Fragment() {

    private lateinit var editTextNote : EditText
    private lateinit var buttonAddNote : Button
    private lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_note, container, false)

        editTextNote = view.findViewById(R.id.editTextNote)
        buttonAddNote = view.findViewById(R.id.buttonAddNote)
        noteDao = NoteDao()

        buttonAddNote.setOnClickListener {

            val note = editTextNote.text.toString()

            if(note.isNotEmpty()) {

                hideKeyboard()

                // using Note data class and NoteDao to add note to Firebase
                noteDao.addNote(note)

                // navigate back to Notes Fragment
                val transaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.frameLayout, NotesFragment())
                transaction.commit()

            } else {
                // warn user to input something before submitting the note
                Toast.makeText(requireContext(), "Please type something before you submit", Toast.LENGTH_SHORT).show()
            }
        }

        val buttonCancelNote = view.findViewById<Button>(R.id.buttonCancelNote)
        buttonCancelNote.setOnClickListener {
            hideKeyboard()

            // navigate back to Notes Fragment
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frameLayout, NotesFragment())
            transaction.commit()

        }

        return view

    }

    private fun hideKeyboard() {
        if (view != null) {
            val hide = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(requireView().windowToken,0)
        }
    }

}