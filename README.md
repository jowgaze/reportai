# ReportAI

**Backend desenvolvido em Java com Spring Boot**, focada no gerenciamento de dados educacionais e preparada para **geração de relatórios inteligentes utilizando IA**.

---

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-%23F7B93E.svg?&style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%23A6B5E4.svg?&style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-%23C71F37.svg?&style=for-the-badge&logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23336791.svg?&style=for-the-badge&logo=postgresql&logoColor=white)

---

## Funções
- Registro de anotações diários de alunos
- Organização de alunos através de escolas e turmas
- Geração de relatórios completos com **Inteligência Artificial**

---

## Estrutura
```
src/
├── main/
│   ├── java/com/reportai/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── dto/
│   │   ├── entity/
│   │   └── config/
│   └── resources/
│       └── application.yml
```

---

## Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/jowgaze/reportai.git
cd reportai
```
2. Configure o arquivo `application.yml` com as credenciais do banco e parâmetros de IA (Modelo, Prompt e Instruções).
3. Adicione sua chave da API do Google Gemini na variável ambiente GOOGLE_API_KEY
4. Execute o projeto 
5. A aplicação estará disponível com a documentação e endpoints em:

```
localhost:8080/swagger-ui/index.html
```
---
**Desenvolvido com 💻 e ☕ por [jowgaze](https://github.com/jowgaze)**