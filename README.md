# Personal Finance

This project demonstrates the use of `Domain Driven Design` and Hexagonal architecture.

## Project Setup

This project uses Nix, and devenv to manage the build environment in a reproducible fashion.  
`devenv` integrates with `direvn` so that changing into the project directory automatically setups the build environment.

```bash
devenv up
devenv down
```

To start the project

```bash
spring init --build=maven --dependencies=web personal_finance
```

> :exclamation: Remember to replace the starter-xxx.jar with actual dependcies to minimize jar bloat.

To create remote repo and push local contents

```bash
gh repo create --public --source=. --remote=origin
git push origin
```

## DDD concepts in brief

[DDD Overview](https://www.domainlanguage.com/wp-content/uploads/2016/05/DDD_Reference_2015-03.pdf)

```mermaid
classDiagram
    class BoundedContext {
        <<DDD Concept>>
        name: String
        ubiquitousLanguage: Set
    }
    class AggregateRoot {
        <<DDD Concept>>
        id: String
        version: Long
        +publishEvent()
        +markAsDeleted()
    }
    class Entity {
        <<DDD Concept>>
        id: String
        +sameIdentityAs()
    }
    class ValueObject {
        <<DDD Concept>>
        +equals()
        +hashCode()
        +isImmutable()
    }
    class DomainEvent {
        <<DDD Concept>>
        aggregateId: String
        eventId: String
        occurredOn: DateTime
    }
    class Repository {
        <<DDD Interface>>
        +save(aggregate)
        +findById(id)
        +findByCriteria()
    }
    class DomainService {
        <<DDD Concept>>
        +performDomainLogic()
    }
    class ApplicationService {
        <<DDD Concept>>
        +coordinateUseCase()
        +handleCommand()
    }
    class Factory {
        <<DDD Concept>>
        +createAggregate()
        +reconstitute()
    }
    
    BoundedContext --* AggregateRoot : contains
    AggregateRoot --* Entity : contains
    AggregateRoot --* ValueObject : contains
    Entity --> ValueObject : uses
    AggregateRoot --> DomainEvent : publishes
    AggregateRoot --> Repository : defines interface for
    Repository --> AggregateRoot : persists
    DomainService --> AggregateRoot : operates across
    ApplicationService --> AggregateRoot : coordinates
    ApplicationService --> Repository : uses
    Factory --> AggregateRoot : creates
    Factory --> ValueObject : creates

```
