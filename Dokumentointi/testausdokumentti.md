#Testausdokumentti

Automaatiotestauksen piiriin ei kuulu käyttöliittymä, tiedostonKirjoittaja XML eikä TiedostonLukijoiden osa koodista. TiedostonLukijoiden testauksen puute johtuu siitä, että yritys, jolle tein tätä apuohjelman raakaversioita, ei halua heidän mittaustiedostojaan julkiseen jakoon. Olen kuitenkin testannut lukijat käsin libreofficen avulla. Sen vuoksi Pit-raportissa on huono rivikattavuus ja mutanttien tappoprosentti.
Tiedostonkirjoittajan olen testannut myös käsin libreofficen funktioiden avulla. Näiden avulla olen myös tarkistanut että aikaleimojen aikavälit menevät oikein, ja myös satunaisista paikoista että keskiarvo menee oikein. 
Käyttöliittymän testaus on sujunut samalla, kun käyttöliittymässä kokeilee muiden toimintojen rajatapausten toimintaa. Myös erilaisilla testisyötteillä kokeileminen on kuulunut esimerkiksi ohjelman luku- ja kirjoitustehokkuuden testaamiseksi.

Tiedossa olevia bugeja on vain yksi, jonka korjaaminen ei luultavasti ole hankalaa, mutta aikateknisistä syistä siihen ei ole kiinnitetty huomiota. 
Bugi: jos browse -napin vieressä olevaan tekstikenttään kirjoittaa tiedostopolun, niin ohjelma ei tunnista sitä, vaan tiedosto tulee valita browse -napin kautta.
