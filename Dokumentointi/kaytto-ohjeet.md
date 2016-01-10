#Käyttöohjeet:

Ohjelmaa käytetään apuna mittaustulosten analysoimiseen, ja erilaisten tiivistelmien tekemiseen.

Avaa ohjelma seuraavasti:
- Mene projektin juuressa olevaan PIAQparser -kansioon.
- Aja tiedosto antamalla komentoriviltä käsky java -jar PIAQparser-1.0-SNAPSHOT.jar
- Ohjelman tulisi aueta.
- HUOM! käytä nimenomaan projektin kansiossa PIAQparser olevaa jar tiedostoa, äläkä target -kansiossa olevaa. Näin vältyt tiedostopolun virheellisyydeltä!

Avaa haluttu data seuraavasti:
- Paina browse -nappia, ja valitse haluttu tiedosto (muotoa .xml tai .csv).
- Muista että mittausten pitää olla tiettyä standardia; testitiedostoja löytyy polusta: PIAQparser/PIAQparser/src/main/resources
- HUOM! aitoja tiedostoja resources -hakemistossa on CSVtesti -tiedosto, joka on tarkoitettu lähinnä testaamiseen. Muut tiedostot ovat joko aitoja mittauksia, tai imitoivat sellaisia.
- Mikäli valitsit väärän tiedoston, voit aina valita uuden tiedoston valitsemalla taas browse -napin ja valitsemalla oikean tiedoston.

Tiedostosta saa halutut arvot syöttämällä ne kenttiin seuraavasti:
- Syötä ensin arvot "Use data from" -kenttiin. 
- HUOM! Jos syötät päivämäärän, joka ylittää mittauksessa olevat rajat, niin ohjelma tekee automaattisesti pisimmän mahdollisen aikavälin.
- Valitse mitkä mittausarvot haluat talteen. Nämä on määritelty "values" -otsikon alapuolella.
- Valitse minkä pituiset mittausvälit sinua kiinnostavat otsikon "Data for every" alapuolelta.
- HUOM! jos jätät valinnan tyhjäksi, ohjelma tekee tiedoston automaattisesti samalla mittausvälillä kuin mitä alkuperäisessä tiedostossa oli ollut.
- Painamalla "Make a new Excel(XML)" -nappia ohjelma tekee uuden tiedoston projektin juureen yllämainittuun resources -hakemistoon.
-Ohjelma avaa uuden ikkunan, jossa se ilmoittaa uuden tiedoston luomisen onnistumisesta. Mikäli uutta ikkunaa ei ilmesty, tarkista punaisella kirjoitetut virheviestit ohjelmasta ja toimi niiden mukaisesti.
- Uudessa ikkunassa painamalla "Ok!" -nappia, pääset takaisin ohjelmaan mikäli haluat tehdä lisää tiedostoja. Painamalla ruksia ohjelma sulkeutuu kokonaan.

