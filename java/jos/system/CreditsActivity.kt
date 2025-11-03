package jos.system

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import io.github.dot166.jlib.widget.ContributorRow


open class CreditsActivity : jActivity() {

    enum class Role(private val descriptionResId: Int) {
        Maintainer(io.github.dot166.jlib.R.string.maintainer),
        Contributor(io.github.dot166.jlib.R.string.contributor),
        Graphene(R.string.about_graphene_info);

        fun descriptionResId(): Int {
            return this.descriptionResId
        }
    }

    data class Contributor(
        val name: String?,
        val role: Role?,
        val photoUrl: String?,
        val socialUrl: String?
    )

    open fun product(): List<Contributor> {
        val contributors: MutableList<Contributor> = ArrayList<Contributor>()
        contributors.add(
            Contributor(
                "._______166",
                Role.Maintainer,
                "https://avatars.githubusercontent.com/u/62702353",
                "https://github.com/dot166"
            )
        )
        contributors.add(
            Contributor(
                "bh916",
                Role.Contributor,
                "https://avatars.githubusercontent.com/u/138221251",
                "https://github.com/bh196"
            )
        )
        contributors.add(
            Contributor(
                "GrapheneOS",
                Role.Graphene,
                "https://avatars.githubusercontent.com/u/48847184",
                "https://github.com/grapheneos"
            )
        )
        return contributors
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        val contributorsContainer = findViewById<LinearLayout>(R.id.contributors_container)
        for (contributor in product()) {
            val row = ContributorRow(this)
            row.setName(contributor.name)
            row.setDescription(getString(contributor.role!!.descriptionResId()))
            row.setUrl(contributor.socialUrl)
            row.setPhotoUrl(contributor.photoUrl)
            contributorsContainer.addView(row)
        }
        setSupportActionBar(findViewById(R.id.actionbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeActionContentDescription(androidx.appcompat.R.string.abc_action_bar_up_description)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
