# Pong

Sovellus on kaksin pelattava oldschool -peli Pong, jossa tarkoituksena on saada pallo osumaan toisen pelaajan päätyyn, estäen samalla palloa osumasta omaan päätyyn mailaa käyttäen. Peli pitää myös kirjaa parhaista pelaajista, joita pääsee tarkastelemaan päävalikon "High Scores" -kohdasta.

Peli on harjoitustyö Helsingin yliopiston tietojenkäsittelytieteen kurssille Ohjelmistotekniikka.

## Dokumentaatio

[Työaikakirjanpito](https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/tuntikirjanpito.md)
[Vaatimusmäärittely](https://github.com/avanine/ot-harjoitustyo/blob/main/dokumentaatio/vaatimusmaarittely.md)

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


### Suoritettavan jarin generointi

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
