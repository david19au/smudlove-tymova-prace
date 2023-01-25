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
Uzivatel<|-- SeznamUzivatelu : obsahuje
KategorieUzivatele<|-- Uzivatel 
Tym<|-- Uzivatel
Chat<|--Tym

class "Uzivatel" {
 +id: string
+jmeno: string
+prijmeni: string
+tym: tym
-heslo: string
+boolean prihlaseni()
+void potvrdit()
+boolean chat()
}
class "SeznamUzivatelu" {
-uzivatel: SeznamUzivatelu
+void zalozitUzivatel(uzivatel : Uzivatel)
+void upravitUzivatel(uzivatel : Uzivatel)
+uzivatel zobrazUzivatel(uzivatel : Uzivatel)
+collection zobrazSeznamUzivatel()

}
class "KategorieUzivatele"{
+nazev: string
+vlastnosti: string

}
class "Tym" {
+nazev: string
+pocetClenu: int

+void pridatClena(uzivatel : Uzivatel)
+void odebratClena(uzivatel : Uzivatel)
+collection zobrazSeznamClenu()
}

class "Chat" {
+zprava: string
+boolean odeslatZpravu()

}
}
package "Main" {
class "Start" {
+main (args[] : String)():void

}
}

package "uzivatelskeRozhrani" {
class "Menu" <<ui>>  {

}
class "HlavniObrazovka" <<ui>> {
}
class " ZalozeniUzivatele" <<ui>> {}
class "Hledat" <<ui>>  {}
class "Kalendar" <<ui>> {}
class "Chatovani" <<ui>> {}

class "ZalozkaUzivatele" <<ui>> {}
class  "Nastaveni" <<ui>> {} 
}
```
- další UML modely
  - Diagram aktivit (activity diagram)
  - Sekvenční diagram (sequence diagram)
  - Stavový diagram (state machine diagram)
```plantuml
scale 300 width
state Přihlásení_zaregistrovaného_uživatele {
[*] --> start_aplikace
start_aplikace : uživatelské údaje

start_aplikace --> [*] : abbort

start_aplikace -> zadej_email
zadej_email : emailová adresa

zadej_email --> zadej_heslo : proced
zadej_heslo : heslo uživatele

zadej_heslo --> zadej_email : špatné heslo

zadej_heslo --> [*]
}
```

  - Objektový diagram (object diagram)
  - Diagram spolupráce (collaboration diagram) / diagram komponent (component diagram)


### Návrh úložiště

- pro naši aplikaci budem využívat strukturu souborů s JSON soubory
- budeme mít jednotlivé JSON soubory, kde každý bude obsahovat všechny informace k danému tématu
    - Uživatel (obsahuje všechny základní údaje všech registrovaných uživatelů)
    - Tým (obsahuje základní data o všech týmech)
    - Událost (obsahuje základní data všech vytvořených událostí)

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

| **Kroky**            | **Akce testera**                                             | **Reakce systému**                         | **Výsledek (OK/error)** |
|----------------------|--------------------------------------------------------------|--------------------------------------------|-------------------------|
| **1.**               | U dané události kliknu na zúčastním se/nezúčastním se/nevím. | Proběhne zaznamenání účasti                | -                       |
| **2.**               | Žádná.                                                       | Uživatel se zobrazí u daného stavu účasti. | -                       |
| **Celkový výsledek** | -                                                            |                                            |                         |

### 2. testovací případ

* **Název testovacího případu:** Registrace
* **Popis:** Tento případ testuje registrování nového uživatele, kdy očekávaným výsledkem je, že se do databáze přidá
  nový záznam a aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel nesmí existovat.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měli vést k úspěšnému registrování uživatele. Dané akce
  zahrnují zobrazení okna pro založení záznamu, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí zadané údaje a
  aplikace se pokusí o přidání nového uživatele.

| **Kroky**            | **Akce testera**                               | **Reakce systému**                                                                             | **Výsledek (OK/error)** |
|----------------------|------------------------------------------------|------------------------------------------------------------------------------------------------|-------------------------|
| **1.**               | Kliknu na tlačítko "Registrace".               | Proběhne přesměrování na registrační formulář.                                                 | -                       |
| **2.**               | Vyplním registrační formulář.                  | Žádná.                                                                                         | -                       |
| **3.**               | Odešlu formulář pomocí tlačítka "Registrovat". | Uživatel dostane potvrzení o registraci.                                                       | -                       |
| **4.**               | Žádná.                                         | Uživatel se bude moci přihlásit do aplikaci pomocí emailu a hesla, které zadal při registraci. | -                       |
| **Celkový výsledek** | -                                              |                                                                                                |                         |

### 3. testovací případ

* **Název testovacího případu:** Přihlášení
* **Popis:** Tento případ testuje přihlášení uživatele, kdy očekávaným výsledkem je, že se v databázi zkontroluje záznam
  a aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel musí existovat.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měli vést k úspěšnému přihlášení uživatele. Dané akce
  zahrnují zobrazení okna pro zkontrolování záznamu, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí zadané
  údaje a aplikace se pokusí o přihlášení uživatele.

| **Kroky**            | **Akce testera**                   | **Reakce systému**                       | **Výsledek (OK/error)** |
|----------------------|------------------------------------|------------------------------------------|-------------------------|
| **1.**               | Vyplním přihlašovací formulář.     | Žádná.                                   | -                       |
| **2.**               | Kliknu na tlačítko "Přihlásit se". | Uživatel dostane potvrzení o přihlášení. | -                       |
| **3.**               | Žádná.                             | Uživateli se zpřístupní funkce aplikace. | -                       |
| **Celkový výsledek** | -                                  |                                          |                         |

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
| **1.**               | V menu kliknu na ikonu chatu.          | Uživateli se zobrazí chat. | -                       |
| **2.**               | Napíši zprávu do textového pole chatu. | Žádná.                     | -                       |
| **3.**               | Kliknu na tlačítko "Odeslat".          | Zpráva se zobrazí v chatu. | -                       |
| **Celkový výsledek** | -                                      |                            |                         |

### 5. testovací případ

* **Název testovacího případu:** Postnutí - fotky a/nebo videa
* **Popis:** Tento případ testuje přidání příspěvku na zeď týmu, kdy očekávaným výsledkem je, že se na zeď přidá nový
  příspěvek a aplikace zobrazí výsledek této operace.
* **Vstupní podmínky:** Spuštěná aplikace, uživatel přihlášen pod svým uživ. jménem a heslem.
* **Poznámky:** V rámci tohoto testu jsou prováděny akce, jež by měly vést k úspěšnému přidání příspěvku na zeď týmu.
  Dané akce zahrnují zobrazení okna pro přidání záznamu, vyplnění vstupních polí a stisknutí tlačítka, jež potvrdí
  zadané údaje a aplikace se pokusí o přidání nového příspěvku.

| **Kroky**            | **Akce testera**              | **Reakce systému**          | **Výsledek (OK/error)** |
|----------------------|-------------------------------|-----------------------------|-------------------------|
| **1.**               | V menu kliknu na ikonu zdi.   | Uživateli se zobrazí zeď.   | -                       |
| **2.**               | Napíši a/nebo přidám přílohu. | Žádná.                      | -                       |
| **3.**               | Kliknu na tlačítko "Odeslat". | Příspěvek se objeví na zdi. | -                       |
| **Celkový výsledek** | -                             |                             |                         |

## Projektový board a workflow

[Odkaz na projektový issue board s vytvořenými issue](https://gitlab.com/FIS-VSE/4IT115/2022ZS/st1100/kafj03/smudlove-tymova-prace/-/boards/5068701)