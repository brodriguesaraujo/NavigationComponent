package br.com.navigationcomponent.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.com.navigationcomponent.R
import br.com.navigationcomponent.databinding.LoginFragmentBinding
import br.com.navigationcomponent.extensions.navigateWithAnimations
import br.com.navigationcomponent.ui.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mAuthorized.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigateWithAnimations(R.id.profileFragment)
            } else {
                Toast.makeText(context, R.string.login_error, Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonLoginSignIn.setOnClickListener {
            val username = binding.inputLoginUsername.text.toString()
            val password = binding.inputLoginPassword.text.toString()
            viewModel.authetication(username, password)
        }
    }

}