# Tetris

## Groupe
+ Gilles Rivière
+ Quentin Remplakowski
+ Elizabeth Duong

## Compiler et lancer le programme
Le projet utilise maven. Tapez simplement 
```
mvn clean install && java -jar *.jar
```

## Comment jouer ?
Lorsque vous lancez le jeu, vous arriverez directement sur le shell intégré. Voici la liste des commandes disponibles
- **help** : Affiche la liste des commandes disponibles
- **start** : Lancer une partie solo
- **host [port]** : Héberger une partie multijoueur (le port est optionnel, par défault le port 4000 est utilisé)
- **join [ip] [port]** : Rejoindre une partie multijoueur. (ex : join 192.168.0.14 4000 )
- **scores** : Affiche les 5 meilleurs scores

### En jeu
- Utilisez les **fléches** pour déplacer votre tétrimino a l'écran.
- Appuyez sur la **bare espace** pour faire tomber le tetrimino jusqu'en bas
- Appuyez sur **V** ou **B** pour faire tourner le tétrimino
- Appuyez sur **R** pour relancer la partie
- Appuyez sur **Q** pour quitter la partie en cours


## Features
+ Tetris jouable
  - En Solo : OK
  - En réseau local : OK (jusqu'a 2 joueurs)
+ Malus
  - Réduction de la zone de jeu : En cours
  - Impossibilité de tourner les pieces : En cours
+ Minimum de 3 types de tétrimminos : OK (7 implémentés)
+ Scores persistants:
  - Lecture : OK
  - Écriture : OK
+ Interface visuelle
  - CLI : OK
  - Graphique : En cours

## Exercice Architecture
L'architecture utilisée à été pensée pour limiter au maximum le couplage inter-composants.
La plupart des classes fontionnent en isolation et peuvent être replacées avec un minimum de contraintes.
La communication inter-composants se fait par le biais d'un **bus de messages** (Synchrone et Asynchrone) qui est l'élément central de l'architecture.
[Insérer un schéma ici]

## Exercice Design Pattern / Solid

### Command pattern
- Puisque nous avons utilisé une interface **console**, il n'y a pas de menus ou de boutons dans le jeu. Nous avons donc réalisé
un **shell** allégé pour la navigation entre les différents écrans. 

- Il s'agit du meilleur endroit possible ou appliquer le **command pattern**. Puisque que nous ne savions pas à l'avance combien
de commandes allaient être disponible, il nous fallait un design **extensible** dont le code métier (ce que réalise la commande) serait
découplé du code qui execute la commande. Il était donc trés facile de rajouter de nouvelles commandes sans avoir a modifier le 
code déja présent.

- Le composant "InteractiveConsoleComponent" dispose donc d'un interpréteur de commandes qui prends en entrée une chaine de caractére
et qui execute la commande qui y correspond. **Les commandes sont des objets** indépendants auquels sont associés une clé (un String) 
afin de les identifier. L'interpréteur de commande stocke en mémoire les différentes commandes et appelle la méthode "execute".

### Publish / Subscribe
- L'architecture est basé sur un **bus d'événements**. N'importe quelle classe peux demander à être informé d'un ou plusieurs types
de messages qui transitent sur le bus. 

- Ce pattern est trés pratique pour **découpler** les actions (les événements qui se produisent) des classes qui effectuent les dites actions.
Au lieu de se créer une, voir plusieures dépendances avec d'autres classes, on ne dépends plus que du bus et du type de messages qui
y transitent.

  + Un exemple concret serais le "ScoreComponent", dont le role est de calculer le score du joueur. Ce composant a besoin de savoir
  quand une ou plusieurs lignes sont supprimées de la zone de jeu. Plutôt que de rajouter une dépendance entre la zone de jeu et 
  ce composant, ScoreComponent s'inscrit au bus de message et demande a être notifié des messages de type "LineNotification". 
  De ce fait, peut importe qui produit ces messages, le ScoreComponent ne s'interresse plus qu'a l'action en elle même.
  
  + Un autre avantage concret est **l'extensibilité**. Lorsque que nous avons rajouté le "PenaltyComponent", les pré-requis étaient
  les mêmes que pour le "ScoreComponent" : Ce composant a besoin de savoir quand une ligne est supprimée. Puisque les événements
  de type "LineNotification" étaient déja transmis sur le bus de message, nous avons pu rajouter ce composant **sans avoir besoin de
  modifier le code déja présent** *(Open Closed Principle)*. De plus, **aucune dépendance inutile** n'est créée entre la zone de jeu, le 
  composant des scores et celui des malus. 
  
  + Un autre avantage est qu'il est beaucoup plus facile de créer de petites classes à **responsabilité unique** *(Single Responsability Principle)*
  ayant un couplage trés faible avec le reste de l'application.
  
  + L'inconvénient bien sur est que la grande majorité des classes ont une dépendance avec le bus de message, mais cela ne nous à
  posé aucun probléme sur un projet de cette échelle. 
  
- Nous avions d'abord implémenté une version naive du publish/subscribe : Un tableau associatif dynamique reliait le type d'événement
  à tout les listeners. Lorsqu'un message arrivait, on cherchait alors tout les listeners enregistrés a ce type de messages et on appelait
  directement la fonction "handle". Nous avons ensuite abandoné ce code au profit d'un bus de message tiers qui supportait les
  messages asynchrone et qui était Thread-Safe. 
  
### Builder pattern
- La création des Tetriminos est une opération longue dans notre implémentation de tetris. Nous avons donc utilisé le Builder Pattern
avec la classe "TetriminoBuilder".

- Ce pattern nous a permis de rajouter trés simplement de nouveaux types de tetriminos en conservant une seule classe paramétrable.

- Les tétriminos sont représentés par plusieurs tableaux d'entiers à deux dimensions. Si la valeur d'une case est de 1, il y a un bloc, sinon 
il n'y a rien. Chaque tableau représente le tétrimino pour chaque rotation. Ces tableaux sont assez longs à écrire mais le builder
pattern nous l'autorise. 
