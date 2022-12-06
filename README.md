# TÝMOVÁNÍČKO

![Týmováníčko](https://gitlab.com/FIS-VSE/4IT115/2022ZS/st1100/kafj03/smudlove-tymova-prace/-/wikis/uploads/fc7558ab704c385325015b38f549eb1d/T%C3%BDmov%C3%A1n%C3%AD%C4%8Dko.png)

## Tým

- Magdalena Hájková
- Trong Dat Luu
- Jakub Kafka
- Adam Schindler
- Hana Žahourová

## Anotace aplikace

Aplikace **TÝMOVÁNÍČKO** slouží pro správu a organizaci sportovního týmu. Členové týmu mají přístup například k týmové nástěnce, týmovému chatu, jednotlivým událostem. V chatech se mohou domlouvat na všem, co se jejich týmu nebo konkrétní události týká, na událostech mohou vyjadřovat svou ne/účast.

## Funkcionalita

ve formátu user stories

- Jako _trenér sportovního týmu_ chci _spravovat tým_, aby _tým mohl být produktivní. Byl schopný se scházet, byla viditelná týmová docházka a týmová komunikace. Zároveň chci mít možnost s týmem sdílet různé soubory, fotky, obrázky, odkazy a ankety_.
- Jako _kapitán týmu_ chci _organizovat tým_, abych _se mohl starat o konkrétní, mě přiřazenou, skupinu. Dohlížet na to že v týmu komunikace funguje a všichni členi se jí účastní_.
- Jako _člen týmu_ se chci _účastnit komunikace v týmu_, abych _měl přehled o všem, co se v týmu děje. Mohl komunikovat s ostatními členy, kapitány i trenéry, abych mohl vyjadřovat svou ne/účast na jednotlivých událostech a zdůvodňovat ji_.

## Návrh aplikace a UML modely

Návrh se skládá z

- UML modelů
    - diagram případů užití (včetně slovního popisu dle MMSP)
    - další UML modely za každého člena týmu (individuální práce)

```plantuml
!theme cerulean

left to right direction
:Nový uživatel: as newUser
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
newUser -[#black,thickness=2]-> createTeam
newUser -[#black,thickness=2]-> joinTeam
newUser -[#black,thickness=2]-> login

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

class "Člen týmu" {
 +jméno
+email
-heslo
 void prihlaseni()
void potvrdit()
void komunikovat()
}
class "Trenér týmu"{
  +jméno
+email
-heslo
void spravovat_tym()
 void sdilet()
 void prihlaseni()
void potvrdit()
}
class "Kapitán týmu"{
    +jméno
+email
-heslo
   void prihlaseni()
void potvrdit()
}
```

- návrhu úložiště
    - soubory? případně v jakém formátu a v jaké struktuře
    - databáze? jaký typ a v jaké struktuře

Pro naši aplikaci budem využívat strukturu souborů s JSON soubory. Budeme mít jednotlivé JSON soubory, kde každý bude obsahovat všechny informace k danému tématu.

- Uživatel (obsahuje všechny základní údaje všech registrovaných uživatelů)
- Tým (obsahuje základní data o všech týmech)
- Událost (obsahuje základní data všech vytvořených událostí)

## Prototyp aplikace

[mobile](https://www.figma.com/proto/SsDWKeWOHXFL7TailRt2EH/T%C3%9DMOV%C3%81N%C3%8D%C4%8CKO?node-id=17%3A663&scaling=scale-down&page-id=0%3A1&starting-point-node-id=0%3A3) [desktop](https://www.figma.com/proto/e01XmYcBzoZowpxB7YSI4N/desktop?node-id=4%3A15&scaling=scale-down&page-id=0%3A1&starting-point-node-id=1%3A2)

Prototyp se může vytvořit online na https://www.invisionapp.com/, https://www.figma.com/, nebo v desktopové aplikaci Axure RP.

Uveďte sem

- odkaz na prototyp online, nebo wiki stránku s obrázky
- popis výsledků testování prototypu

## Testovací případy

![Testovací\_případy](https://gitlab.com/FIS-VSE/4IT115/2022ZS/st1100/kafj03/smudlove-tymova-prace/-/wikis/uploads/0efb767dee9a96ca4041e3c729f998e1/Testovac%C3%AD_p%C5%99%C3%ADpady.png)

## Projektový board a workflow

- odkaz na projektový issue board se vytvořenými issue
    - issue jsou označené štítky podle typu, rozpracovanosti a dalších...
    - každé issue má přiřazenou odpovědnou osobu
- popis stadií rozpracovanosti, kterému odpovídají štítky
- strategie pro větvení