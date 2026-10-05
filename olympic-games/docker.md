# 🐳 Installation et utilisation de Docker

Ce guide décrit les étapes pour construire l'image, lancer les tests et démarrer l'environnement de développement.

## 📋 Prérequis

- [Docker](https://docs.docker.com/get-docker/) installé
- Docker Compose (inclus avec Docker Desktop / Docker Engine récent)

## 🧩 Versions utilisées

| Outil      | Version                 | Source                                  |
| ---------- | ----------------------- | --------------------------------------- |
| Node.js    | 22.23 (`alpine3.24`)    | `ARG NODE_VERSION` des Dockerfiles      |
| Angular    | 20.3.x (`~20.3.7`)      | `package.json` (`@angular/core`, CLI)   |
| TypeScript | 5.8.x (`~5.8.3`)        | `package.json`                          |
| Nginx      | `alpine3.24` (unprivileged) | `ARG NGINX_VERSION` du `Dockerfile` |

## 🚀 Étapes

### 1. Création de l'image

```bash
docker build --tag docker-angular-sample .
```

### 2. Lancement des tests

```bash
docker compose run --rm angular-test
```

### 3. Lancement de l'environnement de développement

```bash
docker compose watch angular-dev
```

### 4. Lancement de l'environnement de production

```bash
docker compose up angular-prod -d
```

## 🌐 Ports et URL

| Service        | Port hôte | Port conteneur | URL                     |
| -------------- | --------- | -------------- | ----------------------- |
| `angular-dev`  | 4200      | 4200           | <http://localhost:4200> |
| `angular-prod` | 4300      | 8080 (nginx)   | <http://localhost:4300> |
| `angular-test` | —         | —              | Aucun port exposé       |

## 📝 Récapitulatif

| Étape | Objectif                    | Commande                                     | URL                     |
| ----- | --------------------------- | -------------------------------------------- | ----------------------- |
| 1     | Créer l'image               | `docker build --tag docker-angular-sample .` | —                       |
| 2     | Lancer les tests            | `docker compose run --rm angular-test`       | —                       |
| 3     | Lancer l'environnement dev  | `docker compose watch angular-dev`           | <http://localhost:4200> |
| 4     | Lancer l'environnement prod | `docker compose up angular-prod -d`          | <http://localhost:4300> |
