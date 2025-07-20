package jos.system;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import io.github.dot166.jlib.LIBTestActivity;
import io.github.dot166.jlib.app.jAboutActivity;

import java.util.ArrayList;
import java.util.List;

public class CreditsActivity extends jAboutActivity {
    @NonNull
    @Override
    public Intent versionIntent(@NonNull Context context) {
        return new Intent(context, LIBTestActivity.class);
    }

    @Override
    public boolean showOnlyContributors(@NonNull Context context) {
        return true;
    }

    @NonNull
    @Override
    public List<Contributor> product() {
        List<Contributor> contributors = new ArrayList<>();
        contributors.add(new Contributor(
                "._______166",
                Role.Maintainer,
                "https://avatars.githubusercontent.com/u/62702353",
                "https://github.com/dot166"
        ));
        contributors.add(new Contributor(
                "bh916",
                Role.Contributor,
                "https://avatars.githubusercontent.com/u/138221251",
                "https://github.com/bh196"
        ));
        contributors.add(new Contributor(
                "GrapheneOS",
                Role.Graphene,
                "https://avatars.githubusercontent.com/u/48847184",
                "https://github.com/grapheneos"
        ));
        return contributors;
    }

    public enum Role implements Roles {
        Maintainer(io.github.dot166.jlib.R.string.maintainer),
        Contributor(io.github.dot166.jlib.R.string.contributor),
        Graphene(R.string.about_graphene_info);

        private final int descriptionResId;

        Role(int descriptionResId) {
            this.descriptionResId = descriptionResId;
        }

        @Override
        public int descriptionResId() {
            return this.descriptionResId;
        }
    }
}