package br.com.navigationcomponent.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.com.navigationcomponent.R
import br.com.navigationcomponent.databinding.FragmentProfileBinding
import br.com.navigationcomponent.ui.viewmodel.UserViewModel

class ProfileFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()
    }

    private fun observer() {
        val navController = findNavController()
        userViewModel.authorized.observe(viewLifecycleOwner, {
            when (it) {
                is UserViewModel.AuthenticationState.Authenticated -> {
                    binding.textWelcome.text =
                        getString(R.string.text_welcome, userViewModel.username)
                }
                is UserViewModel.AuthenticationState.Unauthenticated -> {
                    navController.navigate(R.id.loginFragment)
                }
            }
        })
    }
}