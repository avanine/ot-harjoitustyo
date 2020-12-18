# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Pakkauksen *domain* luokille [Ball](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/main/java/pong/domain/Ball.java), [Paddle](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/main/java/pong/domain/Paddle.java) sekä [Player](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/main/java/pong/domain/Player.java) on tehty testit testaamaan luokkien toimintaa sekä yhdessä että erikseen. Testitapaukset simuloivat luokkien toimintaa peliä pelatessa.

### DAO-luokat

Tiedon pysyväistallennusta on testattu luokilla [PlayerScoreDaoTest](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/test/java/dao/PlayerScoreDaoTest.java) ja [ThemeColorDaoTest](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/src/test/java/dao/ThemeColorDaoTest.java). Jotta pelin tiedot eivät muuttuisi testatessa, annetaan DAO-luokkien konstruktoreille parametrinä erilliset testaukseen tarkoitetut tiedostot *test.txt* ja *colorTest.txt*, jotka on määritelty *config.properties* -tiedostossa.

### Testauskattavuus

Testauskattavuudessa ei ole huomioitu käyttöliittymän rakentavia luokkia. Näitä lukuunottamatta testauksen rivikattavuus on 83% ja haarautumakattavuus 69%.

<img src="https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/testauskattavuus.png" width="820">

Testaamatta jäivät *Paddle*- luokan mailan liikuttamisesta vastaavat metodit, jotka ottavat parametrikseen KeyEventin.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on asennettu ja sitä on testattu käyttöohjeen mukaan OSX-, Linux- sekä Windows-ympäristössä siten, että sovelluksen käynnistyshakemistossa on ollut mukana *config.properties*-tiedosto.

Sovellusta on testattu sekä tilanteissa joissa tiedon pysyväistallennuksesta vastaavat tekstitiedostot ovat olleet olemassa, että tilanteissa joissa niitä ei ole ollut, jolloin ohjelma luo ne automaattisesti.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/vaatimusmaarittely.md) listaamat toiminnallisuudet on käyty läpi ja ne toimivat toivotulla tavalla. Pelaajien nimien syöttämistä on kokeiltu myös virheellisillä syötteillä, kuten tyhjällä, liian pitkällä, sekä puolipisteen sisältävällä syötteellä.

## Sovellukseen jääneet laatuongelmat

Ei testejä mailaa liikuttaville metodeille.
