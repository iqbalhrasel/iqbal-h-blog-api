# Spring Boot Blog REST API

This is the backend for a blog application built using **Spring Boot**. It exposes RESTful endpoints for fetching published blog content, with support for pagination, sorting, and content filtering.

---

## 🚀 Features

- RESTful API for blog posts
- Content filtering using `ContentStatus` enum
- Ordered by `createdAt` field (latest first)
- Pagination and sorting support
- Optimized fetching using JPA `EntityGraph`
- Flyway for database migrations
- PostgreSQL / Supabase support

---

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL / Supabase
- Flyway (for migrations)
- Lombok
- Hibernate
- Mapstruct (for mapping)
- Caffeine for caching (for blog comment adding)

---

## 🔧 Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL or Supabase

### Clone and Run

```bash
git clone https://github.com/iqbalhrasel/iqbal-h-blog-api.git
cd iqbal-h-blog-api
