# TÝMOVÁNÍČKO - Tým. Snadně.

![Týmováníčko](https://gitlab.com/FIS-VSE/4IT115/2022ZS/st1100/kafj03/smudlove-tymova-prace/-/wikis/uploads/fc7558ab704c385325015b38f549eb1d/T%C3%BDmov%C3%A1n%C3%AD%C4%8Dko.png)

## Tým

- 🐸 Magdalena Hájková (hajm17)
- 🐻 Trong Dat Luu (luut02)
- 🪲 Jakub Kafka (kafj03)
- 🐧 Adam Schindler (scha28)
- 🐼 Hana Žahourová (zahh00)

## Anotace aplikace

Aplikace **TÝMOVÁNÍČKO** slouží pro správu a organizaci sportovního týmu. Členové týmu mají přístup například k týmové
nástěnce, týmovému chatu, jednotlivým událostem. V chatech se mohou domlouvat na všem, co se jejich týmu nebo konkrétní
události týká, na událostech mohou vyjadřovat svou ne/účast.

## Seznam úkolů a jejich přiřazení členům týmu

* **Funkcionalita:**
    * 🪲 Jakub Kafka (kafj03)
    * 🐧 Adam Schindler (scha28)
* **Use Case diagram:**
    * 🐻 Trong Dat Luu (luut02)
* **Class Diagram:**
    * 🐼 Hana Žahourová (zahh00)
* **Specifikace případů užití:**
    * 🐧 Adam Schindler (scha28)
* **Prototyp aplikace:**
    * 🐸 Magdalena Hájková (hajm17)
* **Návrh uložiště:**
    * 🐸 Magdalena Hájková (hajm17)
    * 🐻 Trong Dat Luu (luut02)
    * 🪲 Jakub Kafka (kafj03)
    * 🐧 Adam Schindler (scha28)
    * 🐼 Hana Žahourová (zahh00)
* **Testovací případy:**
    * 🪲 Jakub Kafka (kafj03)

* **

* **Implementace chatu:**
    * 🐻 Trong Dat Luu (luut02)
    * 🪲 Jakub Kafka (kafj03)
    * 🐼 Hana Žahourová (zahh00)
* **Implementace uživatelů, správy registrace:**
    * 🐻 Trong Dat Luu (luut02)
    * 🪲 Jakub Kafka (kafj03)
    * 🐧 Adam Schindler (scha28)
* **Implementace seznamu událostí:**
    * 🐸 Magdalena Hájková (hajm17)
    * 🐻 Trong Dat Luu (luut02)
    * 🪲 Jakub Kafka (kafj03)
    * 🐧 Adam Schindler (scha28)

* **

* **Grafika a struktura aplikace:**
    * 🪲 Jakub Kafka (kafj03)
* **Testovací třídy:**
    * 🐼 Hana Žahourová (zahh00)

## Funkcionalita

- Jako **_trenér sportovního týmu_** chci _spravovat tým_, aby _tým mohl být produktivní. Byl schopný se scházet, byla
  viditelná týmová docházka a týmová komunikace. Zároveň chci mít možnost s týmem sdílet různé soubory, fotky, obrázky,
  odkazy a ankety_.
- Jako **_kapitán týmu_** chci _organizovat tým_, abych _se mohl starat o konkrétní, mě přiřazenou, skupinu. Dohlížet na
  to že v týmu komunikace funguje a všichni členi se jí účastní_.
- Jako **_člen týmu_** se chci _účastnit komunikace v týmu_, abych _měl přehled o všem, co se v týmu děje. Mohl
  komunikovat s ostatními členy, kapitány i trenéry, abych mohl vyjadřovat svou ne/účast na jednotlivých událostech a
  zdůvodňovat ji_.

## Návrh aplikace

### Specifikace případů užití

[Specifikace_případů_užití.xlsx](https://gitlab.com/FIS-VSE/4IT115/2022ZS/st1100/kafj03/smudlove-tymova-prace/-/wikis/uploads/944150c28beb93705385cd4c8b04a191/Specifikace_p%C5%99%C3%ADpad%C5%AF_u%C5%BEit%C3%AD.xlsx)

### UML modely:

- diagram případů užití

```plantuml
!theme cerulean

left to right direction
:Nový uživatel: as newUser
:Zaregistrovaný uživatel: as registeredUser
:Člen týmu: as teamMember
:Kapitán: as captain
:Trenér: as trainer


usecase "Registrace" as register
usecase "Vytvoření nového týmu" as createTeam
usecase "Přidání se k existujícímu týmu" as joinTeam
usecase "Přihlášení se do aplikace" as login
usecase "Zadávání reakce na událost" as rsvp
usecase "Posílání zpráv v chatu týmu nebo události" as chat
usecase "Vytvoření události" as createEvent
usecase "Upravení týmu" as modifyTeamMembers
usecase "Upravení události" as modifyEvent
usecase "Jmenování člena týmu kapitánem" as delegateCaptain

newUser -[#black,thickness=2]-> register
newUser -[#black,thickness=2]-|> registeredUser

registeredUser -[#black,thickness=2]-|> teamMember
registeredUser -[#black,thickness=2]-|> captain
registeredUser -[#black,thickness=2]-|> trainer
registeredUser -[#black,thickness=2]-> createTeam
registeredUser -[#black,thickness=2]-> joinTeam

teamMember --> login
teamMember --> rsvp
teamMember --> chat

captain -[#green,thickness=2]-> login
captain -[#green,thickness=2]-> rsvp
captain -[#green,thickness=2]-> chat
captain -[#green,thickness=2]-> createEvent
captain -[#green,thickness=2]-> modifyTeamMembers
captain -[#green,thickness=2]-> modifyEvent

trainer -[#red,thickness=2]-> login
trainer -[#red,thickness=2]-> rsvp
trainer -[#red,thickness=2]-> chat
trainer -[#red,thickness=2]-> createEvent
trainer -[#red,thickness=2]-> modifyTeamMembers
trainer -[#red,thickness=2]-> modifyEvent
trainer -[#red,thickness=2]-> delegateCaptain
```

- diagram tříd na designové úrovni

```plantuml

package "Logika" {
Uzivatel *-- SeznamUzivatelu 
Udalost *-- SpravaUdalosti
Chat	o-- ChatLog : obsahuje
Tymovanicko<|-- Uzivatel
Tymovanicko <|-- Udalost
Tymovanicko <|-- Chat




class "Uzivatel" {
+email: string
+krestniJmeno: string
+prijmeni: string
-heslo: string
+role: string
+getKrestniJmeno() : string
+setKrestniJmeno() : void
+getPrijmeni() : string
+setPrijmeni() : void
+getEmail() : String
+setEmail() : void
+getHeslo() : string
+getRole() : string
+setRole() : void
}
class "SeznamUzivatelu" {
-uzivatel: SeznamUzivatelu
+void zalozitUzivatel(uzivatel : Uzivatel)
+void upravitUzivatel(uzivatel : Uzivatel)
+uzivatel zobrazUzivatel(uzivatel : Uzivatel)
+collection zobrazSeznamUzivatel()

}


class "Chat" {
-chatLog: chatLog
+odeslatZpravu() : void
+pridatZpravu() : void
-ulozitZpravyDoJSON() : void
+nactiZpravyZJSON() : void

}

class "ChatLog" {
+zpravy: ArrayList
+ getZpravy() : ArrayList<String>

}

class "Tymovanicko" {
-seznamUzivatelu: seznamUzivatelu
-uzivatel: uzivatel
-chat: chat
-chatLog: chatLog
-id: string
-spravaUdalosti: spravaUdalosti
-udalost: udalost
-udalosti: List<Udalost>
-zalozTymovanicko() : void
+getSeznamUzivatelu() : SeznamUzivatelu
+setSeznamUzivatelu(SeznamUzivatelu seznamUzivatelu) : void
+getChat() : Chat
+setChat(Chat chat) : void
+getChatLog() : chatLog
+setChatLog(ChatLog chatLog) : void
+getId() : string
+setItd(String id) : void
+getJmeno(String stringEmailu) : string
+getSpravaUdalosti() : spravaUdalosti
+getUdalosti() : List<Udalost>
+setUdalosti(List<Udalost> udalosti) : void
}

class "Udalost" {
-jmenoUdalosti : string
-datumUdalosti : string
-lokaceUdalosti : string
-seznamJde : List<String>
-seznamNejde : List<String>
+ getJmenoUdalosti() : string
+getDatumUdalosti() : string
+setDatumUdalosti (Date datumUdalosti) : void
+getLokaceUdalosti : string
+getSeznamJde : List<String>
+setSeznamJde(List<String> seznamJde) : void
+getSeznamNejde() : List<String>
+setSeznamNejde(List<String> seznamNejde) : void

}

class "SpravaUdalosti" {
- gson : gson
- udalosti : ArrayList<Udalost>
+ vytvorUdalost(String jmenoUdalosti, Date datumUdalosti, String lokaceUdalosti) : void
+ smazUdalost (String jmenoUdalosti) : void
+ pridatUdalost (Udalost udalost) : void
+ getUdalosti() : list<Udalost>
+ setUdalosti(ArrayList<Udalost> udalosti) : void
+ zmenRSVP (Udalost jmenoUdalosti, String jmenoClena, String status) : void
+ ulozUdalostiDoJSON() : void
+ getJde(Udalost jmenoUdalosti) : collection<String>
+getNejde(Udalost jmenoUdalosti) : collection<string>

}




package "Main" {
HomeController <|-- RegisterController
HomeController <|-- LoginController
HomeController <|-- EventsController
HomeController <|-- ChatController
HomeController <|-- ProfileSettings
ProfileSettings <|-- ChangePassword

EventsController <|-- CreateEventController
class "Start" {
+{static} main (String[] args) : void
+ start(Stage stage) : void

}
class "ChangePassword" <<ui>>  {
-home : Label
-zmenHeslo : Button
-zpet : ImageView
-stage : Stage
-scene : Scene
-udalosti : ImageView
- chat : ImageView
- stareHeslo : PasswordField
- noveHeslo : PasswordField
- noveHesloZnovu : PasswordField
-zpracujZmenuHesla(ActionEvent actionEvent) : void
-zpracujZpatky(MouseEvent mouseEvent) : void
-ztmavni (MouseEvent mouseEvent) : void
-zesvetlej(MouseEvent mouseEvent) : void
-ztmavniKalendar(MouseEvent mouseEvent) : void
-zesvetlejKalendar(MouseEvent mouseEvent) : void
-ztmavniChat(MouseEvent mouseEvent) : void
-zesvetlejChat(MouseEvent mouseEvent) : void
-zpracujNaChat(MouseEvent mouseEvent) : void
-zpracujNaUdalost(MouseEvent mouseEvent) : void
-zpracujNaHome(MouseEvent mouseEvent) : void
-ztmavniHome(MouseEvent mouseEvent) : void
-zesvetlejHome(MouseEvent mouseEvent) : void


}
class "ChatController" <<ui>> {
-vstupZprava : textField
-home : label
-zpravyChatu : textArea
-udalosti : imageView
-nastaveni : imageView
-stage : stage
-scene : scene
-zpet : imageView
+nactiStareZpravy() : void
-zpracujPoslani(Actionevent actionEvent) : void
-ztmavniKalendar(MouseEvent mouseEvent) : void
-zesvetlejKalendar(MouseEvent mouseEvent) : void
-zpracujNaNastaveni(MouseEvent mouseEvent) : void
-ztmavniNastaveni(MouseEvent mouseEvent) : void
-zesvetlejNastaveni(MouseEvent mouseEvent) : void
-zpracujZpatky(MouseEvent mouseEvent) : void
-ztmavni(MouseEvent mouseEvent) : void
-zesvetlej(MouseEvent mouseEvent) : void
-zpracujNaUdalosti(MouseEvent mouseEvent) : void
-zpracujNaHome(MouseEvent mouseEvent) : void
-ztmavniHome(MouseEvent mouseEvent) : void
-zesvetlejHome(MouseEvent mouseEvent) : void

}
class "CreateEventController" <<ui>> {
-jmenoUdalosti : textField
-datumUdalosti : datePicker
-lokaceUdalosti : textField
-nastaveni : imageView
-chat : imageView
-zpet : imageView
-stage : stage
-scene: scene
-home: label
-pattern : string
-dateTimeFormatter : DateTimeFormatter
-zpracujNaChat(MouseEvent mouseEvent) : void
-ztmavniChat(MouseEvent mouseEvent) : void
-zesvetlejChat(MouseEvent mouseEvent) : void
-ztmavniNatstaveni(MouseEvent mouseEvent) : void
-zesvetlejNastaveni(MouseEvent mouseEvent) : void
-zpracujNaNastaveni(MouseEvent mouseEvent) : void
+zpracujZpatky(MouseEvent mouseEvent) : void
+ztmavni(MouseEvent mouseEvent) : void
+zesvetlej(MouseEvent mouseEvent) : void
-vytvorUdalost(ActionEvent actionEvent) : void
-zpracujNaHome(MouseEvent mouseEvent) : void
-ztmavniHome(MouseEvent mouseEvent) : void
-zesvetlejHome(MouseEvent mouseEvent) : void
-
}
class "EventsController" <<ui>>  {
-home : label
-vytvorUdalost : button
-panelUdalosti : listView<Udalost>
-nastaveni : imageView
-chat : imageView
- zpet : imageView
-stage : stage
-scene : scene
-initialize() : void
-naplneniPaneluUdalosti() : void
-zpracujNaChat(MouseEvent mouseEvent) : void
-ztmavniChat(MouseEvent mouseEvent) : void
-zesvetlejChat(MouseEvent mouseEvent) : void
-ztmavniNastaveni(MouseEvent mouseEvent) : void
-zesvetlejNastaveni(MouseEvent mouseEvent) : void
-zpracujNaNastaveni(MouseEvent mouseEvent) : void
+zpracujZpatky(MouseEvent mouseEvent) : void
+ztmavni(MouseEvent mouseEvent) : void
+zesvetlej(MouseEvent mouseEvent) : void
-zpracujNaVytvoreniUdalosti(ActionEvent actionEvent) : void
-klikPanelUdalosti(MouseEvent mouseEvent) : void
-zpracujNaHome(MouseEvent mouseEvent) : void
-ztmavniHome(MouseEvent mouseEvent) : void
-zesvetlejHome(MouseEvent mouseEvent) : void



}
class "HomeController" <<ui>> {
-udalosti : imageView
-chat : imageView
-nastaveni : imageView
- stage : stage
-scene : scene
-panelClenu : listView
+initialize() : void
-ztmavniKalendar(MouseEvent mouseEvent) : void
-zesvetlejKalendar(MouseEvent mouseEvent) : void
-ztmavniChat(MouseEvent mouseEvent) : void
-zesvetlejChat(MouseEvent mouseEvent) : void
-ztmavniNastaveni(MouseEvent mouseEvent) : void
-zesvetlejNastaveni(MouseEvent mouseEvent) : void
-zpracujNaNastaveni(MouseEvent mouseEvent) : void
-zpracujNaChat(MouseEvent mouseEvent) : void
-zpracujNaUdalosti(MouseEvent mouseEvent) : void
-naplneniPaneluClenu() : void
+klikPanelClenu(MouseEvent mouseEvent) : void

}
class "LoginController" <<ui>> {
-prihlasit : button
-zaregistrovat : button
-stage : stage
-scene: scene
-email : textField
-password : passwordField
-zpracujPrihlaseni(ActionEvent actionEvent) : void
-zpracujNaZaregistrovani (ActionEvent actionEvent) : void
}

class "ProfileSettings" <<ui>> {
-zmenTym : button
-zmenHeslo : button
-odhlas : button
-uloz : button
-udalosti : imageView
-chat : imageView
-stage : stage
-scene : scene
-zpet : imageView
-jmeno : textField
-prijmeni : textField
-email : textField
-heslo : passwordField
-home : label
-role : textField
-initialize() : void
-zpracujUlozeni(ActionEvent actionEvent) : void
-zpracujOdhlaseni(ActionEvent actionEvent) : void
-zpracujNaZmenuHesla(ActionEvent actionEvent) : void
-ztmavniKalendar(MouseEvent mouseEvent) : void
-zesvetlejKalendar(MouseEvent mouseEvent) : void
-ztmavniChat(MouseEvent mouseEvent) : void
-zesvetlejChat(MouseEvent mouseEvent) : void
-zpracujNaChat(MouseEvent mouseEvent) : void
-zpracujZpatky(MouseEvent mouseEvent) : void
+ztmavni(MouseEvent mouseEvent) : void
+zesvetlej(MouseEvent mouseEvent) : void
-zpracujNaUdalosti(MouseEvent mouseEvent) : void
-zpracujNaHome(MouseEvent mouseEvent) : void
-ztmavniHome(MouseEvent mouseEvent) : void
-zesvetlejHome(MouseEvent mouseEvent) : void

}
class  "RegisterController" <<ui>> {
-zaregistruj : button
-zpet : ImageView
-stage : stage
-scene: scene
-email : textField
-jmeno : textField
-heslo : passwordField
-potvrzeniHesla : passwordField
- prijmeni : textField
-zpracujZaregistrovani(ActionEvent actionEvent) : void
-ztmavni(MouseEvent mouseEvent) : void
-zesvetlej(MouseEvent mouseEvent) : void
-zpracujZpatky(MouseEvent mouseEvent) : void

} 
}

```
- další UML modely
  - **Diagram aktivit (activity diagram)** -> *DIAGRAM CHOVÁNÍ*
    - 🐻 Trong Dat Luu (luut02)
    - Diagram aktivit se používá pro popis dynamických aspektů systému. Znázorňuje tok řízení z aktivity do aktivity. Diagram aktivit se soustřeďuje spíše na proces výpočtu než na objekty účastnící se výpočtu.
```plantuml
  (*) --> if "Login / registrace" then

   -right-> [login] "zobrazení login formuláře" 

else

-left->[registrace] "vyplní registrační formulář"



-->"aplikace zvaliduje zadané údaje"
--> if "Validní údaje?" then
-->[ne]"uživatel není úspěšně zaregistrován"
--> "vyplní registrační formulář"
else
-->[ano] "zobrazí se: Úspěšně jste se zaregistrovali!"



-->"údaje se uloží do jsonu"
-->"zobrazení login formuláře"





-->"zadá přihlašovací údaje"



-->"validace přihlašovacích údajů"

--> if "Validní login?" then
-right-> [ne] "zobrazí se: Účet se zadaným emailem neexistuje / Heslo není správně"
else
-left->[ano] "uživatel se úspěšně přihlásí"


"zobrazí se: Účet se zadaným emailem neexistuje / Heslo není správně"--> === S2 ===



"uživatel se úspěšně přihlásí" --> === S2 ===
--> (*)
endif


endif
 ```
  - **Sekvenční diagram (sequence diagram)** -> *DIAGRAM INTERAKCE*
    - 🐧 Adam Schindler (scha28)
    - Sekvenční diagram se používá v případech, kde jsou důležité časové souvislosti interakcí, ovšem nevidíme v něm zobrazené vztahy mezi objekty. Objekty si mohou posílat zprávy.
    - Sekvenční diagram zobrazuje časovou posloupnost
   
```plantuml
    actor Uživatel #skyblue

participant Registrace
participant Login
participant Validace
participant JSON


Uživatel -> Registrace : kliknutí na tlačítko "zaregistrujte se"
Registrace --> Uživatel : zobrazení registračního formuláře
Uživatel -> Registrace : zadání údajů
Registrace -> Validace : validace zadaných údajů
Validace -> JSON : zapsání uživatele do JSONu
Validace -->Registrace : úspěšná registrace
Registrace --> Uživatel: přesměrování na login
Uživatel -> Login : zadání přihlašovacích údajů
Login -> Validace : validace zadaných údajů (email, heslo)
Validace -> JSON : hledání zadané kombinace údajů
JSON --> Validace : zadané údaje nalezeny
Validace--> Login : úspěšné přihlášení
Login --> Uživatel : vstup do aplikace

```

    
  - **Stavový diagram (state machine diagram)** -> *DIAGRAM CHOVÁNÍ*
    - 🐼 Hana Žahourová (zahh00)
    - Stavový diagram obsahuje tzv. stavový stroj (state machine) -> vyjadřuje stavy určitého objektu a přechody mezi těmito stavy.
```plantuml
scale 300 width
state Přihlásení_zaregistrovaného_uživatele {
[*] --> start_aplikace
start_aplikace : uživatelské údaje
start_aplikace --> [*] : abbort
start_aplikace -> zadej_email

zadej_email : emailová adresa
zadej_email --> zadej_heslo : proceed

zadej_heslo : heslo uživatele
zadej_heslo --> zadej_email : špatné heslo
zadej_heslo --> [*]
}
```

  - **Objektový diagram (object diagram)** -> *STRUKTURNÍ DIAGRAM*
    - 🪲 Jakub Kafka (kafj03)
    - Diagram objektů ukazuje objekty a jejich vztahy v jistém časovém okamžiku.
```plantuml
scale 500 width
map Uživatel {
  krestniJmeno => Magdalena
  prijmeni => Hájková
  e-mail => hajkova.majda@gmail.com
}

map Uživatel2{
  krestniJmeno => Hana
  prijmeni => Žahourová
  e-mail => zahourova.hana@gmail.com
}

map Zpráva {
  text => No já teda nevím, no...
  text2 => Přijdu, asi pozdě, ale přijdu...
}

map Chat {
  timestamp => 25.01.2023, 15:21
  uzivatel => Magdalena Hájková
  text => No já teda nevím, no...
  timestamp2 => 25.01.2023, 15:22
  uzivatel2 => Hana Žahourová
  text2 => Přijdu, asi pozdě, ale přijdu...
}

class Chatlog

Uživatel2 --> Zpráva :poslat
Uživatel2 <-- Zpráva :obdržet
Zpráva --> Uživatel :obdržet 
Uživatel --> Zpráva :poslat
Chat <- Zpráva
Chat --> Chatlog

```
  - **Diagram komponent (component diagram)** -> *STRUKTURNÍ DIAGRAM*
    - 🐸 Magdalena Hájková (hajm17)
    - Diagram komponent ukazuje závislost mezi SW komponentami a jejich implementací. Komponenta v UML reprezentuje modulární část systému, která zapouzdřuje svůj obsah a jejíž projev je nahraditelný v jejím okolí
    - Chování je plně definováno jejími poskytovanými a požadovanými rozhraními
```plantuml
scale 300 width
[Chatlog]
[Uživatel]
[Chat]



Chatlog <- podrobnosti_zprávy
Chat --> podrobnosti_zprávy
Uživatel --> Chat

```

### Návrh úložiště

- pro naši aplikaci budem využívat strukturu souborů s JSON soubory
- budeme mít jednotlivé JSON soubory, kde každý bude obsahovat všechny informace k danému tématu
    - Uživatel (obsahuje všechny základní údaje všech registrovaných uživatelů)
    - Chat (obsahuje zprávy v chatu)
    - Událost (obsahuje základní data všech vytvořených událostí)

* Struktura souboru chat.json

```
{
  "zpravy": [
    "[25.01.2023, 15:16] Adam Schindler: Tak co? Sejdem se zejtra na obhajobě??",
    "[25.01.2023, 15:17] Jakub Kafka: Jasně, počítám s tím.",
    "[25.01.2023, 15:21] Magdalena Hájková: No já teda nevím, no...",
    "[25.01.2023, 15:22] Hana Žahourová: Přijdu, asi pozdě, ale přijdu...",
    "[25.01.2023, 15:25] Trong Dat Luu: Klasická Hanka"
  ]
}
```  

* Struktura souboru udalosti.json

```
[
  {
    "jmenoUdalosti": "Obhajoba 4IT115",
    "datumUdalosti": "26.01.2023",
    "lokaceUdalosti": "VŠE Žižkov",
    "seznamJde": [
      "Adam Schindler",
      "Jakub Kafka",
      "Magdalena Hájková",
      "Hana Žahourová"
    ],
    "seznamNejde": [
    "Trong Dat Luu"
    ]
  }
]
```

* Struktura souboru uzivatele.json

```
{
  "uzivatele": [
    {
      "email": "scha28@vse.cz",
      "krestniJmeno": "Adam",
      "prijmeni": "Schindler",
      "heslo": "$2a$10$vRsqrJmLUejEvJyPL.H0duFV6xC41NcWjh92OZ70PGK3wFp3nrLyC",
      "role": "Trenér"
    }
}
```

## Prototyp aplikace

Odkaz na prototyp
online: [návrh UI: desktop](https://www.figma.com/proto/e01XmYcBzoZowpxB7YSI4N/desktop?node-id=33%3A190&scaling=scale-down&page-id=0%3A1&starting-point-node-id=22%3A13)

## Testovací případy

### 1. testovací případ

* **Název testovacího případu:** Účast na událostech
* **Popis:** Tento případ testuje zaznamenání účasti, kdy očekávaným výsledkem je, že se do databáze přidá nový záznam a
  aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel přihlášen pod svým uživ. jménem a heslem.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měly vést k úspěšnému zaznamenání účasti na události.
  Dané akce zahrnují zobrazení okna pro přidání záznamu, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí
  zadané údaje a aplikace se pokusí o zaznamenání účasti.

| **Kroky**            | **Akce testera**                                       | **Reakce systému**                         | **Výsledek (OK/error)** |
|----------------------|--------------------------------------------------------|--------------------------------------------|-------------------------|
| **1.**               | U dané události kliknu na zúčastním se/nezúčastním se. | Proběhne zaznamenání účasti                | OK                      |
| **2.**               | Žádná.                                                 | Uživatel se zobrazí u daného stavu účasti. | OK                      |
| **Celkový výsledek** | OK                                                     |                                            |                         |

### 2. testovací případ

* **Název testovacího případu:** Registrace
* **Popis:** Tento případ testuje registrování nového uživatele, kdy očekávaným výsledkem je, že se do databáze přidá
  nový záznam a aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel nesmí existovat.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měli vést k úspěšnému registrování uživatele. Dané akce
  zahrnují zobrazení okna pro založení záznamu, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí zadané údaje a
  aplikace se pokusí o přidání nového uživatele.

| **Kroky**            | **Akce testera**                                    | **Reakce systému**                                                                             | **Výsledek (OK/error)** |
|----------------------|-----------------------------------------------------|------------------------------------------------------------------------------------------------|-------------------------|
| **1.**               | Kliknu na tlačítko "Zaregistrovat se".              | Proběhne přesměrování na registrační formulář.                                                 | OK                      |
| **2.**               | Vyplním registrační formulář.                       | Žádná.                                                                                         | OK                      |
| **3.**               | Odešlu formulář pomocí tlačítka "Zaregistrovat se". | Uživatel dostane potvrzení o registraci.                                                       | OK                      |
| **4.**               | Žádná.                                              | Uživatel se bude moci přihlásit do aplikaci pomocí emailu a hesla, které zadal při registraci. | OK                      |
| **Celkový výsledek** | OK                                                  |                                                                                                |                         |

### 3. testovací případ

* **Název testovacího případu:** Přihlášení
* **Popis:** Tento případ testuje přihlášení uživatele, kdy očekávaným výsledkem je, že se v databázi zkontroluje záznam
  a aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel musí existovat.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měli vést k úspěšnému přihlášení uživatele. Dané akce
  zahrnují zobrazení okna pro zkontrolování záznamu, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí zadané
  údaje a aplikace se pokusí o přihlášení uživatele.

| **Kroky**            | **Akce testera**                   | **Reakce systému**                                | **Výsledek (OK/error)** |
|----------------------|------------------------------------|---------------------------------------------------|-------------------------|
| **1.**               | Vyplním přihlašovací formulář.     | Žádná.                                            | OK                      |
| **2.**               | Kliknu na tlačítko "Přihlásit se". | Uživatel bude přesměrován na domovskou obrazovku. | OK                      |
| **3.**               | Žádná.                             | Uživateli se zpřístupní funkce aplikace.          | OK                      |
| **Celkový výsledek** | OK                                 |                                                   |                         |

### 4. testovací případ

* **Název testovacího případu:** Poslání zprávy
* **Popis:** Tento případ testuje poslání zprávy do chatu, kdy očekávaným výsledkem je, že se do chatu přidá nová zpráva
  a aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel přihlášen pod svým uživ. jménem a heslem.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měly vést k úspěšnému poslání zprávy do chatu týmu.
  Dané akce zahrnují zobrazení okna pro přidání záznamu, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí
  zadané údaje a aplikace se pokusí o poslání nové zprávy.

| **Kroky**            | **Akce testera**                       | **Reakce systému**         | **Výsledek (OK/error)** |
|----------------------|----------------------------------------|----------------------------|-------------------------|
| **1.**               | V menu kliknu na ikonu chatu.          | Uživateli se zobrazí chat. | OK                      |
| **2.**               | Napíši zprávu do textového pole chatu. | Žádná.                     | OK                      |
| **3.**               | Kliknu na tlačítko "Odeslat".          | Zpráva se zobrazí v chatu. | OK                      |
| **Celkový výsledek** | OK                                     |                            |                         |

### 5. testovací případ

* **Název testovacího případu:** Změna hesla
* **Popis:** Tento případ testuje změnu hesla, kdy očekávaným výsledkem je, že se změní heslo přihlášeného uživatele
   a aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel přihlášen pod svým uživ. jménem a heslem.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měly vést k úspěšné změně hesla.
  Dané akce zahrnují zobrazení okna pro změnu hesla, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí
  zadané údaje a aplikace se pokusí o změnu hesla.

| **Kroky**            | **Akce testera**                         | **Reakce systému**                                                                | **Výsledek (OK/error)** |
|----------------------|------------------------------------------|-----------------------------------------------------------------------------------|-------------------------|
| **1.**               | V menu kliknu na ikonu profile settings. | Uživateli se zobrazí profile settings.                                            | OK                      |
| **2.**               | Kliknu na tlačítko "Změna hesla".        | Uživateli se zobrazi okno pro změnu hesla.                                        | OK                      |
| **3.**               | Vyplním údaje.                           | Žádná.                                                                            | OK                      |
| **4.**               | Kliknu na tlačítko "Změnit".             | Uživateli se zobrazi hláška a pokud bylo vše zapsáno dobře, proběhne změna hesla. | OK                      |
| **Celkový výsledek** | OK                                       |                                                                                   |                         |

## Projektový board a workflow

[Odkaz na projektový issue board s vytvořenými issue](https://gitlab.com/FIS-VSE/4IT115/2022ZS/st1100/kafj03/smudlove-tymova-prace/-/boards/5068701)