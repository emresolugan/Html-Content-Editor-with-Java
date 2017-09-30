# About Project

It's a html content editor that is written in Java language. Program aims to read data from input.txt and translate text to html, after that 
create an html file with this data. 

***header***                          =    <h></h>
\n Paragraf bitimi                    =    </p>
_text_                                =    <em></em>
*text*                                =    <strong></strong>
![resim alternatif metini](resim url) =    <img src...>
[link metni](link url)                =    <a href...>

Example text:

***Flaş haber!***
İzmir’e gelen bir turist _Saat Kulesi_’nin önününde fotoğraf çektirmedi![a][][a]()![Saat Kulesi
Fotoğrafı](http://site.com/kule.jpg)
Turist *İzmir _Esnafı_’ında* şaşkınlık *[yarattı!](http://www.reklam.com/en-yaratici-reklamlar)*
Uygulama bunu aşağıdaki gibi bir HTML’e çevirmelidir.

Program translates this to output.html

Output.html

<p><h1>Flaş haber</h1></p>
<p>İzmir’e gelen bir turist <em>Saat Kulesi</em>’nin önünde fotoğraf çektirmedi!a][][a][()<img
src=”http://site.com/kule.jpg” alt=”Saat Kulesi Fotoğrafı”></p>
<p>Turist <strong>İzmir <em>Esnafı</em>’nda</strong> şaşkınlık <strong><a
href=”http://www.reklam.com/en-yaratici-reklamlar”>yarattı!</a></strong></p>
