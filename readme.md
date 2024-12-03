### Ligar Docker Desktop

`Entrar na pasta e ligar o container Kafka`

```shell
$ cd src/main/java/com/order/demo/config 
$ docker-compose up -d
Container zookeeper  Running
Container kafka  Running
```



### **Requisição**


#### **1. Produtos**

#### **URL**  
`http://localhost:8080/api/produtos`

#### **Método**  
`POST`


**Request:**  
```json
{
  "nome": "Camiseta1",
  "preco": 30.00,
  "quantidade": 2,
  "estoque":5 
}

```

#### **2. Pedidos**

#### **URL**  
`http://localhost:8080/api/pedidos`

#### **Método**  
`POST`


**Request:**  
```json
{
  "pedidoProdutos": [
    {
      "produto": [
        {
          "produto_id": 1,
          "quantidade": 1
        },
        {
          "produto_id": 2,
          "quantidade": 2
        }
      ]
    }
  ]
}


```


---

### **Respostas**

**Resposta:**  
```plaintext
400 BAD REQUEST

Pedido não contém produtos.
```

```plaintext
400 BAD REQUEST

Produto com ID x possui apenas x em estoque.
```

```plaintext
400 BAD REQUEST

Produto com ID X não encontrado
```


```plaintext
500 INTERNAL SERVER ERROR

Erro ao processar o pedido:
```


### **Headers recomendados**
Inclua os seguintes headers na requisição para melhor segurança e rastreamento:
```plaintext
Content-Type: application/json
Accept: application/json
```