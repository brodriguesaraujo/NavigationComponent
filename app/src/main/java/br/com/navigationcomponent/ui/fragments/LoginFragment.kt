package br.com.navigationcomponent.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.com.navigationcomponent.R
import br.com.navigationcomponent.databinding.LoginFragmentBinding
import br.com.navigationcomponent.ui.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: LoginFragmentBinding

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        observer()
        authetication()
        returnPlunderCorrect()
    }

    private fun returnPlunderCorrect() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelAuthentication()
        }
    }

    private fun authetication() {
        binding.buttonLoginSignIn.setOnClickListener {
            val username = binding.inputLoginUsername.text.toString()
            val password = binding.inputLoginPassword.text.toString()
            viewModel.authetication(username, password)
        }
    }

    private fun observer() {
        viewModel.authorized.observe(viewLifecycleOwner, {
            when (it) {
                is LoginViewModel.AuthenticationState.Authenticated -> {
                    navController.popBackStack()
                }
                is LoginViewModel.AuthenticationState.InvalidateAuthenticate -> {
                    Toast.makeText(context, R.string.login_error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        cancelAuthentication()
        return true
    }

    private fun cancelAuthentication() {
        viewModel.refuseAuthetication()
        navController.popBackStack(R.id.startFragment, false)
    }

}