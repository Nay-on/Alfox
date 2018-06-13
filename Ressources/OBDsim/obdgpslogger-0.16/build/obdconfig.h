#ifndef OBDCONFIG_H
#define OBDCONFIG_H

#define OBD_GPSD_PIDFILE "/tmp/obd_gpsd_pid"
#define OBD_GPSD_CONTROL_SOCKET "/tmp/obd_gpsd_controlsock"
#define OBD_DEFAULT_GPSPORT "/dev/usbserial"
#define OBD_DEFAULT_SERIALPORT "/dev/cu.OBDKeyPro-DevB-1"
#define OBD_DEFAULT_DATABASE "./obdgpslogger.db"
#define OBD_CONFIG_FILENAME ".obdgpslogger"
#define OBD_DEFAULT_COLUMNS "temp,rpm,vss,maf,throttlepos"
#define OBD_FTDIPTY_DEVICE "/var/run/obdftdipty.device"
#define OBDSIM_ELM_VERSION_STRING "ELM327 v1.3a OBDGPSLogger"
#define OBDSIM_ELM_DEVICE_STRING "OBDGPSLogger"

#endif // OBDCONFIG_H

