# Týmovačka - Tým. Snadně.

# TÝMOVÁNÍČKO
![Týmováníčko](uploads/fc7558ab704c385325015b38f549eb1d/Týmováníčko.png)
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
:Člen týmu: as clenTymu
:Trenér: as trener
:Kapitán: as kapitan


usecase "Registrace/Přihlašování" as login
usecase "Účast na událostech" as rsvp
usecase "Komunikace - fotky a videa" as comms
usecase "Docházka" as dochazka
usecase "Posílat zprávy" as chat


clenTymu --> login
clenTymu --> chat
clenTymu --> rsvp
clenTymu --> dochazka : zadat

kapitan -[#green,thickness=2]-> rsvp : organizace
kapitan -[#green,thickness=2]-> dochazka
kapitan -[#green,thickness=2]-> comms
kapitan -[#green,thickness=2]-> chat
kapitan -[#green,thickness=2]-> login

trener -[#red,thickness=2]-> rsvp : organizace
trener -[#red,thickness=2]-> dochazka : spravovat
trener -[#red,thickness=2]-> comms
trener -[#red,thickness=2]-> chat
trener -[#red,thickness=2]-> login
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
