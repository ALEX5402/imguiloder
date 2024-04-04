import os
import lief

#readelf -d libmmkvopp.so // to check lib included or not
libnative = lief.parse("libpine.so")#main target lib you want to include
libnative.add_library("libcrashlog.so") #which lib you want to include
libnative.write("libpine-mod.so") #final output lib
