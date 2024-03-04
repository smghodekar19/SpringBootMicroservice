Using Gateway :- 
Fetching Product Details :
http://localhost:8081/api/product

Adding Product :- POST Request

{
    "name":"Apple Phone",
    "description":"Branch new Samsung latest featured phone",
    "skuCode":"iphone_13_red_128_gb",
    "price":"790.01",
    "isSellable":"true"
}

Delete product by Id :- 
http://localhost:8081/api/product/102


Fetching Inventory Details
http://localhost:8081/api/inventory

Creating Order :: 
POST - http://localhost:8081/api/order

Request : - 
{
    "orderItems":[
        {
            "skuCode":"iphone_13_red_128_gb",
            "quantity":"5"
        }
    ]
}


Docker containers with there ports :
Inventory Service - 10000
Eureka Service - 8761
Order Service - 8500
Product Service - 8000
API Gateway - 8081
Zipkin Server - 9411

Run Servers - docker-compose up
