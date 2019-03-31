package cn.qssq666.xposeddemo

import android.util.Log
import android.widget.TextView
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage

class Main : IXposedHookLoadPackage, IXposedHookZygoteInit {
    private val TAG: String?="Main"

    @Throws(Throwable::class)
     override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam?) {
        Log.w(TAG,"initZygote"+startupParam!!.modulePath)
    }

    @Throws(Throwable::class)
     override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
//        lpparam.classLoader
        //    public final void setText(CharSequence text)
        XposedHelpers.findAndHookMethod(TextView::class.java, "setText", CharSequence::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam?) {
                super.afterHookedMethod(param)
                //          param!!.args[0] = "_" as CharSequence
                param!!.args[0] = "" as CharSequence
            }

            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam?) {
                super.beforeHookedMethod(param)
            }

        });
    }

}