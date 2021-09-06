package hr.algebra.iamu_projekt

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_splash.*
import mehdi.sakout.aboutpage.AboutPage


class About : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return AboutPage(context)
            .isRTL(false)
            .setImage(hr.algebra.iamu_projekt.R.drawable.logo)
            .setDescription(getString(hr.algebra.iamu_projekt.R.string.about_description))
            .addEmail("frankopapic@gmail.com", "Email")
            .addFacebook("facebook.com", "Facebook")
            .addGitHub("github.com/frankopapic", "Github")
            .create()
    }
}