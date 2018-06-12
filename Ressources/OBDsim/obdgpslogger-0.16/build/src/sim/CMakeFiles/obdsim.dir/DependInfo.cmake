# The set of languages for which implicit dependencies are needed:
set(CMAKE_DEPENDS_LANGUAGES
  "CXX"
  )
# The set of files for implicit dependencies of each language:
set(CMAKE_DEPENDS_CHECK_CXX
  "/home/ocommeng/obdgpslogger-0.16/src/sim/bluetoothsimport.cc" "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/obdsim.dir/bluetoothsimport.cc.o"
  "/home/ocommeng/obdgpslogger-0.16/src/sim/mainloop.cc" "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/obdsim.dir/mainloop.cc.o"
  "/home/ocommeng/obdgpslogger-0.16/src/sim/obdsim.cc" "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/obdsim.dir/obdsim.cc.o"
  "/home/ocommeng/obdgpslogger-0.16/src/sim/posixsimport.cc" "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/obdsim.dir/posixsimport.cc.o"
  "/home/ocommeng/obdgpslogger-0.16/src/sim/simport.cc" "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/obdsim.dir/simport.cc.o"
  )
set(CMAKE_CXX_COMPILER_ID "GNU")

# Preprocessor definitions for this target.
set(CMAKE_TARGET_DEFINITIONS
  "AVE_GETTIMEOFDAY"
  "HAVE_BLUETOOTH"
  "HAVE_GPSD"
  "HAVE_GPSD_V3"
  "HAVE_POSIX_OPENPT"
  "HAVE_PTSNAME_R"
  "OBDGPSLOGGER_MAJOR_VERSION=0"
  "OBDGPSLOGGER_MINOR_VERSION=16"
  "OBDPLATFORM_POSIX"
  "OBDSIMGEN_CYCLE"
  "OBDSIMGEN_DLOPEN"
  "OBDSIMGEN_ERROR"
  "OBDSIMGEN_GUI_FLTK"
  "OBDSIMGEN_LOGGER"
  "OBDSIMGEN_RANDOM"
  "OBDSIMGEN_SOCKET"
  )

# Targets to which this target links.
set(CMAKE_TARGET_LINKED_INFO_FILES
  "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/ckobdsim_gui_fltk.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/ckobdsim_error.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/ckobdsim_random.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/ckobdsim_socket.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/ckobdsim_dlopen.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/ckobdsim_logger.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/libs/sqlite3/CMakeFiles/cksqlite.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/sim/CMakeFiles/ckobdsim_cycle.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/obdinfo/CMakeFiles/ckobdinfo.dir/DependInfo.cmake"
  "/home/ocommeng/obdgpslogger-0.16/build/src/conf/CMakeFiles/ckobdconfigfile.dir/DependInfo.cmake"
  )

# The include file search paths:
set(CMAKE_C_TARGET_INCLUDE_PATH
  "."
  "../src/obdinfo"
  "../src/conf"
  "../libs/sqlite3"
  "../src/sim/."
  "../src/sim/../obdinfo"
  "../src/sim/./generators/gui_fltk"
  "/usr/lib/fltk"
  "src/sim"
  "../src/sim/generators/dlopen"
  )
set(CMAKE_CXX_TARGET_INCLUDE_PATH ${CMAKE_C_TARGET_INCLUDE_PATH})
set(CMAKE_Fortran_TARGET_INCLUDE_PATH ${CMAKE_C_TARGET_INCLUDE_PATH})
set(CMAKE_ASM_TARGET_INCLUDE_PATH ${CMAKE_C_TARGET_INCLUDE_PATH})
