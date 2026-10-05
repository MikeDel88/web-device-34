# Projet 6

Monorepo contenant le front et le back de l'application.

```
.
├── olympic-games/      Front Angular (servi par Nginx), voir olympic-games/README.md
├── workshop-organizer/ Back Spring Boot / Gradle (WAR sur Tomcat), voir workshop-organizer/README.md
├── docker-compose.yaml Lance front + back + base PostgreSQL
└── .env.example        Variables d'environnement à copier dans .env
```

## Lancer l'ensemble

```bash
cp .env.example .env        # puis adapter les valeurs
(cd workshop-organizer && ./gradlew build) # produit le WAR attendu par workshop-organizer/Dockerfile
docker compose up --build
```

- Front : <http://localhost:4300>
- Back : <http://localhost:8080>

Chaque dossier garde son propre `Dockerfile`, ses dépendances et ses manifests `k8s/`.
