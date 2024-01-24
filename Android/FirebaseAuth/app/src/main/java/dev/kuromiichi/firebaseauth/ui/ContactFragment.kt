package dev.kuromiichi.firebaseauth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.kuromiichi.firebaseauth.R
import dev.kuromiichi.firebaseauth.databinding.FragmentContactBinding
import dev.kuromiichi.firebaseauth.models.Contact

class ContactFragment : Fragment() {
    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!
    private val auth by lazy { Firebase.auth }
    private val databaseReference by lazy { Firebase.database.reference }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as AppCompatActivity).supportActionBar?.title = "Crear contacto"

        binding.btnSave.setOnClickListener {
            createContact()
        }
    }

    private fun createContact() {
        val name = binding.tilName.editText?.text.toString()
        val email = binding.tilEmail.editText?.text.toString()
        val phone = binding.tilPhone.editText?.text.toString()

        val key = databaseReference.push().key
        val uid = auth.currentUser!!.uid

        val contact = Contact(key, name, email, phone, uid)
        if (key != null) {
            databaseReference.child("contacts").child(key).setValue(contact).addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.tilName.editText?.text?.clear()
                    binding.tilEmail.editText?.text?.clear()
                    binding.tilPhone.editText?.text?.clear()
                    Toast.makeText(context, "Contacto guardado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error al guardar contacto", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Error al crear el contacto", Toast.LENGTH_SHORT).show()
        }
    }
}