# Staff - Docker, SpringBoot, and Angular 8
Este repositório de playground destina-se a criar um aplicativo em Angular que
consome vários microsserviços de back-end desenvolvidos no SpringBoot.
A idéia é configurar inicialmente um ambiente de teste com base em um host único
com vários contêineres de docker que hospedam os diferentes serviços. Um servidor web
(NGINX) que atua como o ponto de entrada do aplicativo que hospeda o
Aplicativo angular e configurado para proxy do tráfego da API para o correspondente
serviço de back-end hospedado em seu próprio contêiner de docker.

## Etapas de compilação
1. Clone repository
```bash
git clone https://github.com/jlopez34/staff-app.git
cd staff-app
```
2. Build Angular application
```bash
cd frontend/frontend-app
ng build
```
3. Build backend services
```bash
cd ../../backend
cd staff-service
mvn clean package

```
4. Build and run docker compose
```bash
cd ../../
docker-compose build
docker-compose up
```
5. Open browser on http://localhost

## Pré-requisitos
- Docker CE >= version 18
- Docker Compose >= version 1.20
- Maven 3.x.x
- JDK 1.8+

## Aviso Legal
Meus repositórios de playground não se destinam a fornecer aplicativos completos,
basicamente, são caixas de areia e projetos para animais de estimação para experimentar 
diferentes tecnologias e técnicas.
