# Arduino NetBeans sample makefile (Arlotto 2015) 
# ------------------------------------------------------------------------------
# Configure the values of COM_PORT, USER_HOME and/or ARDUINO_BASE_DIR 
# and platform (Uno, Nano or Mega). 
# Update the declarations of the libraries that you want to use.
# Only tested on Arduino Uno,Nano and Mega
# For Arduino Duemilanove you must change the BAUD_RATE to 57600 bps.
# Based on:  http://playground.arduino.cc/Code/Netbeans
# Based on : http://arduino-netbeans.googlecode.com

MAKEFILE_VERSION = 1.2
ARDUINO_VERSION = 185
USER_HOME = /home/snir2g2/Téléchargements
ARDUINO_BASE_DIR = ${USER_HOME}/arduino-1.8.5
ARDUINO_TOOL_DIR = ${ARDUINO_BASE_DIR}/hardware/tools/avr/bin
ARDUINO_CORE_DIR = ${ARDUINO_BASE_DIR}/hardware/arduino/avr/cores/arduino
ARDUINO_LIB_DIR = ${ARDUINO_BASE_DIR}/libraries
ARDUINO_AVR_LIB_DIR = ${ARDUINO_BASE_DIR}/hardware/arduino/avr/libraries
ARDUINO_IDE_LIB_DIR = ${USER_HOME}/Arduino/libraries
LIB_CORE_DIR = lib/core
LIB_LIBS_DIR = lib/libs
ARDUINO_LIB_CORE = ${LIB_CORE_DIR}/arduinocore.a
ARDUINO_LIB_LIBS = ${LIB_LIBS_DIR}/arduinolibs.a


# Arduino Uno:
ARDUINO_MODEL = atmega328p
ARDUINO_PROGRAMMER = arduino
COM_PORT = /dev/ttyACM0
BAUD_RATE = 115200
ARDUINO_PINS_DIR = ${ARDUINO_BASE_DIR}/hardware/arduino/avr/variants/standard

# Arduino Nano:
#ARDUINO_MODEL = atmega328p
#ARDUINO_PROGRAMMER = arduino
#COM_PORT = /dev/ttyUSB0
#BAUD_RATE = 57600
#ARDUINO_PINS_DIR = ${ARDUINO_BASE_DIR}/hardware/arduino/avr/variants/standard


# Arduino Mega 2560:
#ARDUINO_MODEL = atmega2560
#ARDUINO_PROGRAMMER = wiring
#COM_PORT = /dev/ttyACM0
#BAUD_RATE = 115200
#ARDUINO_PINS_DIR = ${ARDUINO_BASE_DIR}/hardware/arduino/avr/variants/mega/



# Include the libraries that you want (separeted by ; , end with ;) . 
# At least one librairies must be present : 
#     for instance include AVR Wire lib if you don't use any librairies 
# For those which are subfolders of "arduino-1.6.4/libraries" folder:

#INCLUDE_LIBS=WiFi;

#INCLUDE_LIBS=Esplora;Ethernet;Ethernet;Firmata;GSMSHIELD;\
LiquidCrystal;MemoryFree;SD;Servo;\
Stepper;WiFi;

#For those wihch are subfoldres of "arduino-1.6.4/hardware/arduino/avr/libraries" folder :
INCLUDE_AVR_LIBS=Wire;
#INCLUDE_AVR_LIBS=Wire;SPI;EEPROM;SoftwareSerial;

#For those wihch are installed by 1.6.4 IDE :
#INCLUDE_IDE_LIBS=RTClib;

# Select the Windows or Linux config of AVR_DUDE (upload utility):
#AVR_DUDE_WINDOWS = ${ARDUINO_BASE_DIR}/hardware/tools/avr/bin/avrdude -C ${ARDUINO_BASE_DIR}/hardware/tools/avr/etc/avrdude.conf	
AVR_DUDE_LINUX = ${ARDUINO_BASE_DIR}/hardware/tools/avr/bin/avrdude -C ${ARDUINO_BASE_DIR}/hardware/tools/avr/etc/avrdude.conf
AVR_DUDE = ${AVR_DUDE_LINUX}
	
#
############################# END OF USER CHANGES #############################
#Don't make any change here unless you know what you are doing
	
INCLUDE = -c -g \
-I${ARDUINO_CORE_DIR} \
-I${ARDUINO_PINS_DIR} \
$(patsubst %,-I${ARDUINO_LIB_DIR}/%,$(subst ;, ,$(INCLUDE_LIBS)))\
$(patsubst %,-I${ARDUINO_LIB_DIR}/%/src,$(subst ;, ,$(INCLUDE_LIBS)))\
$(patsubst %,-I${ARDUINO_LIB_DIR}/%/utility,$(subst ;, ,$(INCLUDE_LIBS)))\
$(patsubst %,-I${ARDUINO_AVR_LIB_DIR}/%,$(subst ;, ,$(INCLUDE_AVR_LIBS)))\
$(patsubst %,-I${ARDUINO_AVR_LIB_DIR}/%/src,$(subst ;, ,$(INCLUDE_AVR_LIBS)))\
$(patsubst %,-I${ARDUINO_AVR_LIB_DIR}/%/utility,$(subst ;, ,$(INCLUDE_AVR_LIBS)))\
$(patsubst %,-I${ARDUINO_AVR_LIB_DIR}/%,$(subst ;, ,$(INCLUDE_IDE_LIBS)))\
$(patsubst %,-I${ARDUINO_AVR_LIB_DIR}/%/src,$(subst ;, ,$(INCLUDE_IDE_LIBS)))\
$(patsubst %,-I${ARDUINO_AVR_LIB_DIR}/%/utility,$(subst ;, ,$(INCLUDE_IDE_LIBS)))

FLAGS_GCC = -c -g -Os -Wall -ffunction-sections -fdata-sections -mmcu=${ARDUINO_MODEL} -DF_CPU=16000000L -MMD -DUSB_VID=null -DUSB_PID=null -DARDUINO=${ARDUINO_VERSION}
FLAGS_GPP = ${FLAGS_GCC} -fno-exceptions
FLAGS_LINKER = ${ARDUINO_LIB_CORE} ${ARDUINO_LIB_LIBS} -Os -Wl,--gc-sections,--relax -mmcu=${ARDUINO_MODEL} -lm
#FLAGS_LINKER = -Os -Wl,--gc-sections,--relax -mmcu=${ARDUINO_MODEL} -lm
CMD_AVR_GCC = ${ARDUINO_TOOL_DIR}/avr-gcc ${FLAGS_GCC} ${INCLUDE}
CMD_AVR_GPP = ${ARDUINO_TOOL_DIR}/avr-g++ ${FLAGS_GPP} ${INCLUDE}
CMD_AVR_AR =  ${ARDUINO_TOOL_DIR}/avr-ar rcs

CORE_CPP_SOURCES = $(wildcard ${ARDUINO_CORE_DIR}/*.cpp)
CORE_C_SOURCES = $(wildcard ${ARDUINO_CORE_DIR}/*.c)
COBJECTS=$(CORE_CPP_SOURCES:.cpp=.cpp.o) $(CORE_C_SOURCES:.c=.c.o)
CORE_OBJECTS=$(subst ${ARDUINO_CORE_DIR},${LIB_CORE_DIR},${COBJECTS})

# CPP and C source files are searched in ./ ./utility ./src and ./src/utility subfolders

LIB_CPP_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/*.cpp,$(subst ;, ,$(INCLUDE_LIBS))))
LIB_CPP_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/utility/*.cpp,$(subst ;, ,$(INCLUDE_LIBS))))
LIB_CPP_SRC_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/src/*.cpp,$(subst ;, ,$(INCLUDE_LIBS))))
LIB_CPP_SRC_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/src/utility/*.cpp,$(subst ;, ,$(INCLUDE_LIBS))))


LIB_AVR_CPP_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/*.cpp,$(subst ;, ,$(INCLUDE_AVR_LIBS))))
LIB_AVR_CPP_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/utility/*.cpp,$(subst ;, ,$(INCLUDE_AVR_LIBS))))
LIB_AVR_CPP_SRC_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/src/*.cpp,$(subst ;, ,$(INCLUDE_AVR_LIBS))))
LIB_AVR_CPP_SRC_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/src/utility/*.cpp,$(subst ;, ,$(INCLUDE_AVR_LIBS))))

LIB_IDE_CPP_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/*.cpp,$(subst ;, ,$(INCLUDE_IDE_LIBS))))
LIB_IDE_CPP_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/utility/*.cpp,$(subst ;, ,$(INCLUDE_IDE_LIBS))))
LIB_IDE_CPP_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/utility/*.cpp,$(subst ;, ,$(INCLUDE_IDE_LIBS))))
LIB_IDE_CPP_SRC_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/src/*.cpp,$(subst ;, ,$(INCLUDE_IDE_LIBS))))
LIB_IDE_CPP_SRC_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/src/utility/*.cpp,$(subst ;, ,$(INCLUDE_IDE_LIBS))))

LIB_C_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/*.c,$(subst ;, ,$(INCLUDE_LIBS))) )
LIB_C_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/utility/*.c,$(subst ;, ,$(INCLUDE_LIBS))))
LIB_C_SRC_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/src/*.c,$(subst ;, ,$(INCLUDE_LIBS))))
LIB_C_SRC_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_LIB_DIR}/%/src/utility/*.c,$(subst ;, ,$(INCLUDE_LIBS))))

LIB_AVR_C_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/*.c,$(subst ;, ,$(INCLUDE_AVR_LIBS))))
LIB_AVR_C_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/utility/*.c,$(subst ;, ,$(INCLUDE_AVR_LIBS))))
LIB_AVR_C_SRC_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/src/*.c,$(subst ;, ,$(INCLUDE_AVR_LIBS))))
LIB_AVR_C_SRC_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_AVR_LIB_DIR}/%/src/utility/*.c,$(subst ;, ,$(INCLUDE_AVR_LIBS))))

LIB_IDE_C_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/*.c,$(subst ;, ,$(INCLUDE_IDE_LIBS))))
LIB_IDE_C_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/utility/*.c,$(subst ;, ,$(INCLUDE_IDE_LIBS))))
LIB_IDE_C_SRC_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/src/*.c,$(subst ;, ,$(INCLUDE_IDE_LIBS))))
LIB_IDE_C_SRC_UTILITY_SOURCES = $(wildcard $(patsubst %,${ARDUINO_IDE_LIB_DIR}/%/src/utility/*.c,$(subst ;, ,$(INCLUDE_IDE_LIBS))))



LOBJECTS=$(LIB_CPP_SOURCES:.cpp=.cpp.o) $(LIB_CPP_SRC_SOURCES:.cpp=.cpp.o) $(LIB_CPP_SRC_UTILITY_SOURCES:.cpp=.cpp.o)\
	 $(LIB_C_SOURCES:.c=.c.o) $(LIB_C_SRC_SOURCES:.c=.c.o) $(LIB_C_SRC_UTILITY_SOURCES:.c=.c.o)
	
LAVROBJECTS=$(LIB_AVR_CPP_SOURCES:.cpp=.cpp.o) $(LIB_AVR_CPP_SRC_SOURCES:.cpp=.cpp.o)\
	    $(LIB_AVR_CPP_SRC_UTILITY_SOURCES:.cpp=.cpp.o) $(LIB_AVR_CPP_UTILITY_SOURCES:.cpp=.cpp.o)\
	    $(LIB_AVR_C_SOURCES:.c=.c.o) $(LIB_AVR_C_SRC_SOURCES:.c=.c.o)\
	    $(LIB_AVR_C_SRC_UTILITY_SOURCES:.c=.c.o) $(LIB_AVR_C_UTILITY_SOURCES:.c=.c.o) 
	
LIDEOBJECTS=$(LIB_IDE_CPP_SOURCES:.cpp=.cpp.o) $(LIB_IDE_SRC_SOURCES:.cpp=.cpp.o) $(LIB_IDE_SRC_UTILITY_SOURCES:.cpp=.cpp.o)\
	    $(LIB_IDE_C_SOURCES:.c=.c.o) $(LIB_IDE_C_SRC_SOURCES:.c=.c.o) $(LIB_IDE_C_SRC_UTILITY_SOURCES:.c=.c.o)
	
	
LIB_OBJECTS=$(subst ${ARDUINO_LIB_DIR},${LIB_LIBS_DIR},${LOBJECTS})\
            $(subst ${ARDUINO_AVR_LIB_DIR},${LIB_LIBS_DIR},${LAVROBJECTS})\
            $(subst ${ARDUINO_IDE_LIB_DIR},${LIB_LIBS_DIR},${LIDEOBJECTS})


# Environment 
MKDIR=mkdir
CP=cp
CCADMIN=CCadmin


# build
build: .build-post	
${ARDUINO_LIB_CORE}: ${CORE_OBJECTS}
	${CMD_AVR_AR} ${ARDUINO_LIB_CORE} ${CORE_OBJECTS}
	
${ARDUINO_LIB_LIBS}: ${LIB_OBJECTS}
	${CMD_AVR_AR} ${ARDUINO_LIB_LIBS} ${CORE_OBJECTS} ${LIB_OBJECTS}
	
libraries: ${ARDUINO_LIB_CORE} ${ARDUINO_LIB_LIBS}

${LIB_CORE_DIR}/%.cpp.o: ${ARDUINO_CORE_DIR}/%.cpp
	mkdir -p $(dir $@)
	${CMD_AVR_GPP} $< -o $@

${LIB_CORE_DIR}/%.c.o: ${ARDUINO_CORE_DIR}/%.c
	mkdir -p $(dir $@)
	${CMD_AVR_GCC} $< -o $@

${LIB_LIBS_DIR}/%.cpp.o: ${ARDUINO_LIB_DIR}/%.cpp
	mkdir -p $(dir $@)
	${CMD_AVR_GPP} $< -o $@

${LIB_LIBS_DIR}/%.c.o: ${ARDUINO_LIB_DIR}/%.c
	mkdir -p $(dir $@)
	${CMD_AVR_GCC} $< -o $@
	
${LIB_LIBS_DIR}/%.cpp.o: ${ARDUINO_AVR_LIB_DIR}/%.cpp
	mkdir -p $(dir $@)
	${CMD_AVR_GPP} $< -o $@

${LIB_LIBS_DIR}/%.c.o: ${ARDUINO_AVR_LIB_DIR}/%.c
	mkdir -p $(dir $@)
	${CMD_AVR_GCC} $< -o $@
	
${LIB_LIBS_DIR}/%.cpp.o: ${ARDUINO_IDE_LIB_DIR}/%.cpp
	mkdir -p $(dir $@)
	${CMD_AVR_GPP} $< -o $@

${LIB_LIBS_DIR}/%.c.o: ${ARDUINO_IDE_LIB_DIR}/%.c
	mkdir -p $(dir $@)
	${CMD_AVR_GCC} $< -o $@
	
.build-pre: .build-pre-pre libraries

#Unconditional Build
.build-pre-pre:
	
.build-post: .build-impl	
	${ARDUINO_TOOL_DIR}/avr-objcopy -O ihex ${CND_ARTIFACT_PATH_${CONF}} ${CND_ARTIFACT_PATH_${CONF}}.hex
	${ARDUINO_TOOL_DIR}/avr-size --mcu=${ARDUINO_MODEL} -C ${CND_ARTIFACT_PATH_${CONF}}

#upload: .build-post
upload: 
	${AVR_DUDE} -v -p${ARDUINO_MODEL} -c${ARDUINO_PROGRAMMER} -P ${COM_PORT} -b${BAUD_RATE} -D -Uflash:w:${CND_ARTIFACT_PATH_${CONF}}.hex:i

#debug make : dummy target to help debug makefile 
mkdebug:
	 
	 echo $(LAVROBJECTS)
	 echo $(LIDEOBJECTS)
	 echo ${LIB_OBJECTS}
	 echo ${LIB_AVR_CPP_SOURCES}
	 echo ${LIB_AVR_CPP_SRC_SOURCES}
	 echo ${LIB_AVR_CPP_UTILITY_SOURCES}
	
#delete all generated targets 
mister-proper:
	 rm -rf $(LIB_CORE_DIR) $(LIB_LIBS_DIR)
# clean
clean: .clean-post

.clean-pre:
# Add your pre 'clean' code here...

.clean-post: .clean-impl
# Add your post 'clean' code here...


# clobber
clobber: .clobber-post

.clobber-pre:
# Add your pre 'clobber' code here...

.clobber-post: .clobber-impl
# Add your post 'clobber' code here...


# all
all: .all-post

.all-pre:
# Add your pre 'all' code here...

.all-post: .all-impl
# Add your post 'all' code here...


# build tests
build-tests: .build-tests-post

.build-tests-pre:
# Add your pre 'build-tests' code here...

.build-tests-post: .build-tests-impl
# Add your post 'build-tests' code here...


# run tests
test: .test-post

.test-pre: build-tests
# Add your pre 'test' code here...

.test-post: .test-impl
# Add your post 'test' code here...


# help
help: .help-post

.help-pre:
# Add your pre 'help' code here...

.help-post: .help-impl
# Add your post 'help' code here...


# include project implementation makefile
include nbproject/Makefile-impl.mk

# include project make variables
include nbproject/Makefile-variables.mk
