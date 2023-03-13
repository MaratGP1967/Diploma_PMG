## MYSQL [![Build status](https://ci.appveyor.com/api/projects/status/kehrt7lt6qg02gba?svg=true)](https://ci.appveyor.com/project/MaratGP1967/diploma-pmg) 
## PostgreSQL [![Build status](https://ci.appveyor.com/api/projects/status/kehrt7lt6qg02gba?svg=true)](https://ci.appveyor.com/project/MaratGP1967/diploma-pmg)
# Дипломный проект по профессии «Тестировщик»
### Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

Сервис предлагает купить тур по определённой цене двумя способами:
1. Обычная оплата по дебетовой карте.
2. Уникальная технология: выдача кредита по данным банковской карты.

Сервис в собственной СУБД должен сохранять информацию о том, успешно ли был совершён платёж и каким способом.

Описание задания к дипломному проекту размещено [здесь](https://github.com/netology-code/qa-diploma).

Ключевая задача — автоматизировать позитивные и негативные сценарии покупки тура.

## Тестовая документация
1. [План тестирования](https://github.com/MaratGP1967/Diploma_PMG/blob/main/Plan.md);
1. [Отчёт по итогам тестирования](https://github.com/MaratGP1967/Diploma_PMG/blob/main/Report.md);
1. [Отчет по итогам автоматизации](https://github.com/MaratGP1967/Diploma_PMG/blob/main/Summary.md)

## Запуск проекта автоматизированного тестирования сервиса
### Требования к рабочей станции:
1. Инсталлирована `OS`, не ниже `OS Windows 10` `версия 21H2`, оперативная память не менее `16Гб`;
2. Инсталлирован и используется по умолчанию `Веб-браузер` `Google Chrome` версии `111.0.5563.64` и выше;
3. Инсталлирована среда разработки `IntelliJ IDEA 2022.2.2 (Community Edition)`;
4. Развернута платформа контейнеризации `Docker Desktop`;
5. В `Docker Desktop` загружены с `DockerHub` следующие образы: `node:18.14-alpine`, `mysql:8.0`, `postgres:12.11-alpine`;
6. Скопирован с Github на рабочую станцию репозиторий проекта [по следующей ссылке](https://github.com/MaratGP1967/Diploma_PMG).
### Подготовительный запуск приложений
1. Запустить `Docker Desktop` с образами: `node:18.14-alpine`, `mysql:8.0`, `postgres:12.11-alpine`;
2. Запустить `IntelliJ IDEA` и открыть в ней клонированный проект `Diploma_PMG`;
3. Во вкладке `Terminal (Local)` приложения `IntelliJ IDEA`, ввести в командную  строку команду `docker compose up`, ждать запуска контейнеров `MySQL`, `PostgreSQL`, `node-app:1.0`;
### Запуск Веб-сервиса для работы с `MySQL`
4. Открыть вторую вкладку `Terminal (Local 2)` приложения `IntelliJ IDEA`, ввести в командную строку команду `Java -jar artifacts/aqa-shop.jar --spring.profiles.active=msql`, ждать запуска сервиса;
### Запуск тестов для работы с `MySQL`
5. Открыть третью вкладку `Terminal (Local 3)` приложения `IntelliJ IDEA`, ввести в командную строку команду `./gradlew clean test -D db.url=jdbc:mysql://localhost:3306/msql -D schemas=msql`, ждать окончания прохождения тестирования;
### Запуск отчета о тестировании
6. После прохождения фазы тестирования в третьей вкладке `Terminal (Local 3)` приложения `IntelliJ IDEA` ввести в командную строку команду `./gradlew allureServe`, ждать открытия веб-браузера с отчетом сформированным `Allure`;
### Выход из фазы отчета о тестировании
7. После вывода и анализа отчета о тестировании в третьей вкладке `Terminal (Local 3)` приложения `IntelliJ IDEA` ввести комбинацию клавиш `Ctrl+C`, появится вопрос `Завершить выполнение пакетного файла [Y(да)/N(нет)]?`, ввести `Y`, кликнуть `Enter`, ждать выхода;
### Останов сервиса с `MySQL`
8.  Во второй вкладке `Terminal (Local 2)` приложения `IntelliJ IDEA` ввести комбинацию клавиш `Ctrl+C`, ждать выхода;
### Запуск Веб-сервиса для работы с `PostgreSQL` (`Docker Desktop` и контейнеры запущены после тестирования с БД `MySQL`, в противном случае повторить пункты 1-3 Подготовительного запуска)
9. Открыть вторую вкладку `Terminal (Local 2)` приложения `IntelliJ IDEA`, ввести в командную строку команду `Java -jar artifacts/aqa-shop.jar --spring.profiles.active=psql`, ждать запуска сервиса;
### Запуск тестов для работы с `PostgreSQL`
10. Открыть третью вкладку `Terminal (Local 3)` приложения `IntelliJ IDEA`, ввести в командную строку команду `./gradlew clean test -D db.url=jdbc:postgresql://localhost:5432/psql -D schemas=public`, ждать окончания прохождения тестирования;
### Запуск отчета о тестировании
11. После прохождения фазы тестирования в третьей вкладке `Terminal (Local 3)` приложения `IntelliJ IDEA` ввести в командную строку команду `./gradlew allureServe`, ждать открытия веб-браузера с отчетом сформированным `Allure`;
### Выход из фазы отчета о тестировании
12. После вывода и анализа отчета о тестировании в третьей вкладке `Terminal (Local 3)` приложения `IntelliJ IDEA` ввести комбинацию клавиш `Ctrl+C`, появится вопрос `Завершить выполнение пакетного файла [Y(да)/N(нет)]?`, ввести `Y`, кликнуть `Enter`, ждать выхода;
### Останов сервиса с `PostgreSQL`
13.  Во второй вкладке `Terminal (Local 2)` приложения `IntelliJ IDEA` ввести комбинацию клавиш `Ctrl+C`, ждать выхода;
### Останов работы `Docker Desktop`  и удаление контейнеров
14.  В первой вкладке `Terminal (Local)` приложения `IntelliJ IDEA` ввести комбинацию клавиш `Ctrl+C` (для ускорения можно ввести несколько раз), после выхода в командную строку ввести команду `docker compose down`.

