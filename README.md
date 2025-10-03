O projeto Financial Copilot tem o objetivo de controlar as despesas mensais do usuário de forma automática.
O usuário tem a opção de cadastrar as sua despesas mensais no sistema ou realizar consultas sobre as suas despesas mensais.

O Financial Copilot foi construído de forma integrada com o Gemini para que seja possível 
fazer busca semântica (ex: “gastos com mercado”) via embeddings. Atualmente, o fluxo do sistema funciona da seguinte forma:
- Salva as transações no banco
- Cria embeddings das descrições e salva no pgvector

As funcionalidades em fase de construção e testes são:
- Quando o usuário fizer uma pergunta, o sistema deve buscar as transações mais relevantes (via embeddings).
- Monta um prompt (RAG) com as transações + pergunta.
- Envia para o modelo (Gemini) e retorna a resposta em linguagem natural.
