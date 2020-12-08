# Pong

Sovellus on kaksin pelattava oldschool -peli Pong, jossa tarkoituksena on saada pallo osumaan toisen pelaajan päätyyn, estäen samalla palloa osumasta omaan päätyyn mailaa käyttäen. Peli pitää myös kirjaa parhaista pelaajista, joita pääsee tarkastelemaan päävalikon "High Scores" -kohdasta.

Peli on harjoitustyö Helsingin yliopiston tietojenkäsittelytieteen kurssille Ohjelmistotekniikka.

## Dokumentaatio

[Käyttöohje](https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/kayttoohje.md)

[Työaikakirjanpito](https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/avanine/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/avanine/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

ja testikattavuusraportin saat komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_


### Suoritettavan jar-tiedoston generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston


### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/avanine/ot-harjoitustyo/blob/main/Pong/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
