#ifndef LedTri_H
#define LedTri_H
#include <Arduino.h>

enum COLOR {rouge = 1, jaune, vert, cyan, bleu, magenta};

class LedTri{
  
  public:
	  LedTri();
	  ~LedTri();
	
	  LedTri( int pLedRougePin, int pLedVertPin, int pLedBleuPin );
	
	  void setCouleur(COLOR couleur);

    void setCouleur(int rouge, int vert, int bleu);

  private:
	  COLOR couleur;
	  int ledRougePin;
	  int ledVertPin;
	  int ledBleuPin;
	  void setRouge();
	  void setJaune();
	  void setVert();
	  void setCyan();
	  void setBleu();
	  void setMagenta();
};

#endif /* LedTri */

