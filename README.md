# Projet 6

Monorepo contenant le front et le back de l'application.

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

# Front
cd olympic-games
docker compose up --build
```

- Front : <http://localhost:4300>
- Back : <http://localhost:8080>

Chaque dossier garde son propre `Dockerfile`, ses dépendances et ses manifests `k8s/`.
