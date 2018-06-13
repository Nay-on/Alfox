#l'installation d'obdgpslogger nécessite les étapes suivantes:
#
#    Télécharger OBDSim:
#
#   wget http://icculus.org/obdgpslogger/downloads/obdgpslogger-0.16.tar.gz 
#
#	Compiler le programme
#
#   tar xvf obdgpslogger-0.16.tar.gz
#   cd obdgpslogger-0.16
#   mkdir build
#   cd build
#
#  	Installer les bibliothèques suivantes et tout ce que cmake demande éventuellement en plus
#
#   sudo apt-get install libbluetooth-dev libfltk1.1-dev libfltk1.1 fltk1.1-doc fluid fftw3-dev libgps-dev libftdi-dev
#   cmake ..     #bien rajouter les deux points à la fin de la ligne
#   make obdsim
#	cd ../../
	
hcitool dev
./obdgpslogger-0.16/bin/obdsim -b -g Logger -s ces2010.db &
sudo rfcomm bind 0 78:0C:B8:04:6F:54 1 # remplacer l'adresse MAC par celle de l'interface bluetooth de l'ordinateur
sudo sdptool add SP
