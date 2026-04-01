# 🔥 Sistema de Cadastro com Evolução de Aura (Spring Boot)

## 🎯 Objetivo do projeto

Este projeto tem como objetivo desenvolver uma aplicação web utilizando Spring Boot, permitindo o cadastro e login de usuários com persistência em banco de dados.

O sistema possui uma lógica de gamificação, onde a cada login o usuário ganha 100 pontos de aura. A cada 300 pontos, o usuário evolui de nível, alterando sua transformação e o background da aplicação. Também há uma leaderboard com os usuários ordenados por aura e uma barra de progresso.

---

## 🚀 Como executar

### Pré-requisitos:
- Java 17+
- MySQL

### Passos:

1. Configurar o banco de dados no arquivo:
```
src/main/resources/application.properties
```

Exemplo:
```
spring.datasource.url=jdbc:mysql://localhost:3306/cadastro_db
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

2. Executar o projeto:

No Windows:
```
.\mvnw spring-boot:run
```

3. Acessar no navegador:
```
http://localhost:8080
```

---

## 🛠️ Tecnologias utilizadas

- Java  
- Spring Boot  
- Spring MVC  
- Spring Data JPA  
- MySQL  
- Thymeleaf  
- HTML / CSS / JavaScript  

---

## 👨‍💻 Autor(es)

- Gustavo Pires
