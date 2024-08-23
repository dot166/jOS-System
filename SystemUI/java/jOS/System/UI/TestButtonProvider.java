package jOS.System.UI;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;

import com.android.systemui.plugins.IntentButtonProvider;
import com.android.systemui.plugins.annotations.Requires;

@Requires(target = IntentButtonProvider.class, version = IntentButtonProvider.VERSION)
public class TestButtonProvider implements IntentButtonProvider {
    @Override
    public IntentButton getIntentButton() {
        return new TestButton();
    }

    static class TestButton implements IntentButtonProvider.IntentButton {
        private final Intent mIntent;
        private final IconState mIconState;

        public TestButton() {
            mIntent = new Intent().setComponent(new ComponentName("jOS.System", "jOS.System.PlatLogoActivity"));
            mIconState = new IconState();
            mIconState.isVisible = true;
            mIconState.drawable = Resources.getSystem().getDrawable(android.R.mipmap.sym_def_app_icon);
            mIconState.contentDescription = "test";
            mIconState.tint = false;
        }

        @Override
        public IntentButtonProvider.IntentButton.IconState getIcon() {
            return mIconState;
        }

        @Override
        public Intent getIntent() {
            return mIntent;
        }
    }
}
