package com.j.jOS.info.components

import com.j.jOS.R


/**
 * Created by sds100 on 29/02/2020.
 */

fun Error.getFullMessage(resourceProvider: ResourceProvider) = when (this) {
    Error.NoAppToOpenUrl -> resourceProvider.getString(R.string.error_no_app_to_open_url)
}