package com.dede.basic

import android.content.Context
import android.view.View
import android.view.ViewGroup
import java.lang.ref.WeakReference

abstract class PlatLogoSnapshotProvider {

    @Deprecated
    open val includeBackground: Boolean = false

    @Deprecated
    private var cache: WeakReference<View>? = null

    @Deprecated
    fun get(context: Context): View {
        var view = cache?.get()
        if (view != null) {
            (view.parent as? ViewGroup)?.removeView(view)
            return view
        }
        view = create(context)
        cache = WeakReference(view)
        return view
    }

    @Deprecated
    abstract fun create(context: Context): View
}
