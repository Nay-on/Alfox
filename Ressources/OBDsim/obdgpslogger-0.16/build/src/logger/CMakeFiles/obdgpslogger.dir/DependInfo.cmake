# The set of languages for which implicit dependencies are needed:
set(CMAKE_DEPENDS_LANGUAGES
  "C"
  )
# The set of files for implicit dependencies of each language:
set(CMAKE_DEPENDS_CHECK_C
  "/home/ocommeng/obdgpslogger-0.16/src/logger/database.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/database.c.o"
  "/home/ocommeng/obdgpslogger-0.16/src/logger/ecudb.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/ecudb.c.o"
  "/home/ocommeng/obdgpslogger-0.16/src/logger/gpscomm.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/gpscomm.c.o"
  "/home/ocommeng/obdgpslogger-0.16/src/logger/gpsdb.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/gpsdb.c.o"
  "/home/ocommeng/obdgpslogger-0.16/src/logger/main.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/main.c.o"
  "/home/ocommeng/obdgpslogger-0.16/src/logger/obddb.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/obddb.c.o"
  "/home/ocommeng/obdgpslogger-0.16/src/logger/obddbus.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/obddbus.c.o"
  "/home/ocommeng/obdgpslogger-0.16/src/logger/tripdb.c" "/home/ocommeng/obdgpslogger-0.16/build/src/logger/CMakeFiles/obdgpslogger.dir/tripdb.c.o"
  )
set(CMAKE_C_COMPILER_ID "GNU")

# Preprocessor definitions for this target.
set(CMAKE_TARGET_DEFINITIONS
  "HAVE_GPSD"
  "HAVE_GPSD_V3"
  "HAVE_PTSNAME_R"
  "HAVE_SIGACTION"
  "HAVE_SIGNAL_H"
  "OBDGPSLOGGER_MAJOR_VERSION=0"
  "OBDGPSLOGGER_MINOR_VERSION=16"
  "OBDPLATFORM_POSIX"
  )

# Targets to which this target links.
set(CMAKE_TARGET_LINKED_INFO_FILES
  "/home/ocommeng/obdgpslogger-0.16/build/libs/sqlite3/CMakeFiles/cksqlite.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/conf/CMakeFiles/ckobdconfigfile.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/obdinfo/CMakeFiles/ckobdinfo.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/obdcomm/CMakeFiles/ckobdcomm.dir/DependInfo.cmake"
  )

# The include file search paths:
set(CMAKE_C_TARGET_INCLUDE_PATH
  "."
  "../src/obdinfo"
  "../src/conf"
  "../libs/sqlite3"
  "../src/logger/."
  "../src/logger/../obdinfo"
  "../src/logger/../obdcomm"
  )
set(CMAKE_CXX_TARGET_INCLUDE_PATH ${CMAKE_C_TARGET_INCLUDE_PATH})
set(CMAKE_Fortran_TARGET_INCLUDE_PATH ${CMAKE_C_TARGET_INCLUDE_PATH})
set(CMAKE_ASM_TARGET_INCLUDE_PATH ${CMAKE_C_TARGET_INCLUDE_PATH})
