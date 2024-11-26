Criar o Banco de Dados (Produção)

Configure o PostgreSQL no arquivo application.yml para produção.
Use o script de criação das tabelas gerado automaticamente pelo JPA.
Subir o Kafka

Utilize Docker para rodar o Kafka e o Zookeeper:

```bash
docker-compose up -d
```

Testar o Serviço

Use ferramentas como Postman para testar os endpoints REST:

POST /api/pedidos: Criar um novo pedido.

GET /api/pedidos: Listar todos os pedidos.
