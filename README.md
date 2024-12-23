# Eazy Bank

Eazy Bank — это банковское приложение, построенное с использованием микросервисной архитектуры. Проект разрабатывается с применением современных технологий для обеспечения масштабируемости, безопасности и высокой доступности. В нем используются передовые подходы для создания устойчивых микросервисов с контейнеризацией и распределенной архитектурой.

## Описание

Проект Eazy Bank представляет собой банковское приложение с микросервисной архитектурой, созданное с целью оптимизации различных банковских операций. В этом проекте используются такие технологии, как Spring Boot, Docker, PostgreSQL, RabbitMQ, Kafka, Spring Cloud и многие другие. Микросервисная архитектура позволяет разделить различные компоненты приложения на независимые сервисы, каждый из которых может быть масштабирован и развёрнут отдельно.

## Технологический стек

- **Spring Boot** — основной фреймворк для разработки микросервисов.
- **Spring Security** — для обеспечения безопасности приложения.
- **Spring Cloud** — для реализации облачных решений и управления конфигурациями.
- **Docker** — для контейнеризации микросервисов.
- **PostgreSQL** — реляционная база данных для хранения данных.
- **RabbitMQ** — для организации асинхронных коммуникаций между микросервисами.
- **Kafka** — для обмена сообщениями и обработки событий в реальном времени.
- **Kubernetes** — для оркестрации контейнеров и управления развертыванием микросервисов.
- **Helm** — для управления чартами Kubernetes и автоматизации развертывания приложений.
- **Grafana** — для мониторинга и наблюдаемости.
- **Keycloak** — для централизованного управления идентификацией и доступом.

## Особенности реализации

- **Микросервисная архитектура**: Приложение построено на основе микросервисной архитектуры, что позволяет разделить функциональность на независимые сервисы с возможностью масштабирования каждого из них.

- **Контейнеризация**: Все микросервисы развернуты с использованием Docker, что позволяет легко развертывать и управлять ими в различных средах.

- **Оркестрация контейнеров**: Kubernetes используется для управления контейнерами, их масштабирования и мониторинга, обеспечивая высокую доступность приложения.

- **Управление развертыванием**: Helm используется для автоматизации развертывания и управления конфигурациями Kubernetes через заранее настроенные чарты.

- **Конфигурация и управление**: Используется Spring Cloud Config для централизованного управления конфигурациями и Netflix Eureka для регистрации сервисов и их поиска.

- **API Gateway и балансировка нагрузки**: Реализован API Gateway, который выполняет роль прокси-сервера и обеспечивает балансировку нагрузки между микросервисами.

- **Обработка событий и асинхронная коммуникация**: RabbitMQ и Kafka используются для организации событийной архитектуры и асинхронной обработки сообщений между микросервисами.

- **Устойчивость системы**: Для обеспечения отказоустойчивости системы реализованы механизмы Circuit Breaker, Retries, Fallback, Bulkhead и Rate Limiter.

- **Безопасность**: Применяется Spring Security и интеграция с Keycloak для централизованного управления безопасностью и авторизацией пользователей.

- **Мониторинг и наблюдаемость**: Для мониторинга работы приложения используется Grafana и Spring Boot Actuator.
