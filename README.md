# **Star Gallery - Application Android**

**Star Gallery** est une application Android permettant aux utilisateurs d’explorer une galerie d’acteurs et d'actrices, de noter leurs stars préférées et de partager des informations avec d’autres. Cette application propose une interface fluide, une barre de recherche dynamique, et des animations d’interaction pour rendre l’expérience plus immersive.

---

## **Sommaire**

1. [Fonctionnalités](#fonctionnalités)
2. [Technologies utilisées](#technologies-utilisées)
3. [Prérequis et installation](#prérequis-et-installation)
4. [Structure du projet](#structure-du-projet)
5. [Utilisation de l'application](#utilisation-de-lapplication)
6. [Explication détaillée du code](#explication-détaillée-du-code)
7. [Améliorations futures](#améliorations-futures)
8. [Contributions](#contributions)
9. [Auteurs](#auteurs)
10. [Licence](#licence)

---

## **Fonctionnalités**

### 📸 **Affichage de la galerie de stars**
- **Liste des stars** : Affichage d’une liste d’acteurs avec leur image, leur nom et leur note actuelle. Les images sont chargées via des ressources locales pour le moment, mais peuvent être étendues pour inclure des images dynamiques via une API.
- **Notation via des étoiles** : Utilisation de `RatingBar` pour permettre aux utilisateurs de noter chaque star avec des étoiles visibles sous leur photo.

### 🔍 **Recherche dynamique**
- **Barre de recherche** : L'application inclut une barre de recherche dans la barre d'outils pour filtrer les stars en fonction du nom. Cela met à jour la liste en temps réel grâce à un `TextWatcher` ou une fonctionnalité native de `SearchView`.
  
### 💫 **Animations visuelles**
- **Animations du logo** : Lors du démarrage de l'application, le logo subit des animations incluant rotation, translation, changement d’échelle et disparition progressive, créant un effet visuel engageant pour l’utilisateur.
- **Effets de réduction d'échelle et de disparition** : Les animations des stars incluent également une réduction d’échelle avec disparition progressive, symbolisant un effet "fade out".

### 📤 **Partage social**
- **Icône de partage** : Chaque star possède une icône de partage. Lorsqu'un utilisateur clique dessus, un menu contextuel permet de partager les informations de la star via d'autres applications comme Gmail, WhatsApp ou des réseaux sociaux.
  
### 🌟 **Popup de notation**
- **Noter avec un `SeekBar`** : Appuyez longuement sur une star pour ouvrir un `AlertDialog` personnalisé avec un `SeekBar` permettant d'ajuster précisément la note d’une star, avec des changements instantanés visibles dans la liste.

---

## **Technologies utilisées**

- **Langage** : Java (API Android)
- **Android SDK** : Utilisation du SDK Android 21 (Lollipop) minimum.
- **Android Studio** : Environnement de développement intégré (IDE) utilisé pour construire et tester l'application.
- **Composants natifs Android** : `RecyclerView`, `SearchView`, `RatingBar`, `ImageView`, `AlertDialog`, `Intent` pour le partage.
- **Animations** : API d’animations Android (`ViewPropertyAnimator`) pour créer des animations fluides et personnalisées.

---

## **Prérequis et installation**

### **Prérequis**

- **Android Studio** : Version 4.x ou supérieure.
- **SDK Android** : API 21 ou supérieure.
- **Gradle** : Une version compatible avec le projet (indiquée dans le fichier `build.gradle`).
  
### **Installation de l’application**

1. **Clonez le dépôt** :
   git clone https://github.com/votre-utilisateur/star-gallery.git
2.  **Ouvrez le projet dans Android Studio** :

- Dans Android Studio, allez dans **File > Open**.
- Sélectionnez le dossier du projet.

3. **Synchronisation Gradle** :

- Une fois le projet ouvert, cliquez sur **Sync Project with Gradle Files** pour synchroniser les dépendances et les fichiers de configuration.

4. **Lancer l’application** :

- Sélectionnez un émulateur ou un appareil Android réel.
-  Cliquez sur **Run 'app'** ou utilisez le raccourci clavier `Shift + F10`.

## Structure du projet

Voici un aperçu des principaux fichiers et dossiers :

### Code Java

- **MainActivity.java** : Gère l’écran d’accueil, initialisant les animations du logo.
- **ListActivity.java** : Contient la logique principale de l’application avec le `RecyclerView` affichant les stars.
- **StarAdapter.java** : Adapteur pour connecter les données des stars aux éléments du `RecyclerView`.
- **StarService.java** : Simule une base de données en fournissant une liste d'acteurs avec des notes fictives.
- **Star.java** : Classe modèle représentant une star avec des attributs comme le nom, la note et l'image.

### Layouts XML

- **activity_list.xml** : Interface pour l’activité affichant la liste des stars. Utilisation d’un `RecyclerView` pour afficher la liste des acteurs.
- **dialog_rate_star.xml** : Layout XML pour la popup de notation d’une star.

### Ressources

- **Images** : Les images des stars et les icônes sont stockées dans le dossier `res/drawable/`.
- **Chaînes de caractères** : Les textes et chaînes statiques sont stockés dans `res/values/strings.xml`.

## Utilisation de l'application

### Démarrage

Lorsque l'application démarre, elle affiche une animation de logo qui tourne, descend et disparaît.

### Liste des stars

Après l’animation d'introduction, la liste des stars est présentée. Chaque star est affichée avec son image, son nom et une note attribuée.

### Notation

Pour noter une star, appuyez longuement sur son image pour ouvrir une boîte de dialogue, ajustez la note avec le curseur, puis cliquez sur "OK" pour enregistrer votre choix.

### Recherche

Utilisez la barre de recherche en haut pour filtrer les stars par leur nom. La liste se met à jour dynamiquement pendant que vous tapez.

### Partage

Cliquez sur l'icône de partage pour envoyer des informations sur une star via des applications comme Gmail ou WhatsApp.

## Explication détaillée du code

### ListActivity.java

- **RecyclerView et StarAdapter** : Le `RecyclerView` utilise un `StarAdapter` pour lier chaque star à un élément de la liste. L'adaptateur gère également les clics sur les icônes de partage.
- **SearchView** : Cette activité implémente un `SearchView` qui permet de filtrer les résultats en temps réel. Lorsqu'un utilisateur saisit un nom dans la barre de recherche, le `RecyclerView` est mis à jour en conséquence.

### Animations

**startLogoAnimations() dans MainActivity.java** : Cette méthode enchaîne plusieurs animations sur le logo :

- **Rotation** : Le logo tourne à 360°.
- **Translation** : Le logo descend vers le bas de l’écran.
- **Échelle et disparition** : Le logo réduit progressivement sa taille et devient transparent jusqu'à disparaître.

### Partage

- **Intention de partage** : L'icône de partage déclenche une `Intent` qui permet de partager des informations via d'autres applications installées sur le téléphone.

## Améliorations futures

- **Base de données réelle** : Intégration d'une base de données locale (par exemple SQLite ou Room) ou d'une API pour stocker et gérer les données des stars.
- **Fonctionnalité de favoris** : Ajouter la possibilité de marquer certaines stars comme "favoris" et afficher ces favoris dans une section dédiée.
- **Notifications push** : Implémenter des notifications pour alerter les utilisateurs des nouvelles stars ou des mises à jour.
- **Design modernisé** : Utilisation de composants Material Design pour améliorer l'esthétique générale de l'application.

## Démonstration vidéo
https://github.com/user-attachments/assets/69cb7ca0-620c-43e8-99f3-19260ab1e358

## Auteurs

- **Nom :** KHALIL Fatima
