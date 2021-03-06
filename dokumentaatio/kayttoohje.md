# Käyttöohje

Lataa tiedosto [Pong.jar](https://github.com/avanine/ot-harjoitustyo/releases/tag/loppupalautus)

## Konfigurointi

Sovellus olettaa, että sen käynnistyshakemistossa on tiedosto *config.properties*, joka määrittelee pysyväistallennukseen käytetyt tiedostot.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar Pong.jar
```

## Aloitusnäkymä

Kun pelin käynnistää, avautuu seuraavan näköinen näkymä:

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/aloitusnakyma_uusi.png" width="480">

Klikkaamalla 'High Scores' voi nähdä top-pelaajat.

Kun valitsee 'New Game', kysyy peli pelaajien nimet. Jos kentät jättää tyhjäksi, generoi peli mysteerinimet pelaajille.
Start-napista pääsee aloittamaan pelin.

## Pelinäkymä

Nimien syöttämisen jälkeen avautuu pelinäkymä:

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/pelinakyma.png" width="480">

Vasemmanpuoleisen pelaajan maila liikkuu W- ja S-näppäimillä. Oikeanpuoleinen pelaaja käyttää nuolinäppäimiä. Tarkoitus on osua palloon mailalla. Peli loppuu, kun toinen pelaajista saa 10 pistettä.
 
## Asetukset

Kun päävalikosta valitsee Settings, pääsee asetuksiin:

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/asetukset.png" width="480">

Käyttöliittymän teeman värin voi valita eri värisiä laatikoita klikkaamalla, ja alla oleva nappi tyhjentää pistetilastot.
