package dev.kuromiichi.firebaseauth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.kuromiichi.firebaseauth.databinding.FragmentEditContactBinding
import dev.kuromiichi.firebaseauth.models.Contact

class EditContactFragment : Fragment() {
    private var _binding: FragmentEditContactBinding? = null
    private val binding get() = _binding!!
    private val auth by lazy { Firebase.auth }
    private val databaseReference by lazy { Firebase.database.reference }
    private val args by navArgs<EditContactFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as AppCompatActivity).supportActionBar?.title = "Editar contacto"

        val key = args.contactKey

        databaseReference.child("contacts").child(key).get()
            .addOnSuccessListener { snapshot ->
                val contact = snapshot.value as? Contact
                updateUI(contact)
            }.addOnFailureListener {
                Toast.makeText(context, "Error al cargar el contacto", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(contact: Contact?) {
        contact?.let {
            with(binding) {
                tilName.editText?.setText(it.name)
                tilPhone.editText?.setText(it.phone)
                tilEmail.editText?.setText(it.email)
                btnSave.setOnClickListener { updateContact() }
            }
        }
    }

    private fun updateContact() {
        val name = binding.tilName.editText?.text.toString()
        val phone = binding.tilPhone.editText?.text.toString()
        val email = binding.tilEmail.editText?.text.toString()

        val contact = Contact(args.contactKey, name, email, phone, auth.currentUser!!.uid)

        databaseReference.child("contacts").child(args.contactKey).setValue(contact)
            .addOnSuccessListener {
                Toast.makeText(context, "Contacto actualizado", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, "Error al actualizar el contacto", Toast.LENGTH_SHORT)
                    .show()
            }

        findNavController().navigate(EditContactFragmentDirections.actionEditContactFragmentToHomeFragment())
    }
}