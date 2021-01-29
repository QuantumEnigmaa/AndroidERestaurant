package fr.isen.vaisseau.androiderestaurant2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityProductBinding
import fr.isen.vaisseau.androiderestaurant2.databinding.FragmentTemplateBinding

private lateinit var binding: FragmentTemplateBinding

private const val ARG_PARAM1 = "param1"

class FragmentOne : Fragment() {
    private var param1: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentTemplateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        //binding.fragmentOneText.text = param1
        Picasso.get().load(param1).placeholder(R.drawable.searching).error(R.drawable.error_image).fit().into(
            binding.fragmentOneImage)
    }

    companion object {
        fun newInstance(param1: String): FragmentOne =
                FragmentOne().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}