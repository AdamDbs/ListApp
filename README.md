# Rick-MortyApp

Cecci est mon application réalisée avec Android Studio. Elle répertorie tous les personnages de rick et morty existant dans les différents univers vus dans la série.

Pour pouvoir présenter ces informations, j'ai utilisé l'API : https://rickandmortyapi.com/ sur lequel j'ai récupérer les données dont j'avais besoins.

L'application utilise une liste dans le "MainActivity" qui récupère les données à afficher sur l'API (soit le nom des personnages, l'URL de la page suivante contenant la suite des personnages et une liste contenant tous les personnages et leurs informations). J'ai aussi ajouté une image comme icone apparaissant sur le côté de l'élément de la liste (une image de rick et morty).

Pour la partie "DetailActivity" affichant des informations supplémentaires sur le personnage au clique sur un élément de la liste, j'ai utilisé les informations supplémentaires sur les personnages trouvées via l'API (nom prénom, status "mort" ou "vivant", et planète d'origine). 
Dans cette partie, j'ai voulu afficher sur chaque page de personnage l'image correspondante donnée par l'API grâce à un URL, mais mes String n'arrivent pas à récupérer un URL sur l'API pour une raison qui m'échappe. J'ai donc décidé d'afficher une image fixe sur une partie de l'écran grâce à la librairie "Glide".

J'ai utilisé une architecture MVC (Modèle-vue-contrôleur) pour ordonner mes fichiers java.

J'ai aussi réalisé un Singleton (le fichier "Injection.java") pour restreindre l'instanciation de chaque classe à un seul objet (et rendre le code plus lisible, notamment dans le fichier "MainActivity").

Mon code respecte les principes SOLID pour une plus grande fiabilité et un meilleur rendu.

J'ai essayé de rendre l'application la plus jolie possible en mettant des tailles/couleures d'écriture adaptées et des fonds correspondant au mieux aux images contenues sur chaque page.
