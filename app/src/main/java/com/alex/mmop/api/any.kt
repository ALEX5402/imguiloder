package com.alex.mmop.api

object any {
    external fun valueof() : Array<String>
    val source = valueof()
    const val installbtn = "box1"
    const val uninstallbtn = "box2"
    const val loginbtn = "login"
    const val register = "reg"
    const val globaltag = "alexapi"
     val loginurl : String = source[4]
     val apikey : String = source[5]
    const val prefskey = "userkeyinfo"
    const val usersafe = "usersafe"
    const val packageinstallpermisson = "packageinstallpermisson"
    const val settings = "mainsettings"
    const val gmsmode = "gmsmode"
    const val crashmode = "crashmode"
    const val animation = "animation"
    const val rootmode = "rootmode"
    const val hideroot = "hideroot"
    const val vpnmode = "vpnmode"
    var facebook :String = "com.facebook.katana"
    var twitter :String = "com.twitter.android"
    var zippass = source[1]

    var bgmistatus  : Boolean = true
    var globalstatus : Boolean = false
    var koreastatus  : Boolean = false
    var chinastatus  : Boolean = false
    var tiwanstatus  : Boolean = false
    var vngstatus   : Boolean = false
    var modownername  : String = ""

    var liburlgl : String = ""
    var libbgmiurl : String = ""
    var globalmode : Boolean = false










}