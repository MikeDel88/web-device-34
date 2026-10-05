# Projet 6

Monorepo contenant les applications olympic games et workshop-organizer.

```
.
├── olympic-games/      Front Angular (servi par Nginx), voir olympic-games/README.md
└── workshop-organizer/ Back Spring Boot / Gradle (WAR sur Tomcat), voir workshop-organizer/README.md
```

## Lancer

Chaque dossier se lance avec son propre `docker-compose` :

```bash
# Back + PostgreSQL
cd workshop-organizer
cp .env.example .env        # puis adapter les valeurs
./gradlew build             # produit le WAR attendu par le Dockerfile
docker compose up --build

# Front (prod, port 4300 ; angular-dev pour le mode dev sur 4200)
cd olympic-games
docker compose up --build angular-prod
```

- Olympic Games Prod : <http://localhost:4300>
- Olympic Games Dev : <http://localhost:4200>
- Workshop Organizer : <http://localhost:8080>

Chaque dossier garde son propre `Dockerfile`, ses dépendances et ses manifests `k8s/`.


# Examples de lancement pour le script
```bash
python run-tests.py --path olympic-games 
```
```bash
python run-tests.py --path workshop-organizer
```