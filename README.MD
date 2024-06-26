
# Api para agência de viagem

API RESTful usando Java e Spring Boot que ofereça as funcionalidades mencionadas. Você deve criar endpoints com os métodos HTTP apropriados (GET, POST, PUT, PATCH e DELETE) e implementar uma camada de serviço para processar as solicitações, seguindo as especificações.

1 - Criar viagem
```
    POST (/viagens)
    {
        "destino" : "Torre Eifel",
        "localizacao" : "Paris"
    }

    POST (/viagens)
    {
        "destino" : "Disney",
        "localizacao" : "Orlando"
    }
```

2 - Buscar todas as viagens
```
    GET (/viagens)

```

3 - Buscar todas as viagens
```
    GET (/viagens/filtro/{filtro})
    Exemplo: /viagens/filtro/Orlando
    Exemplo: /viagens/filtro/Eifel
```

4 - Buscar destino especifico
```
    GET (/viagens/id/{id})
    Exemplo: /viagens/filtro/1
```

5 - Buscar destino especifico
```
    GET (/viagens/id/{id})
    Exemplo: /viagens/filtro/1
```

6 - Avaliar destino
```
    POST (viagens/rate)
    {
        "id" : 1,
        "rating" : 5.0
    }
    
    POST (viagens/rate)
    {
        "id" : 1,
        "rating" : 10.0
    }
```

6 - Remover destino
```
    DELETE (viagens/id/{id})
    Exemplo: viagens/id/1
```