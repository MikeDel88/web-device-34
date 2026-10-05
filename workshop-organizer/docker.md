# 🐳 Installation et utilisation de Docker

Ce guide décrit les étapes pour générer le WAR, construire l'image et démarrer l'application avec sa base PostgreSQL.

## 📋 Prérequis

- [Docker](https://docs.docker.com/get-docker/) installé
- Docker Compose (inclus avec Docker Desktop / Docker Engine récent)
- JDK 21 (pour générer le WAR avec `./gradlew bootWar`)

## 🧩 Versions utilisées

| Outil       | Version                | Source                              |
| ----------- | ---------------------- | ----------------------------------- |
| Java        | 21 (Temurin)           | `ARG TOMCAT` du `Dockerfile`        |
| Tomcat      | 10.1.24                | `ARG TOMCAT` du `Dockerfile`        |
| Spring Boot | 3.2.4                  | `build.gradle`                      |
| PostgreSQL  | 13                     | `docker-compose.yml`                |

## 🚀 Étapes

### 0. Configuration

Créer un fichier `.env` à la racine (non commité) :

```env
DB_NAME=workshopsdb
DB_USER=workshops_user
DB_PASSWORD=changeme
```

### 1. Génération du WAR

```bash
./gradlew bootWar
```

### 2. Création de l'image

```bash
docker build --tag workshop-organizer:0.2.4 .
```

### 3. Lancement de l'application et de la base

```bash
docker compose up -d
```

### 4. Vérification

```bash
docker compose ps
```

Les services `app` et `db` doivent être `healthy`.

### 5. Arrêt

```bash
docker compose down
```

Les données PostgreSQL sont conservées dans le volume `db-data`. Ajoutez `-v` pour le supprimer.

## 🌐 Ports et URL

| Service | Port hôte | Port conteneur | URL                     |
| ------- | --------- | -------------- | ----------------------- |
| `app`   | 8080      | 8080 (Tomcat)  | <http://localhost:8080> |
| `db`    | 5432      | 5432           | PostgreSQL (`localhost:5432`) |

## 📝 Récapitulatif

| Étape | Objectif                  | Commande                                      | URL                     |
| ----- | ------------------------- | --------------------------------------------- | ----------------------- |
| 0     | Configurer l'environnement | Créer le fichier `.env`                      | —                       |
| 1     | Générer le WAR            | `./gradlew bootWar`                           | —                       |
| 2     | Créer l'image             | `docker build --tag workshop-organizer .`     | —                       |
| 3     | Lancer app + base         | `docker compose up -d`                        | <http://localhost:8080> |
| 4     | Vérifier l'état           | `docker compose ps`                           | —                       |
| 5     | Arrêter                   | `docker compose down`                         | —                       |
