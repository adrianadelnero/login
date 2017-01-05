# Login  

* Spring Security
* Spring MVC
* Hibernate
* Tomcat
* Postgres
:octocat:

## Installation

Environment preparing:

### Postgres setup

* scripts/createSystemUser
* scripts/database-helper
* scripts/createCredencials

### Installing dependencies

```sh
mvn clean install

```

## Testing

http://localhost:8080/login
User: admin
Senha: 123456

##Verify to build

```sh
mvn findbugs:check

```
