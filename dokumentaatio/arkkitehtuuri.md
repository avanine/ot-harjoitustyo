
# Arkkitehtuurikuvaus

## Pakkausrakenne

Ohjelman rakenne koostuu kolmesta pakkauksesta:

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/Pakkausrakenne.png" width="280">

Pakkaus _Pong.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän sekä eri näkymistä vastaavat Scene-luokat. Pakkauksesta _Pong.domain_ löytyy sovelluslogiikka, ja pakkauksesta _Pong.dao_ pisteiden tallentamiseen ja hakemiseen käytetty koodi.

## Käyttöliittymä

Käyttöliittymä sisältää viisi erillistä näkymää
- alkuvalikko
- high score -näkymä
- pelaajien nimien syöttäminen
- pelinäkymä
- pelin loputtua näkymä joka kertoo voittajan, ja josta voi siirtyä joko uusintaotteluun tai takaisin päävalikkoon

Jokainen näkymä on oma Scene-olionsa, joka sijoitetaan yksi kerrallaan luokan PongUi Stage-olioon.

Näkymät on eriytetty domain-pakkauksen sovelluslogiikasta vastaavista luokista. Näkymät voivat kutsua esimerkiksi domain-pakkauksen luokkaa Player uutta pelaajaa luodessaan. Luokka Player huolehtii pelaajan luomisesta, johon kuuluu mm. nimen ja pisteiden asettaminen.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat [Player](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/main/java/domain/Player.java), [Ball](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/main/java/domain/Ball.java) sekä [Paddle](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/main/java/domain/Paddle.java). Kuten arvata saattaa, Player kuvaa pelaajaa, Ball palloa, ja Paddle pelaajien käyttämiä mailoja:

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/Domain.png" width="470">

Luokka Player sisältää tarvittavat metodit pelaajien luomiseen sekä tietojen hakemiseen, mm.:
- void setName()
  - käyttää konstruktorin parametrina saatua pelaajan nimeä hyväksyttävän pituisen ja muotoisen nimen luomiseen
- String getName()
- int getPoints()
- void resetPoints()
- void newPoint()

Ball ja Paddle vastaavat enimmäkseen suorakulmion luomisesta ja sijoittamisesta kentälle. Luokalla Ball on metodeja, joita kutsumalla pallon nopeutta ja suuntaa voi muuttaa.

_Player_ pääsee käsiksi luokkaan _PlayerScoreDao_, josta se voi tarvittaessa hakea pelaajan aiempien voittojen määrän high score -taulukon päivitystä varten.

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/Pakkausrakenne(2).png" width="380">

## Tietojen pysyväistallennus

Pakkauksen _Pong.dao_ luokka _PlayerScoreDao_ tallettaa pelaajien nimet yhdistettynä voittojen määrään tekstitiedostoon _scores.txt_. Sovellus tallettaa tiedot seuraavassa muodossa

<pre>
Parry Hotter;5
play3r;7
</pre>

Eli pelaajan nimi ja voittojen määrä puolipisteellä erotettuna.

Tulevaisuudessa myös luokilla Paddle ja Ball saattaa olla pääsy pakkaukseen _Pong.dao_, jos sovellusta laajentaa muistamaan myös yksittäisen pelin tilastoja. Esimerkki tällaisesta voisi olla pelaaja, jolla on suurin määrä peräkkäisiä osumia palloon.

## Päätoiminnallisuudet

Kuvataan sovelluksen päätoiminnallisuuksia sekvenssikaavioina.

### voittotilaston päivitys

Kun toinen pelaajista saa 10 pistettä ja peli päättyy, vaihtuu näkymä _EndSceneen_, joka hoitaa tilaston päivityksen. Seuraava sekvenssikaavio kuvaa tilannetta, jossa voitoista kirjaa pitävä tekstitiedosto _scores.txt_ on vielä tyhjä.

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/sd_score.png" width="500">
