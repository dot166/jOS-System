package jOS.System

import android.content.Context
import android.content.Intent
import jOS.Core.LIBTestActivity
import jOS.Core.jAboutActivity

class CreditsActivity : jAboutActivity() {
    override fun versionIntent(context: Context): Intent {
        return Intent(context, LIBTestActivity::class.java)
    }

    override fun showOnlyContributors(context: Context): Boolean {
        return true
    }

    override fun product(): List<Contributor> {
        return object : ArrayList<Contributor>() {
            init {
                add(
                    Contributor(
                        "._______166",
                        Role.LeadDev,
                        "https://avatars.githubusercontent.com/u/62702353",
                        "https://github.com/dot166"
                    )
                )
                add(
                    Contributor(
                        "bh916",
                        Role.Dev,
                        "https://avatars.githubusercontent.com/u/138221251",
                        "https://github.com/bh196"
                    )
                )
                add(
                    Contributor(
                        "GrapheneOS",
                        Role.Graphene,
                        "https://avatars.githubusercontent.com/u/48847184",
                        "https://github.com/grapheneos"
                    )
                )
            }
        }
    }

    enum class Role(val descriptionResId: Int) : Roles {
        LeadDev(jOS.Core.R.string.leaddev),
        Dev(jOS.Core.R.string.dev),
        Graphene(R.string.about_graphene_info);

        override fun descriptionResId(): Int {
            return this.descriptionResId
        }
    }
}
