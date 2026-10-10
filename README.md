# AgendApp

**Aluno:** _[preencher: nome completo]_
**Matrícula:** _[preencher]_
**Data:** 09/10/2026

## Justificativa do tema
Pequenos negócios que atendem por hora (barbearias, clínicas, consultórios) frequentemente controlam a agenda em papel ou mensagens soltas. O AgendApp oferece um registro simples, offline e persistente dos horários marcados, cobrindo o ciclo básico de um CRUD local (criar, listar, excluir).

## Funcionamento
- **Lista de agendamentos** (`AppointmentListScreen`): mostra cliente, serviço, data e horário, ordenados por data/hora; exibe mensagem quando vazia; cada item tem botão de exclusão; o FAB (+) abre o cadastro.
- **Novo agendamento** (`AddAppointmentScreen`): campos Nome, Serviço, Data (`dd/mm/aaaa`) e Horário (`HH:mm`), todos validados (obrigatórios e com formato); ao salvar, grava no banco e volta à lista.
- Os dados persistem em SQLite via Room e a lista atualiza automaticamente (Flow).

## Arquitetura (MVVM)
```
com.agendapp
├── data/        Appointment (Entity), AppointmentDao (insert/delete/getAll Flow), AppDatabase (singleton)
├── repository/  AppointmentRepository (abstrai o DAO)
├── viewmodel/   AppointmentViewModel (StateFlow) + AppointmentViewModelFactory
├── ui/          AppointmentListScreen, AddAppointmentScreen, AppNavigation (Navigation Compose)
└── MainActivity Inicializa banco, repositório e ViewModel (injeção manual via Factory)
```
Fluxo: UI → ViewModel → Repository → DAO → Room; os dados sobem de volta como `Flow` → `StateFlow` → `collectAsState`.

**Stack:** Kotlin, Jetpack Compose + Material 3, Navigation Compose, Room (KSP), Coroutines/Flow.

## Executar
```
./gradlew installDebug
```
(com emulador ou dispositivo conectado, `ANDROID_HOME` ou `local.properties` apontando para o SDK)
