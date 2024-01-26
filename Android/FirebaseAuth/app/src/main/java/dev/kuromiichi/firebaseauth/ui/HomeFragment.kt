package dev.kuromiichi.firebaseauth.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.kuromiichi.firebaseauth.R
import dev.kuromiichi.firebaseauth.databinding.FragmentHomeBinding
import dev.kuromiichi.firebaseauth.databinding.ItemContactBinding
import dev.kuromiichi.firebaseauth.models.Contact

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val auth by lazy { Firebase.auth }
    private val databaseReference by lazy { Firebase.database.reference }

    private lateinit var firebaseAdapter: FirebaseRecyclerAdapter<Contact, ViewHolder>
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as AppCompatActivity).supportActionBar?.title = "Contactos"

        if (auth.currentUser != null) {
            val uid = auth.currentUser!!.uid
            val query = databaseReference.child("contacts").orderByChild("uid").equalTo(uid)
            val options =
                FirebaseRecyclerOptions.Builder<Contact>().setQuery(query, Contact::class.java)
                    .build()

            firebaseAdapter = object : FirebaseRecyclerAdapter<Contact, ViewHolder>(options) {
                private lateinit var context: Context

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                    context = parent.context
                    return ViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
                    )
                }

                override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Contact) {
                    with(holder) {
                        val item = getItem(position)

                        bind(item)
                        setListeners(item.key!!)
                    }
                }
            }

            binding.rvContacts.apply {
                adapter = firebaseAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseAdapter.stopListening()
    }

    private fun editContact(contactKey: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToEditContactFragment(
                contactKey
            )
        )
    }

    private fun deleteContact(contactKey: String) {
        AlertDialog.Builder(requireContext()).setTitle("Eliminar contacto")
            .setMessage("¿Seguro que quieres borrar este contacto?")
            .setPositiveButton("Sí") { _, _ ->
                databaseReference.child("contacts").child(contactKey).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Contacto eliminado", Toast.LENGTH_SHORT).show()
                    }
            }.setNegativeButton("Cancelar") { _, _ -> }.show()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemContactBinding.bind(view)

        fun bind(contact: Contact) {
            binding.tvName.text = contact.name
            binding.tvPhone.text = contact.phone
            binding.tvEmail.text = contact.email
        }

        fun setListeners(contactKey: String) {
            binding.btnEdit.setOnClickListener { editContact(contactKey) }
            binding.btnDelete.setOnClickListener { deleteContact(contactKey) }
        }
    }
}