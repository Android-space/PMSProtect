package io.bunnyblue.android.pmshooker;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;

/**
 * Created by bunnyblue on 5/23/17.
 */

public class FakeContext extends ContextWrapper {
    public FakeContext(Context base) {
        super(base);
    }

    @Override
    public PackageManager getPackageManager() {
        return new FakePackageManger(super.getPackageManager());
    }
}
