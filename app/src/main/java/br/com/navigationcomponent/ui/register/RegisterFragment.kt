package br.com.navigationcomponent.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.navigationcomponent.databinding.FragmentRegisterBinding
import br.com.navigationcomponent.extensions.navigateWithAnimations

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButtonSave.setOnClickListener {
            val name = binding.registerInputName.text.toString()
            val directions = RegisterFragmentDirections
                .actionRegisterFragmentToCredentialsFragment(name)

            findNavController().navigateWithAnimations(directions)
        }
    }
}
