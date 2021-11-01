package br.com.navigationcomponent.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.com.navigationcomponent.R
import br.com.navigationcomponent.extensions.navigateWithAnimations
import br.com.navigationcomponent.ui.viewmodel.LoginViewModel

class ProfileFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.mUnauthorized.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigateWithAnimations(R.id.loginFragment)
            }
        })
    }
}