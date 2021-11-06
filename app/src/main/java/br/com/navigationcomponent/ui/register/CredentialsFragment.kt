package br.com.navigationcomponent.ui.register

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
import androidx.navigation.fragment.navArgs
import br.com.navigationcomponent.R
import br.com.navigationcomponent.databinding.FragmentCredentialsBinding
import br.com.navigationcomponent.ui.viewmodel.UserViewModel

class CredentialsFragment : Fragment() {

    private lateinit var binding: FragmentCredentialsBinding
    private val args: CredentialsFragmentArgs by navArgs()
    private val userViewModel: UserViewModel by activityViewModels()
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCredentialsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        observer()
        setButton()
        returnPlunderCorrect()
        binding.loginTextWelcome.text = getString(R.string.login_text_welcome, args.name)
    }

    private fun returnPlunderCorrect() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelAuthentication()
        }
    }

    private fun setButton() {
        binding.buttonLoginSignIn.setOnClickListener {
            val username = binding.inputLoginUsername.text.toString()
            val password = binding.inputLoginPassword.text.toString()
            userViewModel.authetication(username, password)
        }
    }

    private fun observer() {
        userViewModel.authorized.observe(viewLifecycleOwner, {
            when (it) {
                is UserViewModel.AuthenticationState.Authenticated -> {
                    navController.popBackStack(R.id.profileFragment, false)
                }
                is UserViewModel.AuthenticationState.InvalidateAuthenticate -> {
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
        userViewModel.refuseAuthetication()
        navController.popBackStack(R.id.loginFragment, false)
    }
}