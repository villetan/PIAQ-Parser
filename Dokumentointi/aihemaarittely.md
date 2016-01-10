## Aihe:
Mittausten parseri. Toteutetaan ohjelma, jonka avulla käyttäjä saa valittua mittaustulostiedostosta haluamansa 
sarakkeet ja haluamansa rivit. Mittauksia kirjataan sekunnin välein, mutta ei ole tarkoituksen mukaista katsoa mittausten vaiht
vaihtelua sekunnin välein, vaan käyttäjä voi valita eri mittauksia(mahdollisesti kaikki, mahdollisesti vain yhden) ja 
milta tahansa mahdolliselta aikajänteeltä.

Esimerkiksi mittaukset ovat 12.02.2014 12:00:00 ja 12.02.2014 13:00:00 välillä
ja mitattuja asioita on co2, lämpötila yms. Käyttäjä haluaa vain läpötilamittaukset minuutin välein ensimmäiseltä 
puolelta tunnilta. Ohjelma laskee seuraavan minuutin keskiarvon ja luo dokumentin (xml tai csv) jossa on nämä mittaukset
halutulla aikavälillä ja oikeilla mittausväleillä. Haluttua mittausväliä ei voi valita, jos luettu tiedoston mittausväli 
on liian lyhyt.

##Käyttäjät:
Ohjelman käyttäjä 

##Käyttäjien toiminnot:
* Tiedoston lukeminen
* Aikavälin valitseminen
* Mittaustulosten valitseminen
* Uuden tiedoston kirjoittaminen (XML)


##Rakenne
Ohjelman rakenteessa keskisimmissä osissa ovat Tiedosto, jota luetaan ja MittaustenAnalysoijaPalvelu. Erilaiset Tiedoston lukemiseen liittyvät toiminnallisuudet muodostavat hyvin tiiviin kokonaisuuden, joka sisältää rajapinnan, palvelun ja itse lukijoita.
	 Toisen pääkokonaisuuden muodostaa mittaukset, jotka toimivat eräänlaisina datavarasto-olioina. Mittausolio itsessään sisältää aikaleiman, joka kertoo milloin mittaus on otettu, ja siihen liittyvän hashmapin mittauksia. Mittausten kokonaisuuteen liittyy tiiviisti myös hyppyoliot, jotka tallentavat mittauksissa tapahtuneita hyppyjä. Yksi hyppyolio tietää hypyn alku- ja loppumittauksen, sekä hypyn pituuden.
	 Ajan kääntäminen tapahtuu erillisessä luokassa, joka toimii ajan kääntämisen palveluluokkana.

