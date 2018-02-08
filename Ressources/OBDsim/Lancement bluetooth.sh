hcitool dev
obdsim --b -g gui_fltk
rfcomm bind 0 00:18:E7:1E:C6:29 1 # Change this MAC address, putting the MAC of your device
sdptool add SP
