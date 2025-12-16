# VoyageBooking - Système de Réservation de Voyages

## Description du Projet

Ce projet est un prototype simple de système de réservation de voyages (avions et trains) développé dans le cadre du cours d'Architecture Logicielle. Son objectif est de gérer les offres par un administrateur et de permettre la recherche de trajets par les clients.

## Fonctionnalités Clés

### Partie Administrateur (/api/admin)

L'administrateur gère l'inventaire complet des voyages (CRUD : Création, Lecture, Modification, Suppression).

Admin : Ajout, modification et suppression des vols/trajets, aéroports/gares et opérateurs/compagnies.

### Partie Client et Système (/api/recherche)

Client : Recherche de vols par origine, destination et date de départ.

Système : Filtrage automatique pour n'afficher que les offres valides (avec opérateur, prix > 0, etc.).

## Stack Technique

Ce projet utilise une architecture Spring Boot basée sur les technologies suivantes :

Langage : Java 17

Framework : Spring Boot 3.2.0

Tests : JUnit 5 et Mockito (pour les tests unitaires des services)

Base de données : MariaDB (via JPA/Hibernate)

## Démarrage Rapide

### Installation et Démarrage

Assurez-vous d'avoir le JDK 17 et Maven installés.

| Lancer le projet | `mvn spring-boot:run` |
| Lancer les tests unitaires | `mvn test` |
