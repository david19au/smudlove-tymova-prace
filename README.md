# Týmováníčko - Tým. Snadně.

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

-   UML modelů
    -   diagram případů užití (včetně slovního popisu dle MMSP)
    -   diagram tříd na designové úrovni
    -   další UML modely za každého člena týmu (individuální práce)
-   návrhu úložiště
    -   soubory? případně v jakém formátu a v jaké struktuře
    -   databáze? jaký typ a v jaké struktuře


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
trainer -[#red,thickness=2]-> delegateCaptain
```

## [](#prototyp-aplikace)Prototyp aplikace

Prototyp se může vytvořit online na [https://www.invisionapp.com/](https://www.invisionapp.com/), [https://www.figma.com/](https://www.figma.com/), nebo v desktopové aplikaci Axure RP.

Uveďte sem

-   odkaz na prototyp online, nebo wiki stránku s obrázky
-   popis výsledků testování prototypu

## [](#testovac%C3%ADch-p%C5%99%C3%ADpady)Testovací případy

alespoň 5 testovacích případů

## [](#projektov%C3%BD-board-a-workflow)Projektový board a workflow

-   odkaz na projektový issue board se vytvořenými issue
    -   issue jsou označené štítky podle typu, rozpracovanosti a dalších...
    -   každé issue má přiřazenou odpovědnou osobu
-   popis stadií rozpracovanosti, kterému odpovídají štítky
-   strategie pro větvení
