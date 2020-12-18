# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on kaksin pelattava peli Pong, jossa on tarkoitus saada kiekko osumaan toisen pelaajan päätyyn. Peli päättyy, kun toisella pelaajista on 10 pistettä.

## Toiminnallisuus

### Aloitusnäkymä

Aloitusnäkymässä on viisi eri vaihtoehtoa

* uusi peli
  * perinteinen kaksinpeli

* harjoituspeli
  * yksin pelattava loputtoman mittainen harjoituspeli
  
* pistetilastot
  
* asetukset

* sulje sovellus

### Näkymä pelaajien nimien kysymiseen

* avautuu, kun aloitetaan uusi peli

* kysyy molempien pelaajien nimet
  * jos jompikumpi/molemmat jätetään tyhjäksi, peli generoi nimet
  
* aloita peli -nappi

### Pelinäkymä

* pelikenttä
  * keskiviiva, mailat, pallo

* pelaajien pisteet

### Harjoittelunäkymä

* pelikenttä
  * keskiviiva, pelaajan maila, tietokoneen maila (koko pituuden pituinen), pallo
  
* tekstiohjeistus pelin lopettamiseen (peli loppuu painamalla Enter)

### Voittonäkymä (kaksinpelin jälkeen)

* peli kertoo kumpi voitti

* pelaaja valitsee haluaako uusintaottelun vai palata aloitusnäkymään

### High Scores -näkymä

* listaa 5 parasta pelaajaa

### Asetukset

* mahdollisuus valita viidestä eri teemaväristä

* nappi pistetilastojen tyhjentämiseen
  * popup joka varmistaa, että tilasto halutaan tyhjentää

## Jatkokehitys

Jatkossa sovellukseen voisi mahdollisuuksien mukaan lisätä seuraavia asioita

* yksittäisen pelin tilastoja, mm. pelin kesto

* mahdollisuus valita montako pistettä vaaditaan voittoon

* pelin laittaminen pauselle

* kaksinpeli tietokonetta vastaan

* laskuri sille, montako kertaa palloon osuu harjoitusnäkymässä

* pistetilasto harjoituspelille

* taustan, pelikentän, pallon ja mailojen ulkonäön muuttaminen
