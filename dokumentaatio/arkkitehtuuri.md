
# Arkkitehtuurikuvaus

## Pakkausrakenne

Ohjelman rakenne koostuu kolmesta pakkauksesta:

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/Pakkausrakenne.png" width="280">

Pakkaus _pong.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän sekä eri näkymistä vastaavat Scene-luokat. Pakkauksesta _pong.domain_ löytyy sovelluslogiikka, ja pakkauksesta _pong.dao_ pisteiden tallentamiseen ja hakemiseen käytetty koodi.

## Käyttöliittymä

Käyttöliittymä sisältää seitsemän erillistä näkymää
- alkuvalikko
- harjoituspeli
- high score -näkymä
- asetukset
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

Pakkauksen _pong.dao_ luokka _PlayerScoreDao_ tallettaa pelaajien nimet yhdistettynä voittojen määrään tekstitiedostoon _scores.txt_. Sovellus tallettaa tiedot seuraavassa muodossa

<pre>
Parry Hotter;5
play3r;7
</pre>

Eli pelaajan nimi ja voittojen määrä puolipisteellä erotettuna.

Tulevaisuudessa myös luokilla Paddle ja Ball saattaa olla pääsy pakkaukseen _pong.dao_, jos sovellusta laajentaa muistamaan myös yksittäisen pelin tilastoja. Esimerkki tällaisesta voisi olla pelaaja, jolla on suurin määrä peräkkäisiä osumia palloon.

## Päätoiminnallisuudet

Kuvataan sovelluksen päätoiminnallisuuksia sekvenssikaaviona.

### voittotilaston päivitys

Kun toinen pelaajista saa 10 pistettä ja peli päättyy, vaihtuu näkymä _EndSceneen_, joka hoitaa tilaston päivityksen. Seuraava sekvenssikaavio kuvaa tilannetta, jossa daoon tallennetaan ensimmäinen voitto. Tämän jälkeen avataan HighScoreScene, joka pyytää voittajia daolta.

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/sd1.png" width="300">

Ensin kutsutaan Player-luokan metodia win(), joka palauttaa true sen merkiksi että kyseinen pelaaja voitti. Tämän jälkeen kutsutaan PlayerScoreDao-luokan metodia addNewWin, jolle annetaan parametriksi voittajan nimi. PlayerScoredao tallentaa nimen ja voittojen kokonaismäärän tekstitiedostoon _scores.txt_. Seuraavaksi avataan HighScoreScene, joka kutsuu daon metodia getTopScores() parametrillä 5, eli viisi viimeisintä voittajaa. Tässä tapauksessa voittajia on kuitenkin vasta yksi, joten metodi palauttaa tallennetun pelaajan nimen sekä numeron 1 valmiiksi muotoiltuna.

### teemavärin vaihtaminen

Alla oleva sekvenssikaavio kuvaa tilannetta, jossa pelin käyttöliittymän väri vaihdetaan vihreäksi.

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/sd2.png" width="300">

Kutsutaan ThemeColorDaon metodia changeColor, joka ottaa parametrikseen värin johon vaihdetaan. Dao tallentaa värin, sekä SettingsScenen X-merkin uuden sijainnin tekstitiedostoon _color.txt_. Tämän jälkeen käyttöliittymän tekstit, maila sekä pallo kutsuvat komennon setFill() parametrina ThemeColorDaon metodia getColor(), joka palauttaa sillä hetkellä valittuna olevan värin, eli tässä tapauksessa vihreän.
