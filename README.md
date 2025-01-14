# 🍽️ **Restaurant App voor Vereniging**

Deze android applicatie was bedoeld om efficienter eetfestijnen te laten verlopen, spijtig genoeg zijn veel functionaliteiten niet gehaald

---

## 📌 **Projectdoel**

- **Admins**: Producten beheren in de database.  
- **Obers**: Tafels en klanten beheren, en bestellingen aanmaken die naar de keuken- en barprinters worden gestuurd.  

⚠️ **Huidige status**:  
- Het **beheer van voedselproducten** door admins is succesvol geïmplementeerd.  
- **Functionaliteit voor obers**, inclusief het aanmaken van klanten en bestellingen, is niet operationeel.  
- **Printers** konden niet worden geïntegreerd. Ik had enkel toegang tot ethernet printers en kon er niet mee verbinden

---

## 🛠️ **Technologieën**

- **Programmeertaal**: Kotlin  
- **Frameworks en Bibliotheken**:  
  - Jetpack Compose  
  - Room Database  
  - Coil (voor afbeeldingsbeheer)  
  - Hilt (Dependency Injection)  

---

## 💡 **Functionaliteiten**

### ✅ **Geïmplementeerd**  
- **Admin-functionaliteit**:  
  - Voedsel beheren (CRUD-operaties).  

- **Restaurantoverzicht**:  
  - Obers kunnen een overzicht van het restaurant zien en op tafels klikken om naar een detailpagina te navigeren.  

### ❌ **Niet volledig geïmplementeerd**  
- **Bestellingen beheren**:  
  - Het aanmaken van bestellingen en koppelen aan klanten en tafels.  
  - Versturen van bestellingen naar de keuken- en barprinters.  

- **Printerintegratie**:  
  - Ethernetprinters konden niet worden verbonden met de app.

---

## 🔗 **Hulpmiddelen en Bronnen**

- **Officiële Documentatie**:  
  - [Android Room - Relationships](https://developer.android.com/training/data-storage/room/relationships)  
  

- **Videotutorials**:  
  - [YouTube: Building Android Apps with Kotlin](https://www.youtube.com/watch?v=c8XP_Ee7iqY&t=693s)  

- **PluralSightTutorials**: 
  - [Android Fundamentals met Kotlin](https://app.pluralsight.com/library/courses/android-kotlin-fundamentals/table-of-contents)  

- **ChatGPT Gesprekken**:  
  - [Deel 1](https://chatgpt.com/share/6786ab13-8794-8008-8f5f-85486873ea0a)  
  - [Deel 2](https://chatgpt.com/share/6786ab80-5860-8008-910b-1989448a11e4)  
  - [Deel 3](https://chatgpt.com/share/6786abd6-4038-8008-bdcc-2e3ce39c2dc6)  
  - [Deel 4](https://chatgpt.com/share/6786ac0d-f078-8008-b374-6031f4cb2950)  
  - [Deel 5](https://chatgpt.com/share/6786ac4f-3b9c-8008-a5fd-7ff0f658f7c8)  
  - [Deel 6](https://chatgpt.com/share/6786ac77-a188-8008-943c-c56c2f5105f6)  
  - [Deel 7](https://chatgpt.com/share/6786acae-9868-8008-8517-f507f369bd14)  

---

## 🌟 **Toekomstige Verbeteringen**

1. **Volledige implementatie van obersfunctionaliteit**:  
   - Aanmaken en beheren van klanten.  
   - Ordersystemen koppelen aan tafels en producten(voedsel, drank).  

2. **Integratie van printers**:  
   - Ondersteuning voor Ethernetprinters.  
   - Mogelijke overstap naar draadloze printers voor flexibiliteit.  

3. **Optimalisatie van de gebruikerservaring**:  
   - Betere navigatie en gebruiksvriendelijke interfaces.  

4. **Externe database**:  
   - Ofwel met Firebase of met de database van de website van de vereniging.

5. **Authorization en authentication**:
   - Gebruiker moet zich inloggen en is dan ofwel een ober of een admin indien sucessvol ingelogd.

