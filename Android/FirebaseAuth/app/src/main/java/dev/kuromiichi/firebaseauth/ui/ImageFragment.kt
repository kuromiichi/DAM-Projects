package dev.kuromiichi.firebaseauth.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dev.kuromiichi.firebaseauth.databinding.FragmentImageBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class ImageFragment : Fragment() {
    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!
    private val auth by lazy { Firebase.auth }
    private val storage by lazy { Firebase.storage }
    private var imageUri: Uri? = null

    private val selectedImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                imageUri = uri
                binding.ivImage.setImageURI(uri)
            } else {
                Toast.makeText(
                    requireContext(), "Error al seleccionar la imagen", Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (context as AppCompatActivity).supportActionBar?.title = "Imagen"
        setButtons()

        val storage = Firebase.storage.reference
        var bytes = ByteArray(0)
        runBlocking {
            launch {
                bytes = storage.child("images").child(auth.currentUser!!.uid)
                    .getBytes(1024 * 1024).await()
            }
        }
        binding.ivImage.setImageBitmap(
            android.graphics.BitmapFactory.decodeByteArray(
                bytes,
                0,
                bytes.size
            )
        )
    }

    private fun setButtons() {
        binding.btnOpenGallery.setOnClickListener {
            selectImageFromGallery()
        }

        binding.btnUploadImage.setOnClickListener {
            uploadImage()
        }
    }

    private fun selectImageFromGallery() {
        selectedImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun uploadImage() {
        val uid = auth.currentUser!!.uid
        val storageReference = storage.reference.child("images").child(uid)

        if (imageUri != null) {
            binding.pbUploadImage.visibility = View.VISIBLE
            binding.tvProgress.visibility = View.VISIBLE

            storageReference.putFile(imageUri!!).addOnProgressListener {
                val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
                binding.tvProgress.text = "${progress.toInt()}%"
                binding.pbUploadImage.progress = progress.toInt()
            }.addOnCompleteListener {
                binding.pbUploadImage.visibility = View.INVISIBLE
                binding.tvProgress.visibility = View.INVISIBLE
            }.addOnSuccessListener {
                Toast.makeText(
                    requireContext(), "Imagen subida correctamente", Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(), "Error al subir la imagen: ${it.message}", Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}
